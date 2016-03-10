/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    private static final String FILENAME = "toys.csv";
    
    public static void read(List<Gegenstand> gegenstandsListe) throws IOException{
        List<String> lines = Files.readAllLines(Paths.get(FILENAME));
        for (String line : lines) {
            System.out.println(line);
            String[] elements = line.split(SEPARATOR);
            Gegenstand gegenstand = new Gegenstand(elements[0], elements[1], elements[2], elements[3], elements[4]);
            gegenstandsListe.add(gegenstand);
        }
    }
}
