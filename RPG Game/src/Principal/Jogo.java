package Principal;

import Servicos.ManipularArquivoTexto;
import java.util.Scanner;

/**
 * A classe Jogo é responsável por gerenciar a lógica principal do jogo de RPG,
 * coordenando as interações entre o jogador e o chefão (Monstro). Ela controla
 * o fluxo do jogo, incluindo a escolha de classes, turnos, ataques, uso de
 * itens e verificação das condições de vitória ou derrota.
 */
public class Jogo {

    private final Scanner input;
    private final Personagem chefao;
    private Player jogador;
    private static int contador_rodadas;

    /**
     * Construtor que inicializa o jogo, criando o chefão (Monstro) e preparando
     * o scanner para entrada de dados do usuário.
     */
    public Jogo() {
        contador_rodadas = 1;
        input = new Scanner(System.in);
        chefao = new Monstro();
    }

    /**
     * Inicia o jogo, permitindo ao jogador escolher sua classe e prosseguir com
     * as rodadas de batalha. A batalha continua até que o chefão ou o jogador
     * seja derrotado. Os logs do jogo são gravados em um arquivo de texto.
     */
    public void play() {
        int qual_Decisao;

        ManipularArquivoTexto.abrirArquivoGravacao("log.txt");
        mensagemBoasVindas();
        escolherClasse();
        mensagemIniciarBatalha();

        while (true) {
            mensagemInicioRodada();

            qual_Decisao = input.nextInt();

            if (qual_Decisao == 1) {
                ataquePlayer();
                if (verificarFimDeJogo()) {
                    ManipularArquivoTexto.fecharArquivoGravacao();
                    break;
                }
            } else if (qual_Decisao == 2) {
                usarItem();
            }

            ataqueChefao();
            mensagemResultadoRodada();
            jogador.atualizarDelay();

            if (verificarFimDeJogo()) {
                ManipularArquivoTexto.fecharArquivoGravacao();
                break;
            }

            System.out.println("\nINICIANDO A PROXIMA RODADA...");
        }

    }

    /**
     * Permite ao jogador escolher entre um ataque normal ou especial. A escolha
     * depende do tempo de recarga do ataque especial e da classe do jogador.
     */
    public void ataquePlayer() {
        int tipo_ataque;

        while (true) {
            if (jogador.getDelay_especial() > 0) {
                System.out.println("\nQual ataque voce deseja usar:\n(1) Ataque Normal"
                        + "\n(2) Ataque Especial (Recarregando...)");
            } else {
                System.out.println("\nQual ataque voce deseja usar:\n(1) Ataque Normal"
                        + "\n(2) Ataque Especial");
            }

            System.out.print("Digite sua opcao: ");
            tipo_ataque = input.nextInt();

            if (jogador instanceof Mago) {
                if (ataqueMago(tipo_ataque)) {
                    break;
                }
            } else if (jogador instanceof Cavaleiro) { //if 
                if (ataqueCavaleiro(tipo_ataque)) {
                    break;
                }
            }
        }

    }

    /**
     * Executa o ataque do jogador caso ele seja um Mago. Verifica se o ataque é
     * normal ou especial e ajusta o dano infligido ao chefão de acordo com o
     * tipo de ataque.
     *
     * @param tipo_Ataque o tipo de ataque escolhido pelo jogador (1 para ataque
     * normal, 2 para ataque especial).
     * @return true se o ataque foi executado com sucesso, false se o ataque
     * especial ainda está recarregando.
     */
    public boolean ataqueMago(int tipo_Ataque) {

        if (tipo_Ataque == 1) {
            chefao.setVida(chefao.getVida() - jogador.ataqueNormal());
            System.out.println("O Mago lancou um simples feitiço contra o Monstro e causou " + jogador.ataqueNormal() + " de dano");
            return true;

        } else if (tipo_Ataque == 2) {
            if (jogador.ataqueEspecial() == true) {
                if (jogador.getInventario().isEspecial()) {
                    chefao.setVida(chefao.getVida() - jogador.getInventario().usarForca(((Mago) jogador).getDano_especial()));
                    System.out.println("O Mago lancou um feitico poderoso e fortalecido"
                            + " contra o Monstro e causou " + ((Mago) jogador).getDano_especial() * 2 + " de dano");
                    jogador.getInventario().setEspecial(false);
                    return true;
                } else {
                    chefao.setVida(chefao.getVida() - ((Mago) jogador).getDano_especial());
                    System.out.println("O Mago lancou um feitico poderoso contra o Monstro e causou " + ((Mago) jogador).getDano_especial() + " de dano");
                    return true;
                }

            } else {
                System.out.println("Ataque especial recarregando... Tente no próximo turno.");
                return false;
            }
        }

        System.err.println("Aviso de erro"); //nunca chega neste return
        return false;
    }

    /**
     * Executa o ataque do jogador caso ele seja um Cavaleiro. Verifica se o
     * ataque é normal ou especial e ajusta o dano infligido ao chefão de acordo
     * com o tipo de ataque.
     *
     * @param tipo_Ataque o tipo de ataque escolhido pelo jogador (1 para ataque
     * normal, 2 para ataque especial).
     * @return true se o ataque foi executado com sucesso, false se o ataque
     * especial ainda está recarregando.
     */
    public boolean ataqueCavaleiro(int tipo_Ataque) {
        if (tipo_Ataque == 1) {
            chefao.setVida(chefao.getVida() - jogador.ataqueNormal());
            System.out.println("O Cavaleiro acertou o monstro com um ataque simples e causou " + jogador.ataqueNormal() + " de dano");
            return true;

        } else if (tipo_Ataque == 2) {
            if (jogador.ataqueEspecial() == true) {
                if (jogador.getInventario().isEspecial()) {
                    chefao.setVida(chefao.getVida() - jogador.getInventario().usarForca(((Cavaleiro) jogador).getDano_especial()));
                    System.out.println("O Cavaleiro acertou o monstro com um ataque poderoso e fortalecido, causando " + ((Cavaleiro) jogador).getDano_especial() * 2
                            + " de dano");
                    jogador.getInventario().setEspecial(false);
                    return true;
                } else {
                    chefao.setVida(chefao.getVida() - ((Cavaleiro) jogador).getDano_especial());
                    System.out.println("O Cavaleiro acertou o monstro com um ataque poderoso e causou " + ((Cavaleiro) jogador).getDano_especial() + " de dano");
                    return true;
                }

            } else {
                System.out.println("Ataque especial recarregando... Tente no próximo turno");
                return false;
            }
        }

        System.err.println("Aviso De erro"); //nunca chega neste return
        return false;
    }

    /**
     * Permite ao jogador escolher e usar um item de cura ou de força. A
     * quantidade de itens restantes é atualizada após o uso.
     */
    public void usarItem() {
        int tipo_Item;
        System.out.println("\nQual item deseja usar?\n (1) Cura\n (2) Forca");
        System.out.print("Digite sua opcao: ");
        tipo_Item = input.nextInt();

        if (tipo_Item == 1) {
            pocaoCura();

        } else if (tipo_Item == 2) {
            pocaoForca();
        }
    }

    /**
     * Aplica uma poção de cura ao jogador, restaurando sua vida até o máximo
     * permitido. O número de poções de cura disponíveis é reduzido.
     */
    public void pocaoCura() {
        if (jogador.getInventario().getNum_pocao_vida() <= 0) {
            System.out.println("Acao invalida.");
            System.out.println("Pocoes restantes: " + jogador.getInventario().getNum_pocao_vida() + " de vida e "
                    + jogador.getInventario().getNum_pocao_forca() + " de forca");
            return;
        }

        if (jogador.getInventario().usarVida(jogador.getVida()) > jogador.getVida_max()) {
            jogador.setVida(jogador.getVida_max());
        } else {
            jogador.setVida(jogador.getInventario().usarVida(jogador.getVida()));
        }

        jogador.getInventario().setNum_pocao_vida(jogador.getInventario().getNum_pocao_vida() - 1);

        System.out.println("O " + jogador.getClass().getName() + " usou uma pocao de cura e aumentou sua vida atual em "
                + jogador.getInventario().getPocao_vida().getVALOR_POCAO_VIDA());
        System.out.println("A vida atual do personagem é: " + jogador.getVida());
        System.out.println("Pocoes restantes: " + jogador.getInventario().getNum_pocao_vida() + " de vida e "
                + jogador.getInventario().getNum_pocao_forca() + " de forca");
    }

    /**
     * Aplica uma poção de força ao jogador, dobrando o dano do próximo ataque
     * especial. O número de poções de força disponíveis é reduzido.
     */
    public void pocaoForca() {
        if (jogador.getInventario().getNum_pocao_forca() <= 0) {
            System.out.println("Acao inválida.");
            System.out.println("Pocoes restantes: " + jogador.getInventario().getNum_pocao_vida() + " de vida e "
                    + jogador.getInventario().getNum_pocao_forca() + " de forca");
            return;
        }

        jogador.getInventario().setEspecial(true);
        jogador.getInventario().setNum_pocao_forca(jogador.getInventario().getNum_pocao_forca() - 1);

        System.out.println("O " + jogador.getClass().getName() + " usou uma pocao de força para dobrar o dano do seu proximo ataque especial.");
        System.out.println("Pocoes restantes: " + jogador.getInventario().getNum_pocao_vida() + " de vida e "
                + jogador.getInventario().getNum_pocao_forca() + " de forca");
    }

    /**
     * Exibe uma mensagem inicial de boas-vindas ao jogador.
     */
    public void mensagemBoasVindas() {
        System.out.println("Seja bem-vindo ao RPG Eldoria!");
        System.out.println("Hoje o desafio eh derrotar o poderoso Monstro!");
    }

    /**
     * Permite ao jogador escolher entre as classes Cavaleiro e Mago. A escolha
     * define o tipo de personagem que o jogador controlará durante o jogo.
     */
    public void escolherClasse() {
        System.out.println("\nEscolha sua classe: \n(1) Cavaleiro \n(2) Mago");
        System.out.print("Digite sua opcao: ");
        int escolha_classe = input.nextInt();

        switch (escolha_classe) {
            case 1:
                jogador = new Cavaleiro();
                break;
            case 2:
                jogador = new Mago();
                break;
            default:
                System.out.println("Escolha invalida.");
                escolherClasse();
        }

        System.out.println("\nVoce escolheu a classe: " + jogador.getClass().getName());
    }

    /**
     * Exibe uma mensagem de início de batalha, mostrando a vida atual do
     * jogador e do chefão. Também grava esses dados no arquivo de log.
     */
    public void mensagemIniciarBatalha() {
        System.out.println("\nBatalha Iniciada!");
        System.out.println("Vida do jogador: " + jogador.getVida());
        System.out.println("Vida do Monstro: " + chefao.getVida());
        ManipularArquivoTexto.gravarArquivo("INICIO DA BATALHA");
        ManipularArquivoTexto.gravarArquivo("Vida do " + jogador.getClass().getName() + ": " + jogador.getVida());
        ManipularArquivoTexto.gravarArquivo("Vida do " + chefao.getClass().getName() + ": " + chefao.getVida() + "\n");
    }

    /**
     * Exibe a mensagem de início de rodada, solicitando ao jogador que escolha
     * entre atacar ou usar um item.
     */
    public void mensagemInicioRodada() {
        System.out.println("\nEscolha sua acao: ");
        System.out.println("(1) Atacar");
        System.out.println("(2) Usar Item");
        System.out.print("Digite sua opcao: ");
    }

    /**
     * Exibe o resultado da rodada, mostrando a vida restante do jogador e do
     * chefão. Os resultados também são gravados no arquivo de log.
     */
    public void mensagemResultadoRodada() {
        System.out.println("\nResultado da Rodada " + contador_rodadas + ":");
        System.out.println("Vida do jogador: " + jogador.getVida());
        System.out.println("Vida do Monstro: " + chefao.getVida());
        ManipularArquivoTexto.gravarArquivo("RESULTADO DA RODADA " + (contador_rodadas - 1));
        ManipularArquivoTexto.gravarArquivo("Vida do " + jogador.getClass().getName() + ": " + jogador.getVida());
        ManipularArquivoTexto.gravarArquivo("Vida do " + chefao.getClass().getName() + ": " + chefao.getVida() + "\n");
    }

    /**
     * Realiza o ataque do chefão no jogador, que pode ser um ataque normal ou
     * especial, dependendo das condições.
     */
    public void ataqueChefao() {
        int dano = chefao.ataqueNormal();
        jogador.setVida(jogador.getVida() - dano);
        System.out.println("\nO Monstro atacou o jogador causando " + dano + " de dano.");
    }

    /**
     * Verifica se o jogo chegou ao fim, ou seja, se o jogador ou o chefão foi
     * derrotado. Se o jogo terminou, exibe a mensagem apropriada e retorna
     * true; caso contrário, retorna false.
     *
     * @return true se o jogo terminou, false se ainda não terminou.
     */
    public boolean verificarFimDeJogo() {
        if (chefao.getVida() <= 0) {
            System.out.println("\nParabens! Voce derrotou o Monstro!");
            return true;
        } else if (jogador.getVida() <= 0) {
            System.out.println("\nVoce foi derrotado pelo Monstro. Fim de Jogo.");
            return true;
        }

        return false;
    }
}
