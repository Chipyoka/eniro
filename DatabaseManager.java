import java.sql.*;

public class DatabaseManager {
    private String databaseUrl;  // Path to the SQLite database file
    private Connection connection;

    // Constructor
    public DatabaseManager(String databaseUrl) {
        this.databaseUrl = databaseUrl;
        this.connection = null;
    }

    // Method to connect to the SQLite database
    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseUrl);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to disconnect from the SQLite database
    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection to SQLite has been closed.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // CREATE Operation: Insert a record into the database
    public void insert(String table, String[] columns, String[] values) {
        StringBuilder query = new StringBuilder("INSERT INTO ");
        query.append(table).append(" (");

        // Appending column names
        for (int i = 0; i < columns.length; i++) {
            query.append(columns[i]);
            if (i < columns.length - 1) {
                query.append(", ");
            }
        }

        query.append(") VALUES (");

        // Appending values placeholders
        for (int i = 0; i < values.length; i++) {
            query.append("?");
            if (i < values.length - 1) {
                query.append(", ");
            }
        }
        query.append(");");

        try (PreparedStatement pstmt = connection.prepareStatement(query.toString())) {
            // Setting values dynamically
            for (int i = 0; i < values.length; i++) {
                pstmt.setString(i + 1, values[i]);
            }

            pstmt.executeUpdate();
            System.out.println("Record inserted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // READ Operation: Fetch records from the database
    public ResultSet fetch(String query) {
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // UPDATE Operation: Update a record in the database
    public void update(String table, String[] columns, String[] values, String condition) {
        StringBuilder query = new StringBuilder("UPDATE ");
        query.append(table).append(" SET ");

        // Appending columns and their new values
        for (int i = 0; i < columns.length; i++) {
            query.append(columns[i]).append(" = ?");
            if (i < columns.length - 1) {
                query.append(", ");
            }
        }

        query.append(" WHERE ").append(condition);

        try (PreparedStatement pstmt = connection.prepareStatement(query.toString())) {
            // Setting values dynamically
            for (int i = 0; i < values.length; i++) {
                pstmt.setString(i + 1, values[i]);
            }
            pstmt.executeUpdate();
            System.out.println("Record updated successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // DELETE Operation: Delete a record from the database
    public void delete(String table, String condition) {
        String query = "DELETE FROM " + table + " WHERE " + condition;

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.executeUpdate();
            System.out.println("Record deleted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int countPupils() {
    String query = "SELECT COUNT(*) FROM Pupil";
    try {
        Statement stmt = connection.createStatement();
        ResultSet result = stmt.executeQuery(query);
        if (result.next()) {
            return result.getInt(1);
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return 0;
}

public void insertPupil (String query){
    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
        pstmt.executeUpdate();
        System.out.println("Pupil Record inserted successfully.");
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}
}
