package ch.hearc.boutiqueservice.infrastructure.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.hearc.boutiqueservice.domaine.model.Fabricant;
import ch.hearc.boutiqueservice.domaine.repository.FabricantRepository;
import ch.hearc.boutiqueservice.infrastructure.jpa.BiereSpringDataRepository;
import ch.hearc.boutiqueservice.infrastructure.jpa.FabricantSpringDataRepository;
import ch.hearc.boutiqueservice.infrastructure.repository.entity.FabricantEntity;
import javassist.tools.framedump;

@Repository
public class FabricantH2Repository implements FabricantRepository {

	@Autowired
	private FabricantSpringDataRepository fabricantSpringDataRepository;
	
	@Override
	public Fabricant getFabricantById(String idFabricant) {
		
		Optional<FabricantEntity>  fabricant = fabricantSpringDataRepository.findById(Long.valueOf(idFabricant));
		
		if(fabricant.isPresent()) {
			FabricantEntity fabricantEntity =  fabricant.get();
			return fabricantEntity.toFabricant();
		}
		
		throw new RuntimeException("Id dont exist: " + idFabricant);
	}

	@Override
	public List<Fabricant> getAllFabricants() {
		
		List<FabricantEntity> fabricants = new ArrayList<>();
		
		fabricantSpringDataRepository.findAll().forEach(fabricants::add);
		
		return fabricants.stream().map(entity -> entity.toFabricant()).collect(Collectors.toList());
	}
	
	

}
