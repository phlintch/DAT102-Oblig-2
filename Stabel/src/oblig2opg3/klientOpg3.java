package oblig2opg3;


import no.hvl.dat102.kjedet.KjedetStabel;

public class klientOpg3  {


	public static void main(String[] args) {

			Parentessjekker x = new Parentessjekker(new KjedetStabel<Character>());

			System.out.println(x.erBalansert("{{[[(([][]))]]}}")); // true
			System.out.println(x.erBalansert("{{{[[(([][]))]]}}")); // 1 igjen i stabel
			System.out.println(x.erBalansert("{{[[(([][]))]]}}}")); // treffer lukkesymbol, men stabel er tom
			System.out.println(x.erBalansert("{{[[(([][(]))]]}}")); // Danner ikke par.
			System.out.println(x.erBalansert("{{[{{{{{{{{()}}}}}}}}]}}"));  // true
			
			String asd = "public class KlientDobbelKjedetListe {\r\n"
					+ "\r\n"
					+ "	public static void main(String[] args) {\r\n"
					+ "		String ord[] = { \"o\", \"a\", \"s\", \"m\", \"e\", \"k\", \"c\" };\r\n"
					+ "\r\n"
					+ "		DobbelKjedetOrdnetListe<String> liste = new DobbelKjedetOrdnetListe(new String(\"AAA\"), new String(\"zzz\"));\r\n"
					+ "		// Klienten bør sjekke på at alle verdiene ligger innenfor grensene\r\n"
					+ "		// Legger data inn i listen\r\n"
					+ "		for (int i = 0; i < ord.length; i++) {\r\n"
					+ "			liste.leggTil(ord[i]);\r\n"
					+ "\r\n"
					+ "		}\r\n"
					+ "		// Utskrift foran\r\n"
					+ "		System.out.println(liste);\r\n"
					+ "\r\n"
					+ "		// Utskrift bak\r\n"
					+ "		System.out.println(liste.tilStrengBaklengs());\r\n"
					+ "\r\n"
					+ "		// slette m\r\n"
					+ "		liste.fjern(\"m\");\r\n"
					+ "\r\n"
					+ "		// Utskrift etter sletting\r\n"
					+ "		System.out.println(liste);\r\n"
					+ "\r\n"
					+ "		// slette t som ikek fins\r\n"
					+ "		liste.fjern(\"t\");\r\n"
					+ "		System.out.println(liste);\r\n"
					+ "\r\n"
					+ "	}\r\n"
					+ "\r\n"
					+ "}";
			
			System.out.println(x.erBalansert(asd));
			
	
	}
}
