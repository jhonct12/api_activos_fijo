package apiActivosFijo.example.model;

import javax.persistence.*;


@Entity
@Table(name = "estadoActual")
@NamedQueries({
        @NamedQuery(name = EstadoActual.FIND_ALL, query = "SELECT a FROM EstadoActual a")
})
public class EstadoActual {
    public static final String FIND_ALL = "EstadoActual.findAll";

    @Id
    private String codigo;
    private String descripcion;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
