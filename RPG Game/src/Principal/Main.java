package Principal;

//import Interface.Window.Game;

/**
 * Curso: Engenharia de Computação
 * Disciplina: Programação Orientada a Objetos
 * Professora: Luciana
 *
 * Projeto: Projeto Final - Batalha por Drakovia
 * 
 * Descrição: Um RPG de turno, inspirado nos clássicos Final Fantasy, onde terá
 * apenas uma batalha, você contra o chefão. Esse jogo conta com uma 
 * interface gráfica.
 *
 * AUTORES:
 * @author Guilherme Lourenço Lopes
 * @author João Manoel Nascimento
 * @author Yan Pacheco de Aquino
 * 
 * ENREDO:
 * Em um reino conhecido como Drakovia, forças do mal ameaçam a paz e a 
 * prosperidade. Um Monstro, uma criatura antiga e aterrorizante, se ergue das 
 * profundezas do abismo, espalhando terror e destruição. Sua presença lança uma 
 * sombra sobre a terra, e o povo de Drakovia começa a perder a esperança.
 * Você, um dos últimos guerreiros de Drakovia, deve enfrentar essa criatura 
 * aterrorizante em uma batalha épica. Armado com habilidades únicas e poções 
 * poderosas, sua missão é derrotar o Monstro e restaurar a paz no reino.
 * Ao longo da batalha, você terá que escolher estrategicamente entre atacar com 
 * força bruta ou usar itens valiosos em seu inventário. Mas cuidado: o Monstro é 
 * astuto e não dará trégua. Com ataques devastadores, ele tentará acabar com sua 
 * vida. A vitória depende de suas decisões, coragem, e capacidade de superar o 
 * Monstro antes que seja tarde demais.
 * 
 * MECÂNICA DO JOGO:
 * No jogo, o jogador enfrenta um monstro em turnos. O jogador pode atacar ou usar 
 * um item a cada turno. Existem dois tipos de ataques:
 *  1. Ataque Normal: Pode ser usado sempre.
 *  2. Ataque Especial: Mais poderoso, mas tem um turno de recarga.
 *
 * O jogador também tem dois itens no inventário:
 *  1. Poção de Cura: Recupera 300 pontos de vida.
 *  2. Poção de Força: Dobra o dano do próximo ataque especial por um turno.
 *
 * O monstro, que não tem itens, também possui dois ataques:
 *  1. Ataque Normal: 80% de chance de causar 150 de dano.
 *  2. Ataque Especial: 20% de chance de causar 300 de dano.
 * 
 * O objetivo é zerar a vida do oponente para vencer.
 */
public class Main {

    /**
     * Método principal que inicia o jogo.
     * 
     * Este método cria uma instância da classe Jogo e chama o método play() 
     * para iniciar a jogabilidade. A linha comentada "new Game();" sugere que 
     * uma interface gráfica poderia ser iniciada, mas foi substituída por 
     * um jogo baseado em texto.
     * 
     * @param args Argumentos da linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        jogo.play();
        // new Game();
    }
}
