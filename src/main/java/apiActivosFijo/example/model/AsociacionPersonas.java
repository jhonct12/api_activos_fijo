package apiActivosFijo.example.model;

import javax.persistence.*;

/**
 * The type Asociacion personas.
 */
@Entity
@Table(name = "asociacionPersonas")
@NamedQueries({
        @NamedQuery(name = AsociacionPersonas.FIND_ALL, query = "SELECT a FROM AsociacionPersonas a")
})
public class AsociacionPersonas {
    /**
     * The constant FIND_ALL.
     */
    public static final String FIND_ALL = "AsociacionPersonas.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Personas personas;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Activos activos;

    private Long fechaAsignacion;
    private Long fechaRetiro;
    private String descripcion;

    /**
     * Gets codigo.
     *
     * @return the codigo
     */
    public Long getCodigo() {
        return codigo;
    }

    /**
     * Sets codigo.
     *
     * @param codigo the codigo
     */
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    /**
     * Gets personas.
     *
     * @return the personas
     */
    public Personas getPersonas() {
        return personas;
    }

    /**
     * Sets personas.
     *
     * @param personas the personas
     */
    public void setPersonas(Personas personas) {
        this.personas = personas;
    }

    /**
     * Gets activos.
     *
     * @return the activos
     */
    public Activos getActivos() {
        return activos;
    }

    /**
     * Sets activos.
     *
     * @param activos the activos
     */
    public void setActivos(Activos activos) {
        this.activos = activos;
    }

    /**
     * Gets fecha asignacion.
     *
     * @return the fecha asignacion
     */
    public Long getFechaAsignacion() {
        return fechaAsignacion;
    }

    /**
     * Sets fecha asignacion.
     *
     * @param fechaAsignacion the fecha asignacion
     */
    public void setFechaAsignacion(Long fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    /**
     * Gets fecha retiro.
     *
     * @return the fecha retiro
     */
    public Long getFechaRetiro() {
        return fechaRetiro;
    }

    /**
     * Sets fecha retiro.
     *
     * @param fechaRetiro the fecha retiro
     */
    public void setFechaRetiro(Long fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
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
