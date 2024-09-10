package Principal;

import java.util.Random;

/**
 * Representa um monstro no jogo, que é uma extensão da classe {@link Boss}. O
 * monstro possui ataques normais e especiais e controla sua vida.
 */
public class Monstro extends Boss {

    private final Random random;
    private int dano_especial = 300;
    private final int dano_normal = 150;

    /**
     * Cria uma nova instância de {@code Monstro} com uma vida inicial de 2000.
     * Inicializa o gerador de números aleatórios para determinar o sucesso do
     * ataque especial.
     */
    public Monstro() {
        super(2000);
        random = new Random();
    }

    /**
     * Retorna o dano causado pelo ataque normal do monstro.
     *
     * @return O dano do ataque normal.
     */
    @Override
    public int ataqueNormal() {
        return dano_normal;
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
        int chance = random.nextInt(100);

        return chance >= 80;
    }

    /**
     * Verifica se o monstro está vivo ou morto.
     *
     * @return {@code true} se o monstro estiver vivo (vida > 0), {@code false}
     * se estiver morto (vida <= 0).
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
    public int getDano_especial() {
        return dano_especial;
    }

    /**
     * Define o valor do dano especial causado pelo monstro.
     *
     * @param dano_especial O novo valor do dano especial.
     */
    public void setDano_especial(int dano_especial) {
        this.dano_especial = dano_especial;
    }
}
