package com.bezkoder.springjwt.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "zone")
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long zonen_id;

    @Column(name = "zone")
    private String zone;

    @OneToMany(targetEntity = Systeme.class, mappedBy = "zonen_id", cascade = CascadeType.ALL) //Eine Zone zu vielen Systemen
    private List<Zone> Zone = new ArrayList<>();


    public Zone() {

    }

    public Zone(String zone) {
        this.zone = zone;
    }
    public Long getZonen_id() {
        return zonen_id;
    }

    public void setZonen_id(Long zonen_id) {
        this.zonen_id = zonen_id;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "Zone [zonen_id=" + zonen_id + ", zone=" + zone +"]";
    }
}
