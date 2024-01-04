package pt.sofco.graha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ProjectMiniBudhiOctaviansyahApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectMiniBudhiOctaviansyahApplication.class, args);
	}

}
