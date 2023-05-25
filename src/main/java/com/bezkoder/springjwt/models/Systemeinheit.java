package com.bezkoder.springjwt.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "systemeinheit") //Tabellenname
public class Systemeinheit {

    //alle Bezeichnungen der Tabellenspalten
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Wert wird automatisch vergeben
    @Column(name = "systemeinheit_id")
    private Long systemeinheit_id;

    @Column(name = "systemeinheit_name")
    private String systemeinheit_name;

    @OneToMany(targetEntity = IT_Element.class, mappedBy = "systemeinheit_id", cascade = CascadeType.ALL) //Eine Systemeinheit zu vielen IT-Elementen
    private List<Systemeinheit> Systemeinheit = new ArrayList<>();

    //Konstruktoren
    public Systemeinheit() {

    }

    public Systemeinheit(String systemeinheit_name) {
        this.systemeinheit_name = systemeinheit_name;

    }

    //alle Getter und Setter

    public String getSystemeinheit_name () {return this.systemeinheit_name;}
    public void setSystemeinheit_name (String systemeinheit_name) {this.systemeinheit_name = systemeinheit_name;}

    public Long getSystemeinheit_id() {return  this.systemeinheit_id;}
    public void setSystemeinheit_id(Long systemeinheit_id) {this.systemeinheit_id = systemeinheit_id;}

    @Override
    public String toString() {
        return "Systemeinheit [systemeinheit_id=" + systemeinheit_id + ", systemeinheit_name=" + systemeinheit_name +"]";
    }
}

