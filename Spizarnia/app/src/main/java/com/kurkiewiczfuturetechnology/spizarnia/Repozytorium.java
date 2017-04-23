package com.kurkiewiczfuturetechnology.spizarnia;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Slowo on 2016-12-23.
 */

public class Repozytorium {

    public static final String UrlAddress1="http://slowo2017-001-site1.itempurl.com/api/Spizarnia/";
    public static final String UrlAddress2 = "http://slowo2017-001-site1.itempurl.com/api/Kat_Zywnosc";
    public static final String UrlAddress3 = "http://slowo2017-001-site1.itempurl.com/api/Zywnosc";
    public static final String UrlAddress4 = "http://slowo2017-001-site1.itempurl.com/api/SkladnikiWSpizarni";
    static private Repozytorium repozytorium;

    private ArrayList<SkladnikiWSpizarni> skladnikiWSpizarnis;
    private ArrayList<Spizarnia2> spizarnias;
    public ArrayList<Kat_Zywnosc> kat_zywnoscs;

    private Repozytorium(){}
    static public Repozytorium getSingelton()
    {
        if(repozytorium==null) repozytorium=new Repozytorium();
        return repozytorium;
    }

    public ArrayList<Spizarnia2> getSpizarnias()
    {
        HttpURLConnection urlConnection = null;
        ArrayList<Spizarnia2> al = new ArrayList<>();

        try {
            URL urlToRequest = new URL(Repozytorium.UrlAddress1+"/"+"1");
            urlConnection = (HttpURLConnection) urlToRequest.openConnection();
            urlConnection.setConnectTimeout(1000);
            urlConnection.setReadTimeout(1000);
            urlConnection.setRequestMethod("POST");

            if (urlConnection.getResponseCode()==HttpURLConnection.HTTP_OK) {
                InputStream in = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                String result = "";
                while ((line = bufferedReader.readLine()) != null)
                    result += line;

                JSONArray ja = new JSONArray(result);

                for (int i=0;i<ja.length();i++)
                    al.add(new Spizarnia2(1,ja.getJSONObject(i).getDouble("Ilosc"), ja.getJSONObject(i).getString("Nazwa"), ja.getJSONObject(i).getString("nazwaKat"),ja.getJSONObject(i).getInt("idSpiz")));

            }


        } catch (Exception e) {
            Log.d("getStudents exception",e.getMessage());
        }
        finally {
            if (urlConnection != null) urlConnection.disconnect();
        }

        spizarnias=al;
        return spizarnias;
    }
    public Spizarnia2 getSpizarnia2(int id)
    {
        for (int i=0;i<spizarnias.size();i++)
            if(spizarnias.get(i).getIdSpiz()==id) return spizarnias.get(i);
        return null;

    }
    public ArrayList<Kat_Zywnosc> getKategorie()
    {
        HttpURLConnection urlConnection = null;
        ArrayList<Kat_Zywnosc> al = new ArrayList<>();

        try {
            URL urlToRequest = new URL(Repozytorium.UrlAddress2);
            urlConnection = (HttpURLConnection) urlToRequest.openConnection();
            urlConnection.setConnectTimeout(1000);
            urlConnection.setReadTimeout(1000);


            if (urlConnection.getResponseCode()==HttpURLConnection.HTTP_OK) {
                InputStream in = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                String result = "";
                while ((line = bufferedReader.readLine()) != null)
                    result += line;

                JSONArray ja = new JSONArray(result);

                for (int i=0;i<ja.length();i++)
                    al.add(new Kat_Zywnosc(ja.getJSONObject(i).getInt("idkat_zywnosc"), ja.getJSONObject(i).getString("Nazwa_kat")));

            }


        } catch (Exception e) {
             Log.d("getStudents exception",e.getMessage());
        }
        finally {
            if (urlConnection != null) urlConnection.disconnect();
        }

        kat_zywnoscs=al;
        return kat_zywnoscs;
    }
    public ArrayList<Zywnosc> getZywnosc(int idKat_zywnosc)
    {
        Kat_Zywnosc kz = getKat_Zywnosc(idKat_zywnosc);

        if(kz.getZywnosc()!=null)return kz.getZywnosc();
        HttpURLConnection urlConnection = null;
        ArrayList<Zywnosc> al = new ArrayList<>();

        try {
            URL urlToRequest = new URL(UrlAddress3+"/"+idKat_zywnosc);
            urlConnection = (HttpURLConnection) urlToRequest.openConnection();
            urlConnection.setConnectTimeout(1000);
            urlConnection.setReadTimeout(1000);

            if (urlConnection.getResponseCode()==HttpURLConnection.HTTP_OK) {
                InputStream in = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                String result = "";
                while ((line = bufferedReader.readLine()) != null)
                    result += line;

                JSONArray ja = new JSONArray(result);

                for (int i=0;i<ja.length();i++)

                    al.add(new Zywnosc(ja.getJSONObject(i).getInt("idZywnosc"), ja.getJSONObject(i).getString("Nazwa"), ja.getJSONObject(i).getInt("idkat_zywnosc")));

            }


        } catch (Exception e) {
            Log.d("getStudents exception",e.getMessage());
        }
        finally {
            if (urlConnection != null) urlConnection.disconnect();
        }

        kz.setZywnosc(al);
        return  al;

    }

    public Kat_Zywnosc getKat_Zywnosc(int id)
    {
        for (int i=0;i<kat_zywnoscs.size();i++)
            if (kat_zywnoscs.get(i).getIdkat_zywnosc()==id) return kat_zywnoscs.get(i);
        return null;

    }

    public ArrayList<SkladnikiWSpizarni> getSkladnikiWSpiz()
    {

        HttpURLConnection urlConnection = null;
        ArrayList<SkladnikiWSpizarni> al = new ArrayList<>();

        try {
            URL urlToRequest = new URL(UrlAddress4);
            urlConnection = (HttpURLConnection) urlToRequest.openConnection();
            urlConnection.setConnectTimeout(1000);
            urlConnection.setReadTimeout(1000);

            if (urlConnection.getResponseCode()==HttpURLConnection.HTTP_OK) {
                InputStream in = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                String result = "";
                while ((line = bufferedReader.readLine()) != null)
                    result += line;

                JSONArray ja = new JSONArray(result);

                for (int i=0;i<ja.length();i++)

                    al.add(new SkladnikiWSpizarni(ja.getJSONObject(i).getInt("idSkladnikSpiz"), ja.getJSONObject(i).getDouble("Ilosc"), ja.getJSONObject(i).getInt("idSpizarnia"),ja.getJSONObject(i).getInt("idZywnosc")));

            }


        } catch (Exception e) {
            String err = (e.getMessage()==null)?"Failed " :e.getMessage();
            Log.d("getSkladnikiWSpizarnasn",err);
        }
        finally {
            if (urlConnection != null) urlConnection.disconnect();
        }

        skladnikiWSpizarnis=al;
        return  skladnikiWSpizarnis;
    }
    public SkladnikiWSpizarni skladnikiWSpizarni(int id)
    {
        for (int i=0;i<skladnikiWSpizarnis.size();i++)
            if(skladnikiWSpizarnis.get(i).getIdSkladnikSpiz()==id) return skladnikiWSpizarnis.get(i);
        return null;

    }
    public static void dodajSkladnikWSpizarni(SkladnikiWSpizarni skladnikiWSpizarni)
    {
        HttpURLConnection urlConnection = null;
        ;
        try {
            URL urlToRequest = new URL(UrlAddress4);
            urlConnection = (HttpURLConnection) urlToRequest.openConnection();
            urlConnection.setConnectTimeout(1000);
            urlConnection.setReadTimeout(1000);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestMethod("POST");


            JSONObject jsonparam = new JSONObject();
            jsonparam.put("idSkladnikSpiz",skladnikiWSpizarni.getIdSkladnikSpiz());
            jsonparam.put("Ilosc",skladnikiWSpizarni.getIlosc());
            jsonparam.put("idSpizarnia",skladnikiWSpizarni.getIdSpizarnia());
            jsonparam.put("idZywnosc",skladnikiWSpizarni.getIdZywnosc());



            OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
            out.write(jsonparam.toString());
            out.close();
            Log.d(String.valueOf(urlConnection.getResponseCode()), "dodajSkladnikWSpizarni: ");
            Log.d("dodajSkladniklele: ",String.valueOf(urlConnection.getResponseCode()) );
        } catch (Exception e) {
            String err = (e.getMessage()==null)?"Failed " :e.getMessage();
            Log.d("getStudents exception",err);
        }
        finally {
            if (urlConnection != null) urlConnection.disconnect();
        }

    }
    public void deleteSkladnikWSpizarni(int id) {
        if (spizarnias != null) spizarnias=getSpizarnias();
        HttpURLConnection urlConnection = null;
        ArrayList<SkladnikiWSpizarni> al = new ArrayList<>();
        try {
            URL urlToRequest = new URL(UrlAddress4+"/"+id);
            urlConnection = (HttpURLConnection) urlToRequest.openConnection();
            urlConnection.setConnectTimeout(1000);
            urlConnection.setReadTimeout(1000);
            urlConnection.setRequestMethod("DELETE");

            if (urlConnection.getResponseCode()==HttpURLConnection.HTTP_OK) {
                spizarnias.remove(getSpizarnia2(id));
            }


        } catch (Exception e) {
            Log.d("getStudents exception",e.getMessage());
        }
        finally {
            if (urlConnection != null) urlConnection.disconnect();
        }


    }

    public void editSkladnikiWSpizarni(SkladnikiWSpizarni skladnikiWSpizarni)
    {
        if (skladnikiWSpizarnis != null) skladnikiWSpizarnis=getSkladnikiWSpiz();
        HttpURLConnection urlConnection = null;
        ;
        try {
            URL urlToRequest = new URL(UrlAddress4+"/"+skladnikiWSpizarni.getIdSkladnikSpiz());
            urlConnection = (HttpURLConnection) urlToRequest.openConnection();
            urlConnection.setConnectTimeout(1000);
            urlConnection.setReadTimeout(1000);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestMethod("PUT");


            JSONObject jsonparam = new JSONObject();
            jsonparam.put("idSkladnikSpiz",skladnikiWSpizarni.getIdSkladnikSpiz());
            jsonparam.put("Ilosc",skladnikiWSpizarni.getIlosc());
            jsonparam.put("idSpizarnia",skladnikiWSpizarni.getIdSpizarnia());
            jsonparam.put("idZywnosc",skladnikiWSpizarni.getIdZywnosc());
            OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
            wr.write(jsonparam.toString());
            wr.close();
            if (urlConnection.getResponseCode()==HttpURLConnection.HTTP_OK) {
                for (int i=0;i<skladnikiWSpizarnis.size();i++)
                if(skladnikiWSpizarnis.get(i).getIdSkladnikSpiz()==skladnikiWSpizarni.getIdSkladnikSpiz()) skladnikiWSpizarnis.set(i,skladnikiWSpizarni);
            }
            Log.d("dodajSkladniklele: ",String.valueOf(urlConnection.getResponseCode()) );


        } catch (Exception e) {
            Log.d("getStudents exception",e.getMessage());
        }
        finally {
            if (urlConnection != null) urlConnection.disconnect();
        }

    }
}
