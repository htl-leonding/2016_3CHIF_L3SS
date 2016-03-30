/*
 * To read the data out of the csv
 */
package less;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author mmeie
 */

class CsvReader {
 
    private static final String SEPARATOR = ",";
    private static final String FILENAME = "toys.csv";      //set costants
    public static final String path = System.getProperty("user.dir") +  //set path
        System.getProperty("file.separator") + "mp3" + 
        System.getProperty("file.separator");
    
    public static void read(List<Gegenstand> gegenstandsListe) throws IOException{      //Read from csv
        List<String> lines = Files.readAllLines(Paths.get(FILENAME));
        for (String line : lines) {
            System.out.println(line);
            String[] elements = line.split(SEPARATOR);
            String linkNormal = path+elements[2];
            String linkQuestion = path+elements[3];
            String linkFalse = path+elements[4];
            Gegenstand gegenstand = new Gegenstand(elements[0], elements[1], linkNormal, linkQuestion, linkFalse);  //set Elements
            gegenstandsListe.add(gegenstand);
        }
    }
}
