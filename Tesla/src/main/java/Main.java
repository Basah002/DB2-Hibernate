
import java.util.*;

import org.hibernate.internal.build.AllowSysOut;

public class Main {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		int x;
		boolean tmp = false;

		do {
			System.out.println("1. Daten anzeigen lassen\n" + "2. Daten einfuegen\n" + "3. Daten löschen\n"
					+ "4. Vorschau mit vorhandenden Daten\n" + "5. Programm beenden" + "\nBitte eine Zahl eingeben: ");

			x = sc.nextInt();
			switch (x) {

			case 1:
				datenAnzeigen();
				break;
			case 2:
				dateneinfuegen();

				break;
			case 3:
				datenLöschen();
				break;
			case 4:
				init();
				break;
			case 5:
				tmp = true;

			}
		} while (!tmp);
		System.out.println("geschafft");

	}

	private static void dateneinfuegen() {

		int x;
		boolean tmp = false;
		do {
			System.out.println("1. Kunden einfügen\n" + "2. Mitarbeiter einfügen\n" + "3. Ladestation einfügen\n"
					+ "4. Fahrzeug hinzufügen\n" + "5. Auto aufladen\n" + "6. zurück zum Hauptmenü");
			x = sc.nextInt();

			switch (x) {
			case 1:
				Kunde.addKunde();

				break;
			case 2:
				Mitarbeiter.addMitarbeiter();

				break;
			case 3:

				Ladestation.addLadestation();

				break;
			case 4:
				Fahrzeug.addFahrzeug();
				break;
			case 5:
				autoLädtAuf();
				break;
			case 6:
				tmp = true;
				break;

			}

		} while (!tmp);

	}

	private static void datenLöschen() {

		String tabellenName;
		int id, tmp;
		;
		System.out.println("Diese Tabellen stehen Ihnen zur Verfügung\n" + "1. Kunde\n" + "2. Mitarbeiter\n"
				+ "3. Fahrzeug\n" + "4. Ladestation");

		System.out.println("Aus welcher Relation möchtest du ein Tupel löschen, bitte eine Zahl eingeben");
		tmp = sc.nextInt();
		switch (tmp) {
		case 1:
			ManipulateTable.tabelleAnzeigen("Kunde");
			System.out.println("Welches Tupel möchtest du löschen?");
			System.out.println("Bitte geben sie die id an");
			id = sc.nextInt();
			ManipulateTable.tupelLoeschen("Kunde", id);
			break;
		case 2:
			ManipulateTable.tabelleAnzeigen("Mitarbeiter");
			System.out.println("Welches Tupel möchtest du löschen?");
			System.out.println("Bitte geben sie die id an");
			id = sc.nextInt();
			ManipulateTable.tupelLoeschen("Mitarbeiter", id);
			break;
		case 3:
			ManipulateTable.tabelleAnzeigen("Fahrzeug");
			System.out.println("Welches Tupel möchtest du löschen?");
			System.out.println("Bitte geben sie die id an");
			id = sc.nextInt();
			
			ManipulateTable.fahrzeugLoeschenVonHalter((int)ManipulateTable.getFahrzeug(id).getKunde().getId(),id);

		//	ManipulateTable.tupelLoeschen("Fahrzeug", id);
			break;
		case 4:
			ManipulateTable.tabelleAnzeigen("Ladestation");
			System.out.println("Welches Tupel möchtest du löschen?");
			System.out.println("Bitte geben sie die id an");
			id = sc.nextInt();
			ManipulateTable.ladestationLoeschen(id);
			//ManipulateTable.tupelLoeschen("Ladestation", id);
			break;
		default:
			break;

		}

	}

	private static void datenAnzeigen() {
		boolean tmp = false;
		int x, wahl = 0;
		int pid = 0;
		String tabellenName = null;
		String vorname = null;
		String nachname = null;
		do {
			System.out.println("1. Alle Tabellen anzeigen\n" + "2. Gewünschte Tabellen anzeigen\n" + "3. Tupel\n"
					+ "4. Zurück zum Hauptmenü ");
			x = sc.nextInt();

			switch (x) {
			case 1:

				ManipulateTable.alletabelleAnzeigen();

				break;
			case 2:
				System.out.println("Diese Tabellen stehen Ihnen zur Verfügung\n" + "Kunde\n" + "Mitarbeiter\n"
						+ "Fahrzeug\n" + "Ladestation\n" + "Fahrzeug_Ladestation");
				tabelleAnzeigen();
				break;
			case 3:
				// TODO
				System.out.println("Aus welcher Tabelle möchten sie ein Tupel ausgeben?");
				System.out.println("1.Fahrzeug");
				System.out.println("2.Kunde");
				System.out.println("3.Mitarbeiter");
				System.out.println("4.Ladestation");
				wahl = sc.nextInt();
				switch (wahl) {
				case 1:
					if (Fahrzeug.anzahl > 0) {
						System.out.println("Bitte geben sie die Fahrgestelnummer an");
						pid = sc.nextInt();
						ManipulateTable.tupelAnzeigen("Fahrzeug", pid);

					} else
						System.out.println("In der Tabelle gibt es keinen Eintrag");
					break;

				case 2:
					System.out.println("Wonach möchten sie den Kunden ausgeben ?");
					System.out.println("1.id");
					System.out.println("2.Vornamen");
					System.out.println("3.Nachname");
					wahl = sc.nextInt();
					if (wahl == 1) {
						System.out.println("Bitte geben sie die PID des Kunden ein");
						pid = sc.nextInt();
						ManipulateTable.tupelAnzeigen("Kunde", pid);
					} else if (wahl == 2) {
						System.out.println("Bitte geben sie den Vornamen des Kunden ein");
						vorname = sc.next();
						ManipulateTable.tupelAnzeigenNachVorname("Kunde", vorname);
					} else {
						System.out.println("Bitte geben sie den Nachnamen des Kunden ein");
						nachname = sc.next();
						ManipulateTable.tupelAnzeigenNachNachname("Kunde", nachname);
					}
					break;
				case 3:
					System.out.println("Wonach möchten sie den Mitarbeiter ausgeben ?");
					System.out.println("1.id");
					System.out.println("2.Vornamen");
					System.out.println("3.Nachname");
					wahl = sc.nextInt();
					if (wahl == 1) {
						System.out.println("Bitte geben sie die PID des Mitarbeiters ein");
						pid = sc.nextInt();
						ManipulateTable.tupelAnzeigen("Mitarbeiter", pid);
					} else if (wahl == 2) {
						System.out.println("Bitte geben sie den Vornamen des Mitarbeiters ein");
						vorname = sc.next();
						ManipulateTable.tupelAnzeigenNachVorname("Mitarbeiter", vorname);
					} else {
						System.out.println("Bitte geben sie den Nachnamen des Mitarbeiters ein");
						nachname = sc.next();
						ManipulateTable.tupelAnzeigenNachNachname("Mitarbeiter", nachname);
					}
					break;
				case 4:
					System.out.println("Bitte geben sie die LadestationID ein");
					pid = sc.nextInt();
					ManipulateTable.tupelAnzeigen("Ladestation", pid);
					break;

				default:
					break;
				}
				break;
			case 4:
				tmp = true;
				break;
			}

		} while (!tmp);

	}

	private static void autoLädtAuf() {

		System.out.println("Die Relation Fahrzeug ");
		ManipulateTable.tabelleAnzeigen("Fahrzeug");
		System.out.println("Welches Fahrzeug soll geladen werden-Bitte id eingeben");
		int id = sc.nextInt();
		System.out.println("Die Relation Ladestation ");
		ManipulateTable.tabelleAnzeigen("Ladestation");
		System.out.println("Welche Ladesäule soll genutz werden-Bitte id eingeben");
		int id2 = sc.nextInt();

		ManipulateTable.addFahrzeugToLadestation(ManipulateTable.getFahrzeug(id), ManipulateTable.getLadestation(id2));

	}

	private static void tabelleAnzeigen() {
		System.out.println("Bitte geben Sie ihre Tabelle ein, die Sie haben möchten.");
		String tmp2 = sc.next();
		if (tmp2.equals("Fahrzeug_Ladestation"))
			ManipulateTable.anzeigenFahrzeugLadestation();

		else
			ManipulateTable.tabelleAnzeigen(tmp2);
	}

	private static void init() {

		Kunde a = new Kunde("Ayyoub", "Elamiri", "Herr", "abc@web.de");
		Kunde b = new Kunde("Ayyoub", "elamiri2", "Herr", "abc@web.de");
		Kunde c = new Kunde("Momo", "aydogdu", "Herr", "abc2@web.de");

		ManipulateTable.kundeEinfuegen(a);
		ManipulateTable.kundeEinfuegen(b);
		ManipulateTable.kundeEinfuegen(c);

		System.out.println("Kunde wurde eingefügt");

		Fahrzeug fahrzeug = new Fahrzeug("model3", 2019, 20999, 78, a);
		Fahrzeug model3 = new Fahrzeug("teslaplaid", 2022, 12, 85, a);
		Fahrzeug modelY = new Fahrzeug("modely", 2019, 12, 85, c);

		Standort essen = new Standort("192,122,12", "44147", "musterstraße2", "essen");
		Standort dortmund = new Standort("192,122,12", "44147", "musterstraße3", "dortmund");
		Standort berlin = new Standort("192,122,12", "44147", "kreuzstraße", "berlin");


		Ladestation ladestation1 = new Ladestation(250, 0.33, essen);
		Ladestation ladestation2 = new Ladestation(100, 3.0, dortmund);

		Ladestation ladestation3=new Ladestation(900, 0.45, berlin);
		ladestation1.addBezahlmethode(Bezahlmethode.BANKÜBERWEISUNG);
		ladestation1.addBezahlmethode(Bezahlmethode.KREDITKARTE);

		ladestation2.addBezahlmethode(Bezahlmethode.BANKÜBERWEISUNG);
		ladestation2.addBezahlmethode(Bezahlmethode.BARZAHLUNG);
		
		ladestation3.addBezahlmethode(Bezahlmethode.AMERICANEXPRESS);
		ladestation3.addBezahlmethode(Bezahlmethode.KLARNA);

		System.out.println("Bezahlmethode wurde eingefügt");

		ManipulateTable.ladestationEinfuegen(ladestation1);
		ManipulateTable.ladestationEinfuegen(ladestation2);
		ManipulateTable.ladestationEinfuegen(ladestation3);

		System.out.println("Ladestation wurde eingefügt");

		ManipulateTable.fahrzeugEinfuegen(model3);
		ManipulateTable.fahrzeugEinfuegen(fahrzeug);
		ManipulateTable.fahrzeugEinfuegen(modelY);
		System.out.println("Fahrzeug wurde eingefügt");

		ManipulateTable.addFahrzeugToLadestation(model3, ladestation1);
		ManipulateTable.addFahrzeugToLadestation(model3, ladestation2);
		ManipulateTable.addFahrzeugToLadestation(fahrzeug, ladestation1);
		
		System.out.println("Fahrzeug wurde an Ladestation aufgeladen");
		Mitarbeiter batu = new Mitarbeiter("Batuhan", "sahin", "Herr", "abc@web.de", "Kfz", "Mechaniker");
		Mitarbeiter resul = new Mitarbeiter("resul", "süngüoglu", "Herr", "abc@web.de", "büro", "angestellter");
		ManipulateTable.mitarbeiterEinfuegen(batu);
		ManipulateTable.mitarbeiterEinfuegen(resul);
		

	}

}
