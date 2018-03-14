package com.example.luis.agenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
    public void Altas(View view)
    {
        Intent inte = new Intent(MainActivity.this, Crear.class);
        startActivity(inte);
    }

    public void Busquedas(View view)
    {
        Intent inte = new Intent(MainActivity.this, Buscar.class);
        startActivity(inte);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
