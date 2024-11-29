package org.zerob.goldx;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootTest
@ActiveProfiles("test")
public class PostgreSQLConnectionTest {

    @Value("${spring.datasource.url}")
    private String URL;
    @Value("${spring.datasource.username}")
    private String USERNAME;
    @Value("${spring.datasource.password}")
    private String PASSWORD;

    @Test
    @DisplayName("데이터베이스 연결 테스트")
    public void ConnectionTest() throws Exception {
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); // DB 연결
        Assertions.assertNotNull(connection);
    }

}
