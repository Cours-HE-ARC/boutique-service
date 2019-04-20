package ch.hearc.boutiqueservice.application.api.web;

import ch.hearc.boutiqueservice.application.api.web.ressources.BiereRessource;
import ch.hearc.boutiqueservice.application.api.web.ressources.CreerBiereReponseResource;
import ch.hearc.boutiqueservice.application.api.web.ressources.TypeBiereRessource;
import ch.hearc.boutiqueservice.application.service.BiereService;
import ch.hearc.boutiqueservice.domaine.commande.CreerBiereCommande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("biere")
public class BiereController {
	
	private final BiereService biereService;
	
	@Autowired
	public BiereController(final BiereService biereService) {
		this.biereService = biereService;
	}

	@PreAuthorize("hasAnyAuthority('ROLE_UTILISATEUR')")
	@GetMapping
	public ResponseEntity<List<BiereRessource>> getAllBieres(){
		
		return ResponseEntity.ok(
				biereService.getAllBieres().stream().map(biere -> {
					return BiereRessource.fromBiere(biere);
				}).collect(Collectors.toList())
		);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_UTILISATEUR')")
	@GetMapping("type")
	public ResponseEntity<List<TypeBiereRessource>> getAllTypeBieres(){
		
		return ResponseEntity.ok(
				biereService.getAllTypeBieres().stream().map(tbiere -> {
					return TypeBiereRessource.fromTypeBiere(tbiere);
				}).collect(Collectors.toList())
		);
		
	}

	@PreAuthorize("hasAnyAuthority('ROLE_UTILISATEUR')")
	@GetMapping("/{noArticle}")
	public ResponseEntity<BiereRessource> getBiereByNoArticle(@PathVariable("noArticle") String noArticle){
		
		return ResponseEntity.ok(
				BiereRessource.fromBiere(
				biereService.getBiereByNoArticle(noArticle))
		);
		
	}

	@PreAuthorize("hasAnyAuthority('ROLE_UTILISATEUR')")
	@PostMapping
	public ResponseEntity<CreerBiereReponseResource> createBiere(@RequestBody CreerBiereCommande creerBiereCommande ){
		
		System.out.println(creerBiereCommande);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(
				CreerBiereReponseResource.fromBiere(biereService.creerBiere(creerBiereCommande))
		);
	}
	
}
