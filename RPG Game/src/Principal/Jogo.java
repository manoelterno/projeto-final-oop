package Principal;

/**
 * A classe {@code Jogo} é responsável por gerenciar a lógica principal do jogo
 * de RPG, coordenando as interações entre o jogador e o chefão (Monstro). Ela
 * controla o fluxo do jogo, incluindo a escolha de classes, turnos, ataques,
 * uso de itens e verificação das condições de vitória ou derrota.
 *
 * <p>
 * O jogo envolve dois tipos de personagens jogáveis: Cavaleiro e Mago, cada um
 * com seus próprios ataques normais e especiais. O jogo ocorre em turnos
 * alternados entre o jogador e o chefão.</p>
 *
 */
public class Jogo {

    /**
     * Objeto {@code Personagem} que representa o chefão (Monstro) da partida. É
     * a entidade controlada pelo jogo com a qual o jogador deve lutar.
     */
    private final Personagem CHEFAO;

    /**
     * Objeto {@code Personagem} que representa o jogador controlado pelo
     * usuário. Pode ser instanciado como diferentes classes, como
     * {@code Cavaleiro} ou {@code Mago}.
     */
    private Personagem jogador;

    /**
     * Construtor que inicializa o jogo, criando o chefão (Monstro)
     */
    public Jogo() {
        CHEFAO = new Monstro();
    }

    /**
     * Retorna o jogador atualmente ativo no jogo.
     *
     * @return o personagem controlado pelo jogador
     */
    public Personagem getJogador() {
        return jogador;
    }

    /**
     * Retorna o chefão (Monstro) da partida atual.
     *
     * @return o chefão da partida
     */
    public Personagem getCHEFAO() {
        return CHEFAO;
    }

    /**
     * Instancia um personagem do tipo {@code Mago} como o jogador.
     */
    public void instanciarMago() {
        jogador = new Mago();
    }

    /**
     * Instancia um personagem do tipo {@code Cavaleiro} como o jogador.
     */
    public void instanciarCavaleiro() {
        jogador = new Cavaleiro();
    }

    /**
     * Realiza o ataque do Cavaleiro no chefão. O tipo de ataque pode ser normal
     * ou especial, e o método verifica se o ataque especial está disponível.
     *
     * @param tipo_Ataque o tipo de ataque (1 para ataque normal, 2 para ataque
     * especial)
     */
    public void ataqueCavaleiro(int tipo_Ataque) {
        if (tipo_Ataque == 1) {
            CHEFAO.setVida(CHEFAO.getVida() - jogador.ataqueNormal());
            return;
        } else if (tipo_Ataque == 2) {
            if (jogador.ataqueEspecial()) {
                if (((Player) jogador).getInventario().isEspecial()) {
                    CHEFAO.setVida(CHEFAO.getVida() - ((Player) jogador).getInventario().usarForca(((Cavaleiro) jogador).getDANO_ESPECIAL()));
                    ((Player) jogador).getInventario().setEspecial(false);
                    return;
                } else {
                    CHEFAO.setVida(CHEFAO.getVida() - ((Cavaleiro) jogador).getDANO_ESPECIAL());
                    return;
                }
            } else {
                return;
            }
        }
    }

    /**
     * Realiza o ataque do Mago no chefão. O tipo de ataque pode ser normal ou
     * especial, e o método verifica se o ataque especial está disponível.
     *
     * @param tipo_Ataque o tipo de ataque (1 para ataque normal, 2 para ataque
     * especial)
     */
    public void ataqueMago(int tipo_Ataque) {
        if (tipo_Ataque == 1) {
            CHEFAO.setVida(CHEFAO.getVida() - jogador.ataqueNormal());
            return;
        } else if (tipo_Ataque == 2) {
            if (jogador.ataqueEspecial()) {
                if (((Player) jogador).getInventario().isEspecial()) {
                    CHEFAO.setVida(CHEFAO.getVida() - ((Player) jogador).getInventario().usarForca(((Mago) jogador).getDANO_ESPECIAL()));
                    ((Player) jogador).getInventario().setEspecial(false);
                    return;
                } else {
                    CHEFAO.setVida(CHEFAO.getVida() - ((Mago) jogador).getDANO_ESPECIAL());
                    return;
                }
            } else {
                return;
            }
        }
    }

    /**
     * Aplica uma poção de cura ao jogador, restaurando sua vida até o máximo
     * permitido. O número de poções de cura disponíveis é reduzido.
     */
    public void pocaoCura() {
        if (((Player) jogador).getInventario().getNum_pocao_vida() <= 0) {
            return;
        }

        if (((Player) jogador).getInventario().usarVida(jogador.getVida()) > ((Player) jogador).getVida_max()) {
            jogador.setVida(((Player) jogador).getVida_max());
        } else {
            jogador.setVida(((Player) jogador).getInventario().usarVida(jogador.getVida()));
        }

        ((Player) jogador).getInventario().setNum_pocao_vida(((Player) jogador).getInventario().getNum_pocao_vida() - 1);
    }

    /**
     * Aplica uma poção de força ao jogador, dobrando o dano do próximo ataque
     * especial. O número de poções de força disponíveis é reduzido.
     */
    public void pocaoForca() {
        if (((Player) jogador).getInventario().getNum_pocao_forca() <= 0) {
            return;
        }
        ((Player) jogador).getInventario().setEspecial(true);
        ((Player) jogador).getInventario().setNum_pocao_forca(((Player) jogador).getInventario().getNum_pocao_forca() - 1);
    }

    /**
     * Realiza o ataque do chefão no jogador, que pode ser um ataque normal ou
     * especial, dependendo das condições.
     *
     * @return o dano causado ao jogador pelo chefão
     */
    public int ataqueChefao() {
        if (!CHEFAO.ataqueEspecial()) {
            jogador.setVida(jogador.getVida() - CHEFAO.ataqueNormal());
            return CHEFAO.ataqueNormal();
        } else {
            jogador.setVida(jogador.getVida() - ((Monstro) CHEFAO).getDANO_ESPECIAL());
            return ((Monstro) CHEFAO).getDANO_ESPECIAL();
        }
    }

    /**
     * Verifica se o jogo chegou ao fim, ou seja, se o jogador ou o chefão foi
     * derrotado. Se o jogo terminou, exibe a mensagem apropriada e retorna
     * {@code true}; caso contrário, retorna {@code false}.
     *
     * @return {@code true} se o jogo terminou, {@code false} se ainda não
     * terminou.
     */
    public boolean verificarFimDeJogo() {
        if (CHEFAO.getVida() <= 0) {
            return true;
        } else if (jogador.getVida() <= 0) {
            return true;
        }

        return false;
    }
}
