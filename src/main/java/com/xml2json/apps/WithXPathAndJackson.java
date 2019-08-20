package com.xml2json.apps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class WithXPathAndJackson {

    private static final String XML_URL = "http://autoload.avito.ru/format/Models.xml";
    private static final DocumentBuilder BUILDER = getDocumentBuilder();
    private static final XPath X_PATH = XPathFactory.newInstance().newXPath();
    private static final String BRAND = "Make";
    private static final String MODEL = "Model";
    private static final ObjectWriter PRETTY_PRINTER = new ObjectMapper().writerWithDefaultPrettyPrinter();


    @SneakyThrows
    public static void main(String[] args) {
        Document document = BUILDER.parse(XML_URL);
        XPathExpression expression = X_PATH.compile("/Models/Model");
        NodeList nodes = (NodeList) expression.evaluate(document, XPathConstants.NODESET);
        String json = PRETTY_PRINTER.writeValueAsString(getGroupedModels(nodes));
        System.out.println(json);
    }


    private static Map<String, Set<String>> getGroupedModels(NodeList nodes) {
        return IntStream.range(0, nodes.getLength())
                        .mapToObj(nodes::item)
                        .map(Node::getAttributes)
                        .filter(Objects::nonNull)
                        .filter(WithXPathAndJackson::keyAttributesPresent)
                        .collect(groupingBy(attrs -> attrs.getNamedItem(BRAND).getNodeValue(),
                                LinkedHashMap::new,
                                mapping(attrs -> attrs.getNamedItem(MODEL).getNodeValue(),
                                        toCollection(LinkedHashSet::new))));
    }

    private static boolean keyAttributesPresent(NamedNodeMap attributes) {
        return attributes.getNamedItem(BRAND) != null && attributes.getNamedItem(MODEL) != null;
    }

    @SneakyThrows
    private static DocumentBuilder getDocumentBuilder() {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }
}
