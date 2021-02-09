package tga.hibernate_experiments.collections;

import java.util.Arrays;

import org.junit.Test;

import lombok.val;
import tga.hibernate_experiments.collections.domain.Item;
import tga.hibernate_experiments.collections.domain.Owner;
import tga.hibernate_experiments.utils.TestsWithHibernate;

public class CollectionTests extends TestsWithHibernate {

    public CollectionTests() {
        super(Arrays.asList(Owner.class, Item.class), false);
    }


    @Test public void createTables() { }

/*
    void fillData() {

        val o1 = new Owner(0, "Owner-1", null);
        val i1 = new Item(0, o1, "item-1-1");
        val i2 = new Item(0, o1, "item-1-2");
        val i3 = new Item(0, o1, "item-1-3");
        o1.setItems();

    }
*/


}
