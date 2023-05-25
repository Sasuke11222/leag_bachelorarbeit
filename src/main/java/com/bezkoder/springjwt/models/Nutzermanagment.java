package com.bezkoder.springjwt.models;
import javax.persistence.*;

@Entity
@Table(name = "nutzermanagement") //Tabellenname
public class Nutzermanagment {

    //alle Bezeichnungen der Tabellenspalten
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Wert wird automatisch vergeben
    @Column(name = "ID")
    private Long ID;

    @Column(name = "system_id")
    private Integer system_id;

    @ManyToOne() //viele Mitarbeiter zu dem Nutzer
    @JoinColumn(name="mitarbeiter_id", referencedColumnName = "mitarbeiter_id", nullable = false) //Join Tabelle Mitarbeiter
    private Mitarbeiter mitarbeiter_id;

    @ManyToOne()// viele Kontotypen zu dem Nutzern
    @JoinColumn(name="kontotyp_id", referencedColumnName = "kontotyp_id", nullable = false) //Join Tabelle Kontotyp
    private Kontotyp kontotyp_id;

    @Column(name = "servicepartner")
    private String servicepartner;

    @Column(name = "loginname")
    private String loginname;

    @Column(name = "buerozugang")
    private String buerozugang;

    @ManyToOne() //viele Kontoarten zu dem Nutzern
    @JoinColumn(name="kontoart_id", referencedColumnName = "kontoart_id", nullable = false) //Join Tabelle Kontoart
    private  Kontoart kontoart_id;

    @Column(name = "bemerkung")
    private String bemerkung;

    //Konstruktoren
    public Nutzermanagment() {

    }

    public Nutzermanagment(Integer system_id, Mitarbeiter mitarbeiter_id, Kontotyp kontotyp_id, String servicepartner, String loginname, String buerozugang, Kontoart kontoart_id, String bemerkung) {
        this.system_id = system_id;
        this.mitarbeiter_id = mitarbeiter_id;
        this.kontotyp_id = kontotyp_id;
        this.servicepartner = servicepartner;
        this.loginname = loginname;
        this.buerozugang = buerozugang;
        this.kontoart_id = kontoart_id;
        this.bemerkung = bemerkung;
    }

    //alle Getter und Setter

    public Long getID() {return  this.ID;}
    public void setID(Long ID) {this.ID = ID;}

    /*** @return the mitarbeiter*/
    public Mitarbeiter getMitarbeiter_id() {return  mitarbeiter_id;}
    /*** @param mitarbeiter_id the mitarbeiter_id to set*/
    public void setMitarbeiter_id(Mitarbeiter mitarbeiter_id) {this.mitarbeiter_id = mitarbeiter_id;}

    public Integer getSystem_id() {return  this.system_id;}
    public void setSystem_id(Integer system_id) {this.system_id = system_id;}

    /*** @return the kontotyp*/
    public Kontotyp getKontotyp_id() {return  kontotyp_id;}
    /*** @param kontoart_id the kontoart_id to set*/
    public void setKontotyp_id(Kontotyp kontotyp_id) {this.kontotyp_id = kontotyp_id;}

    public String getServicepartner () {return this.servicepartner;}
    public void setServicepartner (String servicepartner) {this.servicepartner = servicepartner;}

    public String getLoginname () {return this.loginname;}
    public void setLoginname (String loginname) {this.loginname = loginname;}

    public String getBuerozugang () {return this.buerozugang;}
    public void setBuerozugang (String buerozugang) {this.buerozugang = buerozugang;}

    /*** @return the kontoart*/
    public Kontoart getKontoart_id() {
        return kontoart_id;
    }
    /*** @param kontoart_id the kontoart_id to set*/
    public void setKontoart_id(Kontoart kontoart_id) {this.kontoart_id = kontoart_id;}

    public String getBemerkung () {return this.bemerkung;}
    public void setBemerkung (String bemerkung) {this.bemerkung = bemerkung;}

    @Override
    public String toString() {
        return "Nutzermanagment [ID=" + ID + ", system_id=" + system_id +", mitarbeiter_id=" + mitarbeiter_id + ", kontotyp_id=" + kontotyp_id + ", servicepartner=" + servicepartner + ", loginname=" + loginname + ", buerozugang=" + buerozugang + ", kontoart_id=" + kontoart_id + ", bemerkung=" + bemerkung +"]";
    }
}
