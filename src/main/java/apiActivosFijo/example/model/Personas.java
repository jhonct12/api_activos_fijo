package apiActivosFijo.example.model;

import javax.persistence.*;

/**
 * The type Personas.
 */
@Entity
@Table(name = "personas")
@NamedQueries({
        @NamedQuery(name = Personas.FIND_ALL, query = "SELECT a FROM Personas a")
})
public class Personas {
    /**
     * The constant FIND_ALL.
     */
    public static final String FIND_ALL = "Personas.findAll";

    @Id
    private String documento;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String correo;
    private String direccion;

    /**
     * Gets documento.
     *
     * @return the documento
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * Sets documento.
     *
     * @param documento the documento
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * Gets nombres.
     *
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Sets nombres.
     *
     * @param nombres the nombres
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Gets apellidos.
     *
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Sets apellidos.
     *
     * @param apellidos the apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Gets telefono.
     *
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Sets telefono.
     *
     * @param telefono the telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Gets correo.
     *
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Sets correo.
     *
     * @param correo the correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Gets direccion.
     *
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Sets direccion.
     *
     * @param direccion the direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
