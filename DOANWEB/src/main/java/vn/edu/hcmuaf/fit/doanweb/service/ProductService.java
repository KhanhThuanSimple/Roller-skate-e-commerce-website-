package vn.edu.hcmuaf.fit.doanweb.service;

import vn.edu.hcmuaf.fit.doanweb.dao.model.Product;
import vn.edu.hcmuaf.fit.doanweb.dao.ProductDao;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    static ProductDao productDao = new ProductDao();
    public List<Product> getAll() throws SQLException {return productDao.getAll();}

//  public  Product getDetail(String in){
//        try {
//            int id = Integer.parseInt(in);
//            return productDao.getById(id);
//        }catch (NumberFormatException e){
//            return null;
//        }
//    }

}
