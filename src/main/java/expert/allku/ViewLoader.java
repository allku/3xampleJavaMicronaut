package expert.allku;

import com.zaxxer.hikari.HikariDataSource;
import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.Value;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import jakarta.inject.Singleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Singleton
public class ViewLoader implements ApplicationEventListener<ServerStartupEvent> {

    @Value("${datasources.default.username}")
    String username;
    @Value("${datasources.default.password}")
    String password;
    @Value("${datasources.default.url}")
    String url;
    @Value("${datasources.default.driverClassName}")
    String driverClassName;

    @Override
    public void onApplicationEvent(ServerStartupEvent event) {
        HikariDataSource datasource = new HikariDataSource();
        datasource.setJdbcUrl(this.url);
        datasource.setDriverClassName(this.driverClassName);
        datasource.setUsername(this.username);
        datasource.setPassword(this.password);

        try {
            Connection connection = datasource.getConnection();
            String selectSQL = "SELECT version() as version";
            PreparedStatement prepStmt = null;
            prepStmt = connection.prepareStatement(selectSQL);

            ResultSet rs = prepStmt.executeQuery();
            while(rs.next()){
                System.out.println("Version: " + rs.getString("version"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("View created");
    }
}
