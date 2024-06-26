package gift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/{id}")
    public Product getOneProduct(@PathVariable("id") Long id){
        if(!products.containsKey(id)){
            throw new RuntimeException("해당 id를 가지고있는 Product 객체가 없습니다.");
        }
        return products.get(id);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        product.setId(nextId++);
        products.put(product.getId(), product);
        return product;
    }

    @PutMapping("/{id}")
    public Product changeProduct(@PathVariable("id") Long id, @RequestBody Product product){
        if(!products.containsKey(id)){
            throw new RuntimeException("해당 id를 가지고있는 Product 객체가 없습니다.");
        }
        product.setId(id);
        products.put(id, product);
        return product;
    }

    @DeleteMapping("/{id}")
    public void removeProduct(@PathVariable("id") Long id) {
        if (!products.containsKey(id)) {
            throw new RuntimeException("해당 id를 가지고있는 Product 객체가 없습니다.");
        }
        products.remove(id);
    }
}
