package refactorizado;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
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

    public static void rellenarPrimos(boolean[] esPrimo, int[] primos) {
        for (int i = 0, j = 0; i < esPrimo.length; i++) {
            if (esPrimo[i]) {
                primos[j++] = i;
            }
        }
    }

    public static int contarPrimos(boolean[] esPrimo) {
        int cuenta = 0;
        for (boolean b : esPrimo) {
            if (b) {
                cuenta++;
            }
        }
        return cuenta;
    }

    public static void eliminarMultiplos(int i, boolean[] esPrimo) {
        for (int j = 2 * i; j < esPrimo.length; j += i) {
            esPrimo[j] = false;
        }
    }

    public static void inicializar(boolean[] esPrimo) {
        Arrays.fill(esPrimo, true);
    }
}