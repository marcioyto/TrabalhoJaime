package log;

import java.util.Date;

import lista.Lista;
import logica.Arquivo;

public class Log {
	private Data data = new Data();
	private Arquivo arquivo;

	public Log() {
		arquivo = new Arquivo("", "teste1.txt", true);
	}

	public void log(Date date,int tipo,String mensagem) {
		System.out.println("Data:"+date+" Tipo:"+tipo+" Mesagem:"+mensagem);
		data.setData(date.toString());
		Tipo t = pegarTipo(tipo);
		Mensagem m = new Mensagem();
		m.setMsg(mensagem);
		t.getListaMsg().adicionar(m);
	}
	private Tipo pegarTipo(int tipo) {
		Lista<Tipo> listTipo = data.getListaTipo();
		for(int i=0;i < listTipo.tamanho();i++) {
			if(listTipo.pegar(i).getTipo() == tipo) {
				return listTipo.pegar(i);
			}
		}
		Tipo t = new Tipo();
		t.setTipo(tipo);
		data.getListaTipo().adicionar(t);
		return t;
	}
	public void remover(String msg){
		Lista<Tipo> listTipo = data.getListaTipo();
		boolean removeu = false;
		for(int i=0;i < listTipo.tamanho();i++) {
			System.out.println(listTipo.pegar(i).toString());
			Lista<Mensagem> listMensagem = 	listTipo.pegar(i).getListaMsg();
			for(int j=0;j < listMensagem.tamanho();j++) {
				if(listMensagem.pegar(j).getMsg().equals(msg)){
					listTipo.pegar(i).getListaMsg().remove(j);
					removeu = true;
				}
			}
			if(listTipo.pegar(i).getListaMsg().tamanho() == 0){
				data.getListaTipo().remove(i);
			}
		}
		if(removeu){
			salvarBinary();
			salvar();
			System.out.println("Dado removido com Sucesso!");
		}else{
			System.out.println("Dado nao encontrado!");
		}
	}
	public void alterar(String msgAntiga,String msgNova){
		Lista<Tipo> listTipo = data.getListaTipo();
		boolean alterou = false;
		for(int i=0;i < listTipo.tamanho();i++) {
			Lista<Mensagem> listMensagem = 	listTipo.pegar(i).getListaMsg();
			for(int j=0;j < listMensagem.tamanho();j++) {
				if(listMensagem.pegar(j).getMsg().equals(msgAntiga)){
					listMensagem.pegar(j).setMsg(msgNova);
					alterou = true;
				}
			}
			if(listTipo.pegar(i).getListaMsg().tamanho() == 0){
				data.getListaTipo().remove(i);
			}
		}
		if(alterou){
			salvarBinary();
			salvar();
			System.out.println("Dado alterado com Sucesso!");
		}else{
			System.out.println("Dado no encontrado!");
		}
	}
	public void salvar() {
		String contexto = ""+data+"\n";
		for(int i=0;i < data.getListaTipo().tamanho();i++) {
			contexto += "\t"+data.getListaTipo().pegar(i)+"\n";
			for(int j=0;j < data.getListaTipo().pegar(i).getListaMsg().tamanho();j++) {
				contexto += "\t\t"+data.getListaTipo().pegar(i).getListaMsg().pegar(j)+"\n";
			}
		}
		arquivo.salvar(contexto.split("\n"));
	}
	public void salvarBinary() {
		arquivo.salvarBinary(data);
	}
	public void carregarBinary() {
		data = (Data)arquivo.pegaBinary();
		mostrar();
	}
	public void limpar() {
		data = new Data();
	}
	
	
	public void mostrar() {
		System.out.println(data);
		for(int i=0;i < data.getListaTipo().tamanho();i++) {
			System.out.println("|--"+data.getListaTipo().pegar(i));
			for(int j=0;j < data.getListaTipo().pegar(i).getListaMsg().tamanho();j++) {
				System.out.println("|----"+data.getListaTipo().pegar(i).getListaMsg().pegar(j));
			}
		}
	}
	public void carregar() {
		String[] linhas = this.arquivo.ler();
		if(linhas.length > 0) {
			int tipo = -1;
			data = new Data();
			for(int i=0;i < linhas.length;i++) {
				//System.out.println(linhas[i].split("\t").length);
				switch(linhas[i].split("\t").length) {
				case 1:
						data = data.strToData(linhas[i]);
					break;
				case 2:
					data.getListaTipo().adicionar(Tipo.strToTipo(linhas[i].trim()));
					tipo++;
					break;
				case 3:
					data.getListaTipo().pegar(tipo).getListaMsg().adicionar(Mensagem.strToMensagem(linhas[i].trim()));
					break;
	
				}
			}
		}
	}
}
