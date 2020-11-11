package tga.hibernate_experiments.list.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Card {
    @Id @GeneratedValue(strategy = IDENTITY)
    private int id;
    private String pinCode;
    private String num;
    private Instant term;

    public Card() {
    }

    public Card(String pinCode, String num, Instant term) {
        this.pinCode = pinCode;
        this.num = num;
        this.term = term;
    }

    public Card(int id, String pinCode, String num, Instant term) {
        this.id = id;
        this.pinCode = pinCode;
        this.num = num;
        this.term = term;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Instant getTerm() {
        return term;
    }

    public void setTerm(Instant term) {
        this.term = term;
    }
}
