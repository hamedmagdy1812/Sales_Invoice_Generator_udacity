import netscape.javascript.JSObject;
import org.xml.sax.SAXException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.File;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;

import static java.nio.file.Files.newBufferedReader;
import static java.nio.file.Files.newBufferedWriter;


public class Main {
    public static void main(String[] args) throws IOException, TransformerException, ParserConfigurationException, SAXException {

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
        File file = new File("/Users/Hamed/Documents/GitHub/Sales_Invoice_Generator_udacity/lms/student file/student_data_copy.txt");
        file.deleteOnExit();

        //converting student_data_copy_final.txt to student_data_csv.csv
        FileWriter writer = null;
        file = new File("/Users/Hamed/Documents/GitHub/Sales_Invoice_Generator_udacity/lms/student file/student_data_copy_final.txt");
        Scanner scan = new Scanner(file);

        File finalfile = new File("/Users/Hamed/Documents/GitHub/Sales_Invoice_Generator_udacity/lms/student file/student_data_copy_final.txt");
        finalfile.deleteOnExit();


        //creating csv file
        File file2 = new File("/Users/Hamed/Documents/GitHub/Sales_Invoice_Generator_udacity/lms/student file/formatted student data.csv");
        //student_data_csv.csv
        file.createNewFile();
        writer = new FileWriter(file2);

        //sid stands for student id
        file.createNewFile();
        writer = new FileWriter(file2);
        int sid = 0;

        System.out.println("-------------------------------\n" +
                "Current Student List\n" +
                "-------------------------------");
        while (scan.hasNext()) {

            String csv = scan.nextLine().replace("|", ",");
            if (sid == 0) {
                csv = "id" + "," + csv;
                sid++;
            } else
                csv = "" + sid++ + "," + csv;
            System.out.println(csv);
            writer.append(csv);
            writer.append("\n");
            writer.flush();
        }


        ReadXMLFile xmlfile = new ReadXMLFile();
        xmlfile.readxml();


    }
}



