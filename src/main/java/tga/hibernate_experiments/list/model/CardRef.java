package tga.hibernate_experiments.list.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Cacheable
@Cache( usage = CacheConcurrencyStrategy.READ_WRITE)
public class CardRef {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.PROXY)
    private Person owner;

    @Cascade(CascadeType.ALL)
    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.PROXY)
    private Card card;

    public CardRef() {
    }

    public CardRef(int id, Person owner, Card card) {
        this.id = id;
        this.owner = owner;
        this.card = card;
    }

    public CardRef(Person owner, Card card) {
        this.owner = owner;
        this.card = card;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
