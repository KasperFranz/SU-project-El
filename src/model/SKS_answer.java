/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Kasper
 */
public class SKS_answer {
    private SKS_question question;
            private String answer;

    public SKS_answer(SKS_question question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public SKS_question getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
    
    
            
}
