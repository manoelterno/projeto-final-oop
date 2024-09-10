package Principal;

/**
 * A classe abstrata Player representa um personagem jogável no jogo, estendendo
 * a classe base Personagem. Ela inclui funcionalidades adicionais como
 * gerenciamento de inventário, controle de delay para ataques especiais e
 * manutenção da vida máxima.
 */
public abstract class Player extends Personagem {

    /**
     * O inventário do jogador, contendo poções de cura e força.
     */
    protected Inventario inventario;

    /**
     * O atraso (delay) entre o uso de ataques especiais. Quando o valor é maior
     * que 0, o jogador não pode usar o ataque especial.
     */
    protected int delay_especial = 0;

    /**
     * A quantidade máxima de vida que o jogador pode ter. É definida no momento
     * da criação do jogador.
     */
    protected final int vida_max;

    /**
     * Construtor da classe Player. Inicializa a vida, o inventário e a vida
     * máxima do jogador.
     *
     * @param vida A quantidade inicial de vida do jogador.
     * @param num_pocao_vida O número inicial de poções de cura no inventário.
     * @param num_pocao_forca O número inicial de poções de força no inventário.
     */
    public Player(int vida, int num_pocao_vida, int num_pocao_forca) {
        super(vida);
        this.inventario = new Inventario(new PocaoDeCura(), new PocaoDeForca(), num_pocao_vida, num_pocao_forca);
        this.vida_max = vida;
    }

    /**
     * Realiza um ataque normal, causando dano ao oponente. Este método deve ser
     * implementado pelas subclasses para definir o dano específico do ataque
     * normal.
     *
     * @return O dano causado pelo ataque normal.
     */
    @Override
    public abstract int ataqueNormal();

    /**
     * Realiza um ataque especial, se disponível. Este método deve ser
     * implementado pelas subclasses para definir a lógica específica do ataque
     * especial.
     *
     * @return true se o ataque especial foi realizado com sucesso, false se o
     * ataque especial está em recarga.
     */
    @Override
    public abstract boolean ataqueEspecial();

    /**
     * Verifica se o jogador está vivo ou morto. Este método deve ser
     * implementado pelas subclasses para retornar true se o jogador estiver
     * vivo, ou false se estiver morto.
     *
     * @return true se o jogador estiver vivo, false se estiver morto.
     */
    @Override
    public abstract boolean vivoOuMorto();

    /**
     * Retorna o inventário do jogador.
     *
     * @return O inventário do jogador.
     */
    public Inventario getInventario() {
        return inventario;
    }

    /**
     * Retorna o delay atual do ataque especial do jogador.
     *
     * @return O valor atual do delay para o ataque especial.
     */
    public int getDelay_especial() {
        return delay_especial;
    }

    /**
     * Define o delay para o ataque especial do jogador.
     *
     * @param delay_especial O novo valor de delay para o ataque especial.
     */
    public void setDelay_especial(int delay_especial) {
        this.delay_especial = delay_especial;
    }

    /**
     * Retorna a vida máxima que o jogador pode ter.
     *
     * @return A quantidade máxima de vida do jogador.
     */
    public int getVida_max() {
        return vida_max;
    }

    /**
     * Atualiza o delay do ataque especial, diminuindo seu valor em 1 se for
     * maior que 0. Esse método deve ser chamado no final de cada rodada para
     * reduzir o delay do ataque especial.
     */
    public void atualizarDelay() {
        if (this.delay_especial > 0) {
            this.delay_especial--;
        }
    }
}
