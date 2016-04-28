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

/**
 *
 * @author mario
 */
public class Button {

    public static void main(String[] args) {
        final GpioController gpio = GpioFactory.getInstance();

        final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN);
        final GpioPinDigitalOutput led = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_13, "MyLED", PinState.HIGH);
        
        myButton.addListener((GpioPinListenerDigital)(GpioPinDigitalStateChangeEvent event) -> {
            if(led.isHigh()){
                led.setState(PinState.LOW);
            }else{
                led.setState(PinState.HIGH);
            }
        });
    }
}
