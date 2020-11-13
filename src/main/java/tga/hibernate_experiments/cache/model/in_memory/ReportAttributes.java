package tga.hibernate_experiments.cache.model.in_memory;

import java.util.Map;

public class ReportAttributes {

    private Map<String, Integer> parsedXML;

    private CreditAttributes creditAttributes;
    private FcrAttributes fcrAttributes;

    public ReportAttributes(Map<String, Integer> parsedXML) {
        this.parsedXML = parsedXML;
    }

    public CreditAttributes getCreditAttributes() {
        if (creditAttributes == null) {
            creditAttributes = new CreditAttributes(parsedXML);
            removeParsedDataIfAllLazyPropertiesAreCalculated();
        }
        return creditAttributes;
    }

    public FcrAttributes getFcrAttributes() {
        if (fcrAttributes == null) {
            fcrAttributes = new FcrAttributes(parsedXML);
            removeParsedDataIfAllLazyPropertiesAreCalculated();
        }
        return fcrAttributes;
    }

    private void removeParsedDataIfAllLazyPropertiesAreCalculated() {
        if (creditAttributes != null && fcrAttributes != null) {
            parsedXML = null;
        }
    }
}
