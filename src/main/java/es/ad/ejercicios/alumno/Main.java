package es.ad.ejercicios.alumno;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {

        Ejercicio10Impl ejercicio = new Ejercicio10Impl();

        // Cambia estas rutas según dónde tengas los archivos.
        Path csv = Path.of("ventas.csv");
        Path salida = Path.of("informe.txt");

        try {
            System.out.println("===== PROCESAMIENTO DE VENTAS =====");

            System.out.println("Número de ventas: "
                    + ejercicio.numeroVentas(csv));

            System.out.println("Unidades totales: "
                    + ejercicio.unidadesTotales(csv));

            System.out.println("Ingresos totales: "
                    + ejercicio.ingresosTotales(csv));

            System.out.println("\n===== INGRESOS POR TIENDA =====");
            ejercicio.ingresosPorTienda(csv)
                    .forEach((tienda, ingresos) ->
                            System.out.println(tienda + ": " + ingresos));

            System.out.println("\n===== PRODUCTO MÁS VENDIDO =====");
            System.out.println("Producto: "
                    + ejercicio.productoMasVendido(csv));

            System.out.println("Unidades vendidas: "
                    + ejercicio.unidadesProductoMasVendido(csv));

            System.out.println("\n===== VENTA DE MAYOR IMPORTE =====");
            System.out.println("ID de la venta: "
                    + ejercicio.idVentaMayorImporte(csv));

            System.out.println("\n===== INGRESOS POR MES =====");
            ejercicio.ingresosPorMes(csv)
                    .forEach((mes, ingresos) ->
                            System.out.println("Mes " + mes + ": " + ingresos));

            System.out.println("\n===== CATEGORÍA CON MÁS INGRESOS =====");
            System.out.println(ejercicio.categoriaMasIngresos(csv));

            System.out.println("\n===== VENTAS MAYORES DE 500 =====");
            ejercicio.idsVentasMayoresDe(
                    csv, new BigDecimal("500")
            ).forEach(System.out::println);

            // Genera el informe sin sobrescribir uno existente.
            ejercicio.escribirProcesamiento(csv, salida, false);

            System.out.println("\nInforme creado correctamente en: "
                    + salida.toAbsolutePath());

        } catch (IOException e) {
            System.err.println("Error al leer o escribir archivos: "
                    + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error al procesar las ventas: "
                    + e.getMessage());
        }
    }
}