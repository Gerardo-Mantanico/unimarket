package UniMarketCunoc.gt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

	@Bean
	public S3Client s3Client(
			@Value("${storage.s3.region}") String region,
			@Value("${storage.s3.access-key}") String accessKey,
			@Value("${storage.s3.secret-key}") String secretKey) {
		return S3Client.builder()
				.region(Region.of(region))
				.credentialsProvider(
						StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
				.build();
	}
}
