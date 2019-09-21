package apiActivosFijo.example.model;

import javax.persistence.*;


@Entity
@Table(name = "activos")
@NamedQueries({
        @NamedQuery(name = Activos.FIND_ALL, query = "SELECT a FROM Activos a")
})
public class Activos {
    public static final String FIND_ALL = "Activos.findAll";

    @Id
    private String codigo;
    private String nombre;
    private String descripcion;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TipoSerial tipoSerial;

    private String numeroInternoInventario;
    private Double peso;
    private Double alto;
    private Double largo;
    private Number valorCompra;
    private Long fechaCompra;
    private Long fechaBaja;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private EstadoActual estadoActual;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Color color;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoSerial getTipoSerial() {
        return tipoSerial;
    }

    public void setTipoSerial(TipoSerial tipoSerial) {
        this.tipoSerial = tipoSerial;
    }

    public String getNumeroInternoInventario() {
        return numeroInternoInventario;
    }

    public void setNumeroInternoInventario(String numeroInternoInventario) {
        this.numeroInternoInventario = numeroInternoInventario;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAlto() {
        return alto;
    }

    public void setAlto(Double alto) {
        this.alto = alto;
    }

    public Double getLargo() {
        return largo;
    }

    public void setLargo(Double largo) {
        this.largo = largo;
    }

    public Number getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(Number valorCompra) {
        this.valorCompra = valorCompra;
    }

    public Long getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Long fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Long getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Long fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public EstadoActual getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(EstadoActual estadoActual) {
        this.estadoActual = estadoActual;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
