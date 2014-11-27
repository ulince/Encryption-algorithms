package mx.udlap.rc4;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;

import mx.udlap.vigenere.VigenereCipher;

public class vrc4 {

	public static void main(String[] args) {

		String plainText;
		String key;
		
		plainText = args[0];
		key = args[1];

		byte ptxt[] = plainText.getBytes();
		byte ky[] = key.getBytes();

		long p[] = new long[ptxt.length];
		long k[] = new long[ky.length];

		for (int i = 0; i < ptxt.length; i++) {
			p[i] = new Byte(ptxt[i]).longValue();
		}

		for (int i = 0; i < ky.length; i++) {
			k[i] = new Byte(ky[i]).longValue();
		}
		
		//System.out.println();
		System.out.println("Plaintext bytes:" + Arrays.toString(p));
		System.out.print("Plaintext:");
		printChars(p);
		System.out.println("Key bytes:" + Arrays.toString(k));
		System.out.print("Key:");
		printChars(k);
		System.out.println();
		
		
		/*Encriptar con rc4*/
		rc4 r = new rc4();
		r.K = k;
		r.M = p;

		r.initialization();
		long cipher[] = r.streamGeneration();
		
		System.out.println("--------------------------------------");
		System.out.println("Encriptado\n");
		System.out.println("Encriptar con RC4");
		System.out.println("crc4 bytes:"+ Arrays.toString(cipher));
		
		/*for(int i = 0; i < cipher.length; i++){
			System.out.print(Integer.toHexString((int)cipher[i]));
		}
		System.out.println();
		*/
		
		
		System.out.print("crc4 text:");
		printChars(cipher);

		//(int) Math.random() * 255;
		int j = 1;
		
		/*Divide ciphertext and T in two parts at position j*/
		long c1ptxt[] = new long[j + 1];
		long c2ptxt[] = new long[cipher.length - c1ptxt.length];
		long T1[] = new long[j + 1];
		long T2[] = new long[cipher.length - T1.length];
		
		/*Copy values 0 to j*/
		for(int i = 0; i <= j; i++){
			c1ptxt[i] = cipher[i];
			T1[i] = r.T[i];
		}
		
		/*Copy values j + 1 to cipher.length*/
		int l = 0;
		for(int i = j + 1; i < cipher.length; i++){
			c2ptxt[l] = cipher[i];
			T2[l] = r.T[i];
			l++;
		}
		
		//System.out.println("C1:" + Arrays.toString(c1ptxt));
		//System.out.println("C2:" + Arrays.toString(c2ptxt));
		System.out.print("C1:");
		printChars(c1ptxt);
		System.out.print("C2:");
		printChars(c2ptxt);
		System.out.println("J:"+ j);
		System.out.println();
		
		
		/*Encrypt with Vigenere*/
		VigenereCipher vc = new VigenereCipher();				
		int matrixInt[][] = vc.createIntMatrix();
		
		long c1[] = vc.cipher2(T1, matrixInt, c1ptxt);
		long c2[] = vc.cipher2(T2, matrixInt, c2ptxt);	
		long C[] = ArrayUtils.addAll(c1, c2);
		
		
		System.out.println("Encriptar con Vigenere");
		System.out.println("Cipher bytes:" + Arrays.toString(C));
		System.out.print("Ciphertext:");
		/*for(int i = 0; i < C.length; i++){
			System.out.print(Integer.toHexString((int)C[i]));
		}
		System.out.println();
		*/
		
		printChars(C);
		
		
		System.out.println("--------------------------------------");
		System.out.println("Decifrado:\n");
		
		/*Deicfrar con Vigenere*/
		long decrypt1[];
		long decrypt2[];
		
		decrypt1 = vc.decipher2(T1, matrixInt, c1);
		decrypt2 = vc.decipher2(T2, matrixInt, c2);	
		long D[] = ArrayUtils.addAll(decrypt1, decrypt2);
		
		System.out.println("Decifrar con Vigenere");
		System.out.print("crc4:");
		printChars(D);	
		System.out.println();
		
		
		/*Decifrado con rc4*/
		r = new rc4();
		r.K = k;
		r.M = D;

		r.initialization();
		
		long result[] = r.streamGeneration();
		
		System.out.println("Decifrar con RC4");
		System.out.println("Plaintext bytes:" + Arrays.toString(result));
		System.out.print("Plaintext:");
		printChars(result);
		
	}
	
	public static void printChars(long array[]){
		for(int i = 0; i < array.length; i++){
			System.out.print((char)array[i] + " ");
		}
		System.out.println();
	}

}
