package dev.matthias.utiltests;

import dev.matthias.utilities.ConnectionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class ConnectionTest {

    @Test
    @DisplayName("Should connect to DB")
    void shouldConnectToDb() {
        Connection conn = ConnectionUtil.createConnection();
        Assertions.assertNotNull(conn);
    }

}
