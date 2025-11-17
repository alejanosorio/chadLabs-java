package gestor_experimentos.model;

import java.util.List;

public  abstract class Experimento {
    protected String nombre;
    protected int duracion;
    protected boolean exito;

    public Experimento(String nombre, int duracion, boolean exito) {
        this.nombre = nombre != null ? nombre.trim() : "";
        this.duracion = Math.max(0, duracion);
        this.exito = exito;
    }

    public String getNombre() { return nombre; }
    public int getDuracion() { return duracion; }
    public boolean isExito() { return exito; }

    public abstract String tipo();
    public abstract List<Investigador> getInvestigadores();

    @Override
    public String toString() {
        return String.format("[%s] %s - %d min - %s",
                tipo(), nombre, duracion, exito ? "EXITO" : "FALLO");
    }
}
