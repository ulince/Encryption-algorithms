package mx.udlap.playfair;

import java.util.ArrayList;
import java.util.List;

public class PlayfairCipher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char[][] matrix = createMatrix("monarchy");
		
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 5; j++){
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println("");
		}
		
	}
	
	/*Recibir la keyword y checar las letras repetidas*/
	public static String getKeyword(){
		return "";
	}
	
	/*Separar el plaintext en diagrams.
	 * Si hay letras repetidas, insertar x.
	 * Si el numero de letras es impar, agregar x al final.
	 */
	public static String textToDigrams(String plainText){
		return "";
	}
	
	/*Crear la matriz y poner la keyword en la matriz.
	 * Terminar de llenar la matriz, sin contar las letras que ya están.
	 */
	public static char[][] createMatrix(String keyword){
		char matrix[][] = new char[5][5];
		
		List<String> abcd = new ArrayList<>();
		abcd.add("a");
		abcd.add("b");
		abcd.add("c");
		abcd.add("d");
		abcd.add("e");
		abcd.add("f");
		abcd.add("g");
		abcd.add("h");
		abcd.add("i");
		abcd.add("k");
		abcd.add("l");
		abcd.add("m");
		abcd.add("n");
		abcd.add("o");
		abcd.add("p");
		abcd.add("q");
		abcd.add("r");
		abcd.add("s");
		abcd.add("t");
		abcd.add("u");
		abcd.add("v");
		abcd.add("w");
		abcd.add("x");
		abcd.add("y");
		abcd.add("z");
		
		char abc[] = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t',
				'u','v','w','x','y','z',};
		
		int length = keyword.length();
		
		int i= 0, j, k;
		
		for(j = 0; j < 5; j ++){
			for(k = 0; k < 5; k++){
				if(i == length -1){
					break;
				}
				matrix[j][k] = keyword.charAt(i);
				i++;
			}
		}
		
		i = ((int)length/(int)5); //fila
		j = (length % 5);//columna
		k = 0;//abcdario
		
		//checar si j es diferente a 0
		if(j != 0){
			while(i < 5){
				while(j< 5){
					if(keyword.contains(Character.toString(abc[k]))){
						k++;
						continue;						
					}else{
						matrix[i][j] = abc[k];
						k++;
					}
					j++;
				}
				j = 0;
				i++;
			}
		}
		
		return matrix;
	}
	
	public String cipher(char[][] matrix, String digramText){
		return "";
	}
	
}
