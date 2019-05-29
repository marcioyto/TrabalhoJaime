package logica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import log.Data;

public class Arquivo {
	private File arquivo;
	private FileWriter writer;
	private String linha;
	private String[] linhas;
	private String url = "bin.log";
	
	public Arquivo(File file , boolean create) {
		File directory = new File(file.getAbsolutePath());
	    if (!directory.exists()) directory.mkdir();
		if (file.exists()) {
			arquivo = file;
				if (!readFile()) {
				System.err.println("Não encontrado!");
			}
		} else {
			if (create) {
				try {
					file.createNewFile();
					arquivo = file;
					readFile();
				} catch (IOException err) {
					System.out.println("Falha ao criar o arquivo. Detalhes: " + err.getMessage());
				}
			} else {
				System.out.println("Este arquivo não existe! Caminho: " + arquivo.getAbsolutePath());
			}
		}
	}
	
	public Arquivo(String caminho, String nome, boolean create) {
		createNewFile("","bin.log",true);
		createNewFile(caminho, nome, create);
	}
	
	private void createNewFile(String caminho, String nome, boolean create) {
		System.out.println(caminho);
		File directory = new File(caminho);
	    if (!directory.exists()) directory.mkdir();
	    //System.out.println(directory.getAbsolutePath());
	    arquivo = new File(directory.getAbsolutePath()+"/" +nome);
	    //System.out.println(arquivo);
	    if (!arquivo.exists() && create) {
	    	try {
	    		//System.out.println("ola");
	    		arquivo.createNewFile();
	    	} catch (IOException e) {
	    		System.out.println("Falha ao criar o arquivo!");
	    	}
	    } else if (!create) {
	    	System.out.println("Este arquivo nao existe! Caminho: " + arquivo.getAbsolutePath());
	    }
	    //System.out.println("ola1");
	    if (arquivo.exists()) readFile();
	}
	public boolean clearFile() {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(arquivo));
			pw.write("");
			pw.close();
			return true;
		} catch (IOException err) {
			System.out.println("Falha ao limpar o arquivo.");
		}
		return false;
		
	}
	

	public boolean checkFile() {
		return arquivo.exists();
	}
	
	
	public boolean checkFileContent() {
		if (arquivo.exists()){
			if (readFile()) {
				return true;
			}
		} else {
			System.out.println("Este arquivo nao existe! Caminho: " + arquivo.getAbsolutePath());
		}
		return false;
	}
	private boolean readFile() {
		if (arquivo.exists()) {
			try {
				if (linhas != null) linhas = null;
				FileReader reader = new FileReader(arquivo);
				BufferedReader leitor = new BufferedReader(reader, 1024*1024);
				this.linha = "";
				String linha = ""; 
				while((linha = leitor.readLine()) != null) {
					this.linha += linha+"\n";
				};
				leitor.close();
				reader.close();
				//System.out.println(this.linha);
				linhas = this.linha.split("\n");
				return true;
			} catch (IOException e) {
				System.err.println("Erro ao ler o arquivo.");
			}
		} else {
			System.err.println(" O arquivo nao existe.");
		}
		return false;
	}
	
	public boolean updateFile() {
		return this.readFile();
	}
	
	
	public String getValue(int i){
		if(linhas.length > i && i > 0 ) {
			return linhas[i];
		}
		return null;
	}
	public boolean salvar(String linhas[]) {
		this.linhas = linhas;
		return saveFile();
	}
	public boolean salvarBinary(Data obj) {
		FileOutputStream fos;
		ObjectOutputStream oos;
		try {
			fos = new FileOutputStream(url);
			oos = new ObjectOutputStream(fos);	
			oos.writeObject(obj);
			oos.flush(); 
			oos.close(); 
			fos.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return false;
	}
	public Object pegaBinary() {
		File file; 
		FileInputStream fis; 
		ObjectInputStream ois;
		Object obj;
		try{
			file = new File(url);
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			obj = ois.readObject();
			ois.close(); 
			fis.close(); 
		}catch(Exception e){
			e.printStackTrace();
			obj = null;
		}
		return obj;
	}
	
	
	private boolean saveFile() {	
		if (arquivo.exists()) {
			try {
				writer = new FileWriter(arquivo);
				
				PrintWriter printWriter = new PrintWriter(new BufferedWriter(writer,1*1024*1024)); 
				printWriter.write("");
				printWriter.flush();
				for (int l = 0; l < linhas.length; l++) {
					//System.out.println(linhas[l]);
					printWriter.println(linhas[l]);
				}
				printWriter.flush();
				printWriter.close();
				writer.close();
				return true;
			} catch (IOException e) {
				System.err.println("Erro ao gravar o arquivo!");
			}
		} else {
			System.err.println("Falha ao gravar! O arquivo especificado nÃ£o existe.");
		}
		return false;
	}
	
	public String[] ler() {
		if(this.readFile()) {
			return this.linhas;
		}else{
			return null;
		}
	}
}
