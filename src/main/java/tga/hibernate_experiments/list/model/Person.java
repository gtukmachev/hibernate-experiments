package tga.hibernate_experiments.list.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Person {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    private String name;

//        @IndexColumn(name = "index", base = 1)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    @Cascade(CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<CardRef> cardRefs;

    public Person() {
    }

    public Person(String name, List<CardRef> cardRefs) {
        this.name = name;
        this.cardRefs = cardRefs;
    }

    public Person(int id, String name, List<CardRef> cardRefs) {
        this.id = id;
        this.name = name;
        this.cardRefs = cardRefs;
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

    public List<CardRef> getCardRefs() {
        return cardRefs;
    }

    public void setCardRefs(List<CardRef> cardRefs) {
        this.cardRefs = cardRefs;
    }
}
