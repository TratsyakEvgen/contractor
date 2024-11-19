package by.bysend.contractor.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.core.io.InputStreamResource;

@Data
@Accessors(chain = true)
public class FileReport {
    private long fileSizeInBytes;
    private String filename;
    private InputStreamResource inputStreamResource;
}
