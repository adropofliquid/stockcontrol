package com.dami.stockcontrol.service;

import com.dami.stockcontrol.model.Person;
import com.dami.stockcontrol.model.ProductAddInfo;
import com.dami.stockcontrol.model.Role;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProcessXML {

    public List<ProductAddInfo> readXMLProduct(String xmlUrl) {

        List<ProductAddInfo> products = new ArrayList<>();

        // Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(xmlUrl));

            // optional, but recommended
            // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            // get <staff>
            NodeList list = doc.getElementsByTagName("product");

            for (int i = 0; i < list.getLength(); i++) {

                Node node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    // get text
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    String category = element.getElementsByTagName("category").item(0).getTextContent();
                    String subCategory = element.getElementsByTagName("subCategory").item(0).getTextContent();
                    String description = element.getElementsByTagName("description").item(0).getTextContent();
                    String costPrice = element.getElementsByTagName("costPrice").item(0).getTextContent();
                    String sellingPrice = element.getElementsByTagName("sellingPrice").item(0).getTextContent();
                    String quantity = element.getElementsByTagName("quantity").item(0).getTextContent();
                    String company = element.getElementsByTagName("company").item(0).getTextContent();

                    ProductAddInfo p = new ProductAddInfo();

                    p.setName(name);
                    p.setCompany(company);
                    p.setCategoryName(category);
                    p.setSubCategoryName(subCategory);
                    p.setDescription(description);
                    p.setCostPrice(Double.parseDouble(costPrice));
                    p.setSellingPrice(Double.parseDouble(sellingPrice));
                    p.setQuantity(Integer.parseInt(quantity));

                    products.add(p);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }


        return products;
    }

    public List<Person> readXMLPeople(String xmlUrl) {

        List<Person> people = new ArrayList<>();

        // Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(xmlUrl));

            // optional, but recommended
            // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            // get <staff>
            NodeList list = doc.getElementsByTagName("user");

            for (int i = 0; i < list.getLength(); i++) {

                Node node = list.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    // get text
                    String username = element.getElementsByTagName("username").item(0).getTextContent();
                    String password = element.getElementsByTagName("password").item(0).getTextContent();
                    String firstname = element.getElementsByTagName("firstname").item(0).getTextContent();
                    String lastname = element.getElementsByTagName("lastname").item(0).getTextContent();



                    Person person = new Person(username,password,
                            lastname,firstname,true,
                            Role.Group.SALESPERSON,0,
                            String.valueOf(LocalDate.now()));

                    people.add(person);
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }


        return people;

    }
}
