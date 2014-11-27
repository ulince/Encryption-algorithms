package mx.udlap.playfair;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class PlayfairCipher {
	
	static int repetitions = 0; 
	static String   []wordToPrint ;
	static ArrayList<Character>  ch;


	public static void main(String[] args) {
		char[][] matrix = createMatrix("keyword");

		String digrams[] = textToDigrams("whydontyou");
		
		String cipherText = cipher(matrix,digrams);
		
		System.out.println(cipherText);

	}

	/* Recibir la keyword y checar las letras repetidas */
	public static boolean isLetterRepeated(String key){

        int count = 0; 
        char str = (char)32;
        //System.out.println(key.length());
        int i = 0;
        
       
        for (i = 0 ; i<key.length(); i++){
           char c = key.charAt(i);
           if (c == str){
               return true;
           }
           for (int e= i+1; e<key.length(); e++){
               char d = key.charAt(e);
               if (c == d){
                   count ++;
                   //System.out.println(count);
                   return true;
                   //break;
               }
               
               //System.out.println(d);
           }
          // System.out.println(c);
       }
       // System.out.println(count);
        
        
        return false;
    }

	/*
	 * Separar el plaintext en diagrams. Si hay letras repetidas, insertar x. Si
	 * el numero de letras es impar, agregar x al final.
	 */
	public static String[] textToDigrams(String key) {
		//Conocer si hay letras repetidas
        
        //Recibe una llave. Se convierten en pares. Si una letra esta repetida
        //se
        
        //Revisar si hay espacios
        char sp = (char)32;
        String refactored= "";

        String stringPattern = " ";
        Pattern pattern = Pattern.compile(stringPattern);
        
        String[] split = pattern.split(key);
        
        //System.out.println("split.length = " + split.length);

       for(String element : split){
           refactored = refactored + element;
           //System.out.println("element = " + element);
           //System.out.println("Word = " + refactored);
       }
        
       /*
        * Dividir en pares y revisar si hay letras repetidas
        */
       String []temp = addX(refactored);
       //refactored = temp;
       /*
         * Convertir en par
        */ 
       String[] finalWord = checkForRepeatedLetter(temp);
       //System.out.println("Final Word: "+finalWord);
        //Checar si hay pares
       wordToPrint =finalWord;
       
       return finalWord; 
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
	
	private static String[] checkForRepeatedLetter (String[]refactored){

        //System.out.println("Refactored-1: " + refactored[refactored.length-1]);
        if (refactored[refactored.length-1].length() < 2 ){
            refactored[refactored.length-1] += 'x';
        }
        return refactored;
    }
    
    private static String[] addX (String refactored){
       ch = new ArrayList <>();
       for (int i = 0 ; i < refactored.length(); i++){
           ch.add(refactored.charAt(i));
       }
       char c1 = ' ';
       char c2= ' ';
       int pos = 0;
       
       Iterator itr = ch.iterator();
       while (itr.hasNext()){
           c1 = ch.get(pos);
           if (pos +1 == ch.size()){
               break;
           }else {c2 = ch.get(pos +1);}
            
           //System.out.println("c1: " +c1);
           //System.out.println("c2: " +c2);
           
           if (c1 == c2){
               System.out.println("Repetidos: "+ c1 + " "+ c2);
               ch.add(pos+1, 'x');
                //Checa si hay x al final
            }
            
            if (pos +2 >= ch.size()){
                break;
            }else pos = pos+2;
            
        }
        //Construir la palabra
		Iterator it = ch.iterator();
        String parcialWord = "";
        
        String[] finalWord = new String[ch.size()/2+1];
        int i1= 0;
        int i2= 0;
        
        while (it.hasNext()){
           char element = (char)it.next();
           if (i1==1){
               i1=0;
               parcialWord += element;
               
               //if
               if(i2 == finalWord.length){
                   break;
               }else{
                   //parcialWord += element;
                   finalWord[i2] = parcialWord;
                   parcialWord="";
                   i2++;
                   
               }
           }else {parcialWord += element; i1 ++;}            
           
        }
        
        finalWord[finalWord.length-1]="";
        finalWord[finalWord.length-1]+=ch.get(ch.size()-1);
        
        //System.out.println(finalWord[1]);
        wordToPrint = finalWord;
        return finalWord;
    }


   public String toString() {
       String s = "";
       for (int i = 0 ; i < wordToPrint.length; i++){
           s += wordToPrint[i];
       }
       //System.out.println(wordToPrint[0]);
       
       return s;
   }

}
