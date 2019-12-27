import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class FXGuiMaker provide all the UI Funtions of the assignment.
 *    It takes a text file and generates a FXComponentTree and manipulates it.
 */
public class FXGuiMaker {

    /**
     * tree which is to be manipulated in the program.
     */
    public static FXComponentTree tree = new FXComponentTree();

    /**
     * Method to print all the UI Functions available to the user.
     */
    public static void UIFuntions(){

        System.out.print("\n\tL) Load from the file.");
        System.out.print("\n\tP) print tree.");
        System.out.print("\n\tC) Move cursor to a child node.");
        System.out.print("\n\tR) Move cursor to root.");
        System.out.print("\n\tA) Add a child.");
        System.out.print("\n\tE) Edit text of cursor.");
        System.out.print("\n\tD) Delete child.");
        System.out.print("\n\tS) Save to file.");
        System.out.print("\n\tQ) Quit.");
        System.out.print("\nEnter a option: ");
    }

    /**
     * Method processes all the UI Functions of the tree.
     *
     * @param c
     * User chosen choice is passes as a parameter.
     *
     * @throws Exception
     * throws Exception when the file is not found.
     */
    public static void processingMethod(char c) throws Exception{

        Scanner input = new Scanner(System.in);

        /**
         * Loading a saved file.
         */
        if(c == 'L' || c == 'l') {
            System.out.print("\nPlease enter the file name: ");
            String fName = input.next();

            try {
               tree =  tree.readFromFile(fName);
            }catch(FileNotFoundException e){
                System.out.print(fName+" Not Found.");
            };

        }

        /**
         * Printing the Tree in formatted way.
         */
        else if(c == 'P' || c == 'p') {

            System.out.print("\n\nThe tree is as follows:\n");
            tree.toString(tree.getRoot());
        }

        /**
         * Setting the cursor to the indicated child of the current node.
         */
        else if(c == 'c' || c == 'C') {

            System.out.print("\nEnter the Index number of the child" +
                    "(Starting from 1): ");
            int index = input.nextInt()-1;

            tree.cursorToChild(index);
            System.out.print("\nThe cursor has been set to child.");
        }

        /**
         * Adding a new child to the cuurent node.
         */
        else if(c == 'a' || c == 'A') {
            FXTreeNode node = new FXTreeNode();

            System.out.print("\nSelect a Component Type" +
                    "(H - HBox, V - VBox, T - TextArea, B - Button, " +
                        "L - Label): ");

            char t = input.next().charAt(0);

            switch(t){
                case 'h':
                case 'H':   node.setType(ComponentType.HBox);
                            break;

                case 'v':
                case 'V':   node.setType(ComponentType.VBox);
                            break;

                case 'T':
                case 't':   node.setType(ComponentType.TextArea);
                            break;

                case 'B':
                case 'b':   node.setType(ComponentType.Button);
                            break;

                case 'L':
                case 'l':   node.setType(ComponentType.Label);
                    break;
            }

            if(t=='h'||t=='H'||t=='h'||t=='H') {
                node.setText("");
            }

            else {
                System.out.print("\nEnter the Text: ");
                node.setText(input.next());
            }

            System.out.print("\nEnter the index(starting from 1): ");
            tree.addChild(input.nextInt()-1,node);
            System.out.print("\nThe node has been added to the tree.");


        }

        /**
         * Setting the cursor to parent of the current node.
         */
        else if(c == 'u' || c == 'U') {

            tree.cursorToParent();
            System.out.print("\nThe cursor is set to the parent.");

        }

        /**
         * Changing the text on the node at the cursor.
         */
        else if(c == 'e' || c == 'E') {

            System.out.print("Enter the Text you want to display: ");
            tree.setTextAtCursor(input.next());

            System.out.print("\nthe Text a the cursor is edited.");
        }

        /**
         * Deletion of the specified child.
         */
        else if(c == 'd' || c == 'D') {
            System.out.print("\nEnter the index number of the child to be " +
                    "deleted(starting from 1): ");
            tree.deleteChild(input.nextInt()-1);

            System.out.print("\nThe Node at the index is deleted.");

        }

        /**
         * The cursor is set to root.
         */
        else if(c == 'r' || c == 'R') {

            tree.cursorToRoot();
            System.out.print("\nThe cursor is set to the root.");
        }

        /**
         * When the user wants to save the file.
         */
        else if(c == 'S' || c == 's') {

            System.out.print("\nEnter the File name: ");
            tree.writeToFile(tree);

            System.out.print("\nThe File is saved.");
        }

        /**
         * when the user wants to quit the program.
         */
        else if(c == 'q' || c == 'Q') {
            System.out.print("Cutting down all the trees for firewood." +
                    "\nYou know the winter is coming!");
            System.exit(0);

        }

        else {
            System.out.print("\nPlease enter a vaild choice from the menu.");
        }


    }

    /**
     * Main Method to call all the funtions.
     *
     * @param args
     * arguments entered by the user.
     *
     * @throws Exception
     * throws Exception when there's an Exception.
     */
    public static void main(String[] args) throws Exception{

        Scanner input = new Scanner(System.in);
        char choice;// choice of the user
        System.out.print("Welcome to counterfeit SceneBuilder.");

        do {
            System.out.print("\n\n\t\t\tMenu:");
            UIFuntions();
            choice = input.next().charAt(0);
            processingMethod(choice);
        }while(choice !='q' || choice !='Q');


    }
}
