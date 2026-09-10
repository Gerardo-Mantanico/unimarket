package UniMarketCunoc.gt.storage;

import UniMarketCunoc.gt.product.service.ProductImageStorageService;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import java.io.IOException;
import java.net.URLConnection;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

public class GcpStorageService implements ProductImageStorageService, DocumentStorageService {

    private final Storage storage;
    private final String bucket;
    private final String publicBaseUrl;

    public GcpStorageService(Storage storage, String bucket, String publicBaseUrl) {
        this.storage = storage;
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
        try {
            Blob blob = storage.create(
                    BlobInfoBuilder.build(bucket, key, detectContentType(file)),
                    file.getBytes());
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

    private static class BlobInfoBuilder {
        private static com.google.cloud.storage.BlobInfo build(String bucket, String key, String contentType) {
            return com.google.cloud.storage.BlobInfo.newBuilder(bucket, key)
                    .setContentType(contentType)
                    .build();
        }
    }
}
