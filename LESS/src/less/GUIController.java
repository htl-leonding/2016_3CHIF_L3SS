/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package less;

import com.phidgets.PhidgetException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author cristian
 */
public class GUIController implements Initializable {

    @FXML
    private Button questionBtn;
    @FXML
    private Button freeBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void runQuestionMode(ActionEvent event) {

        try {
            FreeMode.closeMode();
            QuestionMode.runMode();
        } catch (PhidgetException | IOException ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void runFreeMode(ActionEvent event) {

        try {
            QuestionMode.closeMode();
            FreeMode.runMode();
        } catch (Exception ex) {
            Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
