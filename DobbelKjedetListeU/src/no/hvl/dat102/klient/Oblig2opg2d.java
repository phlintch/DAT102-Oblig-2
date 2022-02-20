package no.hvl.dat102.klient;

import no.hvl.dat102.kjedet.DobbelKjedetOrdnetListe;

public class Oblig2opg2d {

	public static void main(String[] args) {
		Integer[] liste = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		DobbelKjedetOrdnetListe<Integer> dkl = new DobbelKjedetOrdnetListe<Integer>(-1, 10);
		for (Integer i : liste) {
			dkl.leggTil(i);
		}
		dkl.visListe();
		System.out.println(dkl.fins(1));
		System.out.println(dkl.fins(-1));
		System.out.println(dkl.fins(10));
		System.out.println(dkl.fins(111));
		System.out.println(dkl.fins(9));
		// Antar at vi bare skulle vise metodene vi lagde i opg (Fins(T el) og visListe())
	}
}
