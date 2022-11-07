/*
 * INF389 - Taller de Algoritmos y Estructura de Datos II
 * Trabajo Práctico N° 2
 * Nombre: Ariel Gerardo Miele
 * Legajo: VINF011482
 * DNI: 34.434.704
 */

 package EstructurasDatos;

// La entrada básica de una tabla Hash

class EntradaHash {
    Hashable elemento; // El elemento
    boolean esActivo; // Si es falso, quiere decir que fue eliminado

    public EntradaHash(Hashable e) {
        this(e, true);
    }

    public EntradaHash(Hashable e, boolean i) {
        elemento = e;
        esActivo = i;
    }
}