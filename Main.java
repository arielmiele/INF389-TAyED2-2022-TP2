/*
 * INF389 - Taller de Algoritmos y Estructura de Datos II
 * Trabajo Práctico N° 2
 * Nombre: Ariel Gerardo Miele
 * Legajo: VINF011482
 * DNI: 34.434.704
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import EstructurasDatos.*;

public class Main {

    static Scanner entradaScan = new Scanner(System.in);

    public static void pruebaLlenado() {
        TablaHashCuadratica THC = new TablaHashCuadratica();
        TablaHashLineal THL = new TablaHashLineal();
        TablaHashAbierto THA = new TablaHashAbierto();

        Recurso[] recursos = new Recurso[10];

        recursos[0] = new Recurso(116252);
        recursos[1] = new Recurso(141125);
        recursos[2] = new Recurso(342753);
        recursos[3] = new Recurso(441553);
        recursos[4] = new Recurso(152169);
        recursos[5] = new Recurso(125151);
        recursos[6] = new Recurso(225643);
        recursos[7] = new Recurso(351153);
        recursos[8] = new Recurso(334423);
        recursos[9] = new Recurso(353524);

        System.out.println("\nSe agregan los recursos en la tabla Hash con manejo de colisiones cuadráticas.\n");
        for (int i = 0; i < recursos.length; i++) {
            THC.insertar(recursos[i]);
        }

        System.out.println("\nSe agregan los recursos en la tabla Hash con manejo de colisiones lineales.\n");
        for (int i = 0; i < recursos.length; i++) {
            THL.insertar(recursos[i]);
        }

        System.out.println("\nSe agregan los recursos en la tabla Hash con hashing abierto.\n");
        for (int i = 0; i < recursos.length; i++) {
            THA.insertar(recursos[i]);
        }

        System.out.println("Tabla hash sondeo cuadratico:\n");
        THC.imprimirTablaHash();
        System.out.println("\nTabla hash sondelo lineal:\n");
        THL.imprimirTablaHash();

    }

    public static void main(String[] args) {
        String dato;
        boolean salir = false;

        do {
            System.out.println("\nOpciones:\n" +
                    "Seleccione el metodo deseado:\n" +
                    "1 - Operar con tabla hash de manejo de colisiones cuadratico\n" +
                    "2 - Operar con tabla hash de manejo de colisiones con sondeo lineal\n" +
                    "3 - Operar con tabla hash de manejo de colisiones de direccionamiento cerrado (hashing abierto)\n"
                    +
                    "4 - Ejecución de Prueba\n" +
                    "5 - Salir\n");

            System.out.print(">> ");
            dato = entradaScan.nextLine();

            try {
                switch (dato) {
                    case "1":
                        System.out.println("\nCaso de hash cuadratico.");
                        programa(TablaHashCuadratica.class);
                        break;
                    case "2":
                        System.out.println("\nCaso de hash lineal.");
                        programa(TablaHashLineal.class);
                        break;
                    case "3":
                        System.out.println("\nCaso de hash abierto.");
                        programa(TablaHashAbierto.class);
                        break;
                    case "4":
                        pruebaLlenado();
                        break;
                    case "5":
                        salir = true;
                        break;
                    default:
                        System.out.println("\nOpcion incorrecta, por favor seleccione una opcion del 1 al 5\n");
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

        entradaScan.close();
        System.out.println("\nPrograma finalizado\n");
    }

    public static int imprimirMenu() {
        String dato;
        System.out.println("\nOpciones:" +
                "\n1 - Insertar elemento\n" +
                "2 - Buscar elemento\n" +
                "3 - Borrar elemento\n" +
                "4 - Atrás\n");

        System.out.print(">> ");
        dato = entradaScan.nextLine();
        return Integer.parseInt(dato);
    }

    public static void programa(Class claseHash)
            throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        System.out.println("\nSeleccione el tamaño de la tabla hash (escriba 'D' para default = 10): ");
        String dato;
        dato = entradaScan.nextLine();

        int opcionMenu;
        String opcionRepetir = "SI";
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
                            do {
                                System.out
                                        .println("\nIngrese el valor que se desea ingresar en la tabla (6 digitos): ");
                                dato = entradaScan.nextLine();
                                THA.insertar(Integer.parseInt(dato));
                                System.out.println(
                                        "\nEl dato ha sido ingresado a la tabla. Desea ingresar otro valor? (SI/NO): \n");
                                opcionRepetir = entradaScan.nextLine();
                                opcionRepetir.toUpperCase();
                                while (opcionRepetir != "SI" && opcionRepetir != "NO") {
                                    System.out.println(
                                            "\nOpcion incorrecta. Indique si quiere ingresar otro valor (SI/NO): ");
                                    opcionRepetir = entradaScan.nextLine();
                                    opcionRepetir.toUpperCase();
                                }
                            } while (opcionRepetir == "SI");
                        } while (flag);
                        break;
                    // Buscar el elemento
                    case 2:
                        do {
                            flag = false;
                            do {
                                System.out
                                        .println("\nIngrese el valor que se desea buscar en la tabla (6 digitos): ");
                                dato = entradaScan.nextLine();
                                if (THA.encontrar(Integer.parseInt(dato))) {
                                    System.out.println("\nSe encontro el valor " + dato
                                            + "en la tabla hash. Desea buscar otro valor? (SI/NO): \n");
                                    opcionRepetir = entradaScan.nextLine();
                                    opcionRepetir.toUpperCase();
                                    while (opcionRepetir != "SI" && opcionRepetir != "NO") {
                                        System.out.println(
                                                "\nOpcion incorrecta. Indique si quiere buscar otro valor (SI/NO): ");
                                        opcionRepetir = entradaScan.nextLine();
                                        opcionRepetir.toUpperCase();
                                    }
                                } else {
                                    System.out.println(
                                            "\nValor no encontrado en la tabla. Desea buscar otro valor? (SI/NO): ");
                                    opcionRepetir = entradaScan.nextLine();
                                    opcionRepetir.toUpperCase();
                                    while (opcionRepetir != "SI" && opcionRepetir != "NO") {
                                        System.out.println(
                                                "\nOpcion incorrecta. Indique si quiere buscar otro valor (SI/NO): ");
                                        opcionRepetir = entradaScan.nextLine();
                                        opcionRepetir.toUpperCase();
                                    }
                                }
                            } while (opcionRepetir == "SI");
                        } while (flag);
                        break;
                    // Borrar el elemento
                    case 3:
                        do {
                            flag = false;
                            System.out.println("\nIngrese el valor que se desea borrar de la tabla (6 digitos): ");
                            dato = entradaScan.nextLine();
                            if (THA.encontrar(Integer.parseInt(dato))) {
                                // Se busca el valor en la tabla, solo para indicarle al usuario si existe o no
                                System.out.println(
                                        "\nSe encontró el valor " + dato + " en la tabla hash. Se borrará el dato.\n");
                                THA.eliminar(Integer.parseInt(dato));
                                System.out.println("\nValor " + dato + " borrado.\n");
                            } else {
                                System.out.println(
                                        "\nValor no encontrado en la tabla, por lo tanto no puede ser borrado.\n");
                            }
                        } while (flag);
                        break;
                }
            } while (opcionMenu != 4);

        } else if (claseHash == TablaHashCuadratica.class)

        {
            // Selección del caso Tabla Hash Sondeo Cuadrático
            // Creación de la tabla con hash sondeo cuadratico
            TablaHashCuadratica TH = new TablaHashCuadratica(Integer.parseInt(dato));
            // Imprime menú y retorna selección
            opcionMenu = imprimirMenu();

        } else if (claseHash == TablaHashLineal.class) {
            // Selección del caso Tabla Hash Sondeo Lineal
            // Creación de la tabla con hash sondeo lineal
            TablaHashLineal TH = new TablaHashLineal(Integer.parseInt(dato));
            // Imprime menú y retorna selección
            opcionMenu = imprimirMenu();
        }
    }
}
