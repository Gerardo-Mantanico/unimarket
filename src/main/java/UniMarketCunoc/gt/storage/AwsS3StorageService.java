package UniMarketCunoc.gt.storage;

import UniMarketCunoc.gt.product.service.ProductImageStorageService;
import java.io.IOException;
import java.net.URLConnection;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class AwsS3StorageService implements ProductImageStorageService, DocumentStorageService {

    private final S3Client s3Client;
    private final String bucket;
    private final String publicBaseUrl;

    public AwsS3StorageService(S3Client s3Client, String bucket, String publicBaseUrl) {
        this.s3Client = s3Client;
        this.bucket = bucket;
        this.publicBaseUrl = publicBaseUrl;
    }

    @Override
    public String uploadProductImage(MultipartFile file) {
        return upload(file, "products");
    }

    @Override
    public String uploadDocument(MultipartFile file, String folder) {
        return upload(file, folder == null || folder.isBlank() ? "general" : folder.trim());
    }

    private String upload(MultipartFile file, String folder) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("El archivo no puede estar vacío");
        }

        String key = folder.trim() + "/" + UUID.randomUUID() + extensionFor(file.getOriginalFilename());
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
            throw new IllegalStateException("No se pudo leer el archivo", e);
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
