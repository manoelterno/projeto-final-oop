package Principal;

/**
 * Representa um personagem do tipo Mago no jogo. Esta classe é uma subclasse de
 * {@link Player} e define os ataques normais e especiais do Mago, bem como
 * verifica o estado de vida do personagem.
 */
public class Mago extends Player {

    private final int dano_normal;
    private final int dano_especial;

    /**
     * Cria uma nova instância de {@code Mago} com valores predefinidos para
     * vida, dano normal e dano especial. O Mago começa com 800 de vida, e seus
     * ataques causam 175 de dano normal e 350 de dano especial.
     */
    public Mago() {
        super(800, 4, 2);
        this.dano_normal = 175;
        this.dano_especial = 350;
    }

    /**
     * Retorna o dano causado pelo ataque normal do Mago.
     *
     * @return O dano do ataque normal.
     */
    @Override
    public int ataqueNormal() {
        return getDano_normal();
    }

    /**
     * Determina se o ataque especial do Mago pode ser realizado. O ataque
     * especial só pode ser usado se o delay especial estiver zerado. Após o
     * uso, o delay é definido para 2 turnos.
     *
     * @return {@code true} se o ataque especial puder ser realizado,
     * {@code false} caso contrário.
     */
    @Override
    public boolean ataqueEspecial() {
        if (this.delay_especial <= 0) {
            this.delay_especial = 2;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verifica se o Mago está vivo ou morto.
     *
     * @return {@code true} se o Mago estiver vivo (vida > 0), {@code false} se
     * estiver morto (vida <= 0).
     */
    @Override
    public boolean vivoOuMorto() {
        return getVida() > 0;
    }

    /**
     * Retorna o valor do dano normal causado pelo Mago.
     *
     * @return O dano normal do Mago.
     */
    public int getDano_normal() {
        return dano_normal;
    }

    /**
     * Retorna o valor do dano especial causado pelo Mago.
     *
     * @return O dano especial do Mago.
     */
    public int getDano_especial() {
        return dano_especial;
    }

}
