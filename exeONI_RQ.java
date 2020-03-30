import java.util.Scanner;

// Introdução

// Primeira coisa que percisamos de defenir

// 1's representa pintado, 0's representa não pintado


// Segunda definição

// cada linha tem 3 partes, a 1º e 3º parte sao iguais à linha anterior
// a segunda parte é um zona que eu chamo de "morta", esta zona
// so contem zeros, o tamanho desta zonha é igual ao numero de 1's
// da linha anterior

// linha 0 = "" -> caso base
// linha 1 = 1  -> caso base
// linha 2 = 101 = linha(2-1) + zona_morta(2) + linha(2-1)
// linha 3 = 101 00 101 = linha(3-1) + zona_morta(3) + linha(3-1)
// linha n = linha(n-1) + zona_morta(n) + linha(n)

// como podes inferir podemos calcular o tamanho da linha recursivamente
// o numero de 1's tambem pode ser dado recursivamente mas se reparares
// o numero de 1's segue um padrão que podemos defenir usando
// uma formula matematica
// zona_morta tambem podes inferir que é igual ao numero de 1's da
// linha anterior
// podemos provar isto por indução, se tiveres tempo faz


// Problema: queremos o numero de 1's entre A e B INCLUSIVE

// metodo de ataque :
// descobrir numero de 1's antes de A e B e subtrair
// para obter o numero de 1's entre A e B

// Numero de 1's antes de A

// primeiro passo:
// descobrir a maior linha que não contem A

// segundo passo

// se A esta na zona_morta entao o numero de 1's antes de A
// é igual ao numero de 1's na linha anterior e acabamos a nossa função
// dai a calcular a maior linha que não contem A

// se A não esta na zona_morta, A encontra-se na 3º parte
// entao descartamos o nº 1's da linha anterior e enquadramos nosso A
// este enquadramento vai ser feito recursivamente ate chegarmos
// A == 1 ou a esta numa zona_morta e ai acabamos a nossa função
// Relembro que a 3º parte é igual a linha anterior
// logo fazemos uma "tranlação" de A
// pode parecer um pouco complexo, mas se fores veres o codigo
// vais perceber

// O mesmo raciocinio é feito para B, com a diferença que incluimos B

// Parte final

// fazemos a subtração do que estava antes de A e B e obtemos o que
// esta entre A e B


public class exeONI_RQ
{
    static long[] array = new long[60];
    static long[] array1 = new long[60];

    //guardar o tamanho de cada linha
    public static void guardar_tamanho_linhas()
    {
	array[0] = 0;

	for ( int i = 1; i < 60 ; i++ )
	    array[i] = tamanho_linha( i );
    }

    // guardar o numero de quadrados pintados em cada linha
    public static void guardar_n_1_linhas()
    {
	array1[0] = 0;
	for ( int i = 1; i < 60 ; i++ )
	    array1[i] = numeros_1_linha( i );
    }

    // o numero de quadrados pintados na linha "n"
    public static long numeros_1_linha ( int n ) 
    {
	if ( n == 0 )
	    return 0;
	
	else if ( n == 1 )
	    return 1;
	
	else
	    return pow( 2, n-1 );
    }
    
    // tamanho de cada linha é calculado recursivamente (ver introdução)
    public static long tamanho_linha ( int n ) 
    {
	if ( n == 1 )
	    return 1;
	else if ( n == 0 )
	    return 0;
	else
	    return numeros_1_linha(n-1) + 2*tamanho_linha(n-1);
    }

    // calula potencias, sei que java tem um class para isto
    public static long pow( long base, long exp )
    {
	if ( exp < 0 ) // segurança
	    return 0;
	
	else if ( exp == 0)
	    return 1;
	
	else if ( exp == 1 )
	    return base;
	
	else if ( exp % 2 == 0 )
	    return pow( base*base, exp/2 );
	
	else
	    return base*pow( base*base, exp/2 );
    }

    //----------------------------------------------------------

    
	
    public static void main(String[] args)
    {
	guardar_tamanho_linhas();
	guardar_n_1_linhas();

	Scanner read = new Scanner(System.in);
	int p = read.nextInt();
	
	for ( int i = 1; i <= p; i++ )
	    {
		int linha = read.nextInt();
		long a = read.nextLong();
		long b = read.nextLong();
		
		// este if esta aqui porque da erro se introduzires
		// linha 1 1 
		if ( a == b && a == 1 )
		    System.out.println( "1" );
		else
		    System.out.println( NB(b, 0) - NA(a, 0)  );
	    }
	
    }
    
    public static long NA( long a, long fora )
    {
	int i = 1;
	while( array[i] < a )
	    i++;

	fora += array1[i-1];
	
	// ver introdução
	long parte1 = array[i-1];
	long parte2 = array[i] - parte1;

	// ver introdução 
	if( a <= parte2 || a == 1 )
	    return fora;
	
	// enquadramento de A (ver introdução)
	else 
	    return NA ( parte1 - ( array[i] - a ), fora );	   
    }

    
    public static long NB( long b, long dentro )
    {
	int i = 1;
	while( array[i] < b )
	    i++;

	dentro += array1[i-1];
	
	// ver introdução
	long parte1 = array[i-1];
	long parte2 = array[i] - parte1;

	// ver introdução
	if ( b <= parte2 )
	    return dentro;
	
	else if ( b <= parte2 + 1 ) // b inclusive
	    return dentro + 1;
	
	// enquadramento de A (ver introdução)
	else 
	    return NB ( parte1 - ( array[i] - b ), dentro );    
    }
}

// conclusão

// É necessario guardar o tamanho das linhas e o numero de 1's das
// linhas em arrays, de modo a cortar tempo de execução
// porque no exercicio pedido o professor faz muitas perguntas

// É capaz de haver uma solução mais eficiente, mas por agora não
// tenho tempo para procurar, se algum dia voltares a olhar para
// isto tempo procurar tal maneira.
