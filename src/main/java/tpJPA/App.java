package tpJPA;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class App {
	
	

	public static void main(String[] args) {
		
		EntityManager em = DatabaseHelper.createEntityManager();
		
		Livre l1 = new Livre("UML For Java Programmers","Robert Cecil Martin");
		Client c1 = new Client("nom1","prenom1",Genre.Male);
		c1.setLivrePrefere(l1);

		em.getTransaction().begin();
		em.persist(l1);
		em.persist(c1);
		em.getTransaction().commit();
		em.close();
		
		achat(c1,l1);
		
		System.out.println(livreByClient(c1));
		System.out.println(clientByLivre(l1));
		//System.out.println(livresAchetes());

		/*em = DatabaseHelper.createEntityManager();
		Livre findBook = em.find(Livre.class, l1.getId());
		System.out.println(findBook.getId() + " " + findBook.getTitre() + " " + findBook.getAuteur());
		em.close();*/

	}
	
	public static void achat(Client c, Livre l) {
		
		EntityManager em = DatabaseHelper.createEntityManager();
		
		c.getLivresAchats().add(l);
		//l.getClientsAchats().add(c);
		
		em.getTransaction().begin();
		em.merge(c);
		em.getTransaction().commit();
		em.close();
		
	}
	
	public static List<Livre> livreByClient(Client c) {
		
		List<Livre> l = new ArrayList<Livre>();
		
		EntityManager em = DatabaseHelper.createEntityManager();
		l = c.getLivresAchats();
		em.close();
		
		return l;
		
	}
	
	public static List<Client> clientByLivre(Livre l){
		
		Long id = l.getId();
		
		List<Client> L;
		
		
		EntityManager em = DatabaseHelper.createEntityManager();
		
		TypedQuery<Client> query = em.createQuery("select c from Client c inner join c.livresAchats l where l.id=:id", Client.class);
		query.setParameter("id", id);
		L = query.getResultList();
		
		
		em.close();
		
		return L;
		
	}

	public static List<Livre> livresAchetes(){
		
		EntityManager em = DatabaseHelper.createEntityManager();
		
		TypedQuery<Livre> query = em.createQuery("from Livre", Livre.class);
		List<Livre> l = query.getResultList();
		
		ArrayList<Livre> L = (ArrayList<Livre>) l.stream().filter(e -> e.getClientsAchats()!=null).collect(Collectors.toList());
			
		em.close();
		
		return L;
		
	}
	
}
