package apiActivosFijo.example.model;

import javax.persistence.*;

/**
 * The type Tipo.
 */
@Entity
@Table(name = "tipo")
@NamedQueries({
        @NamedQuery(name = Tipo.FIND_ALL, query = "SELECT a FROM Tipo a")
})
public class Tipo {
    /**
     * The constant FIND_ALL.
     */
    public static final String FIND_ALL = "Tipo.findAll";

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
