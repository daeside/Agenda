package com.example.luis.agenda;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import DataBase.BaseDatos;
import DataBase.Validar;

public class Modificar extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificaciones);
        EditText nom = (EditText) findViewById(R.id.caja_nombre_mod);
        EditText ape = (EditText) findViewById(R.id.caja_apellido_mod);
        EditText cor = (EditText) findViewById(R.id.caja_correo_mod);
        EditText dir = (EditText) findViewById(R.id.caja_fisica_mod);
        EditText tel = (EditText) findViewById(R.id.caja_telefono_mod);
        String name = getIntent().getExtras().getString("nombre");
        String seg = getIntent().getExtras().getString("apellido");
        String ema = getIntent().getExtras().getString("correo");
        String adre = getIntent().getExtras().getString("direccion");
        String tele = getIntent().getExtras().getString("telefono");
        nom.setText(name.substring(8));
        ape.setText(seg.substring(10));
        cor.setText(ema.substring(8));
        dir.setText(adre.substring(11));
        tel.setText(tele.substring(10));
    }

    public void Agregar(View view)
    {
        Validar campos = new Validar();
        EditText nombre = (EditText) findViewById(R.id.caja_nombre_mod);
        EditText apellido = (EditText) findViewById(R.id.caja_apellido_mod);
        EditText correo = (EditText) findViewById(R.id.caja_correo_mod);
        EditText direccion = (EditText) findViewById(R.id.caja_fisica_mod);
        EditText telefono = (EditText) findViewById(R.id.caja_telefono_mod);
        String nom = nombre.getText().toString();
        String ape = apellido.getText().toString();
        String cor = correo.getText().toString();
        String dir = direccion.getText().toString();
        String tel = telefono.getText().toString();
        String dat = getIntent().getExtras().getString("id");
        int id = Integer.parseInt(dat.substring(4));

        if(campos.Crear(nom, ape, cor, dir, tel))
        {
            BaseDatos bh = new BaseDatos(Modificar.this, "contactos", null, 1);

            if (bh != null)
            {
                SQLiteDatabase db = bh.getWritableDatabase();
                ContentValues con = new ContentValues();
                con.put("nombre", nom);
                con.put("apellido", ape);
                con.put("correo", cor);
                con.put("direccion", dir);
                con.put("telefono", tel);
                long insertado = db.update("contactos", con, "id=" + id , null);

                if(insertado > 0)
                {
                    Toast.makeText(Modificar.this, "Modificado con exito", Toast.LENGTH_SHORT).show();
                    nombre.setText("");
                    apellido.setText("");
                    correo.setText("");
                    direccion.setText("");
                    telefono.setText("");
                }
                else
                {
                    Toast.makeText(Modificar.this, "No se modificaron datos", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else
        {
            Toast.makeText(Modificar.this, "no se permiten campos vacios", Toast.LENGTH_SHORT).show();
        }
    }
}
