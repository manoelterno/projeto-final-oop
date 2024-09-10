package Servicos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

/**
 * Classe para manipulação de arquivos de texto. Esta classe fornece métodos
 * para abrir, gravar e ler arquivos de texto.
 */
public class ManipularArquivoTexto {

    private static Formatter gravador;
    private static Scanner leitor;

    /**
     * Cria uma nova instância de {@code ManipularArquivoTexto}. O construtor
     * padrão não realiza nenhuma ação adicional.
     */
    public ManipularArquivoTexto() {
    }

    /**
     * Abre um arquivo para gravação. Se o arquivo não puder ser criado, um erro
     * é exibido.
     *
     * @param nomeArquivo O nome do arquivo a ser aberto para gravação.
     */
    public static void abrirArquivoGravacao(String nomeArquivo) {
        try {
            gravador = new Formatter(nomeArquivo);
        } catch (FileNotFoundException ex) {
            System.err.println("Erro ao abrir o arquivo para gravar");
        }
    }

    /**
     * Grava uma linha de texto no arquivo aberto para gravação.
     *
     * @param comando A linha de texto a ser gravada no arquivo.
     */
    public static void gravarArquivo(String comando) {
        gravador.format("%s\n", comando);
    }

    /**
     * Fecha o arquivo aberto para gravação. Se o arquivo não estiver aberto,
     * não realiza nenhuma ação.
     */
    public static void fecharArquivoGravacao() {
        if (gravador != null) {
            gravador.close();
        }
    }

    /**
     * Abre um arquivo para leitura. Se o arquivo não puder ser aberto, um erro
     * é exibido.
     *
     * @param nomeArquivo O nome do arquivo a ser aberto para leitura.
     */
    public static void abrirArquivoLeitura(String nomeArquivo) {
        try {
            leitor = new Scanner(Paths.get(nomeArquivo));
        } catch (IOException ex) {
            System.err.println("Erro ao abrir o arquivo para leitura");
        }
    }

    /**
     * Lê o conteúdo do arquivo aberto para leitura e o retorna como uma lista
     * de strings. Cada linha do arquivo é adicionada à lista.
     *
     * @return Uma lista contendo as linhas do arquivo.
     */
    public static ArrayList<String> lerArquivo() {
        ArrayList<String> retorno = new ArrayList<>();

        while (leitor.hasNext()) {
            retorno.add(leitor.nextLine());
        }

        return retorno;
    }

    /**
     * Fecha o arquivo aberto para leitura. Se o arquivo não estiver aberto, não
     * realiza nenhuma ação.
     */
    public static void fecharArquivoLeitura() {
        if (leitor != null) {
            leitor.close();
        }
    }
}
