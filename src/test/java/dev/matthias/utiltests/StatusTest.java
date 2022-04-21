package dev.matthias.utiltests;

import dev.matthias.utilities.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StatusTest {

    @Test
    @DisplayName("Should get status as string")
    void shouldGetStatusAsString() {
        Assertions.assertEquals("PENDING", Status.PENDING.toString());
        Assertions.assertEquals("DENIED", Status.DENIED.toString());
        Assertions.assertEquals("APPROVED", Status.APPROVED.toString());
    }

}
