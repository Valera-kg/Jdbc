package com.example.JdbcAPI.dao;

import com.example.JdbcAPI.domain.User;
import org.springframework.stereotype.Component;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {
    String url= "jdbc:postgresql://localhost:5432/postgres";
    String username = "postgres";
    String password = "admin";

    public void create(User user){

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(url,username,password);
             PreparedStatement preparedStatement = connection.prepareStatement("insert into persondb (name) values (?);")){

            preparedStatement.setString(1, user.getName());
            preparedStatement.execute();
            System.out.println("Created!: " + user.getName());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> readAll(){

        List<User> userList = new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(url,username,password);
             PreparedStatement preparedStatement = connection.prepareStatement("select * from persondb");
             ResultSet resultSet = preparedStatement.executeQuery()){

            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("person_id"));
                user.setName(resultSet.getString("name"));
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public User readById(User user){

        User userTemp = null;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(url,username,password);
             PreparedStatement preparedStatement = connection.prepareStatement("select * from persondb where person_id =?;")){
             preparedStatement.setInt(1, user.getId());

            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    userTemp = new User();
                    userTemp.setId(resultSet.getInt("person_id"));
                    userTemp.setName(resultSet.getString("name"));
                    System.out.println("Read by id.");
                }else {
                    System.out.println("No ResultSet data from UserDao-readById!");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userTemp;
    }

    public void update(User user){

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(url,username,password);
             PreparedStatement preparedStatement = connection.prepareStatement("update persondb set name=? where person_id=?;")){

            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
            System.out.println("Updated!: " + user.getName());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteById(User user){

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(url,username,password);
             PreparedStatement preparedStatement = connection.prepareStatement("delete from persondb where person_id=?;")){

            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
            System.out.println("Deleted!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteAll(){

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(url,username,password);
             PreparedStatement preparedStatement = connection.prepareStatement("delete from persondb ;\n" +
                     "alter sequence persondb_person_id_seq restart;")){

            preparedStatement.executeUpdate();
            System.out.println("All users deleted!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

/* russian
1)Для каждого метода нужен свой connection, preparedStatement и resultSet, для потокобезопасности.
2)В параметрах метода где указыватся Id, можно использовать объект person вместо id. Например, readById(id).
3)Данный класс желательно реализовывать от обобщенного интерфейса Dao<Entity>, чтобы подставлять сущности.
Но обобщенный DAO имеет смысл только тогда, когда все Entity-классы одинаковые, т.е их поля идентичные. В противном
случае интерфейс не имеет смысла использовать.
interface Dao<Entity>{
    Optional<Entity> readById(int id);
    List<Entity> readAll();
    void create(Entity entity);
    void update(Entity entity);
    void delete(Entity entity);
}
4)CRUD-методы, методы надо называть по аббриватуре.
5)На каждый layer желательно иметь свой SQLException, например DAOException в слое dao, для локализации ошибок.
6)Всё надо логгировать, либо при помощи sout или ещё лучше классом Logger, для того чтобы локализовать ошибки.
7)Паттерн DAO работает только с одной сущностью, т.е для каждой сущности надо свой Dao, например: userDao, workerDao.
8)Слой(папка) Dao - это классы для "соединения" сущности с базой данных. Например, user + postgres = Dao.
 */
