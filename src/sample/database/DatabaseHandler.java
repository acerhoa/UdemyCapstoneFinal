package sample.database;

import sample.controller.AdditemController;
import sample.model.Task;
import sample.model.User;

import java.sql.*;

public class DatabaseHandler extends Configs{
    Connection dbConnection;
    public Connection getDbConnection() throws SQLException, ClassNotFoundException {
        String connectionString = "jdbc:mysql://" + dbHost + "/" + dbName;
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString,dbUser,dbPass);
        return dbConnection;
    }
//    Write to DB
    public void signUpUser(User user){
        String insert = "INSERT INTO " + Constant.USERS_TABLE + "(" +
                Constant.USERS_FIRSTNAME + "," +
                Constant.USERS_LASTNAME + "," +
                Constant.USERS_USERNAME + "," +
                Constant.USERS_PASSWORD + "," +
                Constant.USERS_LOCATION + "," +
                Constant.USERS_GENDER + ")" +
                "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1,user.getFirstname());
            preparedStatement.setString(2,user.getLastname());
            preparedStatement.setString(3,user.getUsername());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.setString(5,user.getLocation());
            preparedStatement.setString(6,user.getGender());
//            update does not return
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addTask(Task task){
        String insert = "INSERT INTO " + Constant.TASKS_TABLE + "(" + Constant.USERS_USERID  + "," +
                Constant.TASKS_DATECREATED + "," +
                Constant.TASKS_DESCRIPTION + "," +
                Constant.TASKS_TASK + ")" +
                "VALUES(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setInt(1,task.getUserId());
            preparedStatement.setTimestamp(2,task.getDatecreated());
            preparedStatement.setString(3,task.getDescription());
            preparedStatement.setString(4,task.getTask());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //    Read DB
    public ResultSet getTasksByUser(int UserId){
        ResultSet resultTasks = null;

        String query = "SELECT * FROM " + Constant.TASKS_TABLE + " WHERE " + Constant.USERS_USERID + "=?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setInt(1,UserId);
            resultTasks = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultTasks;
    }

    public ResultSet getUser(User user){
        ResultSet resultSet = null;
        if (!user.getUsername().equals("") || !user.getPassword().equals("")){
//            SELECT * FROM USER WHERE USERNAME =? AND PASSWORD =?;
            String query = "SELECT * FROM " + Constant.USERS_TABLE + " WHERE " + Constant.USERS_USERNAME + "=?" + " AND " + Constant.USERS_PASSWORD + "=?";
            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1,user.getUsername());
                preparedStatement.setString(2,user.getPassword());
//                query return
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error");
        }
        return resultSet;
    }

    public int getAllTasks(int userId) throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM " + Constant.TASKS_TABLE + " WHERE " + Constant.USERS_USERID + "=?";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return resultSet.getInt(1);
    }


//    Update DB

//    Delete from DB
    public void deleteTask(int userId, int taskId) {
        String query = "DELETE FROM " + Constant.TASKS_TABLE + " WHERE " + Constant.USERS_USERID + "=?" + " AND " + Constant.TASKS_TASKID + "=?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setInt(1,userId);
            preparedStatement.setInt(2,taskId);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
