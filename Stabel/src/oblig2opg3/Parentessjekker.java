package oblig2opg3;

import no.hvl.dat102.kjedet.KjedetStabel;

public class Parentessjekker implements IParentessjekker {

	private KjedetStabel<Character> stb;
	private int antall;

	public Parentessjekker(KjedetStabel<Character> stb) {

		this.stb = stb;
		this.antall = 0;
	}

	@Override
	public boolean erVenstreparentes(char p) {
		return (p == '(' || p == '[' || p == '{');
	}

	@Override
	public boolean erHogreparentes(char p) {
		return (p == ')' || p == ']' || p == '}');
	}

	@Override
	public boolean erParentes(char p) {
		return (erVenstreparentes(p) || erHogreparentes(p));
	}

	@Override
	public boolean erPar(char venstre, char hogre) {
		if (erVenstreparentes(venstre) && erHogreparentes(hogre)) {
			char[] tabv = { '(', '[', '{' };
			char[] tabh = { ')', ']', '}' };
			for (int i = 0; i < 3; i++) {
				if (venstre == tabv[i] && hogre == tabh[i]) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean erBalansert(String s) {

		for (int i = 0; i < s.length(); i++) {
			if (erParentes(s.charAt(i))) {
				if (erVenstreparentes(s.charAt(i))) {
					stb.push(s.charAt(i));
//					System.out.println(antall + " + ");
					antall++;
				} else if (erHogreparentes(s.charAt(i)) && antall == 0) {
					// System.out.println("antall = 0");
//					System.out.println(antall);
					empty();
					return false;
				} else if (erHogreparentes(s.charAt(i)) && antall != 0) {
					char x = stb.pop();
					antall--;
//					System.out.println(antall + " - ");
					if (!erPar(x, s.charAt(i))) {
						// System.out.println("er ikke par");
						empty();
						return false;
					}
				}
			}
		}
		if (stb.erTom()) {
			return true;
		} else {
			empty();
			// System.out.println("stabel ikke tom");
			return false;
		}

	}

	public void empty() {
		while (!stb.erTom()) {
			stb.pop();
			antall--;
		}
	}

}
