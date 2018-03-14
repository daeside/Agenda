package com.example.luis.agenda;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import DataBase.BaseDatos;
import DataBase.Validar;

public class Crear extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.altas);
    }

    public void Agregar(View view)
    {
        Validar campos = new Validar();
        EditText nombre = (EditText) findViewById(R.id.caja_nombre);
        EditText apellido = (EditText) findViewById(R.id.caja_apellido);
        EditText correo = (EditText) findViewById(R.id.caja_correo);
        EditText direccion = (EditText) findViewById(R.id.caja_fisica);
        EditText telefono = (EditText) findViewById(R.id.caja_telefono);
        String nom = nombre.getText().toString();
        String ape = apellido.getText().toString();
        String cor = correo.getText().toString();
        String dir = direccion.getText().toString();
        String tel = telefono.getText().toString();

        if(campos.Crear(nom, ape, cor, dir, tel))
        {
            BaseDatos bh = new BaseDatos(Crear.this, "contactos", null, 1);

            if (bh != null)
            {
                SQLiteDatabase db = bh.getWritableDatabase();
                ContentValues con = new ContentValues();
                con.put("nombre", nom);
                con.put("apellido", ape);
                con.put("correo", cor);
                con.put("direccion", dir);
                con.put("telefono", tel);
                long insertado = db.insert("contactos", null, con);

                if(insertado > 0)
                {
                    Toast.makeText(Crear.this, "Insertado con exito", Toast.LENGTH_SHORT).show();
                    nombre.setText("");
                    apellido.setText("");
                    correo.setText("");
                    direccion.setText("");
                    telefono.setText("");
                }
                else
                {
                    Toast.makeText(Crear.this, "No se insertaron datos", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else
        {
            Toast.makeText(Crear.this, "no se permiten campos vacios", Toast.LENGTH_SHORT).show();
        }
    }
}
