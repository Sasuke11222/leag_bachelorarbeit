package com.bezkoder.springjwt.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "kritikalitaet") //Tabellenname
public class Kritikalitaet {

    //alle Bezeichnungen der Tabellenspalten
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Wert wird automatisch vergeben
    @Column(name = "kritikalitaet_id")
    private Long kritikalitaet_id;

    @Column(name = "kritikalitaet_name")
    private String kritikalitaet_name;

    @OneToMany(targetEntity = Systeme.class, mappedBy = "kritikalitaet_id", cascade = CascadeType.ALL)//Eine Kritikalität zu vielen Systemen
    private List<Kritikalitaet> Kritikalitaet = new ArrayList<>();

    //Konstruktoren
    public Kritikalitaet() {

    }

    public Kritikalitaet(Long kritikalitaet_id, String kritikalitaet_name) {
        this.kritikalitaet_id = kritikalitaet_id;
        this.kritikalitaet_name = kritikalitaet_name;

    }

    //alle Getter und Setter

    public Long getKritikalitaet_id() {return  this.kritikalitaet_id;}
    public void setKritikalitaet_id(Long kritikalitaet_id) {this.kritikalitaet_id = kritikalitaet_id;}

    public String getKritikalitaet_name () {return this.kritikalitaet_name;}
    public void setKritikalitaet_name (String kritikalitaet_name) {this.kritikalitaet_name = kritikalitaet_name;}

    @Override
    public String toString() {
        return "Kritikalitaet [kritikalitaet_id=" + kritikalitaet_id + ", kritikalitaet_name=" + kritikalitaet_name +"]";
    }
}
