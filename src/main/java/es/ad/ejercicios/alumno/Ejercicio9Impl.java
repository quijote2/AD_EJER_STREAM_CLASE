
package es.ad.ejercicios.alumno;

import es.ad.ejercicios.Ejercicio9;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ejercicio9Impl implements Ejercicio9 {
/**
 * Grupos de anagramas formados por dos o más palabras.
 * El orden de los grupos y de las palabras dentro de cada grupo es indiferente.*/
    @Override
    public List<List<String>> gruposDeAnagramas(Path fichero) throws IOException {
        try (var lineas = Files.lines(fichero)) {
            Map<String, List<String>> grupos = lineas
                    .map(String::trim)
                    .filter(palabra -> !palabra.isEmpty())
                    .collect(Collectors.groupingBy(
                            palabra -> {
                                char[] letras = palabra.toCharArray();
                                Arrays.sort(letras);
                                return new String(letras);
                            }
                    ));

            return grupos.values().stream()
                    .filter(grupo -> grupo.size() >= 2)
                    .toList();
        }
    }
}
