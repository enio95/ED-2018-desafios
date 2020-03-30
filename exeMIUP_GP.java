import java.util.Scanner;

// não estou a me gabar mas este desafio era muito fraquinho professor
public class exeMIUP_GP
{
    public static void main(String[] args)
    {
	Scanner read = new Scanner(System.in);
	int n = read.nextInt();
	int k = read.nextInt();
	int m = read.nextInt();

	analise( n, k, m );
    }

    public static void analise ( int n, int k, int m )
    {
	int[] A = new int[n];
	int[] B = new int[n];
	int teto = n - n%k;
	int x = 0, y = 0;

	// PARTE 1
	
	for ( int i=teto+1; i<=n; i++ )
	    {
		A[x] = i;
		x++;
	    }

	for ( int i=1; i<=teto; i++ )
	    {
		if ( i % k != 0 )
		    {
			A[x] = i;
			x++;
		    }
	    }
	x--; // agora vai representar o ultimo indice
	
	// check point 
	//writeArray( A, x );

	// PARTE 2

	while ( true )
	    {
		y = 0;
		n = x + 1;
		teto = n - n%k - 1;

		for ( int i=teto+1; i<=x; i++ )
		    {
			B[y] = A[i];
			y++;
		    }

		B[y] = A[0];
		y++;

		for ( int i=1; i<=teto; i++ )
		    {
			if ( (i+1) % k != 0  )
			    {
				B[y] = A[i];
				y++;
			    }
			    
		    }
		y--; // y tem um incremento a mais
		//writeArray(B, y);

		// se o numero de elementos em B for menor que k entao
		// acabamos a função
		if( y+1 < k )
		    {
			solution( B, y, m );
			break;
		    }

		x=0;
		n = y + 1;
		teto = n - n%k - 1;

		for ( int i=teto+1; i<=y; i++ )
		    {
			A[x] = B[i];
			x++;
		    }
		A[x] = B[0];
		x++;

		for ( int i=1; i<=teto; i++ )
		    {
			if ( (i+1) % k != 0 )
			    {
				A[x] = B[i];
				x++;
			    }
		    }

		x--;
		//writeArray(A, x);

		if( x+1 < k )
		    {
			solution( A, x, m );
			break;
		    }
	    }
    }

    public static void solution( int[] array, int x, int m )
    {
	int min = 0;
	int max = 0;

	for ( int i=1; i<=x; i++ )
	    {
		if ( array[min] > array[i] )
		    min = i;

		if ( array[max] < array[i] )
		    max = i;
	    }

	if ( m==1 )
	    System.out.printf("%d\n",array[min] );

	else
	   System.out.printf("%d\n",array[max] ); 
    }

    public static void writeArray( int[] A, int x )
    {
	for ( int i=0; i<=x; i++ )
	    System.out.printf("%d\n",A[i]);
	
	System.out.printf("o array tem %d elementos\n", x+1);
    }
   
}
    
