package com.kurkiewiczfuturetechnology.spizarnia;

import java.util.ArrayList;

/**
 * Created by Slowo on 2016-12-23.
 */

public class Uzytkownik {
    public Uzytkownik(int idUzytkownik, String login, String haslo) {
        this.idUzytkownik = idUzytkownik;
        Login = login;
        Haslo = haslo;
    }

    public int getIdUzytkownik() {
        return idUzytkownik;
    }

    public void setIdUzytkownik(int idUzytkownik) {
        this.idUzytkownik = idUzytkownik;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getHaslo() {
        return Haslo;
    }

    public void setHaslo(String haslo) {
        Haslo = haslo;
    }

    private int idUzytkownik;
    private String Login;
    private String Haslo;
    private ArrayList<Spizarnia> spizarnia;
    public ArrayList<Spizarnia> getSpizarnia(){return spizarnia;}
    public void setSpizarnia(ArrayList<Spizarnia> spizarnia){
        this.spizarnia=spizarnia;
    }

}
