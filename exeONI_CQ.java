import java.util.Scanner;

class ponto
{
    long x,y;
    
    ponto()
    {
        x = 0;
        y = 0;
    }

    public void add( long l )
    {
	this.x += l;
	this.y -= l;
    }

    public void show() // isto esta aqui para testar se encontra (x,y)
    {
	System.out.println( "(" + this.x + "," + this.y + ")" );
    }
}
    
public class exeONI_CQ
{
    public static void main(String[] args)
    {
	long n;
	ponto va = new ponto();
	ponto vb = new ponto();

	Scanner read = new Scanner(System.in);

	int p = read.nextInt();


	for( int i = 1; i <= p; i++ )
	    {
		long A = read.nextLong();
		long B = read.nextLong();
		encontrar_coordenada( A, va );
		encontrar_coordenada( B, vb );	
		calcular_soma( va, vb, A );		
	    }
    }

    // parte 1 -----------------------------------------------
    
    public static void encontrar_coordenada( long p, ponto vetor )
    {
	long valor = 1;
	long linha = 0;
        while ( p >= valor )
	    {
		linha++;
		valor += linha - 1;
	    }
	linha -= 1;
	valor -= linha ;
	
	vetor.x = 1;
	vetor.y = linha;

        vetor.add( p - valor );
    } // esta correto ate aqui
    

    // parte 2 -------------------------------------------------------

    public static void calcular_soma( ponto va, ponto vb, long A)
    {
	long soma = 0;
	long temp = A;
	long n = 0;

	for ( long i = va.y; i < vb.y; i++ )
	    {   
		soma = soma + (n+1)*temp + soma_ari(n);
		temp = temp + i + va.x - 1; // prox numero vertical

		if( i < vb.y && va.x + n < vb.x )
		    n++;
	    }
	
	for ( long i=va.x; i<=vb.x; i++ )
	    {
		if( i+n>vb.x )
		    n--;
		
		soma = soma + (n+1)*temp + soma_ari(n);
		temp = temp + i + vb.y; // proximo numero horizontal
	    }
		
	System.out.println( soma );
    }

    public static long soma_ari( long n )
    {
	
	if ( n == 0 )
	    return  0;

	else if ( n == 1 )
	    return 1;

	else if ( n % 2 == 0 )
	    return (n/2)*(n+1);

	else
	    return (((n-1)/2)*(n)+n);
	
    }
}
