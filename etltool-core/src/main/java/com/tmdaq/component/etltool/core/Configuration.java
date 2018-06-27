package com.tmdaq.component.etltool.core;

import com.tmdaq.component.etltool.handler.TypeHandler;
import com.tmdaq.component.json.JsonHolder;
import com.tmdaq.component.json.jsonwapper.Json;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vttmlin
 */
@Slf4j
public class Configuration {
    private Json jsonExecutor = null;
    private Map<String, TypeHandler> typeHandlerMap = new HashMap<>();
    private Map<String, TypeHandler> typeAliases = new HashMap<>();
    private Map<String, Convert> map = new HashMap<>();
    private XPath xPath;
    private Document document;
    private InputSource inputSource;

    public Configuration(InputSource inputSource) {
        this.inputSource = inputSource;
    }

    public Configuration(InputStream inputStream) {
        this.inputSource = new InputSource(inputStream);
    }

    public Configuration parse() throws XPathExpressionException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            document = documentBuilder.parse(inputSource);
            xPath = XPathFactory.newInstance().newXPath();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            log.error(e.getMessage(), e.getCause());
        }
        parseExecutor(getNode("/configuration/jsonExecutor"));
        parseTypeAliases(getNode("/configuration/typeAliases"));
        parseConverts(getNode("/configuration/converts"));
        return this;
    }

    private void parseExecutor(Node node) {
        if (node != null && node.getNodeName() != null && "jsonExecutor".equalsIgnoreCase(node.getNodeName()) && node.getTextContent() != null) {
            switch (node.getTextContent().toUpperCase()) {
                case "FASTJSON":
                    jsonExecutor = JsonHolder.instance;
                    break;
                case "JSON":
                    jsonExecutor = JsonHolder.instance;
                    break;
                case "JSONLIB":
                    jsonExecutor = JsonHolder.instance;
                    break;
                case "JACKSON":
                    jsonExecutor = JsonHolder.instance;
                    break;
                case "GSON":
                    jsonExecutor = JsonHolder.instance;
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 这个地方临时备注下 原本要用这种写法写的
     * boolean autowired = getText(node, "auto") == null ? true : "true".equalsIgnoreCase(getText(node, "auto"));
     * 但是idea把我纠正成了下种写法 阿里巴巴的代码检查又不让我放在代码块中 所以就暂时放在这 看有没有问题
     */
    private void parseConverts(Node root) {
        List<Node> childNode = getChildNode(root.getChildNodes());
        Convert convert;
        for (Node node : childNode) {
            if (node != null && node.getNodeName() != null && "convert".equalsIgnoreCase(node.getNodeName())) {
                String id = getText(node, "id");
                boolean autowired = getText(node, "auto") == null || "true".equalsIgnoreCase(getText(node, "auto"));
                List<Field> fieldList = getFiledList(getChildNode(node.getChildNodes()));
                convert = new Convert(id, autowired, fieldList);
                map.put(id, convert);
            }
        }
    }

    private void parseTypeAliases(Node root) {
        if (root != null && root.getChildNodes() != null && root.getChildNodes().getLength() > 0) {
            List<Node> childNode = getChildNode(root.getChildNodes());
            for (Node node : childNode) {
                if ("typeAlias".equalsIgnoreCase(node.getNodeName())) {

                    String type = getText(node, "type");
                    TypeHandler typeHandler = null;
                    try {
                        typeHandler = (TypeHandler) Class.forName(type).newInstance();
                    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                        log.error(e.getMessage(), e.getCause());
                    }
                    typeAliases.put(getText(node, "alias"), typeHandler);
                }
            }
        }
    }

    private List<Field> getFiledList(List<Node> childNode) {
        List<Field> list = new ArrayList<>();
        for (Node node : childNode) {
            if ("field".equalsIgnoreCase(node.getNodeName())) {
                list.add(new Field(getText(node, "src"), getText(node, "dest"), getText(node, "typeHandle")));
            } else if ("fieldRef".equalsIgnoreCase(node.getNodeName())) {
                list.add(new Field(getText(node, "src"), getText(node, "dest"), typeAliases.get(getText(node, "ref")).getClass().getName()));
            }
        }
        return list;
    }

    private Node getNode(String expression) throws XPathExpressionException {
        return (Node) (xPath.evaluate(expression, document, XPathConstants.NODE));
    }

    private List<Node> getChildNode(NodeList nodeList) {
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            list.add(nodeList.item(i));
        }
        return list;
    }

    private String getText(Node node, String tag) {
        Node attrNode = node.getAttributes().getNamedItem(tag);
        return attrNode == null ? null : attrNode.getTextContent();
    }

    public Map<String, Convert> getMap() {
        return map;
    }

    public Configuration setMap(Map<String, Convert> map) {
        this.map = map;
        return this;
    }

    public TypeHandler getTypeHandler(String className) {
        if (!typeHandlerMap.containsKey(className)) {
            try {
                TypeHandler typeHandler = (TypeHandler) Class.forName(className).newInstance();
                typeHandlerMap.put(className, typeHandler);
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                log.error(e.getMessage(), e.getCause());
            }
        }
        return typeHandlerMap.get(className);
    }

    public Json getJsonExecutor() {
        return jsonExecutor;
    }
}
