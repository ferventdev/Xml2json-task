package com.xml2json.entities;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Model {

//    @JsonProperty("Category")
    @JacksonXmlProperty(localName = "Category", isAttribute = true)
    private String category;

//    @JsonProperty("Make")
    @JacksonXmlProperty(localName = "Make", isAttribute = true)
    private String producer;

//    @JsonProperty("Model")
    @JacksonXmlProperty(localName = "Model", isAttribute = true)
    private String name;
}
