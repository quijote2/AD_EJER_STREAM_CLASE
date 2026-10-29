package es.ad.ejercicios.alumno;

import java.util.List;
import java.util.Scanner;

public class Main1 {

    public static void main(String[] args) {

        Ejercicio6Impl ejercicio = new Ejercicio6Impl();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce un número: ");
        int n = scanner.nextInt();

        List<Integer> primos = ejercicio.primosHasta(n);

        System.out.println("Números primos hasta " + n + ":");
        System.out.println(primos);

        scanner.close();
    }
}