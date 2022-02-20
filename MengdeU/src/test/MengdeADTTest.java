package test;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.kjedet.KjedetMengde;
import no.hvl.dat102.mengde.tabell.TabellMengde;

class MengdeADTTest {
	private Integer a0 = 0;
	private Integer a1 = 1;
	private Integer a2 = 2;
	private Integer a3 = 3;
	private Integer a4 = 4;
	private Integer a5 = 5;
	MengdeADT<Integer> m1k;
	MengdeADT<Integer> m2k;
	MengdeADT<Integer> m1t;
	MengdeADT<Integer> m2t;
	MengdeADT<Integer> testK;
	MengdeADT<Integer> testT;

	@BeforeEach
	void setUp() throws Exception {
		m1k = new KjedetMengde<Integer>();
		m2k = new KjedetMengde<Integer>();
		m1t = new TabellMengde<Integer>();
		m2t = new TabellMengde<Integer>();
		m1k.leggTil(a0);
		m1k.leggTil(a1);
		m1k.leggTil(a2);
		m2k.leggTil(a3);
		m2k.leggTil(a4);
		m2k.leggTil(a5);
		m1t.leggTil(a0);
		m1t.leggTil(a1);
		m1t.leggTil(a2);
		m2t.leggTil(a3);
		m2t.leggTil(a4);
		m2t.leggTil(a5);
		testK = new KjedetMengde<Integer>();
		testT = new TabellMengde<Integer>();
		testK.leggTilAlle(m1k);
		testK.leggTilAlle(m2k);
		testT.leggTilAlle(m1k);
		testT.leggTilAlle(m2k);
	}

	@Test
	void testUnion() {

		MengdeADT<Integer> resultK = (m1k.union(m2k));

		assertTrue(resultK.inneholder(a0));
		assertTrue(resultK.inneholder(a1));
		assertTrue(resultK.inneholder(a2));
		assertTrue(resultK.inneholder(a3));
		assertTrue(resultK.inneholder(a4));
		assertTrue(resultK.inneholder(a5));
		MengdeADT<Integer> resultK2 = m1k.union(m1k);
		assertTrue(m1k.equals(resultK2));
		MengdeADT<Integer> resultT = (m1t.union(m2t));

		assertTrue(resultT.inneholder(a0));
		assertTrue(resultT.inneholder(a1));
		assertTrue(resultT.inneholder(a2));
		assertTrue(resultT.inneholder(a3));
		assertTrue(resultT.inneholder(a4));
		assertTrue(resultT.inneholder(a5));
		MengdeADT<Integer> resultT2 = m1t.union(m1t);
		assertTrue(m1t.equals(resultT2));

	}

	@Test
	void testSnitt() {
		assertEquals(m1k.snitt(m2k).antall(), 0);
		assertEquals(m1t.snitt(m2t).antall(), 0);
		MengdeADT<Integer> resultK = testK.snitt(m1k);
		assertTrue(resultK.inneholder(a0));
		assertTrue(resultK.inneholder(a1));
		assertTrue(resultK.inneholder(a2));
		assertFalse(resultK.inneholder(a3));
		assertFalse(resultK.inneholder(a4));
		assertFalse(resultK.inneholder(a5));
		m1k.leggTil(a3);
		testK.leggTil(a3);
		testK.leggTil(a4);
		resultK = testK.snitt(m1k);
		assertTrue(resultK.inneholder(a0));
		assertTrue(resultK.inneholder(a1));
		assertTrue(resultK.inneholder(a2));
		assertTrue(resultK.inneholder(a3));
		assertFalse(resultK.inneholder(a4));
		assertFalse(resultK.inneholder(a5));
		MengdeADT<Integer> resultT = testT.snitt(m2t);
		assertTrue(resultT.inneholder(a3));
		assertTrue(resultT.inneholder(a4));
		assertTrue(resultT.inneholder(a5));
		assertFalse(resultT.inneholder(a0));
		assertFalse(resultT.inneholder(a1));
		assertFalse(resultT.inneholder(a2));
		m2t.leggTil(a0);
		testT.leggTil(a0);
		testT.leggTil(a1);
		resultT = testT.snitt(m2t);
		assertTrue(resultT.inneholder(a3));
		assertTrue(resultT.inneholder(a4));
		assertTrue(resultT.inneholder(a5));
		assertTrue(resultT.inneholder(a0));
		assertFalse(resultT.inneholder(a1));
		assertFalse(resultT.inneholder(a2));
		
	}

	@Test
	void testDifferens() {
		MengdeADT<Integer> resultK = testK.differens(m2k);
		assertTrue(resultK.inneholder(a0));
		assertTrue(resultK.inneholder(a1));
		assertTrue(resultK.inneholder(a2));
		assertFalse(resultK.inneholder(a3));
		assertFalse(resultK.inneholder(a4));
		assertFalse(resultK.inneholder(a5));
		MengdeADT<Integer> resultT = testT.differens(m2t);
		assertTrue(resultT.inneholder(a0));
		assertTrue(resultT.inneholder(a1));
		assertTrue(resultT.inneholder(a2));
		assertFalse(resultT.inneholder(a3));
		assertFalse(resultT.inneholder(a4));
		assertFalse(resultT.inneholder(a5));
		MengdeADT<Integer> testK2 = testK.differens(testK);
		MengdeADT<Integer> testT2 = testT.differens(testT);
		assertEquals(testK2.antall(), 0);
		assertEquals(testT2.antall(), 0);
	}

}
