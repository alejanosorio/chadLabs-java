package gestor_experimentos.service;
import gestor_experimentos.model.Investigador;


import java.util.ArrayList;
import java.util.List;

public class  InvestigadorImp  implements InvestigadorService{
    private final List<Investigador> investigadores = new ArrayList<>();

    @Override
    public Investigador registrarInvestigador(String nombre, int edad) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre inválido");
        }
        Investigador exists = buscarPorNombre(nombre);
        if (exists != null) {
            return exists;
        }
        Investigador inv = new Investigador(nombre.trim(), Math.max(0, edad));
        investigadores.add(inv);
        return inv;
    }

    @Override
    public Investigador buscarPorNombre(String nombre) {
        if (nombre == null) return null;
        for (Investigador i : investigadores) {
            if (i.getNombre().equalsIgnoreCase(nombre.trim())) return i;
        }
        return null;
    }

    @Override
    public List<Investigador> listar() {
        return new ArrayList<> (investigadores);
    }
}
