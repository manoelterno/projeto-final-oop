
package main;

import java.awt.Dimension;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class GameWindow {
    
    private JFrame jframe;
    
    public GameWindow(GamePanel gamePanel){
        jframe = new JFrame();
        
                // remove the frame's border and title bar
        jframe.setUndecorated(false);
        // get the screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // set the frame's size to the screen size
        jframe.setBounds(0, 0, screenSize.width, screenSize.height);
        
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(false);
        jframe.pack(); // adapta o tamanho ao jpanel
        jframe.setVisible(true);
    }
    
}
