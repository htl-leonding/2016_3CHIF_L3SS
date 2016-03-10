package less;


import less.FreeMode;
import less.QuestionMode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mmeie
 */
public class Mode {
    public void setOnFreeMode() throws Exception{
        FreeMode.main();
    }
    public void setOnQuestionMode()throws Exception{
        QuestionMode.main();
    }
}
