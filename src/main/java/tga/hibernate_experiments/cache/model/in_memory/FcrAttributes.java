package tga.hibernate_experiments.cache.model.in_memory;

import java.util.Map;

public class FcrAttributes {

    private Map<String, Integer> parsedXML;

    public FcrAttributes(Map<String, Integer> parsedXML) {
        this.parsedXML = parsedXML;
    }


}
