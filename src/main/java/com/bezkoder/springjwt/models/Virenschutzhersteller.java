package com.bezkoder.springjwt.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "virenschutzhersteller") //Tabellenname
public class Virenschutzhersteller {

    //alle Bezeichnungen der Tabellenspalten
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Wert wird automatisch vergeben
    @Column(name = "virenschutz_hersteller_id")
    private Long virenschutz_hersteller_id;

    @Column(name = "herstellername")
    private String herstellername;

    @Column(name = "version")
    private String version;

    @OneToMany(targetEntity = IT_Element.class, mappedBy = "virenschutz_hersteller_id", cascade = CascadeType.ALL) //Ein Virenschutzhersteller zu vielen IT-Elementen
    private List<IT_Element> it_elements;

    //Konstruktoren
    public Virenschutzhersteller() {

    }

    public Virenschutzhersteller(String herstellername, String version) {
        this.herstellername = herstellername;
        this.version = version;

    }

    //alle Getter und Setter

    public String getHerstellername () {return this.herstellername;}
    public void setHerstellername (String herstellername) {this.herstellername = herstellername;}

    public String getVersion () {return this.version;}
    public void setVersion (String version) {this.version = version;}

    public Long getVirenschutzhersteller_id() {return  this.virenschutz_hersteller_id;}
    public void setVirenschutzhersteller_id(Long virenschutzhersteller_id) {this.virenschutz_hersteller_id = virenschutzhersteller_id;}

    @Override
    public String toString() {
        return "Virenschutzhersteller [virenschutzhersteller_id=" + herstellername + ", kontoart=" + herstellername + ", version=" + version +"]";
    }
}

