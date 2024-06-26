package gift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private final Map<Long, Product> products = new HashMap<>();
    private long nextId = 1;

    @GetMapping
    public String getAllProducts(Model model){
        List<Product> productList = new ArrayList<>(products.values());
        model.addAttribute("products", productList);
        return "product-list";
    }

    @GetMapping("/add")
    public String addProductForm(Model model){
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product){
        product.setId(nextId++);
        products.put(product.getId(), product);
        return "redirect:/admin/products";
    }

    @GetMapping("edit/{id}")
    public String updateProductForm(@PathVariable("id") Long id, Model model){
        if(!products.containsKey(id)){
            throw new RuntimeException("해당 id를 가지고있는 Product 객체가 없습니다.");
        }
        model.addAttribute("product", products.get(id));
        return "product-form";
    }

    @PostMapping("edit/{id}")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute Product product){
        product.setId(id);
        products.put(product.getId(), product);
        return "redirect:/admin/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        if(!products.containsKey(id)){
            throw new RuntimeException("해당 id를 가지고있는 Product 객체가 없습니다.");
        }
        products.remove(id);
        return "redirect:/admin/products";
    }
}
