
public abstract class Personagem {

    protected int vida; //vida atual

    public Personagem(int vida) {
        this.vida = vida;
    }

    public abstract boolean vivoOuMorto();
    public abstract int ataqueNormal();
    public abstract boolean ataqueEspecial();

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        if (vida < 0) {
            this.vida = 0;
        } else {
            this.vida = vida;
        }
    }
}
