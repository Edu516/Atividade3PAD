package Project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class Leiturista {
    private final Semaphore semaphore;
    
    public Leiturista(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public void lerArquivo(String caminhoArquivo, String busca) throws IOException {
        try {
            semaphore.acquire(); 
            BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo + ".txt"));
            String linha;
            System.out.println("Iniciando Busca no arquivo: "+ caminhoArquivo);
            while ((linha = leitor.readLine()) != null) {
                if(linha.toLowerCase().contains(busca)){
                    System.out.println("Resultado da busca em " + caminhoArquivo + ": " + linha);
                }
            }

            leitor.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }
}

