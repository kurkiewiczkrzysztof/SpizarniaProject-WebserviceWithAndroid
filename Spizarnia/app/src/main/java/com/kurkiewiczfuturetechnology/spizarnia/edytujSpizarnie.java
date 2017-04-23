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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class edytujSpizarnie extends AppCompatActivity {
    private RecyclerView mEdytujSpizarnieRecyclerView;
    private edytujSpizarnieAdapter mAdapter;
    private edytujSpizarnieAdapter2 mAdapter2;
    public static final String STUDENT_ID_MESSAGE="polsl.mojaaplikacja.studentID";
    public static final int ADD_REQUEST =1;
    public static final int DELETE_REQUEST = 1;
    public static final int RESULT_CANCEL = 0;
    public static final int EDIT_REQUEST = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edytuj_spizarnie);
        mEdytujSpizarnieRecyclerView = (RecyclerView) findViewById(R.id.rc_SkladnikiWSpizarni);
        mEdytujSpizarnieRecyclerView.setLayoutManager(new LinearLayoutManager(edytujSpizarnie.this));
        mAdapter = new edytujSpizarnieAdapter(new ArrayList<Spizarnia2>());
        mAdapter2 = new edytujSpizarnieAdapter2(new ArrayList<SkladnikiWSpizarni>());
        mEdytujSpizarnieRecyclerView.setAdapter(mAdapter);
        new getEdytujSpizarnieTask2().execute();
        new getEdytujSpizarnieTask().execute();
    }
    public void onClickDodajSkladnikDoSpizarnie(View view){
        Intent intent =new Intent(edytujSpizarnie.this,edytujSpizarnieDodajSkladnik.class);
        startActivityForResult(intent,ADD_REQUEST);
    }
    public void onResume(){
        super.onResume();
        new getEdytujSpizarnieTask().execute();
        new getEdytujSpizarnieTask2().execute();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK) mAdapter.notifyDataSetChanged();
        if (requestCode==RESULT_OK) mAdapter2.notifyDataSetChanged();
    }
    class getEdytujSpizarnieTask2 extends  AsyncTask<Void,Void,ArrayList<SkladnikiWSpizarni>>
    {

        @Override
        protected ArrayList<SkladnikiWSpizarni> doInBackground(Void... params) {
            Repozytorium rp = Repozytorium.getSingelton();
            return rp.getSkladnikiWSpiz();
        }
        protected void onPostExecute(ArrayList<SkladnikiWSpizarni> skladnikiWSpizarnis)
        {
            mAdapter2.mSkladniki=skladnikiWSpizarnis;
            mAdapter2.notifyDataSetChanged();
        }
    }
    class getEdytujSpizarnieTask extends AsyncTask<Void,Void,ArrayList<Spizarnia2>>
    {

        @Override
        protected ArrayList<Spizarnia2> doInBackground(Void... params) {
            Repozytorium rp = Repozytorium.getSingelton();
            return  rp.getSpizarnias();
        }
        protected void onPostExecute(ArrayList<Spizarnia2> spizarnia2s){
            mAdapter.mKat_spizarnias=spizarnia2s;
            mAdapter.notifyDataSetChanged();
        }
    }

    class edytujSpizarnieHolder extends RecyclerView.ViewHolder {
        private TextView tvnazwa, tvilosc, tvkategoria;
        private Spizarnia2 mSpiz2;
        private Button btnUsun,btnEdytuj;

        public edytujSpizarnieHolder(View itemView) {
            super(itemView);
            tvilosc = (TextView) itemView.findViewById(R.id.TVIloscZywnosc);
            tvnazwa = (TextView) itemView.findViewById(R.id.TVNazwaZywnosc);
            tvkategoria = (TextView) itemView.findViewById(R.id.TVKategoriaZywnosc);
            btnUsun = (Button)itemView.findViewById(R.id.btnUsunSkladnikZSpizarni);
            btnEdytuj = (Button)itemView.findViewById(R.id.btnEdytujSkladnikZSpizarni);
        }

        public void bindEdytujSpizarnie(Spizarnia2 spizarnia2) {
            mSpiz2 = spizarnia2;
            tvilosc.setText(Double.toString(mSpiz2.getIlosc()));
            tvnazwa.setText(mSpiz2.getNazwa());
            tvkategoria.setText(mSpiz2.getNazwa_kat());
            btnUsun.setOnClickListener(new Button.OnClickListener()
            {
                public void onClick(View v){
                    Intent intent=new Intent(edytujSpizarnie.this,edytujSpizarnieUsunSkladnik.class);
                    intent.putExtra(STUDENT_ID_MESSAGE,edytujSpizarnieHolder.this.mSpiz2.getIdSpiz());
                    startActivityForResult(intent,DELETE_REQUEST);
                }
            });
            btnEdytuj.setOnClickListener(new Button.OnClickListener()
            {
                public void onClick(View v){
                    Intent intent=new Intent(edytujSpizarnie.this,edytujSpizarnieEdytujSkladnik.class);
                    intent.putExtra(STUDENT_ID_MESSAGE,edytujSpizarnieHolder.this.mSpiz2.getIdSpiz());
                    startActivityForResult(intent,EDIT_REQUEST);
                }
            });
        }
    }
    class edytujSpizarnieAdapter2 extends  RecyclerView.Adapter<edytujSpizarnieHolder>{
        List<SkladnikiWSpizarni> mSkladniki;
        public edytujSpizarnieAdapter2(List<SkladnikiWSpizarni> skladnikiWSpizarnis){
            mSkladniki=skladnikiWSpizarnis;
        }
        @Override
        public edytujSpizarnieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(edytujSpizarnieHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return mSkladniki.size();
        }
    }
    class edytujSpizarnieAdapter extends RecyclerView.Adapter<edytujSpizarnieHolder> {
        private List<Spizarnia2> mKat_spizarnias;

        public edytujSpizarnieAdapter(List<Spizarnia2> spizarnia2s) {
            mKat_spizarnias = spizarnia2s;
        }

        @Override
        public edytujSpizarnieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(edytujSpizarnie.this);
            View view = layoutInflater
                    .inflate(R.layout.edytujspizarnie_wiersz, parent, false);
            return new edytujSpizarnieHolder(view);
        }

        @Override
        public void onBindViewHolder(edytujSpizarnieHolder holder, int position) {
            Spizarnia2 spizarnia2 = mKat_spizarnias.get(position);
            holder.bindEdytujSpizarnie(spizarnia2);
        }

        @Override
        public int getItemCount() {
            return mKat_spizarnias.size();
        }
    }
}