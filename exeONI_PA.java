import java.util.Scanner;

public class exeONI_PA
{
    public static void main(String[] args)
    {
	Scanner read = new Scanner(System.in);

	int M = read.nextInt(); // guarda o nº max de letras dif
	read.nextLine(); // le o mudança de linha
	    
	String str = read.nextLine(); // a frase a analisar	

	// vou assumir que a string nunca é vazia
	sub_set( str, M );
    }

    public static void sub_set( String str, int M )
    {
	abecedario2 abc = new abecedario2();
	int c=0; // tamanho do subset (temp)
	int real_c=0; // tamanho do subset (maximo)
	int i=0; // posição incial do subset (temp)
	int real_i=0; // posicao inicial do subset (maximo)
	int f=0; // posiçao final do subset (temp)
	int real_f=0; // posiçao final do subset (maximo)
	int len = str.length(); // tamanho da str

	// descobrir primeiro sub_set que sastifaz a nossa condiçao
        while ( abc.get() <= M && f<len )
	    {
		abc.save( str.charAt(f) );
		c++;
		if ( abc.get() <= M && f<len )
		    f++;
	    }
	
	// existe a opção que tenhamos ultrapassado o M e nao
	// tenhamos chegado ao fim da string, entao sera necessario
	// reajustar o contador e o m
	if ( abc.get() > M )
	    {
		abc.remove( str.charAt(f) );
		f--;
		c--;
	    }
	
	// este if esta aqui porque estava com problemas quando
	// o subset inicial é igual a string toda, dai a teu
	// posto esta condição
	if ( c==len )
	    {
		System.out.println(c);
		print_subset( str, 0, c );
		return;
	    }
	else
	    real_c = c;

	// basicamente o que fazemos é "retirar" o primeiro elemento
	// do subset ate que m<M depois acrescentamos letras no
	// fim ate que m>=M (inclusive)
	// relembro que m diz-nos o nº de letras dif no subset

	// no final de cada iteração vemos se o subset atual
	// é maior que o subset guardado, se for atualizamos
	// o subset guardado
	
	while ( f!=len-1 )
	    {
		abc.remove( str.charAt(i) );
		i++;
		c--;
		
		// se m<M 
		if ( abc.get() < M )
		    {
			// neste ciclo acrescentamos no final
			// ate m>M ou f==len
			while ( f!=len-1 && abc.get()<=M )
			    {
				f++;
				abc.save( str.charAt(f) );
				c++;
			    }

			// existe a opção que tenhamos ultrapassado
			// o M e nao tenhamos chegado ao fim da
			// string, entao sera necessario reajustar
			// o contador, o m e f
			
			if ( abc.get()>M )
			    {
				abc.remove( str.charAt(f) );
				f--;
				c--;
			    }

			// se o subset currente é maior que
			// o subset guardado
			if ( c > real_c )
			    {
				real_i = i;
				real_c = c;
			    }
		    }
	    }
	System.out.println(real_c);
        print_subset( str, real_i, real_c );        
    }
    
    public static void print_subset( String str, int start, int c)
    {	
	for ( int i=0; i<c; i++ )
	    {
		System.out.print(str.charAt(i+start));
	    }
	
	System.out.println();
    }
    
}


class abecedario2
{
    private int[] lista; // guarda o numero de letras em cada subset
    private int m; // guada o nº diferente de letras
    
    // this.lista é uma representaçao do abcedario.
    // exemplo lista[0]==3 diz-nos que temos 3's 'a' ou 'A' na
    // nosso subset.
    abecedario2()
    {
	// o abecedario tem 26 letras mas adicionei a 27º posição
	// para "guardar" os characteres que não sao letras
	lista = new int[27];
	m = 0;
    }
    
    public void remove ( char ch )
    {
	int n = function( ch ); // nao sei o que chamar a isto

	// se a lista[n]==1 implica que temos apenas uma
	// instancia da dada letra, entao se removermos a unica
	// essa letra o m diminui (duh)
	if ( n<26 && lista[n]==1 )
	    {
		lista[n]--;
		m--;
	    }

	else if ( n<26 && lista[n]>1 )
	    lista[n]--;

    }
	

    public void save( char ch )
    {
	int n = function( ch );;

	// se lista[n] == 0 e acrescentamos uma instancia dessa letra
	// entao o m aumenta ;)
	if ( n!=26 && this.lista[n]==0)
	    {
		lista[n]++;
		this.m++;
	    }

	else if ( n!=26 )
	    lista[n]++;
    }

    
    public int function( char ch )
    {
	if ( 'A'<=ch && ch<='Z' )
	    return ch - 'A';

	else if ( 'a'<=ch && ch<='z' )
	    return ch - 'a';

	else
	    return 26; // -> "garbage"
    }
    
    public int get()
    {
	return m;
    }
}
