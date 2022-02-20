package opg4;

public class Opg4 {
	private static int teller = -1;

	public static void main(String[] args) {
		System.out.println(oppgave4aSUM(100)); // Oppgave A
		for (int i = 0; i < 10; i++) {
			System.out.println(oppgave4bTF(i)); // Oppgave B
		}
		long x1 = System.currentTimeMillis();
		for (int i = 0; i < 30; i++) {
			System.out.println(oppgave4cFIB(i));
		}
		long x2 = System.currentTimeMillis();
		System.out.println(x2-x1 + "ms");  	// tok 5 ms
		x1 = System.currentTimeMillis();
		for (int i = 0; i < 40; i++) {
			System.out.println(oppgave4cFIB(i));
		}
		x2 = System.currentTimeMillis();
		System.out.println(x2-x1 + "ms");	// tok 520 ms med 10 ekstra elementer
		x1 = System.currentTimeMillis();
		oppgave4dFIB(30);
		x2 = System.currentTimeMillis();
		System.out.println(x2-x1+"ms"); // 0 ms
		x1 = System.currentTimeMillis();
		oppgave4dFIB(40);
		x2 = System.currentTimeMillis();
		System.out.println(x2-x1 + "ms");	// 1ms
	}
	
	public static int oppgave4cFIB(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			return oppgave4cFIB(n-1)+oppgave4cFIB(n-2);
		}
	}
	public static int oppgave4dFIB(int n) {
		int sum = 0;
		int[] tab = new int[3];
		for (int i = 0; i < n; i++) {
			if (i == 0) {
				tab[2] = 0;
			} else if (i == 1) {
				tab[1] = 1;
			} else {
				tab[0] = tab[1]+tab[2];
				tab[2] = tab[1];
				tab[1] = tab[0];
				System.out.println(tab[0]);
			}
		}
		return tab[0];
	}
	

	public static int oppgave4aSUM(int n) {
		if (n == 0) {
			return 0;
		} else {
			return n + oppgave4aSUM(n - 1);
		}
	}

	public static int oppgave4bTF(int n) {
		if (n > 1) {
			return 5 * oppgave4bTF(n - 1) - 6 * oppgave4bTF(n - 2) + 2;
		}
		if (n == 0) {
			return 2;
		} else if (n == 1) {
			return 5;
		} else {
			return 0;
		}
	}
}
