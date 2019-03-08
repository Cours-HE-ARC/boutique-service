package ch.hearc.boutiqueservice.domaine;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Ignore;
import org.junit.Test;

import ch.hearc.boutiqueservice.domaine.model.Biere;
import ch.hearc.boutiqueservice.domaine.model.Fabricant;
import ch.hearc.boutiqueservice.domaine.model.TypeBiere;

@Ignore
public class BiereTest {

	
	@Test
	public void givenNameAndTypeAndFabricant_whenInstanciateBiere_thenInstanceOk() {
		Biere biere = Biere.creerBiere("Cardinal 1664",null, null,  new TypeBiere(1L,"Bllonde"),null );
		
		assertTrue(biere != null);
		assertTrue(biere.getNom() != null);
		assertTrue(biere.getType() != null);
		
	}
	
	@Test(expected=NullPointerException.class)
	public void givenNameAndTypeNull_whenInstanciateBiere_thenNullPointerExceptionIsThrown() {
		Biere biere = Biere.creerBiere("Cardinal 1664", null,null, null,null);
		
		fail();	
	}
	
	@Test(expected=NullPointerException.class)
	public void givenNameNullAndType_whenInstanciateBiere_thenNullPointerExceptionIsThrown() {
		Biere biere = Biere.creerBiere(null,  null,null, new TypeBiere(1L,"Ambree"),null);
		
		fail();	
	}
	
	@Test(expected=NullPointerException.class)
	public void givenNameNullAndTypeNull_whenInstanciateBiere_thenNullPointerExceptionIsThrown() {
		Biere biere = Biere.creerBiere(null, null, null, null, null);
		
		fail();	
	}
	
	@Test(expected=NullPointerException.class)
	public void givenNameAndTypeAndFabricantNull_whenInstanciateBiere_thenNullPointerExceptionIsThrown() {
		Biere biere = Biere.creerBiere("testBiere", null, null, new TypeBiere(1L,"Ambree"),null);
		
		fail();	
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void givenNameWith3CharAndType_whenInstanciateBiere_thenIllegalArcgumentExceptionIsThrown() {
		Biere biere = Biere.creerBiere("123", null, null,  new TypeBiere(1L,"Ambree"),null);
		
		fail();	
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void givenNameAndTypeAndFabricant_whenInstanciateBiere_thenIdentifiantIsGenereated() {
		Biere biere = Biere.creerBiere("123", null, null, new TypeBiere(1L,"Ambree"),null);
		
		assertTrue(biere.getIdentifiant()!= null);
		assertTrue(biere.getIdentifiant().length() == 36);
		
	}
	
	
}
