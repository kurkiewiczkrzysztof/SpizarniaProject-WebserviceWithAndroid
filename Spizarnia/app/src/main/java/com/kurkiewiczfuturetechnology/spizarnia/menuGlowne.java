package com.kurkiewiczfuturetechnology.spizarnia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class menuGlowne extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_glowne);
    }
    protected void onClickEdytujSpizarnie(View view){
        Intent intent = new Intent(menuGlowne.this,edytujSpizarnie.class);
        startActivityForResult(intent,0);
    }
    protected void onClickZnajdzPrzepisy(View view){
        Intent intent = new Intent(menuGlowne.this,edytujSpizarnie.class);
        startActivityForResult(intent,0);
    }
    protected void onClickFaq(View view){
        Intent intent = new Intent(menuGlowne.this,Test.class);
        startActivityForResult(intent,0);
    }

}
