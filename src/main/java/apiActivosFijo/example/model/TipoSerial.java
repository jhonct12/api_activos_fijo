package apiActivosFijo.example.model;

import javax.persistence.*;

@Entity
@Table(name = "tipoSerial")
@NamedQueries({
        @NamedQuery(name = TipoSerial.FIND_ALL, query = "SELECT a FROM TipoSerial a")
})
public class TipoSerial {
    public static final String FIND_ALL = "TipoSerial.findAll";

    @Id
    private String codigo;
    private String tipoSerial;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipoSerial() {
        return tipoSerial;
    }

    public void setTipoSerial(String tipoSerial) {
        this.tipoSerial = tipoSerial;
    }
}
