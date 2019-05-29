package lista;

public class ListaClassica {
	
	private int tamanho = 0;
	
	private ListaClassica proxima, anterior;
	
	private Object objeto;
	
	public void adicionar(Object objeto) {
		if(tamanho==0) {
			setar(objeto);
		}else {
			ListaClassica nova = new ListaClassica();
			nova.setar(objeto);		
			pegaUltimo().proxima =  nova;
			nova.anterior = pegaUltimo(); 
		}
		tamanho++;
	}
	
	public void setar(Object objeto) {
		this.objeto = objeto;
	}
	public Object pegar(int i) {
		if (i >= tamanho || i < 0) {
			throw new IndexOutOfBoundsException("Tamanho do index inválido!");
		}
		return pegaIndex(i, 0).objeto;
	}
	
	public Object remove(int i) {
		if (i >= tamanho || i < 0) {
			throw new IndexOutOfBoundsException("Tamanho do index inválido!");
		}
		ListaClassica remover = pegaIndex(i, 0);
		remover.anterior.proxima = remover.proxima;
		if(remover.proxima != null) {
			remover.proxima.anterior = remover.anterior;
		}
		tamanho--;
		return remover.objeto;
	}
	
	public int tamanho() {
		return tamanho;
	}
	
	public String toString() {
		String lista = "[";
		for(int i = 0; i < tamanho;i++ ) {
			lista += pegar(i);
		}
		lista += "]";
		return lista;
	}
	
	private ListaClassica pegaIndex(int index, int filtro) {
		if(index != filtro) {
			return proxima.pegaIndex(index,filtro+1);
		}else {
			return this;
		}
	}
	private ListaClassica pegaUltimo() {
		if(proxima != null) {
			return proxima.pegaUltimo();
		}else {
			return this;
		}
	}

}
