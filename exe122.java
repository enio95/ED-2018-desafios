import java.util.Scanner;

// este é um exercicio que necessita de um pouco de "cunning"
// existe varias maneiras de fazer isto a força bruta, como
// os codigos que fizeste exe122_1 e exe122_2
// mas eles demoram muito tempo a executar para numeros muito grandes

// vamos pensar em matrizes de array[n][n], as posiçoes array[i][i]
// podem ser calculadas da seguinte maneira,
// f(i) = (2*i+1)**2, sabendo isto o resto é facil.

// so tens que saber que estamos a trabalhar com "quadrados"
// ou seja o comprimento de cada lado vai ser dado da seguinte maneira
// ( f(i)-f(i-1) ) / 4. O i é determinado quando chegares a um numero
// que seja maior do que aquele que queres determinar

// apartir daqui sabes que o numero que queres esta entre f( i ) e f( i - 1)
// como sabes as posiçoes de f(i) e f(i-1) atraves da formula
// consegues chegar a posiçao do valor , usando as regras definadas no exercicio
// e sabendo que cada lado do "quadrado" é ( f(i)-f(i-1) ) / 4

class exe122
{
    public static void main(String[] args)
    {
	Scanner read = new Scanner(System.in);

	int n = read.nextInt(), i = 0;
	
	// vamos encontrar uma posição para qual o valor é maior que n;
	while ( funcao( i ) < n )
	    i++;

	// vamos encontrar o comprimento do nosso "quadrado" ( ver introduçao )

	int dist = ( funcao( i ) - funcao( i - 1 ) ) / 4;

	// não gosto de fazer operaçoes dentro do main, ponto é uma funçao
	// que te vai encontrar o ponto;
	System.out.println( ponto( n, dist, i ) );	
    }

    public static int funcao( int n )
    {
	return ( n*2 + 1) * ( n*2 + 1 );
    }


    public static String ponto( int n, int dist, int i )
    {
	// sei que estas a repetir-te mas sinceramente, é uma gota de agua
	// num oceano, é neglegivel
	int maximo = funcao( i );
	int diferenca = maximo - n;

	// inicialização da minha posição
	int x = i, y = i;

	// nota que podias simplificar as contas, mas sinceramente
	// nao tive pachorra, no futuro se quiseres melhorar tas a vontade
	
	if ( diferenca <= dist ) // o ponto esta a esqueda do maximo
	    x -= diferenca;

	else if ( diferenca <= 2*dist ) // o ponto esta no proximo lado
	    {
		x -= dist;
		y -= ( diferenca - dist );
	    }
	else if ( diferenca <= 3* dist ) // o ponto esta no proximo lado
	    {
		y -= dist;
		x -= dist;
		x += ( diferenca - 2*dist );
	    }
	else // o ponto esta no proximo e ultimo lado do "quadrado"
	    {
		y -= dist;
		y += ( diferenca - 3*dist );
	    }

	return "(" + x + "," + y + ")";
    }
    
}

// Moral da historia, perdeste muito tempo a aprender struturas e a
// aprender a desenhar esta função quando havia uma maneira
// mais interessante e simples de fazer o exercicio

// No futuro pensa no exercicio antes de saltares para cima dele
