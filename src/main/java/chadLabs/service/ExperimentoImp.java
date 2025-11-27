package chadLabs.service;

import chadLabs.model.*;


import java.util.ArrayList;
import java.util.List;

public class ExperimentoImp  implements ExperimentoService{
    private final List<Experimento> experimentos = new ArrayList<>();

    @Override
    public ExperimentoQuimico crearQuimico(String nombre, int duracion, boolean exito, String tipoReactivo, Investigador investigador) {
        if (investigador == null) throw new IllegalArgumentException("Investigador requerido para químico");
        return new ExperimentoQuimico (nombre, duracion, exito, tipoReactivo, investigador);
    }

    @Override
    public ExperimentoFisico crearFisico(String nombre, int duracion, boolean exito, String instrumento, List<Investigador> investigadores) {
        if (investigadores == null || investigadores.isEmpty()) throw new IllegalArgumentException("Debe haber al menos 1 investigador para físico");
        return new ExperimentoFisico (nombre, duracion, exito, instrumento, investigadores);
    }

    @Override
    public void registrarExperimento(Experimento exp) {
        experimentos.add(exp);
    }

    @Override
    public List<Experimento> listar() {
        return new ArrayList<> (experimentos);
    }
}
