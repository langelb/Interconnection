package utils;

import model.data_structures.*;
import java.util.Comparator;

public final class Ordenamiento<T extends Comparable<T>> {

    public void ordenarMergeSort(ILista<T> lista, Comparator<T> criterio, boolean ascendente) throws PosException, VacioException, NullException {
        MergeSort<T> sorter = new MergeSort<>();
        sorter.sort(lista, criterio, ascendente);
    }
}
