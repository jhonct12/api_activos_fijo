package apiActivosFijo.example.model;

import javax.persistence.*;

@Entity
@Table(name = "tipo")
@NamedQueries({
        @NamedQuery(name = Tipo.FIND_ALL, query = "SELECT a FROM Tipo a")
})
public class Tipo {
    public static final String FIND_ALL = "Tipo.findAll";

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
