package log;

import java.io.Serializable;

public class Mensagem implements Serializable{
	private String msg;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String toString() {
		return "Mensagem: "+ msg;
	}
	
	public static Mensagem strToMensagem(String strMsg) {
		Mensagem msg = new Mensagem();
		msg.setMsg(strMsg.replaceAll("Mensagem: ", ""));
		return msg;
	}
}
