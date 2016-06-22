package less;

/*
 * @author Matthias Meier, Jonas Müller, Mario Weigl, Cristian Ciora
 */
import java.io.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;
import com.phidgets.*;
import com.phidgets.event.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static less.QuestionMode.file;

public class FreeMode {

    static File file = null;
    static List<Gegenstand> toys = new LinkedList<Gegenstand>();
    private static String actToy = " ";
    private static RFIDPhidget rfid;

    public static void runMode() throws Exception {
        new Thread(() -> {
            try {
                CsvReader.read(toys);                                                   //read the toys from csv
                rfid = new RFIDPhidget();
                //Chip scanner (RFID) conectet
                rfid.addAttachListener(new AttachListener() {                           //check if the RFID Reader is Connected
                    public void attached(AttachEvent ae) {
                        try {
                            ((RFIDPhidget) ae.getSource()).setAntennaOn(true);
                            ((RFIDPhidget) ae.getSource()).setLEDOn(true);
                        } catch (PhidgetException ex) {
                        }
                        System.out.println("attachment of " + ae);
                    }
                });

                rfid.addDetachListener(new DetachListener() {                           //check if the RFID Reader is Disconnected
                    public void detached(DetachEvent ae) {
                        System.out.println("detachment of " + ae);
                    }
                });

                rfid.addErrorListener(new ErrorListener() {                             //check if the RFID Reader is broken
                    public void error(ErrorEvent ee) {
                        System.out.println("error event for " + ee);
                    }
                });
                rfid.addTagGainListener(new TagGainListener() {                         //Read the Chips
                    public void tagGained(TagGainEvent oe) {
                        try {
                            for (int i = 0; i < toys.size(); i++) {                     //search the toy in the list of the toys
                                if (oe.getValue().equals(toys.get(i).getId())) {
                                    actToy = toys.get(i).getId();
                                    System.out.println(toys.get(i).Name);
                                    file = new File(toys.get(i).getPfadName());
                                    speech();                                       //say the name of the toy
                                    break;
                                }
                            }
                        } catch (IOException o) {
                        }

                    }
                });
                rfid.openAny();
                rfid.waitForAttachment(1000);
            } catch (PhidgetException | IOException e) {
                Logger.getLogger(FreeMode.class.getName()).log(Level.SEVERE, null, e);
            }
        }).start();
        //System.in.read();
    }

    static public void speech() throws FileNotFoundException {                  //say the audio
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        try {
            Player player = new Player(bis);
            player.play();
        } catch (JavaLayerException ex) {
        }
    }

    static public void closeMode() throws PhidgetException {
        if (rfid == null) {
            return;
        }
        System.out.print("closing...");
        rfid.close();
        rfid = null;
        System.out.println(" ok");
        if (false) {
            System.out.println("wait for finalization...");
            System.gc();
        }
    }
}
