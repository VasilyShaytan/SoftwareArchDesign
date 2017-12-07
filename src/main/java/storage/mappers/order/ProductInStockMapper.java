package storage.mappers.order;

import businesslogic.orderfactory.ProductInStock;
import businesslogic.orderfactory.ProductInStockFactory;
import storage.Gateway;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductInStockMapper {
    private static ArrayList<ProductInStock> productInStocks = new ArrayList<ProductInStock>();
    private Connection connection;


    public ProductInStockMapper() throws SQLException, IOException {
        Gateway gateway = Gateway.getInstance();
        connection = gateway.getDataSource().getConnection();
    }

    public ArrayList<ProductInStock> getTableProductInStock() throws SQLException {
        ArrayList<ProductInStock> res = new ArrayList<ProductInStock>();
        String querySQL = "SELECT * FROM products";
        PreparedStatement ps = connection.prepareStatement(querySQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int pid = rs.getInt("ProductId");
            String pn = rs.getString("ProductName");
            int pc = rs.getInt("ProductCount");
            int pp = rs.getInt("ProductPrice");
            ProductInStock pis = ProductInStockFactory.createProductInStock(pid, pn, pc, pp);
            addToProductInStock(pis);
            if(pis.getProductId() != 0)
                res.add(pis);
        }
        return res;
    }

    private void addToProductInStock(ProductInStock pis){
        for (int i = 0; i < productInStocks.size(); ++i){
            if ((productInStocks.get(i).getProductId()) == (pis.getProductId())){
                productInStocks.set(i, pis);
                return;
            }
        }
        productInStocks.add(pis);
    }

    public void editProductInStock(ProductInStock productInStock) throws SQLException {
        String querySQL = "SELECT * FROM products WHERE ProductId = ?";
        PreparedStatement ps = connection.prepareStatement(querySQL);
        ps.setLong(1, productInStock.getProductId());
        ResultSet rs = ps.executeQuery();
        if(!rs.next()) return;
        int pc = rs.getInt("ProductCount");

        String querySQL1 = "UPDATE products SET ProductCount = ? WHERE ProductId = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(querySQL1);
        preparedStatement.setInt(1, productInStock.setProductCount(pc - productInStock.getProductCount()));
        preparedStatement.setLong(2, productInStock.getProductId());

        preparedStatement.executeUpdate();

    }

    public ArrayList<ProductInStock> getTableProviderProductInStock() throws SQLException {
        ArrayList<ProductInStock> res = new ArrayList<ProductInStock>();
        String querySQL = "SELECT * FROM providerproducts";
        PreparedStatement ps = connection.prepareStatement(querySQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int pid = rs.getInt("ProductId");
            String pn = rs.getString("ProductName");
            int pc = rs.getInt("ProductCount");
            int pp = rs.getInt("ProductPrice");
            ProductInStock pis = ProductInStockFactory.createProductInStock(pid, pn, pc, pp);
            addToProductInStock(pis);
            if(pis.getProductId() != 0)
                res.add(pis);
        }
        return res;
    }

    public void createNewProduct(long pid, String name, int count, int price) throws Exception{
        String querySQL = "INSERT INTO providerproducts(" +
                "providerproducts.ProductId, providerproducts.ProductName, providerproducts.ProductCount, providerproducts.ProductPrice)" +
                " VALUES (?, ?, ?, ?);";

        PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
        preparedStatement.setLong(1, pid);
        preparedStatement.setString(2, name);
        preparedStatement.setInt(3, count);
        preparedStatement.setInt(4, price);
        // execute insert SQL stetement
        preparedStatement.executeUpdate();
        //return true;
    }

    public void editProductLine(long pid, String name, int count, int price) throws SQLException {
        String query = "UPDATE providerproducts SET ProductName = ?, ProductCount = ?, ProductPrice = ? WHERE ProductId = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, count);
        preparedStatement.setInt(3, price);
        preparedStatement.setLong(4, pid);

        preparedStatement.executeUpdate();
    }

    public void editProviderProductInStock(ProductInStock productInStock) throws SQLException {
        String querySQL = "SELECT * FROM providerproducts WHERE ProductId = ?";
        PreparedStatement ps = connection.prepareStatement(querySQL);
        ps.setLong(1, productInStock.getProductId());
        ResultSet rs = ps.executeQuery();
        if(!rs.next()) return;
        int pc = rs.getInt("ProductCount");

        String querySQL1 = "UPDATE providerproducts SET ProductCount = ? WHERE ProductId = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(querySQL1);
        preparedStatement.setInt(1, productInStock.setProductCount(pc - productInStock.getProductCount()));
        preparedStatement.setLong(2, productInStock.getProductId());

        preparedStatement.executeUpdate();

    }

    public void editProductLineClient(long pid, String name, int count, int price) throws SQLException {
        String query = "UPDATE products SET ProductName = ?, ProductCount = ?, ProductPrice = ? WHERE ProductId = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, count);
        preparedStatement.setInt(3, price);
        preparedStatement.setLong(4, pid);

        preparedStatement.executeUpdate();
    }


}
