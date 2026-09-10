package UniMarketCunoc.gt.storage;

import org.springframework.web.multipart.MultipartFile;

public interface DocumentStorageService {
    String uploadDocument(MultipartFile file, String folder);
}
