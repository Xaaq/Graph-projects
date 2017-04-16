package graph_classes;

/**
 * Przechowuje krawędź sieci.
 */
public class WebEdge {
    /**
     * Węzeł wejściowy krawędzi.
     */
    private WebNode inputNode;

    /**
     * Węzeł wyjściowy krawędzi.
     */
    private WebNode outputNode;

    /**
     * Wartość tej krawędzi.
     */
    private int value;

    /**
     * Konstruuje krawędź jednokierunkową pomiędzy dwoma węzłami.
     *
     * @param value waga krawędzi
     * @param inputNode węzeł, z którego wychodzi krawędź
     * @param outputNode węzeł, do którego prowadzi krawędź
     */
    WebEdge(int value, WebNode inputNode, WebNode outputNode) {
        this.value = value;
        this.inputNode = inputNode;
        this.outputNode = outputNode;
    }

    /**
     * Zwraca węzeł wyjściowy krawędzi.
     *
     * @return węzeł do którego prowadzi krawędź
     */
    public WebNode getOutputNode() {
        return outputNode;
    }

    /**
     * Zwraca wagę tej krawędzi.
     *
     * @return waga krawędzi
     */
    public int getValue() {
        return value;
    }
}
