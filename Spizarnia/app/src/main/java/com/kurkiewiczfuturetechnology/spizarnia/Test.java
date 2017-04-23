package com.kurkiewiczfuturetechnology.spizarnia;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Test extends AppCompatActivity {
    private RecyclerView mEdytujSpizarnieRecyclerView;
    private edytujSpizarnieAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mEdytujSpizarnieRecyclerView = (RecyclerView) findViewById(R.id.rc_test);
        mEdytujSpizarnieRecyclerView.setLayoutManager(new LinearLayoutManager(Test.this));
        mAdapter = new edytujSpizarnieAdapter(new ArrayList<SkladnikiWSpizarni>());
        mEdytujSpizarnieRecyclerView.setAdapter(mAdapter);
        new getEdytujSpizarnieTask().execute();
    }

    public void onResume(){
        super.onResume();
        new getEdytujSpizarnieTask().execute();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK) mAdapter.notifyDataSetChanged();
    }
    class getEdytujSpizarnieTask extends AsyncTask<Void,Void,ArrayList<SkladnikiWSpizarni>>
    {

        @Override
        protected ArrayList<SkladnikiWSpizarni> doInBackground(Void... params) {
            Repozytorium rp = Repozytorium.getSingelton();
            return  rp.getSkladnikiWSpiz();
        }
        protected void onPostExecute(ArrayList<SkladnikiWSpizarni> spizarnia2s){
            mAdapter.mKat_spizarnias=spizarnia2s;
            mAdapter.notifyDataSetChanged();
        }
    }

    class edytujSpizarnieHolder extends RecyclerView.ViewHolder {

        private TextView tx1,tx2,tx3,tx4;
        private SkladnikiWSpizarni mskladnik;

        public edytujSpizarnieHolder(View itemView) {
            super(itemView);
            tx1 = (TextView)itemView.findViewById(R.id.tvtest1);
            tx2 = (TextView)itemView.findViewById(R.id.tvtest2);
            tx3 = (TextView)itemView.findViewById(R.id.tvtest3);
            tx4 = (TextView)itemView.findViewById(R.id.tvtest4);

        }

        public void bindEdytujSpizarnie(SkladnikiWSpizarni skladnikiWSpizarni) {
            mskladnik=skladnikiWSpizarni;
//            tvilosc.setText(Double.toString(mSpiz2.getIlosc()));
//            tvnazwa.setText(mSpiz2.getNazwa());
            tx1.setText(Integer.toString(mskladnik.getIdSkladnikSpiz()));
            tx2.setText(Double.toString(mskladnik.getIlosc()));
            tx3.setText(Integer.toString(mskladnik.getIdSpizarnia()));
            tx4.setText(Integer.toString(mskladnik.getIdZywnosc()));
        }
    }
    class edytujSpizarnieAdapter extends RecyclerView.Adapter<edytujSpizarnieHolder> {
        private List<SkladnikiWSpizarni> mKat_spizarnias;

        public edytujSpizarnieAdapter(List<SkladnikiWSpizarni> spizarnia2s) {
            mKat_spizarnias = spizarnia2s;
        }

        @Override
        public edytujSpizarnieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(Test.this);
            View view = layoutInflater
                    .inflate(R.layout.testwiersz, parent, false);
            return new edytujSpizarnieHolder(view);
        }

        @Override
        public void onBindViewHolder(edytujSpizarnieHolder holder, int position) {
            SkladnikiWSpizarni skladnikiWSpizarni = mKat_spizarnias.get(position);
            holder.bindEdytujSpizarnie(skladnikiWSpizarni);
        }

        @Override
        public int getItemCount() {
            return mKat_spizarnias.size();
        }
    }
}
