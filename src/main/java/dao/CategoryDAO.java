package dao;

import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements GeneralDAO<Category>{
    SQLConnection connectionSQL = new SQLConnection();
    Connection connection = null;
    PreparedStatement statement = null;
    private final String FIND_ALL_QUERY = "select * from category";
    private final String FIND_BY_ID_QUERY = "select * from category where id = ?;";

    @Override
    public List<Category> findAll() throws SQLException, ClassNotFoundException {
        List<Category> list=new ArrayList<>();
        connection=connectionSQL.getConnection();
        statement=connection.prepareStatement(FIND_ALL_QUERY);
        ResultSet resultSet=statement.executeQuery();
        while (resultSet.next()){
            int id=resultSet.getInt("id");
            String name=resultSet.getString("name");
            list.add(new Category(id,name));
        }
        return list;
    }

    @Override
    public Category findById(int id) throws SQLException, ClassNotFoundException {
        connection=connectionSQL.getConnection();
        statement= connection.prepareStatement(FIND_BY_ID_QUERY);
        statement.setInt(1,id);
        ResultSet resultSet=statement.executeQuery();
        while (resultSet.next()){
           String name = resultSet.getString("name");
           return new Category(id,name);
        }
        return null;
    }

    @Override
    public List<Category> findByName(String name) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void add(Category category) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void edit(int id, Category category) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {

    }
}
