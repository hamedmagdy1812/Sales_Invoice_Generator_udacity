

import org.json.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException, TransformerException, ParserConfigurationException, SAXException, JSONException {

//go to class replacement put txt file=
        //replace # with ,
        Replacement f = new Replacement();
        try {
            f.replace("#", ",");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //replace $ with \n
        Replacement x = new Replacement();
        try {
            x.replace2("$", "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //delete copy folder on exit
        File file = new File("/Users/Hamed/Documents/GitHub/new_sales_invoice/Sales_Invoice_Generator_udacity/lms/student file/student_data_copy.txt");
        file.deleteOnExit();

        //converting student_data_copy_final.txt to student_data_csv.csv
        FileWriter writer = null;
        file = new File("/Users/Hamed/Documents/GitHub/new_sales_invoice/Sales_Invoice_Generator_udacity/lms/student file/student_data_copy_final.txt");
        Scanner scan = new Scanner(file);

        File finalfile = new File("/Users/Hamed/Documents/GitHub/new_sales_invoice/Sales_Invoice_Generator_udacity/lms/student file/student_data_copy_final.txt");
        finalfile.deleteOnExit();


        //creating csv file
        File file2 = new File("/Users/Hamed/Documents/GitHub/new_sales_invoice/Sales_Invoice_Generator_udacity/lms/student file/formatted student data.csv");
        //student_data_csv.csv
        file.createNewFile();
        writer = new FileWriter(file2);

        //sid stands for student id
        file.createNewFile();
        writer = new FileWriter(file2);
        int sid = 0;

       /* System.out.println("-------------------------------\n" +
                "Current Student List\n" +
                "-------------------------------");*/
        while (scan.hasNext()) {

            String csv = scan.nextLine().replace("|", ",");
            if (sid == 0) {
                csv = "id" + "," + csv;
                sid++;
            } else
                csv = "" + sid++ + "," + csv;
            // System.out.println(csv);
            writer.append(csv);
            writer.append("\n");
            writer.flush();
        }


        ReadXMLFile xmlfile = new ReadXMLFile();
        xmlfile.readxml();


        //Files.readAllBytes(Path.of("/Users/Hamed/Documents/GitHub/new_sales_invoice/Sales_Invoice_Generator_udacity/coursedata/formatted course data.csv"))

        /*============ JSON PART =========*/

        int student_id = 4;

        //TODO: get student data (name,age) from csv file
        String jsonString;
        JSONObject jsonObject;
        List<String> courses_user_enrolled_in = new ArrayList<String>();

        jsonString = new String(Files.readAllBytes(Paths.get("/Users/Hamed/Documents/GitHub/new_sales_invoice/Sales_Invoice_Generator_udacity/lms/src/Student course details.json")));
        jsonObject = new JSONObject(jsonString);
        Iterator coursesJsonKeys = jsonObject.keys();
        while (coursesJsonKeys.hasNext()) {
            String currentKey = (String) coursesJsonKeys.next();
            JSONArray currentValue = jsonObject.getJSONArray(currentKey);
            for (int i = 0; i < currentValue.length(); i++) {

                if (currentValue.getInt(i) == student_id) {
                    courses_user_enrolled_in.add(currentKey);
                }

            }
        }

        if (courses_user_enrolled_in.isEmpty()) {
            System.out.println("Student is not enrolled in any courses");
        }

        readXMLFileForCourses((ArrayList) courses_user_enrolled_in);

        /*============ END JSON PART =========*/


    }


    public static void readXMLFileForCourses(ArrayList courses_user_enrolled_in) throws JSONException, IOException {


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

        NodeList nodeList = doc.getElementsByTagName("row");

        for (int itr = 0; itr < nodeList.getLength(); itr++) {
            Node node = nodeList.item(itr);
            // System.out.println("\nNode Name :" + node.getNodeName());

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                String id = eElement.getElementsByTagName("id").item(0).getTextContent();

                if (courses_user_enrolled_in.contains(id)) {
                    String courseid = eElement.getElementsByTagName("id").item(0).getTextContent();
                    String courseName = eElement.getElementsByTagName("CourseName").item(0).getTextContent();
                    String Instructor = eElement.getElementsByTagName("Instructor").item(0).getTextContent();
                    String Courseduration = eElement.getElementsByTagName("Courseduration").item(0).getTextContent();
                    String CourseTime = eElement.getElementsByTagName("CourseTime").item(0).getTextContent();
                    String Location = eElement.getElementsByTagName("Location").item(0).getTextContent();
                    System.out.println(courseid + ", " + ", " + courseName + ", " + Instructor + ", " + Courseduration + ", " + CourseTime + ", " + Location);
                }
            }
        }
    }
}




