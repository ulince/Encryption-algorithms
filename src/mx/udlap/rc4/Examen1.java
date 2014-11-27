package mx.udlap.rc4;

public class Examen1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int C = 10;
		int n = 143;
		int d= 5;
		
		int M = (int) (Math.pow((double)C,(double) d) % n);
		
		System.out.println(M);

	}

}
