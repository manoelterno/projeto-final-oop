
public class Inventario {
    private int num_pocao_vida;
    private final int valor_pocao_vida;
    private int num_pocao_forca;
    private boolean especial = false;

    public Inventario(int num_pocao_vida, int num_pocao_forca) {
        this.num_pocao_vida = num_pocao_vida;
        this.num_pocao_forca = num_pocao_forca;
        this.valor_pocao_vida = 300;
    }

    public int getNum_pocao_vida() {
        return num_pocao_vida;
    }

    public int getNum_pocao_forca() {
        return num_pocao_forca;
    }

    public void setNum_pocao_vida(int num_pocao_vida) {
        this.num_pocao_vida = num_pocao_vida;
    }

    public void setNum_pocao_forca(int num_pocao_forca) {
        this.num_pocao_forca = num_pocao_forca;
    }
    
    public void usarVida(){
        this.num_pocao_vida--;
        this.setNum_pocao_vida(this.num_pocao_vida);
    }
    
    public void usarForca(){
        this.num_pocao_forca--;
        this.setNum_pocao_forca(this.num_pocao_forca);
        this.especial = true;
    }

    public void setEspecial(boolean especial) {
        this.especial = especial;
    }

    public boolean isEspecial() {
        return especial;
    }

    public int getValor_pocao_vida() {
        return valor_pocao_vida;
    }
    
    
}
