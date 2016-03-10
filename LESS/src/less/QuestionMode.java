/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package less;

import com.phidgets.Phidget;
import com.phidgets.PhidgetException;
import com.phidgets.RFIDPhidget;
import com.phidgets.event.AttachEvent;
import com.phidgets.event.DetachEvent;
import com.phidgets.event.ErrorEvent;
import com.phidgets.event.TagGainEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


/**
 *
 * @author mario
 */
public class QuestionMode {

    static List<Gegenstand> toys = new LinkedList<Gegenstand>();            
    static int i;
    static Gegenstand presentToyRFID;
    static File file = null;

    /**
     * @param args the command line arguments
     * @throws com.phidgets.PhidgetException
     * @throws java.io.IOException
     */
    public static void main(String... args) throws PhidgetException, IOException {
        
        /*
         * TO DO:
         * we have to save the numbers, that never comes a object to times
         * make the systemproprties
        */
        
        
        RFIDPhidget rfid;
        CsvReader.read(toys);
        //System.out.println(Phidget.getLibraryVersion());
        rfid = new RFIDPhidget();

        CsvReader.read(toys);
        
        //To get a random toy
        Random rand = new Random();
        i = rand.nextInt(5);

        //Cache the random toy
        presentToyRFID = toys.get(i);
        System.out.println("Bitte lege einen " + transformRFIDtoToyQuestion() + " auf!");
        speech();

        rfid.addAttachListener((AttachEvent ae) -> {
            try {
                ((RFIDPhidget) ae.getSource()).setAntennaOn(true);
                ((RFIDPhidget) ae.getSource()).setLEDOn(true);
            } catch (PhidgetException ex) {
            }
            System.out.println("attachment of " + ae);
        });

        rfid.addDetachListener((DetachEvent ae) -> {
            System.out.println("detachment of " + ae);
        });

        rfid.addErrorListener((ErrorEvent ee) -> {
            System.out.println("error event for " + ee);
        });

        rfid.addTagGainListener((TagGainEvent oe) -> {
            if (oe.getValue().equals(presentToyRFID.getId())) {
                System.out.println("Gut gemacht!");
                file = new File(".\\mp3\\gutgemacht.mp3");
                try {
                    speech();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(QuestionMode.class.getName()).log(Level.SEVERE, null, ex);
                }
                i = rand.nextInt(5);

                //Cache the random toy
                presentToyRFID = toys.get(i);
                System.out.println("Bitte lege einen " + transformRFIDtoToyQuestion() + " auf!");
                try {
                    speech();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(QuestionMode.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("Das ist falsch! Bitte lege einen " + trasnformRFIDtoToyWrongAnswer() + " auf!");
                try {
                    speech();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(QuestionMode.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        rfid.openAny();
        rfid.waitForAttachment(100000);
        System.in.read();
        System.out.print("closing...");
        rfid.close();
        rfid = null;
        System.out.println(" ok");
        if (false) {
            System.out.println("wait for finalization...");
            System.gc();
        }

    }

    static public String transformRFIDtoToyQuestion() {
        
            file = new File(presentToyRFID.pfadFrage);
            return presentToyRFID.Name;
           
    
    }

    static public String trasnformRFIDtoToyWrongAnswer() {
   
     file = new File(presentToyRFID.pfadFalsch);
     return presentToyRFID.Name;
            
    }

    static public void speech() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        try {
            Player player = new Player(bis);
            player.play();
        } catch (JavaLayerException ex) {
        }
    }
}
