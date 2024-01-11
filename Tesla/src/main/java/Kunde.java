import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "Kunde")
public class Kunde extends Person {

	@OneToMany(mappedBy = "kunde", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Fahrzeug> fahrzeuge=new ArrayList<Fahrzeug>();
	
	public static int anzahl=0;

	public Kunde() {

	}

	public Kunde(String vorname, String nachname, String anrede, String email) {
		super(vorname, nachname, anrede, email);
		anzahl++;
	}
	
	public static void addKunde() {
		Scanner sc = new Scanner(System.in);
		String model,name, vorname, anrede, email;
		int baujahr,aktKm,kapazitaet;
			while (true) {
				
					System.out.println("Kunde einfügen: Bitte geben Sie die nötigen Daten des Kunde ein");
					System.out.println("Name");
					name = sc.next();
					System.out.println("Vorname");
					vorname = sc.next();
					System.out.println("Anrede");
					anrede = sc.next();
					System.out.println("Email");
					email = sc.next();
					System.out.println("Bitte Modell nennen ");
					model=sc.next();
					System.out.println("Bitte baujahr nennen ");
					baujahr=sc.nextInt();
					System.out.println("Bitte aktKm nennen ");
					aktKm=sc.nextInt();
					System.out.println("Bitte Kapazität nennen ");
					kapazitaet=sc.nextInt();
					Kunde k=new Kunde(vorname, name, anrede,email);
					Fahrzeug tesla=new Fahrzeug(model,baujahr,aktKm,kapazitaet,k);
					tesla.setKunde(k);
					k.addFahrzeuge(tesla);
					
					try {

						ManipulateTable.kundeEinfuegen(k);
						System.out.println("Kunde wurde erfolgreich eingefügt: "
								+ ManipulateTable.getKunde(k.getId()).toString());
						name = vorname = anrede = email = "";
						break;
					} catch (Exception ex) {
						System.out.println("Kunde konnte nicht eingefügt werden: Bitte an den Support melden");
					}

				}
		
	}


	public void addFahrzeuge(Fahrzeug auto) {

		fahrzeuge.add(auto);
	}

	public Fahrzeug getIndexofFahrzeug(int i) {

		return fahrzeuge.get(i);
	}

	public void getFahrzeuge() {

		for (Fahrzeug a : fahrzeuge) {
			System.out.println(a.toString());
		}

	}
	public List getFahrzeuge2() {

		return fahrzeuge;

	}
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	
}
