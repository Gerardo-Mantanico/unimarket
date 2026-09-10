package UniMarketCunoc.gt.product.controller;

import UniMarketCunoc.gt.product.dto.ProductRequest;
import UniMarketCunoc.gt.product.dto.ProductResponse;
import UniMarketCunoc.gt.product.dto.ProductCreateRequest;
import UniMarketCunoc.gt.product.entidad.CategoryEnty;
import UniMarketCunoc.gt.product.service.ProductImageStorageService;
import UniMarketCunoc.gt.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Encoding;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService productService;
	private final ProductImageStorageService productImageStorageService;

	public ProductController(ProductService productService, ProductImageStorageService productImageStorageService) {
		this.productService = productService;
		this.productImageStorageService = productImageStorageService;
	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(
			summary = "Crear producto con imagen opcional",
			requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
					required = true,
					content = @Content(
							mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
							schema = @Schema(implementation = CreateProductMultipartRequest.class),
							encoding = @Encoding(name = "product", contentType = MediaType.APPLICATION_JSON_VALUE))))
	public ProductResponse create(
			@Valid @RequestPart("product") ProductCreateRequest request,
			@RequestPart(value = "image", required = false) MultipartFile image) {
		String imageUrl = image == null || image.isEmpty() ? null : productImageStorageService.uploadProductImage(image);
		return productService.create(request, imageUrl);
	}

	@GetMapping
	public List<ProductResponse> getAll() {
		return productService.getAll();
	}

	@GetMapping("/categories")
	public List<CategoryEnty> getCategories() {
		return productService.getCategories();
	}

	@GetMapping("/{id}")
	public ProductResponse getById(@PathVariable Long id) {
		return productService.getById(id);
	}

	@PutMapping("/{id}")
	public ProductResponse update(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
		return productService.update(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		productService.delete(id);
	}

	private record CreateProductMultipartRequest(
			ProductCreateRequest product,
			@Schema(type = "string", format = "binary") String image
	) {
	}
}
