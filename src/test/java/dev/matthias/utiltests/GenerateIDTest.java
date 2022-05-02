package dev.matthias.utiltests;

import dev.matthias.utilities.GenerateID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GenerateIDTest {

    @Test
    @DisplayName("Should generate unique random 4 digit number")
    void shouldGenerateUniqueRandom4DigitNumber() {
        Assertions.assertNotEquals(GenerateID.generateRandomID(), GenerateID.generateRandomID());
    }
}
