package vn.edu.hcmuaf.fit.doanweb.dao.cart;

import vn.edu.hcmuaf.fit.doanweb.dao.model.Product;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Cart {
    Map<Integer, CartProduct> data = new HashMap<>();

    public boolean add(Product product) {
        if(data.containsKey(product.getId())) {
            return update(product.getId(), data.get(product.getId()).getQuantity()+1);
        }
        data.put(product.getId(), convert(product));
        return true;
    }

    public boolean update(int id, int quantity) {
        if(!data.containsKey(id)) return false;
        CartProduct cartProduct = data.get(id);
        if(quantity <1) return false;
        cartProduct.setQuantity(quantity);
        data.put(id, cartProduct);
        return true;
    }

    public boolean remove(int id) {
        return data.remove(id) != null;
    }

    public List<CartProduct> getList() {
        return new ArrayList<>(data.values());
    }

    public int getTotalQuantity() {
        AtomicInteger i = new AtomicInteger();
        data.values().forEach(cp-> i.addAndGet(cp.getQuantity()));
        return i.get();
    }

    public double getTotalPrice() {
        AtomicReference<Double> i = new AtomicReference<>(0.0);
        data.values().forEach(cp-> i.updateAndGet(v -> (double) (v + (cp.getQuantity() * cp.getPrice()))));
        return i.get();
    }

    private CartProduct convert(Product product) {
        CartProduct ret = new CartProduct();
        ret.setId(product.getId());
        ret.setName(product.getName());
        ret.setImg(product.getImg());
        ret.setPrice(product.getPrice());
        ret.setQuantity(1);
        return ret;
    }
}
