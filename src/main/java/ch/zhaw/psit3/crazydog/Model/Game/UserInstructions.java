package ch.zhaw.psit3.crazydog.Model.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>UserInstructions</h1>
 * Stores instructions that the user will see on the GUI so that he knows, what he has to do. <br>
 *
 * @author R. Somma
 * @version 1.0
 * @since April 2020
 */
public class UserInstructions {

    private static final int AMOUNTOFINSTRUCTIONS = 10;
    private static List<String> userInstructions = new ArrayList<>();

    /**
     * Function to add a new instruction for the user on the user interface
     *
     * @param instruction as String - Instruction to the user
     */
    public static void addNewInstruction(String instruction) {
        //new messages should always be stored on the top of the list
        userInstructions.add(0, instruction);

        //if List contains more than 10 elements, remove the List
        while (userInstructions.size() > AMOUNTOFINSTRUCTIONS) {
            userInstructions.remove(userInstructions.size() - 1);
        }
    }

    /**
     * Function to get all instructions for the user
     *
     * @return String Array - Array with all Instructions
     */
    public static List<String> getUserInstructions() {
        return userInstructions;
    }


}
