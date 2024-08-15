package Principal;

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

    public void atacar() {
        int tipoAtaque;

        while (true) {
            System.out.println("\n\nQual ataque voce deseja usar:\n(1) Ataque Normal\n(2) Ataque Especial");
            System.out.print("Digite sua opcao: ");
            tipoAtaque = input.nextInt();

            if (jogador instanceof Mago) {
                if (tipoAtaque == 1) {
                    chefao.setVida(chefao.getVida() - jogador.ataqueNormal());
                    System.out.println("Voce acertou a Kitsara e deu " + jogador.ataqueNormal() + " dano");
                    break;

                } else {
                    if (tipoAtaque == 2) {
                        if (jogador.ataqueEspecial() == true) {
                            chefao.setVida(chefao.getVida() - ((Mago) jogador).getDano_especial());
                            System.out.println("Voce acertou a Kitsara e deu " + ((Mago) jogador).getDano_especial() + " dano");
                            break;

                        } else {
                            System.out.println("Ataque especial recarregando... Tente no próximo turno");
                        }
                    }
                }

            } else {

                if (tipoAtaque == 1) {
                    chefao.setVida(chefao.getVida() - jogador.ataqueNormal());
                    System.out.println("Voce acertou a Kitsara e deu " + jogador.ataqueNormal() + " dano");
                    break;

                } else {
                    if (tipoAtaque == 2) {
                        if (jogador.ataqueEspecial() == true) {
                            chefao.setVida(chefao.getVida() - ((Cavaleiro) jogador).getDano_especial());
                            System.out.println("Voce acertou a Kitsara e deu " + ((Cavaleiro) jogador).getDano_especial() + " dano");
                            break;

                        } else {
                            System.out.println("Ataque especial recarregando... Tente no próximo turno");
                        }
                    }
                }
            }
        }

        if (jogador instanceof Mago) {
            ((Mago) jogador).atualizarDelay();

        } else {
            ((Cavaleiro) jogador).atualizarDelay();
        }

    }

    public void pocao_Cura() {

    }

    public void pocao_Forca() {

    }

    public void play() {
        int tipoClasse;
        int qualDecisao;
        int tipoItem;

        //ManipularArquivoTexto.abrirArquivoGravar("Historico_Rodadas.txt"); // Abrindo arquivo para gravar
        
        System.out.println("BEM-VINDO AO RPG:");
        System.out.println("Escolha sua classe\n(1) Cavaleiro\n(2) Mago");
        System.out.print("Digite sua opcao: ");
        tipoClasse = input.nextInt();

        if (tipoClasse == 1) {
            jogador = new Cavaleiro();

        } else {
            if (tipoClasse == 2) {
                jogador = new Mago();
            }
        }

        System.out.println("\n\n\n\n\nINICIANDO A BATALHA...\n\n\n\n\n");

        while (true) {
            System.out.println("RODADA " + contador_rodadas++);
            System.out.println("Sua vez!");
            System.out.println("O que voce quer fazer?\n(1) Atacar\n(2) Usar Item");
            System.out.print("Digite sua opcao: ");
            qualDecisao = input.nextInt();

            if (qualDecisao == 1) {
                atacar();

            } else {
                if (qualDecisao == 2) {
                    System.out.println("\nQual item deseja usar?\n (1) Cura\n (2) Forca");
                    System.out.print("Digite sua opcao: ");
                    tipoItem = input.nextInt();

                    if (tipoItem == 1) {
                        pocao_Cura();
                        continue;

                    } else {
                        if (tipoItem == 2) {
                            pocao_Forca();
                            continue;
                        }
                    }
                }
            }

            if (jogador.vivoOuMorto() == true && chefao.vivoOuMorto() == false) {
                System.out.println("Voce venceu!");
                break;
            }

            if (chefao.ataqueEspecial() == false) {
                jogador.setVida(jogador.getVida() - chefao.ataqueNormal());
                System.out.println("Voce foi acertado pela Kitsara e sofreu " + chefao.ataqueNormal() + " dano");

            } else {
                jogador.setVida(jogador.getVida() - ((Kitsara) chefao).getDano_especial());
                System.out.println("Voce foi acertado pela Kitsara e sofreu " + ((Kitsara) chefao).getDano_especial() + " dano");
            }

            System.out.println("\n\nRESULTADO DA RODADA");
            System.out.println("Vida do jogador: " + jogador.getVida());
            System.out.println("Vida do boss: " + chefao.getVida() + "\n\n");

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
            
            if (jogador.vivoOuMorto() == false && chefao.vivoOuMorto() == true) {
                System.out.println("Voce perdeu!");
                break;
            }

            System.out.println("\n\n\nINICIANDO A PROXIMA RODADA...\n\n\n");

        }

        //ManipularArquivoTexto.fecharArquivoGravacao(); //Fechando arquivo
    }

}
