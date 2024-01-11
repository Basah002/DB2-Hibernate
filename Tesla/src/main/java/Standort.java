import jakarta.persistence.*;
import java.util.*;

@Embeddable

public class Standort {
	
	
	@Column(name = "GPS")
	private String gps;

	@Column(name = "Ort")
	private String ort;

	@Column(name = "Postleitzahl")
	private String postleitzahl;

	@Column(name = "Straße")
	private String straße;

	public Standort() {
	}

	public Standort(String gps, String ort, String postleitzahl, String straße) {
		this.gps = gps;
		this.ort = ort;
		this.postleitzahl = postleitzahl;
		this.straße = straße;
	}

	public static Standort addStandort() {
		Scanner sc = new Scanner(System.in);
		String gps, ort, postl, straße;

		System.out.println("GPS eingeben");
		gps = sc.nextLine();
		System.out.println("Ort eingeben");
		ort = sc.next();
		System.out.println("Postleitzahl eingeben");
		postl = sc.next();
		System.out.println("Straße eingeben");
		straße = sc.next();

		Standort standort = new Standort(gps, ort, postl, straße);
		return standort;

	}

	public String getGps() {
		return gps;
	}

	public void setGps(String gps) {
		this.gps = gps;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getPostleitzahl() {
		return postleitzahl;
	}

	public void setPostleitzahl(String postleitzahl) {
		this.postleitzahl = postleitzahl;
	}

	public String getStraße() {
		return straße;
	}

	public void setStraße(String straße) {
		this.straße = straße;
	}

	@Override
	public String toString() {
		return "Standort: " + gps + ", " + ort + ", " + postleitzahl + ", " + straße;
	}
}
