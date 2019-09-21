package apiActivosFijo.example.model;

import javax.persistence.*;


@Entity
@Table(name = "color")
@NamedQueries({
        @NamedQuery(name = Color.FIND_ALL, query = "SELECT a FROM Color a")
})
public class Color {
    public static final String FIND_ALL = "Color.findAll";

    @Id
    private String code;
    private String descripcion;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
