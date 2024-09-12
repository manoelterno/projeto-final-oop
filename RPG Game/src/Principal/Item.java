package Principal;

/**
 * Classe abstrata que representa um item no jogo. Esta classe serve como base
 * para diferentes tipos de itens, como poções de cura e poções de força. Os
 * itens têm valores específicos que afetam a vida ou a força quando usados.
 */
public abstract class Item {

    /**
     * O valor de restauração de vida que uma poção de cura fornece ao jogador.
     * Usado para aumentar a saúde do jogador ao usar uma poção de vida.
     */
    private final int VALOR_POCAO_VIDA;

    /**
     * O valor de aumento de força que uma poção de força concede ao jogador.
     * Usado para dobrar o dano do próximo ataque especial ao consumir uma poção
     * de força.
     */
    private final int VALOR_POCAO_FORCA;

    /**
     * Cria uma nova instância de {@code Item} com valores predefinidos para
     * poções de vida e força. O valor padrão da poção de vida é 300 e o valor
     * padrão da poção de força é 2.
     */
    public Item() {
        this.VALOR_POCAO_VIDA = 300;
        this.VALOR_POCAO_FORCA = 2;
    }

    /**
     * Aplica o efeito do item. Este método deve ser implementado pelas
     * subclasses para definir o comportamento específico do item.
     *
     * @param valor O valor base que será modificado pelo efeito do item.
     * @return O valor modificado após aplicar o efeito do item.
     */
    public abstract int efeitoItem(int valor);

    /**
     * Retorna o valor associado à poção de vida.
     *
     * @return O valor da poção de vida.
     */
    public int getVALOR_POCAO_VIDA() {
        return VALOR_POCAO_VIDA;
    }

    /**
     * Retorna o valor associado à poção de força.
     *
     * @return O valor da poção de força.
     */
    public int getVALOR_POCAO_FORCA() {
        return VALOR_POCAO_FORCA;
    }
}
