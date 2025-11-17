package gestor_experimentos;

import gestor_experimentos.service.GestorService;
import gestor_experimentos.service.GestorImp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestorExperimentosChadApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestorExperimentosChadApplication.class, args);

    GestorService gestor = new GestorImp();
        gestor.iniciarMenu();

    }
}
