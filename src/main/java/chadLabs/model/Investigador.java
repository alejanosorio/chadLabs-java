package chadLabs.model;

public class Investigador {

        private String nombre;
        private int edad;
        private int cantidadExperimentos;

        public Investigador(String nombre, int edad) {
            this.nombre = nombre != null ? nombre.trim() : "";
            this.edad = Math.max(0, edad);
            this.cantidadExperimentos = 0;
        }

        public String getNombre() { return nombre; }
        public int getEdad() { return edad; }
        public int getCantidadExperimentos() { return cantidadExperimentos; }
        public void incrementarExperimentos() { cantidadExperimentos++; }

        @Override
        public String toString() {
            return nombre + " (edad: " + edad + ", exp: " + cantidadExperimentos + ")";
        }
    }



