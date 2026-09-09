package UniMarketCunoc.gt.product.service;

import UniMarketCunoc.gt.product.dto.CategoryResponse;
import UniMarketCunoc.gt.product.dto.ProductCreateRequest;
import UniMarketCunoc.gt.product.dto.ProductRequest;
import UniMarketCunoc.gt.product.dto.ProductResponse;
import UniMarketCunoc.gt.product.entidad.CategoryEnty;

import java.util.List;
import java.util.Set;

public interface ProductService {
	ProductResponse create(ProductRequest request);
	ProductResponse create(ProductCreateRequest request, String imageUrl);
	ProductResponse update(Long id, ProductRequest request);
	ProductResponse getById(Long id);
	List<ProductResponse> getAll();
	List<CategoryEnty> getCategories();
	void delete(Long id);
}
