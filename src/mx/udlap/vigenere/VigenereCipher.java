package mx.udlap.vigenere;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VigenereCipher {

	/*
	 * public static void main(String[] args) { List<Character> abcd =
	 * generateABCD(); char matrix[][] = createMatrix(abcd); String key =
	 * generateKey("abcd", "helloworld");
	 * 
	 * String cipherText = cipher(key, matrix, "helloworld", abcd);
	 * 
	 * System.out.println(cipherText);
	 * 
	 * String plainText = decipher(key,matrix,cipherText,abcd);
	 * 
	 * System.out.println(plainText);
	 * 
	 * }
	 */

	/* Método para guardar las letras del abcdario */
	public List<Character> generateABCD() {
		List<Character> abcd = new ArrayList<>();

		abcd.add(new Character('a'));
		abcd.add(new Character('b'));
		abcd.add(new Character('c'));
		abcd.add(new Character('d'));
		abcd.add(new Character('e'));
		abcd.add(new Character('f'));
		abcd.add(new Character('g'));
		abcd.add(new Character('h'));
		abcd.add(new Character('i'));
		abcd.add(new Character('j'));
		abcd.add(new Character('k'));
		abcd.add(new Character('l'));
		abcd.add(new Character('m'));
		abcd.add(new Character('n'));
		abcd.add(new Character('o'));
		abcd.add(new Character('p'));
		abcd.add(new Character('q'));
		abcd.add(new Character('r'));
		abcd.add(new Character('s'));
		abcd.add(new Character('t'));
		abcd.add(new Character('u'));
		abcd.add(new Character('v'));
		abcd.add(new Character('w'));
		abcd.add(new Character('x'));
		abcd.add(new Character('y'));
		abcd.add(new Character('z'));

		return abcd;
	}

	/* Crea la matriz */
	public char[][] createMatrix(List<Character> abcd) {

		char matrix[][] = new char[26][26];

		for (int row = 0; row < 26; row++) {

			int temp = row;
			for (int column = 0; column < 26; column++) {
				matrix[row][column] = abcd.get(temp % 26).charValue();
				temp++;
			}
		}
		return matrix;
	}

	/* Lee la llave */
	public String readKeyword() {
		return "";
	}

	/* Lee el plainText */
	public String readPlainText() {
		return "";
	}

	/*
	 * Repite la keyword el número de letras que tenga el plaintext plaintext
	 * mas largo que keyword
	 */
	public String generateKey(String keyword, String plainText) {
		String key = "";

		// char key[] = new char[plainText.length()];
		int times = (int) plainText.length() / keyword.length();
		int remaining = plainText.length() % keyword.length();

		for (int i = 0; i < times; i++) {
			for (int j = 0; j < keyword.length(); j++) {
				key += keyword.charAt(j);
			}
		}

		for (int i = 0; i < remaining; i++) {
			key += keyword.charAt(i);
		}

		return key;
	}

	/* Encripta el plaintext */
	public String cipher(String key, char matrix[][], String plainText,
			List<Character> abcd) {
		String cipherText = "";

		for (int i = 0; i < key.length(); i++) {
			cipherText += matrix[abcd.indexOf(new Character(key.charAt(i)))][abcd
					.indexOf(new Character(plainText.charAt(i)))];
		}

		return cipherText;

	}

	/* Encripta el plaintext usando la matriz de numeros y regresa un long[] */
	public long[] cipher2(long keyLong[], int matrix[][], long plaintext[]) {
		long ciphertext[] = new long[plaintext.length];
		int row, column;

		for (int i = 0; i < ciphertext.length; i++) {
			row = (int) keyLong[i];
			column = (int) plaintext[i];
			ciphertext[i] = matrix[row][column];
		}

		return ciphertext;
	}

	/* Desencripta el ciphertext */
	public String decipher(String key, char matrix[][], String cipherText,
			List<Character> abcd) {
		String plainText = "";
		int row, column;

		for (int i = 0; i < key.length(); i++) {
			row = abcd.indexOf(new Character(key.charAt(i)));
			column = searchArray(matrix[row], cipherText.charAt(i));

			plainText += abcd.get(column).charValue();
		}

		return plainText;
	}

	/* Desencripta el plaintext usando la matriz de numeros y regresa un long[] */
	public long[] decipher2(long keyLong[], int matrix[][], long ciphertext[]) {
		long plaintext[] = new long[ciphertext.length];
		int row;

		for (int i = 0; i < plaintext.length; i++) {
			row = (int) keyLong[i];
			plaintext[i] = searchArray2(matrix[row], (int)ciphertext[i]);
		}

		return plaintext;
	}

	/* Busca un char en un char[] */
	public int searchArray(char array[], char letter) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == letter) {
				return i;
			}
		}
		return -1;
	}
	
	/* Search array*/
	
	/* Busca el long en el arreglo de longs */
	public int searchArray2(int array[], int key){
		for(int i = 0; i < array.length; i++){
			if(array[i] == key){
				return i;
			}
		}
		
		return -1;
	}

	/* Crea la matriz int[256][256] */
	public int[][] createIntMatrix() {
		int matrix[][] = new int[256][256];
		for (int i = 0; i < 256; i++) {
			for (int j = 0; j < 256; j++) {
				matrix[i][j] = (j + i) % 256;
			}
		}

		return matrix;

	}

}
