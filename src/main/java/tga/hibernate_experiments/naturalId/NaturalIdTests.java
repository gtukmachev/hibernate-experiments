package tga.hibernate_experiments.naturalId;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.val;
import tga.hibernate_experiments.naturalId.model.TwoKeysFoos;
import tga.hibernate_experiments.utils.TestsWithHibernate;

public class NaturalIdTests extends TestsWithHibernate {

    private static final Logger logger = LoggerFactory.getLogger(NaturalIdTests.class);

    public NaturalIdTests() {
        super(Collections.singletonList(TwoKeysFoos.class), false);
    }

    @Test
    public void makeSchema(){ }

    @Test
    public void saveAndLoad(){
        val uniquePrefix = "" + System.currentTimeMillis();

        val e11_ = new TwoKeysFoos(1, uniquePrefix + "path-1", 11);
        val e12_ = new TwoKeysFoos(1, uniquePrefix + "path-2", 12);
        val e21_ = new TwoKeysFoos(2, uniquePrefix + "path-1", 21);
        val e22_ = new TwoKeysFoos(2, uniquePrefix + "path-2", 22);

        session.save(e11_);
        session.save(e12_);
        session.save(e21_);
        session.save(e22_);

        commitAndReopenSession();

        val e21 = session.byNaturalId(TwoKeysFoos.class)
            .using("ke1", 2)
            .using("ke2", uniquePrefix + "path-1")
            .load();

        assertEquals(e21_, e21);
    }

}
