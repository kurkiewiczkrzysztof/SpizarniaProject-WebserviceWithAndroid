package com.kurkiewiczfuturetechnology.spizarnia;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class edytujSpizarnieUsunSkladnik extends AppCompatActivity {
    int mIdSklSpiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edytuj_spizarnie_usun_skladnik);
        Intent intent = getIntent();
        mIdSklSpiz=intent.getIntExtra(edytujSpizarnie.STUDENT_ID_MESSAGE,0);
        Repozytorium rp = Repozytorium.getSingelton();
        Spizarnia2 spizarnia2 = rp.getSpizarnia2(mIdSklSpiz);
        ((TextView)findViewById(R.id.tvNazwaUsun)).setText(spizarnia2.getNazwa());
        ((TextView)findViewById(R.id.tvIloscUsun)).setText(Double.toString(spizarnia2.getIlosc()));
    }
    protected void btnUsunClick(View v) {
        new DeleteStudnetTask().execute(new Integer(mIdSklSpiz));
    }

    protected void btnAnulujClick(View v) {
        setResult(edytujSpizarnie.RESULT_CANCEL);
        finish();
    }

    class DeleteStudnetTask extends AsyncTask<Integer, Void, Void>
    {
        protected Void doInBackground(Integer... id) {
            Repozytorium rp = Repozytorium.getSingelton();
            rp.deleteSkladnikWSpizarni(id[0].intValue());
            return null;
        }

        protected void onPostExecute(Void v ) {

            setResult(edytujSpizarnie.RESULT_OK);
            finish();
        }
    }
}
