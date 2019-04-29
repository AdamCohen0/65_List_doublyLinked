/**
  Represent a list, implemented in a chain of nodes
 */

public class List_inChainOfNodes{
    private Node headSentinel;
    private Node backSentinel;

     
    /**
      Construct an empty list
     */
    public List_inChainOfNodes() {
        headSentinel = new Node( null, null, null);
        backSentinel = headSentinel;
    }

    /**
      @return the number of elements in this list
     */
    public int size() {
        // recursive approach seems more perspicuous
        return size( headSentinel);
    }

    // recursively-called helper
    private int size( Node startingAt) {
        Node next = startingAt.getNextNode();
        if( next == null) return 0;
        else return 1+ size( next);
    }


     /**
       @return a string representation of this list,
       format:
           # elements [element0,element1,element2,]
      */
    // public String toString() {
        // String stringRep = size() + " elements [";

        // for( Node node = headSentinel.getNextNode()
           // ; node != null
           // ; node = node.getNextNode() )
            // stringRep += node.getCargo() + ",";
        // return stringRep + "]";
    // }


    /**
      Append @value to the head of this list.

      @return true, in keeping with conventions yet to be discussed
     */
     public boolean addAsHead( Object val) {
        headSentinel.setNextNode(
          new Node( val, headSentinel.getNextNode()));
        return true;
     }
     
     public boolean addAsBack( Object val) {
         backSentinel.setPreviousNode(
            new Node( val, backSentinel.getPreviousNode()));
            return true;
     }


    /**
      @return a reference to the node before
              the node at @index
     */
    private Node getNodeBefore( int index) {
        /* iterate through the chain, up to the node
           that holds a reference to the desired node */
           
        Node node;
        int upTo;  // comma operator precludes declaration in FOR
        for( upTo = 0   , node = headSentinel
           ; upTo < index
           ; upTo ++     , node = node.getNextNode()
           )
           ;  // null loop body since all the action is in the FOR
        return node;
    }
    
    private Node getNodeAfter (int index) {
        Node node;
        int upTo;
        for (upTo = size() + 1, node = backSentinel;
             upTo > index;
             upTo -- , node = node.getPreviousNode()
             );
        return node;
    }


    /**
      @return a reference to the node @index
     */
    private Node getNode( int index) {
        return getNodeBefore( index).getNextNode();
    }
    
    private Node getNodeBackward( int index) {
        return getNodeAfter( index).getPreviousNode();
    }

    // accessors
    /**
      @return element @index from this list
      precondition: @index is within the bounds of the array.
          (Having warned the user about this precondition,
           you should NOT complicate your code to check
           whether user violated the condition.)
     */
    public Object get( int index ) {
        return getNodeBackward( index).getCargo();
    }


    /**
      Set value at @index to @newValue

      @return old value at @index
      @precondition: @index is within the bounds of this list.
     */
    // public Object set( int index, Object newValue ) {
        // return getNode( index).setCargo( newValue);
    // }
    
    public Object set( int index, Object newValue ) {
        return getNodeBackward( index).setCargo( newValue);
    }

    /**
      Insert @value at position @index in this list.

      Shift the element currently at that position (if any)
      and any subsequent elements to the right
      (that is, increase the index associated with each).
     */
    public boolean add( int index, Object value) {
        Node newNode = new Node( value);
        Node afterNew = /* the node that should follow newNode
          in the augmented list */
          getNodeBefore( index).setNextNode( newNode);
        newNode.setNextNode( afterNew);
        return true;
    }


     /**
      Remove the element at position @index in this list.

      Shift any subsequent elements to the left (that is,
      decrease the index associated with each).

      @return the value that was removed from the list
     */
    public Object remove( int index) {
        Node after = getNodeAfter( index);
        Node ax = after.getPreviousNode();
        Object saveForReturn = ax.getCargo();
        after.setPreviousNode( ax.getPreviousNode());
        return saveForReturn;
    }
    
        /**
      Demo use of links to previous Nodes.

      @return a string representation of this list,
              iterating through the list
              from tail to head.
      format, using ` as separator
          [element0`element1`element2`]
     */
    public String toString() {
        String stringRep = "tail-first [";

        for( Node node =
                 backSentinel;
                 node != null;
                 node = node.getPreviousNode()
           )
            stringRep += node.getCargo() + "`";
        return stringRep + "]";
    }
    
}