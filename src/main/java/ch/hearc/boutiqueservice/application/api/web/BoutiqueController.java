package ch.hearc.boutiqueservice.application.api.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.hearc.boutiqueservice.application.api.web.ressources.CreerPanierReponseResource;
import ch.hearc.boutiqueservice.application.api.web.ressources.FabricantRessource;
import ch.hearc.boutiqueservice.application.service.BiereService;
import ch.hearc.boutiqueservice.application.service.BoutiqueService;
import ch.hearc.boutiqueservice.application.util.GitProperties;
import ch.hearc.boutiqueservice.domaine.model.Fabricant;

@RestController
@RequestMapping("boutique")
public class BoutiqueController {

	private final BoutiqueService boutiqueService;
	
	@Autowired
	public BoutiqueController(BoutiqueService boutiqueService) {
		super();
		this.boutiqueService = boutiqueService;
	}

	@GetMapping("/health")
	public ResponseEntity<String> checkAppHealth(){
		return ResponseEntity.ok("app started");
	}
	
	@GetMapping("/build-info")
	public ResponseEntity getBuildInfos(){
		return ResponseEntity.ok(new GitProperties().readGitProperties());
	}
	
	@GetMapping("/fabricant")
	public ResponseEntity<List<FabricantRessource>> getAllFabricants(){
		
		return ResponseEntity.ok(
				boutiqueService.getAllFabricants()
				.stream()
				.map(fabricant -> FabricantRessource.fromFabricant(fabricant)).collect(Collectors.toList()));
	}
	
	
	
}
