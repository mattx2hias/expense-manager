package dev.matthias.utiltests;

import dev.matthias.utilities.ConnectionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class ConnectionTest {
    @Test
    void canConnect() {
        Connection conn = ConnectionUtil.createConnection();
        Assertions.assertNotNull(conn);
    }
}
