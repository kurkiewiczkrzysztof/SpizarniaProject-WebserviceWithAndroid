package com.kurkiewiczfuturetechnology.spizarnia;

import java.util.ArrayList;

/**
 * Created by Slowo on 2016-12-23.
 */

public class Kat_Zywnosc {
    public Kat_Zywnosc(int idkat_zywnosc, String nazwa_kat) {
        this.idkat_zywnosc = idkat_zywnosc;
        Nazwa_kat = nazwa_kat;
    }

    public int getIdkat_zywnosc() {
        return idkat_zywnosc;
    }

    public void setIdkat_zywnosc(int idkat_zywnosc) {
        this.idkat_zywnosc = idkat_zywnosc;
    }

    public String getNazwa_kat() {
        return Nazwa_kat;
    }

    public void setNazwa_kat(String nazwa_kat) {
        Nazwa_kat = nazwa_kat;
    }

    public ArrayList<Zywnosc> getZywnosc() {
        return zywnosc;
    }

    public void setZywnosc(ArrayList<Zywnosc> zywnosc) {
        this.zywnosc = zywnosc;
    }

    private int idkat_zywnosc;
    private String Nazwa_kat;
    public ArrayList<Zywnosc> zywnosc;

}
