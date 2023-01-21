import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Replacement {
    // a class to replace strings
    void replace(String stringToReplace, String replaceWith) throws IOException {

        //put text file here
        BufferedReader in = new BufferedReader(new FileReader("/Users/Hamed/Documents/GitHub/Sales_Invoice_Generator_udacity/lms/student file/studetdata.txt"));
        BufferedWriter out = new BufferedWriter(new FileWriter("/Users/Hamed/Documents/GitHub/Sales_Invoice_Generator_udacity/lms/student file/student_data_copy.txt"));

        String line;

        while ((line = in.readLine()) != null) {
            if (line.contains(stringToReplace))
                line = line.replace(stringToReplace, replaceWith);
            out.write(line);
            out.newLine();
        }
        in.close();
        out.close();
    }

    void replace2(String stringToReplace, String replaceWith) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("/Users/Hamed/Documents/GitHub/Sales_Invoice_Generator_udacity/lms/student file/student_data_copy.txt"));
        BufferedWriter out = new BufferedWriter(new FileWriter("/Users/Hamed/Documents/GitHub/Sales_Invoice_Generator_udacity/lms/student file/student_data_copy_final.txt"));

        String line;

        while ((line = in.readLine()) != null) {
            if (line.contains(stringToReplace))
                line = line.replace(stringToReplace, replaceWith);
            out.write(line);
            out.newLine();
        }
        in.close();
        out.close();
    }


}
