package no.hvl.dat102.tabell;

import no.hvl.dat102.adt.OrdnetListeADT;
import no.hvl.dat102.exceptions.EmptyCollectionException;

public class TabellOrdnetListe<T extends Comparable<T>> implements OrdnetListeADT<T> {

	private final static int STDK = 100;
	private final static int IKKE_FUNNET = -1;
	private int bak;
	private T[] liste;

	public TabellOrdnetListe() {
		this(STDK);
	}

	public TabellOrdnetListe(int startKapasitet) {
		bak = 0;
		liste = (T[]) (new Comparable[startKapasitet]);
	}

	@Override
	public T fjernSiste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T resultat = null;
		resultat = liste[bak-1];
		bak = bak-1;
		return resultat;
	}

	@Override
	public T fjernFoerste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T resultat = null;
		resultat = fjern(liste[0]);
		sorter();
		return resultat;
	}
	public void sorter()  {
		T[] temp = (T[])(new Comparable[liste.length]);
		int teller = 0;
		for (int i = 0; i < liste.length; i++) {
			if (liste[i] != null) {
				temp[teller] = liste[i];
				teller++;
			}
		}
		liste = temp;
		
	}
	@Override
	public T foerste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T resultat = liste[0];
		return resultat;
	}

	@Override
	public T siste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");
		
		T resultat = null;
		resultat = liste[bak-1];
		return resultat;
	}

	@Override
	public boolean erTom() {
		return (bak == 0);
	}

	@Override
	public int antall() {
		return bak;
	}

	@Override
	public void leggTil(T element) {
		if (erTom()) {
			liste[bak] = element;
			bak++;
		} else {
			boolean added = false;
			for(int i = 0; i < STDK-1 && !added; i++) {
				if (liste[i] == null) {
					liste[i] = element;
					bak++;
					added=true;
				}
				else if (element.compareTo(liste[i]) < 0 ) {
					T el = liste[i];
					liste[i] = element;
					dyttFram(i+1, el);
					bak++;
					added= true;
				}
			}
		}
	}
	public void dyttFram(int index, T el) {
		for (int i = index; i < bak+1; i++) {
			T temp = liste[i];
			liste[i] = el;
			el = temp;
		}
	}

	@Override
	public boolean inneholder(T element) {
		return (finn(element) != IKKE_FUNNET);
	}

	@Override
	public T fjern(T element) {
		int index = finn(element);
		if (index != IKKE_FUNNET) {
			liste[index] = null;
			
		} else {
			return null;
		}
		T[] temp = (T[]) (new Comparable[liste.length]);
		int teller = 0;
		for (int i = 0; i < bak; i++) {
			if (liste[i] != null) {
				temp[teller] = liste[i];
				teller++;
			}
		}
		bak = teller;
		liste = temp;
		return element;
	}

	private int finn(T el) {
		int i = 0, resultat = IKKE_FUNNET;
		boolean funnet = false;
		for (; i < bak && !funnet; i++) {
			if (liste[i]==el) {
				resultat = i;
				funnet = true;
			}
		}
		return resultat;
	}

	public String toString() {
		String resultat = "";

		for (int i = 0; i < bak; i++) {
			resultat = resultat + liste[i].toString() + "\n";
		}
		return resultat;
	}

	private void utvid() {
		T[] hjelpeTabell = (T[]) (new Comparable[liste.length * 2]);

		for (int i = 0; i < liste.length; i++) {
			hjelpeTabell[i] = liste[i];
		}

		liste = hjelpeTabell;
	}

}// class
