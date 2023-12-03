package com.bezkoder.springjwt.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "status_firewall") //Tabellenname
public class Status_Firewall {

    //alle Bezeichnungen der Tabellenspalten
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Wert wird automatisch vergeben
    @Column(name = "status_firewall_id")
    private Long status_firewall_id;

    @Column(name = "status")
    private String status;

    @OneToMany(targetEntity = IT_Element.class, mappedBy = "status_firewall_id", cascade = CascadeType.ALL) //Ein Firewallstatus zu vielen IT-Elementen
    private List<IT_Element> it_elements;

    //Konstruktoren
    public Status_Firewall() {

    }

    public Status_Firewall(String status) {
        this.status = status;

    }

    //alle Getter und Setter

    public String getStatus () {return this.status;}
    public void setStatus (String status) {this.status = status;}

    public Long getStatus_firewall_id() {return  this.status_firewall_id;}
    public void setStatus_firewall_id(Long status_firewall_id) {this.status_firewall_id = status_firewall_id;}

    @Override
    public String toString() {
        return "Firewallstatus [status_firewall_id=" + status_firewall_id + ", status=" + status +"]";
    }
}

