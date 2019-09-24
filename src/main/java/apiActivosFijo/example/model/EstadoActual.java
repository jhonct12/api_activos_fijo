package apiActivosFijo.example.model;

import javax.persistence.*;


/**
 * The type Estado actual.
 */
@Entity
@Table(name = "estadoActual")
@NamedQueries({
        @NamedQuery(name = EstadoActual.FIND_ALL, query = "SELECT a FROM EstadoActual a")
})
public class EstadoActual {
    /**
     * The constant FIND_ALL.
     */
    public static final String FIND_ALL = "EstadoActual.findAll";

    @Id
    private String codigo;
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
     * @param codigo the codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
