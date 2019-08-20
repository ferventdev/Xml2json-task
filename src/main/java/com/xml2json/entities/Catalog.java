package com.xml2json.entities;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@JacksonXmlRootElement(localName = "Models")
public class Catalog {

    @JacksonXmlProperty(localName = "Model")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Model> models = new ArrayList<>();
}
