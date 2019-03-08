package ch.hearc.boutiqueservice.application.api.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.hearc.boutiqueservice.application.api.web.ressources.BiereRessource;
import ch.hearc.boutiqueservice.application.api.web.ressources.CreerBiereReponseResource;
import ch.hearc.boutiqueservice.application.api.web.ressources.TypeBiereRessource;
import ch.hearc.boutiqueservice.application.service.BiereService;
import ch.hearc.boutiqueservice.domaine.commande.CreerBiereCommande;

@RestController
@RequestMapping("biere")
public class BiereController {
	
	private final BiereService biereService;
	
	@Autowired
	public BiereController(final BiereService biereService) {
		this.biereService = biereService;
	}
	
	@GetMapping
	public ResponseEntity<List<BiereRessource>> getAllBieres(){
		
		return ResponseEntity.ok(
				biereService.getAllBieres().stream().map(biere -> {
					return BiereRessource.fromBiere(biere);
				}).collect(Collectors.toList())
		);
	}
	
	@GetMapping("type")
	public ResponseEntity<List<TypeBiereRessource>> getAllTypeBieres(){
		
		return ResponseEntity.ok(
				biereService.getAllTypeBieres().stream().map(tbiere -> {
					return TypeBiereRessource.fromTypeBiere(tbiere);
				}).collect(Collectors.toList())
		);
		
	}
	
	@GetMapping("/{noArticle}")
	public ResponseEntity<BiereRessource> getBiereByNoArticle(@PathVariable("noArticle") String noArticle){
		
		return ResponseEntity.ok(
				BiereRessource.fromBiere(
				biereService.getBiereByNoArticle(noArticle))
		);
		
	}
	
	
	@PostMapping
	public ResponseEntity<CreerBiereReponseResource> createBiere(@RequestBody CreerBiereCommande creerBiereCommande ){
		
		System.out.println(creerBiereCommande);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(
				CreerBiereReponseResource.fromBiere(biereService.creerBiere(creerBiereCommande))
		);
	}
	
}
