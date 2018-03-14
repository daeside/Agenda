package com.example.luis.agenda;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import DataBase.BaseDatos;
import DataBase.Contactos;
import DataBase.Validar;

public class Buscar extends AppCompatActivity
{
    private ArrayList<Contactos> contactos = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busquedas);
    }

    public void Buscar(View view)
    {
        EditText caja = (EditText) findViewById(R.id.caja_buscar);
        String busq = caja.getText().toString();
        Validar campos = new Validar();

        if(campos.BusqElim(busq))
        {
            try
            {
                LlenarLista(busq);
            }
            catch (Exception e)
            {
                Toast.makeText(Buscar.this, "No se encontraron datos", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(Buscar.this, "no se permiten campos vacios", Toast.LENGTH_SHORT).show();
        }
    }

    public void LlenarLista(String buscar)
    {
        TextView id = (TextView) findViewById(R.id.id_show);
        TextView nom = (TextView) findViewById(R.id.nombre_show);
        TextView ape = (TextView) findViewById(R.id.apellido_show);
        TextView cor = (TextView) findViewById(R.id.correo_show);
        TextView dir = (TextView) findViewById(R.id.direccion_show);
        TextView tel = (TextView) findViewById(R.id.telefono_show);
        BaseDatos bh = new BaseDatos(Buscar.this, "contactos", null, 1);

        if (bh != null)
        {
            SQLiteDatabase db = bh.getReadableDatabase();
            Cursor curso = db.rawQuery("SELECT * FROM contactos WHERE correo = '" + buscar + "'", null);

            if(curso.moveToFirst())
            {
                do
                {
                    contactos.add(new Contactos(curso.getInt(0), curso.getString(1), curso.getString(2), curso.getString(3), curso.getString(4), curso.getString(5)));
                }
                while (curso.moveToNext());
            }
        }

        String[] arreglo = new String[contactos.size()];
        String[] arreglo1 = new String[contactos.size()];
        String[] arreglo2 = new String[contactos.size()];
        String[] arreglo3 = new String[contactos.size()];
        String[] arreglo4 = new String[contactos.size()];
        String[] arreglo5 = new String[contactos.size()];

        for(int i = 0; i < arreglo.length; i++)
        {
            arreglo[i] = "ID: " + contactos.get(i).getId();
            arreglo1[i] = "Nombre: " + contactos.get(i).getNombre();
            arreglo2[i] = "Apellido: " + contactos.get(i).getApellido();
            arreglo3[i] = "Correo: " + contactos.get(i).getCorreo();
            arreglo4[i] = "Direccion: " + contactos.get(i).getDireccion();
            arreglo5[i] = "Telefono: " + contactos.get(i).getTelefono();
        }

        id.setText(arreglo[0]);
        nom.setText(arreglo1[0]);
        ape.setText(arreglo2[0]);
        cor.setText(arreglo3[0]);
        dir.setText(arreglo4[0]);
        tel.setText(arreglo5[0]);
    }

    public void Limpiar(View view)
    {
        Intent intent = new Intent(Buscar.this, Buscar.class);
        intent.putExtra("ValorExtra", true);
        startActivity(intent);
        finish();
    }

    public void Cambios(View view)
    {
        EditText caja = (EditText) findViewById(R.id.caja_buscar);
        String busq = caja.getText().toString();
        Validar campos = new Validar();
        TextView id = (TextView) findViewById(R.id.id_show);
        TextView nom = (TextView) findViewById(R.id.nombre_show);
        TextView ape = (TextView) findViewById(R.id.apellido_show);
        TextView cor = (TextView) findViewById(R.id.correo_show);
        TextView dir = (TextView) findViewById(R.id.direccion_show);
        TextView tel = (TextView) findViewById(R.id.telefono_show);

        if(campos.BusqElim(busq))
        {
            Intent inte = new Intent(Buscar.this, Modificar.class);
            inte.putExtra("id", id.getText().toString());
            inte.putExtra("nombre", nom.getText().toString());
            inte.putExtra("apellido", ape.getText().toString());
            inte.putExtra("correo", cor.getText().toString());
            inte.putExtra("direccion", dir.getText().toString());
            inte.putExtra("telefono", tel.getText().toString());
            startActivity(inte);
        }
        else
        {
            Toast.makeText(Buscar.this, "El campo busqueda esta vacio", Toast.LENGTH_SHORT).show();
        }
    }

    public void Bajas(View view)
    {
        EditText caja = (EditText) findViewById(R.id.caja_buscar);
        String busq = caja.getText().toString();
        Validar campos = new Validar();
        TextView ide = (TextView) findViewById(R.id.id_show);
        String dat = ide.getText().toString();

        if(campos.BusqElim(busq))
        {
            try
            {
                int id = Integer.parseInt(dat.substring(4));
                EliminarUser(id);
                Toast.makeText(Buscar.this, "Eliminado exitosamente", Toast.LENGTH_SHORT).show();
            }
            catch (Exception e)
            {
                Toast.makeText(Buscar.this, "No se encontro dato", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(Buscar.this, "El campo busqueda esta vacio", Toast.LENGTH_SHORT).show();
        }
    }

    public void EliminarUser(int id)
    {
        BaseDatos bh = new BaseDatos(Buscar.this, "contactos", null, 1);

        if(bh != null)
        {
            SQLiteDatabase db = bh.getReadableDatabase();
            long response = db.delete("contactos", "id=" + id , null);

            if(response > 0)
            {
                Toast.makeText(Buscar.this, "Eliminado con exito", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(Buscar.this, "Fallo", Toast.LENGTH_SHORT).show();
        }
    }
}
