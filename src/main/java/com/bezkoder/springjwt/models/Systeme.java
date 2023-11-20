package com.bezkoder.springjwt.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "systeme")
public class Systeme {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long system_id;

    @Column(name = "system_name")
    private String system_name;

    @Column(name = "beschreibung")
    private String beschreibung;

    @OneToMany(targetEntity = IT_Element.class, mappedBy = "system_id", cascade = CascadeType.ALL) //Ein Systeme zu vielen IT-Elementen
    private List<Systeme> Systeme = new ArrayList<>();

    @ManyToOne() //viele Systemtypen zu einem systeme
    @JoinColumn(name="systemtyp_id", referencedColumnName = "systemtyp_id") //Join Tabelle systemtypen
    private Systemtyp systemtyp_id;

    @ManyToOne
    @JoinColumn(name = "kritikalitaet_id", referencedColumnName = "kritikalitaet_id")
    private Kritikalitaet kritikalitaet_id;

    @ManyToOne () //viele Zonen zu einem systeme
    @JoinColumn(name = "zonen_id", referencedColumnName = "zonen_id") //Join Tabelle zone
    private Zone zonen_id;

    @ManyToOne() // viele Kraftwerke zu einem systeme
    @JoinColumn(name = "kw_id", referencedColumnName = "kw_id") //Join Tabelle kraftwerke
    private Kraftwerk kw_id;

    @ManyToOne() // viele Mitarbeiter zu einem system
    @JoinColumn(name = "mitarbeiter_id", referencedColumnName = "mitarbeiter_id") //Join Tabelle kraftwerke
    private Mitarbeiter mitarbeiter_id;


    @Column(name = "systemverantwortlicher_id")
    private Integer systemverantwortlicher_id;

    @Column(name = "buerozugang")
    private Byte buerozugang;

    @Column(name = "fernzugang")
    private Byte fernzugang;

    @Column(name = "errichter")
    private String errichter;

    @Column(name = "pdn")
    private Byte pdn;

    @Column(name = "zugangsart")
    private String zugangsart;

    @Column(name = "pdndate")
    private Integer pdndate;

    @Column(name = "ksp_a")
    private Byte ksp_a;

    @Column(name = "ksp_b")
    private Byte ksp_b;

    @Column(name = "ksp_y")
    private Byte ksp_y;

    @Column(name = "box_n")
    private Byte box_n;

    @Column(name = "box_p")
    private Byte box_p;

    @Column(name = "box_q")
    private Byte box_q;

    @Column(name = "box_r")
    private Byte box_r;

    @Column(name = "box_y")
    private Byte box_y;

    @Column(name = "jae_a")
    private Byte jae_a;

    @Column(name = "jae_b")
    private Byte jae_b;

    @Column(name = "jae_c")
    private Byte jae_c;

    @Column(name = "jae_d")
    private Byte jae_d;

    @Column(name = "jae_e")
    private Byte jae_e;

    @Column(name = "jae_f")
    private Byte jae_f;

    @Column(name = "jae_y")
    private Byte jae_y;

    @Column(name = "lip_r")
    private Byte lip_r;

    @Column(name = "lip_s")
    private Byte lip_s;

    @Column(name = "lip_y")
    private Byte lip_y;

    @Column(name = "ism_relevant")
    private Integer ism_relevant;

    @Column(name = "isms_reduzierung")
    private Integer isms_reduzierung;

    @Column(name = "isms_auswirkung")
    private String isms_auswirkung;

    @Column(name = "isms_begruendung")
    private String isms_begruendung;

    public Systeme() {

    }

    public Systeme(Long system_id,
                   String system_name,
                   String beschreibung,
                   Systemtyp systemtyp_id,
                   Kritikalitaet kritikalitaet_id,
                   Zone zonen_id,
                   Kraftwerk kw_id,
                   Mitarbeiter mitarbeiter_id,
                   Integer systemverantwortlicher_id,
                   Byte buerozugang,
                   Byte fernzugang,
                   String errichter,
                   Byte pdn,
                   String zugangsart,
                   Integer pdndate,
                   Byte ksp_a,
                   Byte ksp_b,
                   Byte ksp_y,
                   Byte box_n,
                   Byte box_p,
                   Byte box_q,
                   Byte box_r,
                   Byte box_y,
                   Byte jae_a,
                   Byte jae_b,
                   Byte jae_c,
                   Byte jae_d,
                   Byte jae_e,
                   Byte jae_f,
                   Byte jae_y,
                   Byte lip_r,
                   Byte lip_s,
                   Byte lip_y,
                   Integer ism_relevant,
                   Integer isms_reduzierung,
                   String isms_auswirkung,
                   String isms_begruendung
    ) {
        this.system_id = system_id;
        this.system_name = system_name;
        this.beschreibung = beschreibung;
        this.systemtyp_id = systemtyp_id;
        this.kritikalitaet_id = kritikalitaet_id;
        this.zonen_id = zonen_id;
        this.kw_id = kw_id;
        this.mitarbeiter_id = mitarbeiter_id;
        this.systemverantwortlicher_id = systemverantwortlicher_id;
        this.buerozugang = buerozugang;
        this.fernzugang = fernzugang;
        this.errichter = errichter;
        this.pdn = pdn;
        this.zugangsart = zugangsart;
        this.pdndate = pdndate;
        this.ksp_a = ksp_a;
        this.ksp_b = ksp_b;
        this.ksp_y = ksp_y;
        this.box_n = box_n;
        this.box_p = box_p;
        this.box_q = box_q;
        this.box_r = box_r;
        this.box_y = box_y;
        this.jae_a = jae_a;
        this.jae_b = jae_b;
        this.jae_c = jae_c;
        this.jae_d = jae_d;
        this.jae_e = jae_e;
        this.jae_f = jae_f;
        this.jae_y = jae_y;
        this.lip_s = lip_s;
        this.lip_r = lip_r;
        this.lip_y = lip_y;
        this.ism_relevant = ism_relevant;
        this.isms_reduzierung = isms_reduzierung;
        this.isms_auswirkung = isms_auswirkung;
        this.isms_begruendung = isms_begruendung;
    }


    public Long getSystem_id() {return  this.system_id;}
    public void setSystem_id(Long system_id) {this.system_id = system_id;}

    public String getSystem_name () {return this.system_name;}
    public void setSystem_name (String system_name) {this.system_name = system_name;}

    public String getBeschreibung () {return this.beschreibung;}
    public void setBeschreibung (String beschreibung) {this.beschreibung = beschreibung;}

    /*** @return the systemtyp*/
    public Systemtyp getSystemtyp_id() {return  this.systemtyp_id;}
    /*** @param systemtyp_id the systemtyp_id to set*/
    public void setSystemtyp_id(Systemtyp systemtyp_id) {this.systemtyp_id = systemtyp_id;}

    /*** @return the kritikalität*/
    public Kritikalitaet getKritikalitaet_id() {return  this.kritikalitaet_id;}
    /*** @param kontoart_id the kritikalitaet_id to set*/
    public void setKritikalitaet_id(Kritikalitaet kritikalitaet_id) {this.kritikalitaet_id = kritikalitaet_id;}

    /*** @return the zone*/
    public Zone getZonen_id() {return  this.zonen_id;}
    /*** @param zone_id the zone_id to set*/
    public void setZonen_id(Zone zonen_id) {this.zonen_id = zonen_id;}

    /*** @return the kraftwerk*/
    public Kraftwerk getKw_id() {return  this.kw_id;}
    /*** @param kw_id the kw_id to set*/
    public void setKw_id(Kraftwerk kw_id) {this.kw_id = kw_id;}

    /*** @return the mitarbeiter*/
    public Mitarbeiter getMitarbeiter_id() {return  this.mitarbeiter_id;}
    /*** @param mitarbeiter_id the mitarbeiter_id to set*/
    public void setMitarbeiter_id(Mitarbeiter mitarbeiter_id) {this.mitarbeiter_id = mitarbeiter_id;}


    /*
    public Integer getMitarbeiter_id() {return  this.mitarbeiter_id;}
    public void setMitarbeiter_id(Integer mitarbeiter_id) {this.mitarbeiter_id = mitarbeiter_id;}
     */

    public Integer getSystemverantwortlicher_id() {return  this.systemverantwortlicher_id;}
    public void setSystemverantwortlicher_id(Integer systemverantwortlicher_id) {this.systemverantwortlicher_id = systemverantwortlicher_id;}

    public Byte getBuerozugang () {return this.buerozugang;}
    public void setBuerozugang (Byte buerozugang) {this.buerozugang = buerozugang;}

    public Byte getFernzugang () {return this.fernzugang;}
    public void setFernzugang (Byte fernzugang) {this.fernzugang = fernzugang;}

    public String getErrichter () {return this.errichter;}
    public void setErrichter (String errichter) {this.errichter = errichter;}

    public Byte getPdn () {return this.pdn;}
    public void setPdn (Byte pdn) {this.pdn = pdn;}

    public String getZugangsart () {return this.zugangsart;}
    public void setZugangsart (String zugangsart) {this.zugangsart = zugangsart;}

    public Integer getPdndate() {return  this.pdndate;}
    public void setPdndate(Integer pdndate) {this.pdndate = pdndate;}

    public Byte getKsp_a () {return this.ksp_a;}
    public void setKsp_a (Byte ksp_a) {this.ksp_a = ksp_a;}

    public Byte getKsp_b () {return this.ksp_b;}
    public void setKsp_b (Byte ksp_b) {this.ksp_b = ksp_b;}

    public Byte getKsp_y () {return this.ksp_y;}
    public void setKsp_y (Byte ksp_y) {this.ksp_y = ksp_y;}

    public Byte getBox_n () {return this.box_n;}
    public void setBox_n (Byte box_n) {this.box_n = box_n;}

    public Byte getBox_p () {return this.box_p;}
    public void setBox_p (Byte box_p) {this.box_p = box_p;}

    public Byte getBox_q () {return this.box_q;}
    public void setBox_q (Byte box_q) {this.box_q = box_q;}

    public Byte getBox_r () {return this.box_r;}
    public void setBox_r (Byte box_r) {this.box_r = box_r;}

    public Byte getBox_y () {return this.box_y;}
    public void setBox_y (Byte box_y) {this.box_y = box_y;}

    public Byte getJae_a () {return this.jae_a;}
    public void setJae_a (Byte jae_a) {this.jae_a = jae_a;}

    public Byte getJae_b () {return this.jae_b;}
    public void setJae_b (Byte jae_b) {this.jae_b = jae_b;}

    public Byte getJae_c() {return this.jae_c;}
    public void setJae_c (Byte jae_c) {this.jae_c = jae_c;}

    public Byte getJae_d () {return this.jae_d;}
    public void setJae_d (Byte jae_d) {this.jae_d = jae_d;}

    public Byte getJae_e () {return this.jae_e;}
    public void setJae_e (Byte jae_e) {this.jae_e = jae_e;}

    public Byte getJae_f () {return this.jae_f;}
    public void setJae_f (Byte jae_f) {this.jae_f = jae_f;}

    public Byte getJae_y () {return this.jae_y;}
    public void setJae_y (Byte jae_y) {this.jae_y = jae_y;}

    public Byte getLip_r () {return this.lip_r;}
    public void setLip_r (Byte lip_r) {this.lip_r = lip_r;}

    public Byte getLip_s () {return this.lip_s;}
    public void setLip_s (Byte lip_s) {this.lip_s = lip_s;}

    public Byte getLip_y () {return this.lip_y;}
    public void setLip_y (Byte lip_y) {this.lip_y = lip_y;}

    public Integer getIsms_Reduzierung() {return  this.isms_reduzierung;}
    public void setIsms_Reduzierung(Integer isms_reduzierung) {this.isms_reduzierung = isms_reduzierung;}

    public Integer getIsms_Relevant() {return  this.ism_relevant;}
    public void setIsms_Relevant(Integer ism_relevant) {this.ism_relevant = ism_relevant;}

    public String getIsms_Auswirkung () {return this.isms_auswirkung;}
    public void setIsms_Auswirkung (String isms_auswirkung) {this.isms_auswirkung = isms_auswirkung;}

    public String getIsms_Begruendung () {return this.isms_begruendung;}
    public void setIsms_Begruendung (String isms_begruendung) {this.isms_begruendung = isms_begruendung;}


    @Override
    public String toString() {
        return "System [system_id=" + system_id
                + ", system_name=" + system_name
                + ", beschreibung=" + beschreibung
                + ", errichter=" + errichter
                + ", zonen_id=" + zonen_id
                +"]";
    }

}

