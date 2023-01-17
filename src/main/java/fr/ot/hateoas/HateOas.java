package fr.ot.hateoas;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class HateOas {

    @Getter
    Map<String, String> links;

    public HateOas(){
        links = new HashMap<>();
    }

    public void addLink(String key, String link){
        links.put(key, link);
    }
}