package Oppgave2Oblig;



public class Main {
	
	
	public static void main (String[] args) {
		Datakontakt dk = new Datakontakt();
		meny meny = new meny(dk);
		meny.start();
	}
}
