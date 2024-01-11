import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
// Erstellung einer abstrakten Basisklasse 
@MappedSuperclass
public abstract class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="vergebe_id" ) 
	@SequenceGenerator(name="vergebe_id",sequenceName="vergebe_id",allocationSize=1)
	
	// id-->Die Generierung des Primärschlüssels wird der Datenbank-Engine überlassen 
	private long id;

	@Column(length = 25)
	private String vorname;
	@Column(length = 25)
	private String nachname;
	@Column(length = 25)
	private String anrede;
	@Column(length = 25)
	private String email;

	public Person() {

	}

	public Person(String vorname, String nachname, String anrede, String email) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.anrede = anrede;
		this.email = email;
	}
	
	

	public  long getId() {
		return id;
	}

	public String getVorname() {
 
		return this.vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {

		return this.nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getAnrede() {
		return this.anrede;
	}

	public void setAnrede(String anrede) {
		this.anrede = anrede;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {

		String text = "";
		if(this instanceof Mitarbeiter)
		return text +="Mitarbeiter-ID: "+ getId()+ " Vorname: " + this.vorname + ", Nachname: " + this.nachname + ", Anrede: " + this.anrede
				+ ", Email: " + this.email;
		else
			return text +="Kunden-ID: "+ getId()+ " Vorname: " + this.vorname + ", Nachname: " + this.nachname + ", Anrede: " + this.anrede
			+ ", Email: " + this.email;
			
	}
}
