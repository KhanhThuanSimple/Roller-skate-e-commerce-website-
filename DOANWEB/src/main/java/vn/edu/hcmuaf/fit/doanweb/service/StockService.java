package vn.edu.hcmuaf.fit.doanweb.service;

import vn.edu.hcmuaf.fit.doanweb.dao.StocksDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Stock;

import java.sql.SQLException;
import java.util.ArrayList;

public class StockService {
    public ArrayList<Stock> getListStock(int page) throws SQLException {
        StocksDao stocksDao = new StocksDao();
        ArrayList<Stock> stockss = stocksDao.getListStocks(page);
        return stockss;
    }
    public int getPageStock( ) throws SQLException {
        StocksDao stocksDao = new StocksDao();
        return stocksDao.getPageStock();
    }

    public boolean insertStock (int product_id,String product_name,int quantity_stock ) throws SQLException {
        StocksDao stocksDao = new StocksDao();
        return stocksDao.insertStock(product_id,product_name,quantity_stock);
    }
    public boolean updateStock(int product_id,String product_name,int quantity_stock ,int id) throws SQLException {
        StocksDao stocksDao = new StocksDao();
        return stocksDao.updateStock(product_id, product_name, quantity_stock, id);
    }
    public boolean deleteProduct(int id) throws SQLException {
        StocksDao stocksDao = new StocksDao();
        return stocksDao.deleteStock(id);
    }


}
