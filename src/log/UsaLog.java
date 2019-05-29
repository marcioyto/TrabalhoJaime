package log;

import java.util.Date;

public class UsaLog {
	public static void main(String[] args) {
		Log m = new Log();
		m.log(new Date(), Tipo.TAD3, "Tad3 por aqui 1");
		m.log(new Date(), Tipo.TAD3, "Tad3 por aqui 2");
		m.log(new Date(), Tipo.TAD1, "Tad1 por aqui 1");
		m.log(new Date(), Tipo.TAD1, "Tad1 por aqui 2");
		m.log(new Date(), Tipo.TAD2, "Tad2 por aqui 1");
		m.log(new Date(), Tipo.TAD2, "Tad2 por aqui 2");
		m.log(new Date(), Tipo.SUCCESS, "Succeso por aqui 1");
		m.log(new Date(), Tipo.SUCCESS, "Succeso por aqui 2");

		m.salvar();
		m.carregar();
		m.mostrar();
			
		m.salvarBinary();
		
		m.limpar();
		m.mostrar();
		
		//m.carregarBinary();
		//m.mostrar();
		
		
		
	}
}
