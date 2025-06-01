package vn.edu.hcmuaf.fit.doanweb.cache;

import vn.edu.hcmuaf.fit.doanweb.dao.ProductDao;
import vn.edu.hcmuaf.fit.doanweb.dao.model.Product;

import java.util.List;

public class ProductCacheManager {
    private static List<Product> cachedProductNew;
    private static List<Product> cachedAllProducts;

    private static long lastUpdateNew = 0;
    private static long lastUpdateAll = 0;

    private static final long CACHE_EXPIRATION = 5 * 60 * 1000; // 5 phút

    /**
     * Lấy danh sách sản phẩm mới (có cache)
     */
    public static List<Product> getCachedProductNew() {
        if (cachedProductNew == null || isExpired(lastUpdateNew)) {
            ProductDao dao = new ProductDao();
            cachedProductNew = dao.getAllProductnew();
            lastUpdateNew = System.currentTimeMillis();
        }
        return cachedProductNew;
    }

    /**
     * Lấy toàn bộ danh sách sản phẩm (có cache)
     */
    public static List<Product> getCachedAllProducts() {
        if (cachedAllProducts == null || isExpired(lastUpdateAll)) {
            ProductDao dao = new ProductDao();
            cachedAllProducts = dao.getAll();
            lastUpdateAll = System.currentTimeMillis();
        }
        return cachedAllProducts;
    }

    /**
     * Kiểm tra cache có hết hạn không
     */
    private static boolean isExpired(long lastUpdateTime) {
        return System.currentTimeMillis() - lastUpdateTime > CACHE_EXPIRATION;
    }

    /**
     * Xóa cache thủ công (nếu cần làm mới)
     */
    public static void clearCache() {
        cachedProductNew = null;
        cachedAllProducts = null;
        lastUpdateNew = 0;
        lastUpdateAll = 0;
    }
}
