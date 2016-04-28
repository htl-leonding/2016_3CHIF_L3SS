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

/**
 *
 * @author mario
 */
public class Button {

    public static void main(String[] args) {
        try {
            final GpioController gpio = GpioFactory.getInstance();
            
            final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN);
            final GpioPinDigitalOutput led = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_13, "MyLED", PinState.HIGH);
            FreeMode.runMode();
            
            myButton.addListener((GpioPinListenerDigital)(GpioPinDigitalStateChangeEvent event) -> {
                if(led.isHigh()){
                    led.setState(PinState.LOW);
                    try {
                        QuestionMode.closeMode();
                        FreeMode.runMode();
                    } catch (Exception ex) {
                        Logger.getLogger(Button.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    led.setState(PinState.HIGH);
                    try {
                        FreeMode.closeMode();
                        QuestionMode.runMode();
                    } catch (Exception ex) {
                        Logger.getLogger(Button.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (Exception ex) {
            Logger.getLogger(Button.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
