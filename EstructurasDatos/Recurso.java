/*
 * INF389 - Taller de Algoritmos y Estructura de Datos II
 * Trabajo Práctico N° 2
 * Nombre: Ariel Gerardo Miele
 * Legajo: VINF011482
 * DNI: 34.434.704
 */

package EstructurasDatos;

/**
 * Clase perteneciente al recurso que se almacenará en la tabla Hash
 * Contiene el código hash utilizado para calcular la posición del elemento
 */

public class Recurso implements Hashable {

    private int codigo;

    public Recurso(int valor) {
        this.codigo = valor;
    }

    @Override
    public int hash(int tableSize) {
        return this.codigo % tableSize;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Recurso recurso = (Recurso) o;
        return this.codigo == recurso.codigo;
    }

    public String aCadena() {
        return "Recurso (codigo: " + this.codigo + " ).\n";
    }

    public int obtenerCodigo() {
        return this.codigo;
    }
}
