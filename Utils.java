import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    /**
     * javadoc.
     */
    public static String readContentFromFile(String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            return String.join(System.lineSeparator(), lines);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * javadoc.
     */
    public static void writeContentToFile(String path, String content) {
        try {
            Files.write(Paths.get(path), content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * javadoc.
     */
    public static void appendContentToFile(String path, String content) {
        try {
            Files.write(Paths.get(path), content.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * javadoc.
     */
    public static Path findFileByName(String folderPath, String fileName) {
        try {
            return Files.walk(Paths.get(folderPath))
                    .filter(path -> path.getFileName().toString().equals(fileName))
                    .findFirst()
                    .orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {

        String filePath = "C:\\example.txt";

        String content = readContentFromFile(filePath);
        System.out.println("Nội dung tệp:");
        System.out.println(content);

        String newContent = "Nội dung mới";
        writeContentToFile(filePath, newContent);

        String additionalContent = "\nNội dung bổ sung";
        appendContentToFile(filePath, additionalContent);

        String folderPath = "C:\\";
        String fileNameToFind = "example.txt";
        Path foundFile = findFileByName(folderPath, fileNameToFind);
        if (foundFile != null) {
            System.out.println("Tìm thấy tệp: " + foundFile.toAbsolutePath());
        } else {
            System.out.println("Không tìm thấy tệp.");
        }
    }
}
