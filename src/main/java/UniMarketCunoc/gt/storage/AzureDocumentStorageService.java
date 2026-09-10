package UniMarketCunoc.gt.storage;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobHttpHeaders;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public class AzureDocumentStorageService implements DocumentStorageService {

    private final BlobServiceClient blobServiceClient;
    private final String containerName;
    private final String publicBaseUrl;

    public AzureDocumentStorageService(
            BlobServiceClient blobServiceClient,
            @Value("${storage.azure.container-name}") String containerName,
            @Value("${storage.azure.public-base-url}") String publicBaseUrl) {
        this.blobServiceClient = blobServiceClient;
        this.containerName = containerName;
        this.publicBaseUrl = publicBaseUrl;
    }

    @Override
    public String uploadDocument(MultipartFile file, String folder) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("El archivo no puede estar vacío");
        }

        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
        containerClient.createIfNotExists();

        String targetFolder = (folder == null || folder.isBlank()) ? "general" : folder.trim();
        String fileName = UUID.randomUUID() + "-" + sanitize(file.getOriginalFilename());
        String key = targetFolder + "/" + fileName;
        BlobClient blobClient = containerClient.getBlobClient(key);
        BlobHttpHeaders headers = new BlobHttpHeaders().setContentType(detectContentType(file));

        try (InputStream inputStream = file.getInputStream()) {
            blobClient.upload(inputStream, file.getSize(), true);
            blobClient.setHttpHeaders(headers);
            return buildUrl(key);
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo leer el archivo", e);
        }
    }

    private String buildUrl(String key) {
        String base = publicBaseUrl.endsWith("/") ? publicBaseUrl.substring(0, publicBaseUrl.length() - 1) : publicBaseUrl;
        return base + "/" + key;
    }

    private String sanitize(String originalFilename) {
        if (originalFilename == null || originalFilename.isBlank()) {
            return "documento";
        }
        String sanitized = originalFilename.replaceAll("[^a-zA-Z0-9._-]", "_");
        return sanitized.length() > 150 ? sanitized.substring(0, 150) : sanitized;
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
