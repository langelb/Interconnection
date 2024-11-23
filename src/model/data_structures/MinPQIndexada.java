package model.data_structures;

import java.util.logging.Logger;

public class MinPQIndexada<K extends Comparable<K>, IK extends Comparable <IK>, V extends Comparable <V>> extends MinPQ<K, V>
{

    private static final Logger logger = Logger.getLogger(MinPQ.class.getName());
    
    public MinPQIndexada(int inicial) {
		super(inicial);
	}

	public void insert(K key, IK indexedKey, V value) {
        try {
			arbol.insertElement(new NodoIndexedMinPQ<K, IK, V>(key, indexedKey, value), arbol.size() +1);
		} catch (PosException | NullException e) {
			
			logger.severe("Error: La lista está vacía. Detalles: " + e.getMessage());
		}
        tamano += 1;
        swim(arbol, tamano);
    }
    
    public void changePriority(IK indexedKey, K newKey, V value) 
    {
    	try
    	{
    		int posicionBuscado = -1;
            for(int i = 1; i <= arbol.size() && posicionBuscado == -1; i++) {
                NodoIndexedMinPQ<K, IK, V> actual = (NodoIndexedMinPQ<K, IK, V>) arbol.getElement(i);
                if(actual.getIndexedKey().compareTo(indexedKey) == 0) {
                    posicionBuscado = i;
                }
            }
            
            
            if(newKey.compareTo(arbol.getElement(posicionBuscado).getKey()) > 0) {
                arbol.getElement(posicionBuscado).setKey(newKey);
                arbol.getElement(posicionBuscado).setValue(value);
                sink(arbol, posicionBuscado);
            }else if(newKey.compareTo(arbol.getElement(posicionBuscado).getKey()) < 0) {
                arbol.getElement(posicionBuscado).setKey(newKey);
                arbol.getElement(posicionBuscado).setValue(value);
                swim(arbol, posicionBuscado);
            }
    	}
    	catch (PosException | VacioException e) 
		{
			logger.severe("Error: La lista está vacía. Detalles: " + e.getMessage());
		}
        
    }
}
