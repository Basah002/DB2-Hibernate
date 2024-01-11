import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "Mitarbeiter")
public class Mitarbeiter extends Person {

	@Column(length = 25)
	private String abteilung;

	@Column(length = 25)
	private String rolle;

	public Mitarbeiter() {

	}

	public Mitarbeiter(String vorname, String nachname, String anrede, String email, String abteilung, String rolle) {

		super(vorname, nachname, anrede, email);
		this.abteilung = abteilung;
		this.rolle = rolle;

	}

	public static void addMitarbeiter() {
		Scanner sc = new Scanner(System.in);

		String name, vorname, anrede, email, abteilung, rolle;
		while (true) {

			System.out.println("Mitarbeiter einfügen: Bitte geben Sie die nötigen Daten des Mitarbeiters ein");
			System.out.println("Name");
			name = sc.next();
			System.out.println("Vorname");
			vorname = sc.next();
			System.out.println("Anrede");
			anrede = sc.next();
			System.out.println("Email");
			email = sc.next();
			System.out.println("abteilung");
			abteilung = sc.next();
			System.out.println("rolle");
			rolle = sc.next();

			Mitarbeiter mit = new Mitarbeiter(vorname, name, anrede, email, abteilung, rolle);
			try {

				ManipulateTable.mitarbeiterEinfuegen(mit);
				System.out.println("Mirarbeiter wurde erfolgreich eingefügt: "
						+ ManipulateTable.getMitarbeiter(mit.getId()).toString());
				name = vorname = anrede = email = "";
				break;
			} catch (Exception ex) {
				System.out.println("Mitarbeiter konnte nicht eingefügt werden: Bitte an den Support melden");
			}

		}
	}

	public void setAbteilung(String abt) {
		this.abteilung = abt;
	}

	public String getAbteilung() {
		return this.abteilung;
	}

	public void setRolle(String rolle) {
		this.rolle = rolle;
	}

	public String getRolle() {
		return rolle;
	}

	@Override
	public String toString() {
		return super.toString() + ", Abteilung: " + this.abteilung + ", Rolle: " + this.rolle;
	}

}
