
package es.ad.ejercicios.alumno;

import es.ad.ejercicios.Ejercicio6;
import java.util.List;
import java.util.stream.IntStream;

public class Ejercicio6Impl implements Ejercicio6 {
    /** Primos menores o iguales que {@code n}, en orden ascendente. */
    @Override
    public List<Integer> primosHasta(int n) {
        return IntStream.rangeClosed(2, n)
                .filter(num -> IntStream.rangeClosed(2, (int) Math.sqrt(num))
                        .noneMatch(divisor -> num % divisor == 0))
                .boxed()
                .toList();
    }
}
