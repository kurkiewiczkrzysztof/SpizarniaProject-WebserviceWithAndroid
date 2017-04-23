package com.kurkiewiczfuturetechnology.spizarnia;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class edytujSpizarnieDodajSkladnik2 extends AppCompatActivity {
    private RecyclerView mZywnoscRecyclerView;
    private ZywnoscAdapter mAdapter;
    SkladnikiWSpizarni skladnikiWSpizarni;
    int mId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edytuj_spizarnie_dodaj_skladnik2);
        Intent intent= getIntent();
        mId=intent.getIntExtra(edytujSpizarnieDodajSkladnik.STUDENT_ID_MESSAGE,0);
        mZywnoscRecyclerView = (RecyclerView)findViewById(R.id.rc_SkladnikiWSpizarniZywnosc);
        mZywnoscRecyclerView.setLayoutManager(new LinearLayoutManager(edytujSpizarnieDodajSkladnik2.this));
        mAdapter = new ZywnoscAdapter(new ArrayList<Zywnosc>());
        mZywnoscRecyclerView.setAdapter(mAdapter);
        new getZywnoscTask().execute();
    }
    class getZywnoscTask extends AsyncTask<Void,Void,ArrayList<Zywnosc>>
    {
        protected ArrayList<Zywnosc> doInBackground(Void... params){
            Repozytorium sDB = Repozytorium.getSingelton();

            return sDB.getZywnosc(mId);
        }
        protected void onPostExecute(ArrayList<Zywnosc> zywnoscs){
            mAdapter.mZywnosc=zywnoscs;
            mAdapter.notifyDataSetChanged();
        }
    }
    class DodajSkladnikTask extends  AsyncTask<SkladnikiWSpizarni,Void,Void>
    {
        protected  Void doInBackground(SkladnikiWSpizarni... skladnikiWSpizarnis){
            Repozytorium udB = Repozytorium.getSingelton();
            udB.dodajSkladnikWSpizarni(skladnikiWSpizarnis[0]);
            return null;
        }
        protected void onPostExecute(Void v){
            setResult(RESULT_OK);
            finish();
        }
    }
    class ZywnoscHolder extends RecyclerView.ViewHolder {
        private TextView twid,twnazwa,twidkat;
        private Zywnosc mZywnosc;
        private Button mDodaj;
        private EditText edIlosc;
        public ZywnoscHolder(View itemView) {
            super(itemView);

            twid = (TextView)itemView.findViewById(R.id.TVidZywnosc);
            twnazwa = (TextView)itemView.findViewById(R.id.TVNazwaZywnosc);
            twidkat = (TextView)itemView.findViewById(R.id.TVKategoriaZywnosc);
            mDodaj = (Button)itemView.findViewById(R.id.btnDodajFinalnieSkladnik);
            edIlosc = (EditText) itemView.findViewById(R.id.edPodajIlosc);
        }

        public void bindZywnosc(Zywnosc zywnosc) {
            mZywnosc = zywnosc;
            twid.setText(String.valueOf(mZywnosc.getIdZywnosc()));
            twnazwa.setText(mZywnosc.getNazwa());
            twidkat.setText(String.valueOf(mZywnosc.getIdkat_zywnosc()));
            mDodaj.setOnClickListener(new Button.OnClickListener()
            {
                public void onClick(View v){
                    double ilosc = Double.parseDouble(edIlosc.getText().toString());
                    int id = Integer.parseInt(twid.getText().toString());
                    skladnikiWSpizarni = new SkladnikiWSpizarni(0,ilosc,1,id);
                    new DodajSkladnikTask().execute(skladnikiWSpizarni);
                }
            });

        }

    }
    private class ZywnoscAdapter extends RecyclerView.Adapter<ZywnoscHolder> {

        private List<Zywnosc> mZywnosc;

        public ZywnoscAdapter(List<Zywnosc> zywnosc) {
            mZywnosc=zywnosc;
        }
        @Override
        public ZywnoscHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(edytujSpizarnieDodajSkladnik2.this);
            View view = layoutInflater.inflate(R.layout.zywnoscwiersz,parent,false);
            return new ZywnoscHolder(view);
        }

        @Override
        public void onBindViewHolder(ZywnoscHolder holder, int position) {
            Zywnosc zywnosc = mZywnosc.get(position);
            holder.bindZywnosc(zywnosc);

        }
        @Override
        public int getItemCount() {
            return mZywnosc.size();
        }
    }

}
