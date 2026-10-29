package es.ad.ejercicios.alumno2;

import es.ad.ejercicios.Ejercicio1;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

public class Ejercicio1Impl implements Ejercicio1 {
    @Override
    public void ejecutar() {
        // Predicate: las tres formas de escribir una lambda
        Predicate<Integer>esPar1=(Integer n)->n%2==0;
        Predicate<Integer>esPar2=(n)->n%2==0;
        Predicate<Integer>esPar3=n->n%2==0;

        // Comprobar si 4 y 7 son pares
        System.out.println(esPar1.test(4));
        System.out.println(esPar1.test(7));

        // Function: longitud de una cadena
        Function<String,Integer>longitud=texto->texto.length();
        System.out.println(longitud.apply("lambda"));

        // BinaryOperator: mayor de dos números
        BinaryOperator<Integer>mayor=(a,b)->a>b ? a:b;
        System.out.println(mayor.apply(3,9));

        // Comparator: comparación alfabética

        Comparator<String>comparador=(a,b)->a.compareTo(b);
        System.out.println(comparador.compare("Cadiz","Sevilla"));
    }
}
