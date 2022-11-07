/*
 * INF389 - Taller de Algoritmos y Estructura de Datos II
 * Trabajo Práctico N° 2
 * Nombre: Ariel Gerardo Miele
 * Legajo: VINF011482
 * DNI: 34.434.704
 */

package EstructurasDatos;

// Nodo básico almacenado en la lista encadenada

class NodoLista {
    // Constructores
    NodoLista(Object elElemento) {
        this(elElemento, null);
    }

    NodoLista(Object elElemento, NodoLista n) {
        elemento = elElemento;
        proximo = n;
    }

    Object elemento;
    NodoLista proximo;
}