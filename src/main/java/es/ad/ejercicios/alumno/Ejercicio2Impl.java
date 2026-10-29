
package es.ad.ejercicios.alumno;

import es.ad.ejercicios.Ejercicio2;
import java.util.List;

public class Ejercicio2Impl implements Ejercicio2 {
    /** a) Cuenta cuántas ciudades hay, sin bucle for ni cambiar la lista. */
    @Override
    public int contarCiudades(List<String> ciudades) {
        return (int) ciudades.stream()
                .count();
    }
    /** b) Cuenta las ciudades con más letras que {@code minimo} (la lambda debe leer {@code minimo}). */
    @Override
    public int contarMasLargasQue(List<String> ciudades, int minimo) {
        return (int) ciudades.stream()
                .filter(ciudad -> ciudad.length() > minimo)
                .count();
    }
    /** c) Suma de las longitudes, sin modificar variables desde dentro de una lambda. */
    @Override
    public int sumaLongitudes(List<String> ciudades) {
        return ciudades.stream()
                .mapToInt(ciudad -> ciudad.length())
                .sum();
    }
}
