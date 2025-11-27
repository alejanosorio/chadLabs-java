package chadLabs.service;

import chadLabs.model.Experimento;
import chadLabs.model.ExperimentoFisico;
import chadLabs.model.ExperimentoQuimico;
import chadLabs.model.Investigador;

import java.util.List;

public  interface  ExperimentoService {
    ExperimentoQuimico crearQuimico(String nombre, int duracion, boolean exito, String tipoReactivo, Investigador investigador);
    ExperimentoFisico crearFisico(String nombre, int duracion, boolean exito, String instrumento, List<Investigador> investigadores);
    void registrarExperimento(Experimento exp);
    List<Experimento> listar();
}
