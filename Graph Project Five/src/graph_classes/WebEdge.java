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
    private int maxValue;

    /**
     * Aktualny przepływ przez tą krawędź
     */
    private int currentValue;

    /**
     * Konstruuje krawędź jednokierunkową pomiędzy dwoma węzłami.
     *
     * @param maxValue waga krawędzi
     * @param inputNode węzeł, z którego wychodzi krawędź
     * @param outputNode węzeł, do którego prowadzi krawędź
     */
    WebEdge(int maxValue, WebNode inputNode, WebNode outputNode) {
        this.maxValue = maxValue;
        currentValue = maxValue;
        this.inputNode = inputNode;
        this.outputNode = outputNode;
    }

    /**
     * Zwraca węzeł wejściowy krawędzi.
     *
     * @return węzeł od którego prowadzi krawędź
     */
    public WebNode getInputNode() {
        return inputNode;
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
    public int getMaxValue() {
        return maxValue;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }
}
