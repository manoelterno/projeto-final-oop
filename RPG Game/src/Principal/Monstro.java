package Principal;

import java.util.Random;

/**
 * Representa um monstro no jogo, que é uma extensão da classe {@link Boss}. O
 * monstro possui ataques normais e especiais e controla sua vida.
 */
public class Monstro extends Boss {

    /**
     * Objeto {@code Random} utilizado para gerar valores aleatórios no decorrer
     * do jogo, valores responsáveis por decidir se o Monstro irá usar um ataque
     * normal ou um ataque especial.
     */
    private final Random RANDOM;

    /**
     * O dano causado por um ataque especial do Monstro.
     */
    private final int DANO_ESPECIAL = 300;

    /**
     * O dano causado por um ataque normal do Monstro.
     */
    private final int DANO_NORMAL = 150;

    /**
     * Cria uma nova instância de {@code Monstro} com uma vida inicial de 2000.
     * Inicializa o gerador de números aleatórios para determinar o sucesso do
     * ataque especial.
     */
    public Monstro() {
        super(2000);
        RANDOM = new Random();
    }

    /**
     * Retorna o dano causado pelo ataque normal do monstro.
     *
     * @return O dano do ataque normal.
     */
    @Override
    public int ataqueNormal() {
        return getDANO_NORMAL();
    }

    /**
     * Determina se o ataque especial do monstro é bem-sucedido. O sucesso é
     * baseado em uma chance aleatória.
     *
     * @return {@code true} se o ataque especial for bem-sucedido, {@code false}
     * caso contrário.
     */
    @Override
    public boolean ataqueEspecial() {
        int chance = RANDOM.nextInt(100);

        return chance >= 80;
    }

    /**
     * Verifica se o monstro está vivo ou morto.
     *
     * @return {@code true} se o monstro estiver vivo (vida maior 0), {@code false}
     * se estiver morto (vida menor  ou que 0).
     */
    @Override
    public boolean vivoOuMorto() {
        return getVida() > 0;
    }

    /**
     * Retorna o valor do dano especial causado pelo monstro.
     *
     * @return O dano especial do monstro.
     */
    public int getDANO_ESPECIAL() {
        return DANO_ESPECIAL;
    }
    
    public int getDANO_NORMAL(){
        return DANO_NORMAL;
    }

}
