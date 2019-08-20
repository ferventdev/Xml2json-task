package com.xml2json.apps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.xml2json.entities.Catalog;
import lombok.SneakyThrows;

import java.net.URI;

public class WithJacksonOnly {

    private static final String XML_URL = "http://autoload.avito.ru/format/Models.xml";
    private static final ObjectWriter PRETTY_PRINTER = new ObjectMapper().writerWithDefaultPrettyPrinter();


    @SneakyThrows
    public static void main(String[] args) {
        Catalog catalog = new XmlMapper().readValue(new URI(XML_URL).toURL(), Catalog.class);
        String json = PRETTY_PRINTER.writeValueAsString(catalog);
        System.out.println(json);
    }
}
