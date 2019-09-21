package apiActivosFijo.example.model;

import javax.persistence.*;

@Entity
@Table(name = "asociacionAreas")
@NamedQueries({
        @NamedQuery(name = AsociacionAreas.FIND_ALL, query = "SELECT a FROM AsociacionAreas a")
})
public class AsociacionAreas {
    public static final String FIND_ALL = "AsociacionAreas.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Areas areas;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Activos activos;

    private Long fechaAsignacion;
    private Long fechaRetiro;
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Areas getAreas() {
        return areas;
    }

    public void setAreas(Areas areas) {
        this.areas = areas;
    }

    public Activos getActivos() {
        return activos;
    }

    public void setActivos(Activos activos) {
        this.activos = activos;
    }

    public Long getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Long fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Long getFechaRetiro() {
        return fechaRetiro;
    }

    public void setFechaRetiro(Long fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
