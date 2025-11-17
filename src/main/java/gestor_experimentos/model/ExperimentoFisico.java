package gestor_experimentos.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExperimentoFisico extends Experimento{
    private String instrumento;
    private List<Investigador> investigadores;

    public ExperimentoFisico(String nombre, int duracion, boolean exito, String instrumento, List<Investigador> investigadores) {
        super(nombre, duracion, exito);
        this.instrumento = instrumento != null ? instrumento.trim() : "";
        this.investigadores = investigadores != null ? new ArrayList<> (investigadores) : new ArrayList<>();
    }

    public String getInstrumento() { return instrumento; }
    @Override
    public List<Investigador> getInvestigadores() { return investigadores; }

    @Override
    public String tipo() { return "Físico"; }

    @Override
    public String toString() {
        String names = investigadores.stream().map(Investigador::getNombre).collect(Collectors.joining(", "));
        return super.toString() + " | Instrumento: " + instrumento + " | Investigadores: [" + names + "]";
    }
}
