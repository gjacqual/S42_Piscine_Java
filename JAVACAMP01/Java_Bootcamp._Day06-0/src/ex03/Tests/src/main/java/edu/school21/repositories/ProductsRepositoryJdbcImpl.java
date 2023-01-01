package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {
    private final DataSource dataSource;

    private static final String SQL_ALL = "SELECT * FROM products";

    public  ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> productList = new ArrayList<>();

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Product product = new Product(
                    resultSet.getLong("identifier"),
                    resultSet.getString("name"),
                    resultSet.getInt("price")
            );
            productList.add(product);
        }
        preparedStatement.close();
        connection.close();
        return productList;
    }

    @Override
    public Optional<Product> findById(Long id)  throws SQLException {

        String SQL_FINDBYID = "SELECT * FROM products WHERE identifier=?";
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FINDBYID);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
            return Optional.empty();
        }
        Product product = new Product(
                resultSet.getLong("identifier"),
                resultSet.getString("name"),
                resultSet.getInt("price")
        );

        preparedStatement.close();
        connection.close();
        return Optional.of(product);

    }

    @Override
    public void update(Product product) throws SQLException {

        String SQL_UDATE = "UPDATE products SET name=?, price=? WHERE identifier=?";
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_UDATE);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setInt(2, product.getPrice());
        preparedStatement.setLong(3, product.getIdentifier());
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();

    }

    @Override
    public void save(Product product) throws SQLException {

        String SQL_SAVE = "INSERT INTO products (name, price) VALUES (?, ?)";
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setInt(2, product.getPrice());
        preparedStatement.execute();
        ResultSet resultSet = connection.createStatement().executeQuery("CALL IDENTITY()");
        if (resultSet.next()) {
            product.setIdentifier(resultSet.getLong(1));
        }
        preparedStatement.close();
        connection.close();
    }

    @Override
    public void delete(Long id) throws SQLException{

        String SQL_DELETE = "DELETE FROM products WHERE identifier = ?";
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
        preparedStatement.setLong(1, id);
        preparedStatement.execute();

        preparedStatement.close();
        connection.close();

    }


}
