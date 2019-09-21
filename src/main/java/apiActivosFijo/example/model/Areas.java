package apiActivosFijo.example.model;

import javax.persistence.*;

@Entity
@Table(name = "areas")
@NamedQueries({
        @NamedQuery(name = Areas.FIND_ALL, query = "SELECT a FROM Areas a")
})
public class Areas {

    public static final String FIND_ALL = "Areas.findAll";

    @Id
    private String codigo;

    private String nombre;
    private String ciudad;
    private String descripcion;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigoArea) {
        this.codigo = codigoArea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombreArea) {
        this.nombre = nombreArea;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
