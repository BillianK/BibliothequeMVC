package model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Livre {

	private String isbn;
	private String titre;
	private Date parution;
	private int nbPages;
	private List<Auteur> auteurs;
	private Editeur editeur;
	private Theme theme;

	public Livre(String isbn, String titre, Date parution, int nbPages, List<Auteur> auteurs, Editeur editeur,
			Theme theme) {
		this.isbn = isbn;
		this.titre = titre;
		this.parution = parution;
		this.nbPages = nbPages;
		this.auteurs = auteurs;
		this.editeur = editeur;
		this.theme = theme;
	}

	@Override
	public String toString() {
		return "Livre : ISBN: " + isbn + "|| titre: " + titre + "|| parution: " + parution;
	}
	
	

}
