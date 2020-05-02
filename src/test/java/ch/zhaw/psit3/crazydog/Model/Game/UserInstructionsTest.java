package ch.zhaw.psit3.crazydog.Model.Game;

import ch.zhaw.psit3.crazydog.Model.GameField.GameField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserInstructionsTest {

    /**
     * Add one Instruction to the class, before every test
     */
    @BeforeEach
    void setup() {
        UserInstructions.addNewInstruction("Test1");
    }

    /**
     * test if the list is correctly returned
     */
    @Test
    void testGetUserInstructions() {
        assertEquals("Test1", UserInstructions.getUserInstructions().get(0));
    }

    /**
     * test that the new entry is always added on the index 0
     */
    @Test
    void testAddNewInstructionToAddInstructionOnIndexZero() {
        UserInstructions.addNewInstruction("Test2");
        assertEquals("Test2", UserInstructions.getUserInstructions().get(0));
    }

    /**
     * test that the list will delete old entries after there are more than 10 entries.
     */
    @Test
    void testAddNewInstructionDeleteAfter10Instructions() {
        UserInstructions.addNewInstruction("Test2");
        UserInstructions.addNewInstruction("Test3");
        UserInstructions.addNewInstruction("Test4");
        UserInstructions.addNewInstruction("Test5");
        UserInstructions.addNewInstruction("Test6");
        UserInstructions.addNewInstruction("Test7");
        UserInstructions.addNewInstruction("Test8");
        UserInstructions.addNewInstruction("Test9");
        UserInstructions.addNewInstruction("Test10");
        UserInstructions.addNewInstruction("Test11");

        List<String> userInstructions = UserInstructions.getUserInstructions();

        assertEquals("Test11", userInstructions.get(0));
        assertEquals("Test2", userInstructions.get(userInstructions.size()-1));
    }

    /**
     * Test that the List only keeps 10 entries after 15 adds.
     */
    @Test
    void testAddNewInstructionMaximumOf10Instructions() {
        UserInstructions.addNewInstruction("Test2");
        UserInstructions.addNewInstruction("Test3");
        UserInstructions.addNewInstruction("Test4");
        UserInstructions.addNewInstruction("Test5");
        UserInstructions.addNewInstruction("Test6");
        UserInstructions.addNewInstruction("Test7");
        UserInstructions.addNewInstruction("Test8");
        UserInstructions.addNewInstruction("Test9");
        UserInstructions.addNewInstruction("Test10");
        UserInstructions.addNewInstruction("Test11");
        UserInstructions.addNewInstruction("Test12");
        UserInstructions.addNewInstruction("Test13");
        UserInstructions.addNewInstruction("Test14");
        UserInstructions.addNewInstruction("Test15");

        List<String> userInstructions = UserInstructions.getUserInstructions();

        assertEquals("Test15", userInstructions.get(0));
        assertEquals("Test6", userInstructions.get(userInstructions.size()-1));
        assertEquals(10, userInstructions.size());
    }

}
