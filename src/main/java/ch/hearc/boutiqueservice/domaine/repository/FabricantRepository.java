package ch.hearc.boutiqueservice.domaine.repository;

import java.util.List;

import ch.hearc.boutiqueservice.domaine.model.Fabricant;

public interface FabricantRepository {

	Fabricant getFabricantById(String idFabricant);
	
	List<Fabricant> getAllFabricants();

}
