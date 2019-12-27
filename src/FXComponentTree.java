import java.io.*;
import java.util.Scanner;

/**
 * Class <code>FXComponentTree</code> serves as a tree manager for the FXTreeNode.
 */
public class FXComponentTree extends Exception{

    private static FXTreeNode root;// Root of the tree.
    private static FXTreeNode cursor;// cursor of the tree.

    private int treeDepth=0;// depth of the tree.
    Scanner input = new Scanner(System.in);

    public FXComponentTree(){
        FXTreeNode temporary = new FXTreeNode();
        temporary.setType(ComponentType.AnchorPane);
        root = temporary;
        cursor = root;
    }
    /**
     * Method returns the root of the tree.
     *
     * @return
     * returns the root of the tree.
     */
    public FXTreeNode getRoot() {
        return root;
    }

    /**
     * Method returns the cursor of the tree.
     *
     * @return
     * returns the cursor of the tree.
     */
    public FXTreeNode getCursor() {
        return cursor;
    }

    /**
     * Method sets the cursor to the desired node of the Tree.
     *
     * @param node
     * user passes node to which the cursor is set to.
     */
    public void setCursor(FXTreeNode node) {
        this.cursor = node;
    }

    /**
     * Method sets the cursor to the root of the tree.
     */
    public void cursorToRoot() {
        cursor = root;
    }

    /**
     * Method checks the validity of the index and returns a boolean value.
     *
     * @param i
     * User passed index which is to be checked for validity.
     *
     * @return
     * returns a boolean validity based on the validity.
     */
    public static boolean indexCheck(int i) {
        return (i>=0 && i<10);
    }

    /**
     * Method deletes the child at the
     *      specified index and all of its children too.
     *
     * @param index
     * User passed parameter to specify the node to be deleted.
     */
    public void deleteChild(int index) {

        if(!indexCheck(index))
            throw new IndexOutOfBoundsException();

        else {

             FXTreeNode[] temp = cursor.getChildren();

             // if there is only one element to be deleted.
             if(cursor.getcSize()==1) {
                 cursor.setChildren(null);
                 return;
             }

         cursor.setcSize(cursor.getcSize()-1);

         for(int i=index;i<temp.length-1;i++)
             temp[i] = temp[i+1];

        }
    }

    /**
     * Method adds a user passed node to the tree at the specified index.
     *
     * @param index
     * User passed parameter to specify the node to be deleted.
     *
     * @param node
     * User passed node to be added.
     *
     * @throws Exception
     * Throws an exception when there's a void in the Array.
     */
    public void addChild(int index, FXTreeNode node) throws Exception {

        FXTreeNode[] t = new FXTreeNode[10];

        node.setDepth(cursor.getDepth()+1);

        try {
            if (cursor.getcSize() == 0) {
                t[0] = node;
                cursor.setChildren(t);
                cursor.setcSize(cursor.getcSize() + 1);
                return;
            }
        }catch(NullPointerException e){return;};


        t = cursor.getChildren();
        if(indexCheck(index)) {
            if (index  > cursor.getcSize())
                throw new VoidInTheArrayException();


            else {

                if (index < cursor.getcSize())
                    for (int i = t.length-1; i > index; i--) {
                        t[i] = t[i - 1];
                    }

                t[index] = node;
                cursor.setcSize(cursor.getcSize() + 1);
                cursor.setChildren(t);
            }
        }

    }

    /**
     * Method sets the user passed text to the node present at the cursor.
     *
     * @param text
     */
    public void setTextAtCursor(String text){
        cursor.setText(text);
    }

    /**
     * Method sets the cursor to the specified child of the node.
     *
     * @param index
     * User passed index to specify the child to set the cursor.
     */
    public void cursorToChild(int index) {

        if(cursor.getcSize() == 0 || index>=cursor.getcSize()) {
            System.out.print("There is no child.");
            return;
        }

          cursor = cursor.getChildren()[index];
    }

    /**
     * Method sets the cursor to the Parent of the node.
     */
    public void cursorToParent(){
        cursor = cursor.getParent();
    }


    /**
     * Method returns the Component type by checking the String passed to it.
     *
     * @param s
     * User entered parameter which contains the componen type in a String.
     *
     * @return
     * returns the Component type corresponding to the passed String.
     */
    public ComponentType checkComponent(String s){
        if(s.equalsIgnoreCase("VBox"))
            return ComponentType.VBox;

        else
            if(s.equalsIgnoreCase("HBox"))
            return ComponentType.HBox;

        else
            if(s.equalsIgnoreCase("Label"))
            return ComponentType.Label;

        else
            if(s.equalsIgnoreCase("Button"))
            return ComponentType.Button;

        else
            if(s.equalsIgnoreCase("TextArea"))
            return ComponentType.TextArea;

        else
            if(s.equalsIgnoreCase("AnchorPane"))
            return ComponentType.AnchorPane;

        else
            return null;

    }

    /**
     * Method reads and interprets the text from the file.
     *      thereby, creates an tree from the given information in the file.
     *
     * @param fileName
     * User passed filename from which the data should be read.
     *
     * @return
     * returns the tree made from the read data.
     *
     * @throws Exception
     * throws Exception when there's no data in the file.
     */
    public FXComponentTree readFromFile(String fileName)throws Exception{

        FXComponentTree tree = new FXComponentTree();
        FileInputStream in = new FileInputStream("C:\\Users\\" +"Kishore " +
                "Thamilvanan\\IdeaProjects\\Homework - 5\\"+fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;

        try{


                int flag=0,i=0,j=0;

                line = br.readLine();
                /*
                * splitting the line into Strings to identify the
                *      whether it is a root or node or button.
                */
                String[] s = line.split(" ");

                        FXTreeNode t = new FXTreeNode();
                        t.setParent(null);
                        t.setType(checkComponent(s[1]));
                        tree.root = t;
                        tree.cursor = t;
                        t.setText("");
                        t.setDepth(0);
                        t.setcSize(0);

            while((line = br.readLine())!= null)
            {
                s = line.split(" ");

                String[] n = s[0].split("-");

                tree.cursorToRoot();

                for(int k =1;k<n.length-1;k++) {
                    tree.cursorToChild(Integer.parseInt(n[k]));
                }


                FXTreeNode o = new FXTreeNode();

                o.setDepth(cursor.getDepth()+1);
                o.setParent(cursor);
                o.setType(checkComponent(s[1]));

                if(s.length>=3) {
                    if(s.length>3)
                        for (int k = 3; k < s.length; k++)
                            s[2] += " "+s[k];

                    o.setText(s[2]);
                }


                else
                    o.setText("");

                tree.addChild(Integer.parseInt(n[n.length-1]),o);
                tree.cursorToRoot();


            }
            System.out.print("\nThe file is loaded.");
            br.close();
        }catch( IOException e){

        };


    return tree;

    }

    /**
     * Method acts as ahelper method for the write to file method.
     *
     * @param node
     * tree node which is to be written.
     *
     * @param p
     * print writer
     */
    public void helperWriter(FXTreeNode node, PrintWriter p){

        for(int i=node.getDepth();i>0;i--)
            p.write("\t");

        p.write( node.toString());

        for(FXTreeNode child: node.getChildren()){
            if(child != null)
                helperWriter(child,p);
        }

    }


    /**
     * Method writes the data of the Specified tree on to the specified file.
     *
     * @param fileName
     * User passed filename in which the data has to be written.
     *
     * @param tree
     * User passed tree which is to be written onto the file.
     */
    public void write(String fileName, FXComponentTree tree) throws FileNotFoundException {

        try{
            PrintWriter writer = new PrintWriter("C:\\Users\\" + "Kishore " +
                    "Thamilvanan\\IdeaProjects\\Homework - 5\\"+ fileName);

            tree.helperWriter(tree.getRoot(),writer);

            writer.close();
        }catch(FileNotFoundException e){
            System.out.print("\nFile not found.");
        };
    }

    /**
     * Method acts as a helper method to the saveToFile method.
     *
     * @param tree
     * User passed tree which is to be written onto the file.
     */
    public void writeToFile(FXComponentTree tree)throws FileNotFoundException {
        tree.write(input.next(),tree);
    }

    String str ="";
    /**
     * Method returns a String containing the details of the tree.
     *
     * @param node
     * User passed node from which the tree is printed.
     *
     * @return
     * returns a String of details of the tree.
     */
    public void toString(FXTreeNode node){

     for(int i=node.getDepth();i>0;i--)
            System.out.print("\t");

        if(node == cursor)
            System.out.print("==>");
        else
            System.out.print("+--");

        System.out.print(node.toString());

        for(FXTreeNode child: node.getChildren()){
            if(child != null)
                toString(child);
        }
//
//
    }
}
