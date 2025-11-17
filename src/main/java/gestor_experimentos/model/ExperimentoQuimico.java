package gestor_experimentos.model;

import java.util.Collections;
import java.util.List;

public class ExperimentoQuimico extends Experimento {
    private String tipoReactivo;
    private Investigador investigador;

    public ExperimentoQuimico(String nombre, int duracion, boolean exito, String tipoReactivo, Investigador investigador) {
        super(nombre, duracion, exito);
        this.tipoReactivo = tipoReactivo != null ? tipoReactivo.trim() : "";
        this.investigador = investigador;
    }

    public String getTipoReactivo() { return tipoReactivo; }
    public Investigador getInvestigador() { return investigador; }

    @Override
    public String tipo() { return "Químico"; }

    @Override
    public List<Investigador> getInvestigadores() {
        return Collections.singletonList(investigador);
    }

    @Override
    public String toString() {
        return super.toString() + " | Reactivo: " + tipoReactivo + " | Investigador: " + investigador.getNombre();
    }
}
