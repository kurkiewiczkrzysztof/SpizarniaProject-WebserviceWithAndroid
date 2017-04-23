package com.kurkiewiczfuturetechnology.spizarnia;

import java.util.ArrayList;

/**
 * Created by Slowo on 2016-12-23.
 */

public class Zywnosc {
    public Zywnosc(int idZywnosc, String nazwa, int idkat_zywnosc) {
        this.idZywnosc = idZywnosc;
        Nazwa = nazwa;
        this.idkat_zywnosc = idkat_zywnosc;
    }

    public int getIdZywnosc() {
        return idZywnosc;
    }

    public void setIdZywnosc(int idZywnosc) {
        this.idZywnosc = idZywnosc;
    }

    public String getNazwa() {
        return Nazwa;
    }

    public void setNazwa(String nazwa) {
        Nazwa = nazwa;
    }

    public int getIdkat_zywnosc() {
        return idkat_zywnosc;
    }

    public void setIdkat_zywnosc(int idkat_zywnosc) {
        this.idkat_zywnosc = idkat_zywnosc;
    }

    public ArrayList<SkladnikiWSpizarni> getSkladniWSpizarnis() {
        return skladniWSpizarnis;
    }

    public void setSkladniWSpizarnis(ArrayList<SkladnikiWSpizarni> skladniWSpizarnis) {
        this.skladniWSpizarnis = skladniWSpizarnis;
    }

    private int idZywnosc;
    private String Nazwa;
    private int idkat_zywnosc;
    public ArrayList<SkladnikiWSpizarni> skladniWSpizarnis;
}
