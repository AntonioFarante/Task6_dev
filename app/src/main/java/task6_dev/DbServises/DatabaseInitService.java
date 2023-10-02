package task6_dev.DbServises;


import org.flywaydb.core.Flyway;
import task6_dev.DataBase.DataBase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabaseInitService {

   public void initDb(DataBase database) {
       // Create the Flyway instance and point it to the database
       Flyway flyway = Flyway
               .configure()
               .dataSource("jdbc:h2:./11113_db", null, null)
               .load();

       // Start the migration
       flyway.migrate();
   }
}
