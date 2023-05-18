package task6_dev.ClientServices;

import task6_dev.DataBase.DataBase;
import task6_dev.Entity.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

    private PreparedStatement createSt;
    private PreparedStatement getByIdSt;
    private PreparedStatement setNameSt;
    private PreparedStatement deleteByIdSt;
    private PreparedStatement getIdSt;
    private PreparedStatement selectAllSt;

    public ClientService(DataBase dataBase) throws SQLException {

        Connection conn = dataBase.getConnection();

        createSt = conn.prepareStatement(
                "INSERT INTO client (name) VALUES (?)"
        );
        getByIdSt = conn.prepareStatement(
                "SELECT id, name FROM client WHERE id = ?"
        );
        getIdSt = conn.prepareStatement(
                "SELECT max(id) AS maxId FROM client"
        );
        setNameSt = conn.prepareStatement(
                "UPDATE client SET name = ? WHERE id = ?"
        );
        deleteByIdSt = conn.prepareStatement(
                "DELETE FROM client WHERE id = ?"
        );
        selectAllSt = conn.prepareStatement(
                "SELECT id, name FROM client"
        );

    }

    public long create(String name) throws SQLException {
        if (name.length() <= 100 && name.length() > 0) {
            createSt.setString(1, name);
            createSt.executeUpdate();
        } else {
            throw new IllegalArgumentException();
        }

        long id;
        try (ResultSet resultSet = getIdSt.executeQuery()) {
            resultSet.next();
            id = resultSet.getLong("maxId");
        }

        return id;
    }

    public String getById(long id) throws SQLException {
        if (id > 0) {
            getByIdSt.setLong(1, id);
            getByIdSt.executeQuery();
        } else {
            throw new IllegalArgumentException();
        }
        String response;
        try(ResultSet result = getByIdSt.executeQuery()) {
            result.next();
            long resultId = result.getLong("id");
            String name = result.getString("name");
            response = resultId + " " + name;
        }
        return response;
    }

    public void setName(long id, String name) throws SQLException {
        if (id > 0 && name.length() <= 100 && name.length() > 0) {
            setNameSt.setLong(2, id);
            setNameSt.setString(1, name);

            setNameSt.executeUpdate();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void deleteById(long id) throws SQLException {
        if (id > 0) {
            deleteByIdSt.setLong(1, id);
            deleteByIdSt.executeUpdate();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public List<Client> listAll() {
        List<Client> result = new ArrayList<>();

        try(ResultSet rs = selectAllSt.executeQuery()) {
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                result.add(new Client(id, name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
