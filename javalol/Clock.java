/**
 * @author Mº Magdalena Freixa, Estefania Garcia, Daniel Pastor
 */

package projecte.nucli;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

import org.apache.log4j.Logger;

/** 
 *Esta clase es la responsable del funcionamiento del reloj. se ha realizado segun el 
 *patron de diseño Observer, por eso hereda de la clase observable.
 */
public class Clock extends Observable implements Serializable {	
	
	/** 
	 * Controla si el reloj esta activo o no
	 * @uml.property name="inicio"
	 */	
	private boolean inicio = false;
	
    /** 
	 * Objeto de tipo Timer, nos facilita la fecha y hora actual
	 * @uml.property name="timer"
	 */	
    private Timer timer;
    
    /**
     * Variables que indican la hora actual. (AlarmTask)
     */
	private int hora, minutos, segundos;
	
	/**
	 * Variables que indican la hora programada. Cuando la tarea ha de empezar a
	 * cronometrarse. (AlarmTask)
	 */
	private int horaProg, minutosProg, segundosProg;
    
	/** 
	 * Guarda el tiempo de duracion
	 * @uml.property name="duracion"
	 */	
	private long duracion = 0;
	
	/**
	 * Variable que indica en tiempo que ha de durar la tarea (TemporizeTask)
	 */
	private long temporize;
	
	/** 
	 * Indica en que medidida de tiempo este reloj notificara a los Observers (milisegundos)
	 * @uml.property name="INTERVALO"
	 */	
	private static long INTERVALO = 2000;

	/** 
	 * Guarda la fecha actual
	 * @uml.property name="FechaActual"
	 */	
	private static Date FechaActual;

	/** 
	 * variable donde le sumaremos su intervalo con el INTERVALO de 2000 milisegundos. 
	 * @uml.property name="intervalo_tiempo"
	 */	
	private static long intervalo_tiempo;
	
	/**
     * inicializacion para la definicion de los niveles de depuracion.
     * @uml.property  name="logger"
     */   
    private static Logger logger= Logger.getLogger(Clock.class);
	
	/** 
	* Inicializa el reloj
	*/	
	public Clock(){		
		timer = null;
		inicio = false;
		duracion = -1;   
	}
   
	/**
	 * Inicializa el constructor del reloj para la clase TemporizeTask
	 * @param temporize: tiempo que ha de durar la tarea cronomentrando
	 */
	public Clock(long temporize){		
		timer = null;
		inicio = false;
		duracion = -1;
		this.temporize=temporize;
	}
	
	/**
	 * Inicializa el constructor del reloj para la clase AlarmTask
	 * @param horaProg: hora programada
	 * @param minutosProg: minutos programada
	 * @param segundosProg: segundos programada
	 */
	public Clock(int horaProg, int minutosProg, int segundosProg){		
		timer = null;
		inicio = false;
		duracion = -1;
		this.segundosProg =segundosProg;
		this.minutosProg = minutosProg;
		this.horaProg = horaProg;
	}
   
    /** 
	 * Metodo que parar el reloj
		*/    
	public void Parar(){
		inicio = false;
	}
	
	/**
	 * Actualiza la fecha de los intervalos
	 * @uml.property  name="NotificadorO"
	 */   
	private TimerTask NotificadorO = new TimerTask() {
		public void run() {
			if (inicio) {
				intervalo_tiempo = intervalo_tiempo + INTERVALO;
				setFechaActual(new Date());
				setChanged();
				notifyObservers();
			}
		}
	};
	
	/**
	 * Actualiza la fecha de los intervalos y si el valor del temporize es igual al del intervalo
	 * la tarea se para. Esto se utiliza para el caso de las TemporizeTask.
	 */
	private TimerTask NotificadorTemp = new TimerTask() {
		public void run() {
			if (inicio) {
				intervalo_tiempo = intervalo_tiempo + INTERVALO;
				if (temporize == intervalo_tiempo) Parar();
				setFechaActual(new Date());
				setChanged();
				notifyObservers();
			}
		}
	};
	
	/**
	 * Actualiza la fecha de los intervalos y mientras la hora programada no sea igual a la actual
	 * la tarea no se empezara a cronometrar. Esto se utiliza para el caso de la AlamTask.
	 */
	private TimerTask preguntarHora = new TimerTask() {
		public void run() {
			if(inicio){
				while ((hora != horaProg) || (minutos != minutosProg) || (segundos != segundosProg)){
					Calendar calendario = Calendar.getInstance();
					hora = calendario.get(Calendar.HOUR_OF_DAY);	
					minutos = calendario.get(Calendar.MINUTE);	
					segundos = calendario.get(Calendar.SECOND);
				}
				logger.info("ALARMAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
				duracion = 0;
				setFechaActual(new Date());
				setChanged();
				notifyObservers();
			}
		}
	};
	
    /**
     * se encarga de poner en marxa el tiempo del clock, instancia un Timetask. (BasicTask)
     */    
    public void iniciar(){
            inicio = true;
            if(duracion == -1){
                    timer = new Timer();
                    timer.scheduleAtFixedRate(NotificadorO, 0, INTERVALO);
            }
            duracion = 0;
    }
    
    /**
     * se encarga de poner en marxa el tiempo del clock, instancia un Timetask. (TemporizeTask)
     */    
    public void iniciarTemp(){
        inicio = true;
        if(duracion == -1){
                timer = new Timer();
                timer.scheduleAtFixedRate(NotificadorTemp, 0, INTERVALO);
                
        }
        duracion = 0;
}
    
    /**
     * se encarga de poner en marxa el tiempo del clock, instancia un Timetask. (AlarmTask)
     */    
    public void iniciarAlarma(){
        inicio = true;
        if(duracion == -1){
                timer = new Timer();
                timer.scheduleAtFixedRate(preguntarHora, 0, INTERVALO);
                
        }
        duracion = 0;
}
  
	/** 
	 * Getter of the property <tt>on</tt>
	 * @return  Returns the on.
	 * @uml.property  name="inicio"
	 */	
	public boolean isInicio() {
		return inicio;
	}
    
    /** 
	 * Getter of the property <tt>timer</tt>
	 * @return  Returns the timer.
	 * @uml.property  name="timer"
	 */    
    public Timer getTimer() {
		return timer;
	}

    /** 
	 * Setter of the property <tt>timer</tt>
	 * @param timer  The timer to set.
	 * @uml.property  name="timer"
	 */    
    public void setTimer(Timer timer) {
		this.timer = timer;
	}
    
	/** 
	 * Getter of the property <tt>temps</tt>
	 * @return  Returns the temps.
	 * @uml.property  name="duracion"
	 */	
	public long getDuracion() {
		return duracion;
	}

	/** 
	 * Setter of the property <tt>temps</tt>
	 * @param temps  The temps to set.
	 * @uml.property  name="duracion"
	 */	
	public void setDuracion(long duracion) {
		this.duracion = duracion;
	}

    /**
     * @return the data_actual
     */	
    public static Date getFechaActual() {
        return FechaActual;
    }

    /**
     * @param data_actual the data_actual to set
     */  
    public static void setFechaActual(Date FechaActual) {
        Clock.FechaActual = FechaActual;
    }
    
    /**
     * Getter of the property <tt>intervalo</tt>
     * @return  Returns the intervalo.
     * @uml.property  name="intervalo"
     */    
    public static long getIntervalo_tiempo() {
		return intervalo_tiempo;
	}

     /**
     * Setter of the property <tt>intervalo</tt>
     * @param intervalo  The intervalo to set.
     * @uml.property  name="intervalo"
     */    
    public static void setIntervalo_tiempo(long intervalo_tiempo) {
		Clock.intervalo_tiempo = intervalo_tiempo;
	}
    
	/** 
	 * Setter of the property <tt>on</tt>
	 * @param on  The on to set.
	 * @uml.property  name="inicio"
	 */
    public void setInicio(boolean inicio) {
		this.inicio = inicio;
	}
}

