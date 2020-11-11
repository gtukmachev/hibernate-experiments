package tga.hibernate_experiments.map.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Human implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    private String name;

//        @IndexColumn(name = "index", base = 1)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    @MapKey(name = "month")
    @Cascade(CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    private Map<String, CarRef> carRefs;

    public Human() {
    }

    public Human(int id) {
        this.id = id;
    }

    public Human(String name) {
        this.name = name;
    }

    public Human(int id, String name, Map<String, CarRef> carRefs) {
        this.id = id;
        this.name = name;
        this.carRefs = carRefs;
    }

    public CarRef addCar(String month, Car car) {
        CarRef carRef = new CarRef(this, month, car);
        this.getCarRefs().put(month, carRef);
        return carRef;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, CarRef> getCarRefs() {
        if (carRefs == null) carRefs = new HashMap<>();
        return carRefs;
    }

    void setCarRefs(Map<String, CarRef> carRefs) {
        this.carRefs = carRefs;
    }
}
