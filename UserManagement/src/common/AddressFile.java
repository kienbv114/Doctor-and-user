package common;

import java.io.File;
import java.nio.file.Paths;

public class AddressFile {

    public File file() {
        String path = Paths.get("").toAbsolutePath().toString();
        return new File(path + "/src/user.kien");
    }
}
