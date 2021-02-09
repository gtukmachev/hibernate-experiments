package tga.hibernate_experiments.collections.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Access(AccessType.FIELD)
@Data @AllArgsConstructor @NoArgsConstructor
public class Owner {

    @Id @GeneratedValue(strategy = IDENTITY)
    int id;
    String name;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    List<Item> items;

}
