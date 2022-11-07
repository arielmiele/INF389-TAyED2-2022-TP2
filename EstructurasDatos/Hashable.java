/*
 * INF389 - Taller de Algoritmos y Estructura de Datos II
 * Trabajo Práctico N° 2
 * Nombre: Ariel Gerardo Miele
 * Legajo: VINF011482
 * DNI: 34.434.704
 */

 package EstructurasDatos;

public interface Hashable {
    /**
     * Computa una función hash para este objeto.
     * 
     * @param tableSize el tamaño de la tabla hash.
     * @return un numero distribuido equitativamente entre 0 y tableSize-1.
     */
    int hash(int tableSize);
}