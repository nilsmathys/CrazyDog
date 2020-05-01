package ch.zhaw.psit3.crazydog.Model.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * This class stores all user instructions that are shown on the user Interface
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
        userInstructions.add(0,instruction);

        //if List contains more than 10 elements, remove the List
        while(userInstructions.size() > AMOUNTOFINSTRUCTIONS) {
            userInstructions.remove(userInstructions.size()-1);
        }
    }

    /**
     * Function to get all instructions for the user
     *
     * @return String Array - Array with all Instructions
     */
    public static List<String> getUserInstructions () {
        return userInstructions;
    }

    /**
     * Display all User Instructions on the console
     */
    public static void printList() {
        int i = 0;
        for (String instruction: userInstructions) {
            System.out.println(i + ": "+instruction);
            i++;
        }
    }

}
