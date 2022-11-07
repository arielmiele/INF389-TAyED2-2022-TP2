/*
 * INF389 - Taller de Algoritmos y Estructura de Datos II
 * Trabajo Práctico N° 2
 * Nombre: Ariel Gerardo Miele
 * Legajo: VINF011482
 * DNI: 34.434.704
 */
package EstructurasDatos;

public class TablaHashAbierto {
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
        listas = new ListaEncadenada[proximoPrimo(tamanio)];
        for (int i = 0; i < listas.length; i++)
            listas[i] = new ListaEncadenada();
    }

    /**
     * Inserta el item en la tabla Hash. Si el item está presente, no hace nada.
     * 
     * @param x el item a ser insertado.
     */
    public void insertar(Hashable x) {
        ListaEncadenada enQueLista = listas[x.hash(listas.length)];
        ListaEncadenadaItr itr = enQueLista.encontrar(x);

        if (itr.fueraDeLista())
            enQueLista.insertar(x, enQueLista.zeroth());
    }

    /**
     * Elimina el item de la tabla.
     * 
     * @param x es el item a eliminar.
     */
    public void eliminar(Hashable x) {
        listas[x.hash(listas.length)].eliminar(x);
    }

    /**
     * Busca un item dentro de la tabla.
     * 
     * @param x es el item que se buscará.
     * @return devuelve el item, si no se encuentra deuvuelve null.
     */
    public Hashable encontrar(Hashable x) {
        return (Hashable) listas[x.hash(listas.length)].encontrar(x).devolver();
    }

    /**
     * Vacía la tabla Hash logicamente
     */
    public void vaciar() {
        for (int i = 0; i < listas.length; i++)
            listas[i].vaciar();
    }

    private static final int TAMANIO_TABLA_DEFAULT = 11;

    // Lista de arrays
    private ListaEncadenada[] listas;

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