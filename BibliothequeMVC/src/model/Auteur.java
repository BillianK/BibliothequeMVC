package model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
@Setter(AccessLevel.PRIVATE)
public class Auteur {

	private int idAuteur;
	private String nom;
	private String prenom;
	
}
