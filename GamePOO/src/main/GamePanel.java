package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    
    //private Random random;
    
    private MouseInputs mouseInput;
    private float  dx_hero, dy_hero;
    private float dx_villain = 740, dy_villain = 40;
    private BufferedImage img;
    private BufferedImage imgVillain;
    private BufferedImage[] idleAniVillain;
    private BufferedImage[] idleAni;
    private Image backgorund;
    private int aniTick, aniIndex, aniSpeed = 10;
    private int widthTickHero, heightTickHero;
    private int aniTickVillain, aniIndexVillain, aniSpeedVillain = 20;
    private  int tickFig = 0;
    
    /*private float xDir = 0.06f, yDir = 0.06f;
    private Color color = new Color(150,90,25);*/
    
    public GamePanel(){
        
        //random = new Random();
        
        mouseInput = new MouseInputs(this);
        
        this.initialize();
        
        importImg();
        importImgVillain();
        loadAnimations(0);
        loadAnimationsVillain();
        
        setPanelSize();
        
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);
    }
    
    public void changeFig(int fig){
        aniIndex = 0;
        aniTick = 0;
        this.tickFig = fig;
        importImg();
        loadAnimations(fig);
    }
    
    
        private void importImg(){
        if(tickFig == 0){
        InputStream is = getClass().getResourceAsStream("Idle_mago.png");
        widthTickHero = 825; heightTickHero = 825;
        dy_hero = -30;
        dx_hero = 160;
            try{
                img = ImageIO.read(is);
            }catch(IOException e){
                e.printStackTrace();
            } finally{
                    try{
                        is.close();
                    }catch(IOException e){
                        e.printStackTrace();
                }
            }
            
        }else{
            InputStream is = getClass().getResourceAsStream("Idle_cavaleiro.png");
            widthTickHero = 625; heightTickHero = 625;
            dy_hero = 160;
            dx_hero = 200;
            try{
                img = ImageIO.read(is);
            }catch(IOException e){
                e.printStackTrace();
            } finally{
                    try{
                        is.close();
                    }catch(IOException e){
                        e.printStackTrace();
                }
            }
            
            
        }
    }
    
    private void importImgVillain(){
        
            InputStream is = getClass().getResourceAsStream("necrotus.png");
            try{
                imgVillain = ImageIO.read(is);
            }catch(IOException e){
                e.printStackTrace();
            } finally{
                    try{
                        is.close();
                    }catch(IOException e){
                        e.printStackTrace();
                }
            }
            
    
        
    }
    
    private void setPanelSize(){
            Dimension size = new Dimension(1920,1080);
            setMinimumSize(size);
            setPreferredSize(size);
            setMaximumSize(size);
    }
    
    public void changeDeltaX(int value){
        dx_hero += value;
        repaint();
    }
    
    public void setRecPos(int x, int y){
        this.dx_hero = x;
        this.dy_hero = y;
    }
    
    public void changeDeltaY(int value){
        dy_hero += value;
    }
    
        protected void initialize() {
        this.backgorund = this.getImage("Battleground3.png");
        
        this.setLayout(new BorderLayout());
    }
    
    public Image getImage(String path) {
        URL imageURL = getClass().getResource(path);
        if (imageURL == null)
            return null;

        return new ImageIcon(imageURL).getImage();
    }
    
        private void loadAnimations(int fig) {
            if(fig == 0){
                idleAni = new BufferedImage[8];
                for(int i = 0; i < idleAni.length; i++){
                    idleAni[i] = img.getSubimage(i*128, 0, 128, 128);
                }
            }else{
                idleAni = new BufferedImage[4];
                for(int i = 0; i < idleAni.length; i++){
                    idleAni[i] = img.getSubimage(i*67, 0, 86, 86);
                }
            }
    }
        
        private void loadAnimationsVillain() {
                idleAniVillain = new BufferedImage[6];
                for(int i = 0; i < idleAniVillain.length; i++){
                    idleAniVillain[i] = imgVillain.getSubimage(i*128, 0, 128, 128);
                }
    }
        
     

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Image background = new ImageIcon(this.backgorund.getScaledInstance(1580,1080,1)).getImage();
        System.out.println("X:"+dx_hero+" Y:"+dy_hero);

        g.drawImage(background, 0, 0, this);

        updateAnimationTick();
        updateAnimationTickVillain();
        
        g.drawImage(idleAniVillain[aniIndexVillain], (int)dx_villain, (int)dy_villain, 756, 756, null);
        g.drawImage(idleAni[aniIndex], (int)dx_hero, (int)dy_hero, widthTickHero, heightTickHero, null);
     
        
                               
    }
    
    private void updateAnimationTickVillain() {
        aniTickVillain++;
        if(aniTickVillain >= aniSpeedVillain){
            aniTickVillain = 0;
            aniIndexVillain++;
            if(aniIndexVillain >= idleAniVillain.length){
                aniIndexVillain = 0;
            }
        }
    }

    private void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= idleAni.length){
                aniIndex = 0;
            }
        }
    }



    
    
}
