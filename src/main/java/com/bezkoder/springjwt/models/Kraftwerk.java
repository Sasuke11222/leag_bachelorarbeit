package com.bezkoder.springjwt.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "kraftwerke") //Tabellenname
public class Kraftwerk {

    //alle Bezeichnungen der Tabellenspalten
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Wert wird automatisch vergeben
    @Column(name = "kw_id")
    private Long kw_id;

    @Column(name = "kraftwerk_name")
    private String kraftwerk_name;

    @Column(name = "kraftwerksleiter")
    private String kraftwerksleiter;

    @Column(name = "zoneninstanzbesitzer")
    private String zoneninstanzbesitzer;

    @Column(name = "systemkoordinator")
    private String systemkoordinator;

    /*
    @OneToMany(mappedBy = "kw_id") //ein Kraftwerk zu vielen IT-Elementen, Mitarbeitern & Systemen
    private List<Kraftwerk> Kraftwerke = new ArrayList<>();
     */

    //Konstruktoren
    protected  Kraftwerk() {

    }

    public Kraftwerk(Long kw_id, String kraftwerk_name, String kraftwerksleiter, String zoneninstanzbesitzer, String systemkoordinator) {
        super();

        this.kw_id = kw_id;
        this.kraftwerk_name = kraftwerk_name;
        this.kraftwerksleiter = kraftwerksleiter;
        this.zoneninstanzbesitzer = zoneninstanzbesitzer;
        this.systemkoordinator = systemkoordinator;

    }

    public Kraftwerk(String kraftwerk_name) {
        this.kraftwerk_name = kraftwerk_name;
    }

    //alle Getter und Setter

    public Long getKw_id() {return  this.kw_id;}
    public void setKw_id(Long kw_id) {this.kw_id = kw_id;}

    public String getKraftwerk_name () {return this.kraftwerk_name;}
    public void setKraftwerk_name (String kraftwerk_name) {this.kraftwerk_name = kraftwerk_name;}

    public String getKraftwerksleiter () {return this.kraftwerksleiter;}
    public void setKraftwerksleiter (String kraftwerksleiter) {this.kraftwerksleiter = kraftwerksleiter;}

    public String getZoneninstanzbesitzer () {return this.zoneninstanzbesitzer;}
    public void setZoneninstanzbesitzer (String zoneninstanzbesitzer) {this.zoneninstanzbesitzer = zoneninstanzbesitzer;}

    public String getSystemkoordinator () {return this.systemkoordinator;}
    public void setSystemkoordinator (String systemkoordinator) {this.systemkoordinator = systemkoordinator;}
    @Override

    public String toString() {
        return "Kraftwerk [kw_id=" + kw_id + ", kraftwerk_name=" + kraftwerk_name + ", kraftwerksleiter=" + kraftwerksleiter + ", zoneninstanzbesitzer=" + zoneninstanzbesitzer + ", systemkoordinator=" + systemkoordinator +"]";
    }
}

