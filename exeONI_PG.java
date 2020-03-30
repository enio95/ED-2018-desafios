import java.util.Scanner;

// ---------------------------- DRAFT ----------------------------------
// quando tiver tempo meto isto direito, so queria dizer que grande
// exercicio adorei =)
public class exeONI_PG
{
    public static void main(String[] args)
    {
	Scanner read = new Scanner(System.in);

	int l = read.nextInt();
	int g = read.nextInt();
	pattern padrao = new pattern(l, g);
	
	read.nextLine(); // le o mudaça de linha

	padrao.save( read );

	//padrao.show();

	int p = read.nextInt();

	for ( int i=1; i<=p; i++ )
	    {

		long y1 = read.nextLong();
		long x1 = read.nextLong();
		long y2 = read.nextLong();
		long x2 = read.nextLong();
	                
		System.out.println( conta( x1,y1,x2,y2, padrao, padrao.g) );  
	    }
    }

    public static long conta(long ax, long  ay, long bx, long by, pattern p, int g )
    {
	long contador = 0L;
	
	if ( ax==ay && ax==0 && bx==by && bx==p.size[g]-1 )
	    return p.pintado[g];
	
	else if ( g==1 )
	    {
		for( int i=(int)ay; i<=(int)by; i++ )
		    {
			for( int j=(int)ax; j<=(int)bx; j++ )
			    {
				if( p.matriz[i][j]=='#' )
				    contador++;
			    }
		    }
	    }

	else
	    {
		for( int i=0; i<p.l; i++ )
		    {
			for( int j=0; j<p.l; j++ )
			    {
				if( p.matriz[i][j]=='#' )
				    {
					long a = condicao_A(ax+1,j,g,p);
					long b = condicao_A(ay+1,i,g,p);
					long c = condicao_B(bx+1,j,g,p);
					long d = condicao_B(by+1,i,g,p);
					//System.out.printf("a->%d,b->%d,c->%d,d->%d\n",a,b,c,d);
					if ( a!=-1 && b!=-1 && c!=-1 &&d!=-1)
					    contador+=conta(a,b,c,d,p,g-1);
				    }
			    }
		    }
	    }
	return contador;
    }

    public static long condicao_A( long A, int j, int g,pattern p )
    {
	long l = p.size[g-1];
	
	if ( A>(j+1)*l )
	    return -1;

	else if ( j*l-A<0 )
	    return A-j*l-1;

	else
	    return 0;
    }
    
    public static long condicao_B( long B, int j, int g,pattern p )
    {
	long l = p.size[g-1];
	if ( B>=(j+1)*l )
	    return l-1;

	else if ( B<j*l )
	    return -1;

	else
	    return B-j*l-1;
    }   

}

    

class pattern
{
    int l;  // tamanho do lado da geração mae
    char[][] matriz; // matriz que guarda o padrao
    long[] size; //generation size
    long[] pintado; // numero de quadrados pintados em cada geracao
    int g; // generation

    pattern( int lado, int geracao )
    {
	l = lado;
	matriz = new char[lado][lado];
	g = (int)pow(2,(long)(geracao-1));
	size = new long[g+1];
	pintado = new long[g+1];
	
	for( int i=1; i<=g; i++ )
	    {
		size[i] = pow((long)lado, i );
	    }
    }

    public void save( Scanner read ) // guarda o padrao
    {
	for( int i=0; i<l; i++ )
	    {
		String str = read.nextLine();
		for( int j=0; j<l; j++ )
		    {
			matriz[i][j] = str.charAt(j);

			if ( str.charAt(j) == '#' )
			    pintado[1]++;
		    }
	    }

	for( int i=2; i<=g; i++ )
	    {
		pintado[i] = pintado[1]*pintado[i-1];
	    }
    }

    public void show() // imprime o padrao recebido
    {
	for( int i=0; i<l; i++ )
	    {
		for( int j=0; j<l; j++ )
		    {
			System.out.print( matriz[i][j] );
		    }
		System.out.println();
	    }
	System.out.println("o tamanho de cada geração é");
	for( int i=0; i<=g; i++ )
	    {
		System.out.printf("%d->%d->%d\n",i,size[i],pintado[i]);
	    }
    }

    public static long pow( long base, long exp )
    {
	if ( exp < 0 ) // segurança
	    return 0L;
	
	else if ( exp == 0)
	    return 1L;
	
	else if ( exp == 1 )
	    return base;
	
	else if ( exp % 2 == 0 )
	    return pow( base*base, exp/2 );
	
	else
	    return base*pow( base*base, exp/2 );
    }

}
