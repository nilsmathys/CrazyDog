package ch.zhaw.psit3.crazydog.Model.Message;

import ch.zhaw.psit3.crazydog.Model.Game.Move;
import ch.zhaw.psit3.crazydog.Model.GameField.GameField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageTest {
    private Message message;
    @BeforeEach
    void setup() {
        message = new Message("this is a test message");
    }

    @Test
    void testGetMessage() {
        assertEquals("this is a test message",message.getMessage());
    }

}
