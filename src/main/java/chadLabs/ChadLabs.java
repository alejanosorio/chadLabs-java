package chadLabs;

import chadLabs.service.GestorService;
import chadLabs.service.GestorMenuImp;
import org.springframework.boot.SpringApplication;


public class ChadLabs {

	public static void main(String[] args) {
		SpringApplication.run(ChadLabs.class, args);

    GestorService gestor = new GestorMenuImp ();
        gestor.iniciarMenu();

    }
}
