package com.github.way.english.console;

import com.github.way.english.model.*;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

/**
 * Created by way on 08.08.2014.
 */
public class XmlWriter {

    public void writeLessons(List<Lesson> lessons, String path) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("lessons");
        doc.appendChild(rootElement);

        for (Lesson lesson : lessons) {
            Element lessonElement = doc.createElement("lesson");
            rootElement.appendChild(lessonElement);

            Attr attr = doc.createAttribute("number");
            attr.setValue(String.valueOf(lesson.getNumber()));
            lessonElement.setAttributeNode(attr);

            for (LessonItem item : lesson.getItems()) {
                if (item instanceof Word) {
                    Element element = createTextItemElement(doc, item, "word");
                    lessonElement.appendChild(element);
                } else if (item instanceof Grammar) {
                    Element element = createTextItemElement(doc, item, "grammar");
                    lessonElement.appendChild(element);
                } else if (item instanceof Question) {
                    Element element = createTextItemElement(doc, item, "question");

                    for (Answer answer : ((Question) item).getAnswers()) {
                        Element answerElement = createTextItemElement(doc, answer, "answer");
                        element.appendChild(answerElement);
                    }

                    lessonElement.appendChild(element);
                } else if (item instanceof Dictation) {
                    Element element = createTextItemElement(doc, item, "dictation");
                    lessonElement.appendChild(element);
                }
            }
        }



        // write the content into xml file
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
//            StreamResult result = new StreamResult(System.out);
            StreamResult result = new StreamResult(new File(path));
            transformer.transform(source, result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Element createTextItemElement(Document doc, HasText item, String tagName) {
        Element itemElement = doc.createElement(tagName);

        Element textElement = doc.createElement("text");
        textElement.appendChild(doc.createTextNode(item.getText()));
        itemElement.appendChild(textElement);

        return itemElement;
    }
}
