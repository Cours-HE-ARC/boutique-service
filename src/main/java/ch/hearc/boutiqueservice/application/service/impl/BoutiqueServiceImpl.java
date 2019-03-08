package ch.hearc.boutiqueservice.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.hearc.boutiqueservice.application.service.BoutiqueService;
import ch.hearc.boutiqueservice.domaine.model.Fabricant;
import ch.hearc.boutiqueservice.domaine.model.Panier;
import ch.hearc.boutiqueservice.domaine.repository.FabricantRepository;

@Service
public class BoutiqueServiceImpl implements BoutiqueService {

	private FabricantRepository fabricantRepository;
	
	@Autowired
	public BoutiqueServiceImpl(FabricantRepository fabricantRepository) {
		this.fabricantRepository = fabricantRepository;
	}


	@Override
	public List<Fabricant> getAllFabricants() {
		return this.fabricantRepository.getAllFabricants();
	}
	
	

}
