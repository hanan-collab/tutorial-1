package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        Product originalProduct = new Product();
        originalProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        originalProduct.setProductName("Sampo Cap Bambang");
        originalProduct.setProductQuantity(100);

        when(productRepository.create(originalProduct)).thenReturn(originalProduct);

        Product createdProduct = productService.create(originalProduct);

        assertNotNull(createdProduct);
        assertEquals(originalProduct.getProductId(), createdProduct.getProductId());
        assertEquals(originalProduct.getProductName(), createdProduct.getProductName());
        assertEquals(originalProduct.getProductQuantity(), createdProduct.getProductQuantity());

        verify(productRepository, times(1)).create(originalProduct);
    }

    @Test
    void testFindAllProducts() {
        List<Product> mockProducts = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("1");
        product1.setProductName("Product 1");
        product1.setProductQuantity(10);
        mockProducts.add(product1);

        Product product2 = new Product();
        product2.setProductId("2");
        product2.setProductName("Product 2");
        product2.setProductQuantity(20);
        mockProducts.add(product2);

        Iterator<Product> iterator = mockProducts.iterator();

        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> allProducts = productService.findAll();

        assertNotNull(allProducts);
        assertEquals(mockProducts.size(), allProducts.size());

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindProductById() {
        Product mockProduct = new Product();
        mockProduct.setProductId("1");
        mockProduct.setProductName("Mock Product");
        mockProduct.setProductQuantity(5);

        when(productRepository.findById("1")).thenReturn(mockProduct);

        Product foundProduct = productService.findById("1");

        assertNotNull(foundProduct);
        assertEquals(mockProduct.getProductId(), foundProduct.getProductId());
        assertEquals(mockProduct.getProductName(), foundProduct.getProductName());
        assertEquals(mockProduct.getProductQuantity(), foundProduct.getProductQuantity());

        verify(productRepository, times(1)).findById("1");
    }

    @Test
    void testEditProduct() {
        Product existingProduct = new Product();
        existingProduct.setProductId("1");
        existingProduct.setProductName("Existing Product");
        existingProduct.setProductQuantity(15);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("1");
        updatedProduct.setProductName("Updated Product");
        updatedProduct.setProductQuantity(20);

        when(productRepository.edit("1", updatedProduct)).thenReturn(updatedProduct);

        Product editedProduct = productService.edit("1", updatedProduct);

        assertNotNull(editedProduct);
        assertEquals(updatedProduct.getProductId(), editedProduct.getProductId());
        assertEquals(updatedProduct.getProductName(), editedProduct.getProductName());
        assertEquals(updatedProduct.getProductQuantity(), editedProduct.getProductQuantity());

        verify(productRepository, times(1)).edit("1", updatedProduct);
    }

    @Test
    void testDeleteProduct() {
        Product existingProduct = new Product();
        existingProduct.setProductId("1");
        existingProduct.setProductName("Existing Product");
        existingProduct.setProductQuantity(15);

        when(productRepository.delete("1")).thenReturn(existingProduct);

        Product deletedProduct = productService.delete("1");

        assertNotNull(deletedProduct);
        assertEquals(existingProduct.getProductId(), deletedProduct.getProductId());
        assertEquals(existingProduct.getProductName(), deletedProduct.getProductName());
        assertEquals(existingProduct.getProductQuantity(), deletedProduct.getProductQuantity());

        verify(productRepository, times(1)).delete("1");
    }
}
