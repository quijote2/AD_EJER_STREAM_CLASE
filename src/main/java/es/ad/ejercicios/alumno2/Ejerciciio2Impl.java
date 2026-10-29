package es.ad.ejercicios.alumno2;

import es.ad.ejercicios.Ejercicio2;

import java.util.List;

public class Ejerciciio2Impl implements Ejercicio2 {

    @Override
    public int contarCiudades(List<String> ciudades) {
        return (int)ciudades.stream()
                .count();
    }

    @Override
    public int contarMasLargasQue(List<String> ciudades, int minimo) {
        return (int)ciudades.stream()
                .filter(ciudad->ciudad.length()>minimo)
                .count();
    }

    @Override
    public int sumaLongitudes(List<String> ciudades) {
        return ciudades.stream()
                .mapToInt(ciudad->ciudad.length())
                .sum();
    }
}
