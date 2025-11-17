package gestor_experimentos.service;
import gestor_experimentos.model.Experimento;
import gestor_experimentos.model.Investigador;
import gestor_experimentos.utils.CsvUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class ReporteImp implements ReporteService {
    private final ExperimentoService experimentoService;
    private final InvestigadorService investigadorService;

    public ReporteImp (ExperimentoService experimentoService, InvestigadorService investigadorService) {
        this.experimentoService = experimentoService;
        this.investigadorService = investigadorService;
    }



    @Override
    public void mostrarReporte() {
        List<Experimento> exps = experimentoService.listar();
        if (exps.isEmpty()) {
            System.out.println("No hay experimentos registrados.");
            return;
        }
        long exitos = exps.stream().filter(Experimento::isExito).count();
        double promedio = exps.stream().mapToInt(Experimento::getDuracion).average().orElse(0.0);
        double porcentaje = (double) exitos / exps.size() * 100.0;

        Experimento masLargo = exps.stream().max(Comparator.comparingInt(Experimento::getDuracion)).orElse(null);
        Map<Investigador, Long> cuenta = exps.stream()
                .flatMap(e -> e.getInvestigadores().stream())
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        Investigador masActivo = cuenta.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        System.out.println("--- REPORTE ---");
        System.out.println("Total experimentos: " + exps.size());
        System.out.println("Exitos: " + exitos + " | Fallos: " + (exps.size() - exitos));
        System.out.printf("Promedio duración: %.2f min%n", promedio);
        System.out.printf("Porcentaje de éxito: %.2f %%n", porcentaje);
        if (masLargo != null) System.out.println("Experimento más largo: " + masLargo.getNombre() + " (" + masLargo.getDuracion() + " min)");
        if (masActivo != null) System.out.println("Investigador más activo: " + masActivo.getNombre());
    }

    @Override
    public void exportarInvestigadoresCSV(String ruta) {
        List<Investigador> invs = investigadorService.listar();
        List<Experimento> exps = experimentoService.listar();
        CsvUtils.exportarInvestigadores(ruta, invs, exps);
    }

}
