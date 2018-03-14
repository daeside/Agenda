package DataBase;

public class Validar
{
    public boolean Crear(String nom, String ape, String corr, String dir, String tel)
    {
        if(nom.isEmpty() || ape.isEmpty() || corr.isEmpty() || dir.isEmpty() || tel.isEmpty())
        {
            return  false;
        }
        else
        {
            return true;
        }
    }

    public boolean BusqElim(String dato)
    {
        if(dato.isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
