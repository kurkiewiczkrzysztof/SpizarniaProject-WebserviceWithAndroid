package com.kurkiewiczfuturetechnology.spizarnia;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class edytujSpizarnieEdytujSkladnik extends AppCompatActivity {
    int mIdSpiz;
    SkladnikiWSpizarni skladnikiWSpizarni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edytuj_spizarnie_edytuj_skladnik);
        Repozytorium rp = Repozytorium.getSingelton();
        Intent intent = getIntent();
        mIdSpiz=intent.getIntExtra(edytujSpizarnie.STUDENT_ID_MESSAGE,0);

        skladnikiWSpizarni=rp.skladnikiWSpizarni(mIdSpiz);
        ((EditText)findViewById(R.id.etIloscEdytuj)).setText(String.valueOf(skladnikiWSpizarni.getIlosc()));
    }
    protected void btnZmienClick(View v) {
        skladnikiWSpizarni.setIlosc(Double.parseDouble(((EditText)findViewById(R.id.etIloscEdytuj)).getText().toString()));
        new EditStudnetTask().execute(skladnikiWSpizarni);
    }

    protected void btnAnulujClick(View v) {
        setResult(edytujSpizarnie.RESULT_CANCEL);
        finish();
    }

    class EditStudnetTask extends AsyncTask<SkladnikiWSpizarni, Void, Void>
    {
        protected Void doInBackground(SkladnikiWSpizarni... skladnikiWSpizarnis) {
            Repozytorium rp = Repozytorium.getSingelton();
            rp.editSkladnikiWSpizarni(skladnikiWSpizarnis[0]);
            return null;
        }

        protected void onPostExecute(Void v ) {
            setResult(edytujSpizarnie.RESULT_OK);
            finish();
        }
    }
}

