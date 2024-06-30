package gift.controller;

import gift.dto.Product;
import gift.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProduct() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getOneProduct(@PathVariable("id") Long id) {
        if (productService.getProductById(id) == null) {
            throw new RuntimeException("해당 id를 가지고있는 Product 객체가 없습니다.");
        }
        return productService.getProductById(id);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        productService.saveProduct(product);
        return product;
    }

    @PutMapping("/{id}")
    public Product changeProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        if (productService.getProductById(id) == null) {
            throw new RuntimeException("해당 id를 가지고있는 Product 객체가 없습니다.");
        }
        product.setId(id);
        productService.saveProduct(product);
        return product;
    }

    @DeleteMapping("/{id}")
    public void removeProduct(@PathVariable("id") Long id) {
        if (productService.getProductById(id) == null) {
            throw new RuntimeException("해당 id를 가지고있는 Product 객체가 없습니다.");
        }
        productService.deleteProduct(id);
    }
}
