package tga.hibernate_experiments.cache.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import tga.hibernate_experiments.cache.GsonUtils;
import tga.hibernate_experiments.cache.model.in_memory.ReportAttributes;

import javax.persistence.*;
import java.util.Map;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Cacheable
@org.hibernate.annotations.Cache( usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Report {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @LazyToOne(LazyToOneOption.PROXY)
    private ReportXml reportXml;

    @Transient
    private ReportAttributes attrs;

    public Report() {
    }

    public Report(ReportXml reportXml) {
        this.reportXml = reportXml;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public ReportXml getReportXml() {
        return reportXml;
    }

    private void setReportXml(ReportXml reportXml) {
        this.reportXml = reportXml;
    }

    public ReportAttributes getAttrs() {
        if (attrs == null) {
            String xml = this.reportXml.getXml();
            Map<String, Integer> parseAttrs = GsonUtils.gson.fromJson(xml, GsonUtils.empMapStringToIntType);
            attrs = new ReportAttributes(parseAttrs);
        }
        return attrs;
    }

    private void setAttrs(ReportAttributes attrs) {
        this.attrs = attrs;
    }

}
