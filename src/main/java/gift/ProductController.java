package gift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    private long nextId = 1;

    @GetMapping
    public List<Product> getProduct(){
        return new ArrayList<>(products.values());
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        products.put(nextId++,product);
        return product;
    }
}
