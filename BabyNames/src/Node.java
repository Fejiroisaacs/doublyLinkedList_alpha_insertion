import javax.xml.soap.Name;

public class Node<E> {
    // fields
    private NameData element;
    private Node<E> next;
    private Node<E> prev;

    // constructor - used to make the Node object
    public Node(NameData element, Node<E> prev, Node<E> next) {
        this.element = element;
        this.prev = prev;
        this.next = next;
    }

    // getters -  returns either the next or previous Node or the element
    public NameData getElement(){ return this.element; }
    public Node<E> getNext(){ return this.next; }
    public Node<E> getPrev(){ return this.prev; }

    // setters - set the next or previous Node
    public void setPrev(Node<E> prev){ this.prev = prev; }
    public void setNext(Node<E> next){ this.next = next; }

    // the toString method that prints details about each node. 
    public String toString(){ return element.toString(); }

}
