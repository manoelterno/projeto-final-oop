package Principal;

public abstract class Player extends Personagem {

    protected Item cura;
    protected Item forca;

    public Player() {
        super();
        cura = new PocaoDeCura();
        forca = new PocaoDeForca();
    }

    public Player(int vida) {
        super(vida);
    }

    @Override
    public abstract int ataqueNormal();

    @Override
    public abstract boolean ataqueEspecial();

    @Override
    public abstract boolean vivoOuMorto();

    public abstract void curar();

    public abstract void dobrarDano();

    public Item getCura() {
        return cura;
    }

    public void setCura(Item cura) {
        this.cura = cura;
    }

    public Item getForca() {
        return forca;
    }

    public void setForca(Item forca) {
        this.forca = forca;
    }

}
