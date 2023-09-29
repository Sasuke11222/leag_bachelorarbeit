package com.bezkoder.springjwt.models;

import javax.persistence.*;

@Entity
@Table(name = "it_element") //Tabellenname
public class IT_Element {
    //alle Bezeichnungen der Tabellenspalten
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Wert wird automatisch vergeben
    @Column(name = "it_element_id")
    private Long it_element_id;

    @ManyToOne() //viele IT-Elemente zu einem Kraftwerk
    @JoinColumn(name = "kw_id", referencedColumnName = "kw_id") //Join Tabelle kraftwerke
    private Kraftwerk kw_id;

    @Column(name = "kks")
    private String kks;

    @Column(name = "kurztext")
    private String kurztext;

    @ManyToOne() // viele IT-Elemente zu einer Systemeinheit
    @JoinColumn(name = "systemeinheit_id", referencedColumnName = "systemeinheit_id") //Join Tabelle systemeinheiten
    private Systemeinheit systemeinheit_id;

    @ManyToOne() // viele IT-Elemente zu einem System
    @JoinColumn(name = "system_id", referencedColumnName = "system_id") //Join Tabelle systeme
    private Systeme system_id;

    @ManyToOne() // viele IT-Elemente zu einem USB-Status
    @JoinColumn(name = "status_usb_id", referencedColumnName = "status_usb_id") //Join Tabelle status_usb
    private Status_USB status_usb_id;

    @ManyToOne() //viele IT-Elemente zu einem Virenschutzhersteller
    @JoinColumn(name="virenschutz_hersteller_id", referencedColumnName = "virenschutz_hersteller_id") //Join Tabelle virenschutzhersteller
    private Virenschutzhersteller virenschutz_hersteller_id;

    @ManyToOne() //viele IT-Elemente zu einem RJ45-Status
    @JoinColumn(name="status_rj45_id", referencedColumnName = "status_rj45_id") //Join Tabelle status_rj45
    private Status_RJ45 status_rj45_id;

    @ManyToOne() //viele IT-Elemente zu einem Betriebssystem
    @JoinColumn(name="betriebssystem_id", referencedColumnName = "betriebssystem_id") //Join Tabelle betriebssysteme
    private Betriebssystem betriebssystem_id;

    @ManyToOne() //viele IT-Elemente zu einem Firewall-Status
    @JoinColumn(name="status_firewall_id", referencedColumnName = "status_firewall_id") //Join Tabelle status_firewall
    private Status_Firewall status_firewall_id;

    @ManyToOne() //viele IT-Elemente zu einem Virenschutzstatus
    @JoinColumn(name="status_virenschutz_id", referencedColumnName = "status_virenschutz_id") //Join Tabelle status_virenschutz
    private Status_Virenschutz status_virenschutz_id;

    @ManyToOne() //viele IT-Elemente zu einem Systemhersteller
    @JoinColumn(name="systemhersteller_id", referencedColumnName = "systemhersteller_id") //Join Tabelle systemhersteller
    private Systemhersteller systemhersteller_id;


    //private Integer systemhersteller_id;

    @Column(name = "modell")
    private String modell;

    @Column(name = "firmwareversion")
    private String firmwareversion;

    @ManyToOne() //viele IT-Elemente zu einem Office
    @JoinColumn(name="office_id", referencedColumnName = "office_id") //Join Tabelle office
    private Office office_id;

    @Column(name = "ibs_datum")
    private Integer ibs_datum;

    @Column(name = "sonstige_sw")
    private String sonstige_sw;

    @Column(name = "checkliste")
    private String checkliste;

    @Column(name = "backup")
    private String backup;

    @Column(name = "backup_test")
    private String backup_test;


    //Konstruktoren
    public IT_Element() {

    }

    public IT_Element(
            Kraftwerk kw_id,
            String kks,
            String kurztext,
            Systemeinheit systemeinheit_id,
            Systeme system_id,
            Status_USB status_usb_id,
            Virenschutzhersteller virenschutz_hersteller_id,
            Status_RJ45 status_rj45_id,
            Betriebssystem betriebssystem_id,
            Status_Firewall status_firewall_id,
            Status_Virenschutz status_virenschutz_id,
            Systemhersteller systemhersteller_id,
            String modell,
            String firmwareversion,
            Office office_id,
            Integer ibs_datum,
            String sonstige_sw,
            String checkliste,
            String backup,
            String backup_test) {
        this.kw_id = kw_id;
        this.kks = kks;
        this.kurztext = kurztext;
        this.systemeinheit_id = systemeinheit_id;
        this.system_id = system_id;
        this.status_usb_id = status_usb_id;
        this.virenschutz_hersteller_id = virenschutz_hersteller_id;
        this.status_rj45_id = status_rj45_id;
        this.betriebssystem_id = betriebssystem_id;
        this.status_firewall_id = status_firewall_id;
        this.status_virenschutz_id = status_virenschutz_id;
        this.systemhersteller_id = systemhersteller_id;
        this.modell = modell;
        this.firmwareversion = firmwareversion;
        this.office_id = office_id;
        this.ibs_datum = ibs_datum;
        this.sonstige_sw = sonstige_sw;
        this.checkliste = checkliste;
        this.backup = backup;
        this.backup_test = backup_test;
    }

    //alle Getter und Setter
    public Long getIt_element_id() {return  this.it_element_id;}
    public void setIt_element_id(Long it_element_id) {this.it_element_id = it_element_id;}

    /*** @return the KW*/
    public Kraftwerk getKw_id() {return  this.kw_id;}
    /*** @param kw_id the kw_id to set*/
    public void setKw_id(Kraftwerk kw_id) {this.kw_id = kw_id;}

    public String getKKS () {return this.kks;}
    public void setKKS (String kks) {this.kks = kks;}

    public String getKurztext () {return this.kurztext;}
    public void setKurztext (String kurztext) {this.kurztext = kurztext;}

    /*** @return the systemeinheit*/
    public Systemeinheit getSystemeinheit_id() {return  this.systemeinheit_id;}
    /*** @param systemeinheit_id the systemeinheit_id to set*/
    public void setSystemeinheit_id(Systemeinheit systemeinheit_id) {this.systemeinheit_id = systemeinheit_id;}

    /*** @return the systeme_id*/
    public Systeme getSystem_id() {return  this.system_id;}
    /*** @param systeme_id the systeme_id to set*/
    public void setSystem_id(Systeme system_id) {this.system_id = system_id;}

    /*** @return the status_usb*/
    public Status_USB getStatus_usb_id() {return  this.status_usb_id;}
    /*** @param status_usb_id the status_usb_id to set*/
    public void setStatus_usb_id(Status_USB status_usb_id) {this.status_usb_id = status_usb_id;}

    /*** @return the virenschutzhersteller*/
    public Virenschutzhersteller getVirenschutz_hersteller_id() {return  this.virenschutz_hersteller_id;}
    /*** @param virenschutz_hersteller_id the virenschutz_hersteller_id to set*/
    public void setVirenschutz_hersteller_id(Virenschutzhersteller virenschutz_hersteller_id) {this.virenschutz_hersteller_id = virenschutz_hersteller_id;}

    /*** @return the status_rj45*/
    public Status_RJ45 getStatus_rj45_id() {return  this.status_rj45_id;}
    /*** @param status_rj45_id the status_rj45_id to set*/
    public void setStatus_rj45_id(Status_RJ45 status_rj45_id) {this.status_rj45_id = status_rj45_id;}

    /*** @return the betriebssystem_id*/
    public Betriebssystem getBetriebssystem_id() {return betriebssystem_id;}
    /*** @param betriebssystem_id the betriebssystem_id to set*/
    public void setBetriebssystem_id(Betriebssystem betriebssystem_id) {this.betriebssystem_id = betriebssystem_id;}

    /*** @return the status_firewall*/
    public Status_Firewall getStatus_firewall_id() {return  this.status_firewall_id;}
    /*** @param status_firewall_id the status_firewall_id to set*/
    public void setStatus_firewall_id(Status_Firewall status_firewall_id) {this.status_firewall_id = status_firewall_id;}

    /*** @return the status_virenschutz*/
    public Status_Virenschutz getStatus_virenschutz_id() {return  this.status_virenschutz_id;}
    /*** @param status_virenschutz_id the status_virenschutz_id to set*/
    public void setStatus_virenschutz_id(Status_Virenschutz status_virenschutz_id) {this.status_virenschutz_id = status_virenschutz_id;}

    /*** @return the systemhersteller*/
    public Systemhersteller getSystemhersteller_id() {return  this.systemhersteller_id;}
    /*** @param systemherstelelr_id the systemhersteller_id to set*/
    public void setSystemhersteller_id(Systemhersteller systemhersteller_id) {this.systemhersteller_id = systemhersteller_id;}

    public String getModell () {return this.modell;}
    public void setModell (String modell) {this.modell = modell;}

    public String getFirmwareversion () {return this.firmwareversion;}
    public void setFirmwareversion (String firmwareversion) {this.firmwareversion = firmwareversion;}

    /*** @return the office*/
    public Office getOffice_id() {return  this.office_id;}
    /*** @param office_id the office_id to set*/
    public void setOffice_id(Office office_id) {this.office_id = office_id;}

    public Integer getIbs_datum() {return  this.ibs_datum;}
    public void setIbs_datum(Integer ibs_datum) {this.ibs_datum = ibs_datum;}

    public String getSonstige_sw () {return this.sonstige_sw;}
    public void setSonstige_sw (String sonstige_sw) {this.sonstige_sw = sonstige_sw;}

    public String getCheckliste () {return this.checkliste;}
    public void setCheckliste (String checkliste) {this.checkliste = checkliste;}

    public String getBackup () {return this.backup;}
    public void setBackup (String backup) {this.backup = backup;}

    public String getBackup_test () {return this.backup_test;}
    public void setBackup_test (String backup_test) {this.backup_test = backup_test;}

    @Override
    public String toString() {
        return "IT-Element [it_element_id=" + it_element_id
                + ", kw_id=" + kw_id
                + ", kks=" + kks
                + ", kurztext=" + kurztext
                + ", systemeinheit_id=" + systemeinheit_id
                + ", system_id=" + system_id
                + ", status_usb_id=" + status_usb_id
                + ", virenschutz_hersteller_id=" + virenschutz_hersteller_id
                + ", status_rj45_id=" + status_rj45_id
                + ", betriebssystem_id=" + betriebssystem_id
                + ", status_firewall_id=" + status_firewall_id
                + ", status_virenschutz_id=" + status_virenschutz_id
                + ", systemhersteller_id=" + systemhersteller_id
                + ", modell=" + modell
                + ", firmwareversion=" + firmwareversion
                + ", office_id=" + office_id
                + ", ibs_datum=" + ibs_datum
                + ", sonstige_sw=" + sonstige_sw
                + ", checkliste=" + checkliste
                + ", backup=" + backup
                + ", backup_test=" + backup_test +"]";
    }
}
