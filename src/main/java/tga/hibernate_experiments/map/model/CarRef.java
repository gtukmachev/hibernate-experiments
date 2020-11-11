package tga.hibernate_experiments.map.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CarRef implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.PROXY)
    private Human owner;

    @Id
    private String month; //format: "yyyy-mm" - year & month

    @Cascade(CascadeType.ALL)
    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.PROXY)
    private Car car;

    public CarRef() {
    }

    public CarRef(Human owner, String month) {
        this.owner = owner;
        this.month = month;
    }

    public CarRef(Human owner, String month, Car car) {
        this.owner = owner;
        this.month = month;
        this.car = car;
    }

    public Human getOwner() {
        return owner;
    }

    void setOwner(Human owner) {
        this.owner = owner;
    }

    public String getMonth() {
        return month;
    }

    void setMonth(String month) {
        this.month = month;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
