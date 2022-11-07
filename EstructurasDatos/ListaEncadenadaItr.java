/*
 * INF389 - Taller de Algoritmos y Estructura de Datos II
 * Trabajo Práctico N° 2
 * Nombre: Ariel Gerardo Miele
 * Legajo: VINF011482
 * DNI: 34.434.704
 */

package EstructurasDatos;

/**
 * Implementación del iterador de listas para las listas encadenadas usando un
 * Nodo encabezado.
 */

public class ListaEncadenadaItr<AnyType> {
    /**
     * Constructor del iterador de la lista
     * 
     * @param theNode cualquier nodo de la lista encadenada
     */
    ListaEncadenadaItr(NodoLista elNodo) {
        actual = elNodo;
    }

    /**
     * Prueba si la posición actual se encuentra fuera de la lista.
     * 
     * @return verdadero si al posición actual es null.
     */
    public boolean fueraDeLista() {
        return actual == null;
    }

    /**
     * Devuelve si un item se encuentra almacenado en la posición actual.
     * 
     * @return el item almacenado, o null si la posición actual no se encuentra en
     *         la lista.
     */
    public Object devolver() {
        return fueraDeLista() ? null : actual.elemento;
    }

    /**
     * Avanza la posición actual para el proximo nodo de la lista.
     * Si la posición actual es null, entonces no hace nada.
     */
    public void avanzar() {
        if (!fueraDeLista())
            actual = actual.proximo;
    }

    NodoLista actual; // actual position
}
