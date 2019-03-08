package ch.hearc.boutiqueservice.application;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ch.hearc.boutiqueservice.domaine.model.Fabricant;
import ch.hearc.boutiqueservice.domaine.model.TypeBiere;
import ch.hearc.boutiqueservice.domaine.repository.BiereRepository;
import ch.hearc.boutiqueservice.infrastructure.repository.entity.TypeBiereEntity;

@SpringBootApplication
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
