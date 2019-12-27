/**
 * Class VoidInTheArrayException is custom Exception class
 *      which is called when the children array has holes in it.
 */
public class VoidInTheArrayException extends Exception{

    /**
     * Method is called everytime when the user tries to add a child to the
     *      children array leaving a hole in the array.
     */
    public void voidInTheArrayException(){
        System.out.print("Invalid index position as it creates a void in " +
                "the array.");
    }
}
