
package es.ad.ejercicios.alumno;

import es.ad.ejercicios.Ejercicio7;
import java.nio.file.Path;


public class Ejercicio7Impl implements Ejercicio7 {
    /** Ruta normalizada. */
    @Override
    public Path normalizada(Path ruta) {
        return ruta.normalize();
    }
    /** Nombre del fichero. */
    @Override
    public Path nombreFichero(Path ruta) {
        return ruta.normalize().getFileName();
    }
    /** Directorio que contiene al fichero. */
    @Override
    public Path directorioPadre(Path ruta) {
        return ruta.normalize().getParent();
    }
    /** Indica si la ruta (tal como se recibe) es absoluta. */
    @Override
    public boolean esAbsoluta(Path ruta) {
        return ruta.isAbsolute();
    }
    /** Misma ruta normalizada, pero con otra extensión (p. ej. {@code "txt"}, sin punto). */
    @Override
    public Path conExtension(Path ruta, String extension) {
        Path normalizada = ruta.normalize();
        Path nombre = normalizada.getFileName();

        String fichero = nombre.toString();
        int punto = fichero.lastIndexOf('.');

        String base = (punto > 0) ? fichero.substring(0, punto) : fichero;
        String nuevoNombre = base + "." + extension;

        return normalizada.resolveSibling(nuevoNombre);
    }
}
