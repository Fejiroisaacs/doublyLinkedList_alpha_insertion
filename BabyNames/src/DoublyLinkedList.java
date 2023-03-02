import org.omg.CORBA.TRANSACTION_MODE;

import javax.naming.Name;


public class DoublyLinkedList {
    //three cases for adding, linked list 0, 1 or > 1
    private Node<NameData> header; // header sentinel
    private Node<NameData> trailer; // trailer sentinel
    private int size = 0;

    public DoublyLinkedList(){
        this.header = new Node<>(null, null, null); // initializing the header sentinel
        this.trailer = new Node<>(null, header, null); // initializing the trailer sentinel
        this.header.setNext(trailer);
    }

    /**
     *
     * @return the size of the DoublyLinkedList
     */
    public int getSize() { return size; }

    /**
     *
     * @return if the DoublyLinkedList contains any element
     */
    public boolean isEmpty(){ return this.size == 0; }

    /**
     *
     * @return returns the first element in the DoublyLinkedList
     */
    public NameData first(){
        if(!isEmpty()) return this.header.getNext().getElement();
        return null;
    }
    /**
     *
     * @return returns the last element in the DoublyLinkedList
     */
    public NameData last(){
        if(!isEmpty()) return this.trailer.getPrev().getElement();
        return null;
    }

    /**
     *
     * @param data the NameData we are trying to add in-between the two other NameData
     * @param predecessor the Node that should be before data
     * @param successor the Node that should be after data
     */
    private void addBetween(NameData data, Node<NameData> predecessor, Node<NameData> successor){
        Node<NameData> added = new Node<>(data, predecessor, successor); // creating the Node for the new NameData we're adding
        predecessor.setNext(added);
        successor.setPrev(added);
        size++; // increasing the size
    }

    /**
     *
     * @param element the NameData we are trying to make the first in the DoublyLinkedList
     */
    public void addFirst(NameData element){
        addBetween(element, header, header.getNext());
    }

    /**
     *
     * @param element the NameData we are trying to make the last in the DoublyLinkedList
     */
    public void addLast(NameData element){
        addBetween(element, trailer.getPrev(), trailer);
    }

    /**
     *
     * @param element the Node we are trying to remove
     * @return the element we removed from the DoublyLinkedList
     */
    public NameData remove(Node<NameData> element){
        element.getPrev().setNext(element.getNext());
        element.getNext().setPrev(element.getPrev());
        size--; // reducing the size of the list by 1
        return element.getElement(); // returns the element we removed
    }

    /**
     *
     * @return the first element which we removed
     */
    public NameData removeFirst(){
        if(isEmpty()) return null;
        return remove(header.getNext());
    }

    /**
     *
     * @return the last element which we removed
     */
    public NameData removeLast(){
        if(isEmpty()) return null;
        return remove(trailer.getPrev());
    }
    // end of DoublyLinkedList required data

    /**
     *
     * @param inputName the NameData we are trying to insert in the right spot
     * @throws Exception throws an exception if the NameData was not inserted
     */
    public void insertAlpha(NameData inputName) throws Exception{
        if(isEmpty()){ // checks if the list is empty, then we just add the node
            addFirst(inputName);
            return; // ends this method, so we don't increment the first element added to the list
        }

        NameData alreadyExists = fetch(inputName.getBabyName());
        if(alreadyExists != null) { // checks if the name we want to add already exists in the DoublyLinkedList, then we just increment the occurrence instead of repeating the name
            alreadyExists.increment(inputName.getOccurrence());
        } else {
            Node<NameData> curr = header.getNext();
            int comparedValue = curr.getElement().compareTo(inputName); //checking where the Node should be
            //boolean enteredLoop = false;

            // while we haven't found the position of the node and the DoublyLinkedList has not been completely filtered through
            while (curr != trailer.getPrev() && comparedValue < 0) {
                //enteredLoop = true;
                curr = curr.getNext();
                comparedValue = curr.getElement().compareTo(inputName);
                if (comparedValue >= 0) {
                    addBetween(inputName, curr.getPrev(), curr);
                    return; // ends the method after the NameData has been added
                }
            }
            // adds the NameData element to the doubly LinkedList if it wasn't already added.
            // we don't worry about duplicates because after we add the NameData, we usually "return" -- end the method.
            if (comparedValue >= 0) addBetween(inputName, curr.getPrev(), curr); else addLast(inputName);
            // accounts for the corner case where the loop was never entered because the comparedValue gotten before the loop had a value >= 0. then we just add the NameData as usual
//            if (!enteredLoop) {
//                addBetween(inputName, curr.getPrev(), curr);
//            } else if(curr == trailer.getPrev()){ // accounts for corner case where we got to the end of the LinkedList and haven't
//                // inserted the NameData, but we can't insert it in the loop because curr.getNext() would point to null which would break the code
//                // so, we manually check where the NameData should be inserted here, at the end.
//                comparedValue = curr.getElement().compareTo(inputName);
//                if (comparedValue >= 0) addBetween(inputName, curr.getPrev(), curr); else addLast(inputName);
//            }
        }

    }

    /**
     *
     * @param name the name we are to get the NameData of
     * @return the NameData associated to the mame we received
     */
    public NameData fetch(String name){
        Node<NameData> curr = header.getNext();
        NameData value = null; // sets the value to null, so we return null if the NameData wasn't found
        while(curr != trailer){ // loops through the DoublyLinkedList until we get to the end
            if(curr.getElement().getBabyName().equals(name)){ // if the name matches, we set the value to the curr NameData
                value = curr.getElement();
                break; // ends the loop after finding the name
            }
            curr = curr.getNext(); // updates curr
        }
        return value;
    }

    /**
     *
     * @param name the name we are trying to get the position of
     * @return the position of the NameData element in the DoublyLinkedList
     */
    public int findPosition(String name){
        Node<NameData> curr = header.getNext();
        int position = -1; // sets the position to -1, so we return -1 if the name wasn't found
        int index = 0;
        while(curr != trailer){ // loops through the DoublyLinkedList until we get to the end
            if(curr.getElement().getBabyName().equals(name)){
                position = index;
                break; // ends the loop after finding the position of the name
            }
            index++; // updates the index
            curr = curr.getNext(); // updates curr
        }
        return position;
    }

    /**
     *
     * @return the total occurrence of all NameData in the DoublyLinkedList
     */
    public int totalOccurrence(){
        Node<NameData> curr = header.getNext();
        int total = 0;
        while(curr != trailer){ // loops through the DoublyLinkedList until we get to the end
            total += curr.getElement().getOccurrence(); // increments the total with all the occurrence of each NameData in the DoublyLinkedList
            curr = curr.getNext();
        }
        return total; // returns the total occurrence
    }
    /**
     *
     * @return a string with all the elements in the DoublyLinkedList
     */
    public String toString(){
        if(isEmpty()) return "This DoublyLinkedList is empty";
        Node<NameData> curr = header.getNext();
        StringBuilder toPrint = new StringBuilder();
        while(curr != trailer){ // loops through the DoublyLinkedList until we get to the end
            toPrint.append(curr.toString()).append("\n"); // adds each NameData in a new line
            curr = curr.getNext(); // updates curr
        }

        return toPrint.toString();
    }
}
