package tga.hibernate_experiments.naturalId.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TwoKeysFoos {

    @Id @GeneratedValue(strategy = IDENTITY)
    int id;

    @NaturalId int ke1;
    @NaturalId String ke2;

    int position;

    public TwoKeysFoos(int ke1, String path, int position) {
        this.ke1 = ke1;
        this.ke2 = path;
        this.position = position;
    }
}
