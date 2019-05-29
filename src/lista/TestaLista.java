package lista;

import log.Mensagem;

public class TestaLista {
	public static void main(String[] args) {
		Lista<Mensagem> listaMsg = new Lista<>();
		
		Mensagem msg = new Mensagem();
		msg.setMsg("Ola");
		listaMsg.adicionar(msg);
		
		msg = new Mensagem();
		msg.setMsg("Ola2");
		listaMsg.adicionar(msg);
		
		msg = new Mensagem();
		msg.setMsg("Ola3");
		listaMsg.adicionar(msg);
		
		msg = new Mensagem();
		msg.setMsg("Ola4");
		listaMsg.adicionar(msg);
		
		System.out.println(listaMsg);
		
		listaMsg.remove(3);
		
		System.out.println(listaMsg);
		
		System.out.println(listaMsg.pegar(2));
		
		
	}
}
