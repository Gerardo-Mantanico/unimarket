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
    public AzureBlobStorageService azureBlobStorageService(
            BlobServiceClient blobServiceClient,
            @Value("${storage.azure.container-name}") String containerName,
            @Value("${storage.azure.public-base-url}") String publicBaseUrl) {
        return new AzureBlobStorageService(blobServiceClient, containerName, publicBaseUrl);
    }

    @Bean
    @ConditionalOnProperty(name = "storage.provider", havingValue = "azure", matchIfMissing = true)
    public ProductImageStorageService azureProductImageStorageService(AzureBlobStorageService azureBlobStorageService) {
        return azureBlobStorageService;
    }

    @Bean
    @ConditionalOnProperty(name = "storage.provider", havingValue = "azure", matchIfMissing = true)
    public DocumentStorageService azureDocumentStorageService(AzureBlobStorageService azureBlobStorageService) {
        return azureBlobStorageService;
    }

    @Bean
    @ConditionalOnProperty(name = "storage.provider", havingValue = "aws")
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
    @ConditionalOnProperty(name = "storage.provider", havingValue = "aws")
    public AwsS3StorageService awsStorageService(
            S3Client s3Client,
            @Value("${storage.s3.bucket}") String bucket,
            @Value("${storage.s3.public-base-url}") String publicBaseUrl) {
        return new AwsS3StorageService(s3Client, bucket, publicBaseUrl);
    }

    @Bean
    @ConditionalOnProperty(name = "storage.provider", havingValue = "aws")
    public ProductImageStorageService awsProductImageStorageService(AwsS3StorageService awsStorageService) {
        return awsStorageService;
    }

    @Bean
    @ConditionalOnProperty(name = "storage.provider", havingValue = "aws")
    public DocumentStorageService awsDocumentStorageService(AwsS3StorageService awsStorageService) {
        return awsStorageService;
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
    public GcpStorageService gcpStorageService(
            Storage storage,
            @Value("${storage.gcp.bucket}") String bucket,
            @Value("${storage.gcp.public-base-url}") String publicBaseUrl) {
        return new GcpStorageService(storage, bucket, publicBaseUrl);
    }

    @Bean
    @ConditionalOnProperty(name = "storage.provider", havingValue = "gcp")
    public ProductImageStorageService gcpProductImageStorageService(GcpStorageService gcpStorageService) {
        return gcpStorageService;
    }

    @Bean
    @ConditionalOnProperty(name = "storage.provider", havingValue = "gcp")
    public DocumentStorageService gcpDocumentStorageService(GcpStorageService gcpStorageService) {
        return gcpStorageService;
    }
}
