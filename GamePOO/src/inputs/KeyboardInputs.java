/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.GamePanel;

/**
 *
 * @author yanpa
 */
public class KeyboardInputs implements KeyListener{
    
    private GamePanel gamePanel;

    public KeyboardInputs(GamePanel panel) {
        this.gamePanel = panel;
    }
            
       

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()){
                case KeyEvent.VK_A:
                    gamePanel.changeDeltaX(-5);
                    break;
                
                case KeyEvent.VK_W:
                    gamePanel.changeDeltaY(-5);
                    break;
                
                case KeyEvent.VK_S:
                    gamePanel.changeDeltaY(+5);
                    break;
                
                    
                case KeyEvent.VK_D:
                    gamePanel.changeDeltaX(+5);
                    break;
                    
                case KeyEvent.VK_P:
                    gamePanel.changeFig(1);
                    break;
                
                case KeyEvent.VK_L:
                    gamePanel.changeFig(0);
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
