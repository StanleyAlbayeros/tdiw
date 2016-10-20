/**
 * @author Mº Magdalena Freixa, Estefania Garcia, Daniel Pastor
 */

package projecte.nucli;

import java.util.ArrayList;



/**
 * Clase EncadenaTask que hereda de ComplementaryTask.
 * Una tarea es iniciada cuando la anterior para.
 * Esta clase pertenece al patron Decorator.
 */
public class EncadenaTask extends ComplementaryTask {
	
	/**
	 * Constructor de la EncadenaTask que se hereda de ComplementaryTask
	 * @param nombre: Nombre de la tarea 
	 * @param descripcion: breve descripcion de la tarea.
	 * @param padre: proyecto padre de la tarea.
	 * @param raiz:  Array de la raiz
	 */
	public EncadenaTask(String nombre, String descripcion, Project padre,
			ArrayList<Activity> raiz) {
		super(nombre, descripcion, padre, raiz);
	}

}
