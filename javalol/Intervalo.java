/**
 * @author Mº Magdalena Freixa, Estefania Garcia, Daniel Pastor
 */

package projecte.nucli;


import java.io.Serializable;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import org.apache.log4j.Logger;

/** 
 * la clase intervalo es la clase que observa el clock segun el patron de diseño Observer
 * durante la conometracion de la tarea en la variable duracion se guarda el intervalo que es la 
 * diferencia entre la fecha inicial de la tarea y la fecha final. 
 */
public class Intervalo implements Observer, Serializable {

    /**
     * @uml.property  name="tareaPadre"
     * @uml.associationEnd  multiplicity="(1 1)" inverse="intervals:es.uab.es2.TimeTracker.nucli.Tasca"
     */	
    private Task tareaPadre = null;  
    
    /**
     * @uml.property  name="logger"
     */    
    private static Logger logger= Logger.getLogger(Intervalo.class);
   
    /** 
	 * identificador
	 * @uml.property name="id"
	 */    
    private int id = 0;
   
    /**
     * nombre del intervalo
	 * @uml.property  name="nombre"
	 */    
    private String nombre = new java.lang.String();
    
	/** 
	 * Fecha fin del intervalo
	 * @uml.property name="fechaFin"
	 */	
	private Date fechaFin;
	
	/** 
	 * Fecha inicio intervalo
	 * @uml.property name="fechaInicio"
	 */	
	private Date fechaInicio;
	
	/**
	 * @uml.property  name="tareapadre"
	 * @uml.associationEnd  multiplicity="(1 1)" inverse="intervalo:projecte.nucli.Task"
	 */	
	private Task tareapadre = null;
	
	/** 
	 * variable que guarda la diferencia de la fecha inicial i la fecha final de la tarea
	 * @uml.property name="duracion"
	 */	
	private long duracion = 0;
	
	/** 
	 * descripcion del Intervalo
	 * @uml.property name="descripcion"
	 */	
	private String descripcion = new java.lang.String();
   
    /**
     * Constructor que inicializa los intervalos de cada tarea
     * 
     * @param nombre:	nombre de la tarea
     * @param descripcion:	descripcion de la tarea
     * @param padre:	tarea padre al que pertenece el intervalo
     */    
    public Intervalo(String nombre, String descripcion, Task padre){
    	this.tareaPadre = padre;
    	this.nombre = nombre;
    	this.descripcion = descripcion;
    	this.fechaInicio = new Date();
    	tareaPadre.getIntervalos().add(this);
    }
    
	/** 
	 * metodo que informa a los observadores
	 * @param observable: La clase observador.
	 * @param fecha: fecha
	 */    
	public void update(Observable observable, Object fecha){
		this.duracion= Clock.getIntervalo_tiempo() ;
		Activity buscaA = this.tareaPadre;
		while(buscaA != null){
			this.setFechaFin(Clock.getFechaActual());
		    buscaA.setFechaFinal(this.fechaFin);
		    buscaA.setDuracion(2000);
		    buscaA = buscaA.projectPadre;
		}
	}
   
    /**
     * Getter of the property <tt>tareaPadre</tt>
     * @return  Returns the tareaPadre.
     * @uml.property  name="tareaPadre"
     */   
    public Task gettareaPadre() {
		return tareaPadre;
	}

    /**
     * Setter of the property <tt>tareaPadre</tt>
     * @param tareaPadre  The tareaPadre to set.
     * @uml.property  name="tareaPadre"
     */    
    public void settareaPadre(Task tareaPadre) {
		this.tareaPadre = tareaPadre;
	}
    
    /** 
	 * Getter of the property <tt>id</tt>
	 * @return  Returns the id.
	 * @uml.property  name="id"
	 */    
    public int getId() {
		return id;
	}

    /** 
	 * Setter of the property <tt>id</tt>
	 * @param id  The id to set.
	 * @uml.property  name="id"
	 */   
    public void setId(int id) {
		this.id = id;
	}

    /**
     * Getter of the property <tt>nombre</tt>
     * @return  Returns the nombre.
     * @uml.property  name="nombre"
     */   
    public String getNombre() {
		return nombre;
	}

    /**
     * Setter of the property <tt>nombre</tt>
     * @param nombre  The nombre to set.
     * @uml.property  name="nombre"
     */   
    public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/** 
	 * Getter of the property <tt>fechaFin</tt>
	 * @return  Returns the fechaFin.
	 * @uml.property  name="fechaFin"
	 */	
	public Date getFechaFin() {
		return fechaFin;
	}

	/** 
	 * Setter of the property <tt>fechaFin</tt>
	 * @param fechaFin  The fechaFin to set.
	 * @uml.property  name="fechaFin"
	 */	
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/** 
	 * Getter of the property <tt>fechaInicio</tt>
	 * @return  Returns the fechaInicio.
	 * @uml.property  name="fechaInicio"
	 */	
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/** 
	 * Setter of the property <tt>fechaInicio</tt>
	 * @param fechaInicio  The fechaInicio to set.
	 * @uml.property  name="fechaInicio"
	 */	
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/** 
	 * Getter of the property <tt>descripcion</tt>
	 * @return  Returns the descripcion.
	 * @uml.property  name="descripcion"
	 */	
	public String getDescripcion() {
		return descripcion;
	}

	/** 
	 * Setter of the property <tt>descripcion</tt>
	 * @param descripcion  The descripcion to set.
	 * @uml.property  name="descripcion"
	 */	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/** 
	 * Getter of the property <tt>duracion</tt>
	 * @return  Returns the duracion.
	 * @uml.property  name="duracion"
	 */	
	public long getDuracion() {
		return duracion;
	}

	/** 
	 * Setter of the property <tt>duracion</tt>
	 * @param duracion  The duracion to set.
	 * @uml.property  name="duracion"
	 */	
	public void setDuracion(long duracion) {
		this.duracion = duracion;
	}
	
	/**
	 * Getter of the property <tt>tareapadre</tt>
	 * @return  Returns the tareapadre.
	 * @uml.property  name="tareapadre"
	 */	
	public Task getTareapadre() {
		return tareapadre;
	}

	/**
	 * Setter of the property <tt>tareapadre</tt>
	 * @param tareapadre  The tareapadre to set.
	 * @uml.property  name="tareapadre"
	 */	
	public void setTareapadre(Task tareapadre) {
		this.tareapadre = tareapadre;
	}
} 
