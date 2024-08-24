
public class Cavaleiro extends Player {

    private final int dano_normal;
    private final int dano_especial;

    public Cavaleiro() {
        super(1500);
        this.dano_normal = 100;
        this.dano_especial = 200;
        this.inventario = new Inventario(3, 3);
    }

    @Override
    public int ataqueNormal() {
        return getDano_normal();
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

    public int getDano_normal() {
        return dano_normal;
    }

    public int getDano_especial() {
        return dano_especial;
    }

}
