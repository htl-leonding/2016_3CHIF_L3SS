package less;

/*
 * Matthias Meier/Jonas Müller/Ciora Cristian/Weigl Mario
 */
import java.io.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;
import com.phidgets.*;
import com.phidgets.event.*;
import java.util.LinkedList;
import java.util.List;
import static less.QuestionMode.file;

public class FreeMode {
    
    /*
     * To do:
     * We have to make the path with systemproperties and we have to make a put the path in the function speech
     *
    */
     static File file = null;
     static List<Gegenstand> toys = new LinkedList<Gegenstand>();
     private static String actToy = " ";
     
    public static void main(String... args) throws Exception {
        CsvReader.read(toys);
        RFIDPhidget rfid;
        rfid = new RFIDPhidget();
        
        CsvReader.read(toys);
        //Chip scanner (RFID) conectet
        rfid.addAttachListener(new AttachListener() {
            public void attached(AttachEvent ae) {
                try {
                    ((RFIDPhidget) ae.getSource()).setAntennaOn(true);
                    ((RFIDPhidget) ae.getSource()).setLEDOn(true);
                } catch (PhidgetException ex) {
                }
                System.out.println("attachment of " + ae);
            }
        });
        //Chip scanner (RFID) disconennctet
        rfid.addDetachListener(new DetachListener() {
            public void detached(DetachEvent ae) {
                System.out.println("detachment of " + ae);
            }
        });
        //Chip scanner (RFID) has an error
        rfid.addErrorListener(new ErrorListener() {
            public void error(ErrorEvent ee) {
                System.out.println("error event for " + ee);
            }
        });
        //Now it begins reading chips
        rfid.addTagGainListener(new TagGainListener() {
            public void tagGained(TagGainEvent oe) {
                try {
                    for (int i = 0; i < toys.size(); i++) {
                        if (oe.getValue().equals(toys.get(i).getId())) {
                                actToy = toys.get(i).getId();
                                System.out.println(toys.get(i).Name);
                                file = new File(toys.get(i).getPfadName());
                                speech();
                                break;
                        }
                    }
                } catch (IOException o) {
                }

            }
        });
        rfid.openAny();
        rfid.waitForAttachment(1000);
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
