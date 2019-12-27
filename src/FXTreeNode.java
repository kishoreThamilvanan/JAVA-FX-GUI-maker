/**
 * FXTreeNode Class contains the details of a Node of a tree.
 *      It contains the data of the node, details of the parent,
 *          and the children.
 */
public class FXTreeNode {

    private String text;// A String to store the Data of the node as a String.
    private ComponentType type;// Type of component of the tree(Enumeration).
    private FXTreeNode parent;// Parent Node.
    private FXTreeNode[] children;// Array of Children node of the Parent.
    final int maxChildren =10;// Maximum capacity of the children of each node.
    private int depth;// Depth of this node.
    private int cSize;//size of the children's array

    /**
     * Default constructor for initialising the node.
     */
    public FXTreeNode() {
        text = "";
        type = type.AnchorPane;
        children = new FXTreeNode[10];
        depth =0;
        cSize = 0;
    }

    /**
     * Method returns the size of the children's array of the node.
     *
     * @return
     * returns the size of the childrens array.
     */
    public int getcSize() {
        return cSize;
    }

    /**
     * Method sets the size of children's array of the node.
     *
     * @param cSize
     * An integer parameter entered by user to change the cSize.
     */
    public void setcSize(int cSize) {
        this.cSize = cSize;
    }

    /**
     * Method returns the depth of the particular node.
     *
     * @return
     * returns the depth.
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Method sets the user defined depth to the depth of the node.
     *
     * @param depth
     * user passed integer parameter to be set as the depth.
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

    /**
     * Method returns an array of children nodes of current node.
     *
     * @return
     * returns an array of Children nodes.
     */
    public FXTreeNode[] getChildren() {
        return children;
    }

    /**
     * Method helps to set the children array of the current node.
     *
     * @param c
     * Accepts an Array of children nodes to be set to the current node.
     */
    public void setChildren(FXTreeNode[] c) {
        children = c;
    }

    /**
     * Method to return the text present in the node.
     *
     * @return
     * returns the text.
     */
    public String getText() {
        return this.text;
    }

    /**
     * Method to set the user defined text on the node.
     *
     * @param text
     * accepts a String from the user as a parameter.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Method to return the Component type of the node.
     *
     * @return
     * returns the component type of the node.
     */
    public ComponentType getType() {
        return type;
    }

    /**
     * Method to set the Component type of the method to user preferred one.
     *
     * @param type
     * accepts the user defined Component type as a parameter.
     */
    public void setType(ComponentType type) {
        this.type = type;
    }

    /**
     * Method returns the parent of the current node
     *
     * @return
     * returns the parent of the current node.
     */
    public FXTreeNode getParent() {
        return parent;
    }

    /**
     * Method sets the parent of the given node.
     *
     * @param parent
     * Accepts a node as a parameter to set it as the parent.
     */
    public void setParent(FXTreeNode parent) {
        this.parent = parent;
    }

    /**
     * Method gets the mamximum number of children each node can have.
     *
     * @return
     * returns the maximum number oc children.
     */
    public int getMaxChildren() {
        return maxChildren;
    }

    /**
     * Method returns the details of the node in a String.
     *
     * @return
     * returns the details of the Node as a String.
     */
    public String toString() {
      if (this.getText().equals("") ||
                this.getText() == null)
           return  "" + this.getType() + ".\n";

        else
           return  "" + this.getType() + ": "
                    + this.getText() + ".\n";

    }
}
