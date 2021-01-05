package tga.hibernate_experiments.ids.domain;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity @Access(AccessType.FIELD)
@Setter @Getter @ToString
public class Usr implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) int id;
    String name;
}
