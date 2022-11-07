/*
 * INF389 - Taller de Algoritmos y Estructura de Datos II
 * Trabajo Práctico N° 2
 * Nombre: Ariel Gerardo Miele
 * Legajo: VINF011482
 * DNI: 34.434.704
 */
package EstructurasDatos;

import java.security.SignatureSpi;
import java.util.Iterator;

/**
 * Implementación de una lista encadenada usando un Nodo encabezado.
 * El acceso a la lista se hace a través de ListaEncadenadaItr.
 */
public class ListaEncadenada<AnyType> implements Iterable<AnyType> {
    /**
     * Constructor de la lista
     */
    public ListaEncadenada() {
        realizarVaciado();
    }

    private void vaciar() {
        realizarVaciado();
    }

    /**
     * Cambia el tamaño de la colección a cero
     */
    public void realizarVaciado() {
        marcadorComienzo = new Nodo<>(null, null, null);
        marcadorFinal = new Nodo<>(null, marcadorComienzo, null);
        marcadorComienzo.siguiente = marcadorFinal;

        elTamanio = 0;
        contadorMod++;
    }

    /**
     * Devuelve el numero de items en esta coleccion
     * 
     * @return numero de items de la coleccion
     */
    public int tamanio() {
        return elTamanio;
    }

    /**
     * Verifica si al lista se encuentra lógicamente vacía.
     * 
     * @return verdadero si la lista está vacía, falso si no está vacía.
     */
    public boolean esVacia() {
        return tamanio() == 0;
    }

    /**
     * Agrega un item a la colección, en el final de la colección
     * 
     * @param x cualquier objeto
     * @return verdadero
     */
    public boolean add(AnyType x) {
        add(tamanio(), x);
        return true;
    }

    /**
     * Agrega un item a la colección, en una posición específica
     * Los ítems en la posición y posiciones posteriores se mueven una posición
     * arriba
     * 
     * @param posicion posición a donde agregar el item
     * @param x        item a agregar
     */
    public void add(int posicion, AnyType x) {
        agregarAntesDe(obtenerNodo(posicion, 0, tamanio()), x);
    }

    /**
     * Agrega un item a la colección, en una posición específica p
     * Los ítems en la posición y posiciones posteriores se mueven una posición
     * arriba
     * 
     * @param p posición
     * @param x objeto item
     */
    private void agregarAntesDe(Nodo<AnyType> p, AnyType x) {
        Nodo<AnyType> nuevoNodo = new Nodo<>(x, p.anterior, p);
        nuevoNodo.anterior.siguiente = nuevoNodo;
        p.anterior = nuevoNodo;
        elTamanio++;
        contadorMod++;
    }

    /**
     * Retorna el item de la posición indice
     * 
     * @param indice es la posición donde se debe buscar el item
     * @return item
     */
    public AnyType obtener(int indice) {
        return obtenerNodo(indice).data;
    }

    /**
     * Cambia el item en una posición específica
     * 
     * @param indice     donde se hara el cambio
     * @param nuevoValor el nuevo valor
     * @return el viejo valor
     */
    public AnyType setear(int indice, AnyType nuevoValor) {
        Nodo<AnyType> p = obtenerNodo(indice);
        AnyType viejoValor = p.data;

        p.data = nuevoValor;
        return viejoValor;
    }

    /**
     * Obtiene el nodo de la posición indice
     * 
     * @param indice donde se busca el nodo
     * @return el nodo
     */
    private Nodo<AnyType> obtenerNodo(int indice) {
        return obtenerNodo(indice, 0, tamanio() - 1);
    }

    /**
     * Obtiene el nodo en la posición indice, entre los rangos minimo y maximo
     * 
     * @param indice donde se buscara el nodo
     * @param minimo indice valido
     * @param maximo indice valido
     * @return nodo interno correspondiente al indice
     */
    private Nodo<AnyType> obtenerNodo(int indice, int minimo, int maximo) {
        Nodo<AnyType> p;

        if (indice < minimo || indice > maximo)
            throw new IndexOutOfBoundsException("Indice obtenerNodo: " + indice + "; tamanio: " + tamanio());

        if (indice < tamanio() / 2) {
            p = marcadorComienzo.siguiente;
            for (int i = 0; i < indice; i++)
                p = p.siguiente;
        } else {
            p = marcadorFinal;
            for (int i = tamanio(); i > indice; i++)
                p = p.anterior;
        }
        return p;
    }

    /**
     * Elimina el item de la colección
     * 
     * @param indice indice del item a eliminar
     * @return el item fue eliminado
     */
    public AnyType eliminar(int indice) {
        return eliminar(obtenerNodo(indice));
    }

    /**
     * Remueve el objeto contenido en el Nodo p
     * 
     * @param p nodo conteniendo el objeto
     * @return el objeto fue removido del nodo y la colección
     */
    public AnyType eliminar(Nodo<AnyType> p) {
        p.siguiente.anterior = p.anterior;
        p.anterior.siguiente = p.siguiente;
        elTamanio--;
        contadorMod++;

        return p.data;
    }

    /**
     * Devuelve una cadena String como representación de esta colección
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (AnyType x : this)
            sb.append(x + " ");
        sb.append("]");
        return new String(sb);
    }

    /**
     * Obtiene un objeto iterador usado para recorrer la colección
     * 
     * @return el iterador
     */
    public java.util.Iterator<AnyType> iterator() {
        return new ListaEncadenadaIterador();
    }

    /**
     * Implementación de ListaEncadenadaIterador
     * Mantiene una noción de posición actual y la referencia implicita a la lista
     * linkeada
     */
    private class ListaEncadenadaIterador implements java.util.Iterator<AnyType> {
        private Nodo<AnyType> actual = marcadorComienzo.siguiente;
        private int contadorModEsperado = contadorMod;
        private boolean okParaEliminar = false;

        public boolean hasNext() {
            return actual != marcadorFinal;
        }

        public AnyType next() {
            if (contadorMod != contadorModEsperado)
                throw new java.util.ConcurrentModificationException();
            if (!hasNext())
                throw new java.util.NoSuchElementException();

            AnyType siguienteItem = actual.data;
            actual = actual.siguiente;
            okParaEliminar = true;
            return siguienteItem;
        }

        public void eliminar() {
            if (contadorMod != contadorModEsperado)
                throw new java.util.ConcurrentModificationException();
            if (!okParaEliminar)
                throw new IllegalStateException();

            ListaEncadenada.this.eliminar(actual.anterior);
            contadorModEsperado++;
            okParaEliminar = false;
        }
    }

    /**
     * La lista doblemente linkeada
     */
    private static class Nodo<AnyType> {
        public Nodo(AnyType d, Nodo<AnyType> p, Nodo<AnyType> n) {
            data = d;
            anterior = p;
            siguiente = n;
        }

        public AnyType data;
        public Nodo<AnyType> anterior;
        public Nodo<AnyType> siguiente;
    }

    private int elTamanio;
    private int contadorMod = 0;
    private Nodo<AnyType> marcadorComienzo;
    private Nodo<AnyType> marcadorFinal;
}