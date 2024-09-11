package Principal;

/**
 * A classe Cavaleiro representa um jogador do tipo Cavaleiro no jogo. Ela
 * estende a classe abstrata Player e implementa os métodos para ataques normais
 * e especiais, bem como a verificação de vida.
 */
public class Cavaleiro extends Player {

    /**
     * O dano causado por um ataque normal do Cavaleiro.
     */
    private final int dano_normal;

    /**
     * O dano causado por um ataque especial do Cavaleiro.
     */
    private final int dano_especial;

    /**
     * Construtor da classe Cavaleiro. Inicializa a vida, inventário, dano
     * normal, e dano especial do Cavaleiro.
     */
    public Cavaleiro() {
        super(1500, 2, 4);
        this.dano_normal = 100;
        this.dano_especial = 200;
    }

    /**
     * Realiza um ataque normal, retornando o dano padrão do Cavaleiro.
     *
     * @return O dano causado pelo ataque normal.
     */
    @Override
    public int ataqueNormal() {
        return getDano_normal();
    }

    /**
     * Realiza um ataque especial, caso o delay permita. Se o ataque for
     * bem-sucedido, o delay é reiniciado.
     *
     * @return true se o ataque especial foi realizado com sucesso, false se
     * ainda está em recarga.
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
     * Verifica se o Cavaleiro está vivo ou morto.
     *
     * @return true se o Cavaleiro está vivo, false se está morto.
     */
    @Override
    public boolean vivoOuMorto() {
        if (getVida() <= 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Retorna o dano causado por um ataque normal do Cavaleiro.
     *
     * @return O valor do dano normal.
     */
    public int getDano_normal() {
        return dano_normal;
    }

    /**
     * Retorna o dano causado por um ataque especial do Cavaleiro.
     *
     * @return O valor do dano especial.
     */
    public int getDano_especial() {
        return dano_especial;
    }
}
