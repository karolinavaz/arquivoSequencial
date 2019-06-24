

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;



public class PesquisaSequencialDados {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {

		//Define o nome do arquivo a ser trabalhado		
		System.out.print("Informe o nome do Arquivo a ser pesquisado: ");
		String nomeArquivo = Teclado.readLine();	

		int numRegPesquisados = 0;		
		
		try {
			
			//Objetos utilizados na manipulação do arquivo e seus dados			
			FileInputStream arqEntrada = new FileInputStream(nomeArquivo);
			DataInputStream entrada = new DataInputStream(arqEntrada);

			System.out.print("Digite o nome do Veículo a ser pesquisado: ");
			String nome = Teclado.readLine();
			
			boolean registroEncontrado = false;

			//Realiza a pesquisa no arquivo
			while (entrada.available() != 0) { //significa que não chegou no fim do arquivo
				
				String linha = entrada.readLine(); //lê uma linha
				StringTokenizer listaPalavras = new StringTokenizer(linha); //Pega uma linha e gera um vetor com as palavras desta linha
				
				String codigoLinha = listaPalavras.nextToken();
				String nomeLinha = listaPalavras.nextToken();
				
				numRegPesquisados++;
				if( nomeLinha.startsWith(nome) ) { 
					System.out.println(linha);
					System.out.println("Registro encontrado!\n");
					registroEncontrado = true;
					break;
				}
			}
			if (!registroEncontrado)
				System.out.println("Registro nao encontrado!\n");
		}
		catch(FileNotFoundException e) {
			System.out.println("O Arquivo informado não existe.\n" + e.toString());
		}		

		System.out.println("Registros testados: " + numRegPesquisados);
		
	}
}
