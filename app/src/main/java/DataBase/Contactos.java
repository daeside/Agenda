package DataBase;

public class Contactos
{
    public int id;
    public String nombre;
    public String apellido;
    public String correo;
    public String direccion;
    public String telefono;

    public Contactos(int id, String nombre, String apellido, String correo, String direccion, String telefono)
    {
        setId(id);
        setNombre(nombre);
        setApellido(apellido);
        setCorreo(correo);
        setDireccion(direccion);
        setTelefono(telefono);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}

