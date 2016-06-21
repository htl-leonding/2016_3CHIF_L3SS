/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.phidgets.PhidgetException;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import java.io.IOException;
import java.lang.Thread.State;
import java.util.logging.Level;
import java.util.logging.Logger;
import less.FreeMode;
import less.QuestionMode;

/**
 *
 * @author Matthias
 */
public class Button {
    private static boolean freeMode;
    private static boolean qMode;
    public static void main(String[] args) {
            
        //launch(args);
        try {
            final GpioController gpio = GpioFactory.getInstance();

            final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN);
            final GpioPinDigitalInput myButton2 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_04, PinPullResistance.PULL_DOWN);
            final GpioPinDigitalOutput led = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "MyLED", PinState.HIGH);
            FreeMode.runMode();
            freeMode = true;
            
            myButton.addListener((GpioPinListenerDigital) (GpioPinDigitalStateChangeEvent event) -> {
                led.setState(PinState.LOW);
                if(!freeMode){
                    try {
                        freeMode = true;
                        qMode = false;
                        QuestionMode.closeMode();
                        FreeMode.runMode();
                    } catch (Exception ex) {
                        Logger.getLogger(Button.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            myButton2.addListener((GpioPinListenerDigital) (GpioPinDigitalStateChangeEvent event) ->{
                led.setState(PinState.HIGH);
                if(!qMode){
                    try {
                        freeMode = false;
                        qMode = true;
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
        while (true) {
        }
    }
    boolean turnOn() {
        return true;
    }
}
