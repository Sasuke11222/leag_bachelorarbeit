package com.bezkoder.springjwt.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "status_usb") //Tabellenname
public class Status_USB {

    //alle Bezeichnungen der Tabellenspalten
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Wert wird automatisch vergeben
    @Column(name = "status_usb_id")
    private Long status_usb_id;

    @Column(name = "status")
    private String status;

    @OneToMany(targetEntity = IT_Element.class, mappedBy = "status_usb_id", cascade = CascadeType.ALL) //Ein USB-Status zu vielen IT-Elementen
    private List<IT_Element> it_elements;

    //Konstruktoren
    public Status_USB() {

    }

    public Status_USB(String status) {
        this.status = status;

    }

    //alle Getter und Setter

    public String getStatus () {return this.status;}
    public void setStatus (String status) {this.status = status;}

    public Long getStatus_usb_id() {return  this.status_usb_id;}
    public void setStatus_usb_id(Long status_usb_id) {this.status_usb_id = status_usb_id;}

    @Override
    public String toString() {
        return "USB-Status [status_usb_id=" + status_usb_id + ", status=" + status +"]";
    }
}
