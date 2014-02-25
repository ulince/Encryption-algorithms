package mx.udlap.playfair;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class PlayfairCipher {


	public static void main(String[] args) {
		char[][] matrix = createMatrix("keyword");

		String digrams[] = new String[]{"wh","yd","on","ty","ou"};
		
		String cipherText = cipher(matrix,digrams);
		
		System.out.println(cipherText);

	}

	/* Recibir la keyword y checar las letras repetidas */
	public static String getKeyword() {
		return "";
	}

	/*
	 * Separar el plaintext en diagrams. Si hay letras repetidas, insertar x. Si
	 * el numero de letras es impar, agregar x al final.
	 */
	public static String textToDigrams(String plainText) {
		return "";
	}

	/*
	 * Crear la matriz y poner la keyword en la matriz. Terminar de llenar la
	 * matriz, sin contar las letras que ya están.
	 */
	public static char[][] createMatrix(String keyword) {
		char matrix[][] = new char[5][5];

		char abc[] = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
				'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
				'w', 'x', 'y', 'z', };

		int length = keyword.length();

		int index = 0, row, column;

		for (row = 0; row < 5; row++) {
			for (column = 0; column < 5; column++) {
				if (index == length) {
					break;
				}
				matrix[row][column] = keyword.charAt(index);
				index++;
			}
		}

		int i = ((int) length / (int) 5); // fila
		int j = (length % 5);// columna
		int k = 0;// abcdario

		// checar si j es diferente a 0
		while (i < 5) {
			while (j < 5) {
				if (keyword.contains(Character.toString(abc[k]))) {
					k++;
					continue;
				} else {
					matrix[i][j] = abc[k];
					k++;
				}
				j++;
			}
			j = 0;
			i++;
		}

		return matrix;
	}
	
	/*Busca un char en la matriz. Si lo encuentra, regresa un int[2] con los índices 
	 * del char en la matriz. Si no lo encuentra, regresa null.
	 */
	public static Point findInMatrix(char letter, char matrix[][]){
		for(int row = 0; row < matrix.length; row++){
			for(int column = 0; column < matrix[0].length; column++){
				if(letter == matrix[row][column]){
					return new Point(row,column);
				}
			}
		}

		return null;
	}
	
	/*Busca los digrams enla matriz y va poniendo las coordenadas de cada uno
	 * en un Point[][].
	 */
	public static Point[][] allCoordinates(char matrix[][], String digrams[]){
		Point coordenates[][] = new Point[digrams.length][2];
		
		for(int row = 0; row < digrams.length; row++){
			coordenates[row][0] = findInMatrix(digrams[row].charAt(0),matrix);
			coordenates[row][1] = findInMatrix(digrams[row].charAt(1),matrix);
		}
		
		return coordenates;
	}

	public static String cipher(char[][] matrix, String digrams[]){
		Point coordenates[][] = allCoordinates(matrix,digrams);
		String cipherText = "";
		
		for(int row = 0; row < digrams.length; row++){
			/*Caso en que las dos letras están en la misma fila*/
			if(coordenates[row][0].x == coordenates[row][1].x){
				coordenates[row][0].x = (coordenates[row][0].x + 1) % 5;
				coordenates[row][1].x = (coordenates[row][1].x + 1) % 5;
			}else
			
			/*Caso en que las dos letras están en la misma column*/
			if(coordenates[row][0].y == coordenates[row][1].y){
				coordenates[row][0].y = (coordenates[row][0].y + 1) % 5;
				coordenates[row][1].y = (coordenates[row][1].y + 1) % 5;
			}else{
			
			/*Caso en que las letras están en fila y columna diferente*/
				Point temp0 = new Point(coordenates[row][0].x,coordenates[row][0].y);
				Point temp1 = new Point(coordenates[row][1].x,coordenates[row][1].y);
				
				coordenates[row][0].y = temp1.y;
				coordenates[row][1].y = temp0.y;
			}
		}
		
		for(int row = 0; row < digrams.length; row++){
			Point temp0 = new Point(coordenates[row][0].x,coordenates[row][0].y);
			Point temp1 = new Point(coordenates[row][1].x,coordenates[row][1].y);
			cipherText += matrix[temp0.x][temp0.y];
			cipherText += matrix[temp1.x][temp1.y];
		}
		
		return cipherText;
	}

}
