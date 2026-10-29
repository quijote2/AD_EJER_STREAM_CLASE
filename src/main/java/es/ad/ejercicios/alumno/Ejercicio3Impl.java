package es.ad.ejercicios.alumno;

import es.ad.ejercicios.Ejercicio3;
import java.util.List;

public class Ejercicio3Impl implements Ejercicio3 {
    /** Ciudades de más de 6 letras, en mayúsculas, respetando el orden original. */
    @Override
    public List<String> largasEnMayusculas(List<String> ciudades) {
        return ciudades.stream()
                .filter(ciudad -> ciudad.length() > 6)
                .map(String::toUpperCase)
                .toList();
    }
}