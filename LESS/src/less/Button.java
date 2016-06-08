/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package less;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author mario
 */
public class Button extends Application  {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
//        try {
//            final GpioController gpio = GpioFactory.getInstance();
            
//            final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN);
//            final GpioPinDigitalOutput led = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_13, "MyLED", PinState.HIGH);
//            final GpioPinDigitalOutput led2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_14, "MyLED", PinState.LOW);
//            FreeMode.runMode();
            
//            myButton.addListener((GpioPinListenerDigital)(GpioPinDigitalStateChangeEvent event) -> {
//                if(led.isHigh()){
//                    led.setState(PinState.LOW);
//                    led2.setState(PinState.HIGH);
//                    try {
//                        QuestionMode.closeMode();
//                        FreeMode.runMode();
//                    } catch (Exception ex) {
//                        Logger.getLogger(Button.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }else{
//                    led2.setState(PinState.LOW);
//                    led.setState(PinState.HIGH);
//                    try {
//                        FreeMode.closeMode();
//                        QuestionMode.runMode();
//                    } catch (Exception ex) {
//                        Logger.getLogger(Button.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            });
//        } catch (Exception ex) {
//            Logger.getLogger(Button.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
