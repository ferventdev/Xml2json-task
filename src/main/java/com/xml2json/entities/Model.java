package com.xml2json.entities;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;

@Getter
public class Model {

    @JacksonXmlProperty(localName = "Category", isAttribute = true)
    private String category;

    @JacksonXmlProperty(localName = "Make", isAttribute = true)
    private String brand;

    @JacksonXmlProperty(localName = "Model", isAttribute = true)
    private String name;
}
