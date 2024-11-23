package model.data_structures;

import java.util.logging.Logger;

public class PilaEncadenada<T extends Comparable <T>> extends ListaEncadenada<T> 
{
	private static final Logger logger = Logger.getLogger(MinPQ.class.getName());
	
	public void push(T element)
	{
		try {
			this.addLast(element);
		} catch (NullException e) {
			logger.severe("Error: La lista está vacía. Detalles: " + e.getMessage());
		}
	}
	
	public T pop()
	{
		try 
		{
			return this.removeLast();
		} catch (VacioException e) {
			logger.severe("Error: La lista está vacía. Detalles: " + e.getMessage());
		}
		return null;
	}
	
	public T top()
	{
		try {
			return this.lastElement();
		} catch (VacioException e) {
			logger.severe("Error: La lista está vacía. Detalles: " + e.getMessage());
			return null; // Retornar un valor por defecto si ocurre la excepción
		}
	}
}
