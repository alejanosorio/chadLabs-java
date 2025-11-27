package chadLabs.service;
import java.util.ArrayList;
import java.util.List;
import chadLabs.model.Experimento;
import chadLabs.model.Investigador;
import chadLabs.utils.InputUtils;

public class GestorMenuImp implements GestorService{
    private final InvestigadorService investigadorService = new InvestigadorImp();
    private final ExperimentoService experimentoService = new ExperimentoImp();
    private final ReporteService reporteService = new ReporteImp(experimentoService, investigadorService);

    @Override
    public void iniciarMenu() {
        int opcion;
        do {
            mostrarMenu();
            opcion = InputUtils.leerEntero("Opción: ");
            switch (opcion) {
                case 1 -> registrarInvestigadorFlow();
                case 2 -> registrarExperimentoFlow();
                case 3 -> listarExperimentos();
                case 4 -> reporteService.mostrarReporte();
                case 5 -> generarReporteResumen();
                case 6 -> exportarCSVFlow();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("\n=== GESTOR DE EXPERIMENTOS CHAD ===");
        System.out.println("1. Registrar investigador");
        System.out.println("2. Registrar experimento");
        System.out.println("3. Listar experimentos");
        System.out.println("4. Mostrar reporte detallado");
        System.out.println("5. Mostrar resumen rápido");
        System.out.println("6. Exportar investigadores a CSV");
        System.out.println("0. Salir");
    }

    /* ======= Flujos públicos delegados a servicios, divididos en métodos privados pequeños ======= */

    private void registrarInvestigadorFlow() {
        String nombre = InputUtils.leerTexto("Nombre: ");
        int edad = InputUtils.leerEntero("Edad: ");
        investigadorService.registrarInvestigador(nombre, edad);
        System.out.println("Investigador registrado: " + nombre);
    }

    private void registrarExperimentoFlow() {
        if (investigadorService.listar().isEmpty()) {
            System.out.println("Primero registre al menos un investigador.");
            return;
        }
        int tipo = pedirTipoExperimento();
        if (tipo == 1) registrarQuimicoFlow();
        else if (tipo == 2) registrarFisicoFlow();
        else System.out.println("Tipo inválido.");
    }

    private int pedirTipoExperimento() {
        System.out.println("Tipo de experimento:\n1) Químico\n2) Físico");
        return InputUtils.leerEntero("Seleccione: ");
    }

    /* --- Químico --- */
    private void registrarQuimicoFlow() {
        String nombre = InputUtils.leerTexto("Nombre experimento químico: ");
        int dur = InputUtils.leerEntero("Duración (min): ");
        boolean exito = InputUtils.leerBoolean("¿Fue exitoso?");
        String reactivo = InputUtils.leerTexto("Tipo de reactivo: ");
        Investigador inv = pedirInvestigadorPorNombre();
        if (inv == null) return;
        experimentoService.registrarExperimento(experimentoService.crearQuimico(nombre, dur, exito, reactivo, inv));
        inv.incrementarExperimentos();
        System.out.println("Experimento químico registrado.");
    }

    /* --- Físico --- */
    private void registrarFisicoFlow() {
        String nombre = InputUtils.leerTexto("Nombre experimento físico: ");
        int dur = InputUtils.leerEntero("Duración (min): ");
        boolean exito = InputUtils.leerBoolean("¿Fue exitoso?");
        String instrumento = InputUtils.leerTexto("Instrumento: ");
        List<Investigador> invs = pedirVariosInvestigadores();
        if (invs.isEmpty()) {
            System.out.println("Debe asignar al menos un investigador.");
            return;
        }
        experimentoService.registrarExperimento(experimentoService.crearFisico(nombre, dur, exito, instrumento, invs));
        invs.forEach(Investigador::incrementarExperimentos);
        System.out.println("Experimento físico registrado.");
    }

    private Investigador pedirInvestigadorPorNombre() {
        String nombre = InputUtils.leerTexto("Ingrese nombre del investigador: ");
        Investigador inv = investigadorService.buscarPorNombre(nombre);
        if (inv == null) {
            System.out.println("Investigador no encontrado. Regístrelo primero.");
        }
        return inv;
    }

    private List<Investigador> pedirVariosInvestigadores() {
        List<Investigador> seleccion = new ArrayList<>();
        System.out.println("Ingrese los nombres de los investigadores (escriba 'fin' para terminar):");
        while (true) {
            String nombre = InputUtils.leerTexto("→ Nombre: ");
            if (nombre.equalsIgnoreCase("fin")) break;
            if (nombre.isBlank()) {
                System.out.println("Nombre vacío, intente nuevamente.");
                continue;
            }
            Investigador inv = investigadorService.buscarPorNombre(nombre);
            if (inv == null) {
                System.out.println("Investigador no encontrado: " + nombre);
                continue;
            }
            seleccion.add(inv);
        }
        return seleccion;
    }

    private void listarExperimentos() {
        List<Experimento> exps = experimentoService.listar();
        if (exps.isEmpty()) {
            System.out.println("No hay experimentos.");
            return;
        }
        System.out.println("--- Experimentos registrados ---");
        exps.forEach(System.out::println);
    }

    private void generarReporteResumen() {
        reporteService.mostrarReporte();
    }

    private void exportarCSVFlow() {
        String ruta = InputUtils.leerTexto("Nombre archivo CSV (ej: investigadores.csv): ");
        reporteService.exportarInvestigadoresCSV(ruta);
    }
}

