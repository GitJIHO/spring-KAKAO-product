package gift.service;

import gift.dao.ProductDAO;
import gift.dto.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductDAO productDAO;

    @Autowired
    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    public Product getProductById(Long id) {
        return productDAO.findById(id);
    }

    public void saveProduct(Product product) {
        if (product.getId() == null) {
            productDAO.save(product);
            return;
        }
        productDAO.update(product);
    }

    public void deleteProduct(Long id) {
        productDAO.deleteById(id);
    }
}
