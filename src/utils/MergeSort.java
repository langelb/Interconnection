// New class MergeSort for handling sorting logic

package utils;

import model.data_structures.*;
import java.util.Comparator;

public class MergeSort<T extends Comparable<T>> {

    public void sort(ILista<T> lista, Comparator<T> criterio, boolean ascendente) throws PosException, VacioException, NullException {
        int size = lista.size();
        if (size > 1) {
            int mid = size / 2;
            ILista<T> leftList = lista.sublista(1, mid);
            ILista<T> rightList = lista.sublista(mid + 1, size - mid);

            sort(leftList, criterio, ascendente);
            sort(rightList, criterio, ascendente);

            merge(lista, leftList, rightList, criterio, ascendente);
        }
    }

    private void merge(ILista<T> lista, ILista<T> leftList, ILista<T> rightList, Comparator<T> criterio, boolean ascendente) throws PosException, VacioException, NullException {
        int i = 1, j = 1, k = 1;
        while (i <= leftList.size() && j <= rightList.size()) {
            T elemi = leftList.getElement(i);
            T elemj = rightList.getElement(j);
            int factorComparacion = (ascendente ? 1 : -1) * criterio.compare(elemi, elemj);

            if (factorComparacion <= 0) {
                lista.changeInfo(k, elemi);
                i++;
            } else {
                lista.changeInfo(k, elemj);
                j++;
            }
            k++;
        }

        while (i <= leftList.size()) {
            lista.changeInfo(k, leftList.getElement(i));
            i++;
            k++;
        }

        while (j <= rightList.size()) {
            lista.changeInfo(k, rightList.getElement(j));
            j++;
            k++;
        }
    }
}
