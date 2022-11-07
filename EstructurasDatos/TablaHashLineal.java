/*
 * INF389 - Taller de Algoritmos y Estructura de Datos II
 * Trabajo Práctico N° 2
 * Nombre: Ariel Gerardo Miele
 * Legajo: VINF011482
 * DNI: 34.434.704
 */

package EstructurasDatos;

public class TablaHashLineal {
    /**
     * Constructor de la tabla hash.
     */
    public TablaHashLineal() {
        this(DEFAULT_TABLE_SIZE);
    }

    /**
     * Construye la tabla hash.
     * 
     * @param tamanio el tamaño inicial aproximado.
     */
    public TablaHashLineal(int tamanio) {
        almacenarArreglo(tamanio);
        vaciarTabla();
    }

    /**
     * Insertar un item en la tabla. Si el item se encuentra presente, no hacer
     * nada.
     * El item se setea como activo cuando es insertado.
     * Si la tabla se encuentra completa al 50% se genera un rehash.
     * 
     * @param x es el item a insertar.
     */
    public void insertar(Hashable x) {
        int posActual = buscarPosicion(x);
        if (esActivo(posActual))
            return;

        arreglo[posActual] = new EntradaHash(x, true);

        if (++tamanioActual > arreglo.length / 2)
            rehash();
    }

    /**
     * Expande la tabla hash cuando es necesario.
     */
    private void rehash() {
        EntradaHash[] arregloViejo = arreglo;

        // Crea una nueva tabla vacía, del doble de tamaño
        almacenarArreglo(proximoPrimo(2 * arregloViejo.length));
        tamanioActual = 0;

        // Copia la tabla
        for (int i = 0; i < arregloViejo.length; i++)
            if (arregloViejo[i] != null && arregloViejo[i].esActivo)
                insertar(arregloViejo[i].elemento);

        return;
    }

    /**
     * Éste método realiza la resolución por sondeo lineal.
     * 
     * @param x es el dato que buscamos.
     * @return devuelve la posición donde se terminó la busqueda.
     */
    private int buscarPosicion(Hashable x) {
        // Define la posición dentro de la cual se debería guardar el ítem x
        int posActual = x.hash(arreglo.length);

        // Mientras que la posición del arreglo tenga datos y el elemento sea x
        while (arreglo[posActual] != null &&
                !arreglo[posActual].elemento.equals(x)) {
            // Computa una nueva posición en el arreglo
            posActual += 1;
            if (posActual >= arreglo.length)
                // Implementa el módulo, para mantenerse dentro del arreglo
                posActual -= arreglo.length;
        }
        return posActual;
    }

    /**
     * Elimina un item de la tabla hash.
     * 
     * @param x es el item a eliminar.
     */
    public void eliminar(Hashable x) {
        int posActual = buscarPosicion(x);
        if (esActivo(posActual))
            arreglo[posActual].esActivo = false;
    }

    /**
     * Busca un item en la tabla hash.
     * 
     * @param x es el item a buscar..
     * @return devuelve el item que hizo match.
     */
    public Hashable buscar(Hashable x) {
        int posActual = buscarPosicion(x);
        return esActivo(posActual) ? arreglo[posActual].elemento : null;
    }

    /**
     * Devuelve true si posActual existe y está activa.
     * 
     * @param posActual es el resultado de la llamada a buscarPosicion.
     * @return verdadero si posActual es activo.
     */
    private boolean esActivo(int posActual) {
        return arreglo[posActual] != null && arreglo[posActual].esActivo;
    }

    /**
     * Vacía lógicamente la tabla hash.
     */
    public void vaciarTabla() {
        tamanioActual = 0;
        for (int i = 0; i < arreglo.length; i++)
            arreglo[i] = null;
    }

    private static final int DEFAULT_TABLE_SIZE = 11;

    /** El arreglo de elementos. */
    private EntradaHash[] arreglo; // El arreglo de elementos
    private int tamanioActual; // El número de posiciones ocupadas

    /**
     * Metodo interno para ubicar arreglos.
     * 
     * @param tamanioArreglo el tamaño del arreglo.
     */
    private void almacenarArreglo(int tamanioArreglo) {
        arreglo = new EntradaHash[tamanioArreglo];
    }

    /**
     * Metodo interno para encontrar un número primo al menos tan grande como n.
     * 
     * @param n es el numero inical, debe ser positivo.
     * @return devuelve un numero primo igual o mayor que n.
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

    /**
     * Metodo para imprimir la tabla hash
     */
    public void imprimirTablaHash() {
        System.out.println(" | POSICION | VALOR |");
        for (int i = 0; i < this.arreglo.length; i++) {
            if (this.arreglo[i] == null) {
                System.out.println(" | " + i + " | " + "Libre" + " |");
            } else {
                System.out.println(" | " + i + " | " + this.arreglo[i] + " |");
            }
        }
    }
}