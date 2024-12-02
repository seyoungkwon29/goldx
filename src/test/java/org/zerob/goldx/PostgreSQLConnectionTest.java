package org.zerob.goldx;

import org.assertj.core.api.Fail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.*;

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

    @Test
    @DisplayName("DB 연결 및 특정 ID의 이름 검증")
    public void FindByIdTest() throws Exception {
        // Given
        try {
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement pre = con.createStatement();
            ResultSet rs = pre.executeQuery("select * from member where id = 1");

            String username = null;
            if (rs.next()) {
                username = rs.getString("username");
            }
            Assertions.assertEquals("Tim", username);
        } catch (SQLException e) {
            Fail.fail("테스트에 예외가 발생했습니다." + e.getMessage());
        }
    }
}