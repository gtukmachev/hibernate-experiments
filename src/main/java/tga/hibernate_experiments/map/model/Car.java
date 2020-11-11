package tga.hibernate_experiments.map.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.Instant;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Car implements Serializable {
    @Id @GeneratedValue(strategy = IDENTITY)
    private int id;
    private String mark;
    private String num;
    private Instant createdAt;

    public Car() {
    }

    public Car(String mark, String num, Instant createdAt) {
        this.mark = mark;
        this.num = num;
        this.createdAt = createdAt;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
