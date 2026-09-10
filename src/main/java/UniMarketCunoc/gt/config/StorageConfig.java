package UniMarketCunoc.gt.config;

import UniMarketCunoc.gt.product.service.ProductImageStorageService;
import UniMarketCunoc.gt.storage.AwsS3StorageService;
import UniMarketCunoc.gt.storage.AzureBlobStorageService;
import UniMarketCunoc.gt.storage.DocumentStorageService;
import UniMarketCunoc.gt.storage.GcpStorageService;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class StorageConfig {

    @Bean
    @ConditionalOnProperty(name = "storage.provider", havingValue = "azure", matchIfMissing = true)
    public BlobServiceClient azureBlobServiceClient(
            @Value("${storage.azure.connection-string}") String connectionString) {
        return new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();
    }

    @Bean
    @ConditionalOnProperty(name = "storage.provider", havingValue = "azure", matchIfMissing = true)
    public AzureBlobStorageService azureBlobStorageServiceProvider(
            BlobServiceClient blobServiceClient,
            @Value("${storage.azure.container-name}") String containerName,
            @Value("${storage.azure.public-base-url}") String publicBaseUrl) {
        return new AzureBlobStorageService(blobServiceClient, containerName, publicBaseUrl);
    }

    @Bean
    @ConditionalOnProperty(name = "storage.provider", havingValue = "s3")
    public S3Client awsS3Client(
            @Value("${storage.s3.region}") String region,
            @Value("${storage.s3.access-key}") String accessKey,
            @Value("${storage.s3.secret-key}") String secretKey) {
        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
                .build();
    }

    @Bean
    @ConditionalOnProperty(name = "storage.provider", havingValue = "s3")
    public AwsS3StorageService awsStorageServiceProvider(
            S3Client s3Client,
            @Value("${storage.s3.bucket}") String bucket,
            @Value("${storage.s3.public-base-url}") String publicBaseUrl) {
        return new AwsS3StorageService(s3Client, bucket, publicBaseUrl);
    }

    @Bean
    @ConditionalOnProperty(name = "storage.provider", havingValue = "gcp")
    public Storage gcpStorage(
            @Value("${storage.gcp.project-id}") String projectId,
            @Value("${storage.gcp.credentials-json}") String credentialsJson) throws Exception {
        return StorageOptions.newBuilder()
                .setProjectId(projectId)
                .setCredentials(GoogleCredentials.fromStream(
                        new ByteArrayInputStream(credentialsJson.getBytes(StandardCharsets.UTF_8))))
                .build()
                .getService();
    }

    @Bean
    @ConditionalOnProperty(name = "storage.provider", havingValue = "gcp")
    public GcpStorageService gcpStorageServiceProvider(
            Storage storage,
            @Value("${storage.gcp.bucket}") String bucket,
            @Value("${storage.gcp.public-base-url}") String publicBaseUrl) {
        return new GcpStorageService(storage, bucket, publicBaseUrl);
    }

    @Bean
    public ProductImageStorageService productImageStorageService(
            List<ProductImageStorageService> delegates) {
        return file -> {
            RuntimeException lastError = null;
            for (ProductImageStorageService delegate : delegates) {
                try {
                    return delegate.uploadProductImage(file);
                } catch (RuntimeException ex) {
                    lastError = ex;
                }
            }
            throw new IllegalStateException("No se pudo subir la imagen en ningún proveedor configurado", lastError);
        };
    }

    @Bean
    public DocumentStorageService documentStorageService(
            List<DocumentStorageService> delegates) {
        return (file, folder) -> {
            RuntimeException lastError = null;
            for (DocumentStorageService delegate : delegates) {
                try {
                    return delegate.uploadDocument(file, folder);
                } catch (RuntimeException ex) {
                    lastError = ex;
                }
            }
            throw new IllegalStateException("No se pudo subir el documento en ningún proveedor configurado", lastError);
        };
    }
}
