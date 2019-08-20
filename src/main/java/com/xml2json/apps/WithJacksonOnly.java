package com.xml2json.apps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.xml2json.entities.Catalog;
import com.xml2json.entities.Model;
import lombok.SneakyThrows;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.*;

public class WithJacksonOnly {

    private static final String XML_URL = "http://autoload.avito.ru/format/Models.xml";
    private static final ObjectWriter PRETTY_PRINTER = new ObjectMapper().writerWithDefaultPrettyPrinter();


    @SneakyThrows
    public static void main(String[] args) {
        Catalog catalog = new XmlMapper().readValue(new URI(XML_URL).toURL(), Catalog.class);
        String json = PRETTY_PRINTER.writeValueAsString(getGroupedModels(catalog));
        System.out.println(json);
    }


    private static Map<String, Set<String>> getGroupedModels(Catalog catalog) {
        return catalog.getModels().stream()
                      .collect(groupingBy(Model::getBrand,
                              LinkedHashMap::new, mapping(Model::getName, toCollection(LinkedHashSet::new))));
    }
}
