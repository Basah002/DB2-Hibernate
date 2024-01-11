
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import java.util.*;

public class ManipulateTable {

	@PersistenceUnit
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUnit");

	@PersistenceContext
	static EntityManager em;

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	
	
	
	
	
	
	
	public static void anzeigenFahrzeugLadestation() {
	    EntityManager em = getEntityManager();
	    em.getTransaction().begin();
	    try {
	        String nativeQuery = "SELECT * FROM Fahrzeug_Ladestation";
	        Query query = em.createNativeQuery(nativeQuery);
	        List<Object[]> result = query.getResultList();

	        if (!result.isEmpty()) {
	            System.out.println("+--------------+-----------------+");
	            System.out.println("| Fahrzeug ID  | Ladestation ID  |");
	            System.out.println("+--------------+-----------------+");

	            for (Object[] row : result) {
	                Integer fahrzeugId = (Integer) row[0];
	                Integer ladestationId = (Integer) row[1];
	                System.out.printf("| %-12s | %-15s |\n", fahrzeugId, ladestationId);
	            }

	            System.out.println("+--------------+-----------------+");
	        } else {
	            System.out.println("Es wurden keine Fahrzeug-Ladestation-Beziehungen gefunden.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    em.getTransaction().commit();
	    em.close();
	}



	
	

	public static void addFahrzeugToLadestation(Fahrzeug fahrzeug, Ladestation ladestation) {
	    EntityManager entityManager = emf.createEntityManager();
	    EntityTransaction transaction = null;
	    
	    try {
	        transaction = entityManager.getTransaction();
	        transaction.begin();
	        
	        // Fahrzeug aus der Datenbank laden
	        Fahrzeug managedFahrzeug = entityManager.find(Fahrzeug.class, fahrzeug.getFahrgestellnr());
	        
	        // Ladestation dem Fahrzeug hinzufügen
	        managedFahrzeug.setLadestationen(ladestation);
	        
	        // Fahrzeug persistieren
	        entityManager.merge(managedFahrzeug);
	        
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        // Handle exception
	    } finally {
	        entityManager.close();
	    }
	}


	
	public static void ladestationEinfuegen(Ladestation ladestation) {
	    EntityManager entityManager = emf.createEntityManager();
	    EntityTransaction transaction = null;
	    
	    try {
	        transaction = entityManager.getTransaction();
	        transaction.begin();
	        
	        entityManager.persist(ladestation);
	        
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        // Handle exception
	    } finally {
	        entityManager.close();
	    }
	}

	@Transactional
	public static void tupelLoeschen(String tabellenName, long pid) {
		em = getEntityManager();
		em.getTransaction().begin();
		try {

			Object entity = em.find(Class.forName(tabellenName), pid);
			if (entity != null) {
				em.remove(entity);
	            
			} else {
				System.out.println(
						"Das Tupel mit der PID " + pid + " in der Tabelle " + tabellenName + " wurde nicht gefunden.");
			}
		} catch (Exception e) {
			System.out.println();
		}

		em.getTransaction().commit();
		em.close();
	}
	
	@Transactional
	public static void fahrzeugLoeschenVonHalter(int halterId, int fahrzeugId) {
	    EntityManager em = getEntityManager();

	    try {
	        em.getTransaction().begin();

	        Kunde halter = em.find(Kunde.class, halterId);
	        if (halter != null) {
	            Fahrzeug fahrzeug = em.find(Fahrzeug.class, fahrzeugId);
	            if (fahrzeug != null) {
	                halter.getFahrzeuge2().remove(fahrzeug);
	                fahrzeug.deleteLadestation();
	                em.remove(fahrzeug);
	                System.out.println("Das Fahrzeug mit der ID " + fahrzeugId + " wurde vom Halter entfernt und gelöscht.");
	            } else {
	                System.out.println("Das Fahrzeug mit der ID " + fahrzeugId + " wurde nicht gefunden.");
	            }
	        } else {
	            System.out.println("Der Halter mit der ID " + halterId + " wurde nicht gefunden.");
	        }

	        em.getTransaction().commit();
	    } catch (Exception e) {
	        em.getTransaction().rollback();
	        System.out.println("Fehler beim Löschen des Fahrzeugs: " + e.getMessage());
	    } finally {
	        em.close();
	    }
	}
	@Transactional
	public static void ladestationLoeschen(int ladestationId) {
	    EntityManager em = getEntityManager();

	    try {
	        em.getTransaction().begin();

	        Ladestation ladestation = em.find(Ladestation.class, ladestationId);
	        if (ladestation != null) {
	            // Entferne die Beziehungen zu den Fahrzeugen
	            for (Fahrzeug fahrzeug : ladestation.getFahrzeuge()) {
	                fahrzeug.getLadestationen().remove(ladestation);
	            }
	            ladestation.getFahrzeuge().clear();

	            em.remove(ladestation);
	            System.out.println("Die Ladestation mit der ID " + ladestationId + " wurde gelöscht.");
	        } else {
	            System.out.println("Die Ladestation mit der ID " + ladestationId + " wurde nicht gefunden.");
	        }

	        em.getTransaction().commit();
	    } catch (Exception e) {
	        em.getTransaction().rollback();
	        System.out.println("Fehler beim Löschen der Ladestation: " + e.getMessage());
	    } finally {
	        em.close();
	    }
	}


	
	

	// überarbeiten
	@Transactional
	public static void alletabelleAnzeigen() {

		tabelleAnzeigen("Kunde");
		tabelleAnzeigen("Mitarbeiter");
		tabelleAnzeigen("Fahrzeug");
		tabelleAnzeigen("Ladestation");
		anzeigenFahrzeugLadestation();

	}
	
	

	@Transactional
	public static void tabelleAnzeigen(String tableName) {
		em = getEntityManager();

		// Erstellen der Abfrage, um alle Einträge aus der Tabelle abzurufen
		Query query = em.createQuery("SELECT e FROM " + tableName + " e");

		// Ausführen der Abfrage und Abrufen der Ergebnisse
		List<Object> eintraege = query.getResultList();

		// Schleife über die Einträge und Ausgabe der Inhalte
		for (Object eintrag : eintraege) {
			System.out.println(eintrag.toString());
			System.out.println();
		}

		em.close();
	}
	@Transactional	
	public static void tupelAnzeigenNachNachname(String tabellenName, String nachname) {
	    EntityManager em = getEntityManager();
	    em.getTransaction().begin();
	    try {
	        String queryString = "SELECT e FROM " + tabellenName + " e WHERE e.nachname = :nachname";
	        TypedQuery<?> query = em.createQuery(queryString, Class.forName(tabellenName));
	        query.setParameter("nachname", nachname);
	        Object entity = query.getSingleResult();

	        if (entity != null) {
	            Class<?> myEntityClass = Class.forName(tabellenName);
	            System.out.println(myEntityClass.cast(entity));
	        } else {
	            System.out.println("Das Tupel mit dem Nachnamen " + nachname + " in der Tabelle " + tabellenName + " wurde nicht gefunden.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    em.getTransaction().commit();
	    em.close();
	}
	
	@Transactional	
	public static void tupelAnzeigenNachVorname(String tabellenName, String vorname) {
	    EntityManager em = getEntityManager();
	    em.getTransaction().begin();
	    try {
	        String queryString = "SELECT e FROM " + tabellenName + " e WHERE e.vorname = :vorname";
	        TypedQuery<?> query = em.createQuery(queryString, Class.forName(tabellenName));
	        query.setParameter("vorname", vorname);
	        Object entity = query.getSingleResult();

	        if (entity != null) {
	            Class<?> myEntityClass = Class.forName(tabellenName);
	            System.out.println(myEntityClass.cast(entity));
	        } else {
	            System.out.println("Das Tupel mit dem Vornamen " + vorname + " in der Tabelle " + tabellenName + " wurde nicht gefunden.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    em.getTransaction().commit();
	    em.close();
	}

	
	@Transactional
	public static void tupelAnzeigen(String tabellenName, long pid) {
		em = getEntityManager();
		em.getTransaction().begin();
		try {

			Object entity = em.find(Class.forName(tabellenName), pid);
			if (entity != null) {
				Class<?> myEntityClass=Class.forName(tabellenName);
				System.out.println(myEntityClass.cast(entity));
			} else {
				System.out.println(
						"Das Tupel mit der PID " + pid + " in der Tabelle " + tabellenName + " wurde nicht gefunden.");
			}
		} catch (Exception e) {
			System.out.println();
		}

		em.getTransaction().commit();
		em.close();
	}
	
	


	@Transactional
	public static void kundeEinfuegen(Kunde kunde) {
		em = getEntityManager();
		em.getTransaction().begin();
		em.persist(kunde);
		em.getTransaction().commit();
		em.close();
	}

	@Transactional
	public static void mitarbeiterEinfuegen(Mitarbeiter mitarbeiter) {
		em = getEntityManager();
		em.getTransaction().begin();
		em.persist(mitarbeiter);
		em.getTransaction().commit();
		em.close();
	}

	public static Kunde getKunde(long pid) { // muss pid genauso heußen wie in @Column bei Person
		EntityManager em = getEntityManager();
		Kunde kunde = em.find(Kunde.class, pid);
		em.detach(kunde);
		return kunde;
	}

	public static Mitarbeiter getMitarbeiter(long pid) {
		EntityManager em = getEntityManager();
		Mitarbeiter mitarbeiter = em.find(Mitarbeiter.class, pid);
		em.detach(mitarbeiter);
		return mitarbeiter;
	}

	@Transactional
	public static void fahrzeugEinfuegen(Fahrzeug fahrzeug) {
		em = getEntityManager();
		em.getTransaction().begin();
		em.persist(fahrzeug);
		em.getTransaction().commit();
		em.close();
	}

	public static Fahrzeug getFahrzeug(int fahrgestellnr) {
		EntityManager em = getEntityManager();
		Fahrzeug fahrzeug = em.find(Fahrzeug.class, fahrgestellnr);
		em.detach(fahrzeug);
		return fahrzeug;
	}

	public static Ladestation getLadestation(int ladestationId) {
		EntityManager em = getEntityManager();
		Ladestation ladestation = em.find(Ladestation.class, ladestationId);
		em.detach(ladestation);
		return ladestation;
	}

}
