// contador de pocoes
// botao de saida ao fim de jogo
// arquivo (feito no jogo.java)
// visual
// texto de log 
// arrumar o jogo.java


package janela;

import Principal.Jogo;
import Principal.Cavaleiro;
import Principal.Mago;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;

public class FXMLDocumentController implements Initializable {

    @FXML
    private Label lbl_vidaMonstro;

    @FXML
    private Label lbl_log;

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
    private Label lbl_vidaJogador;

    private Jogo jogo;
    private int contador_rodadas = 1;

    public void initialize(URL url, ResourceBundle rb) {
        iniciar_jogo();
        jogo = new Jogo();

    }

    @FXML
    private void btn_ajudaAction(ActionEvent event) {
        Alert ajuda = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Ajuda");
        alert.setHeaderText("Bem Vindo a Drakovia herói");
        alert.setContentText("Neste RPG por turnos, você joga como um Cavaleiro ou Mago enfrentando um monstro.\n\
            Turnos: Você e o monstro atacam alternadamente.\n\
            Ataques: Escolha entre um ataque normal ou especial (mais forte, mas com possui uma recarga).\n\
            Inventário: Use poções para curar ou aumentar a força de seu próximo ataque especial.\n\
            Objetivo: Derrote o monstro antes de perder toda sua vida!\n\
            Boa sorte!");
        
        ButtonType buttonTypeOK = new ButtonType("Ok");
        //ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        
        ajuda.getButtonTypes().setAll(buttonTypeOK);

    }

    @FXML
    private void botaoCavaleiro(ActionEvent event) {
        btn_cavaleiro.setVisible(false);
        btn_mago.setVisible(false);
        jogo.instanciarCavaleiro();
        btn_item.setVisible(true);
        btn_atacar.setVisible(true);
        lbl_vidaJogador.setVisible(true);
        lbl_vidaMonstro.setVisible(true);
        lbl_vidaJogador.setText(Integer.toString(jogo.getJogador().getVida()));
        lbl_vidaMonstro.setText(Integer.toString(jogo.getChefao().getVida()));

    }

    @FXML
    private void botaoMago(ActionEvent event) {
        //atualizar imagem personagem

        btn_cavaleiro.setVisible(false);
        btn_mago.setVisible(false);
        jogo.instanciarMago();
        btn_item.setVisible(true);
        btn_atacar.setVisible(true);
        lbl_vidaJogador.setVisible(true);
        lbl_vidaMonstro.setVisible(true);
        lbl_vidaJogador.setText(Integer.toString(jogo.getJogador().getVida()));
        lbl_vidaMonstro.setText(Integer.toString(jogo.getChefao().getVida()));
    }

    @FXML
    private void botaoAtacar(ActionEvent event) {
        btn_atacar.setDisable(true);
        btn_item.setDisable(true);
        btn_ataqueNormal.setVisible(true);
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

    }

    @FXML
    private void botaoAtaqueNormal(ActionEvent event) {
        //metodo ataquenormal
        
        btn_ataqueNormal.setVisible(false);
        btn_ataqueEspecial.setVisible(false);
        btn_atacar.setDisable(false);
         btn_item.setDisable(false);
        if (jogo.getJogador() instanceof Cavaleiro) {
            jogo.ataqueCavaleiro(1);
        } else if (jogo.getJogador() instanceof Mago) {
            jogo.ataqueMago(1);
        }

         if (jogo.verificarFimDeJogo()) {
            lbl_vidaMonstro.setText(Integer.toString(jogo.getChefao().getVida()));
            btn_atacar.setDisable(true);
            btn_item.setDisable(true);
            JOptionPane.showMessageDialog(null, "Voce venceu!");
        } else {
             
            atualizarJogo();
        }

    }

    @FXML
    private void botaoAtaqueEspecial(ActionEvent event) {
        //metodo ataqueespecial
        btn_atacar.setDisable(false);
        btn_item.setDisable(false);
        btn_ataqueNormal.setVisible(false);
        btn_ataqueEspecial.setVisible(false);
        if (jogo.getJogador() instanceof Cavaleiro) {
            jogo.ataqueCavaleiro(2);
        } else if (jogo.getJogador() instanceof Mago) {
            jogo.ataqueMago(2);
        }

        if (jogo.verificarFimDeJogo()) {
            lbl_vidaMonstro.setText(Integer.toString(jogo.getChefao().getVida()));
            btn_atacar.setDisable(true);
            btn_item.setDisable(true);
            JOptionPane.showMessageDialog(null, "Voce venceu!");
        } else {
            atualizarJogo();
        }
    }

    @FXML
    private void botaoCura(ActionEvent event) {
        //metodo pocaocura
        btn_atacar.setDisable(false);
        btn_item.setDisable(false);
        btn_cura.setVisible(false);
        btn_forca.setVisible(false);

        jogo.pocaoCura();
        atualizarJogo();

    }

    @FXML
    private void botaoForca(ActionEvent event) {
        //metodo pocaoforca
        btn_atacar.setDisable(false);
        btn_item.setDisable(false);
        btn_cura.setVisible(false);
        btn_forca.setVisible(false);

        jogo.pocaoForca();
        atualizarJogo();
    }

    private void iniciar_jogo() {
        btn_cavaleiro.setText("CAVALEIRO");
        btn_mago.setText("MAGO");
        lbl_log.setText("Selecione sua classe");
        btn_item.setVisible(false);
        btn_atacar.setVisible(false);
        btn_ataqueEspecial.setVisible(false);
        btn_ataqueNormal.setVisible(false);
        btn_cura.setVisible(false);
        btn_forca.setVisible(false);
        lbl_vidaJogador.setVisible(false);
        lbl_vidaMonstro.setVisible(false);
        lbl_rodadas.setText(Integer.toString(contador_rodadas));

    }

    private void atualizarJogo() {
        jogo.ataqueChefao();
        if (jogo.verificarFimDeJogo()) {
            lbl_vidaJogador.setText(Integer.toString(jogo.getJogador().getVida()));
            btn_atacar.setDisable(true);
            btn_item.setDisable(true);
            JOptionPane.showMessageDialog(null, "Voce perdeu!");
        } else {
            lbl_vidaJogador.setText(Integer.toString(jogo.getJogador().getVida()));
            lbl_vidaMonstro.setText(Integer.toString(jogo.getChefao().getVida()));
            contador_rodadas++;
            lbl_rodadas.setText(Integer.toString(contador_rodadas));
            jogo.getJogador().setDelay_especial(jogo.getJogador().getDelay_especial() - 1);
        }
    }

}
