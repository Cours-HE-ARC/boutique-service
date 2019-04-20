package ch.hearc.boutiqueservice.application;

import ch.hearc.boutiqueservice.domaine.repository.BiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class,
		UserDetailsServiceAutoConfiguration.class})
@ComponentScan("ch.hearc.boutiqueservice")
@EnableJpaRepositories("ch.hearc.boutiqueservice.infrastructure.jpa")
@EntityScan("ch.hearc.boutiqueservice.infrastructure.repository.entity")
public class BoutiqueServiceApplication {
	
	@Autowired
	private BiereRepository biereRepository;

	public static void main(String[] args) {
		SpringApplication.run(BoutiqueServiceApplication.class, args);
	}

}
