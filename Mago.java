
public class Mago extends Player {

    private final int dano_normal;
    private final int dano_especial;

    public Mago() {
        super(800);
        this.dano_normal = 175;
        this.dano_especial = 350;
        this.inventario = new Inventario(3, 3);
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

    public int getDano_especial() {
        return dano_especial;
    }

}
