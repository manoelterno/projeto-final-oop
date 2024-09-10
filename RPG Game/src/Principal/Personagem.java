package Principal;

/**
 * A classe abstrata Personagem serve como a classe base para todos os
 * personagens do jogo, fornecendo a estrutura básica de atributos e métodos que
 * devem ser implementados pelas subclasses. Cada personagem possui uma
 * quantidade de vida e pode realizar ataques normais e especiais, além de
 * verificar se está vivo ou morto.
 */
public abstract class Personagem {

    /**
     * A quantidade de vida do personagem. É usada para determinar se o
     * personagem ainda está em condições de lutar.
     */
    protected int vida;

    /**
     * Construtor da classe Personagem. Inicializa a vida do personagem com o
     * valor fornecido.
     *
     * @param vida A quantidade inicial de vida do personagem.
     */
    public Personagem(int vida) {
        this.vida = vida;
    }

    /**
     * Método abstrato para verificar se o personagem está vivo ou morto. Deve
     * ser implementado pelas subclasses para retornar true se o personagem
     * estiver vivo, ou false se estiver morto.
     *
     * @return true se o personagem estiver vivo, false se estiver morto.
     */
    public abstract boolean vivoOuMorto();

    /**
     * Método abstrato para realizar um ataque normal. Deve ser implementado
     * pelas subclasses para retornar o dano causado pelo ataque normal.
     *
     * @return O dano causado pelo ataque normal.
     */
    public abstract int ataqueNormal();

    /**
     * Método abstrato para realizar um ataque especial. Deve ser implementado
     * pelas subclasses para executar a lógica do ataque especial e indicar se
     * ele foi realizado com sucesso.
     *
     * @return true se o ataque especial foi realizado, false se o ataque
     * especial está em recarga.
     */
    public abstract boolean ataqueEspecial();

    /**
     * Retorna a quantidade de vida atual do personagem.
     *
     * @return A quantidade de vida do personagem.
     */
    public int getVida() {
        return vida;
    }

    /**
     * Define a quantidade de vida do personagem. Se a quantidade fornecida for
     * menor que 0, a vida do personagem será ajustada para 0.
     *
     * @param vida A nova quantidade de vida do personagem.
     */
    public void setVida(int vida) {
        if (vida < 0) {
            this.vida = 0;
        } else {
            this.vida = vida;
        }
    }
}
