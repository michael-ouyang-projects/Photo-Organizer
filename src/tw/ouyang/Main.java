package tw.ouyang;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class Main {

    public static void main(String[] args) throws IOException {

        Path sourceDirectory = Paths.get(args[0]);
        Path targetDirectory = Paths.get(args[1]);

        Files.walk(sourceDirectory)
                .filter(Files::isRegularFile)
                .forEach(photo -> {
                    try {
                        // get creation year and month
                        BasicFileAttributes photoAttributes = Files.readAttributes(photo, BasicFileAttributes.class);
                        String year = photoAttributes.creationTime().toString().substring(0, 4);
                        String month = photoAttributes.creationTime().toString().substring(5, 7);
                        
                        // create directories if not exist
                        Path monthDirectory = Paths.get(targetDirectory.toString(), year, month);
                        if (!(Files.exists(monthDirectory) && Files.isDirectory(monthDirectory))) {
                            Files.createDirectories(monthDirectory);
                        }
                        
                        // copy file
                        Path targetFile = Paths.get(monthDirectory.toString(), photo.getFileName().toString());
                        Files.copy(photo, targetFile);
                    } catch (IOException e) {
                        System.err.println("Something Went Wrong!");
                        e.printStackTrace();
                    }
                });

        System.out.println("Done!");

    }

}
