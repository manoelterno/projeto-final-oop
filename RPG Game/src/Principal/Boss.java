package Principal;

/**
 * Classe abstrata que representa um boss no jogo. Esta classe é uma extensão da
 * classe {@link Personagem} e define os métodos abstratos para ataques normais
 * e especiais, bem como para verificar se o boss está vivo ou morto.
 */
public abstract class Boss extends Personagem {

    /**
     * Cria uma nova instância de {@code Boss} com a quantidade de vida
     * especificada.
     *
     * @param vida A quantidade inicial de vida do boss.
     */
    public Boss(int vida) {
        super(vida);
    }

    /**
     * Retorna o dano causado pelo ataque normal do boss. Este método deve ser
     * implementado pelas subclasses para definir o comportamento do ataque
     * normal.
     *
     * @return O dano do ataque normal.
     */
    @Override
    public abstract int ataqueNormal();

    /**
     * Determina se o ataque especial do boss é bem-sucedido. Este método deve
     * ser implementado pelas subclasses para definir o comportamento do ataque
     * especial.
     *
     * @return {@code true} se o ataque especial for bem-sucedido, {@code false}
     * caso contrário.
     */
    @Override
    public abstract boolean ataqueEspecial();

    /**
     * Verifica se o boss está vivo ou morto. Este método deve ser implementado
     * pelas subclasses para definir a lógica de verificação de vida.
     *
     * @return {@code true} se o boss estiver vivo (vida maior que 0), {@code false} se
     * estiver morto (vida menor ou igual a 0).
     */
    @Override
    public abstract boolean vivoOuMorto();
}
