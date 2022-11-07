/*
 * INF389 - Taller de Algoritmos y Estructura de Datos II
 * Trabajo Práctico N° 2
 * Nombre: Ariel Gerardo Miele
 * Legajo: VINF011482
 * DNI: 34.434.704
 */
package EstructurasDatos;

/**
 * Implementación de una lista encadenada usando un Nodo encabezado.
 * El acceso a la lista se hace a través de ListaEncadenadaItr.
 */
public class ListaEncadenada {
    /**
     * Constructor de la lista
     */
    public ListaEncadenada() {
        encabezado = new NodoLista(null);
    }

    /**
     * Verifica si al lista se encuentra lógicamente vacía.
     * 
     * @return verdadero si la lista está vacía, falso si no está vacía.
     */
    public boolean esVacia() {
        return encabezado.proximo == null;
    }

    /**
     * Vacía logicamente la lista.
     */
    public void vaciar() {
        encabezado.proximo = null;
    }

    /**
     * Devuelve un iterador repsentando el primer nodo de la lista.
     */
    public ListaEncadenadaItr zeroth() {
        return new ListaEncadenadaItr(encabezado);
    }

    /**
     * Devuelve un iterador que representa el primer nodo en la lista.
     * Esta operación es válida para listas vacías.
     */
    public ListaEncadenadaItr primero() {
        return new ListaEncadenadaItr(encabezado.proximo);
    }

    /**
     * Inserta despues de p.
     * 
     * @param x el item a insertar.
     * @param p la posición anterior al item a insertar.
     */
    public void insertar(Object x, ListaEncadenadaItr p) {
        if (p != null && p.actual != null)
            p.actual.proximo = new NodoLista(x, p.actual.proximo);
    }

    /**
     * Devuelve un iterador correspondiente al primer nodo conteniendo un item.
     * 
     * @param x es el item que es buscado.
     * @return un iterador.
     */
    public ListaEncadenadaItr encontrar(Object x) {
        NodoLista itr = encabezado.proximo;

        while (itr != null && !itr.elemento.equals(x))
            itr = itr.proximo;

        return new ListaEncadenadaItr(itr);
    }

    /**
     * Devuelve un iterador correspondiente al primer nodo conteniendo un item.
     * 
     * @param x es el item que es buscado.
     * @return devuelve el iterador apropiado si el item es encontrado. Sino
     *         devuelve el operador del último item encontrado.
     */
    public ListaEncadenadaItr encontrarPrevio(Object x) {
        NodoLista itr = encabezado;

        while (itr.proximo != null && !itr.proximo.elemento.equals(x))
            itr = itr.proximo;

        return new ListaEncadenadaItr(itr);
    }

    /**
     * Elimina la primer ocurrencia de un item.
     * 
     * @param x item a eliminar.
     */
    public void eliminar(Object x) {
        ListaEncadenadaItr p = encontrarPrevio(x);

        if (p.actual.proximo != null)
            p.actual.proximo = p.actual.proximo.proximo;
    }

    // Imprime la lista
    public static void imprimirLista(ListaEncadenada laLista) {
        if (laLista.esVacia())
            System.out.print("Lista vacia.\n");
        else {
            ListaEncadenadaItr itr = laLista.primero();
            for (; !itr.fueraDeLista(); itr.avanzar())
                System.out.print(itr.devolver() + " ");
        }

        System.out.println();
    }

    private NodoLista encabezado;
}