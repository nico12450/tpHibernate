package tpJPA;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String nom;
	
	@Column
	private String prenom;
	
	@Column
	private Genre genre;
	
	@ManyToOne
	private Livre livrePrefere;
	
	@ManyToMany
	private List<Livre> livresAchats = new ArrayList<Livre>();
	
	public Client() {
		
	}

	public Client(String nom, String prenom, Genre genre) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.genre = genre;
	}

	public Long getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public void setLivrePrefere(Livre livrePrefere) {
		this.livrePrefere = livrePrefere;
	}

	public Livre getLivrePrefere() {
		return livrePrefere;
	}

	public List<Livre> getLivresAchats() {
		return livresAchats;
	}

	@Override
	public String toString() {
		return "Client [nom=" + nom + ", prenom=" + prenom + ", genre=" + genre + "]";
	}

}
