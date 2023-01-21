
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class ReadXMLFile {
    public void readxml() throws IOException {

        File file = new File("/Users/Hamed/Documents/GitHub/Sales_Invoice_Generator_udacity/coursedata/coursedata.xml");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            throw new RuntimeException(ex);
        }
        Document doc = null;
        try {
            doc = db.parse(file);
        } catch (SAXException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        doc.getDocumentElement().normalize();
        System.out.println("" +
                "" +
                "" +
                "" +
                "");
        NodeList nodeList = doc.getElementsByTagName("row");
        File filexml = new File("/Users/Hamed/Documents/GitHub/Sales_Invoice_Generator_udacity/coursedata/formatted course data.csv");

        filexml.createNewFile();
        FileWriter writer = new FileWriter(filexml);
        String header = "id" + "," + "Course Name" + "," + "Instructor" + "," + "Course duration" + "," + "Course time" + "," + "Location" + "\n";
        String row;


        //System.out.println(header);
        writer.append(header);


        for (int itr = 0; itr < nodeList.getLength(); itr++) {
            Node node = nodeList.item(itr);
            // System.out.println("\nNode Name :" + node.getNodeName());

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;

                row = eElement.getElementsByTagName("id").item(0).getTextContent()
                        + "," + eElement.getElementsByTagName("CourseName").item(0).getTextContent()
                        + "," + eElement.getElementsByTagName("Instructor").item(0).getTextContent()
                        + "," + eElement.getElementsByTagName("Courseduration").item(0).getTextContent()
                        + "," + eElement.getElementsByTagName("CourseTime").item(0).getTextContent()
                        + "," + eElement.getElementsByTagName("Location").item(0).getTextContent() + "\n";
                //System.out.println(row);
                writer.append(row);


                /*
                System.out.println("Course Name: " + eElement.getElementsByTagName("CourseName").item(0).getTextContent());
                System.out.println("Instructor: " + eElement.getElementsByTagName("Instructor").item(0).getTextContent());
                System.out.println("Course duration: " + eElement.getElementsByTagName("Courseduration").item(0).getTextContent());
                System.out.println("Course time: " + eElement.getElementsByTagName("CourseTime").item(0).getTextContent());
                System.out.println("Location: " + eElement.getElementsByTagName("Location").item(0).getTextContent());
                */
            }
        }
        writer.flush();
    }


}
