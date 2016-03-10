package less;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jonasmuller
 */
public class Gegenstand {
    
    String Name;
    String id;
    String pfadName;
    String pfadFrage;
    String pfadFalsch;

    public Gegenstand(String Name, String id, String pfadName, String pfadFrage, String pfadFalsch) {
        this.Name = Name;
        this.id = id;
        this.pfadName = pfadName;
        this.pfadFrage = pfadFrage;
        this.pfadFalsch = pfadFalsch;
    }
  
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPfadName() {
        return pfadName;
    }

    public void setPfadName(String pfadName) {
        this.pfadName = pfadName;
    }

    public String getPfadFrage() {
        return pfadFrage;
    }

    public void setPfadFrage(String pfadFrage) {
        this.pfadFrage = pfadFrage;
    }

    public String getPfadFalsch() {
        return pfadFalsch;
    }

    public void setPfadFalsch(String pfadFalsch) {
        this.pfadFalsch = pfadFalsch;
    }
}
   
    
   
    
  