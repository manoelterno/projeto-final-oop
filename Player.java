
public abstract class Player extends Personagem {

    protected Inventario inventario;
    protected int delay_especial = 0;
    protected final int vida_max;

    public Player(int vida) {
        super(vida);
        vida_max = vida;
    }

    @Override
    public abstract int ataqueNormal();

    @Override
    public abstract boolean ataqueEspecial();

    @Override
    public abstract boolean vivoOuMorto();

    public Inventario getInventario() {
        return inventario;
    }

    public int getDelay_especial() {
        return delay_especial;
    }

    public void setDelay_especial(int delay_especial) {
        this.delay_especial = delay_especial;
    }

    public void atualizarDelay() {
        if (this.delay_especial > 0) {
            this.delay_especial--;
        }
    }

    public int getVida_max() {
        return vida_max;
    }
}
