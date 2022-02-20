package Oppgave2Oblig;

import no.hvl.dat102.mengde.adt.MengdeADT;
import java.util.Iterator;

public class Medlem {
	private String navn;
	private MengdeADT<Hobby> hobbyer;
	private int statusIndeks;
	
	public Medlem() {
		this.navn = null;
		this.hobbyer = null;
		this.statusIndeks = -1;
	}
	
	public Medlem(String navn, MengdeADT<Hobby> hobbyer, int statusIndeks) {
		this.setNavn(navn);
		this.setHobbyer(hobbyer);
		this.setStatusIndeks(statusIndeks);
	}

	public int getStatusIndeks() {
		return statusIndeks;
	}

	public void setStatusIndeks(int statusIndeks) {
		this.statusIndeks = statusIndeks;
	}

	public MengdeADT<Hobby> getHobbyer() {
		return hobbyer;
	}

	public void setHobbyer(MengdeADT<Hobby> hobbyer) {
		this.hobbyer = hobbyer;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}	
	public boolean passerTil(Medlem medlem2) {
		return (this.getHobbyer().equals(medlem2.getHobbyer()));
	}
	public String hobString(MengdeADT<Hobby> m1) {
		Iterator<Hobby> teller = m1.iterator();
		String retur = "<";
		Hobby el;
		while(teller.hasNext()) {
			el = teller.next();
			retur += el.toString();
			if (teller.hasNext()) {
				retur += ", ";
			}
		}
		retur += ">";
		return retur;
	}
	@Override
	public String toString() {
		return getNavn() + ", " + getStatusIndeks() + ", " + hobString(getHobbyer());
	}
}
