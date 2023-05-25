package com.bezkoder.springjwt.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "kontoart") //Tabellenname
public class Kontoart {

    //alle Bezeichnungen der Tabellenspalten
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Wert wird automatisch vergeben
    @Column(name = "kontoart_id")
    private Long kontoart_id;

    @Column(name = "kontoart")
    private String kontoart;

    @OneToMany(targetEntity = Nutzermanagment.class, mappedBy = "kontoart_id", cascade = CascadeType.ALL) //eine Kontoart zu vielen Nutzern
    private List<Kontoart> Kontoart = new ArrayList<>();

    //Konstruktoren
    public Kontoart() {

    }

    public Kontoart(String kontoart) {
        this.kontoart = kontoart;

    }

    //alle Getter und Setter

    public Long getKontoart_id() {return  this.kontoart_id;}
    public void setKontoart_id(Long kontoart_id) {this.kontoart_id = kontoart_id;}

    public String getKontoart () {return this.kontoart;}
    public void setKontoart (String kontoart) {this.kontoart = kontoart;}

    @Override
    public String toString() {
        return "Kontoart [kontoart_id=" + kontoart_id + ", kontoart=" + kontoart +"]";
    }
}
