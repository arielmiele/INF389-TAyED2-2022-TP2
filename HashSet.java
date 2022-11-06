import java.util.AbstractCollection;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class HashSet<AnyType> extends AbstractCollection<AnyType> implements Set<AnyType> {

    /*
     * Mantiene una noción sobre la posición actual y sobre la referencia implicita
     * al HashSet
     */
    private class HashSetIterator implements Iterator<AnyType> {
        private int expectedModCount = modCount;
        private int currentPos = -1;
        private int visited = 0;

        public boolean hasNext() {
            if (expectedModCount != modCount)
                throw new ConcurrentModificationException();
            return visited != size();
        }

        public AnyType next() {
            if (!hasNext())
                throw new NoSuchElementException();
            do {
                currentPos++;
            } while (currentPos < array.length && !isActive(array, currentPos));
            visited++;
            return (AnyType) array[currentPos].element;
        }

        public void remove() {
            if (expectedModCount != modCount)
                throw new ConcurrentModificationException();
            if (currentPos == -1 || !isActive(array, currentPos))
                throw new IllegalStateException();
            array[currentPos].isActive = false;
            currentSize--;
            visited--;
            modCount++;
            expectedModCount++;
        }
    }

    private static class HashEntry implements java.io.Serializable {
        public Object element; // El elemento
        public boolean isActive; // Falso si se encuentra marcado para eliminar

        public HashEntry(Object e) {
            this(e, true);
        }

        public HashEntry(Object e, boolean i) {
            element = e;
            isActive = i;
        }
    }

    private static final int DEFAULT_TABLE_SIZE = 11;

    /**
     * Construye un HashSet vacío
     */
    public HashSet() {
        allocateArray(DEFAULT_TABLE_SIZE);
        clear();
    }

    /**
     * Construye un HashSet desde cualquier colección
     */
    public HashSet(Collection<? extends AnyType> other) {
        allocateArray(nextPrime(other.size() * 2));
        clear();
        for (AnyType val : other)
            add(val);
    }

    public int size() {
        return currentSize;
    }

    public Iterator<AnyType> iterator() {
        return new HashSetIterator();
    }

    /**
     * Verifica si x se encuentra en la Tabla Hash
     * Si se encuentra, retorna la referencia la objeto, sino retorna null
     * 
     * @param x es el objeto que se quiere buscar
     * @return si contains(x) es falso, retorna null, sino retorna el objeto que
     *         hace que contains(x) sea verdadero
     */
    public AnyType getMatch(AnyType x) {
        int currentPos = findPos(x);
        if (isActive(array, currentPos))
            return (AnyType) array[currentPos].element;
        return null;
    }

    /**
     * Comprueba si el ítem se encuentra en la colección
     * 
     * @param x es cualquier objeto
     * @return verdadero si la colección contiene un item igual que x
     */
    public boolean contains(Object x) {
        return isActive(array, findPos(x));
    }

    /**
     * Comprueba si un item en pos está activo
     * 
     * @param arr es el array para HashEntry
     * @param pos una posición en la tabla Hash
     * @return true si la posición está activa
     */
    public boolean isActive(HashEntry[] arr, int pos) {
        return arr[pos] != null && arr[pos].isActive;
    }

    /**
     * Remueve un item desde su colección
     * 
     * @param x es cualquier objeto
     * @return verdadero si el ítem fue removido de la colección
     */
    public boolean remove(Object x) {
        int currentPos = findPos(x);
        if (!isActive(array, currentPos))
            return false;
        array[currentPos].isActive = false;
        currentSize--;
        modCount++;

        if (currentSize < array.length / 8)
            rehash();
        return true;
    }

    /**
     * Cambia el tamaño de la colección a cero
     */
    public void clear() {
        currentSize = occupied = 0;
        modCount++;
        for (int i = 0; i < array.length; i++)
            array[i] = null;
    }

    /**
     * Agrega un item a la colección
     * 
     * @param x es cualquier objeto
     * @return verdadero si el item fue agregado a la colección
     */
    public boolean add(AnyType x) {
        int currentPos = findPos(x);
        if (isActive(array, currentPos))
            return false;
        if (array[currentPos] == null)
            occupied++;
        array[currentPos] = new HashEntry(x, true);
        currentSize++;
        modCount++;

        if (occupied > array.length / 2)
            rehash();

        return true;
    }

    /**
     * Rutina privada para realizar rehashing
     * Puede ser llamada por add y por remove
     */
    public void rehash() {
        HashEntry[] oldArray = array;

        // Crea una tabla nueva vacía
        allocateArray(nextPrime(4 * size()));
        currentSize = 0;
        occupied = 0;

        // Copia la tabla
        for (int i = 0; i < oldArray.length; i++)
            if (isActive(oldArray, i))
                add((AnyType) oldArray[i].element);
    }

    /**
     * Metodo que realiza la resolución cuadrática
     * 
     * @param x es el ítem que se buscará
     * @return la posición donde finaliza la búsqueda
     */
    private int findPos(Object x) {
        int offset = 1;
        int currentPos = (x == null) ? 0 : Math.abs(x.hashCode() % array.length);
        while (array[currentPos] != null) {
            if (x == null) {
                if (array[currentPos].element == null)
                    break;
            } else if (x.equals(array[currentPos].element))
                break;
            currentPos += offset;
            offset += 2;
            if (currentPos >= array.length)
                currentPos -= array.length;
        }
        return currentPos;
    }

    private void allocateArray(int arraySize) {
        array = new HashEntry[arraySize];
    }

    /**
     * Método para encontrar un número primo al menos tan grande como n
     * 
     * @param n el número donde comienza la búsqueda (tiene que ser positivo)
     * @return un número primo más grande o igual a n
     */
    private static int nextPrime(int n) {
        if (n % 2 == 0)
            n++;

        for (; !isPrime(n); n += 2)
            ;
        return n;
    }

    /**
     * Método interno para corroborar si un número es primo o no.
     * 
     * @param n es el numero a probar.
     * @return es el resultado (verdadero o falso).
     */
    private static boolean isPrime(int n) {
        if (n == 2 || n == 3)
            return true;

        if (n == 1 || n % 2 == 0)
            return false;

        for (int i = 3; i * i <= n; i += 2)
            if (n % i == 0)
                return false;

        return true;
    }

    private int currentSize = 0;
    private int occupied = 0;
    private int modCount = 0;
    private HashEntry[] array;
}