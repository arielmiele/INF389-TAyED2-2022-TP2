/*
 * INF389 - Taller de Algoritmos y Estructura de Datos II
 * Trabajo Práctico N° 2
 * Nombre: Ariel Gerardo Miele
 * Legajo: VINF011482
 * DNI: 34.434.704
 */
package EstructurasDatos;

import java.util.LinkedList;
import java.util.List;

public class TablaHashAbierto<AnyType> {
    /**
     * Constructor de la tabla hash.
     */
    public TablaHashAbierto() {
        this(TAMANIO_TABLA_DEFAULT);
    }

    /**
     * Construye la tabla Hash.
     * 
     * @param tamanio tamaño aproximado de la tabla.
     */
    public TablaHashAbierto(int tamanio) {
        lasListas = new LinkedList[proximoPrimo(tamanio)];
        for (int i = 0; i < lasListas.length; i++)
            lasListas[i] = new LinkedList<>();
    }

    /**
     * Inserta el item en la tabla Hash. Si el item está presente, no hace nada.
     * 
     * @param x el item a ser insertado.
     */
    public void insertar(AnyType x) {
        List<AnyType> enQueLista = lasListas[miHash(x)];
        if (!enQueLista.contains(x)) {
            enQueLista.add(x);
            if (++tamanioActual > lasListas.length)
                rehash();
        }
    }

    /**
     * Elimina el item de la tabla.
     * 
     * @param x es el item a eliminar.
     */
    public void eliminar(AnyType x) {
        List<AnyType> enQueLista = lasListas[miHash(x)];
        if (enQueLista.contains(x)) {
            enQueLista.remove(x);
            tamanioActual--;
        }
    }

    /**
     * Busca un item dentro de la tabla.
     * 
     * @param x es el item que se buscará.
     * @return devuelve el item, si no se encuentra deuvuelve null.
     */
    public boolean encontrar(AnyType x) {
        List<AnyType> enQueLista = lasListas[miHash(x)];
        return enQueLista.contains(x);
    }

    /**
     * Vacía la tabla Hash logicamente
     */
    public void vaciar() {
        for (int i = 0; i < lasListas.length; i++)
            lasListas[i].clear();
        tamanioActual = 0;
    }

    /**
     * Rutina hash para objetos String.
     * 
     * @param key       la cadena a hashear
     * @param tableSize el tamaño de la tabla hash.
     * @return valor del hash.
     */
    public static int hash(String key, int tamanioTabla) {
        int hashVal = 0;

        for (int i = 0; i < key.length(); i++)
            hashVal = 37 * hashVal + key.charAt(i);

        hashVal %= tamanioTabla;
        if (hashVal < 0)
            hashVal += tamanioTabla;

        return hashVal;
    }

    private void rehash() {
        List<AnyType>[] listasViejas = lasListas;

        lasListas = new List[proximoPrimo(2 * lasListas.length)];
        for (int j = 0; j < lasListas.length; j++)
            lasListas[j] = new LinkedList<>();

        tamanioActual = 0;
        for (List<AnyType> lista : listasViejas)
            for (AnyType item : lista)
                insertar(item);
    }

    private int miHash(AnyType x) {
        int hashVal = x.hashCode();
        hashVal %= lasListas.length;
        if (hashVal < 0)
            hashVal += lasListas.length;
        return hashVal;
    }

    private static final int TAMANIO_TABLA_DEFAULT = 11;

    // Lista de arrays
    private List<AnyType>[] lasListas;
    private int tamanioActual;

    /**
     * Metodo interno para encontrar el proximo numero primo mayor que n.
     * 
     * @param n el numero inical (tiene que ser positivo).
     * @return un numero primo igual o mayor que n.
     */
    private static int proximoPrimo(int n) {
        if (n % 2 == 0)
            n++;

        for (; !esPrimo(n); n += 2)
            ;

        return n;
    }

    /**
     * Método interno para probar que un número es primo
     * 
     * @param n es el número a probar.
     * @return el resutlado del test.
     */
    private static boolean esPrimo(int n) {
        if (n == 2 || n == 3)
            return true;

        if (n == 1 || n % 2 == 0)
            return false;

        for (int i = 3; i * i <= n; i += 2)
            if (n % i == 0)
                return false;

        return true;
    }
}