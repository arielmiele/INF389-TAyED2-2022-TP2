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
                    "5 - Salir");

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

        if (claseHash == TablaHashAbierto.class) {
            TablaHashAbierto TH = new TablaHashAbierto(Integer.parseInt(dato));
        } else if (claseHash == TablaHashCuadratica.class) {
            TablaHashCuadratica TH = new TablaHashCuadratica(Integer.parseInt(dato));
        } else if (claseHash == TablaHashLineal.class) {
            TablaHashLineal TH = new TablaHashLineal(Integer.parseInt(dato));
        }

        int opcion;
        boolean otro;
        do {
            opcion = imprimirMenu();
            switch (opcion) {
                case 1:
                    do {
                        otro = false;

                    } while (otro);
                    break;
                case 2:
                    do {
                        otro = false;
                        tabla.buscar(obtenerObjetoHasheableConLeyenda(
                                "\n\033[1m" + "Ingrese el valor que desea buscar" + "\033[0m"));
                        System.out.println("\n\033[1m" + "Desea buscar otro? (s/N)" + "\033[0m");
                        System.out.print("\033[1m" + "> " + "\033[0m");
                        if (scanInput.nextLine().toUpperCase().equals("S")) {
                            otro = true;
                        }
                    } while (otro);
                    break;
                case 3:
                    do {
                        otro = false;
                        tabla.eliminar(obtenerObjetoHasheableConLeyenda(
                                "\n\033[1m" + "Ingrese el valor que desea eliminar" + "\033[0m"));
                        System.out.println("\n\033[1m" + "Desea borrar otro? (s/N)" + "\033[0m");
                        System.out.print("\033[1m" + "> " + "\033[0m");
                        if (scanInput.nextLine().toUpperCase().equals("S")) {
                            otro = true;
                        }
                    } while (otro);
                    break;
            }
        } while (opcion != 4);
    }

}
