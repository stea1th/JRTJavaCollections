package com.javarush.task.task33.task3309;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.regex.Pattern;

/*
Комментарий внутри xml
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws JAXBException, ParserConfigurationException, IOException, SAXException, TransformerException {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        //marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(obj, writer);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setCoalescing(true);
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        Document document = documentBuilder.parse(new ByteArrayInputStream(writer.toString().getBytes()));
        document.setXmlStandalone(false);
        Element root = document.getDocumentElement();

        stepThrough(root, tagName, comment, document);

        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new ByteArrayOutputStream());
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);

        return result.getOutputStream().toString();
    }

    public static void stepThrough(Node start, String tagName, String comment, Document document){
        if(start==null)
            return;
        Node cmnt = document.createComment(comment);
        if(start.getNodeName().equals(tagName)&& start.isSameNode(document.getDocumentElement())) {
            start.getParentNode().insertBefore(cmnt, start);
        }
        for(Node child = start.getFirstChild(); child!=null; child = child.getNextSibling()){
            if(child.getNodeName().equals(tagName)){
                cmnt = document.createComment(comment);
                child.getParentNode().insertBefore(cmnt, child);
            }
            if(child.getNodeValue()!=null && Pattern.compile("[<>&\"']").matcher(child.getTextContent()).find()){
                child.getParentNode().replaceChild(document.createCDATASection(child.getNodeValue()), child);
            }
            stepThrough(child, tagName, comment, document);
        }

    }

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, JAXBException, TransformerException {

        System.out.println(Solution.toXmlWithComment(new First(), "second", "it's a comment"));

    }

    @XmlRootElement(name = "first")
    public static class First {
        @XmlElement(name = "second")
        public String item1 = "some string";
        @XmlElement(name = "second")
        public String item2 = "need CDATA because of <second>";
        @XmlElement(name = "second")
        public String item3 = "";
        @XmlElement(name = "third")
        public String item4;
        @XmlElement(name = "forth")
        public Second[] third = new Second[]{new Second()};
        @XmlElement(name = "fifth")
        public String item5 = "need CDATA because of \"";
    }

    public static class Second {
        @XmlElement(name = "second")
        public String item1 = "some string";
        @XmlElement(name = "second")
        public String item2 = "need CDATA because of <second>";
    }
}

/*String makeComment = String.format("\n<!--%s-->\n<%s", comment,tagName);
        Pattern pattern = Pattern.compile("<"+tagName);
        Matcher matcher = pattern.matcher(result);
        if(matcher.matches()){
            matcher.replaceAll(makeComment);
        }
        return result;*/
