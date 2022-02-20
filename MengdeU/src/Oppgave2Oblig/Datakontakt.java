package Oppgave2Oblig;

public class Datakontakt {
	private Medlem[] tab;
	private int antall;
	
	public Datakontakt() {
		tab = new Medlem[100];
		antall = 0;
	}
	public Datakontakt(int size) {
		tab = new Medlem[size];
		antall = 0;
	}
	public int getAntall() { return antall; }
	
	public Medlem[] getDatakontakt() { return tab; }
	public boolean fjernMedlem(Medlem m) {
		boolean funnet = false;
		for (int i = 0; i < antall && !funnet; i++) {
			if (tab[i].equals(m)) {
				tab[i] = null;
				antall--;
				sorter();
				funnet = true;
			}
		}
		return funnet;
	}
	public void sorter() {
		Medlem[] temp = new Medlem[antall];
		int teller = 0;
		for (int i = 0; i < tab.length; i++) {
			if (tab[i] != null) {
				temp[teller] = tab[i];
				teller++;
			}
		}
		tab = temp;
	}
	
	public void leggTilMedlem(Medlem person) {
		if (antall >= tab.length) {
			utvid();
			leggTilMedlem(person);
		} else {
			tab[antall] = person;
			antall++;
		}
	}
	public int finnMedlemsIndeks(String medlemsnavn) {
		int retur = -1;
		boolean funnet = false;
		
		for (int i = 0; i < tab.length && !funnet; i++) {
			if (tab[i] != null) {
			if (medlemsnavn.toUpperCase().trim().equals(tab[i].getNavn().toUpperCase().trim())) {
				retur = i;
				funnet = true;
			}
			}
		}
		return retur;
	}
	public boolean finnes(String medlemsnavn) {
		boolean retur = false;
		for (int i = 0; i < tab.length && !retur; i++) {
			if (tab[i].getNavn().toUpperCase().equals(medlemsnavn.toUpperCase())) {
				retur = true;
			}
		}
		return retur;
	
		}
	public int finnPartnerFor (String medlemsnavn) {
		int indeks = finnMedlemsIndeks(medlemsnavn);
		int returIndeks = -1;
		Medlem subjekt = tab[indeks];
		boolean funnet = false;
		
		for (int i = 0; i < tab.length && !funnet; i++) {
			if (tab[i] != null) {
			if (subjekt.passerTil(tab[i]) && tab[i].getStatusIndeks() == -1 && tab[i] != subjekt) {
				
				tab[i].setStatusIndeks(indeks);
				tab[indeks].setStatusIndeks(i);
				returIndeks = i;
				funnet = true;
			}
		}
		}
		return returIndeks;
	}
	public int partnerIndeks(String medlemsnavn) {
		Medlem subjekt = tab[finnMedlemsIndeks(medlemsnavn)];
		return subjekt.getStatusIndeks();
	}
	public void tilbakestillStatusIndeks(String medlemsnavn) {
		int i = finnMedlemsIndeks(medlemsnavn);
		if (i != -1) {
			if (tab[i].getStatusIndeks() != -1) {
				tab[tab[i].getStatusIndeks()].setStatusIndeks(-1);
				tab[i].setStatusIndeks(-1);
			}
		}
	}
	public void utvid() {
		Medlem[] temp = new Medlem[tab.length*2];
		for (int i = 0; i < antall; i++) {
			temp[i] = tab[i];
		}
		tab = temp;
	}
}
