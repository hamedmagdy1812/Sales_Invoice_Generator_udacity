import java.io.*;
import java.util.Scanner;

import static java.nio.file.Files.newBufferedReader;
import static java.nio.file.Files.newBufferedWriter;


public class Main {
    public static void main(String[] args) throws IOException {


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
        File file = new File("/Users/Hamed/Documents/lms project/lms/student file/student_data_copy.txt");
        file.deleteOnExit();

        //converting student_data_copy_final.txt to student_data_csv.csv
        FileWriter writer = null;
        file = new File("/Users/Hamed/Documents/lms project/lms/student file/student_data_copy_final.txt");
        Scanner scan = new Scanner(file);

        //creating csv file
        File file2 = new File("/Users/Hamed/Documents/lms project/lms/student file/student_data_csv.csv");
        file.createNewFile();
        writer = new FileWriter(file2);

        while (scan.hasNext()) {
            String csv = scan.nextLine().replace("|", ",");
            System.out.println(csv);
            writer.append(csv);
            writer.append("\n");
            writer.flush();
        }



        /*File file = new File(
                "/Users/Hamed/Documents/udacity/student_data.txt");
        BufferedReader br
                = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        String st;

        while (true)
        {
            try {
                if (!((st = br.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("(.txt)"+st);
        }*/
    }

}