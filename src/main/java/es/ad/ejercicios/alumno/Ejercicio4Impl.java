
package es.ad.ejercicios.alumno;

import es.ad.ejercicios.Ejercicio4;
import java.util.List;

public class Ejercicio4Impl implements Ejercicio4 {
    /** a) Los tres mayores valores distintos, de mayor a menor. */
    @Override
    public List<Integer> tresMayoresDistintos(List<Integer> numeros) {
        return numeros.stream()
                .distinct()
                .sorted((a, b) -> b.compareTo(a))
                .limit(3)
                .toList();
    }
    /** b) Suma de la lista. */
    @Override
    public int suma(List<Integer> numeros) {
        return numeros.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
    /** b) Media de la lista. */
    @Override
    public double media(List<Integer> numeros) {
        return numeros.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }
    /** b) Máximo de la lista. */
    @Override
    public int maximo(List<Integer> numeros) {
        return numeros.stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElseThrow(() -> new IllegalArgumentException("La lista está vacía"));
    }
}
