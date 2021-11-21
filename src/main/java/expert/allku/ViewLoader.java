package expert.allku;

import com.zaxxer.hikari.HikariDataSource;
import io.micronaut.context.annotation.Value;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import jakarta.inject.Singleton;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.sql.Connection;
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
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(this.url);
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);

        try {
            Connection connection = dataSource.getConnection();
            ScriptRunner runner = new ScriptRunner(connection);


            Reader inputString = new StringReader(
                    "drop table if exists v_locations;");
            BufferedReader dropTable = new BufferedReader(inputString);

            Reader createView = new BufferedReader(
                    new FileReader("./sql/v_locations.sql")
            );

            runner.runScript(dropTable);
            runner.runScript(createView);

            connection.close();
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("View created");
    }
}
