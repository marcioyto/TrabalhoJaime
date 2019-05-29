package lista;

import java.io.Serializable;

public class Lista<T> implements Serializable{

	private int tamanho = 0;
	
	private static int TAMANHO_INICIAL = 0;	
	
	private Object objetos[];
	
	public Lista() {
		objetos = new Object[TAMANHO_INICIAL];
	}
	
	public void adicionar(T t) {
		if (tamanho == objetos.length) {
			this.aumentarTamanho();
		}
		objetos[tamanho++] = t;
	}
	
	public T pegar(int i) {
		if (i >= tamanho || i < 0) {
			throw new IndexOutOfBoundsException("Tamanho do index invalido!");
		}
		return (T) objetos[i];
	}
	
	public T remove(int i) {
		if (i >= tamanho || i < 0) {
			throw new IndexOutOfBoundsException("Tamanho do index invalido!");
		}
		Object objetos[] = new Object[tamanho];
		Object obj = new Object();
		for(int j = 0; j < tamanho;j++ ) {
			if(i != j) {
				objetos[j] = this.objetos[j];
			}else {
				obj = this.objetos[j];
			}
		}
		this.objetos = objetos;
		tamanho--;
		return (T) obj;
	}
	
	public int tamanho() {
		return tamanho;
	}
	
	public String toString() {
		String lista = "[";
		for(int i = 0; i < objetos.length;i++ ) {
			lista += objetos[i];
		}
		lista += "]";
		return lista;
	}
	
	private void aumentarTamanho() {
		Object objetos[] = new Object[this.objetos.length+2];
		for(int i = 0; i < this.objetos.length;i++ ) {
			objetos[i] = this.objetos[i];
		}
		this.objetos = objetos;
	}
}
