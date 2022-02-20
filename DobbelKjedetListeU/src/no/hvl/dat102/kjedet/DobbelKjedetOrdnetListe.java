package no.hvl.dat102.kjedet;

import no.hvl.dat102.adt.DobbelKjedetOrdnetListeADT;
import no.hvl.dat102.exceptions.EmptyCollectionException;

public class DobbelKjedetOrdnetListe<T extends Comparable<T>> implements DobbelKjedetOrdnetListeADT<T> {
	private DobbelNode<T> foerste;
	private DobbelNode<T> siste;
	private int antall;

	public DobbelKjedetOrdnetListe(T minVerdi, T maksVerdi) {
		foerste = new DobbelNode<T>();
		foerste.setElement(minVerdi);
		siste = new DobbelNode<T>();
		siste.setElement(maksVerdi);
		foerste.setNeste(siste);
		siste.setForrige(foerste);
		antall= 0;
	}

	@Override
	public void leggTil(T el) {
		DobbelNode<T> nyNode = new DobbelNode<T>(el);
		DobbelNode<T> aktuell = foerste.getNeste();
		while ((el.compareTo(aktuell.getElement()) > 0)) {
			aktuell = aktuell.getNeste();
		}
		// Legg inn foran aktuell
		nyNode.setNeste(aktuell);
		nyNode.setForrige(aktuell.getForrige());
		aktuell.getForrige().setNeste(nyNode);
		aktuell.setForrige(nyNode);
		antall++;

	}

	@Override
	public T fjern(T el) {
		T resultat = null;
		if (erTom())
			throw new EmptyCollectionException("dobbelkjedet ordnet liste er tom");
		DobbelNode<T> aktuell = finn(el);
		if (aktuell != null) {// returner og slett
			resultat = aktuell.getElement();
			aktuell.getForrige().setNeste(aktuell.getNeste());
			aktuell.getNeste().setForrige(aktuell.getForrige());

		}

		return resultat;

	}

	/*
	 * Returnerer referansen til noden hvis el fins, ellers returneres
	 * null-referansen
	 */
	public boolean fins(T el) {
		if (finn(el) == null) {
			return false;
		} else {
			return true;
		}
	}
	public void visListe() {

		String[] temp = new String[antall];
		DobbelNode<T> tempF = foerste.getNeste();
		DobbelNode<T> tempS = siste.getForrige();
		for (int i = 0; i < antall/2 && temp[i] == null; i++) {
			temp[i] = tempF.getElement().toString();
			tempF = tempF.getNeste();
			temp[antall-1-i] = tempS.getElement().toString();
			tempS = tempS.getForrige();
		}
		for (String x : temp) {
			System.out.println(x);
		}
		
	}
	private DobbelNode<T> finn(T el) {
		boolean funnet = false;
		DobbelNode<T> tempF = foerste.getNeste();
		DobbelNode<T> tempS = siste.getForrige();
		DobbelNode<T> retur = null;
		while (!funnet) {
			if (tempF.getElement().equals(el)) {
				funnet = true;
				retur = tempF;
			} else {
				tempF = tempF.getNeste();
			}
			if (tempS.getElement().equals(el)) {
				funnet = true;
				retur = tempS;
			} else {
				tempS = tempS.getForrige();
			}
			if (tempS.equals(foerste) || tempF.equals(siste)) {
				return null;
			}
		}
		return retur;

	}

	@Override
	public boolean erTom() {
		return (antall == 0);
	}

	@Override
	public int antall() {
		return antall;
	}

	public String toString() {
		String resultat = "";
		DobbelNode<T> aktuell = foerste.getNeste();
		while (aktuell != siste) {

			resultat = resultat + aktuell.getElement().toString();
			aktuell = aktuell.getNeste();
		}

		return resultat;
	}

	public String tilStrengBaklengs() {
		String resultat = "";
		DobbelNode<T> aktuell = siste.getForrige();
		while (aktuell != foerste) {

			resultat = resultat + aktuell.getElement().toString();
			aktuell = aktuell.getForrige();
		}

		return resultat;

	}

}