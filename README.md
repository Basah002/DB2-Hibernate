# Tesla Datenbankanwendung

Im Rahmen des Moduls DB2 im Studiengang Praktische Informatik haben wir im laufenden Semester eine relationale Datenbank mittels Java und Hibernate erstellt.

Unser Anwendungsszenario konzentriert sich auf einen wichtigen Teil der Tesla-Infrastruktur: das Netzwerk der Ladestationen und den Servicebereich des Unternehmens. Die Datenbank liefert Informationen über Standorte von Ladesäulen, Statistiken zu einzelnen Stationen sowie über die Servicehistorie der Fahrzeuge. Diese Daten helfen Tesla bei der Optimierung der Infrastruktur und der Organisation des Fahrzeugservices.

Die Datenbank enthält mehrere Relationen, wobei die Relation „Person“ als Oberklasse modelliert wurde. Daraus gehen die Relationen „Mitarbeiter“ und „Kunde“ hervor. Die Relation „Kunde“ enthält zusätzlich das Attribut „Fahrgestellnummer“, welches als Fremdschlüssel auf die Relation „Fahrzeug“ verweist. Ein Fahrzeug wird durch Attribute wie Kilometerstand, Batteriekapazität und weitere Merkmale beschrieben. Alle durchgeführten Wartungen werden in der „Servicehistorie“ festgehalten.

Kunden können ihre Fahrzeuge an beliebigen Ladestationen aufladen, sowohl direkt nach einem Service als auch unabhängig davon. Die Ladeinfrastruktur besteht aus verschiedenen Standorten innerhalb und außerhalb von Städten. In der Datenbank ist außerdem die Verfügbarkeit der Ladestationen gespeichert, damit Kunden vorab prüfen können, ob eine Station betriebsbereit oder in Wartung ist.

Zusätzlich verwaltet die Datenbank Informationen zu akzeptierten Bezahlmethoden, z. B. Kreditkarte oder mobile Zahlungsdienste, um einen reibungslosen Bezahlvorgang zu gewährleisten. Einige Ladestationen bieten ergänzende Dienstleistungen wie Restaurants oder andere Einrichtungen, um das Ladeerlebnis für Kunden komfortabler zu gestalten.
