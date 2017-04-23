package com.kurkiewiczfuturetechnology.spizarnia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    protected void onClickZaloguj(View view){
        Intent intent = new Intent(MainActivity.this,menuGlowne.class);
        startActivityForResult(intent,0);
    }
}
