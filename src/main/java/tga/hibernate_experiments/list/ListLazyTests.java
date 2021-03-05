package tga.hibernate_experiments.list;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import tga.hibernate_experiments.list.model.Card;
import tga.hibernate_experiments.list.model.CardRef;
import tga.hibernate_experiments.list.model.Person;
import tga.hibernate_experiments.utils.HibernateConfiguration;
import tga.hibernate_experiments.utils.TestsWithHibernate;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ListLazyTests extends TestsWithHibernate {

    private static final Logger log = LoggerFactory.getLogger(TestsWithHibernate.class);

    public ListLazyTests() {
        super(
                Arrays.asList(Person.class, CardRef.class, Card.class),
                HibernateConfiguration.no_l2_cache
        );
    }

    int personId = 0;
    int cardRef1Id = 0;
    int cardRef2Id = 0;
    int cardRef3Id = 0;
    int card1Id = 0;
    int card2Id = 0;
    int card3Id = 0;

    private void initData() {
        Person person = new Person("user-1", new ArrayList<>(3));

        Card card1 = new Card("1234", "1", Instant.now().plus(1, ChronoUnit.DAYS));
        Card card2 = new Card("2345", "2", Instant.now().plus(1, ChronoUnit.DAYS));
        Card card3 = new Card("3456", "3", Instant.now().plus(1, ChronoUnit.DAYS));

        CardRef cardRef1 = new CardRef(person, card1);
        CardRef cardRef2 = new CardRef(person, card2);
        CardRef cardRef3 = new CardRef(person, card3);

        person.getCardRefs().add(cardRef1);
        person.getCardRefs().add(cardRef2);
        person.getCardRefs().add(cardRef3);

        session.save(person);

        cardRef1Id = cardRef1.getId();
        cardRef2Id = cardRef2.getId();
        cardRef3Id = cardRef3.getId();

        card1Id = card1.getId();
        card2Id = card2.getId();
        card3Id = card3.getId();

        personId = person.getId();

        commitAndReopenSession();
    }

    protected void readCardRef(CardRef cardRef) {
        MDC.put("lp", "CardRef(id=??) :");

        int cardRefId = withLog("cardRefId = cardRef.getId()", cardRef::getId);
        MDC.put("lp", "CardRef(id=" + cardRefId + ") :");

        Person owner     = withLog("owner = cardRef.getOwner() ", cardRef::getOwner);
        int    ownerId   = withLog("ownerId = owner.getId()    ", owner::getId  , true);
        String ownerName = withLog("ownerName = owner.getName()", owner::getName, true);

        Card card = withLog("Card card = cardRef.getCard()", cardRef::getCard);

        int    cardId  = withLog("cardId = card.getId()  ", card::getId , true);
        String cardNum = withLog("cardNum = card.getNum()", card::getNum, true);

        MDC.put("lp", "");
    }

    @Test public void getById_loads_entity() {
        initData();
        CardRef cardRef = getById(CardRef.class, cardRef1Id);
        readCardRef(cardRef);

        log.warn("---------------------------------------------------------------------------------------------------");
        commitAndReopenSession();

        CardRef cardRef1 = getById(CardRef.class, cardRef1Id);
        readCardRef(cardRef1);
    }

    @Test public void loadById_creates_lazy_proxy() {
        initData();
        CardRef cardRef = loadById(CardRef.class, cardRef1Id);
        readCardRef(cardRef);
    }

    @Test public void test_collection_size_then_getFirst() {
        initData();

        Person p = withLog       ("getById(Person.class, "+personId+")", () -> getById(Person.class, personId) , false);
        withLog                  ("person.getCardRefs().size()",         () -> p.getCardRefs().size()          , true);
        CardRef cardRef = withLog("cardRef = p.getCardRefs().get(0)",    () -> p.getCardRefs().get(0)          , false);
        readCardRef(cardRef);
    }

    private void readItemsByIndex() {
        Person p = getById(Person.class, personId);

        for (int i = 0; i < 3; i++) {
            log.info("cardRef"+i+" = p.getCardRefs().get("+i+")....");
            CardRef cardRef = p.getCardRefs().get(i);
            log.info("cardRef"+i+" = p.getCardRefs().get("+i+").... done: {}", cardRef);
            readCardRef(cardRef);
        }
    }

    @Test public void test_collection_travers_by_index() {
        initData();

        readItemsByIndex();
        commitAndReopenSession();

        log.warn("---------------------------------------------------------------------------------------------------");
        readItemsByIndex();
        commitAndReopenSession();

        log.warn("---------------------------------------------------------------------------------------------------");
        Card card2 = getById(Card.class, card2Id);
        card2.setNum("NEW-NUM");
        commitAndReopenSession();

        log.warn("---------------------------------------------------------------------------------------------------");
        readItemsByIndex();
        commitAndReopenSession();

    }

    @Test public void test_collection_travers_through_iterator() {
        initData();

        Person p = getById(Person.class, personId);
        Iterator<CardRef> cardRefIterator =
                withLog("cardRefIterator = p.getCardRefs().iterator()",
                        () -> p.getCardRefs().iterator()
                );

        while (cardRefIterator.hasNext()) {
            CardRef cardRef = withLog("cardRef = cardRefIterator.next()", cardRefIterator::next);

            readCardRef(cardRef);
        }

    }

}
