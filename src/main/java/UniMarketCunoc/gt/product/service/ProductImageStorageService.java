package UniMarketCunoc.gt.product.service;

import org.springframework.web.multipart.MultipartFile;

public interface ProductImageStorageService {
	String uploadProductImage(MultipartFile file);
}
