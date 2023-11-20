package com.bezkoder.springjwt.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "systemtyp") //Tabellenname
public class Systemtyp {

    //alle Bezeichnungen der Tabellenspalten
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Wert wird automatisch vergeben
    @Column(name = "systemtyp_id")
    private Long systemtyp_id;

    @Column(name = "systemtyp_name")
    private String systemtyp_name;

    @OneToMany(targetEntity = Systeme.class, mappedBy = "systemtyp_id", cascade = CascadeType.ALL) //Ein Systemtyp zu vielen Systemen
    private List<Systemtyp> Systemtyp = new ArrayList<>();

    //Konstruktoren
    public Systemtyp() {

    }

    public Systemtyp(String systemtyp_name) {
        this.systemtyp_name = systemtyp_name;
    }

    //alle Getter und Setter

    public String getSystemtyp_name () {return this.systemtyp_name;}
    public void setSystemtyp_name (String systemtyp_name) {this.systemtyp_name = systemtyp_name;}

    public Long getSystemtyp_id() {return  this.systemtyp_id;}
    public void setSystemtyp_id(Long systemtyp_id) {this.systemtyp_id = systemtyp_id;}

    @Override
    public String toString() {
        return "Systemtyp [systemtyp_id=" + systemtyp_id + ", systemtyp_name=" + systemtyp_name +"]";
    }
}
