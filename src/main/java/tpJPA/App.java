package tpJPA;

import javax.persistence.EntityManager;

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

		/*em = DatabaseHelper.createEntityManager();
		Livre findBook = em.find(Livre.class, l1.getId());
		System.out.println(findBook.getId() + " " + findBook.getTitre() + " " + findBook.getAuteur());
		em.close();*/

	}
	
	public static void achat(Client c, Livre l) {
		
		EntityManager em = DatabaseHelper.createEntityManager();
		
		c.getAchats().add(l);
		
		em.getTransaction().begin();
		em.merge(c);
		em.getTransaction().commit();
		em.close();
		
	}

}
