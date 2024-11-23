package model.data_structures;

import java.util.Iterator;

public interface ILista<T> extends Comparable<ILista <T>>, Iterable<T> {

    /**
     * Agregar un elemento al inicio de la lista.
     */
    void addFirst(T element);

    /**
     * Agregar un elemento al final de la lista.
     */
    void addLast(T element) throws NullException;

    /**
     * Insertar un elemento en una posición específica.
     */
    void insertElement(T elemento, int pos) throws PosException, NullException;

    /**
     * Eliminar el primer elemento de la lista.
     */
    T removeFirst() throws VacioException;

    /**
     * Eliminar el último elemento de la lista.
     */
    T removeLast() throws VacioException;

    /**
     * Eliminar un elemento en una posición específica.
     */
    T deleteElement(int pos) throws PosException, VacioException;

    /**
     * Obtener el primer elemento de la lista.
     */
    T firstElement() throws VacioException;

    /**
     * Obtener el último elemento de la lista.
     */
    T lastElement() throws VacioException;

    /**
     * Obtener un elemento en una posición específica.
     */
    T getElement(int pos) throws PosException, VacioException;

    /**
     * Obtener el tamaño de la lista.
     */
    int size();

    /**
     * Verificar si la lista está vacía.
     */
    boolean isEmpty();

    /**
     * Verificar si un elemento está presente en la lista y devolver su posición.
     */
    int isPresent(T element) throws VacioException, NullException, PosException;

    /**
     * Intercambiar dos elementos en posiciones específicas.
     */
    void exchange(int pos1, int pos2) throws PosException, VacioException, NullException;

    /**
     * Cambiar la información de un elemento en una posición específica.
     */
    void changeInfo(int pos, T element) throws PosException, VacioException, NullException;

    /**
     * Crear una sublista de la lista original.
     * 
     * @param pos          Posición inicial de la sublista.
     * @param numElementos Número de elementos que contendrá la sublista.
     * @return Sublista creada con la misma representación de la lista original.
     */
    ILista<T> sublista(int pos, int numElementos) throws PosException, VacioException, NullException;

    /**
     * Retorna un iterador para recorrer la lista.
     * Este método permite usar bucles for-each.
     */
    @Override
    Iterator<T> iterator();
}
