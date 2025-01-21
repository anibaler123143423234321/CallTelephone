package api.codesoft.com.CallTelephone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories("api.codesoft.com.CallTelephone.repository")
@EntityScan("api.codesoft.com.CallTelephone.model")
public class CallTelephoneApplication {
	public static void main(String[] args) {
		SpringApplication.run(CallTelephoneApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// Configuración de CORS
				registry.addMapping("/**")
						.allowedOrigins(
								"https://tufibrarapida.es",
								"https://tufibrarapida.com"
						)
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
						.allowedHeaders("*") // Permitir todos los encabezados
						.allowCredentials(true); // Permitir credenciales si es necesario
			}
		};
	}
}
