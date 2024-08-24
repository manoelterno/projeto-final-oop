
import java.util.Scanner;

public class Jogo {

    private static int contador_rodadas;
    private final Scanner input;
    private final Personagem chefao;
    private Player jogador;

    public Jogo() {
        contador_rodadas = 1;
        input = new Scanner(System.in);
        chefao = new Monstro();
    }

    public void play() {
        int qualDecisao;
        ManipularArquivoTexto.abrirArquivoGravacao("log.txt");
        mensagem_boas_vindas();
        escolher_classe();
        mensagem_iniciar_batalha();

        while (true) {
            mensagem_inicio_rodada();

            qualDecisao = input.nextInt();

            if (qualDecisao == 1) {
                ataque_player();

            } else if (qualDecisao == 2) {
                usar_item();
            }

            ataque_chefao();
            mensagem_resultado_rodada();
            jogador.atualizarDelay();

            if (verificar_fim_de_jogo()) {
                ManipularArquivoTexto.fecharArquivoGravacao();
                break;
            }

            System.out.println("\nINICIANDO A PROXIMA RODADA...");
        }

        
    }

    public void ataque_player() {
        int tipoAtaque;

        while (true) {
            if (jogador.getDelay_especial() > 0) {
                System.out.println("\nQual ataque voce deseja usar:\n(1) Ataque Normal"
                        + "\n(2) Ataque Especial (Recarregando...)");
            } else {
                System.out.println("\nQual ataque voce deseja usar:\n(1) Ataque Normal"
                        + "\n(2) Ataque Especial");
            }
            System.out.print("Digite sua opcao: ");
            tipoAtaque = input.nextInt();

            if (jogador instanceof Mago) {
                if (ataque_mago(tipoAtaque)) {
                    break;
                }
            } else if (jogador instanceof Cavaleiro) { //if 
                if (ataque_cavaleiro(tipoAtaque)) {
                    break;
                }
            }
        }

    }

    public boolean ataque_mago(int tipoAtaque) {

        if (tipoAtaque == 1) {
            chefao.setVida(chefao.getVida() - jogador.ataqueNormal());
            System.out.println("O Mago lançou um simples feitiço contra o Monstro e causou " + jogador.ataqueNormal() + " de dano");
            return true;

        } else if (tipoAtaque == 2) {
            if (jogador.ataqueEspecial() == true) {
                if (jogador.getInventario().isEspecial()) {
                    chefao.setVida(chefao.getVida() - ((Mago) jogador).getDano_especial() * 2);
                    System.out.println("O Mago lançou um feitiço poderoso e fortalecido"
                            + " contra o Monstro e causou " + ((Mago) jogador).getDano_especial() * 2 + " de dano");
                    jogador.getInventario().setEspecial(false);
                    return true;
                } else {
                    chefao.setVida(chefao.getVida() - ((Mago) jogador).getDano_especial());
                    System.out.println("O Mago lançou um feitiço poderoso contra o Monstro e causou " + ((Mago) jogador).getDano_especial() + " de dano");
                    return true;
                }

            } else {
                System.out.println("Ataque especial recarregando... Tente no próximo turno.");
                return false;
            }
        }
        //nunca chega neste return
        return false;
    }

    public boolean ataque_cavaleiro(int tipoAtaque) {
        if (tipoAtaque == 1) {
            chefao.setVida(chefao.getVida() - jogador.ataqueNormal());
            System.out.println("O Cavaleiro acertou o monstro com um ataque simples e causou " + jogador.ataqueNormal() + " de dano");
            return true;

        } else if (tipoAtaque == 2) {
            if (jogador.ataqueEspecial() == true) {
                if (jogador.getInventario().isEspecial()) {
                    chefao.setVida(chefao.getVida() - ((Cavaleiro) jogador).getDano_especial() * 2);
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
        //nunca chega neste return
        System.out.println("aviso erro");
        return false;
    }

    public void usar_item() {
        int tipoItem;
        System.out.println("\nQual item deseja usar?\n (1) Cura\n (2) Forca");
        System.out.print("Digite sua opcao: ");
        tipoItem = input.nextInt();

        if (tipoItem == 1) {
            pocao_Cura();

        } else if (tipoItem == 2) {
            pocao_Forca();
        }
    }

    public void pocao_Cura() {
        if (jogador.getInventario().getNum_pocao_vida() <= 0) {
            System.out.println("Ação inválida.");
            System.out.println("Poções restantes: " + jogador.getInventario().getNum_pocao_vida() + " de vida e "
                    + jogador.getInventario().getNum_pocao_forca() + " de força");
            return;
        }
        if (jogador.getVida() + jogador.getInventario().getValor_pocao_vida() > jogador.getVida_max()) {
            jogador.setVida(jogador.getVida_max());
        } else {
            jogador.setVida(jogador.getVida() + jogador.getInventario().getValor_pocao_vida());
        }
        jogador.getInventario().usarVida();
        System.out.println("O " + jogador.getClass().getName() + " usou uma pocao de cura e aumentou sua vida atual em " 
                + jogador.getInventario().getValor_pocao_vida());
        System.out.println("A vida atual do " + jogador.getClass().getName() + " é " + jogador.getVida());
        System.out.println("Poções restantes: " + jogador.getInventario().getNum_pocao_vida() + " de vida e " + jogador.getInventario().getNum_pocao_forca() + " de força");
    }

    public void pocao_Forca() {
        if (jogador.getInventario().getNum_pocao_forca() <= 0) {
            System.out.println("Ação inválida.");
            System.out.println("Poções restantes: " + jogador.getInventario().getNum_pocao_vida() + " de vida e " + jogador.getInventario().getNum_pocao_forca() + " de força");
            return;
        }

        jogador.getInventario().usarForca();
        System.out.println("Você usou uma poção de força e agora seu próximo ataque especial terá o dobro de dano");
        System.out.println("Poções restantes: " + jogador.getInventario().getNum_pocao_vida() + " de vida e " + jogador.getInventario().getNum_pocao_forca() + " de força");
    }

    public void mensagem_boas_vindas() {
        System.out.println("BEM-VINDO AO RPG:");

    }

    public void escolher_classe() {
        System.out.println("Escolha sua classe\n (1) Cavaleiro\nResistente, porém modesto.\n (2) Mago\nPoderoso, porém frágil.");
        System.out.print("Digite sua opcao: ");
        int tipoClasse;

        while (true) {
            tipoClasse = input.nextInt();
            switch (tipoClasse) {
                case 1:
                    jogador = new Cavaleiro();
                    return;
                case 2:
                    jogador = new Mago();
                    return;
                default:
                    System.out.println("Entrada invalida. Tente Novamente.");
                    continue;

            }
        }
    }

    public void mensagem_iniciar_batalha() {
        System.out.println("\n\nINICIANDO A BATALHA...\n\n");
        System.out.println("Vida do " + jogador.getClass().getName() + ": " + jogador.getVida());
        System.out.println("Vida do " + chefao.getClass().getName() + ": " + chefao.getVida() + "\n");
        System.out.println("Inventario do jogador:\n" + jogador.getInventario().getNum_pocao_vida() + " pocoes de vida\n"
                + jogador.getInventario().getNum_pocao_forca() + " pocoes de forca");

        ManipularArquivoTexto.gravarArquivo("INICIO DA BATALHA");
        ManipularArquivoTexto.gravarArquivo("Vida do " + jogador.getClass().getName() + ": " + jogador.getVida());
        ManipularArquivoTexto.gravarArquivo("Vida do " + chefao.getClass().getName() + ": " + chefao.getVida() + "\n");

    }

    public void mensagem_inicio_rodada() {
        System.out.println("RODADA " + Jogo.contador_rodadas++);
        System.out.println("Sua vez!");
        System.out.println("O que voce quer fazer?\n(1) Atacar\n(2) Usar Item");
        System.out.print("Digite sua opcao: ");
    }

    public void mensagem_resultado_rodada() {
        System.out.println("\nRESULTADO DA RODADA");
        System.out.println("Vida do " + jogador.getClass().getName() + ": " + jogador.getVida());
        System.out.println("Vida do " + chefao.getClass().getName() + ": " + chefao.getVida() + "\n");

        ManipularArquivoTexto.gravarArquivo("RESULTADO DA RODADA " + (contador_rodadas - 1));
        ManipularArquivoTexto.gravarArquivo("Vida do " + jogador.getClass().getName() + ": " + jogador.getVida());
        ManipularArquivoTexto.gravarArquivo("Vida do " + chefao.getClass().getName() + ": " + chefao.getVida() + "\n");

    }

    public void ataque_chefao() {
        if (chefao.ataqueEspecial() == false) {
            jogador.setVida(jogador.getVida() - chefao.ataqueNormal());
            System.out.println("O Monstro atacou o " + jogador.getClass().getName() + " e causou " + chefao.ataqueNormal() + " de dano");

        } else {
            jogador.setVida(jogador.getVida() - ((Monstro) chefao).getDano_especial());
            System.out.println("O Monstro atacou o " + jogador.getClass().getName() + " com um ataque especial e causou "
                    + ((Monstro) chefao).getDano_especial() + " de dano");
        }
    }

    public boolean verificar_fim_de_jogo() {
        if (jogador.vivoOuMorto() == true && chefao.vivoOuMorto() == false) {
            System.out.println("Voce venceu! Os dados da partida foram salvos no arquivo log.txt");
            return true;
        }

        if (jogador.vivoOuMorto() == false && chefao.vivoOuMorto() == true) {
            System.out.println("Voce perdeu! Os dados da partida foram salvos no arquivo log.txt\nGame Over");
            return true;
        }
        return false;
    }

}
