package com.kurkiewiczfuturetechnology.spizarnia;

/**
 * Created by Slowo on 2016-12-23.
 */

public class SkladnikiWSpizarni {
    public int idSkladnikSpiz;

    public int getIdSkladnikSpiz() {
        return idSkladnikSpiz;
    }

    public void setIdSkladnikSpiz(int idSkladnikSpiz) {
        this.idSkladnikSpiz = idSkladnikSpiz;
    }

    public double getIlosc() {
        return Ilosc;
    }

    public void setIlosc(double ilosc) {
        Ilosc = ilosc;
    }

    public int getIdSpizarnia() {
        return idSpizarnia;
    }

    public void setIdSpizarnia(int idSpizarnia) {
        this.idSpizarnia = idSpizarnia;
    }

    public int getIdZywnosc() {
        return idZywnosc;
    }

    public void setIdZywnosc(int idZywnosc) {
        this.idZywnosc = idZywnosc;
    }

    public double Ilosc;
    public int idSpizarnia;

    public SkladnikiWSpizarni(int idSkladnikSpiz, double ilosc, int idSpizarnia, int idZywnosc) {
        this.idSkladnikSpiz = idSkladnikSpiz;
        this.Ilosc = ilosc;
        this.idSpizarnia = idSpizarnia;
        this.idZywnosc = idZywnosc;
    }

    public int idZywnosc;
}
