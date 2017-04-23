package com.kurkiewiczfuturetechnology.spizarnia;

import java.util.ArrayList;

/**
 * Created by Slowo on 2016-12-23.
 */

public class Spizarnia {
    public Spizarnia(int idSpizarnia, String nazwa, String nr_seryjny, int idUzytkownik) {
        this.idSpizarnia = idSpizarnia;
        this.nazwa = nazwa;
        this.nr_seryjny = nr_seryjny;
        this.idUzytkownik = idUzytkownik;
    }

    private int idSpizarnia;
    private String nazwa;
    public String nr_seryjny;
    private int idUzytkownik;

    public int getIdUzytkownik() {
        return idUzytkownik;
    }

    public void setIdUzytkownik(int idUzytkownik) {
        this.idUzytkownik = idUzytkownik;
    }

    public int getIdSpizarnia() {
        return idSpizarnia;
    }

    public void setIdSpizarnia(int idSpizarnia) {
        this.idSpizarnia = idSpizarnia;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getNr_seryjny() {
        return nr_seryjny;
    }

    public void setNr_seryjny(String nr_seryjny) {
        this.nr_seryjny = nr_seryjny;
    }



    public ArrayList<SkladnikiWSpizarni> getSkladnikiWSpizarnis() {
        return skladnikiWSpizarnis;
    }

    public void setSkladnikiWSpizarnis(ArrayList<SkladnikiWSpizarni> skladnikiWSpizarnis) {
        this.skladnikiWSpizarnis = skladnikiWSpizarnis;
    }

    private ArrayList<SkladnikiWSpizarni> skladnikiWSpizarnis;

}
