package dao;

import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements GeneralDAO<Product>{
    SQLConnection connectionSQL = new SQLConnection();
    Connection connection = null;
    PreparedStatement statement = null;
    private final String FIND_ALL_QUERY = "select * from product";
    private final String FIND_BY_ID_QUERY = "select * from product where id = ?";
    private final String FIND_BY_NAME_QUERY ="select * from product where `name` like ?;";
    private final String INSERT_INTO_UPDATE = "INSERT INTO product (`name`, `price`, `quantity`, `color`, `description`, `category`) VALUES (?,?,?,?,?,?);";
    private final String UPDATE_BY_ID = "UPDATE product SET name = ?, price = ?, quantity = ?, color = ?, description = ?, category = ? WHERE id = ?;";
    private final String DELETE_BY_ID = "DELETE FROM product WHERE (`id` = ?);";
    @Override
    public List<Product> findAll() throws SQLException, ClassNotFoundException {
        List<Product> list=new ArrayList<>();
        connection=connectionSQL.getConnection();
        statement=connection.prepareStatement(FIND_ALL_QUERY);
        ResultSet resultSet=statement.executeQuery();
        while (resultSet.next()){
            int id= resultSet.getInt("id");
            String name = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            int quantity=resultSet.getInt("quantity");
            String color=resultSet.getString("color");
            String desc =resultSet.getString("description");
            int category= resultSet.getInt("category");
            list.add(new Product(id,name,price,quantity,color,desc,category));
        }
        return list;
    }

    @Override
    public Product findById(int id) throws SQLException, ClassNotFoundException {
        connection=connectionSQL.getConnection();
        statement=connection.prepareStatement(FIND_BY_ID_QUERY);
        statement.setInt(1,id);
        ResultSet resultSet=statement.executeQuery();
        while (resultSet.next()){
            String name = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            int quantity=resultSet.getInt("quantity");
            String color=resultSet.getString("color");
            String desc =resultSet.getString("description");
            int category= resultSet.getInt("category");
          return new Product(id,name,price,quantity,color,desc,category);
        }
        return null;
    }

    @Override
    public List<Product> findByName(String name) throws SQLException, ClassNotFoundException {
        List<Product> list=new ArrayList<>();
        connection=connectionSQL.getConnection();
        statement=connection.prepareStatement(FIND_BY_NAME_QUERY);
        statement.setString(1,"%"+name+"%");
        ResultSet resultSet=statement.executeQuery();
        while (resultSet.next()){
            int id= resultSet.getInt("id");
            String nameProduct = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            int quantity=resultSet.getInt("quantity");
            String color=resultSet.getString("color");
            String desc =resultSet.getString("description");
            int category= resultSet.getInt("category");
            list.add(new Product(id,nameProduct,price,quantity,color,desc,category));
        }
        return list ;
    }

    @Override
    public void add(Product product) throws SQLException, ClassNotFoundException {
    connection=connectionSQL.getConnection();
    statement= connection.prepareStatement(INSERT_INTO_UPDATE);
    statement.setString(1,product.getName());
    statement.setDouble(2,product.getPrice());
    statement.setInt(3,product.getQuantity());
    statement.setString(4,product.getColor());
    statement.setString(5,product.getDesc());
    statement.setInt(6,product.getCategory());
    statement.executeUpdate();
    }

    @Override
    public void edit(int id, Product product) throws SQLException, ClassNotFoundException {
    connection=connectionSQL.getConnection();
    statement= connection.prepareStatement(UPDATE_BY_ID);
    statement.setString(1,product.getName());
    statement.setDouble(2,product.getPrice());
    statement.setInt(3,product.getQuantity());
    statement.setString(4, product.getColor());
    statement.setString(5,product.getDesc());
    statement.setInt(6,product.getCategory());
    statement.setInt(7,id);
    statement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {
    connection=connectionSQL.getConnection();
    statement= connection.prepareStatement(DELETE_BY_ID);
    statement.setInt(1,id);
    statement.executeUpdate();
    }
}
