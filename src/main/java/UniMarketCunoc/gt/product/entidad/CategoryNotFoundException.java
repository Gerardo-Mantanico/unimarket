package UniMarketCunoc.gt.product.entidad;

public class CategoryNotFoundException extends RuntimeException {
	public CategoryNotFoundException(Long id) {
		super("Category not found with id: " + id);
	}
}
