package gestor_experimentos.service;

import gestor_experimentos.model.Experimento;
import gestor_experimentos.model.ExperimentoFisico;
import gestor_experimentos.model.ExperimentoQuimico;
import gestor_experimentos.model.Investigador;

import java.util.List;

public  interface  ExperimentoService {
    ExperimentoQuimico crearQuimico(String nombre, int duracion, boolean exito, String tipoReactivo, Investigador investigador);
    ExperimentoFisico crearFisico(String nombre, int duracion, boolean exito, String instrumento, List<Investigador> investigadores);
    void registrarExperimento(Experimento exp);
    List<Experimento> listar();
}
