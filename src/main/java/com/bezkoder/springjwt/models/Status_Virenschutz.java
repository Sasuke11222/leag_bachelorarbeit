package com.bezkoder.springjwt.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "status_virenschutz") //Tabellenname
public class Status_Virenschutz {

    //alle Bezeichnungen der Tabellenspalten
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Wert wird automatisch vergeben
    @Column(name = "status_virenschutz_id")
    private Long status_virenschutz_id;

    @Column(name = "status")
    private String status;

    @OneToMany(targetEntity = IT_Element.class, mappedBy = "status_virenschutz_id", cascade = CascadeType.ALL) //Ein Virenschutzstatus zu vielen IT-Elementen
    private List<Status_Virenschutz> Status_Virenschutz = new ArrayList<>();

    //Konstruktoren
    public Status_Virenschutz() {

    }

    public Status_Virenschutz(String status) {
        this.status = status;

    }

    //alle Getter und Setter

    public String getStatus () {return this.status;}
    public void setStatus (String status) {this.status = status;}

    public Long getStatus_virenschutz_id() {return  this.status_virenschutz_id;}
    public void setStatus_virenschutz_id(Long status_virenschutz_id) {this.status_virenschutz_id = status_virenschutz_id;}

    @Override
    public String toString() {
        return "Virenschutzstatus [status_virenschutz_id=" + status_virenschutz_id + ", status=" + status +"]";
    }
}
