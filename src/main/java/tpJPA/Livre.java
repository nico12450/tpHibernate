package tpJPA;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "livre")
public class Livre {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String titre;
	
	@Column
	private String auteur;
	
	@ManyToMany(mappedBy = "livresAchats")
	private List<Client> clientsAchats;
	
	public Livre() {
		
	}

	public Livre(String titre, String auteur) {
		super();
		this.titre = titre;
		this.auteur = auteur;
	}

	public Long getId() {
		return id;
	}

	public String getTitre() {
		return titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public List<Client> getClientsAchats() {
		return clientsAchats;
	}

	@Override
	public String toString() {
		return "Livre [titre=" + titre + ", auteur=" + auteur + "]";
	}

}
