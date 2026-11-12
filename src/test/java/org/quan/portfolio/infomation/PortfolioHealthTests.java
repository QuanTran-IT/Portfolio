package org.quan.portfolio.infomation;

import org.junit.jupiter.api.Test;
import org.quan.portfolio.infomation.config.ProfileProperties;
import org.quan.portfolio.infomation.controller.PortfolioApiController;
import org.quan.portfolio.infomation.service.ProjectService;
import org.quan.portfolio.infomation.service.SkillService;
import org.quan.portfolio.infomation.service.impl.PortfolioServiceImpl;
import org.springframework.boot.SpringBootVersion;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PortfolioHealthTests {
    private final DataSource dataSource = mock(DataSource.class);
    private final PortfolioServiceImpl service = new PortfolioServiceImpl(
            new ProfileProperties("Quan", "Developer", "About", "test@example.com", "github"),
            mock(SkillService.class), mock(ProjectService.class), dataSource
    );
    private final PortfolioApiController controller = new PortfolioApiController(service);

    @Test
    void healthyDatabaseReturnsActualFrameworkVersionAndClosesConnection() throws SQLException {
        Connection connection = mock(Connection.class);
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.isValid(3)).thenReturn(true);

        var response = controller.getHealth();

        assertEquals(200, response.getStatusCode().value());
        assertEquals("CONNECTED", response.getBody().databaseStatus());
        assertEquals("Spring Boot " + SpringBootVersion.getVersion(), response.getBody().framework());
        verify(connection).close();
    }

    @Test
    void unavailableDatabaseReturnsServiceUnavailable() throws SQLException {
        when(dataSource.getConnection()).thenThrow(new SQLException("Unavailable"));

        var response = controller.getHealth();

        assertEquals(503, response.getStatusCode().value());
        assertEquals("DOWN", response.getBody().status());
        assertEquals("DISCONNECTED", response.getBody().databaseStatus());
    }

    @Test
    void invalidConnectionReturnsServiceUnavailableAndClosesConnection() throws SQLException {
        Connection connection = mock(Connection.class);
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.isValid(3)).thenReturn(false);

        assertEquals(503, controller.getHealth().getStatusCode().value());
        verify(connection).close();
    }
}
