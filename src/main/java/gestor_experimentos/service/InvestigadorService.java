package gestor_experimentos.service;

import gestor_experimentos.model.Investigador;

import java.util.List;

public interface InvestigadorService {

    Investigador registrarInvestigador(String nombre, int edad);
    Investigador buscarPorNombre(String nombre);
    List<Investigador> listar();
}
