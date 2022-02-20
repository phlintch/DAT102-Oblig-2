package Oppgave2Oblig;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.kjedet.KjedetMengde;

public class Tekstgrensesnitt {

	public Medlem lesMedlem() {
		boolean godtatt = false;
		Medlem retur = new Medlem();
		String melding = "Skriv inn navnet til medlemmet";
		while (!godtatt) {
			godtatt = true;
			String navn = JOptionPane.showInputDialog(melding);
			if (navn.equals("")) {
				godtatt = false;
				melding = "Blankt navn er ikke godtatt.";
			}
			char[] bkst = navn.toCharArray();
			boolean inneholder = false;
			for (char x : bkst) {
				if (Character.isDigit(x)) {
					inneholder = true;
					godtatt = false;
				}
			}
			if (inneholder) {
				melding = "Tall er ikke godtatt i navn.";
			}
			if (godtatt) {
				retur.setNavn(navn);
			}

		}
		godtatt = false;
		melding = "Skriv medlemmets hobbyer, skriv ferdig for å avslutte etter en hobby har blitt lagt til.";
		MengdeADT<Hobby> mengde = new KjedetMengde<Hobby>();
		int teller = 0;
		while (!godtatt) {

			String input = JOptionPane.showInputDialog(melding);
			if (input.equals("")) {
				godtatt = false;
				melding = "Godtar ikke navnløse hobbyer. skriv ferdig for å avslutte om en hobby har blitt lagt til.";
			} else if (input.toUpperCase().equals("FERDIG") && teller != 0) {
				godtatt = true;
				retur.setHobbyer(mengde);
			} else if (input.toUpperCase().equals("FERDIG") && teller == 0) {
				godtatt = false;
				melding = "Må ha minst en hobby, skriv 'break' for å avslutte uansett";
			} else if (input.toUpperCase().equals("BREAK")) {
				return null;
			} else {
				mengde.leggTil(new Hobby(input));
				teller++;
			}
		}
		JOptionPane.showMessageDialog(null, retur.getNavn() +" Lagt til.");
		return retur;

	}

	public String skrivHobbyListe(Medlem medlem) {
		System.out.println("Alle hobbyene ");
		System.out.println(medlem.hobString(medlem.getHobbyer()));
		return "Alle hobbyene :\n" + medlem.hobString(medlem.getHobbyer());
	}
	public static boolean listetOpp(int x, int[] xtab) {
		boolean retur = false;
		for (int i = 0; i < xtab.length && retur; i++) {
			if (xtab[i] == x) {
				retur = true;
			}
		}
		return retur;
	}
	public Medlem Medlem(String navn, Datakontakt arkiv) {
		Medlem[] temp = arkiv.getDatakontakt();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].getNavn().toUpperCase().equals(navn.toUpperCase())) {
				return temp[i];
			} 
		}
		return new Medlem();
	}
	public String skrivUtMedlem(Datakontakt arkiv) {
		Medlem[] temp = arkiv.getDatakontakt();
		String retur = "";
		for (Medlem x: temp) {
			retur += x.toString()+"\n";
		}
		System.out.println(retur);
		return retur;
	}
	public String skrivMedlemNavn(Datakontakt arkiv) {
		arkiv.sorter();
		Medlem[] temp = arkiv.getDatakontakt();
		String retur = "";
		for (Medlem x: temp) {
			retur += x.getNavn()+"\n";
		}
		System.out.println(retur);
		return retur;
	}
	public  String skrivParListe(Datakontakt arkiv) {
				String temp = "Par Navn:     Hobbyer:\n";
				ArrayList<Integer> ind = new ArrayList<Integer>();
				arkiv.sorter();
				Medlem[] medtab = arkiv.getDatakontakt();
				for (int i = 0; i < medtab.length; i++) {
					System.out.println("ute");
					if (!medtab[i].equals(null)) {
						if (medtab[i].getStatusIndeks() != -1 && !ind.contains(i)) {
							temp += (medtab[i].getNavn() + " og " + medtab[medtab[i].getStatusIndeks()].getNavn()+"     "+medtab[i].hobString(medtab[i].getHobbyer())+"\n");
							ind.add(i);
							ind.add(medtab[i].getStatusIndeks());
						}
					}
				}
				return temp;
				
	}
}











