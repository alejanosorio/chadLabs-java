package chadLabs.utils;

import chadLabs.model.Experimento;
import chadLabs.model.Investigador;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CsvUtils {

    public static void exportarInvestigadores(String ruta, List<Investigador> investigadores, List<Experimento> experimentos) {
        Map<Investigador, Long> conteo = experimentos.stream()
                .flatMap(e -> e.getInvestigadores().stream())
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()));

        try (FileWriter fw = new FileWriter(ruta)) {
            fw.append("nombre,edad,cantidad_experimentos\n");
            for (Investigador inv : investigadores) {
                long cnt = conteo.getOrDefault(inv, 0L);
                // simple escaping de comillas
                String nombre = inv.getNombre().replace("\"", "\"\"");
                fw.append(String.format("\"%s\",%d,%d\n", nombre, inv.getEdad(), cnt));
            }
            System.out.println("CSV exportado en: " + ruta);
        } catch (IOException e) {
            System.out.println("Error escribiendo CSV: " + e.getMessage());
        }
    }
}
