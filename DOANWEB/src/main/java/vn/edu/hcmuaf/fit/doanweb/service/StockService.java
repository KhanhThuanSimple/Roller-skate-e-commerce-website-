package vn.edu.hcmuaf.fit.doanweb.service;

import vn.edu.hcmuaf.fit.doanweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.doanweb.dao.StocksDao;
import vn.edu.hcmuaf.fit.doanweb.dao.db.DBConnect;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Stock;
import vn.edu.hcmuaf.fit.doanweb.dao.model.order.OrderDetail;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public Stock findProduct( int product_id) throws SQLException {
        StocksDao stocksDao = new StocksDao();
        return stocksDao.findProduct(product_id);
    }

    public boolean insertStock (int product_id,int quantity_stock ) throws SQLException {
        StocksDao stocksDao = new StocksDao();
        return stocksDao.insertStock(product_id,quantity_stock);
    }
    public boolean updateStock(int id , int quantity_stock) throws SQLException {
        StocksDao stocksDao = new StocksDao();
        return stocksDao.updateStock(id,quantity_stock);
    }
    public boolean deleteProduct(int id) throws SQLException {
        StocksDao stocksDao = new StocksDao();
        return stocksDao.deleteStock(id);
    }

    public void exportStockByOrder(int order_id) throws SQLException {
        //XUất kho các ặt hàng trong order_detail và đổi trạng thái order thành đã giao hàng
        ProductDao dao = new ProductDao();
        List<OrderDetail> list = dao.getOrderDetails(0,order_id);
        for (OrderDetail orderDetail : list) {
            Stock stock = this.findProduct(orderDetail.getProduct().getId());
            if(stock!=null){
                this.updateStock(stock.getId(),-1* orderDetail.getOrderItem().getQuantity());
            }
        }

        StocksDao stocksDao = new StocksDao();
        stocksDao.updateStatusOrder(order_id, "Đã xuất kho");

    }




}
