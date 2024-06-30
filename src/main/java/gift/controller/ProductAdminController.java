package gift.controller;

import gift.dto.Product;
import gift.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/products")
public class ProductAdminController {

    private ProductService productService;

    @Autowired
    public ProductAdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> productList = new ArrayList<>(productService.getAllProducts());
        model.addAttribute("products", productList);
        return "product-list";
    }

    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("edit/{id}")
    public String updateProductForm(@PathVariable("id") Long id, Model model) {
        if (productService.getProductById(id) == null) {
            throw new RuntimeException("해당 id를 가지고있는 Product 객체가 없습니다.");
        }
        model.addAttribute("product", productService.getProductById(id));
        return "product-form";
    }

    @PostMapping("edit/{id}")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute Product product) {
        product.setId(id);
        productService.saveProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        if (productService.getProductById(id) == null) {
            throw new RuntimeException("해당 id를 가지고있는 Product 객체가 없습니다.");
        }
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }
}
