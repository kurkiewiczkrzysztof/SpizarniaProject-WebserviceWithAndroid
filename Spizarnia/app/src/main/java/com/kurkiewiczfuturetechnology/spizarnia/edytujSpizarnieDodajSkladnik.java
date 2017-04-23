package com.kurkiewiczfuturetechnology.spizarnia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class edytujSpizarnieDodajSkladnik extends AppCompatActivity {
    public static final String STUDENT_ID_MESSAGE="polsl.mojaaplikacja.studentID";
    private RecyclerView mEdytujSpizarnieKategorieRecyclerView;
    private edytujSpizarnieKategoriaAdapter mAdapter;
    public static final int ADD_REQUEST =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edytuj_spizarnie_dodaj_skladnik);

        mEdytujSpizarnieKategorieRecyclerView = (RecyclerView)findViewById(R.id.rc_SkladnikiWSpizarniKategoria);
        mEdytujSpizarnieKategorieRecyclerView.setLayoutManager(new LinearLayoutManager(edytujSpizarnieDodajSkladnik.this));
        mAdapter = new edytujSpizarnieKategoriaAdapter(new ArrayList<Kat_Zywnosc>());
        mEdytujSpizarnieKategorieRecyclerView.setAdapter(mAdapter);
        new getEdytujSpizarnieKategorieTask().execute();
    }

    public void onResume(){
        super.onResume();
        new getEdytujSpizarnieKategorieTask().execute();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK) mAdapter.notifyDataSetChanged();
    }
    public void onClickWybierzKategorie(View view){
        Intent intent =new Intent(edytujSpizarnieDodajSkladnik.this,edytujSpizarnieDodajSkladnik2.class);
        startActivityForResult(intent,ADD_REQUEST);
    }
    class getEdytujSpizarnieKategorieTask extends AsyncTask<Void,Void,ArrayList<Kat_Zywnosc>>
    {
        @Override
        protected ArrayList<Kat_Zywnosc> doInBackground(Void... params) {
            Repozytorium rp = Repozytorium.getSingelton();
            return  rp.getKategorie();
        }
        protected void onPostExecute(ArrayList<Kat_Zywnosc> spizarnia2s){
            mAdapter.mKat_zywnoscs=spizarnia2s;
            mAdapter.notifyDataSetChanged();
        }
    }

    class edytujSpizarnieKategoriaHolder extends RecyclerView.ViewHolder {
        private TextView tvid,tvnazwa;
        private Kat_Zywnosc mKat_zywnosc;
        private Button mBtnWybierz;

        public edytujSpizarnieKategoriaHolder(View itemView) {
            super(itemView);
            tvnazwa = (TextView) itemView.findViewById(R.id.TVnazwaKategorii);
            tvid = (TextView) itemView.findViewById(R.id.TVidKategorii);
            mBtnWybierz = (Button) itemView.findViewById(R.id.btnWybierzKategorie);
        }

        public void bindEdytujKategoriaSpizarnie(Kat_Zywnosc kat_zywnosc) {
            mKat_zywnosc = kat_zywnosc;
            tvnazwa.setText(mKat_zywnosc.getNazwa_kat());
            tvid.setText(Integer.toString(mKat_zywnosc.getIdkat_zywnosc()));
            mBtnWybierz.setOnClickListener(new Button.OnClickListener()
            {
                public void onClick(View v){
                    Intent intent = new Intent(edytujSpizarnieDodajSkladnik.this,edytujSpizarnieDodajSkladnik2.class);
                    intent.putExtra(STUDENT_ID_MESSAGE,edytujSpizarnieKategoriaHolder.this.mKat_zywnosc.getIdkat_zywnosc());
                    startActivityForResult(intent,ADD_REQUEST);
                }
            });
        }
    }
    class edytujSpizarnieKategoriaAdapter extends RecyclerView.Adapter<edytujSpizarnieDodajSkladnik.edytujSpizarnieKategoriaHolder> {
        private List<Kat_Zywnosc> mKat_zywnoscs;

        public edytujSpizarnieKategoriaAdapter(List<Kat_Zywnosc> kat_zywnoscs) {
            mKat_zywnoscs=kat_zywnoscs;
        }

        @Override
        public edytujSpizarnieKategoriaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(edytujSpizarnieDodajSkladnik.this);
            View view = layoutInflater
                    .inflate(R.layout.edytujspizarniekategoria_wiersz, parent, false);
            return new edytujSpizarnieKategoriaHolder(view);
        }

        @Override
        public void onBindViewHolder(edytujSpizarnieKategoriaHolder holder, int position) {
            Kat_Zywnosc kat_zywnosc = mKat_zywnoscs.get(position);
            holder.bindEdytujKategoriaSpizarnie(kat_zywnosc);
        }

        @Override
        public int getItemCount() {
            return mKat_zywnoscs.size();
        }
    }
}
