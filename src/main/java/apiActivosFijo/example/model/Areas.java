package apiActivosFijo.example.model;

import javax.persistence.*;

/**
 * The type Areas.
 */
@Entity
@Table(name = "areas")
@NamedQueries({
        @NamedQuery(name = Areas.FIND_ALL, query = "SELECT a FROM Areas a")
})
public class Areas {

    /**
     * The constant FIND_ALL.
     */
    public static final String FIND_ALL = "Areas.findAll";

    @Id
    private String codigo;

    private String nombre;
    private String ciudad;
    private String descripcion;

    /**
     * Gets codigo.
     *
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Sets codigo.
     *
     * @param codigoArea the codigo area
     */
    public void setCodigo(String codigoArea) {
        this.codigo = codigoArea;
    }

    /**
     * Gets nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets nombre.
     *
     * @param nombreArea the nombre area
     */
    public void setNombre(String nombreArea) {
        this.nombre = nombreArea;
    }

    /**
     * Gets ciudad.
     *
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Sets ciudad.
     *
     * @param ciudad the ciudad
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Gets descripcion.
     *
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets descripcion.
     *
     * @param descripcion the descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
