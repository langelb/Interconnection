package model.data_structures;

import java.util.logging.Logger;

public class ColaEncadenada<T extends Comparable <T>> extends ListaEncadenada<T>
{
	private static final Logger logger = Logger.getLogger(MinPQ.class.getName());

	public void enqueue(T element)
	{
		try {
			this.addLast(element);
		} catch (NullException e) {
			logger.severe("Error: La lista está vacía. Detalles: " + e.getMessage());
		}
	}
	
	public T dequeue() throws VacioException
	{
		T retorno=null;
		try 
		{
			retorno= this.deleteElement(1);
		} catch (PosException | VacioException e) {
			logger.severe("Error: La lista está vacía. Detalles: " + e.getMessage());
		}
		
		return retorno;
	}
	
	public T peek()
	{
		T retorno=null;
		try {
			retorno = this.getElement(1);
		} catch (PosException | VacioException e) {
			logger.severe("Error: La lista está vacía. Detalles: " + e.getMessage());
		}
		
		return retorno;
	}
}
