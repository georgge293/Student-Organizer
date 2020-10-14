/**
 * Custom Binary Search Tree implementation
 * @author ICS4U
 * @version Nov 2019
 */
import java.util.Stack;

/**
 * BinaryTree class
 * December 12 2019
 * George Li
 */
public class BinaryTree<E extends Comparable <E>>{
    private Node<E> root;
    
    //constructor
    public BinaryTree(){
        this.root = null;
    }
//------------------------------------------------------------------------------  
    //Adds the specified element to this tree if it is not already present.
    public boolean add(E item){
        if (this.root == null){//if root is null then tree is empty then simply create new root
            root = new Node<E>(item);
        }
        else {
            Node<E> currentNode = this.root;
            Node<E>nodeToInsert = new Node<E>(item);
            int comp = item.compareTo(currentNode.getItem());        
            while (comp < 0 && currentNode.getLeft() != null || //while the item is smaller and left child is not empty
                   comp > 0 && currentNode.getRight() != null){ //or the item is greater and right child is not empty
                //move one level down to left or to right
                if (comp < 0){currentNode = currentNode.getLeft();}
                else {currentNode = currentNode.getRight();}
                //compare the item with the current node
                comp = item.compareTo(currentNode.getItem());             
            }
            //insert the item to left or to right
            if (comp < 0){currentNode.setLeft(nodeToInsert );}
            else if (comp > 0){currentNode.setRight(nodeToInsert );}
            else {return false;} //the item is present and cannot be inserted
        }
        return true;
    }
//------------------------------------------------------------------------------   
//Removes the specified element from this tree if it is present.
    public void remove(E item){
        root = remove(root,item);
    }
    //recursive remove
    private Node<E> remove(Node <E>currentNode, E item){
        if(currentNode == null){//base case
            System.out.println("Couldn't find event with that date and time");
            return currentNode;
        }
        if(item.compareTo(currentNode.getItem()) == 0){//if currentNode is to be deleted(same date and time)
            if(currentNode.isLeaf()){//if  leaf (no children)
                return null;//simply make the node null if no children
            }
            else if(currentNode.getRight() == null){//if only left child exists
                return currentNode.getLeft();
            }
            else if(currentNode.getLeft() == null){//if only right child exists
                return currentNode.getRight();
            }
            else{//if two children
                E successor = getSuccessor(currentNode.getRight());//get inorder successor
                currentNode.setItem(successor);//set node to be deleted as inorder successor
                remove(currentNode.getRight(),successor);//remove succesor
            }
        }
        else if(item.compareTo(currentNode.getItem()) > 0){//move down to the right if item greater than value of currentNode
            currentNode.setRight(remove(currentNode.getRight(),item));//recursive call
        }
        else{
            currentNode.setLeft(remove(currentNode.getLeft(),item));//move down to the left if item is less than value of currentNode
        }
        return currentNode;
    }
    //returns value of inOrder successor used by recursive remove method if removed node has two children
    private E getSuccessor(Node<E> node){
    while(node.getLeft() != null){
            node = node.getLeft();
    }
    return node.getItem();
}
    //removes all nodes from the tree
    public void clear(){
        root = null;//clear entire tree by making root null
        System.out.println("Schedule Cleared!");
    }
    
    //returns size(number of nodes) in the tree
    public void size(){
        System.out.println(size(root));
    }
    private int size(Node <E> node){//recursive size
        if(node == null){//base case
            return 0;
        }
        else{
            return size(node.getLeft()) + 1 +size(node.getRight());
        }
    }
    //returns boolean value depending on if tree contains a node with that value
    public boolean contains(E item){
        if(contains(root,item) != null){//if return a non null node then it found the event
            return true;
        }
        return false;//returns null if couldnt find(doesn't contain)
    }
    
    private Node<E> contains(Node<E> node, E item){
        if(node == null){//base case
            return null;
        }
        else if(node.getItem().compareTo(item) == 0){//if match return node
            return node;
        }
        else if(node.getItem().compareTo(item) > 0){//if item less than node go down to the left
            return contains(node.getLeft(),item);
        }
        else{//if item greater than node go down to the right
            return contains(node.getRight(),item);
        }
    }
//------------------------------------------------------------------------------    
    //Returns String representation of the tree. Elements are in natural order.
    @Override
    public String toString(){
        if (this.root == null){return "You're calendar is empty ";}
        Stack<Node<E>> stack = new Stack<Node<E>>();
        Node<E> currentNode = this.root;
        String s = "";
        while (!stack.empty() || currentNode != null){
            if (currentNode != null){
                stack.push(currentNode);
                currentNode = currentNode.getLeft();
            }else {
                currentNode = stack.pop();
                s = s + currentNode.getItem().toString() +" \n";
                currentNode = currentNode.getRight();
            }
        }
        return  s.substring(0,s.length()-2);
    }
   
//------------------------------------------------------------------------------     
//  inner class Node  
//------------------------------------------------------------------------------  
    private class Node<T>{
        private T item;
        private Node<T> left;
        private Node<T> right;
        //Node constructor
        public Node(T item){
            this.item = item;
            this.left = null;
            this.right = null;
        } 
        //getters and setters
        public T getItem(){
            return this.item;
        }
        public void setItem(T item){
            this.item = item;
        }
        public Node<T> getLeft(){
            return this.left;
        }        
        public void setLeft(Node<T> node){
            this.left = node;
        }
        public Node<T> getRight(){
            return this.right;
        } 
        public void setRight(Node<T> node){
            this.right = node;
        }  
        //helper methods
        public boolean isLeaf(){
            return this.getLeft() == null && this.getRight() == null;//if it is a leaf(no children) then its children would be null
        }
    } //end of Node class 
} //end of BinaryTree class