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
    private String codigoArea;

    private String nombreArea;
    private String ciudad;
    private String descripcion;
    private String tipoArea;

    public String getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(String codigoArea) {
        this.codigoArea = codigoArea;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
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

    public String getTipoArea() {
        return tipoArea;
    }

    public void setTipoArea(String tipoArea) {
        this.tipoArea = tipoArea;
    }
}
