package com.bezkoder.springjwt.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mitarbeiter") //Tabellenname
public class Mitarbeiter {

    //alle Bezeichnungen der Tabellenspalten
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Wert wird automatisch vergeben
    @Column(name = "mitarbeiter_id")
    private Long mitarbeiter_id;

    @Column(name = "nachname")
    private String nachname;

    @Column(name = "vorname")
    private String vorname;

    @Column(name = "abteilung")
    private String abteilung;

    @Column(name = "telefon")
    private String telefon;

    @Column(name = "mail")
    private String mail;

    /*
    @ManyToOne() // viele Kraftwerke zu einem Mitarbeiter
    @JoinColumn(name = "kw_id", referencedColumnName = "kw_id") //Join Tabelle kraftwerke
    private Kraftwerk kw_id;
     */
    @ManyToOne(targetEntity = Kraftwerk.class)
    @JoinColumn(name = "kw_id")
    private Kraftwerk kraftwerk;



    @OneToMany(targetEntity = Nutzermanagment.class, mappedBy = "mitarbeiter_id", cascade = CascadeType.ALL) //ein Mitarbeiter zu vielen Nutzern
    private List<Mitarbeiter> Mitarbeiter = new ArrayList<>();


    @OneToMany(targetEntity = Systeme.class, mappedBy = "mitarbeiter_id", cascade = CascadeType.ALL) //ein Mitarbeiter zu vielen Systemen
    private List<Mitarbeiter> Systemverantwortlicher = new ArrayList<>();


    //Konstruktoren
    protected  Mitarbeiter() {

    }

    public Mitarbeiter(Long mitarbeiter_id, String nachname, String vorname, String abteilung, String telefon, String mail, Kraftwerk kraftwerk) {
        this.mitarbeiter_id = mitarbeiter_id;
        this.nachname = nachname;
        this.vorname = vorname;
        this.abteilung = abteilung;
        this.telefon = telefon;
        this.mail = mail;
        this.kraftwerk = kraftwerk;

    }

    //alle Getter und Setter

    public Long getMitarbeiter_id() {return  this.mitarbeiter_id;}
    public void setMitarbeiter_id(Long mitarbeiter_id) {this.mitarbeiter_id = mitarbeiter_id;}

    public String getNachname () {return this.nachname;}
    public void setNachname (String nachname) {this.nachname = nachname;}

    public String getVorname () {return this.vorname;}
    public void setVorname (String vorname) {this.vorname = vorname;}

    public String getAbteilung () {return this.abteilung;}
    public void setAbteilung (String abteilung) {this.abteilung = abteilung;}

    public String getTelefon () {return this.telefon;}
    public void setTelefon (String telefon) {this.telefon = telefon;}

    public String getMail () {return this.mail;}
    public void setMail (String mail) {this.mail = mail;}

    public Kraftwerk getKw_id() {return  kraftwerk;}
    public void setKw_id(Kraftwerk kraftwerk) {this.kraftwerk = kraftwerk;}

    @Override
    public String toString() {
        return "Mitarbeiter [mitarbeiter_id=" + mitarbeiter_id + ", nachname=" + nachname + ", vorname=" + vorname + ", abteilung=" + abteilung + ", telefon=" + telefon +", mail=" + mail + ", kw_id=" + kraftwerk +"]";
    }
}
