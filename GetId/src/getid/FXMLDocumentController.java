/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getid;

import com.phidgets.PhidgetException;
import com.phidgets.RFIDPhidget;
import com.phidgets.event.AttachEvent;
import com.phidgets.event.AttachListener;
import com.phidgets.event.DetachEvent;
import com.phidgets.event.DetachListener;
import com.phidgets.event.ErrorEvent;
import com.phidgets.event.ErrorListener;
import com.phidgets.event.TagGainEvent;
import com.phidgets.event.TagGainListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author mmeie
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;

    private static RFIDPhidget rfid;
    @FXML
    private Label lb_Id;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
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
            }
        }
        );
        rfid.openAny();
        rfid.waitForAttachment(1000);
        } catch (PhidgetException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
