package hu.mobilalkfejl.villanyorajelentes.model;

import android.widget.EditText;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class VillanyoraJelentes {
    private String id;
    private String userID;
    private String oraAz;
    private String oraAllas;
    private String datum;

    public VillanyoraJelentes() {
    }

    public VillanyoraJelentes(String userID,String oraAz, String oraAllas, String datum) {
        this.userID = userID;
        this.oraAz = oraAz;
        this.oraAllas = oraAllas;
        this.datum = datum;
    }

    public String getOraAz() {
        return oraAz;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setOraAz(String oraAz) {
        this.oraAz = oraAz;
    }

    public String getOraAllas() {
        return oraAllas;
    }

    public void setOraAllas(String oraAllas) {
        this.oraAllas = oraAllas;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        return "órád: "+oraAz+" Állás: "+oraAllas+"Kv, rögzítás: "+datum;
    }

    public String _getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
