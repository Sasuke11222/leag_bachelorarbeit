package com.bezkoder.springjwt.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "office") //Tabellenname
public class Office {

    //alle Bezeichnungen der Tabellenspalten
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Wert wird automatisch vergeben
    @Column(name = "office_id")
    private Long office_id;

    @Column(name = "version")
    private String version;

    @OneToMany(targetEntity = IT_Element.class, mappedBy = "office_id", cascade = CascadeType.ALL) //Ein Office zu vielen IT-Elementen
    private List<IT_Element> it_elements;

    //Konstruktoren
    public Office() {

    }

    public Office(String version) {
        this.version = version;

    }

    //alle Getter und Setter

    public String getVersion () {return this.version;}
    public void setVersion (String version) {this.version = version;}

    public Long getOffice_id() {return  this.office_id;}
    public void setOffice_id(Long office_id) {this.office_id = office_id;}

    @Override
    public String toString() {
        return "Office [office_id=" + office_id + ", version=" + version +"]";
    }
}
