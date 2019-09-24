package apiActivosFijo.example.model;

import javax.persistence.*;


/**
 * The type Activos.
 */
@Entity
@Table(name = "activos")
@NamedQueries({
        @NamedQuery(name = Activos.FIND_ALL, query = "SELECT a FROM Activos a"),
        @NamedQuery(name = Activos.FIND_TIPO, query = "SELECT a FROM Activos a where a.tipo = :tipo"),
        @NamedQuery(name = Activos.FIND_FECHA_COMPRA, query = "SELECT a FROM Activos a where a.fechaCompra = :fechaCompra"),
        @NamedQuery(name = Activos.FIND_SERIAL, query = "SELECT a FROM Activos a where a.serial = :serial")
})
public class Activos {
    /**
     * The constant FIND_ALL.
     */
    public static final String FIND_ALL = "Activos.findAll";
    /**
     * The constant FIND_TIPO.
     */
    public static final String FIND_TIPO = "Activos.findTipo";
    /**
     * The constant FIND_FECHA_COMPRA.
     */
    public static final String FIND_FECHA_COMPRA = "Activos.findFechaCompra";
    /**
     * The constant FIND_SERIAL.
     */
    public static final String FIND_SERIAL = "Activos.findSerial";

    @Id
    private String codigo;
    private String nombre;
    private String descripcion;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tipo tipo;

    private String numeroInternoInventario;
    private Double peso;
    private Double alto;
    private Double largo;
    private String serial;
    private Number valorCompra;
    private Long fechaCompra;
    private Long fechaBaja;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private EstadoActual estadoActual;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Color color;

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
     * Gets nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets nombre.
     *
     * @param nombre the nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    /**
     * Gets tipo.
     *
     * @return the tipo
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * Sets tipo.
     *
     * @param tipo the tipo
     */
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    /**
     * Gets numero interno inventario.
     *
     * @return the numero interno inventario
     */
    public String getNumeroInternoInventario() {
        return numeroInternoInventario;
    }

    /**
     * Sets numero interno inventario.
     *
     * @param numeroInternoInventario the numero interno inventario
     */
    public void setNumeroInternoInventario(String numeroInternoInventario) {
        this.numeroInternoInventario = numeroInternoInventario;
    }

    /**
     * Gets peso.
     *
     * @return the peso
     */
    public Double getPeso() {
        return peso;
    }

    /**
     * Sets peso.
     *
     * @param peso the peso
     */
    public void setPeso(Double peso) {
        this.peso = peso;
    }

    /**
     * Gets alto.
     *
     * @return the alto
     */
    public Double getAlto() {
        return alto;
    }

    /**
     * Sets alto.
     *
     * @param alto the alto
     */
    public void setAlto(Double alto) {
        this.alto = alto;
    }

    /**
     * Gets largo.
     *
     * @return the largo
     */
    public Double getLargo() {
        return largo;
    }

    /**
     * Sets largo.
     *
     * @param largo the largo
     */
    public void setLargo(Double largo) {
        this.largo = largo;
    }

    /**
     * Gets serial.
     *
     * @return the serial
     */
    public String getSerial() {
        return serial;
    }

    /**
     * Sets serial.
     *
     * @param serial the serial
     */
    public void setSerial(String serial) {
        this.serial = serial;
    }

    /**
     * Gets valor compra.
     *
     * @return the valor compra
     */
    public Number getValorCompra() {
        return valorCompra;
    }

    /**
     * Sets valor compra.
     *
     * @param valorCompra the valor compra
     */
    public void setValorCompra(Number valorCompra) {
        this.valorCompra = valorCompra;
    }

    /**
     * Gets fecha compra.
     *
     * @return the fecha compra
     */
    public Long getFechaCompra() {
        return fechaCompra;
    }

    /**
     * Sets fecha compra.
     *
     * @param fechaCompra the fecha compra
     */
    public void setFechaCompra(Long fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    /**
     * Gets fecha baja.
     *
     * @return the fecha baja
     */
    public Long getFechaBaja() {
        return fechaBaja;
    }

    /**
     * Sets fecha baja.
     *
     * @param fechaBaja the fecha baja
     */
    public void setFechaBaja(Long fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    /**
     * Gets estado actual.
     *
     * @return the estado actual
     */
    public EstadoActual getEstadoActual() {
        return estadoActual;
    }

    /**
     * Sets estado actual.
     *
     * @param estadoActual the estado actual
     */
    public void setEstadoActual(EstadoActual estadoActual) {
        this.estadoActual = estadoActual;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
