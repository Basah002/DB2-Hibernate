import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "Ladestation")
public class Ladestation {

	private static int anzahl = 0;
	@Column(name = "LadestationID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ladestationId;
	@Column(name = "Ladeleistung")
	private int ladeleistung;

	@Column(name = "Preis")
	private double preis;

	@Column(name = "tüchtigkeit")
	private boolean tüchtigkeit = true;;

	// struk attribut
	@Embedded
	private Standort standort;

	@ManyToMany(mappedBy = "ladestationen")
	private List<Fahrzeug> fahrzeuge = new ArrayList<Fahrzeug>();
// mehrwertiges attribut
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "Ladestation_Bezahlmethode", joinColumns = @JoinColumn(name = "LadestationID"))
	@Column(name = "Bezahlmethode")
	@Enumerated(EnumType.STRING)

	private List<Bezahlmethode> bezahlmethoden = new ArrayList<Bezahlmethode>();

	public Ladestation() {
	}

	public Ladestation(int ladeleistung, double preis, Standort standort) {
		this.ladeleistung = ladeleistung;
		this.preis = preis;
		this.standort = standort;

	anzahl++;
	}

	public void addBezahlmethode(Bezahlmethode bezahlmethode) {
		bezahlmethoden.add(bezahlmethode);
	}

	public List<Bezahlmethode> getBezahlmethoden() {
		return bezahlmethoden;
	}

	public void setFahrzeug(Fahrzeug fahrzeug) {
		fahrzeuge.add(fahrzeug);
	}

	
	public static void addLadestation() {
	    Scanner sc = new Scanner(System.in);
	    while (true) {
	        int ladeleistung;
	        double preis;

	        System.out.println("Ladestation einfügen: Bitte geben Sie die nötigen Daten der Ladestation ein");
	        System.out.println("Bitte Ladeleistung eingeben: ");
	        ladeleistung = sc.nextInt();
	        sc.nextLine(); // Zeilenumbruch verwerfen
	        System.out.println("Bitte Preis eingeben: ");
	        preis = sc.nextDouble();
	        sc.nextLine(); // Zeilenumbruch verwerfen

	        System.out.println("Geben Sie die verfügbaren Bezahlmethoden ein (eine nach der anderen, 'q' zum Beenden):");
	        List<Bezahlmethode> bezahlmethoden = new ArrayList();
	        boolean validInput = false; // Flag-Variable für gültige Eingabe

	        while (!validInput) {
	            String input = sc.nextLine().trim();

	            if (input.equalsIgnoreCase("q")) {
	                break;
	            }

	            try {
	                Bezahlmethode methode = Bezahlmethode.valueOf(input.toUpperCase());
	                bezahlmethoden.add(methode);
	              
	            } catch (IllegalArgumentException e) {
	                System.out.println("Ungültige Bezahlmethode. Bitte erneut eingeben oder 'q' zum Beenden.");
	            }
	        }

	        if (!bezahlmethoden.isEmpty()) {
	            Ladestation ladestation = new Ladestation(ladeleistung, preis, Standort.addStandort());
	            ladestation.setBezahlmethoden(bezahlmethoden);

	            try {
	                ManipulateTable.ladestationEinfuegen(ladestation);
	                System.out.println("Ladestation wurde erfolgreich eingefügt: " + ladestation.toString());
	                System.out.println("Dankeschön");

	                break;
	            } catch (Exception ex) {
	                ex.printStackTrace();
	                System.out.println("Ladestation konnte nicht eingefügt werden: Bitte an den Support melden");
	            }
	        } else {
	            System.out.println("Es wurden keine Bezahlmethoden eingegeben. Bitte erneut versuchen.");
	        }
	    }
	}


	

	private void setBezahlmethoden(List<Bezahlmethode> bezahlmethoden2) {
		// TODO Auto-generated method stub
		this.bezahlmethoden=bezahlmethoden2;
		
	}

	public List<Fahrzeug> getFahrzeuge() {
		return fahrzeuge;
	}

	public int getId() {
		return ladestationId;

	}

	public int getLadeleistung() {
		return ladeleistung;
	}

	public int getLadestationId() {
		return ladestationId;
	}

	public void setLadeleistung(int ladeleistung) {
		this.ladeleistung = ladeleistung;
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}

	public boolean isVerfügbarkeit() {
		return tüchtigkeit;
	}

	public void setVerfügbarkeit(boolean verfügbarkeit) {
		this.tüchtigkeit = verfügbarkeit;
	}

	public Standort getStandort() {
		return standort;
	}

	public void setStandort(Standort standort) {
		this.standort = standort;
	}

	@Override
	public String toString() {

		String text = "";
		text += "Ladestation-ID: " + getLadestationId() + ", Ladeleistung: " + getLadeleistung() + ", Preis: "
				+ getPreis() + ", Verfügbarkeit: " + isVerfügbarkeit() + "\n";
		text += getStandort().toString() + "\n";
		text += "Bezahlmethoden: " + getBezahlmethoden().toString();
		return text;
	}

	public static int getAnzahl() {
		return anzahl;
	}

	
}
