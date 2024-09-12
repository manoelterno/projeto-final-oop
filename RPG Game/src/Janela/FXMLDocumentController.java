package janela;

import Servicos.ManipularArquivoTexto;
import Principal.Jogo;
import Principal.Cavaleiro;
import Principal.Mago;
import Principal.Player;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.util.Duration;
import javax.jws.soap.SOAPBinding.Use;
import javax.swing.JOptionPane;
import static jdk.nashorn.tools.ShellFunctions.input;

/**
 * Controlador da interface gráfica FXML do jogo RPG. Responsável por gerenciar
 * as interações do usuário com a interface e atualizar o estado do jogo de
 * acordo com as ações realizadas. Implementa a interface {@link Initializable}.
 *
 * <p>
 * Esta classe utiliza JavaFX para a criação da interface gráfica e manipula
 * componentes visuais como botões, rótulos, imagens, e retângulos. As ações
 * incluem ataques, uso de poções, escolha de classes, e controle do progresso
 * do jogo. Além disso, logs das ações são gravados em um arquivo de texto.
 * </p>
 */
public class FXMLDocumentController implements Initializable {

    /**
     * Retângulo que representa o lugar onde as informações da rodada serão mostradas.
     */
    @FXML
    private Rectangle retangulo1;

    /**
     * Retângulo que representa o lugar onde as informações das poções serão mostradas.
     */
    @FXML
    private Rectangle retangulo2;

    /**
     * Retângulo que representa o espaço para poções.
     */
    @FXML
    private Rectangle recPocoes;

    /**
     * Rótulo que exibe o número de poções de cura disponíveis.
     */
    @FXML
    private Label lbl_cura;

    /**
     * Rótulo que exibe o número de poções de força disponíveis.
     */
    @FXML
    private Label lbl_forca;

    /**
     * Rótulo que exibe a quantidade de vida do monstro.
     */
    @FXML
    private Label lbl_vidaMonstro;

    /**
     * Rótulo que exibe o log das ações do jogador.
     */
    @FXML
    private Label lbl_logJogador;

    /**
     * Rótulo que exibe o log das ações do monstro.
     */
    @FXML
    private Label lbl_logMonstro;

    /**
     * Rótulo que exibe o número da rodada atual.
     */
    @FXML
    private Label lbl_rodadas;

    /**
     * Rótulo que exibe a quantidade de vida do jogador.
     */
    @FXML
    private Label lbl_vidaJogador;

    /**
     * Botão para a escolha da classe Cavaleiro.
     */
    @FXML
    private Button btn_cavaleiro;

    /**
     * Botão para sair do jogo.
     */
    @FXML
    private Button btn_sair;

    /**
     * Botão para a escolha da classe Mago.
     */
    @FXML
    private Button btn_mago;

    /**
     * Botão de ajuda que exibe o guia do jogo.
     */
    @FXML
    private Button btn_ajuda;

    /**
     * Botão para realizar o ataque normal.
     */
    @FXML
    private Button btn_ataqueNormal;

    /**
     * Botão para realizar o ataque especial.
     */
    @FXML
    private Button btn_ataqueEspecial;

    /**
     * Botão para atacar o monstro.
     */
    @FXML
    private Button btn_atacar;

    /**
     * Botão para usar itens.
     */
    @FXML
    private Button btn_item;

    /**
     * Botão para usar a poção de força.
     */
    @FXML
    private Button btn_forca;

    /**
     * Botão para usar a poção de cura.
     */
    @FXML
    private Button btn_cura;

    /**
     * Botão para voltar ao menu anterior.
     */
    @FXML
    private Button btn_voltar;

    /**
     * Imagem da poção de força.
     */
    @FXML
    private ImageView imgPocaoForca;

    /**
     * Imagem da poção de cura.
     */
    @FXML
    private ImageView imgPocaoCura;

    /**
     * Imagem do monstro.
     */
    @FXML
    private ImageView imgMonstro;

    /**
     * Imagem do Mago.
     */
    @FXML
    private ImageView imgMago;

    /**
     * Imagem do Cavaleiro.
     */
    @FXML
    private ImageView imgCavaleiro;

    /**
     * Imagem que aparece quando o monstro está morto.
     */
    @FXML
    private ImageView imgMonstroMorto;

    /**
     * Imagem que aparece quando o Mago está morto.
     */
    @FXML
    private ImageView imgMagoMorto;

    /**
     * Imagem que aparece quando o Cavaleiro está morto.
     */
    @FXML
    private ImageView imgCavaleiroMorto;

    /**
     * Imagem que aparece quando o jogo termina em derrota (Game Over).
     */
    @FXML
    private ImageView imgGameover;

    /**
     * Imagem que aparece quando o jogador vence o jogo.
     */
    @FXML
    private ImageView imgWin;

    /**
     * Objeto da classe Jogo que gerencia a lógica do jogo.
     */
    private Jogo jogo;

    /**
     * Contador de rodadas do jogo.
     */
    private int contador_rodadas = 1;

    /**
     * Método chamado durante a inicialização da interface FXML. Define o
     * comportamento inicial dos componentes visuais e inicia o jogo.
     *
     * @param url URL do arquivo FXML.
     * @param rb ResourceBundle para localização de recursos.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciar_jogo();
        jogo = new Jogo();

        lbl_logJogador.setPrefWidth(365);
        lbl_logMonstro.setPrefWidth(365);

        translateImg(imgCavaleiro, 50);
        translateImg(imgMonstro, -50);
        translateImg(imgMago, 50);
    }

    /**
     * Inicia o estado inicial do jogo, ocultando ou desativando elementos da
     * interface que não devem estar visíveis ou disponíveis no começo do jogo.
     */
    private void iniciar_jogo() {
        btn_cavaleiro.setText("CAVALEIRO");
        btn_mago.setText("MAGO");
        btn_item.setVisible(false);
        btn_atacar.setVisible(false);
        btn_ataqueEspecial.setVisible(false);
        btn_ataqueNormal.setVisible(false);
        btn_cura.setVisible(false);
        btn_forca.setVisible(false);
        btn_voltar.setVisible(false);

        lbl_logJogador.setText("Selecione sua classe");
        lbl_vidaJogador.setVisible(false);
        lbl_vidaMonstro.setVisible(false);
        lbl_rodadas.setText(Integer.toString(contador_rodadas));
        lbl_cura.setVisible(false);
        lbl_forca.setVisible(false);
        lbl_logMonstro.setVisible(false);

        retangulo2.setVisible(false);
        recPocoes.setVisible(false);

        imgPocaoCura.setVisible(false);
        imgPocaoForca.setVisible(false);
        imgGameover.setVisible(false);
        imgWin.setVisible(false);

        ManipularArquivoTexto.abrirArquivoGravacao("log.txt");
    }

    /**
     * Método que inicia o jogo ao selecionar a classe Cavaleiro. Configura os
     * componentes da interface para o Cavaleiro e registra o início da batalha
     * no arquivo de log.
     *
     * @param event Evento acionado pelo botão de escolha da classe Cavaleiro.
     */
    @FXML
    private void botaoCavaleiro(ActionEvent event) {
        jogo.instanciarCavaleiro();

        lbl_logJogador.setText("");
        lbl_vidaJogador.setVisible(true);
        lbl_vidaMonstro.setVisible(true);
        lbl_vidaJogador.setText(Integer.toString(jogo.getJogador().getVida()));
        lbl_vidaMonstro.setText(Integer.toString(jogo.getCHEFAO().getVida()));
        lbl_cura.setVisible(true);
        lbl_forca.setVisible(true);
        lbl_cura.setText("Poção de Cura: " + ((Player) jogo.getJogador()).getInventario().getNum_pocao_vida());
        lbl_forca.setText("Poção de Força: " + ((Player) jogo.getJogador()).getInventario().getNum_pocao_forca());

        btn_cavaleiro.setVisible(false);
        btn_mago.setVisible(false);
        btn_item.setVisible(true);
        btn_atacar.setVisible(true);

        retangulo2.setVisible(true);
        recPocoes.setVisible(true);

        imgPocaoCura.setVisible(true);
        imgPocaoForca.setVisible(true);
        imgMonstro.setVisible(true);
        imgCavaleiro.setVisible(true);

        ManipularArquivoTexto.gravarArquivo("INICIO DA BATALHA");
        ManipularArquivoTexto.gravarArquivo("Vida do " + jogo.getJogador().getClass().getSimpleName() + ": " + jogo.getJogador().getVida());
        ManipularArquivoTexto.gravarArquivo("Vida do " + jogo.getCHEFAO().getClass().getSimpleName() + ": " + jogo.getCHEFAO().getVida() + "\n");

    }

    /**
     * Manipula a ação do botão de escolha do Mago. Quando clicado, inicializa a
     * batalha com o jogador como Mago. Atualiza as labels de vida, itens,
     * imagens e registros de log.
     *
     * @param event O evento de ação disparado pelo botão.
     */
    @FXML
    private void botaoMago(ActionEvent event) {
        jogo.instanciarMago();

        lbl_logJogador.setText("");
        lbl_vidaJogador.setVisible(true);
        lbl_vidaMonstro.setVisible(true);
        lbl_vidaJogador.setText(Integer.toString(jogo.getJogador().getVida()));
        lbl_vidaMonstro.setText(Integer.toString(jogo.getCHEFAO().getVida()));
        lbl_cura.setVisible(true);
        lbl_forca.setVisible(true);
        lbl_cura.setText("Poção de Cura: " + ((Player) jogo.getJogador()).getInventario().getNum_pocao_vida());
        lbl_forca.setText("Poção de Força: " + ((Player) jogo.getJogador()).getInventario().getNum_pocao_forca());

        btn_cavaleiro.setVisible(false);
        btn_mago.setVisible(false);
        btn_item.setVisible(true);
        btn_atacar.setVisible(true);

        retangulo2.setVisible(true);
        recPocoes.setVisible(true);

        imgPocaoCura.setVisible(true);
        imgPocaoForca.setVisible(true);
        imgMonstro.setVisible(true);
        imgMago.setVisible(true);

        ManipularArquivoTexto.gravarArquivo("INICIO DA BATALHA");
        ManipularArquivoTexto.gravarArquivo("Vida do " + jogo.getJogador().getClass().getSimpleName() + ": " + jogo.getJogador().getVida());
        ManipularArquivoTexto.gravarArquivo("Vida do " + jogo.getCHEFAO().getClass().getSimpleName() + ": " + jogo.getCHEFAO().getVida() + "\n");
    }

    /**
     * Manipula a ação do botão "Atacar". Quando clicado, exibe as opções de
     * ataque normal e especial. Desativa os botões de ação durante o processo
     * de escolha.
     *
     * @param event O evento de ação disparado pelo botão.
     */
    @FXML
    private void botaoAtacar(ActionEvent event) {
        btn_atacar.setDisable(true);
        btn_item.setDisable(true);
        btn_ataqueNormal.setVisible(true);
        btn_voltar.setVisible(true);
        btn_ataqueEspecial.setVisible(true);

        if (((Player) jogo.getJogador()).getDelay_especial() > 0) {
            btn_ataqueEspecial.setDisable(true);
        } else {
            btn_ataqueEspecial.setDisable(false);
        }

    }

    /**
     * Executa o ataque normal do jogador. Atualiza a interface e desativa as
     * opções de ataque. Verifica se o jogo terminou e, caso contrário, atualiza
     * o estado do jogo.
     *
     * @param event O evento de ação disparado pelo botão.
     */
    @FXML
    private void botaoAtaqueNormal(ActionEvent event) {
        btn_voltar.setVisible(false);
        btn_ataqueNormal.setVisible(false);
        btn_ataqueEspecial.setVisible(false);
        btn_atacar.setDisable(false);
        btn_item.setDisable(false);

        if (jogo.getJogador() instanceof Cavaleiro) {
            jogo.ataqueCavaleiro(1);
            lbl_logJogador.setText("Você causou " + ((Cavaleiro) jogo.getJogador()).getDANO_NORMAL() + " de dano");
        } else if (jogo.getJogador() instanceof Mago) {
            jogo.ataqueMago(1);
            lbl_logJogador.setText("Você causou " + ((Mago) jogo.getJogador()).getDANO_NORMAL() + " de dano");
        }

        if (jogo.verificarFimDeJogo()) {
            lbl_vidaMonstro.setText(Integer.toString(jogo.getCHEFAO().getVida()));

            btn_atacar.setDisable(true);
            btn_item.setDisable(true);

            imgWin.setVisible(true);

            imgMonstro.setVisible(false);
            imgMonstroMorto.setVisible(true);

            gravarRodada();
        } else {
            atualizarJogo();
        }

    }

    /**
     * Executa o ataque especial do jogador. Atualiza a interface e verifica se
     * o jogo terminou. Caso o jogador tenha algum bônus de força, o dano
     * causado é dobrado.
     *
     * @param event O evento de ação disparado pelo botão.
     */
    @FXML
    private void botaoAtaqueEspecial(ActionEvent event) {
        btn_voltar.setVisible(false);
        btn_atacar.setDisable(false);
        btn_item.setDisable(false);
        btn_ataqueNormal.setVisible(false);
        btn_ataqueEspecial.setVisible(false);

        if (jogo.getJogador() instanceof Cavaleiro) {
            if (((Player) jogo.getJogador()).getInventario().isEspecial()) {
                lbl_logJogador.setText("Você causou " + ((Cavaleiro) jogo.getJogador()).getDANO_ESPECIAL() * 2 + " de dano");
                lbl_logJogador.setWrapText(true);
            } else {
                lbl_logJogador.setText("Você causou " + ((Cavaleiro) jogo.getJogador()).getDANO_ESPECIAL() + " de dano");
                lbl_logJogador.setWrapText(true);
            }
            jogo.ataqueCavaleiro(2);
        } else if (jogo.getJogador() instanceof Mago) {

            if (((Player) jogo.getJogador()).getInventario().isEspecial()) {
                lbl_logJogador.setText("Você causou " + ((Mago) jogo.getJogador()).getDANO_ESPECIAL() * 2 + " de dano");
                lbl_logJogador.setWrapText(true);
            } else {
                lbl_logJogador.setText("Você causou " + ((Mago) jogo.getJogador()).getDANO_ESPECIAL() + " de dano");
                lbl_logJogador.setWrapText(true);
            }
            jogo.ataqueMago(2);
        }

        if (jogo.verificarFimDeJogo()) {
            lbl_vidaMonstro.setText(Integer.toString(jogo.getCHEFAO().getVida()));

            btn_atacar.setDisable(true);
            btn_item.setDisable(true);

            imgWin.setVisible(true);

            imgMonstro.setVisible(false);
            imgMonstroMorto.setVisible(true);

            gravarRodada();
        } else {
            atualizarJogo();
        }
    }

    /**
     * Manipula a ação do botão "Usar Item". Exibe as opções de poções
     * disponíveis para o jogador.
     *
     * @param event O evento de ação disparado pelo botão.
     */
    @FXML
    private void botaoUsarItem(ActionEvent event) {
        btn_atacar.setDisable(true);
        btn_item.setDisable(true);
        btn_cura.setVisible(true);
        btn_forca.setVisible(true);
        btn_voltar.setVisible(true);

    }

    /**
     * Usa a poção de cura do inventário do jogador. Atualiza a vida do jogador
     * e o estado da interface. Verifica se o jogador ainda possui poções de
     * cura restantes.
     *
     * @param event O evento de ação disparado pelo botão.
     */
    @FXML
    private void botaoCura(ActionEvent event) {
        jogo.pocaoCura();

        btn_voltar.setVisible(false);
        btn_atacar.setDisable(false);
        btn_item.setDisable(false);
        btn_cura.setVisible(false);
        btn_forca.setVisible(false);

        lbl_logJogador.setText("Você curou 300 de vida");

        atualizarJogo();

        if (((Player) jogo.getJogador()).getInventario().getNum_pocao_vida() <= 0) {
            btn_cura.setDisable(true);
        }

    }

    /**
     * Usa a poção de força do inventário do jogador. Dobra o dano do ataque
     * especial do jogador. Verifica se o jogador ainda possui poções de força
     * restantes.
     *
     * @param event O evento de ação disparado pelo botão.
     */
    @FXML
    private void botaoForca(ActionEvent event) {
        jogo.pocaoForca();
        btn_voltar.setVisible(false);
        btn_atacar.setDisable(false);
        btn_item.setDisable(false);
        btn_cura.setVisible(false);
        btn_forca.setVisible(false);

        lbl_logJogador.setText("Você dobrou o dano do ataque especial.");
        lbl_logJogador.setWrapText(true);
        atualizarJogo();

        if (((Player) jogo.getJogador()).getInventario().getNum_pocao_forca() <= 0) {
            btn_forca.setDisable(true);
        }
    }

    /**
     * Atualiza o estado do jogo após cada rodada. Inclui o ataque do Monstro e
     * verifica o fim do jogo. Atualiza as labels de vida e log, além de contar
     * as rodadas.
     */
    private void atualizarJogo() {
        lbl_logMonstro.setVisible(true);
        lbl_logMonstro.setText("O Monstro te atacou causando " + jogo.ataqueChefao() + " de dano");
        lbl_logMonstro.setWrapText(true);

        if (jogo.verificarFimDeJogo()) {
            lbl_vidaJogador.setText(Integer.toString(jogo.getJogador().getVida()));

            btn_atacar.setDisable(true);
            btn_item.setDisable(true);

            imgGameover.setVisible(true);

            if (jogo.getJogador() instanceof Cavaleiro) {
                imgCavaleiro.setVisible(false);
                imgCavaleiroMorto.setVisible(true);
            } else {
                imgMago.setVisible(false);
                imgMagoMorto.setVisible(true);
            }
            gravarRodada();

        } else {
            lbl_vidaJogador.setText(Integer.toString(jogo.getJogador().getVida()));
            lbl_vidaMonstro.setText(Integer.toString(jogo.getCHEFAO().getVida()));

            gravarRodada();
            contador_rodadas++;

            lbl_rodadas.setText(Integer.toString(contador_rodadas));
            lbl_cura.setText("Poção de Cura: " + ((Player) jogo.getJogador()).getInventario().getNum_pocao_vida());
            lbl_forca.setText("Poção de Força: " + ((Player) jogo.getJogador()).getInventario().getNum_pocao_forca());
            ((Player) jogo.getJogador()).setDelay_especial(((Player) jogo.getJogador()).getDelay_especial() - 1);

        }

    }

    /**
     * Exibe uma janela de ajuda com informações sobre o jogo. Explica as regras
     * básicas, como o sistema de turnos e o uso de poções.
     *
     * @param event O evento de ação disparado pelo botão.
     */
    @FXML
    private void botaoAjuda(ActionEvent event) {
        Alert ajuda = new Alert(AlertType.CONFIRMATION);
        ajuda.setTitle("Ajuda");
        ajuda.setHeaderText("Guia do Jogo RPG");
        ajuda.setContentText("Neste RPG por turnos, você deverá escolher uma classe para batalhar contra o Monstro.\n\n"
                + "1. Turnos: Você e o Monstro realizam ações alternadamente entre si. Você age primeiro e logo depois o Monstro.\n"
                + "2. Ataques: Escolha entre um ataque normal ou um ataque especial. Há um tempo de recarregamento "
                + "entre cada uso do ataque especial\n"
                + "3. Inventário: Cada classe possui um número limitado de poções. "
                + "Use as poções para curar ou aumentar a força de seu próximo ataque especial.\n"
                + "4. Objetivo: Derrote o Monstro sem deixar sua vida chegar a zero. Uma boa coordenação entre os usos de poções e ataques"
                + " é a chave da vitória.\n"
                + "5. Arquivo Log: Ao final do jogo é gerado um arquivo log.txt que registra os resultados de cada rodada, é necessário"
                + " apertar o botão EXIT para encerrar o jogo corretamente.\n\n"
                + "BOA SORTE!");

        ButtonType buttonTypeOK = new ButtonType("Ok");
        ajuda.getButtonTypes().setAll(buttonTypeOK);
        ajuda.showAndWait();

    }

    /**
     * Finaliza o jogo e fecha o programa. Grava os registros de log antes de
     * encerrar.
     *
     * @param event O evento de ação disparado pelo botão.
     */
    @FXML
    private void botaoSair(ActionEvent event) {
        ManipularArquivoTexto.fecharArquivoGravacao();
        System.exit(1);
    }

    /**
     * Retorna ao menu principal, escondendo as opções de ataque e itens.
     *
     * @param event O evento de ação disparado pelo botão.
     */
    @FXML
    private void botaoVoltar(ActionEvent event) {
        btn_atacar.setDisable(false);
        btn_item.setDisable(false);
        btn_cura.setVisible(false);
        btn_forca.setVisible(false);
        btn_ataqueNormal.setVisible(false);
        btn_ataqueEspecial.setVisible(false);
        btn_voltar.setVisible(false);
    }

    /**
     * Aplica uma animação de translação à imagem fornecida. A imagem se move de
     * acordo com o valor especificado em um loop infinito.
     *
     * @param img A imagem a ser animada.
     * @param value O valor pelo qual a imagem será transladada ao longo do eixo
     * X.
     */
    private void translateImg(ImageView img, double value) {
        TranslateTransition translateMonstro = new TranslateTransition();
        translateMonstro.setNode(img);
        translateMonstro.setDuration(Duration.millis(1000));
        translateMonstro.setCycleCount(TranslateTransition.INDEFINITE);
        translateMonstro.setByX(value);
        translateMonstro.setAutoReverse(true);
        translateMonstro.play();
    }

    /**
     * Grava os resultados da rodada atual em um arquivo de texto. Inclui a vida
     * do jogador e do chefe ao final da rodada.
     */
    private void gravarRodada() {
        ManipularArquivoTexto.gravarArquivo("RESULTADO DA RODADA " + (contador_rodadas));
        ManipularArquivoTexto.gravarArquivo("Vida do " + jogo.getJogador().getClass().getSimpleName() + ": " + jogo.getJogador().getVida());
        ManipularArquivoTexto.gravarArquivo("Vida do " + jogo.getCHEFAO().getClass().getSimpleName() + ": " + jogo.getCHEFAO().getVida() + "\n");
    }

}
