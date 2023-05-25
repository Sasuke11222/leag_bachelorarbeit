package com.bezkoder.springjwt.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "kontotyp") //Tabellenname
public class Kontotyp {

    //alle Bezeichnungen der Tabellenspalten
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Wert wird automatisch vergeben
    @Column(name = "kontotyp_id")
    private Long kontotyp_id;

    @Column(name = "kontotyp")
    private String kontotyp;

    @OneToMany(targetEntity = Nutzermanagment.class, mappedBy = "kontotyp_id", cascade = CascadeType.ALL) //ein Kontotyp zu vielen Nutzern
    private List<Kontotyp> Kontotyp = new ArrayList<>();

    //Konstruktoren
    public Kontotyp() {

    }

    public Kontotyp(String kontotyp) {
        this.kontotyp = kontotyp;

    }

    //alle Getter und Setter

    public Long getKontotyp_id() {return  this.kontotyp_id;}
    public void setKontotyp_id(Long kontotyp_id) {this.kontotyp_id = kontotyp_id;}

    public String getKontotyp () {return this.kontotyp;}
    public void setKontotyp (String kontotyp) {this.kontotyp = kontotyp;}

    @Override
    public String toString() {
        return "Kontotyp [kontotyp_id=" + kontotyp_id + ", kontotyp=" + kontotyp +"]";
    }
}

