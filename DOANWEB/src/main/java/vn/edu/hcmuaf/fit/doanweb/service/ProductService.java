package vn.edu.hcmuaf.fit.doanweb.service;

import vn.edu.hcmuaf.fit.doanweb.dao.CategoryDao;
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
public List<Product> getListProduct() throws SQLException {
    ProductDao productDao = new ProductDao();
    List<Product> products = productDao.getAll();
    return products;
}
//    public boolean insertProduct(String name) throws SQLException {
//        ProductDao productDao = new ProductDao();
//        return productDao.insertProduct(name);
//    }
//    public boolean updateCategory(String name, int id) throws SQLException {
//        CategoryDao categoryDao = new CategoryDao();
//        return categoryDao.updateCategory(name,id);
//    }
//    public boolean deleteCategory(int id) throws SQLException {
//        CategoryDao categoryDao = new CategoryDao();
//        return categoryDao.deleteCategory(id);
//    }


}
