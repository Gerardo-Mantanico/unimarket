package UniMarketCunoc.gt.product.service;

import java.io.IOException;
import java.net.URLConnection;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Service
public class S3ProductImageStorageService implements ProductImageStorageService {

	private final S3Client s3Client;
	private final String bucket;
	private final String publicBaseUrl;

	public S3ProductImageStorageService(
			S3Client s3Client,
			@Value("${storage.s3.bucket}") String bucket,
			@Value("${storage.s3.public-base-url}") String publicBaseUrl) {
		this.s3Client = s3Client;
		this.bucket = bucket;
		this.publicBaseUrl = publicBaseUrl;
	}

	@Override
	public String uploadProductImage(MultipartFile file) {
		String key = "products/" + UUID.randomUUID() + extensionFor(file.getOriginalFilename());
		PutObjectRequest request = PutObjectRequest.builder()
				.bucket(bucket)
				.key(key)
				.contentType(detectContentType(file))
				.acl(ObjectCannedACL.PUBLIC_READ)
				.build();
		try {
			s3Client.putObject(request, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
			return publicBaseUrl.endsWith("/") ? publicBaseUrl + key : publicBaseUrl + "/" + key;
		} catch (IOException e) {
			throw new IllegalStateException("No se pudo leer el archivo de imagen", e);
		} catch (S3Exception e) {
			throw new IllegalStateException("No se pudo subir la imagen a S3", e);
		}
	}

	private String extensionFor(String originalFilename) {
		if (originalFilename == null || !originalFilename.contains(".")) {
			return "";
		}
		return originalFilename.substring(originalFilename.lastIndexOf('.'));
	}

	private String detectContentType(MultipartFile file) {
		String contentType = file.getContentType();
		if (contentType != null && !contentType.isBlank()) {
			return contentType;
		}
		String guessed = URLConnection.guessContentTypeFromName(file.getOriginalFilename());
		return guessed != null ? guessed : "application/octet-stream";
	}
}
