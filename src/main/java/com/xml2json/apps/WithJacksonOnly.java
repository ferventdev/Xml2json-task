package com.xml2json.apps;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.xml2json.entities.Catalog;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class WithJacksonOnly {

    private static final String XML_URL = "http://autoload.avito.ru/format/Models.xml";

    public static void main(String[] args) throws URISyntaxException, IOException {
        XmlMapper xmlMapper = new XmlMapper();
        Catalog catalog = xmlMapper.readValue(new URI(XML_URL).toURL(), Catalog.class);

        int a = 0;
        a++;
    }
}
