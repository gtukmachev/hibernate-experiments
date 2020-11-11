package tga.hibernate_experiments.map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import tga.hibernate_experiments.map.model.Car;
import tga.hibernate_experiments.map.model.CarRef;
import tga.hibernate_experiments.map.model.Human;
import tga.hibernate_experiments.utils.TestsWithHibernate;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class MapLazyTests extends TestsWithHibernate {

    private static final Logger log = LoggerFactory.getLogger(TestsWithHibernate.class);

    public MapLazyTests() {
        super(Arrays.asList(Human.class, CarRef.class, Car.class));
    }

    private Human gHuman;
    private Car  gCar1;
    private Car  gCar2;
    private Car  gCar3;
    private CarRef gCarRef1;
    private CarRef gCarRef2;
    private CarRef gCarRef3;

    private void initData() {
        gHuman = new Human("user-map-1");
        gCar1 = new Car("toyota", "1111", Instant.now().minus(365  , ChronoUnit.DAYS));
        gCar2 = new Car("subaru", "2222", Instant.now().minus(365*2, ChronoUnit.DAYS));
        gCar3 = new Car("honda",  "3333", Instant.now().minus(365*3, ChronoUnit.DAYS));
        gCarRef1 = gHuman.addCar("2020-09", gCar1);
        gCarRef2 = gHuman.addCar("2020-10", gCar2);
        gCarRef3 = gHuman.addCar("2020-11", gCar3);

        session.save(gHuman);

        MDC.put("lp", "Global Objects :");
            log.info("gHuman   = {}", gHuman);
            log.info("gCar1    = {}", gCar1);
            log.info("gCar2    = {}", gCar2);
            log.info("gCar3    = {}", gCar3);
            log.info("gCarRef1 = {}", gCarRef1);
            log.info("gCarRef2 = {}", gCarRef2);
            log.info("gCarRef3 = {}", gCarRef3);
        MDC.put("lp", "");
        commitAndReopenSession();
    }


    protected void readCarRef(CarRef carRef) {
        MDC.put("lp", "CarRef(id=??) :");

        String carRefMonth = withLog("carRefMonth = carRef.getMonth()", carRef::getMonth);
        Human  owner       = withLog("owner = carRef.getOwner()......", carRef::getOwner);
        int    ownerId     = withLog("ownerId = owner.getId()........", owner::getId);

        MDC.put("lp", "CarRef("+ownerId+":"+carRefMonth+") :");

        withLog("ownerName = owner.getName()", owner::getName);

        Car car = withLog("Car car = carRef.getCar()", carRef::getCar);
                  withLog("carId = car.getId()  ", car::getId);
                  withLog("carNum = car.getNum()", car::getNum);

        MDC.put("lp", "");
    }


    @Test
    public void load_carRef_by_byMonthAndHuman() {
        initData();

        int humanId = gHuman.getId();
        String month = gCarRef1.getMonth();

        CarRef key = new CarRef(new Human(humanId), month);

        CarRef carRef = withLog("session.get(CarRef.class, CarRef(new Human("+humanId+"), "+month+")  )", () -> session.get(CarRef.class, key) );

        readCarRef(carRef);
    }



    @Test
    public void test_collection_size_then_getFirst() {
        initData();

        Human human = getById(Human.class, gHuman.getId());

        String m = gCarRef1.getMonth();

        CarRef carRef = withLog("carRef = human.getMonth("+m+")", () -> human.getCarRefs().get( m ));
        readCarRef(carRef);

    }

    @Test
    public void test_collection_travers_by_index() {
        initData();

        Human human = getById(Human.class, gHuman.getId());

        String[] keys = new String[]{gCarRef1.getMonth(), gCarRef2.getMonth(), gCarRef3.getMonth() };

        for (int i = 0; i < 3; i++) {
            String key = keys[i];
            CarRef carRef = withLog("carRef = human.getCarRefs().get(\""+key+"\")", () -> human.getCarRefs().get(key));
            readCarRef(carRef);
        }
    }

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
