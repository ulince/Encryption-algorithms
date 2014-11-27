package mx.udlap.rc4;

import java.util.Arrays;

public class rc4 {

	long K[];
	long S[];
	long M[];
	long T[];
	
	/*public static void main(String[] args) {
		K = new long[4];
		S = new long[4];
		M = new long[2];
		
		long C[] = new long[2];
		
		K[0] = 1;
		K[1] = 7;
		K[2] = 1;
		K[3] = 7;
		
		M[0] = 0b01001000;
		M[1] = 0b01001001;
		
		initialization(K,S,M);
		C = streamGeneration();
		
		for(int i = 0 ; i < C.length; i++){
			System.out.print(Long.toBinaryString(C[i]) + ", ");
		}
		
				
				
	}
	*/
	
	/*Initialization*/
	public void initialization(){
		
		System.out.println("Initialization");
		S = new long[256];
		
		T = new long[256];
		
		for(int i = 0; i < 256; i++){
			S[i] = i;
			T[i] = K[i % K.length];
			//System.out.println("S[i]:"+ S[i]);
		}
		
		long j = 0;
		for(int i = 0; i < 256; i++){
			j = (j + S[i] + T[i]) % 256;
			S = swap(S, i, (int)j);
			//System.out.println("i:" + i + " j:" + j);
			//System.out.println("S:" + Arrays.toString(S));
			//System.out.println("Swap S[i] and S[j]");
		}	
	}
	
	/*Encryption*/
	public long[] streamGeneration(){
		System.out.println("\nStream Generation");
		int i = 0;
		long j = 0;
		long t, k;
		
		long C[] = new long[M.length];
		
		for(int l = 0; l < M.length; l++){
			i = (i + 1) % 256;
			j = (j + S[i]) % 256;
			S = swap(S, i, (int)j);
			t = (S[i] + S[(int)j]) % 256;
			k = S[(int)t];
			C[l]  = M[l] ^ k;
			
			//System.out.println("i:" + i + " j:" + j);
			//System.out.println("S:" + Arrays.toString(S));
		}
		
		return C;
	}
	
	/*Swap*/
	public long[] swap(long S[], int i, int j){
		long temp;
		temp = S[i];
		S[i] = S[j];
		S[j] = temp;
		
		return S;			
	}

}
