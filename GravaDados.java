

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;



public class GravaDados {
	
	public static String getStringTamanhoFixo(String texto, int tamanho) {
		StringBuffer s1 = new StringBuffer(texto);
		s1.setLength(tamanho);
		return s1.toString();
	}
	
	public static void main(String[] args) {
		
		try {
			
			//Define o nome do arquivo a ser trabalhado
			System.out.print("Informe o nome do Arquivo a ser gravado: ");
			String nomeArquivo = Teclado.readLine();			
			
			//Objetos utilizados na manipulação do arquivo e seus dados
			FileOutputStream arqSaida = new FileOutputStream(nomeArquivo);
			PrintStream saida = new PrintStream(arqSaida);
			
			System.out.print("Informe o número de registros a serem gravados: ");
			int numRegistros = Teclado.readInt();			
			
			//Adiciona os registros desejados
			for(int i=0;i<numRegistros;i++) {
				System.out.print("Informe o código do Veículo: ");
				int codigo = Teclado.readInt();
				
				System.out.print("Nome Veiculo: ");
				String nomeVeiculo = Teclado.readLine();
				
				String linha = codigo + " " + nomeVeiculo;
				saida.println(linha);
				
				// >>> E se a duas linhas a seguir substituissem as 2 linhas anteriores? O que aconteceria com os registros?
				// >>> String linha2 = getStringTamanhoFixo(codigo+"", 5) + " " + getStringTamanhoFixo(nomeVeiculo, 15); 
				// >>> saida.println(linha2);

			}

			//Finalização da manipulação dos dados no arquivo
			saida.flush();
			saida.close();
			arqSaida.close();
			
			System.out.println("Dados Gravados com sucesso");
			String escolha = JOptionPane.showInputDialog(null, "Deseja salvar como?\n1- Sim\n 2- Não",
					"Salvar como", JOptionPane.OK_CANCEL_OPTION);
			
			int op=Integer.parseInt(escolha);
			if(op==1) {
				System.out.println("Digite o nome do arquivo: ");
				String nomeNovo = Teclado.readLine();	
				salvarComo(nomeArquivo, nomeNovo);
			}
			else {
				System.out.println(" ");
			}
			
		}
		catch(Exception e) { //Tratamento genérico da excessão ocorrida
			System.out.println("O seguinte erro ocorreu: " + e.toString());
		}
		
	}
	
	static void salvarComo(String atual, String Novo) throws IOException {
		
		
		FileInputStream arqEntrada = new FileInputStream(atual);
		DataInputStream entrada = new DataInputStream(arqEntrada);
		
		FileOutputStream arqSaida = new FileOutputStream(Novo);
		PrintStream saida = new PrintStream(arqSaida);
		
		
		while (entrada.available() != 0) { 
			
			String linha = entrada.readLine(); 
			StringTokenizer listaPalavras = new StringTokenizer(linha); 
			
			String codigoLinha = listaPalavras.nextToken();
			String nomeLinha = listaPalavras.nextToken();
			
			String linha1 = codigoLinha + " " + nomeLinha;
			saida.println(linha1);
		}
		
		saida.flush();
		saida.close();
		arqSaida.close();
		
	
		entrada.close();
		arqEntrada.close();
	}
}
