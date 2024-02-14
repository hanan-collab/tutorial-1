package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return new ArrayList<>(productData).iterator();
    }

    public Product delete(String productId) {
        Iterator<Product> iterator = productData.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductId().equals(productId)) {
                iterator.remove();
                return product; // Product with the specified ID found and deleted
            }
        }
        return null; // In case the list is empty or product not found
    }

    public Product edit(String productId, Product updatedProduct) {
        for (int i = 0; i < productData.size(); i++) {
            Product product = productData.get(i);
            if (product.getProductId().equals(productId)) {
                productData.set(i, updatedProduct);
                return updatedProduct; // Product with the specified ID found and updated
            }
        }
        return null; // In case the list is empty or product not found
    }

    public Product findById(String productId) {
        for (Product product : productData) {
            if (product.getProductId().equals(productId)) {
                return product; // Product with the specified ID found
            }
        }
        return null; // In case the list is empty or product not found
    }
}
