
public class Mago extends Player {

    private final int dano_normal = 175;
    private final int dano_especial = 350;
    private int delay_especial = 0;
    private Inventario inventario;
    
    public Mago() {
        super(800);
        this.inventario = new Inventario(3,3);
    }

    @Override
    public int ataqueNormal() {
        return dano_normal;
    }

    @Override
    public boolean ataqueEspecial() { // True = Pode usar e False = Não pode usar
        if (this.delay_especial == 0) {
            this.delay_especial = 2;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean vivoOuMorto() { // False = Morto e True = Vivo
        if (getVida() <= 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void curar() {

    }

    @Override
    public void dobrarDano() {

    }

    public void atualizarDelay() {
        if (this.delay_especial > 0) {
            this.delay_especial--;
        }
    }

    public int getDano_especial() {
        return dano_especial;
    }   

    public int getDelay_especial() {
        return delay_especial;
    }

    public void setDelay_especial(int delay_especial) {
        this.delay_especial = delay_especial;
    }

    public Inventario getInventario() {
        return inventario;
    }

    
}
