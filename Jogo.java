
import java.util.Scanner;

public class Jogo {

    private static int contador_rodadas;
    private final Scanner input;
    private final Personagem chefao;
    private Personagem jogador;

    public Jogo() {
        contador_rodadas = 1;
        input = new Scanner(System.in);
        chefao = new Kitsara();
    }

    public void play() {
        int tipoClasse;
        int qualDecisao;
        //int tipoItem;

        //ManipularArquivoTexto.abrirArquivoGravar("Historico_Rodadas.txt"); // Abrindo arquivo para gravar
        mensagem_boas_vindas();

        tipoClasse = input.nextInt();
        escolher_classe(tipoClasse);

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

            // GRAVANDO ARQUIVO COM OS DADOS DE CADA RODADA (Rodada ; Classe ; VidaJogador ; Chefao ; VidaChefao)
            /*if (jogador instanceof Mago) {
                ManipularArquivoTexto.gravarArquivo("Rodada" + contador_rodadas + ";"
                        + Mago.class.getSimpleName() + ";" + jogador.getVida() + ";" + "100"
                        + ";" + Kitsara.class.getSimpleName() + ";" + chefao.getVida() + ";" + "200"); 
            }

            if (jogador instanceof Cavaleiro) {
                ManipularArquivoTexto.gravarArquivo("Rodada" + contador_rodadas + ";"
                        + Cavaleiro.class.getSimpleName() + ";" + jogador.getVida() + ";" + "100"
                        + ";" + Kitsara.class.getSimpleName() + ";" + chefao.getVida() + ";" + "200"); 
            }*/
            
            if (verificar_fim_de_jogo()) {
                break;
            }

            System.out.println("\n\n\nINICIANDO A PROXIMA RODADA...\n\n\n");

        }

        //ManipularArquivoTexto.fecharArquivoGravacao(); //Fechando arquivo
    }

    public void ataque_player() {
        int tipoAtaque;

        while (true) {
            
            System.out.println("\n\nQual ataque voce deseja usar:\n(1) Ataque Normal\n(2) Ataque Especial");
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

        if (jogador instanceof Mago) {
            ((Mago) jogador).atualizarDelay();
        } else if (jogador instanceof Cavaleiro) {
            ((Cavaleiro) jogador).atualizarDelay();
        }

    }

    public boolean ataque_mago(int tipoAtaque) {

        if (tipoAtaque == 1) {
            chefao.setVida(chefao.getVida() - jogador.ataqueNormal());
            System.out.println("Voce acertou a Kitsara e deu " + jogador.ataqueNormal() + " dano");
            return true;

        } else if (tipoAtaque == 2) {
            if (jogador.ataqueEspecial() == true) {
                if (jogador.getInventario().isEspecial()) {
                    chefao.setVida(chefao.getVida() - ((Mago) jogador).getDano_especial() * 2);
                    System.out.println("Voce acertou a Kitsara com o poder especial aumentado " + ((Mago) jogador).getDano_especial() * 2 + " dano");
                    jogador.getInventario().setEspecial(false);
                    return true;
                } else {
                    chefao.setVida(chefao.getVida() - ((Mago) jogador).getDano_especial());
                    System.out.println("Voce acertou a Kitsara e deu " + ((Mago) jogador).getDano_especial() + " dano");
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

    public boolean ataque_cavaleiro(int tipoAtaque) {
        if (tipoAtaque == 1) {
            chefao.setVida(chefao.getVida() - jogador.ataqueNormal());
            System.out.println("Voce acertou a Kitsara e deu " + jogador.ataqueNormal() + " dano");
            return true;

        } else if (tipoAtaque == 2) {
            if (jogador.ataqueEspecial() == true) {
                if (jogador.getInventario().isEspecial()) {
                    chefao.setVida(chefao.getVida() - ((Cavaleiro) jogador).getDano_especial() * 2);
                    System.out.println("Voce acertou a Kitsara com o poder especial aumentado " + ((Cavaleiro) jogador).getDano_especial() * 2 + " dano");
                    jogador.getInventario().setEspecial(false);
                    return true;
                } else {
                    chefao.setVida(chefao.getVida() - ((Cavaleiro) jogador).getDano_especial());
                    System.out.println("Voce acertou a Kitsara e deu " + ((Cavaleiro) jogador).getDano_especial() + " dano");
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
            System.out.println("Poções restantes: " + jogador.getInventario().getNum_pocao_vida() + " de vida e " + jogador.getInventario().getNum_pocao_forca() + " de força");
            return;
        }

        jogador.setVida(jogador.getVida() + 100);
        jogador.getInventario().usarVida();
        System.out.println("Você usou uma poção de cura e aumentou sua vida atual em 100");
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
        System.out.println("Escolha sua classe\n(1) Cavaleiro\n(2) Mago");
        System.out.print("Digite sua opcao: ");
    }

    public void escolher_classe(int tipoClasse) {

        if (tipoClasse == 1) {
            jogador = new Cavaleiro();

        } else if (tipoClasse == 2) {
            jogador = new Mago();
        }
    }

    public void mensagem_iniciar_batalha() {
        System.out.println("\n\n\n\n\nINICIANDO A BATALHA...\n\n\n\n\n");
        System.out.println("Vida do chefao: " + chefao.getVida());
        System.out.println("Vida do jogador: " + jogador.getVida());
        System.out.println("Inventario do jogador:\n" + jogador.getInventario().getNum_pocao_vida() + " pocoes de vida\n"
                + jogador.getInventario().getNum_pocao_forca() + " pocoes de forca");

    }

    public void mensagem_inicio_rodada() {
        System.out.println("RODADA " + Jogo.contador_rodadas++);
        System.out.println("Sua vez!");
        System.out.println("O que voce quer fazer?\n(1) Atacar\n(2) Usar Item");
        System.out.print("Digite sua opcao: ");
    }

    public void mensagem_resultado_rodada() {
        System.out.println("\n\nRESULTADO DA RODADA");
        System.out.println("Vida do jogador: " + jogador.getVida());
        System.out.println("Vida do boss: " + chefao.getVida() + "\n\n");
    }

    public void ataque_chefao() {
        if (chefao.ataqueEspecial() == false) {
            jogador.setVida(jogador.getVida() - chefao.ataqueNormal());
            System.out.println("Voce foi acertado pela Kitsara e sofreu " + chefao.ataqueNormal() + " dano");

        } else {
            jogador.setVida(jogador.getVida() - ((Kitsara) chefao).getDano_especial());
            System.out.println("Voce foi acertado pela Kitsara e sofreu " + ((Kitsara) chefao).getDano_especial() + " dano");
        }
    }

    public boolean verificar_fim_de_jogo() {
        if (jogador.vivoOuMorto() == true && chefao.vivoOuMorto() == false) {
            System.out.println("Voce venceu!");
            return true;
        }

        if (jogador.vivoOuMorto() == false && chefao.vivoOuMorto() == true) {
            System.out.println("Voce perdeu!\nGame Over");
            return true;
        }
        return false;
    }

}
