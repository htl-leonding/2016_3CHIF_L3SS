/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RunFile;

import less.*;

/**
 *
 * @author mmeie
 */
public class Run {

    public static void main(String[] args) throws Exception {

        Mode mode = new Mode();
        int i = 1;
        while (true) {
            if (i == 2) {                   //check buttonvalue
                mode = new Mode();
                mode.setOnFreeMode();
            } else {
                mode = new Mode();
                mode.setOnQuestionMode();
            }
        }
    }
}
