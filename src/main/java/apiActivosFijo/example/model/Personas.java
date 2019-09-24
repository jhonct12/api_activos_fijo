package apiActivosFijo.example.model;

import javax.persistence.*;

@Entity
@Table(name = "personas")
@NamedQueries({
        @NamedQuery(name = Personas.FIND_ALL, query = "SELECT a FROM Personas a")
})
public class Personas {
    public static final String FIND_ALL = "Personas.findAll";

    @Id
    private String documento;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String correo;
    private String direccion;

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
