import Project.Leiturista;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2); 
        Leiturista leitor = new Leiturista(semaphore);
        String[] arquivos = {
            "nomescompletos-00",
            "nomescompletos-01",
            "nomescompletos-02",
            "nomescompletos-03",
            "nomescompletos-04",
            "nomescompletos-05",
            "nomescompletos-06",
            "nomescompletos-07",
            "nomescompletos-08",
            "nomescompletos-09"
        };

        Scanner entrada = new Scanner(System.in);
        System.out.println("Informe o nome para busca: ");
        String busca = entrada.nextLine();

        Thread[] threads = new Thread[arquivos.length];
        for (int i = 0; i < arquivos.length; i++) {
            String arquivo = arquivos[i];
            threads[i] = new Thread(() -> {
                try {
                    leitor.lerArquivo(arquivo, busca);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
