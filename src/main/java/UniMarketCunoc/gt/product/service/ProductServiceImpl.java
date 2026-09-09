package UniMarketCunoc.gt.product.service;

import UniMarketCunoc.gt.product.dto.CategoryResponse;
import UniMarketCunoc.gt.product.dto.ProductRequest;
import UniMarketCunoc.gt.product.dto.ProductResponse;
import UniMarketCunoc.gt.product.entidad.CategoryEnty;
import UniMarketCunoc.gt.product.entidad.Product;
import UniMarketCunoc.gt.product.entidad.ProductNotFoundException;
import UniMarketCunoc.gt.product.repository.CategoriaRepository;
import UniMarketCunoc.gt.product.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

import org.springframework.dao.CannotSerializeTransactionException;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final CategoriaRepository categoriaRepository;
	@PersistenceContext
	private EntityManager entityManager;

	public ProductServiceImpl(ProductRepository productRepository, CategoriaRepository categoriaRepository) {
		this.productRepository = productRepository;
        this.categoriaRepository = categoriaRepository;
    }

	@Override
	public ProductResponse create(ProductRequest request) {
		Product product = new Product();
		apply(product, request);
		return toResponse(productRepository.save(product));
	}

	@Override
	public ProductResponse update(Long id, ProductRequest request) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException(id));
		apply(product, request);
		return toResponse(productRepository.save(product));
	}

	@Override
	public ProductResponse getById(Long id) {
		return toResponse(productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException(id)));
	}

	@Override
	public List<ProductResponse> getAll() {
		return productRepository.findAll().stream().map(this::toResponse).toList();
	}

	@Override
	public List<CategoryEnty> getCategories() {
		return this.categoriaRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		if (!productRepository.existsById(id)) {
			throw new ProductNotFoundException(id);
		}
		productRepository.deleteById(id);
	}

	private void apply(Product product, ProductRequest request) {
		product.setName(request.name().trim());
		product.setDescription(request.description().trim());
		product.setPrice(request.price());
		product.setStock(request.stock());
		product.setCategory(request.category().trim());
	}

	private ProductResponse toResponse(Product product) {
		return new ProductResponse(
				product.getId(),
				product.getName(),
				product.getDescription(),
				product.getPrice(),
				product.getStock(),
				product.getCategory()
		);
	}
}
