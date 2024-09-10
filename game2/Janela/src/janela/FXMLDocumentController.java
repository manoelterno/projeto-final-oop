/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package janela;

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

/**
 *
 * @author yanpa
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label lbl_vidaMonstro;

    @FXML
    private Label lbl_log;

    @FXML
    private ImageView HOMER1;

    @FXML
    private Button btn_2;
    
    @FXML
    private Button btn_4;

    @FXML
    private Label lbl_rodadas;

    @FXML
    private Button btn_1;

    @FXML
    private ImageView HOMER;

    @FXML
    private Button btn_ajuda;
    
    @FXML
    private Button btn_3;

    @FXML
    private Label lbl_vidaJogador;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciar_jogo();
        
    }

    @FXML
    private void btn_ajudaAction(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "Mensagem de ajuda a ser feita");

    }
    
    
    @FXML
     private void btn_1Action(ActionEvent event) {
        btn_1.setVisible(false);
        btn_2.setVisible(false);
        //instanciar jogador como CAVALHEIRO
        btn_3.setVisible(true);
        btn_4.setVisible(true);

    }
     
     @FXML
     private void btn_2Action(ActionEvent event) {
        btn_1.setVisible(false);
        btn_2.setVisible(false);
        //instanciar jogador como MAGO
        btn_3.setVisible(true);
        btn_4.setVisible(true);

    }

    private void iniciar_jogo() {
        btn_1.setText("CAVALHEIRO");
        btn_2.setText("MAGO");
        lbl_log.setText("Selecione sua classe");
        btn_3.setVisible(false);
        btn_4.setVisible(false);
        
    }

}
