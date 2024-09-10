package Principal;

/**
 * Representa uma poção de força no jogo. Esta classe é uma subclasse de
 * {@link Item} e aplica um efeito de aumento de dano quando usada.
 */
public class PocaoDeForca extends Item {

    /**
     * Cria uma nova instância da {@code PocaoDeForca}. Este construtor chama o
     * construtor da classe base {@link Item}.
     */
    public PocaoDeForca() {
        super();
    }

    /**
     * Aplica o efeito da poção de força. Multiplica o dano fornecido pelo valor
     * da poção de força.
     *
     * @param dano O dano base a ser modificado pelo efeito da poção.
     * @return O dano aumentado após aplicar o efeito da poção de força.
     */
    @Override
    public int efeitoItem(int dano) {
        return dano * getVALOR_POCAO_FORCA();
    }
}
