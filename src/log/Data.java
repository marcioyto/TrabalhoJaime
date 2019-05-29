package log;

import java.io.Serializable;

import lista.Lista;

public class Data implements Serializable {
	private Lista<Tipo> listaTipo;
	
	public Data() {
		listaTipo = new Lista<Tipo>();
	}
	
	private String data;
	
	public Lista<Tipo> getListaTipo() {
		return listaTipo;
	}
	public void setListaTipo(Lista<Tipo> listaTipo) {
		this.listaTipo = listaTipo;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public String toString() {
		return "Data: " + data;
	}
	public Data strToData(String strData) {
		Data data = new Data();
		data.setData(strData.replaceAll("Data: ", ""));
		return  data;
	}
}
