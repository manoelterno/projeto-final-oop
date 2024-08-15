
public abstract class Boss extends Personagem {

    public Boss() {
        super();
    }

    public Boss(int vida) {
        super(vida);
    }

    @Override
    public abstract int ataqueNormal();

    @Override
    public abstract boolean ataqueEspecial();

    @Override
    public abstract boolean vivoOuMorto();
}
