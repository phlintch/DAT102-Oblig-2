package no.hvl.dat102.kjedet;

import no.hvl.dat102.adt.OrdnetListeADT;
import no.hvl.dat102.exceptions.EmptyCollectionException;

/**
 * 
 * @param <T> elementypen
 */
public class KjedetOrdnetListe<T extends Comparable<T>> implements OrdnetListeADT<T> {
	private int antall;
	private LinearNode<T> foerste, siste;

	/**
	 * Lager en ny tom liste.+
	 */
	public KjedetOrdnetListe() {
		antall = 0;
		foerste = null;
		siste = null;
	}

	@Override
	public T fjernFoerste() {
		if (erTom()) {
			throw new EmptyCollectionException("ordnet liste");
		}
		T resultat = foerste.getElement();
		foerste = foerste.getNeste();
		antall--;
		return resultat;
	}

	public LinearNode<T> getForgje(LinearNode<T> el) {
		LinearNode<T> temp = foerste;
		LinearNode<T> retur = null;
		if (el.equals(foerste)) {
			return null;
		} else {
			while (true) {
				retur = temp;
				temp = temp.getNeste();
				if (temp.equals(el)) {
					return retur;
				}
			}
		}
	}

	@Override
	public T fjernSiste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");
		if (siste != null) {
			T resultat = siste.getElement();
			siste = getForgje(siste);
			antall--;
			return resultat;
		} else if (antall == 1) {
			return fjernFoerste();
		}
		return null;
	}

	@Override
	public T foerste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T svar = foerste.getElement();

		return svar;
	}

	@Override
	public T siste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T resultat = siste.getElement();

		return resultat;
	}

	@Override
	public boolean erTom() {
		return antall == 0;
	}

	@Override
	public int antall() {
		return antall;
	}

	@Override
	public void leggTil(T element) {

		if (erTom()) {
			LinearNode<T> ny = new LinearNode();
			ny.setElement(element);
			foerste = ny;
			siste = ny;
			antall++;

		} else {
			LinearNode<T> el = foerste;
			LinearNode<T> prev = null;
			boolean added = false;

			while (!added) {
				if (el == null) {
					LinearNode<T> ny = new LinearNode();
					ny.setElement(element);	
					prev.setNeste(ny);
					siste = ny;
					antall++;
					added = true;
				} else if (element.compareTo(el.getElement()) < 0) {
					LinearNode<T> ny = new LinearNode();
					ny.setElement(element);
					if (prev == null) {
						foerste = ny;
					}
					if (!(prev == null)) {
						prev.setNeste(ny);
					}
					ny.setNeste(el);
					antall++;
					added = true;
				}
				if (!added) {
					prev = el;
					el = el.getNeste();
				}

			}
		}
	}

	@Override
	public T fjern(T element) {
		T svar = null;
		LinearNode<T> forrige = null, denne = foerste;
		while (denne != null && element.compareTo(denne.getElement()) > 0) {
			forrige = denne;
			denne = denne.getNeste();
		}
		if (denne != null && element.equals(denne.getElement())) { // funnet
			antall--;
			svar = denne.getElement();
			if (forrige == null) { // Første element
				foerste = foerste.getNeste();
				if (foerste == null) { // Tom liste
					siste = null;
				}
			} else { // Inni listen eller bak
				forrige.setNeste(denne.getNeste());
				if (denne == siste) { // bak
					siste = forrige;
				}
			}
		} // ikke-funn
		return svar;
	}

	@Override
	public boolean inneholder(T element) {
		LinearNode<T> denne = foerste;
		boolean resultat = false;
		while (denne != null && element.compareTo(denne.getElement()) > 0) {
			denne = denne.getNeste();
		}
		if (denne != null) {
			if (element.equals(denne.getElement())) {
				resultat = true;
			}
		} // ikke-funn
		return resultat;
	}

}// class
