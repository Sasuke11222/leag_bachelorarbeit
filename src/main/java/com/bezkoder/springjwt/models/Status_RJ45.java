package com.bezkoder.springjwt.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "status_rj45") //Tabellenname
public class Status_RJ45 {

    //alle Bezeichnungen der Tabellenspalten
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Wert wird automatisch vergeben
    @Column(name = "status_rj45_id")
    private Long status_rj45_id;

    @Column(name = "status")
    private String status;

    @OneToMany(targetEntity = IT_Element.class, mappedBy = "status_rj45_id", cascade = CascadeType.ALL) //Ein RJ45status zu vielen IT-Elementen
    private List<IT_Element> it_elements;

    //Konstruktoren
    public Status_RJ45() {

    }

    public Status_RJ45(String status) {
        this.status = status;

    }

    //alle Getter und Setter

    public String getStatus () {return this.status;}
    public void setStatus (String status) {this.status = status;}

    public Long getStatus_rj45_id() {return  this.status_rj45_id;}
    public void setStatus_rj45_id(Long status_rj45_id) {this.status_rj45_id = status_rj45_id;}

    @Override
    public String toString() {
        return "RJ45-Status [status_rj45_id=" + status_rj45_id + ", status=" + status +"]";
    }
}
