package vn.edu.hcmuaf.fit.doanweb.dao.model;

import org.jdbi.v3.core.statement.Update;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class CartP {
    Map<Integer,CartProduct> data = new HashMap<>();
    public boolean addProduct(Product p) {
        if (data.containsKey(p.getId())) {
            return update(p.getId(),data.get(p.getId()).getQuantity()+1);
        }
        data.put(p.getId(), convert(p));
        return true;
    }

    public boolean update(int id,int quantity) {
        if (!data.containsKey(id)) return false;
        CartProduct cartProduct = data.get(id);
        if(quantity <1) return false;
        cartProduct.setQuantity(quantity);
        data.put(id, cartProduct);
        return true;
    }
    public boolean removeProduct(int id) {
        return data.remove(id) != null;

    }
    public List<CartProduct> getList() {
        return new ArrayList<>(data.values());
    }
    public int getTotalQuantity() {
        AtomicInteger i = new AtomicInteger();
        data.values().forEach(cp -> i.addAndGet(cp.getQuantity()));
        return i.get();
    }
    public double getTotal() {
        AtomicReference<Double> total = new AtomicReference<>(0.0);
        data.values().forEach(cp -> {
            double productTotal = cp.getQuantity() * cp.getPrice();
            total.updateAndGet(v -> (v + productTotal));
            System.out.println("Sản phẩm: " + cp.getName() + ", Giá: " + cp.getPrice() + ", Số lượng: " + cp.getQuantity() + ", Thành tiền: " + productTotal);
        });
        return total.get();
    }


    private CartProduct convert(Product p) {
        CartProduct re = new CartProduct();
        re.setId(p.getId());
        re.setName(p.getName());
        re.setImg(p.getImg());
        re.setPrice(p.getPrice());
        re.setTitle(p.getTitle());
        re.setDescription(p.getDescription());
        re.setOffer(p.getOffer());
        re.setQuantity(1);
        return re;

    }
}