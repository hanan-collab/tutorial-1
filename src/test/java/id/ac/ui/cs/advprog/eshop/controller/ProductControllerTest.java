package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateProductPage() {
        String viewName = productController.createProductPage(model);

        assertEquals("createProduct", viewName);
        verify(model, times(1)).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        product.setProductId(UUID.randomUUID().toString());

        String viewName = productController.createProductPost(product, model);

        verify(productService, times(1)).create(product);
        assertEquals("redirect:list", viewName);
    }

    @Test
    void testProductListPage() {
        List<Product> mockProducts = Arrays.asList(new Product(), new Product());
        when(productService.findAll()).thenReturn(mockProducts);

        String viewName = productController.productListPage(model);

        verify(productService, times(1)).findAll();
        verify(model, times(1)).addAttribute(eq("products"), eq(mockProducts));
        assertEquals("productList", viewName);
    }

    @Test
    void testEditProductPage() {
        String productId = UUID.randomUUID().toString();
        Product mockProduct = new Product();
        when(productService.findById(productId)).thenReturn(mockProduct);

        String viewName = productController.editProductPage(productId, model);

        verify(productService, times(1)).findById(productId);
        verify(model, times(1)).addAttribute(eq("product"), eq(mockProduct));
        assertEquals("editProduct", viewName);
    }

    @Test
    void testEditProductPost() {
        String productId = UUID.randomUUID().toString();
        Product product = new Product();
        product.setProductId(productId);

        String viewName = productController.editProductPost(productId, product);

        verify(productService, times(1)).edit(eq(productId), any(Product.class));
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testDeleteProduct() {
        String productId = UUID.randomUUID().toString();
        Product deletedProduct = new Product();
        when(productService.delete(productId)).thenReturn(deletedProduct);

        String viewName = productController.deleteProduct(productId, model);

        verify(productService, times(1)).delete(productId);
        verify(model, times(1)).addAttribute(eq("deletedProduct"), eq(deletedProduct));
        assertNull(model.getAttribute("error"));
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testDeleteProductNotFound() {
        String productId = UUID.randomUUID().toString();
        when(productService.delete(productId)).thenReturn(null);

        String viewName = productController.deleteProduct(productId, model);

        verify(productService, times(1)).delete(productId);
        assertNull(model.getAttribute("deletedProduct"));
        assertEquals("redirect:/product/list", viewName);
    }
}
