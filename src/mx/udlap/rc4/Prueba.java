package mx.udlap.rc4;

import java.util.Arrays;

public class Prueba {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String plainText;
		String key;
		
		plainText = args[0];
		key = args[1];

		byte ptxt[] = plainText.getBytes();
		byte ky[] = key.getBytes();

		//long p[] = new long[ptxt.length];
		//long k[] = new long[ky.length];

		/*for (int i = 0; i < ptxt.length; i++) {
			p[i] = new Byte(ptxt[i]).longValue();
		}

		for (int i = 0; i < ky.length; i++) {
			k[i] = new Byte(ky[i]).longValue();
		}
		*/
		long p[] = new long[2];
		long k[] = new long[2];
		
		p[0] = 72;
		p[1] = 73;
		
		k[0] = 2;
		k[1] = 5;
		
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
		//System.out.println("Encriptar con RC4");
		//System.out.println("crc4 bytes:"+ Arrays.toString(cipher));
		System.out.print("hex: ");
		for(int i = 0; i < cipher.length; i++){
			System.out.print(Integer.toHexString((int)cipher[i]) + " ");
		}
		System.out.println();
		printChars(cipher);

	}
	
	public static void printChars(long array[]){
		for(int i = 0; i < array.length; i++){
			System.out.print((char)array[i] + " ");
		}
		System.out.println();
	}

}
