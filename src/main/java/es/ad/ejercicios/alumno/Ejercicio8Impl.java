
package es.ad.ejercicios.alumno;

import es.ad.ejercicios.Ejercicio8;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class Ejercicio8Impl implements Ejercicio8 {
    /** Crea el directorio (y los padres que falten). Devuelve si el resultado es un directorio. */

    @Override
    public boolean crearDirectorio(Path directorio) throws IOException {
        Files.createDirectories(directorio);
        return Files.isDirectory(directorio);
    }
    /** Crea el fichero con las líneas indicadas. */

    @Override
    public void crearFichero(Path fichero, List<String> lineas) throws IOException {
        Files.write(fichero, lineas);
    }
    /** Lee todas las líneas del fichero. */

    @Override
    public List<String> leerLineas(Path fichero) throws IOException {
        return Files.readAllLines(fichero);
    }
    /** Copia SIN sobrescribir: si el destino existe lanza {@link java.nio.file.FileAlreadyExistsException}. */

    @Override
    public void copiar(Path origen, Path destino) throws IOException {
        Files.copy(origen, destino);
    }
    /** Mueve el fichero. */

    @Override
    public void mover(Path origen, Path destino) throws IOException {
        Files.move(origen, destino);
    }
    /** Indica si existe. */
    @Override
    public boolean existe(Path ruta) {
        return Files.exists(ruta);
    }
    /** Borra el fichero si existe. Devuelve {@code true} si lo borró, {@code false} si no existía. */

    @Override
    public boolean borrar(Path fichero) throws IOException {
        return Files.deleteIfExists(fichero);
    }
}
