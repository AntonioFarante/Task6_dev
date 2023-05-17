package task6_dev.DbServises;


import task6_dev.DataBase.DataBase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabaseInitService {
    public static final String INIT_DB ="C:\\Users\\HP\\IdeaProjects\\Task6_dev\\app\\src\\main\\java\\task6_dev\\SqlFiles\\init_db.sql";

   public void initDb(DataBase database) {
       try {
           String sql = String.join("\n", Files.readAllLines(Paths.get(INIT_DB)));
           database.executeUpdate(sql);
       } catch (IOException e) {
           e.printStackTrace();
       }

   }
}
