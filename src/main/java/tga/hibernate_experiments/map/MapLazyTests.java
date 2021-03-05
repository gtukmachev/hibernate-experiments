package tga.hibernate_experiments.map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import tga.hibernate_experiments.map.model.Car;
import tga.hibernate_experiments.map.model.CarRef;
import tga.hibernate_experiments.map.model.Human;
import tga.hibernate_experiments.utils.HibernateConfiguration;
import tga.hibernate_experiments.utils.TestsWithHibernate;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class MapLazyTests extends TestsWithHibernate {

    private static final Logger log = LoggerFactory.getLogger(TestsWithHibernate.class);

    public MapLazyTests() {
        super(
            Arrays.asList(Human.class, CarRef.class, Car.class),
            HibernateConfiguration.no_l2_cache
        );
    }

    private Human gHuman;
    private Car  gCar1;
    private Car  gCar2;
    private Car  gCar3;

    private void initData() {
        gHuman = new Human("user-map-1");
        gCar1 = new Car("toyota", "1111", Instant.now().minus(365  , ChronoUnit.DAYS));
        gCar2 = new Car("subaru", "2222", Instant.now().minus(365*2, ChronoUnit.DAYS));
        gCar3 = new Car("honda",  "3333", Instant.now().minus(365*3, ChronoUnit.DAYS));
        gHuman.putCar("2020-09", gCar1);
        gHuman.putCar("2020-10", gCar2);
        gHuman.putCar("2020-11", gCar3);

        session.save(gHuman);

        MDC.put("lp", "Global Objects :");
            log.info("gHuman   = {}", gHuman);
            log.info("gCar1    = {}", gCar1);
            log.info("gCar2    = {}", gCar2);
            log.info("gCar3    = {}", gCar3);
        MDC.put("lp", "");
        commitAndReopenSession();
    }


    protected void readCarRef(CarRef carRef) {
        MDC.put("lp", "CarRef(id=??) :");

        String carRefMonth = withLog("carRefMonth = carRef.getMonth()", carRef::getMonth);
        log.warn("carRefMonth = {}", carRefMonth);

        Human  owner       = withLog("owner = carRef.getOwner()......", carRef::getOwner);
        int    ownerId     = withLog("ownerId = owner.getId()........", owner::getId);
        log.warn("ownerId = {}", ownerId);

        MDC.put("lp", "CarRef("+ownerId+":"+carRefMonth+") :");

        String ownerName = withLog("ownerName = owner.getName()", owner::getName);
        log.warn("ownerName = {}", ownerName);

        Car car = withLog("Car car = carRef.getCar()", carRef::getCar);
            int carId = withLog("carId = car.getId()  ", car::getId);
            log.warn("carId = {}", carId);
            String carNum = withLog("carNum = car.getNum()", car::getNum);
            log.warn("carNum = {}", carNum);

        MDC.put("lp", "");
    }


    @Test
    public void load_carRef_by_byMonthAndHuman() {
        initData();

        int humanId = gHuman.getId();
        String month = "2020-09";

        CarRef key = new CarRef(new Human(humanId), month);

        CarRef carRef = withLog("session.get(CarRef.class, CarRef(new Human("+humanId+"), "+month+")  )", () -> session.get(CarRef.class, key) );

        readCarRef(carRef);
    }

    @Test
    public void load_HumanFirst_then_carRef_by_byMonthAndHuman() {
        initData();

        int humanId = gHuman.getId();
        String month = "2020-09";
        CarRef key = new CarRef(new Human(humanId), month);

        Human human = getById(Human.class, humanId);
        log.warn("human.getName() = {}", human.getName());

        CarRef carRef = withLog("session.get(CarRef.class, CarRef(new Human("+humanId+"), "+month+")  )", () -> session.get(CarRef.class, key) );

        readCarRef(carRef);
        log.warn("--------------------");
        log.warn("            human = {}, id={}, name={}", human, human.getId(), human.getName());
        log.warn("carRef.getOwner() = {}, id={}, name={}", carRef.getOwner(), carRef.getOwner().getId(), carRef.getOwner().getName());
        log.warn("key.getOwner()    = {}, id={}, name={}", key.getOwner(), key.getOwner().getId(), key.getOwner().getName());
        log.info("-----------------------------------------------------");
        log.warn("");
    }

    @Test
    public void test_collection_size_then_getFirst() {
        initData();

        Human human = getById(Human.class, gHuman.getId());

        String m = "2020-09";

        CarRef carRef = withLog("carRef = human.getMonth("+m+")", () -> human.getCarRefs().get( m ));
        readCarRef(carRef);

    }

    @Test
    public void test_collection_travers_by_index() {
        initData();

        Human human = getById(Human.class, gHuman.getId());

        String[] keys = new String[]{"2020-09", "2020-10", "2020-11" };

        for (int i = 0; i < 3; i++) {
            String key = keys[i];
            CarRef carRef = withLog("carRef = human.getCarRefs().get(\""+key+"\")", () -> human.getCarRefs().get(key));
            readCarRef(carRef);
        }
    }


    private void printHumanCars(Human human){
        int humanId = human.getId();
        String humanName = human.getName();

        human.getCarRefs().values().forEach(carRef -> {
            Car car = carRef.getCar();
            log.info("Human(id={}, name='{}').car[{}] -> Car(id={}, mark='{}', num='{}')",
                    humanId, humanName,
                    carRef.getMonth(),
                    car.getId(), car.getMark(), car.getNum()
            );
        });

    }

    @Test
    public void deleteCar(){
        initData();

        Human human = getById(Human.class, gHuman.getId());
        printHumanCars(human);

        CarRef existedCarRef = human.getCarRefs().remove("2020-11");

        session.delete(existedCarRef);
        printHumanCars(human);
    }

    @Test
    public void replaceCar(){
        initData();

        Human human = getById(Human.class, gHuman.getId());
        log.info("$$$$$$$$$$$$  initial $$$$$$$$$$$$$$$$$$$$$");
        printHumanCars(human);

        Car newCar = new Car("Mitsuki", "m12-ab", Instant.now().minus(30*3, ChronoUnit.DAYS));
        Car oldCar = human.putCar( "2020-11", newCar );
        if (oldCar != null) session.delete(oldCar);


        log.info("$$$$$$$$$$$$  after the replacement $$$$$$$$$$$$$$$$$$$$$");
        printHumanCars(human);

        commitAndReopenSession();

        Human h1 = getById(Human.class, gHuman.getId());
        log.info("$$$$$$$$$$$$  in a new session $$$$$$$$$$$$$$$$$$$$$");
        printHumanCars(h1);
        printHumanCars(h1);


    }



/*    @Test
    public void add_new_car_instead_of_previous(){
        initData();

        Human human = getById(Human.class, gHuman.getId());
        printHumanCars(human);

        CarRef existedCarRef = human.getCarRefs().get("2020-11");
        Car existedCar = existedCarRef.getCar();
        session.delete(existedCar);


    }*/


/*    @Test
    public void test_collection_travers_through_iterator() {
        initData();

        User p = getById(User.class, personId);
        Iterator<CarRef> carRefIterator =
                withLog("carRefIterator = p.getRefs().iterator()",
                        () -> p.getCarRefs().iterator()
                );

        while (carRefIterator.hasNext()) {
            CarRef carRef = withLog("carRef = carRefIterator.next()", carRefIterator::next);

            readCarRef(carRef);
        }

    }
*/

}
