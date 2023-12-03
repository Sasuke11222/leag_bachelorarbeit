package com.bezkoder.springjwt.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "betriebssystem") //Tabellenname
public class Betriebssystem {
    //alle Bezeichnungen der Tabellenspalten
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Wert wird automatisch vergeben
    @Column(name = "betriebssystem_id")
    private Long betriebssystem_id;

    @Column(name = "betriebssystem_name")
    private String betriebssystem_name;

    @OneToMany(targetEntity = IT_Element.class, mappedBy = "betriebssystem_id", cascade = CascadeType.ALL) //Ein Betriebssystem zu vielen IT-Elementen
    private List<IT_Element> it_elements;

    //Konstruktoren
    public Betriebssystem() {

    }

    public Betriebssystem(String betriebssystem_name) {
        this.betriebssystem_name = betriebssystem_name;

    }

    //alle Getter und Setter

    public Long getBetriebssystem_id() {return  this.betriebssystem_id;}
    public void setBetriebssystem_id(Long betriebssystem_id) {this.betriebssystem_id = betriebssystem_id;}

    public String getBetriebssystem_name () {return this.betriebssystem_name;}
    public void setBetriebssystem_name (String betriebssystem_name) {this.betriebssystem_name = betriebssystem_name;}

    @Override
    public String toString() {
        return "Betreibssystem [betriebssystem_id=" + betriebssystem_id + ", betriebssystem_name=" + betriebssystem_name +"]";
    }
}
