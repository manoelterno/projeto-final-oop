package Principal;

/**
 * Representa o inventário de um jogador, contendo poções de vida e força. O
 * inventário mantém o controle sobre a quantidade de cada tipo de poção e pode
 * aplicar seus efeitos.
 */
public class Inventario {

    private Item pocao_vida;
    private Item pocao_forca;
    private int num_pocao_vida;
    private int num_pocao_forca;
    private boolean especial = false;

    /**
     * Cria uma nova instância de {@code Inventario} com valores padrão.
     * Inicialmente, o inventário não contém poções e a quantidade é zero.
     */
    public Inventario() {
    }

    /**
     * Cria uma nova instância de {@code Inventario} com poções e quantidades
     * especificadas.
     *
     * @param pocao_vida A poção de vida a ser adicionada ao inventário.
     * @param pocao_forca A poção de força a ser adicionada ao inventário.
     * @param num_pocao_vida A quantidade de poções de vida no inventário.
     * @param num_pocao_forca A quantidade de poções de força no inventário.
     */
    public Inventario(Item pocao_vida, Item pocao_forca, int num_pocao_vida, int num_pocao_forca) {
        this.pocao_vida = pocao_vida;
        this.pocao_forca = pocao_forca;
        this.num_pocao_vida = num_pocao_vida;
        this.num_pocao_forca = num_pocao_forca;
    }

    /**
     * Retorna a poção de vida no inventário.
     *
     * @return A poção de vida.
     */
    public Item getPocao_vida() {
        return pocao_vida;
    }

    /**
     * Define a poção de vida no inventário.
     *
     * @param pocao_vida A poção de vida a ser definida.
     */
    public void setPocao_vida(Item pocao_vida) {
        this.pocao_vida = pocao_vida;
    }

    /**
     * Retorna a poção de força no inventário.
     *
     * @return A poção de força.
     */
    public Item getPocao_forca() {
        return pocao_forca;
    }

    /**
     * Define a poção de força no inventário.
     *
     * @param pocao_forca A poção de força a ser definida.
     */
    public void setPocao_forca(Item pocao_forca) {
        this.pocao_forca = pocao_forca;
    }

    /**
     * Retorna o número de poções de vida no inventário.
     *
     * @return O número de poções de vida.
     */
    public int getNum_pocao_vida() {
        return num_pocao_vida;
    }

    /**
     * Define o número de poções de vida no inventário.
     *
     * @param num_pocao_vida O número de poções de vida a ser definido.
     */
    public void setNum_pocao_vida(int num_pocao_vida) {
        this.num_pocao_vida = num_pocao_vida;
    }

    /**
     * Retorna o número de poções de força no inventário.
     *
     * @return O número de poções de força.
     */
    public int getNum_pocao_forca() {
        return num_pocao_forca;
    }

    /**
     * Define o número de poções de força no inventário.
     *
     * @param num_pocao_forca O número de poções de força a ser definido.
     */
    public void setNum_pocao_forca(int num_pocao_forca) {
        this.num_pocao_forca = num_pocao_forca;
    }

    /**
     * Define se o inventário tem um status especial.
     *
     * @param especial O status especial a ser definido.
     */
    public void setEspecial(boolean especial) {
        this.especial = especial;
    }

    /**
     * Aplica o efeito da poção de vida ao valor atual de vida.
     *
     * @param vida_atual O valor atual de vida a ser aumentado.
     * @return O valor de vida aumentado após aplicar o efeito da poção.
     */
    public int usarVida(int vida_atual) {
        return pocao_vida.efeitoItem(vida_atual);
    }

    /**
     * Aplica o efeito da poção de força ao valor de dano.
     *
     * @param dano O valor de dano a ser aumentado.
     * @return O valor de dano aumentado após aplicar o efeito da poção.
     */
    public int usarForca(int dano) {
        return pocao_forca.efeitoItem(dano);
    }

    /**
     * Verifica se o inventário tem um status especial.
     *
     * @return {@code true} se o inventário tiver um status especial,
     * {@code false} caso contrário.
     */
    public boolean isEspecial() {
        return especial;
    }
}
