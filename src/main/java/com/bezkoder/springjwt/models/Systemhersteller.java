package com.bezkoder.springjwt.models;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "systemhersteller")
public class Systemhersteller {

    //alle Bezeichnungen der Tabellenspalten
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //wert wird automatisch vergeben
    private Long systemhersteller_id;

    private String herstellername;

    //@OneToMany(targetEntity = IT_Element.class, mappedBy = "systemhersteller_id", cascade = CascadeType.ALL) //Ein Systemhersteller zu vielen IT-Elementen
    //private List<Systemhersteller> Systemhersteller = new ArrayList<>();


    public Systemhersteller() {

    }

    public Systemhersteller(Long systemhersteller_id, String herstellername) {
        this.systemhersteller_id = systemhersteller_id;
        this.herstellername = herstellername;
    }

    //alle geter und seter

    public String getHerstellername () {return this.herstellername;}
    public void setHerstellername (String herstellername) {this.herstellername = herstellername;}

    public Long getSystemhersteller_id() {return  this.systemhersteller_id;}
    public void setSystemhersteller_id(Long systemhersteller_id) {this.systemhersteller_id = systemhersteller_id;}



    @Override
    public String toString() {
        return "Systemhersteller [systemhersteller_id=" + systemhersteller_id + ", herstellername=" + herstellername +"]";
    }
}

