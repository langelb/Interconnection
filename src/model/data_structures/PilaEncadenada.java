package model.data_structures;

public class PilaEncadenada<T extends Comparable <T>> extends ListaEncadenada<T> 
{
	public void push(T element)
	{
		try {
			this.addLast(element);
		} catch (NullException e) {
			e.printStackTrace();
		}
	}
	
	public T pop()
	{
		try 
		{
			return this.removeLast();
		} catch (VacioException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public T top()
	{
		try {
			return this.lastElement();
		} catch (VacioException e) {
			e.printStackTrace();
			return null; // Retornar un valor por defecto si ocurre la excepción
		}
	}
}
