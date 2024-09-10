package Principal;

/**
 * Representa uma poção de cura no jogo. Esta classe é uma subclasse de
 * {@link Item} e aplica um efeito de cura quando usada.
 */
public class PocaoDeCura extends Item {

    /**
     * Cria uma nova instância da {@code PocaoDeCura}. Este construtor chama o
     * construtor da classe base {@link Item}.
     */
    public PocaoDeCura() {
        super();
    }

    /**
     * Aplica o efeito da poção de cura. Adiciona o valor da poção de cura à
     * vida fornecida.
     *
     * @param vida A vida base a ser aumentada pelo efeito da poção.
     * @return A vida aumentada após aplicar o efeito da poção de cura.
     */
    @Override
    public int efeitoItem(int vida) {
        return getVALOR_POCAO_VIDA() + vida;
    }
}
