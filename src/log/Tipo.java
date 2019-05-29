package log;

import java.io.Serializable;

import lista.Lista;

public class Tipo implements Serializable{
	private Lista<Mensagem> listaMsg;
	
	public Tipo() {
		listaMsg = new Lista<Mensagem>();
	}
	
	public static int TAD1 = 0;
	public static int TAD2 = 1;
	public static int TAD3 = 2;
	public static int SUCCESS = 3;
	
	private int tipo;
	
	public Lista<Mensagem> getListaMsg() {
		return listaMsg;
	}
	public void setListaMsg(Lista<Mensagem> listaMsg) {
		this.listaMsg = listaMsg;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public String toString() {
		return "Tipo: "+(tipo==TAD1?"TAD1":
			tipo==TAD2?"TAD2":
			tipo==TAD3?"TAD3":
			tipo==SUCCESS?"SUCCESS":"");
	}
	
	public static Tipo strToTipo(String strTipo) {
		Tipo tipo = new Tipo();
		switch(strTipo.replaceAll("Tipo: ","")) {
		case "TAD1":
			tipo.setTipo(TAD1);
			break;
		case "TAD2":
			tipo.setTipo(TAD2);
			break;
		case "TAD3":
			tipo.setTipo(TAD3);
			break;
		case "SUCCESS":
			tipo.setTipo(SUCCESS);
			break;
		}
		return tipo; 
	}
}
