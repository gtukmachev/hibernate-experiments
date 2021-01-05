package tga.hibernate_experiments.ids.domain;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity @Access(AccessType.FIELD)
@Getter @Setter @ToString
public class Hist implements Serializable {

    @Id @ManyToOne Usr usr;
    @Id            int month; //yyyymm

    Integer abc;
    Integer qwe;

}
