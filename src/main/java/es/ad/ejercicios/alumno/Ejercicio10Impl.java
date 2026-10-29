
package es.ad.ejercicios.alumno;

import es.ad.ejercicios.Ejercicio10;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ejercicio10Impl implements Ejercicio10 {

    // Representa una fila del CSV.
    private static class Venta {
        int id;
        String fecha;
        String tienda;
        String producto;
        String categoria;
        int unidades;
        BigDecimal precio;

        Venta(String[] columnas) {
            id = Integer.parseInt(columnas[0].trim());
            fecha = columnas[1].trim();
            tienda = columnas[2].trim();
            producto = columnas[3].trim();
            categoria = columnas[4].trim();
            unidades = Integer.parseInt(columnas[5].trim());
            precio = new BigDecimal(columnas[6].trim());
        }

        BigDecimal importe() {
            return precio.multiply(BigDecimal.valueOf(unidades));
        }
    }

    // Lee el CSV y convierte sus filas en objetos Venta.
    private List<Venta> leerVentas(Path csv) throws IOException {
        List<String> lineas = Files.readAllLines(csv);
        List<Venta> ventas = new ArrayList<>();

        for (int i = 1; i < lineas.size(); i++) {
            String linea = lineas.get(i).trim();

            if (!linea.isEmpty()) {
                ventas.add(new Venta(linea.split(",", -1)));
            }
        }

        return ventas;
    }

    // a) Número de ventas.
    @Override
    public int numeroVentas(Path csv) throws IOException {
        return leerVentas(csv).size();
    }

    // a) Suma de las unidades vendidas.
    @Override
    public int unidadesTotales(Path csv) throws IOException {
        return leerVentas(csv).stream()
                .mapToInt(v -> v.unidades)
                .sum();
    }

    // b) Ingresos totales.
    @Override
    public BigDecimal ingresosTotales(Path csv) throws IOException {
        return leerVentas(csv).stream()
                .map(Venta::importe)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // c) Ingresos agrupados por tienda.
    @Override
    public Map<String, BigDecimal> ingresosPorTienda(Path csv)
            throws IOException {
        return leerVentas(csv).stream()
                .collect(Collectors.groupingBy(
                        v -> v.tienda,
                        LinkedHashMap::new,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                Venta::importe,
                                BigDecimal::add
                        )
                ));
    }

    // d) Producto más vendido por número de unidades.
    @Override
    public String productoMasVendido(Path csv) throws IOException {
        Map<String, Integer> unidades = unidadesPorProducto(leerVentas(csv));

        return unidades.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() ->
                        new IllegalArgumentException("No hay ventas"))
                .getKey();
    }

    @Override
    public int unidadesProductoMasVendido(Path csv) throws IOException {
        Map<String, Integer> unidades = unidadesPorProducto(leerVentas(csv));

        return unidades.values().stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElseThrow(() ->
                        new IllegalArgumentException("No hay ventas"));
    }

    private Map<String, Integer> unidadesPorProducto(List<Venta> ventas) {
        return ventas.stream()
                .collect(Collectors.groupingBy(
                        v -> v.producto,
                        LinkedHashMap::new,
                        Collectors.summingInt(v -> v.unidades)
                ));
    }

    // e) ID de la venta con mayor importe.
    @Override
    public int idVentaMayorImporte(Path csv) throws IOException {
        return leerVentas(csv).stream()
                .max((a, b) -> a.importe().compareTo(b.importe()))
                .orElseThrow(() ->
                        new IllegalArgumentException("No hay ventas"))
                .id;
    }

    // f) Ingresos agrupados por mes.
    @Override
    public Map<String, BigDecimal> ingresosPorMes(Path csv)
            throws IOException {
        return leerVentas(csv).stream()
                .collect(Collectors.groupingBy(
                        v -> v.fecha.substring(5, 7),
                        LinkedHashMap::new,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                Venta::importe,
                                BigDecimal::add
                        )
                ));
    }

    // g) Categoría con más ingresos.
    @Override
    public String categoriaMasIngresos(Path csv) throws IOException {
        Map<String, BigDecimal> ingresos = leerVentas(csv).stream()
                .collect(Collectors.groupingBy(
                        v -> v.categoria,
                        LinkedHashMap::new,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                Venta::importe,
                                BigDecimal::add
                        )
                ));

        return ingresos.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() ->
                        new IllegalArgumentException("No hay ventas"))
                .getKey();
    }

    // h) IDs de ventas cuyo importe es estrictamente superior al límite.
    @Override
    public List<Integer> idsVentasMayoresDe(
            Path csv, BigDecimal limite) throws IOException {

        return leerVentas(csv).stream()
                .filter(v -> v.importe().compareTo(limite) > 0)
                .map(v -> v.id)
                .collect(Collectors.toList());
    }

    // Escribe el informe con los resultados de todos los apartados.
    @Override
    public void escribirProcesamiento(
            Path csv, Path salida, boolean sobrescribir) throws IOException {

        if (!sobrescribir && Files.exists(salida)) {
            throw new FileAlreadyExistsException(salida.toString());
        }

        List<String> lineas = new ArrayList<>();

        lineas.add("PROCESAMIENTO DE VENTAS");
        lineas.add("=======================");
        lineas.add("Número de ventas: " + numeroVentas(csv));
        lineas.add("Unidades totales: " + unidadesTotales(csv));
        lineas.add("Ingresos totales: " + formato(ingresosTotales(csv)));
        lineas.add("");

        lineas.add("INGRESOS POR TIENDA");
        ingresosPorTienda(csv).forEach((tienda, importe) ->
                lineas.add(tienda + ": " + formato(importe)));
        lineas.add("");

        lineas.add("PRODUCTO MÁS VENDIDO: " + productoMasVendido(csv));
        lineas.add("Unidades vendidas: "
                + unidadesProductoMasVendido(csv));
        lineas.add("ID de la venta de mayor importe: "
                + idVentaMayorImporte(csv));
        lineas.add("");

        lineas.add("INGRESOS POR MES");
        ingresosPorMes(csv).forEach((mes, importe) ->
                lineas.add(mes + ": " + formato(importe)));
        lineas.add("");

        lineas.add("CATEGORÍA CON MÁS INGRESOS: "
                + categoriaMasIngresos(csv));
        lineas.add("");

        lineas.add("VENTAS MAYORES DE 500");
        idsVentasMayoresDe(csv, new BigDecimal("500")).forEach(id ->
                lineas.add(String.valueOf(id)));

        // CREATE_NEW impide sobrescribir incluso si el fichero
        // aparece después de la comprobación inicial.
        if (sobrescribir) {
            Files.write(
                    salida,
                    lineas,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING,
                    StandardOpenOption.WRITE
            );
        } else {
            Files.write(
                    salida,
                    lineas,
                    StandardOpenOption.CREATE_NEW,
                    StandardOpenOption.WRITE
            );
        }
    }

    private String formato(BigDecimal importe) {
        return importe.setScale(2, java.math.RoundingMode.HALF_UP)
                .toPlainString();
    }
    
}

