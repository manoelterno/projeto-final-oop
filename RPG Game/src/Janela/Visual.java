package janela;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

/**
 * Classe Visual é responsável por inicializar e exibir a interface gráfica da
 * aplicação. Extende a classe {@link javafx.application.Application} e carrega
 * o arquivo FXML para construir a interface gráfica.
 */
public class Visual extends Application {

    /**
     * O método start é o ponto de entrada para a aplicação JavaFX. Ele carrega
     * o arquivo FXML, configura a cena e exibe o estágio.
     *
     * @param stage O estágio principal da aplicação onde a interface será
     * exibida.
     * @throws Exception Se ocorrer algum erro durante o carregamento do FXML.
     */
    @Override
    public void start(Stage stage) throws Exception {

        // Carrega o arquivo FXML para construir a interface gráfica
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        // Cria uma nova cena a partir do FXML carregado
        Scene scene = new Scene(root);

        // Configura o estágio principal da aplicação
        stage.setScene(scene);
        stage.show();
    }

    /**
     * O método main é o ponto de entrada padrão de uma aplicação Java. Ele
     * chama o método {@link #launch(String...)} para iniciar a aplicação
     * JavaFX.
     *
     * @param args Argumentos da linha de comando.
     */
    public static void main(String[] args) {
        launch(args);
    }

}
