package model.data_structures;

import java.util.Iterator;

public class ListaEncadenada<T extends Comparable<T>> implements ILista<T> {

    private Nodo<T> first; // Nodo inicial de la lista
    private Nodo<T> last;  // Último nodo de la lista
    private int size;      // Tamaño de la lista

    public ListaEncadenada() {
        first = null;
        last = null;
        size = 0;
    }

    public ListaEncadenada(T element) {
        first = new Nodo<>(element);
        last = first;
        size = 1;
    }

    @Override
    public void addFirst(T element) {
        Nodo<T> newNode = new Nodo<>(element);
        newNode.setNext(first);
        first = newNode;
        if (last == null) {
            last = first;
        }
        size++;
    }

    @Override
    public void addLast(T element) throws NullException{
        Nodo<T> newNode = new Nodo<>(element);
        if (last != null) {
            last.setNext(newNode);
        }
        last = newNode;
        if (first == null) {
            first = last;
        }
        size++;
    }

    @Override
    public void insertElement(T element, int pos) throws PosException, NullException {
        if (pos < 1 || pos > size + 1) {
            throw new PosException("La posición no es válida");
        }
        if (element == null) {
            throw new NullException("El elemento no puede ser nulo");
        }
        if (pos == 1) {
            addFirst(element);
        } else if (pos == size + 1) {
            addLast(element);
        } else {
            Nodo<T> current = first;
            for (int i = 1; i < pos - 1; i++) {
                current = current.getNext();
            }
            Nodo<T> newNode = new Nodo<>(element);
            newNode.setNext(current.getNext());
            current.setNext(newNode);
            size++;
        }
    }

    @Override
    public T removeFirst() throws VacioException {
        if (isEmpty()) {
            throw new VacioException("La lista está vacía");
        }
        T element = first.getInfo();
        first = first.getNext();
        if (first == null) {
            last = null;
        }
        size--;
        return element;
    }

    @Override
    public T removeLast() throws VacioException {
        if (isEmpty()) {
            throw new VacioException("La lista está vacía");
        }
        if (size == 1) {
            T element = first.getInfo();
            first = null;
            last = null;
            size = 0;
            return element;
        }
        Nodo<T> current = first;
        while (current.getNext() != last) {
            current = current.getNext();
        }
        T element = last.getInfo();
        last = current;
        last.setNext(null);
        size--;
        return element;
    }

    @Override
    public T deleteElement(int pos) throws PosException, VacioException {
        if (isEmpty()) {
            throw new VacioException("La lista está vacía");
        }
        if (pos < 1 || pos > size) {
            throw new PosException("La posición no es válida");
        }
        if (pos == 1) {
            return removeFirst();
        }
        if (pos == size) {
            return removeLast();
        }
        Nodo<T> current = first;
        for (int i = 1; i < pos - 1; i++) {
            current = current.getNext();
        }
        T element = current.getNext().getInfo();
        current.setNext(current.getNext().getNext());
        size--;
        return element;
    }

    @Override
    public T firstElement() throws VacioException {
        if (isEmpty()) {
            throw new VacioException("La lista está vacía");
        }
        return first.getInfo();
    }

    @Override
    public T lastElement() throws VacioException {
        if (isEmpty()) {
            throw new VacioException("La lista está vacía");
        }
        return last.getInfo();
    }

    @Override
    public T getElement(int pos) throws PosException, VacioException {
        if (isEmpty()) {
            throw new VacioException("La lista está vacía");
        }
        if (pos < 1 || pos > size) {
            throw new PosException("La posición no es válida");
        }
        Nodo<T> current = first;
        for (int i = 1; i < pos; i++) {
            current = current.getNext();
        }
        return current.getInfo();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int isPresent(T element) throws VacioException, NullException {
        if (element == null) {
            throw new NullException("El elemento no puede ser nulo");
        }
        if (isEmpty()) {
            throw new VacioException("La lista está vacía");
        }
        Nodo<T> current = first;
        for (int i = 1; current != null; i++) {
            if (current.getInfo().equals(element)) {
                return i;
            }
            current = current.getNext();
        }
        return -1; // No encontrado
    }

    @Override
    public void exchange(int pos1, int pos2) throws PosException, VacioException, NullException {
        if (pos1 < 1 || pos1 > size || pos2 < 1 || pos2 > size) {
            throw new PosException("Las posiciones no son válidas");
        }
        if (isEmpty()) {
            throw new VacioException("La lista está vacía");
        }
        T elem1 = getElement(pos1);
        T elem2 = getElement(pos2);
        changeInfo(pos1, elem2);
        changeInfo(pos2, elem1);
    }

    @Override
    public void changeInfo(int pos, T element) throws PosException, VacioException, NullException {
        if (element == null) {
            throw new NullException("El elemento no puede ser nulo");
        }
        if (pos < 1 || pos > size) {
            throw new PosException("La posición no es válida");
        }
        if (isEmpty()) {
            throw new VacioException("La lista está vacía");
        }
        Nodo<T> current = first;
        for (int i = 1; i < pos; i++) {
            current = current.getNext();
        }
        current.change(element);
    }

    @Override
    public ILista<T> sublista(int pos, int numElementos) throws PosException, VacioException, NullException {
        if (isEmpty()) {
            throw new VacioException("La lista está vacía");
        }
        if (pos < 1 || pos > size) {
            throw new PosException("La posición no es válida");
        }
        ILista<T> sublista = new ListaEncadenada<>();
        Nodo<T> current = first;
        for (int i = 1; i < pos; i++) {
            current = current.getNext();
        }
        for (int i = 0; i < numElementos && current != null; i++) {
            sublista.addLast(current.getInfo());
            current = current.getNext();
        }
        return sublista;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Nodo<T> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T data = current.getInfo();
                current = current.getNext();
                return data;
            }
        };
    }

    @Override
    public int compareTo(ILista<T> otraLista) {
        return Integer.compare(this.size(), otraLista.size());
    }
}
