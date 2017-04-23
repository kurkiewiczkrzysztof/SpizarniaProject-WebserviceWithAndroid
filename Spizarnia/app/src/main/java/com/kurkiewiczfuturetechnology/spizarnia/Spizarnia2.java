package com.kurkiewiczfuturetechnology.spizarnia;

/**
 * Created by Slowo on 2017-01-22.
 */

public class Spizarnia2 {
    public int getIdSpizarnia() {
        return idSpizarnia;
    }

    public void setIdSpizarnia(int idSpizarnia) {
        this.idSpizarnia = idSpizarnia;
    }

    public double getIlosc() {
        return Ilosc;
    }

    public void setIlosc(double ilosc) {
        Ilosc = ilosc;
    }

    public String getNazwa() {
        return Nazwa;
    }

    public void setNazwa(String nazwa) {
        Nazwa = nazwa;
    }

    public String getNazwa_kat() {
        return Nazwa_kat;
    }

    public void setNazwa_kat(String nazwa_kat) {
        Nazwa_kat = nazwa_kat;
    }



    public int getIdSpiz() {
        return idSpiz;
    }

    public void setIdSpiz(int idSpiz) {
        this.idSpiz = idSpiz;
    }

    private int idSpizarnia;
    private int idSpiz;
    private double Ilosc;
    private String Nazwa;
    private String Nazwa_kat;

    public Spizarnia2(int idSpizarnia, double ilosc, String nazwa, String nazwa_kat, int idSpiz) {
        this.idSpizarnia = idSpizarnia;
        Ilosc = ilosc;
        Nazwa = nazwa;
        Nazwa_kat = nazwa_kat;
        this.idSpiz = idSpiz;
    }



}
