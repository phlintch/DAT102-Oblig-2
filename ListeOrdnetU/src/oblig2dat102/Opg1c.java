package oblig2dat102;

import javax.swing.JOptionPane;

import no.hvl.dat102.adt.ListeADT;
import no.hvl.dat102.kjedet.KjedetOrdnetListe;
import no.hvl.dat102.listeklient.Person;
import no.hvl.dat102.tabell.TabellOrdnetListe;

public class Opg1c {
	private static KjedetOrdnetListe<Person> liste1;
	private static TabellOrdnetListe<Person> liste2;

	public static void main(String[] args) {
		liste1 = new KjedetOrdnetListe<Person>();
		liste2 = new TabellOrdnetListe<Person>();
		
		Person p1 = new Person("Magnus", "Henningsen", 1996);
		Person p2 = new Person("Sandra", "Dueland", 1998);
		Person p3 = new Person("Sindre", "Fonnes", 1996);
		Person p4 = new Person("Malin", "Henningsen", 1998);
		liste1.leggTil(p1);
		liste1.leggTil(p2);
		liste1.leggTil(p3);
		liste1.leggTil(p4);
		liste2.leggTil(p1);
		liste2.leggTil(p2);
		liste2.leggTil(p3);
		liste2.leggTil(p4);
		visPersoner(liste1, "KjedetOrdnetListe"); // feil med legg til antakelig
		visPersoner(liste2, "TabellOrdnetListe");

			String[] choices = new String[2];
			choices[0] = "KjedetOrdnetListe";
			choices[1] = "TabellOrdnetListe";
			int x =JOptionPane.showOptionDialog(null, "Velg hvilken liste du vil bruke", null, 0, JOptionPane.INFORMATION_MESSAGE, null, choices, 0);
			if (x == 0) {
				lesPerson(liste1);
				visPersoner(liste1, "KjedetOrdnetListe");
			} else if (x == 1) {
				lesPerson(liste2);
				visPersoner(liste2, "TabellOrdnetListe");
			}
		
		

	}

	public static void visPersoner(ListeADT<Person> liste, String type) {
		String mld = type+"\nFra eldste til yngste (Topp --> Bunn)\n";
		while(!liste.erTom()) {
			mld += liste.fjernFoerste() +"\n";
		}
		JOptionPane.showMessageDialog(null, mld);
	}
	public static void visPersoner(TabellOrdnetListe<Person> liste, String type) {
		String mld = type+"\nFra eldste til yngste (Topp --> Bunn)\n";
		while(!liste.erTom()) {
			mld += liste.fjernFoerste() +"\n";
		}
		JOptionPane.showMessageDialog(null, mld);
	}

	public static void lesPerson(KjedetOrdnetListe<Person> liste) {
		int pers = 0;
		boolean ferdig = false;
		while (!ferdig) {
			
			Person temp = new Person();
			String mld = "Skriv fornavn til person nr: " + (pers + 1);
			boolean godtatt = false;
			while (!godtatt) {

				String in = JOptionPane.showInputDialog(mld);
				if (lovlig(in)) {
					temp.setFornavn(in);
					godtatt = true;
				} else {
					mld += "\nNavn kan ikke være tomt eller inneholde tall.";
				}
			}
			godtatt = false;
			mld = "Skriv etternavn til person nr: " + (pers + 1);
			while (!godtatt) {
				String in = JOptionPane.showInputDialog(mld);
				if (lovlig(in)) {
					temp.setEtternavn(in);
					godtatt = true;
				} else {
					mld += "\nNavn kan ikke være tomt eller inneholde tall.";
				}
			}
			godtatt = false;
			mld = "Skriv fødselsår til person nr: " + (pers + 1);
			while (!godtatt) {
				int in = Integer.parseInt(JOptionPane.showInputDialog(mld));
				if (lovlig(in)) {
					temp.setFoedselsaar(in);
					godtatt = true;
				} else {
					mld += "\nMå være et heltall mellom 1900 og 2022";
				}
			}
			liste.leggTil(temp);
			pers++;
			if (pers == 4) {
				ferdig = true;
			}
		}
	}
	public static void lesPerson(TabellOrdnetListe<Person> liste) {
		int pers = 0;
		boolean ferdig = false;
		while (!ferdig) {
			
			Person temp = new Person();
			String mld = "Skriv fornavn til person nr: " + (pers + 1);
			boolean godtatt = false;
			while (!godtatt) {

				String in = JOptionPane.showInputDialog(mld);
				if (lovlig(in)) {
					temp.setFornavn(in);
					godtatt = true;
				} else {
					mld += "\nNavn kan ikke være tomt eller inneholde tall.";
				}
			}
			godtatt = false;
			mld = "Skriv etternavn til person nr: " + (pers + 1);
			while (!godtatt) {
				String in = JOptionPane.showInputDialog(mld);
				if (lovlig(in)) {
					temp.setEtternavn(in);
					godtatt = true;
				} else {
					mld += "\nNavn kan ikke være tomt eller inneholde tall.";
				}
			}
			godtatt = false;
			mld = "Skriv fødselsår til person nr: " + (pers + 1);
			while (!godtatt) {
				int in = Integer.parseInt(JOptionPane.showInputDialog(mld));
				if (lovlig(in)) {
					temp.setFoedselsaar(in);
					godtatt = true;
				} else {
					mld += "\nMå være et heltall mellom 1900 og 2022";
				}
			}
			liste.leggTil(temp);
			pers++;
			if (pers == 4) {
				ferdig = true;
			}
		}
	}

	public static boolean lovlig(int in) {
		return (in > 1899 && in < 2023);
	}

	public static boolean lovlig(String in) {
		if ((!in.equals("")) && !(harTall(in))) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean harTall(String in) {
		char[] y = in.toCharArray();
		for (char x : y) {
			if (Character.isDigit(x)) {
				return true;
			}
		}
		return false;
	}
}
