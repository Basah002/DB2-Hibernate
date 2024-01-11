import jakarta.persistence.*;
import java.util.*;
@Entity
@Table(name = "Fahrzeug")
public class Fahrzeug {

	public static int anzahl = 0;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fahrgestellnr;

	private String model;

	private int baujahr;

	private int aktuellerKmStand;

	private int batterieKapazität;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Fahrzeug_Ladestation", joinColumns = @JoinColumn(name = "fahrgestellnr"), inverseJoinColumns = @JoinColumn(name = "ladestationId"))
	private List<Ladestation> ladestationen = new ArrayList<Ladestation>();

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "kunde_id")
	private Kunde kunde;

	public Fahrzeug() {
	}

	public Fahrzeug(String model, int baujahr, int aktuellerKmStand, int batterieKapazität, Kunde k) {
		this.model = model;
		this.baujahr = baujahr;
		this.aktuellerKmStand = aktuellerKmStand;
		this.batterieKapazität = batterieKapazität;
		kunde = k;
		k.addFahrzeuge(this);
		anzahl++;
	}

	public static void addFahrzeug() {

		Scanner sc = new Scanner(System.in);
		while (true) {
			String model, wahl;
			int baujahr, aktKm, kapazitaet;

			if (Kunde.anzahl == 0) { // Keine kunden sind vorhanden, deswegen muss ein kunde erstellt werden
				System.out.println("Sie müssen erst einen Kunden einfügen");
				Kunde.addKunde();
				break;
			} else { // Es gibt kunden, --> ein kunde hat mehrere Fahrzeuge

				ManipulateTable.tabelleAnzeigen("Kunde");
				System.out.println("Besitzer eingeben(pid): ");
				long tmp = sc.nextLong();
				Kunde k = ManipulateTable.getKunde(tmp);

				System.out.println("Fahrzeug einfügen: Bitte geben Sie die nötigen Daten des Fahrzeugs ein");
				System.out.println("Bitte Modell nennen ");
				model = sc.next();
				System.out.println("Bitte baujahr nennen ");
				baujahr = sc.nextInt();
				System.out.println("Bitte aktKm nennen ");
				aktKm = sc.nextInt();
				System.out.println("Bitte Kapazität nennen ");
				kapazitaet = sc.nextInt();
				Fahrzeug tesla = new Fahrzeug(model, baujahr, aktKm, kapazitaet, k);

				try {

					ManipulateTable.fahrzeugEinfuegen(tesla);

					System.out.println(
							"Fahrzeug wurde erfolgreich eingefügt: " + ManipulateTable.getFahrzeug(anzahl).toString());
					System.out.println("Dankeschön");
					model = "";
					break;
				} catch (Exception ex) {
					ex.printStackTrace();
					System.out.println("Fahrzeug konnte nicht eingefügt werden: Bitte an den Support melden");
				}
			}
		}
	}

	public void deleteLadestation() {
		
		this.ladestationen=null;
		
	}
	
	public  void setLadestationen(Ladestation ladestation) {
		ladestationen.add(ladestation);
	}

	
	
	public Kunde getKunde() {
		return kunde;
	}

	public List<Ladestation> getLadestationen() {
		return ladestationen;
	}

	public String getModel() {
		return this.model;
	}

	public int getBaujahr() {
		return baujahr;
	}

	public int getFahrgestellnr() {
		return fahrgestellnr;
	}

	public int getAktuellerKmStand() {
		return aktuellerKmStand;
	}

	public int getBatterieKapazität() {
		return batterieKapazität;
	}

	public void setBaujahr(int baujahr) {
		this.baujahr = baujahr;
	}

	public void setFahrgestellnr(int fahrgestellnr) {
		this.fahrgestellnr = fahrgestellnr;
	}

	public void setAktuellerKmStand(int aktuellerKmStand) {
		this.aktuellerKmStand = aktuellerKmStand;
	}

	public void setBatterieKapazität(int batterieKapazität) {
		this.batterieKapazität = batterieKapazität;
	}

	public void setKunde(Kunde k) {
		this.kunde = k;
	}

	@Override
	public String toString() {
		return "Fahrzeug-ID: "+getFahrgestellnr()+" Model: " + this.getModel() + ", Baujahr: " + baujahr + ", Fahrgestellnummer=" + fahrgestellnr
				+ ", AktuellerKmStand=" + aktuellerKmStand + ", BatterieKapazität=" + batterieKapazität;
	}
}
