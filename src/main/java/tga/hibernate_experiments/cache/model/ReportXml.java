package tga.hibernate_experiments.cache.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class ReportXml {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    private String xml;

    public ReportXml() {
    }

    public ReportXml(String xml) {
        this.xml = xml;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getXml() {
        return xml;
    }

    private void setXml(String xml) {
        this.xml = xml;
    }
}
