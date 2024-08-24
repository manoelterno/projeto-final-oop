
import java.util.Random;

public class Monstro extends Boss {

    private final int dano_normal = 150;
    private int dano_especial = 300;
    private final Random random;

    public Monstro() {
        super(2000);
        random = new Random();
    }

    @Override
    public int ataqueNormal() {
        return dano_normal;
    }

    @Override
    public boolean ataqueEspecial() {
        int chance;

        chance = random.nextInt(100);

        if (chance < 80) {
            return false;
        } else {
            return true;
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

    public void setDano_especialL(int dano_especial) {
        this.dano_especial = dano_especial;
    }

}
