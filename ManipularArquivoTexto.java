
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class ManipularArquivoTexto {

    private static Formatter gravador;
    private static Scanner leitor;

    public ManipularArquivoTexto() {
    }

    public static void abrirArquivoGravacao(String nomeArquivo) {
        try {
            gravador = new Formatter(nomeArquivo);
        } catch (FileNotFoundException ex) {
            System.err.println("Erro ao abrir o arquivo para gravar");
        }
    }

    public static void gravarArquivo(String comando) {
        gravador.format("%s\n", comando);
    }

    public static void fecharArquivoGravacao() {
        if (gravador != null) {
            gravador.close();
        }
    }

    public static void abrirArquivoLeitura(String nomeArquivo) {
        try {
            leitor = new Scanner(Paths.get(nomeArquivo));
        } catch (IOException ex) {
            System.err.println("Erro ao abrir o arquivo para leitura");
        }
    }

    public static ArrayList<String> lerArquivo() {
        ArrayList<String> retorno = new ArrayList<>();

        while (leitor.hasNext()) {
            retorno.add(leitor.nextLine());
        }

        return retorno;
    }

    public static void fecharArquivoLeitura() {
        if (leitor != null) {
            leitor.close();
        }
    }
}
