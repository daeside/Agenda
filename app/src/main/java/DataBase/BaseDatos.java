package DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatos extends SQLiteOpenHelper
{
    public  String contactos = "CREATE TABLE  contactos(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellido TEXT, correo TEXT, direccion TEXT, telefono TEXT);" ;

    public BaseDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(contactos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
