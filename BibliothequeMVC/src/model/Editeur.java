package model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
public class Editeur {

	private int idEditeur;
	private String nomEditeur;
	private String ville;
	
}
