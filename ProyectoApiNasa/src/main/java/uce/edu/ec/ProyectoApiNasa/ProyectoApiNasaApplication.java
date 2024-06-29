package uce.edu.ec.ProyectoApiNasa;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.WebApplicationType;
import uce.edu.ec.ProyectoApiNasa.Container.Container;

/**
 * @author Jordi Pila
 * @theme Programacion Funcional, listas inmutables, y api de la nasa
 */
@SpringBootApplication
public class ProyectoApiNasaApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(ProyectoApiNasaApplication.class)
				.headless(false)
				.web(WebApplicationType.NONE)
				.run(args);
		Container container = context.getBean(Container.class);
	}
}

