package tga.hibernate_experiments.collections.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Access(AccessType.FIELD)
@Data @AllArgsConstructor @NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    int id;

    @ManyToOne
    Owner owner;

    int n;
    String st;
}
