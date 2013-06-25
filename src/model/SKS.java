/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Kasper
 */
public class SKS {
   private ArrayList<SKS_Headline> headlines;
   private String installation;
    private Date installationDate;
   private  Employee employee;

    public SKS(ArrayList<SKS_Headline> headlines, String installation, Date installationDate, Employee employee) {
        this.headlines = headlines;
        this.installation = installation;
        this.installationDate = installationDate;
        this.employee = employee;
    }

    public SKS(ArrayList<SKS_Headline> headlines, String installation, Employee employee) {
        this.headlines = headlines;
        this.installation = installation;
        this.employee = employee;
    }


    
    
}
