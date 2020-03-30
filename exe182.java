import java.util.Scanner;

public class exe182
{

    public static void main(String[] args)
    {
	Scanner read = new Scanner(System.in);

	String operacao = read.nextLine();

	int len = operacao.length() - 1;

        // Bem o que estamos basicamente a fazer neste ciclo é
	// escrever linha a linha, cada "operacao" tem 7 linhas
	
	for ( int linhas = 1 ; linhas <= 7; linhas++)
	    {	      	
		for ( int i = 0; i<=len; i++)
		    {
			// A função atribuidor retorna uma string
			// correspondente a representação numa dada linha de uma
			// operacao
		        desenhar( terminal( operacao.charAt(i), linhas) );

			// este if esta aqui, para ser separador de entre os
			// characters na variavel operacao
			if ( i!=len )
			    System.out.print(" ");
			else
			    System.out.println();
		    }
	    }
    }

    // Este é uma pequena função, tudo que esta função deve receber é 1 e 0.
    // Exemplo a primeira linha do numero 9 vai ser representado pela string "0110"
    // Cada numero/operador ira ter a sua representação, e cada representação
    // Esta dividida por linhas

    //Esta função tambem é pratica se quisermos mudar o estilo da representação"
    
    public static void desenhar( String str )
    {
	for ( int i = 0; i<=3; i++)
	    {
		if ( str.charAt( i ) == '0' )
		    System.out.print(".");
		
		else
		    System.out.print("#");
	    }
    }


    //Esta função terminal é basicamente terminal de autocarros, digamos que
    //dou um input e a função terminal e ela acha o caminho, preferi usar
    //este metodo porque simplificava as coisas na minha cabeça e porque
    //no futuro, se quiser adicionar mais simblos de operadres,
    //tudo o que tenho que fazer é escrever a representação deles e
    //adicionar um caminho nesta função
 
    public static String terminal( char x, int linha )
    {
	switch ( x )
	    {
	    case '0':
		return zero( linha );
		
	    case '1':
		return um( linha );
		
	    case '2':
		return dois( linha );
		
	    case '3':
		return tres( linha );
		
	    case '4':
		return quatro( linha );
		
	    case '5':
		return cinco( linha );
		
	    case '6':
		return seis( linha );
		
	    case '7':
		return sete( linha );
		
	    case '8':
		return oito( linha );
		
	    case '9':
		return nove( linha );

	    case '+':
		return mais( linha );

	    case '-':
		return menos( linha );

	    case '/' :
		return dividir( linha );

	    case '*':
		return multiplicar( linha );

	    case '=':
		return igual( linha );
		
	    default :
		return "wtf";
	    }
    }
    
    // estes esquemas serve para caso no futuro queiras mudar a forma dos traços
    
    public static String esquema1()
    {
	return  "0000";
    }
    
    public static String esquema2()
    {
	return  "1000";
    }
    
    public static String esquema3()
    {
	return  "0001";
    }
    
    public static String esquema4()
    {
	return  "0110";
    }
    
    public static String esquema5()
    {
	return  "1001";
    }
    
    public static String esquema6()
    {
	return  "1111";
    }
    
    // Nesta parte estão as representações dos numero e operados
    // No futuro se quiseres adicionar characters vens aqui e adicionas
    // usando as esquemas de maneira a no futuro ser possivel mudar radicalmente
    // o formato dos characteres
    
    public static String zero( int i)
    {
	if( i==1 || i==7 )
	    return esquema4();

	else if( i==4 )
	    return esquema1() ;

	else
	    return esquema5();	    
    }
    
    public static String um( int i)
    {	    
	if ( i==1 ||i==4 || i==7 )
	    return esquema1();
	else
	    return esquema3();	    
    }

    public static String dois( int i )
    {
  	if( i==1 || i==4 || i==7 )
	    return esquema4();

	else if( i==2 || i==3 )
	    return esquema3();

	else
	    return esquema2();	    
    }

    public static String tres( int i )
    {
	if( i==1 || i==4 || i==7 )
	    return esquema4();
		
	else
	    return esquema3();
    }
    
    public static String quatro( int i )
    {
	if ( i==1 || i== 7 )
	    return esquema1();
		
	else if ( i==2 || i==3 )
	    return esquema5();
		
	else if ( i==4)
	    return esquema4();
		
	else
	    return esquema3();
    }
    
    public static String cinco( int i )
    {
	if( i==1 || i==4 || i==7 )
	    return esquema4();

	else if( i==2 || i==3 )
	    return esquema2();

	else
	    return esquema3();
    }
       
    public static String seis( int i )
    {
	if( i==1 || i==4 || i==7 )
	    return esquema4();

	else if( i==2 || i==3 )
	    return esquema2();

	else
	    return esquema5();
    }
    
    public static String sete( int i )
    {
	if( i==1  )
	    return esquema4();

	else if( i==2 || i==3 )
	    return esquema5();

	else if ( i==5 || i==6 )
	    return esquema3();
	else
	    return esquema1();
    }

    public static String oito( int i )
    {
	if( i==1 || i==4 || i==7 )
	    return esquema4();

	else
	    return esquema5();
    }
    
    public static String nove( int i )
    {
	if( i==1 || i==4 || i==7 )
	    return esquema4();

	else if( i==2 || i==3 )
	    return esquema5();

	else
	    return esquema3();
    }

    public static String mais( int i )
    {
	if( i==3 || i==5 )
	    return esquema4();

	else if( i==4 )
	    return esquema6();

	else
	    return esquema1();
    }
    
    public static String menos( int i )
    {
	if( i==4 )
	    return esquema4();
	else
	    return esquema1();
    }

    //esta representação nao usa esquemas porque ela é especial
    //ela tem traços diagonais 
    public static String dividir( int i )
    {
        if ( i == 3 )
	    return "0001";
	else if ( i == 4)
	    return "0010";
	else if ( i == 5)
	    return "0100";
	else if (i == 6 )
	    return "1000";
	else
	    return esquema1();	             
    }
    
    public static String multiplicar( int i )
    {
	if( i==4 || i==5 )
	    return esquema4();
	
	else
	    return esquema1();
    }
    
    public static String igual( int i )
    {
	if( i==3 || i==5 )
	    return esquema4();

	else
	    return esquema1();
    }
}
