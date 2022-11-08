/*
 * INF389 - Taller de Algoritmos y Estructura de Datos II
 * Trabajo Práctico N° 2
 * Nombre: Ariel Gerardo Miele
 * Legajo: VINF011482
 * DNI: 34.434.704
 */

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.lang.Process;
import EstructurasDatos.*;

public class Main {

    // Inicializa el scanner para poder tomar datos desde la consola
    static Scanner entradaScan = new Scanner(System.in);

    /**
     * Método que permite borrar la consola para una mejor utilización del sistema
     */
    public static void ClearConsole() {
        try {
            String operatingSystem = System.getProperty("os.name"); // Check the current operating system

            if (operatingSystem.contains("Windows")) {
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        String dato; // Almacenará las opciones sobre las tablas Hash
        boolean salir = false; // flag utilizado para salir del menu principal

        do {
            ClearConsole();
            System.out.println("\n¡BIENVENIDO AL ADMINISTRADOR DE TABLAS HASH!");
            System.out.println("\nOpciones:\n" +
                    "Seleccione el metodo deseado:\n" +
                    "1 - Operar con tabla hash de manejo de colisiones cuadratico\n" +
                    "2 - Operar con tabla hash de manejo de colisiones con sondeo lineal\n" +
                    "3 - Operar con tabla hash de manejo de colisiones de direccionamiento cerrado (hashing abierto)\n"
                    +
                    "4 - Salir");

            System.out.print(">> ");
            dato = entradaScan.nextLine(); // Obtiene el dato para elegir el tipo de uso del programa

            try {
                switch (dato) {
                    case "1":
                        ClearConsole();
                        System.out.println("CASO DE HASH CUADRATICO");
                        programa(TablaHashCuadratica.class);
                        break;
                    case "2":
                        ClearConsole();
                        System.out.println("CASO DE HASH LINEAL");
                        programa(TablaHashLineal.class);
                        break;
                    case "3":
                        ClearConsole();
                        System.out.println("CASO DE HASH ABIERTO");
                        programa(TablaHashAbierto.class);
                        break;
                    case "4":
                        salir = true;
                        break;
                    default:
                        ClearConsole();
                        System.out.println("Opcion incorrecta, por favor seleccione una opcion del 1 al 4.");
                        break;
                }
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } while (!salir);

        // Cierra el Scanner
        entradaScan.close();
        // Limpia la consola
        ClearConsole();
        // Muestra mensaje de salida
        System.out.println("\n¡PROGRAMA FINALIZADO! - ¡Gracias por utilizar el Administrador de Tablas Hash!.\n");
    }

    /**
     * Menú de opciones para administrar la tabla
     * 
     * @return opción elegida como entero
     */
    public static int imprimirMenu() {
        // Usado para almacenar la opción elegida desde el menú
        String dato;
        // Limpia la consola para mostrar el menú
        ClearConsole();
        System.out.println("ADMINISTRAR TABLA HASH");
        System.err.println("----------------------");
        System.out.println("\nOpciones:" +
                "\n1 - Insertar elemento\n" +
                "2 - Buscar elemento\n" +
                "3 - Borrar elemento\n" +
                "4 - Atrás\n");

        System.out.print(">> ");
        dato = entradaScan.nextLine(); // Obtiene la opción elegida
        return Integer.parseInt(dato); // Transforma la opción en entero y la devuelve
    }

    /**
     * Menú de opciones SI / NO
     * 
     * @return opción elegida como entero
     */
    public static int imprimirOpciones() {
        String dato; // Almacena el dato elegido
        System.out.println("\nOpciones:" +
                "\n1 - SI\n" +
                "2 - NO\n");

        System.out.print(">> ");
        dato = entradaScan.nextLine(); // Obtiene el dato ingresado por teclado
        ClearConsole(); // Limpia la consola
        return Integer.parseInt(dato); // Transforma la opción en entero y la devuelve
    }

    /**
     * Este programa permite:
     * - Ingreso de datos en tabla
     * - Busqueda de datos en tabla
     * - Eliminacion de datos de tabla
     * 
     * @param claseHash es el tipo de tabla hash seleccionada
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public static void programa(Class claseHash)
            throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        /*
         * Se solicita el tamaño de la tabla Hash para inicializarla con su tamaño
         * inicial
         */
        System.out.println("\nSeleccione el tamaño de la tabla hash: ");
        String dato;
        dato = entradaScan.nextLine();

        /*
         * opcionMenu - Almacena la opcion del menu
         * opcionRepetir - Almacena la opcion del menu SI/NO
         */
        int opcionMenu, opcionRepetir;
        boolean flag;

        if (claseHash == TablaHashAbierto.class) {
            // Selección del caso Tabla Hash Abierto
            // Creación de la tabla con hash abierto
            TablaHashAbierto THA = new TablaHashAbierto(Integer.parseInt(dato));
            do {
                // Imprime menú y retorna selección
                opcionMenu = imprimirMenu();
                switch (opcionMenu) {
                    // Ingreso de Dato
                    case 1:
                        do {
                            flag = false;
                            ClearConsole();
                            System.out.println("\nINGRESO DE VALORES A TABLA HASH\n");
                            do {
                                System.out
                                        .println("\nIngrese el valor que se desea ingresar en la tabla (6 digitos): ");
                                dato = entradaScan.nextLine();
                                // Inserta el valor en la tabla
                                THA.insertar(Integer.parseInt(dato));
                                System.out.println(
                                        "\nEl dato " + dato
                                                + " ha sido ingresado a la tabla. Desea ingresar otro valor? (SI/NO): \n");
                                opcionRepetir = imprimirOpciones();
                                while (opcionRepetir != 1 && opcionRepetir != 2) {
                                    System.out.println(
                                            "\nOpcion incorrecta. Indique si quiere ingresar otro valor (SI/NO): ");
                                    opcionRepetir = imprimirOpciones();
                                }
                            } while (opcionRepetir == 1);
                        } while (flag);
                        break;
                    // Buscar el elemento
                    case 2:
                        do {
                            flag = false;
                            ClearConsole();
                            System.out.println("\nBUSQUEDA DE VALORES EN TABLA HASH\n");
                            do {
                                System.out
                                        .println("\nIngrese el valor que se desea buscar en la tabla (6 digitos): ");
                                dato = entradaScan.nextLine();
                                // Busca el valor en la tabla
                                if (THA.encontrar(Integer.parseInt(dato))) {
                                    System.out.println("\nSe encontro el valor " + dato
                                            + " en la tabla hash. Desea buscar otro valor? (SI/NO): \n");
                                    opcionRepetir = imprimirOpciones();
                                    while (opcionRepetir != 1 && opcionRepetir != 2) {
                                        System.out.println(
                                                "\nOpcion incorrecta. Indique si quiere buscar otro valor (SI/NO): ");
                                        opcionRepetir = imprimirOpciones();
                                    }
                                } else {
                                    System.out.println(
                                            "\nValor no encontrado en la tabla. Desea buscar otro valor? (SI/NO): ");
                                    opcionRepetir = imprimirOpciones();
                                    while (opcionRepetir != 1 && opcionRepetir != 2) {
                                        System.out.println(
                                                "\nOpcion incorrecta. Indique si quiere buscar otro valor (SI/NO): ");
                                        opcionRepetir = imprimirOpciones();
                                    }
                                }
                            } while (opcionRepetir == 1);
                        } while (flag);
                        break;
                    // Borrar el elemento
                    case 3:
                        do {
                            flag = false;
                            ClearConsole();
                            System.out.println("\nBORRADO DE VALORES DE TABLA HASH\n");
                            do {

                                System.out.println("\nIngrese el valor que se desea borrar de la tabla (6 digitos): ");
                                dato = entradaScan.nextLine();
                                // Busca el valor en la tabla, para mostrar si pudo ser eliminado o no
                                if (THA.encontrar(Integer.parseInt(dato))) {
                                    // Se busca el valor en la tabla, solo para indicarle al usuario si existe o no
                                    System.out.println(
                                            "\nSe encontró el valor " + dato
                                                    + " en la tabla hash. Se borrará el dato.\n");
                                    // Si encuentra el valor, lo elimina
                                    THA.eliminar(Integer.parseInt(dato));
                                    System.out.println("\nValor " + dato
                                            + " borrado. Indique si quiere borrar otro valor (SI/NO): \n");
                                    opcionRepetir = imprimirOpciones();
                                    while (opcionRepetir != 1 && opcionRepetir != 2) {
                                        System.out.println(
                                                "\nOpcion incorrecta. Indique si quiere buscar otro valor (SI/NO): ");
                                        opcionRepetir = imprimirOpciones();
                                    }
                                } else {
                                    System.out.println(
                                            "\nValor no encontrado en la tabla, por lo tanto no puede ser borrado. Indique si quiere borrar otro valor (SI/NO): \n");
                                    opcionRepetir = imprimirOpciones();
                                    while (opcionRepetir != 1 && opcionRepetir != 2) {
                                        System.out.println(
                                                "\nOpcion incorrecta. Indique si quiere buscar otro valor (SI/NO): ");
                                        opcionRepetir = imprimirOpciones();
                                    }
                                }
                            } while (opcionRepetir == 1);
                        } while (flag);
                        break;
                }
            } while (opcionMenu != 4);

        } else if (claseHash == TablaHashCuadratica.class)

        {
            // Selección del caso Tabla Hash Sondeo Cuadrático
            // Creación de la tabla con hash sondeo cuadratico
            TablaHashCuadratica THC = new TablaHashCuadratica(Integer.parseInt(dato));
            // Imprime menú y retorna selección
            do {
                // Imprime menú y retorna selección
                opcionMenu = imprimirMenu();
                switch (opcionMenu) {
                    // Ingreso de Dato
                    case 1:
                        do {
                            flag = false;
                            ClearConsole();
                            System.out.println("\nINGRESO DE VALORES A TABLA HASH\n");
                            do {
                                System.out
                                        .println("\nIngrese el valor que se desea ingresar en la tabla (6 digitos): ");
                                dato = entradaScan.nextLine();
                                THC.insertar(Integer.parseInt(dato));
                                System.out.println(
                                        "\nEl dato " + dato
                                                + " ha sido ingresado a la tabla. Desea ingresar otro valor? (SI/NO): \n");
                                opcionRepetir = imprimirOpciones();
                                while (opcionRepetir != 1 && opcionRepetir != 2) {
                                    System.out.println(
                                            "\nOpcion incorrecta. Indique si quiere ingresar otro valor (SI/NO): ");
                                    opcionRepetir = imprimirOpciones();
                                }
                            } while (opcionRepetir == 1);
                        } while (flag);
                        break;
                    // Buscar el elemento
                    case 2:
                        do {
                            flag = false;
                            ClearConsole();
                            System.out.println("\nBUSQUEDA DE VALORES EN TABLA HASH\n");
                            do {
                                System.out
                                        .println("\nIngrese el valor que se desea buscar en la tabla (6 digitos): ");
                                dato = entradaScan.nextLine();
                                if (THC.buscar(Integer.parseInt(dato))) {
                                    System.out.println("\nSe encontro el valor " + dato
                                            + " en la tabla hash. Desea buscar otro valor? (SI/NO): \n");
                                    opcionRepetir = imprimirOpciones();
                                    while (opcionRepetir != 1 && opcionRepetir != 2) {
                                        System.out.println(
                                                "\nOpcion incorrecta. Indique si quiere buscar otro valor (SI/NO): ");
                                        opcionRepetir = imprimirOpciones();
                                    }
                                } else {
                                    System.out.println(
                                            "\nValor no encontrado en la tabla. Desea buscar otro valor? (SI/NO): ");
                                    opcionRepetir = imprimirOpciones();
                                    while (opcionRepetir != 1 && opcionRepetir != 2) {
                                        System.out.println(
                                                "\nOpcion incorrecta. Indique si quiere buscar otro valor (SI/NO): ");
                                        opcionRepetir = imprimirOpciones();
                                    }
                                }
                            } while (opcionRepetir == 1);
                        } while (flag);
                        break;
                    // Borrar el elemento
                    case 3:
                        do {
                            flag = false;
                            ClearConsole();
                            System.out.println("\nBORRADO DE VALORES DE TABLA HASH\n");
                            do {

                                System.out.println("\nIngrese el valor que se desea borrar de la tabla (6 digitos): ");
                                dato = entradaScan.nextLine();
                                if (THC.buscar(Integer.parseInt(dato))) {
                                    // Se busca el valor en la tabla, solo para indicarle al usuario si existe o no
                                    System.out.println(
                                            "\nSe encontró el valor " + dato
                                                    + " en la tabla hash. Se borrará el dato.\n");
                                    THC.eliminar(Integer.parseInt(dato));
                                    System.out.println("\nValor " + dato
                                            + " borrado. Indique si quiere borrar otro valor (SI/NO): \n");
                                    opcionRepetir = imprimirOpciones();
                                    while (opcionRepetir != 1 && opcionRepetir != 2) {
                                        System.out.println(
                                                "\nOpcion incorrecta. Indique si quiere buscar otro valor (SI/NO): ");
                                        opcionRepetir = imprimirOpciones();
                                    }
                                } else {
                                    System.out.println(
                                            "\nValor no encontrado en la tabla, por lo tanto no puede ser borrado. Indique si quiere borrar otro valor (SI/NO): \n");
                                    opcionRepetir = imprimirOpciones();
                                    while (opcionRepetir != 1 && opcionRepetir != 2) {
                                        System.out.println(
                                                "\nOpcion incorrecta. Indique si quiere buscar otro valor (SI/NO): ");
                                        opcionRepetir = imprimirOpciones();
                                    }
                                }
                            } while (opcionRepetir == 1);
                        } while (flag);
                        break;
                }
            } while (opcionMenu != 4);

        } else if (claseHash == TablaHashLineal.class) {
            // Selección del caso Tabla Hash Sondeo Lineal
            // Creación de la tabla con hash sondeo lineal
            TablaHashLineal THL = new TablaHashLineal(Integer.parseInt(dato));
            // Imprime menú y retorna selección
            do {
                // Imprime menú y retorna selección
                opcionMenu = imprimirMenu();
                switch (opcionMenu) {
                    // Ingreso de Dato
                    case 1:
                        do {
                            flag = false;
                            ClearConsole();
                            System.out.println("\nINGRESO DE VALORES A TABLA HASH\n");
                            do {
                                System.out
                                        .println("\nIngrese el valor que se desea ingresar en la tabla (6 digitos): ");
                                dato = entradaScan.nextLine();
                                THL.insertar(Integer.parseInt(dato));
                                System.out.println(
                                        "\nEl dato " + dato
                                                + " ha sido ingresado a la tabla. Desea ingresar otro valor? (SI/NO): \n");
                                opcionRepetir = imprimirOpciones();
                                while (opcionRepetir != 1 && opcionRepetir != 2) {
                                    System.out.println(
                                            "\nOpcion incorrecta. Indique si quiere ingresar otro valor (SI/NO): ");
                                    opcionRepetir = imprimirOpciones();
                                }
                            } while (opcionRepetir == 1);
                        } while (flag);
                        break;
                    // Buscar el elemento
                    case 2:
                        do {
                            flag = false;
                            ClearConsole();
                            System.out.println("\nBUSQUEDA DE VALORES EN TABLA HASH\n");
                            do {
                                System.out
                                        .println("\nIngrese el valor que se desea buscar en la tabla (6 digitos): ");
                                dato = entradaScan.nextLine();
                                if (THL.buscar(Integer.parseInt(dato))) {
                                    System.out.println("\nSe encontro el valor " + dato
                                            + " en la tabla hash. Desea buscar otro valor? (SI/NO): \n");
                                    opcionRepetir = imprimirOpciones();
                                    while (opcionRepetir != 1 && opcionRepetir != 2) {
                                        System.out.println(
                                                "\nOpcion incorrecta. Indique si quiere buscar otro valor (SI/NO): ");
                                        opcionRepetir = imprimirOpciones();
                                    }
                                } else {
                                    System.out.println(
                                            "\nValor no encontrado en la tabla. Desea buscar otro valor? (SI/NO): ");
                                    opcionRepetir = imprimirOpciones();
                                    while (opcionRepetir != 1 && opcionRepetir != 2) {
                                        System.out.println(
                                                "\nOpcion incorrecta. Indique si quiere buscar otro valor (SI/NO): ");
                                        opcionRepetir = imprimirOpciones();
                                    }
                                }
                            } while (opcionRepetir == 1);
                        } while (flag);
                        break;
                    // Borrar el elemento
                    case 3:
                        do {
                            flag = false;
                            ClearConsole();
                            System.out.println("\nBORRADO DE VALORES DE TABLA HASH\n");
                            do {

                                System.out.println("\nIngrese el valor que se desea borrar de la tabla (6 digitos): ");
                                dato = entradaScan.nextLine();
                                if (THL.buscar(Integer.parseInt(dato))) {
                                    // Se busca el valor en la tabla, solo para indicarle al usuario si existe o no
                                    System.out.println(
                                            "\nSe encontró el valor " + dato
                                                    + " en la tabla hash. Se borrará el dato.\n");
                                    THL.eliminar(Integer.parseInt(dato));
                                    System.out.println("\nValor " + dato
                                            + " borrado. Indique si quiere borrar otro valor (SI/NO): \n");
                                    opcionRepetir = imprimirOpciones();
                                    while (opcionRepetir != 1 && opcionRepetir != 2) {
                                        System.out.println(
                                                "\nOpcion incorrecta. Indique si quiere buscar otro valor (SI/NO): ");
                                        opcionRepetir = imprimirOpciones();
                                    }
                                } else {
                                    System.out.println(
                                            "\nValor no encontrado en la tabla, por lo tanto no puede ser borrado. Indique si quiere borrar otro valor (SI/NO): \n");
                                    opcionRepetir = imprimirOpciones();
                                    while (opcionRepetir != 1 && opcionRepetir != 2) {
                                        System.out.println(
                                                "\nOpcion incorrecta. Indique si quiere buscar otro valor (SI/NO): ");
                                        opcionRepetir = imprimirOpciones();
                                    }
                                }
                            } while (opcionRepetir == 1);
                        } while (flag);
                        break;
                }
            } while (opcionMenu != 4);
        }
    }
}