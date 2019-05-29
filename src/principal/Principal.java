package principal;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import log.Log;
import log.Tipo;

public class Principal {
	public static void main(String[] args) {
		String menu = "\t\tMENU\n"
				+ "Escolha uma opção:\n"
				+ "\t1- Adicionar Bloco\n"
				+ "\t2- Mostrar Bloco\n"
				+ "\t3- Mostrar Arquivo\n"
				+ "\t4- Remover Bloco\n"
				+ "\t5- Atualiza Bloco\n"
				+ "\t6- Sair";
		
	    Scanner sc = new Scanner(System.in);
	    Log m = new Log();
	    do {
	    	System.out.print(menu);
	    	if(sc.hasNext()) {
	    		switch (sc.nextInt()) {
				case 1:
					System.out.println("Informe o TIPO (0-TAD1/1-TAD2/2-TAD3/3-SUCCESS):");//TAD1=INFO, TAD2=ERROR, TAD3=WARN, TAD4=SUCCESS
					int tipo = sc.nextInt();
					tipo = (tipo  == Tipo.TAD1 || tipo  == Tipo.TAD2 || tipo  == Tipo.TAD3 || tipo  == Tipo.SUCCESS)? tipo :1;
					System.out.println("Informe a Mensagem:");
					sc = new Scanner(System.in);
					String msgs = sc.nextLine();
					m.carregar();
					m.log(new Date(), tipo, msgs);
					m.salvar();
					m.salvarBinary();
					break;
				case 2:
					m.carregarBinary();
					m.carregar();
					m.mostrar();
					break;
				case 3:
					try {
						Runtime.getRuntime().exec("notepad bin.log");
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 4:
					m.carregarBinary();
					sc = new Scanner(System.in);
					System.out.println("Informe a Mensagem exata:");
					String msg = sc.nextLine();
					m.remover(msg);
					break;
				case 5:
					m.carregarBinary();
					sc = new Scanner(System.in);
					System.out.println("Informe a Mensagem exata:");
					String msgAntiga = sc.nextLine();
					System.out.println("Informe a nova Mensagem:");
					String msgNova = sc.nextLine();
					m.alterar(msgAntiga,msgNova);
					break;
				case 6:
					sc.close(); 
					return;
				}
	    	}
	    }
	    while(true);//Encerra o programa
	}
}
