

/* AUTORES
    Guilherme Lourenço Lopes
    João Manoel Nascimento
    Yan Pacheco de Aquino
 */

 /* ENREDO
    O jogo se passa no reino de Eldoria, um lugar outrora pacífico 
que foi mergulhado em trevas com o ressurgimento do terrível Kitsara. 
Esse monstro antigo ameaça destruir tudo em seu caminho. A última esperança 
do reino são os heróis que se levantam para enfrentar essa ameaça. O jogador 
deve escolher entre o valente Cavaleiro e o poderoso Mago para enfrentar o 
Necrotus e restaurar a paz em Eldoria.
 */

 /* MECANICA DO JOGO
    A batalha segue um sistema de turnos alternados, onde o jogador e a Kitsara 
se revezam para atacar. O jogador começa a batalha, escolhendo entre o ataque 
normal, ataque especial (com recarga de um turno) ou o uso de itens. A vitória 
é alcançada ao zerar a vida de Kitsara, enquanto o jogador perde se sua própria 
vida for reduzida a zero.

 */
public class Main {

    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        jogo.play();
    }
}
