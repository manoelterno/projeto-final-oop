// FEITO arquivo (feito no jogo.java)
// FEITO visual
// FALTA IMPORTAR arrumar o jogo.java

// botao ajuda -> explique o jogo 
// -> explique a gravação do arquiv : "resultado do jogo gravado no arquivo log.txt"

//estilizar botao voltar
// mudar o tamanho da janela pra tirar a linha branca que tem embaixo
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

public class FXMLDocumentController implements Initializable {

    @FXML
    private Button btn_sair;

    @FXML
    private Label lbl_vidaMonstro;

    @FXML
    private Label lbl_logJogador;

    @FXML
    private Label lbl_logMonstro;

    @FXML
    private Rectangle retangulo1;

    @FXML
    private Rectangle retangulo2;

    @FXML
    private Label lbl_cura;

    @FXML
    private Label lbl_forca;

    @FXML
    private Button btn_mago;

    @FXML
    private Label lbl_rodadas;

    @FXML
    private Button btn_cavaleiro;

    @FXML
    private Button btn_ajuda;

    @FXML
    private Button btn_ataqueNormal;

    @FXML
    private Button btn_ataqueEspecial;

    // atacar
    @FXML
    private Button btn_atacar;

    // usar item
    @FXML
    private Button btn_item;

    @FXML
    private Button btn_forca;

    @FXML
    private Button btn_cura;

    @FXML
    private Button btn_voltar;

    @FXML
    private Label lbl_vidaJogador;
    
    @FXML
    private ImageView imgPocaoForca;
    
    @FXML
    private ImageView imgPocaoCura;
    
    @FXML
    private Rectangle recPocoes;
    
     @FXML
    private ImageView imgMonstro;
     
    @FXML
    private ImageView imgMago;
      
    @FXML
    private ImageView imgCavaleiro;

     @FXML
    private ImageView imgMonstroMorto;
     
    @FXML
    private ImageView imgMagoMorto;
      
    @FXML
    private ImageView imgCavaleiroMorto;  

    private Jogo jogo;
    private int contador_rodadas = 1;

    public void initialize(URL url, ResourceBundle rb) {
      
        iniciar_jogo();
        jogo = new Jogo();

        lbl_logJogador.setPrefWidth(365);
        lbl_logMonstro.setPrefWidth(365);
        
        translateImg(imgCavaleiro, 50);
        translateImg(imgMonstro, -50);
        translateImg(imgMago, 50);

    }
    
    private void translateImg(ImageView img, double value){
        TranslateTransition translateMonstro = new TranslateTransition();
        translateMonstro.setNode(img);
        translateMonstro.setDuration(Duration.millis(1000));
        translateMonstro.setCycleCount(TranslateTransition.INDEFINITE);
        translateMonstro.setByX(value);
        translateMonstro.setAutoReverse(true);
        translateMonstro.play();
    }

    @FXML
    private void botaoAjuda(ActionEvent event) {
        Alert ajuda = new Alert(AlertType.CONFIRMATION);
        ajuda.setTitle("Ajuda");
        ajuda.setHeaderText("Bem Vindo ao reino de Drakovia");
        ajuda.setContentText("Neste RPG por turnos, você joga como um Cavaleiro ou Mago enfrentando um monstro.\n"
                + "Turnos: Você e o monstro atacam alternadamente.\n"
                + "Ataques: Escolha entre um ataque normal ou especial (mais forte, mas com possui uma recarga).\n"
                + "Inventário: Use poções para curar ou aumentar a força de seu próximo ataque especial.\n"
                + "Objetivo: Derrote o monstro antes de perder toda sua vida!\n\nBoa sorte!");
        ButtonType buttonTypeOK = new ButtonType("Ok");

        ajuda.getButtonTypes().setAll(buttonTypeOK);
        ajuda.showAndWait();

    }

    @FXML
    private void botaoCavaleiro(ActionEvent event) {
        lbl_logJogador.setText("");
        btn_cavaleiro.setVisible(false);
        btn_mago.setVisible(false);
        jogo.instanciarCavaleiro();
        btn_item.setVisible(true);
        btn_atacar.setVisible(true);
        lbl_vidaJogador.setVisible(true);
        lbl_vidaMonstro.setVisible(true);
        lbl_vidaJogador.setText(Integer.toString(jogo.getJogador().getVida()));
        lbl_vidaMonstro.setText(Integer.toString(jogo.getChefao().getVida()));
        retangulo2.setVisible(true);
        lbl_cura.setVisible(true);
        lbl_forca.setVisible(true);
        imgPocaoCura.setVisible(true);
        imgPocaoForca.setVisible(true);
        recPocoes.setVisible(true);
        imgMonstro.setVisible(true);
        imgCavaleiro.setVisible(true);
        lbl_cura.setText("Poção de Cura: " + jogo.getJogador().getInventario().getNum_pocao_vida());
        lbl_forca.setText("Poção de Força: " + jogo.getJogador().getInventario().getNum_pocao_forca());
        
        ManipularArquivoTexto.gravarArquivo("INICIO DA BATALHA");
        ManipularArquivoTexto.gravarArquivo("Vida do " + jogo.getJogador().getClass().getSimpleName() + ": " + jogo.getJogador().getVida());
        ManipularArquivoTexto.gravarArquivo("Vida do " + jogo.getChefao().getClass().getSimpleName() + ": " + jogo.getChefao().getVida() + "\n");

    }

    @FXML
    private void botaoMago(ActionEvent event) {
        //atualizar imagem personagem

        lbl_logJogador.setText("");
        btn_cavaleiro.setVisible(false);
        btn_mago.setVisible(false);
        jogo.instanciarMago();
        btn_item.setVisible(true);
        btn_atacar.setVisible(true);
        lbl_vidaJogador.setVisible(true);
        lbl_vidaMonstro.setVisible(true);
        lbl_vidaJogador.setText(Integer.toString(jogo.getJogador().getVida()));
        lbl_vidaMonstro.setText(Integer.toString(jogo.getChefao().getVida()));
        retangulo2.setVisible(true);
        lbl_cura.setVisible(true);
        lbl_forca.setVisible(true);
        imgPocaoCura.setVisible(true);
        imgPocaoForca.setVisible(true);
        recPocoes.setVisible(true);
        imgMonstro.setVisible(true);
        imgMago.setVisible(true);
        lbl_cura.setText("Poção de Cura: " + jogo.getJogador().getInventario().getNum_pocao_vida());
        lbl_forca.setText("Poção de Força: " + jogo.getJogador().getInventario().getNum_pocao_forca());
        
        ManipularArquivoTexto.gravarArquivo("INICIO DA BATALHA");
        ManipularArquivoTexto.gravarArquivo("Vida do " + jogo.getJogador().getClass().getSimpleName() + ": " + jogo.getJogador().getVida());
        ManipularArquivoTexto.gravarArquivo("Vida do " + jogo.getChefao().getClass().getSimpleName() + ": " + jogo.getChefao().getVida() + "\n");
    }

    @FXML
    private void botaoAtacar(ActionEvent event) {
        btn_atacar.setDisable(true);
        btn_item.setDisable(true);
        btn_ataqueNormal.setVisible(true);
        btn_voltar.setVisible(true);
        btn_ataqueEspecial.setVisible(true);
        if (jogo.getJogador().getDelay_especial() > 0) {
            btn_ataqueEspecial.setDisable(true);
        } else {
            btn_ataqueEspecial.setDisable(false);
        }

    }

    @FXML
    private void botaoUsarItem(ActionEvent event) {
        btn_atacar.setDisable(true);
        btn_item.setDisable(true);
        btn_cura.setVisible(true);
        btn_forca.setVisible(true);
        btn_voltar.setVisible(true);

    }

    @FXML
    private void botaoAtaqueNormal(ActionEvent event) {
        //metodo ataquenormal
        btn_voltar.setVisible(false);
        btn_ataqueNormal.setVisible(false);
        btn_ataqueEspecial.setVisible(false);
        btn_atacar.setDisable(false);
        btn_item.setDisable(false);
        if (jogo.getJogador() instanceof Cavaleiro) {
            jogo.ataqueCavaleiro(1);
            lbl_logJogador.setText("Você causou " + ((Cavaleiro) jogo.getJogador()).getDano_normal() + " de dano");
        } else if (jogo.getJogador() instanceof Mago) {
            jogo.ataqueMago(1);
            lbl_logJogador.setText("Você causou " + ((Mago) jogo.getJogador()).getDano_normal() + " de dano");
        }

        if (jogo.verificarFimDeJogo()) {
            lbl_vidaMonstro.setText(Integer.toString(jogo.getChefao().getVida()));
            btn_atacar.setDisable(true);
            btn_item.setDisable(true);
            JOptionPane.showMessageDialog(null, "Voce venceu!");
            imgMonstro.setVisible(false);
            imgMonstroMorto.setVisible(true);
            gravarRodada();
        } else {

            atualizarJogo();
        }

    }

    @FXML
    private void botaoAtaqueEspecial(ActionEvent event) {
        btn_voltar.setVisible(false);
        btn_atacar.setDisable(false);
        btn_item.setDisable(false);
        btn_ataqueNormal.setVisible(false);
        btn_ataqueEspecial.setVisible(false);
        if (jogo.getJogador() instanceof Cavaleiro) {
            
            if (jogo.getJogador().getInventario().isEspecial()){
                lbl_logJogador.setText("Você causou " + ((Cavaleiro) jogo.getJogador()).getDano_especial()*2 + " de dano");
                lbl_logJogador.setWrapText(true);
            }
            else{
                lbl_logJogador.setText("Você causou " + ((Cavaleiro) jogo.getJogador()).getDano_especial() + " de dano");
                lbl_logJogador.setWrapText(true);
            }
            jogo.ataqueCavaleiro(2);
        } else if (jogo.getJogador() instanceof Mago) {
            
             if (jogo.getJogador().getInventario().isEspecial()){
                lbl_logJogador.setText("Você causou " + ((Mago) jogo.getJogador()).getDano_especial()*2 + " de dano");
                lbl_logJogador.setWrapText(true);
                 System.out.println("Você causou " + ((Mago) jogo.getJogador()).getDano_especial()*2 + " de dano");
            }
            else{
                lbl_logJogador.setText("Você causou " + ((Mago) jogo.getJogador()).getDano_especial() + " de dano");
                lbl_logJogador.setWrapText(true);
            }
             jogo.ataqueMago(2);
        }

        if (jogo.verificarFimDeJogo()) {
            lbl_vidaMonstro.setText(Integer.toString(jogo.getChefao().getVida()));
            btn_atacar.setDisable(true);
            btn_item.setDisable(true);
            JOptionPane.showMessageDialog(null, "Voce venceu!");
            imgMonstro.setVisible(false);
            imgMonstroMorto.setVisible(true);
            gravarRodada();
        } else {
            atualizarJogo();
        }
    }

    @FXML
    private void botaoCura(ActionEvent event) {
        btn_voltar.setVisible(false);
        btn_atacar.setDisable(false);
        btn_item.setDisable(false);
        btn_cura.setVisible(false);
        btn_forca.setVisible(false);

        jogo.pocaoCura();

        lbl_logJogador.setText("Você curou 300 de vida");

        atualizarJogo();
        
        if(jogo.getJogador().getInventario().getNum_pocao_vida() <= 0)
        {
            btn_cura.setDisable(true);
        }

    }

    @FXML
    private void botaoSair(ActionEvent event) {
        ManipularArquivoTexto.fecharArquivoGravacao();
        System.exit(1);
    }
    
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
    
    @FXML
    private void botaoForca(ActionEvent event) {
        
        btn_voltar.setVisible(false);
        btn_atacar.setDisable(false);
        btn_item.setDisable(false);
        btn_cura.setVisible(false);
        btn_forca.setVisible(false);

        jogo.pocaoForca();
        lbl_logJogador.setText("Você dobrou o dano do ataque especial.");
        lbl_logJogador.setWrapText(true);
        atualizarJogo();
        
        if(jogo.getJogador().getInventario().getNum_pocao_forca() <= 0)
        {
            btn_forca.setDisable(true);
        }
    }

    private void iniciar_jogo() {
        btn_cavaleiro.setText("CAVALEIRO");
        btn_mago.setText("MAGO");
        lbl_logJogador.setText("Selecione sua classe");
        btn_item.setVisible(false);
        btn_atacar.setVisible(false);
        btn_ataqueEspecial.setVisible(false);
        btn_ataqueNormal.setVisible(false);
        btn_cura.setVisible(false);
        btn_forca.setVisible(false);
        lbl_vidaJogador.setVisible(false);
        lbl_vidaMonstro.setVisible(false);
        lbl_rodadas.setText(Integer.toString(contador_rodadas));
        retangulo2.setVisible(false);
        lbl_cura.setVisible(false);
        lbl_forca.setVisible(false);
        lbl_logMonstro.setVisible(false);
        imgPocaoCura.setVisible(false);
        imgPocaoForca.setVisible(false);
        recPocoes.setVisible(false);
        btn_voltar.setVisible(false);
         ManipularArquivoTexto.abrirArquivoGravacao("log.txt");
    }

    private void atualizarJogo() {
        int dano = jogo.ataqueChefao();
        lbl_logMonstro.setVisible(true);
        lbl_logMonstro.setText("O Monstro te atacou causando " + dano + " de dano");
        lbl_logMonstro.setWrapText(true);
        if (jogo.verificarFimDeJogo()) {
            lbl_vidaJogador.setText(Integer.toString(jogo.getJogador().getVida()));
            btn_atacar.setDisable(true);
            btn_item.setDisable(true);
            JOptionPane.showMessageDialog(null, "Voce perdeu!");
            if(jogo.getJogador() instanceof  Cavaleiro){
                imgCavaleiro.setVisible(false);
                imgCavaleiroMorto.setVisible(true);
            }else{
                imgMago.setVisible(false);
                imgMagoMorto.setVisible(true);
            }
            gravarRodada();
            
            
        } else {

            lbl_vidaJogador.setText(Integer.toString(jogo.getJogador().getVida()));
            lbl_vidaMonstro.setText(Integer.toString(jogo.getChefao().getVida()));
            gravarRodada();
            contador_rodadas++;
            lbl_rodadas.setText(Integer.toString(contador_rodadas));
            jogo.getJogador().setDelay_especial(jogo.getJogador().getDelay_especial() - 1);
            lbl_cura.setText("Poção de Cura: " + jogo.getJogador().getInventario().getNum_pocao_vida());
            lbl_forca.setText("Poção de Força: " + jogo.getJogador().getInventario().getNum_pocao_forca());
        }
        
        
    }
    
        private void gravarRodada(){
            ManipularArquivoTexto.gravarArquivo("RESULTADO DA RODADA " + (contador_rodadas));
            ManipularArquivoTexto.gravarArquivo("Vida do " + jogo.getJogador().getClass().getSimpleName() + ": " + jogo.getJogador().getVida());
            ManipularArquivoTexto.gravarArquivo("Vida do " + jogo.getChefao().getClass().getSimpleName() + ": " + jogo.getChefao().getVida() + "\n");
        }

}
