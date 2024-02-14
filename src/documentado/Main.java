package documentado;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Darius Gabriel Dobre
 * @since 14/02/2024
 */
public class Main {
    /**
     * Imprime por pantalla el vector completo y luego imprime el vector con los numeros primos
     * @param args
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce el número para la criba de Erastótenes:");
        int dato = teclado.nextInt();
        int[] vector = new int[dato];
        boolean primosGenerados = false;

        System.out.println("\nVector inicial hasta :" + dato);
        mostrarVector(vector,primosGenerados);

        vector = generarPrimos(dato);
        primosGenerados = true;
        System.out.println("\nVector de primos hasta:" + dato);
        mostrarVector(vector,primosGenerados);

    }

    /**
     * Muestra el vector pasado como parametro por pantalla
     * @param vector será el vector que se utilizará para imprimirse por pantlla
     * @param primosGenerados parametro para especificar si los numeros primos ya han sido generados o no
     */
    public static void mostrarVector(int[] vector,boolean primosGenerados) {
        for (int i = 0; i < vector.length; i++) {
            if (i % 10 == 0){
                System.out.println();
            }

            if(primosGenerados){
                System.out.print(vector[i] + "\t");
            } else {
                System.out.print(i + 1 + "\t");
            }
        }
    }

    // Generar números primos de 1 a max

    /**
     * Se generarán numeros primos desde el 1 hasta el numero dados como parametro
     * @param max el numero maximo hasta el cual queremos encontrar numeros primos
     * @return devuelve el vector generado con los numeros primos o un vector vacio dependiendo del caso
     */
    public static int[] generarPrimos(int max) {
        if (max >= 2) {
            // Declaraciones
            int dim = max + 1; // Tamaño del array
            boolean[] esPrimo = new boolean[dim];
            inicializar(esPrimo);

            esPrimo[0] = esPrimo[1] = false;

            // Criba
            for (int i = 2; i < Math.sqrt(dim) + 1; i++) {
                if (esPrimo[i]) {
                // Eliminar los múltiplos de i
                    eliminarMultiplos(i, esPrimo);
                }
            }

            // ¿Cuántos primos hay?
            int cuenta = contarPrimos(esPrimo);

            // Rellenar el vector de números primos
            int[] primos = new int[cuenta];
            rellenarPrimos(esPrimo, primos);
            return primos;

        } else { // max < 2
            return new int[0];
            // Vector vacío
        }
    }

    /**
     * Se rellenará el parametro primos con todos los numeros primos que haya
     * @param esPrimo
     * @param primos
     */
    public static void rellenarPrimos(boolean[] esPrimo, int[] primos) {
        for (int i = 0, j = 0; i < esPrimo.length; i++) {
            if (esPrimo[i]) {
                primos[j++] = i;
            }
        }
    }

    /**
     * Contará cuantos numeros primos hay dentro del parametro esPrimo
     * @param esPrimo
     * @return Devuelve la cuenta de los numeros primos que se haya encontrado
     */
    public static int contarPrimos(boolean[] esPrimo) {
        int cuenta = 0;
        for (boolean b : esPrimo) {
            if (b) {
                cuenta++;
            }
        }
        return cuenta;
    }

    /**
     * Elimina los multiplos del parametro i
     * @param i
     * @param esPrimo
     */
    public static void eliminarMultiplos(int i, boolean[] esPrimo) {
        for (int j = 2 * i; j < esPrimo.length; j += i) {
            esPrimo[j] = false;
        }
    }

    /**
     * Se inicializa todos los datos que hay en el vector esPrimo como true
     * @param esPrimo
     */
    public static void inicializar(boolean[] esPrimo) {
        Arrays.fill(esPrimo, true);
    }
}