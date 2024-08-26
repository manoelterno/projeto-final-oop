/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import main.GamePanel;

/**
 *
 * @author yanpa
 */
public class MouseInputs implements MouseListener, MouseMotionListener{
    
    private GamePanel gamePainel;
    
    public MouseInputs(GamePanel panel){
        this.gamePainel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("Clicou!");
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.println("Mouse movido!");
        //gamePainel.setRecPos(e.getX(), e.getY());
    }
    
}
