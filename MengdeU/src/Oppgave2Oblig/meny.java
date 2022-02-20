package Oppgave2Oblig;

import static javax.swing.JOptionPane.*;

import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.kjedet.KjedetMengde;

public class meny {
	private Tekstgrensesnitt tekstgr;
	private Datakontakt dk;

	public meny(Datakontakt dk) {
		this.dk = dk;
		tekstgr = new Tekstgrensesnitt();
	}

	public void start() {
		setup();
		boolean ferdig = false;
		while (!ferdig) {
			int valg = valg(showInputDialog("Hva vil du gjøre?\n skriv 'Hjelp' for muligheter\n'Avslutt' for å avslutte."));
			if (valg == -1 || valg == 4) {
				if (valg == -1) {
					showMessageDialog(null, "Ikke godkjent input");
				} else {
					showMessageDialog(null,
							"Valg:\nNy -> lag nytt medlem\nParliste -> skriv ut alle par\nHobbyliste -> skriv ut hobbylisten til et meldem\nMedlemsliste -> skriv ut en liste av alle medlem\n"+
							"Date -> Finn en partner for et medlem om det eksisterer.");
							
				}
			} else if (valg == 1) {
				String x = tekstgr.skrivParListe(dk);
				showMessageDialog(null, x);
			} else if (valg == 2) {
				boolean x = false;
				while (!x) {
					String in = showInputDialog(
							"Hva heter medlemmet du vil ha hobbylisten til?\n" + tekstgr.skrivMedlemNavn(dk));

					if (dk.finnes(in) || in.toUpperCase().equals("avbryt".toUpperCase())) {
						if (dk.finnes(in)) {
							Medlem[] temp = dk.getDatakontakt();
							for (int i = 0; i < temp.length; i++) {
								if (temp[i].getNavn().toUpperCase().equals(in.toUpperCase())) {
									showMessageDialog(null, tekstgr.skrivHobbyListe(temp[i]));
								}
							}
						}
						x = true;
					} else {
						showMessageDialog(null, "Finner ikke medlem.\nSkriv 'avbryt' for å avbryte.");
					}
				}
			} else if (valg == 3) {
				showMessageDialog(null, tekstgr.skrivMedlemNavn(dk));
			} else if (valg == 5) {
				ferdig = true;
			} else if (valg == 0) {
				dk.leggTilMedlem(tekstgr.lesMedlem());
			}  else if (valg == 6) {
				String navn = showInputDialog("Hvilket medlem vil du finne partner til?");  	
				if (!(navn.equals(""))) {
					if (dk.finnes(navn)) {
						Medlem[] temp = dk.getDatakontakt();
						if (temp[dk.finnMedlemsIndeks(navn)].getStatusIndeks() != -1) {
							showMessageDialog(null, "Medlemmet har allerede en partner");
						} else {
							temp = dk.getDatakontakt();
							dk.finnPartnerFor(navn);
							showMessageDialog(null, navn + " dater nå " + temp[dk.partnerIndeks(navn)].getNavn());
						}
					} else {
						showMessageDialog(null, "Finner ikke medlem, prøv igjen.");
					}
				}
			}
		}
	}

	public int valg(String in) {
		String[] tab = new String[7]; // nytt medlem // skriv ut parliste // skriv hobbyliste // liste av medlem
		tab[0] = "Ny";
		tab[1] = "Parliste";
		tab[2] = "Hobbyliste";
		tab[3] = "Medlemsliste";
		tab[4] = "Hjelp";
		tab[5] = "Avslutt";
		tab[6] = "Date";
		for (int i = 0; i < tab.length; i++) {
			if (in.toUpperCase().equals(tab[i].toUpperCase())) {
				return i;
			}
		}
		return -1;
	}

	public void setup() {
		Hobby h1 = new Hobby("Spill");
		Hobby h2 = new Hobby("Film");
		Hobby h3 = new Hobby("Programmere");
		Hobby h4 = new Hobby("Løpe");
		Hobby h5 = new Hobby("Fotball");
		Hobby h6 = new Hobby("Drikke");
		Hobby h7 = new Hobby("Debatere");
		Hobby h8 = new Hobby("Konspirasjons teoretiker");
		MengdeADT<Hobby> hobbyer1 = new KjedetMengde<Hobby>();
		hobbyer1.leggTil(h1);
		hobbyer1.leggTil(h2);
		hobbyer1.leggTil(h3);
		MengdeADT<Hobby> hobbyer2 = new KjedetMengde<Hobby>();
		hobbyer2.leggTil(h4);
		hobbyer2.leggTil(h5);
		hobbyer2.leggTil(h6);
		MengdeADT<Hobby> hobbyer3 = new KjedetMengde<Hobby>();
		hobbyer3.leggTil(h6);
		hobbyer3.leggTil(h7);
		hobbyer3.leggTil(h4);
		MengdeADT<Hobby> hobbyer4 = new KjedetMengde<Hobby>();
		hobbyer4.leggTil(h7);
		hobbyer4.leggTil(h8);
		hobbyer4.leggTil(h2);
		Medlem x1 = new Medlem("Magnus", hobbyer1, -1);
		Medlem x2 = new Medlem("Sandra", hobbyer1, -1);
		Medlem x3 = new Medlem("Reidun", hobbyer3, -1);
		Medlem x4 = new Medlem("John", hobbyer3, -1);
		Medlem x5 = new Medlem("Luke", hobbyer2, -1);
		Medlem x6 = new Medlem("Haruka", hobbyer2, -1);
		Medlem x7 = new Medlem("Lonely bob", hobbyer4, -1);
		Datakontakt base = new Datakontakt();
		base.leggTilMedlem(x1);
		base.leggTilMedlem(x2);
		base.leggTilMedlem(x3);
		base.leggTilMedlem(x4);
		base.leggTilMedlem(x5);
		base.leggTilMedlem(x6);
		base.leggTilMedlem(x7);
		base.finnPartnerFor(x1.getNavn());
		base.finnPartnerFor(x3.getNavn());
		base.finnPartnerFor(x5.getNavn());
		base.finnPartnerFor(x7.getNavn());
		dk = base;
	}
}
