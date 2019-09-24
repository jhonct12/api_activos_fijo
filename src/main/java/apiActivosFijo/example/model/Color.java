package apiActivosFijo.example.model;

import javax.persistence.*;


/**
 * The type Color.
 */
@Entity
@Table(name = "color")
@NamedQueries({
        @NamedQuery(name = Color.FIND_ALL, query = "SELECT a FROM Color a")
})
public class Color {
    /**
     * The constant FIND_ALL.
     */
    public static final String FIND_ALL = "Color.findAll";

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
     * @param code the code
     */
    public void setCodigo(String code) {
        this.codigo = code;
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
