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
        Files.createDirectories(targetDirectory);

        Files.walk(sourceDirectory)
                .filter(Files::isRegularFile)
                .forEach(photo -> {
                    try {
                        BasicFileAttributes photoAttributes = Files.readAttributes(photo, BasicFileAttributes.class);
                        String year = photoAttributes.creationTime().toString().substring(0, 4);
                        String month = photoAttributes.creationTime().toString().substring(5, 7);
                        Path yearDirectory = Paths.get(targetDirectory.toString(), year);
                        Path monthDirectory = Paths.get(yearDirectory.toString(), month);
                        Path targetFile = Paths.get(monthDirectory.toString(), photo.getFileName().toString());
                        if (!(Files.exists(yearDirectory) && Files.isDirectory(yearDirectory))) {
                            Files.createDirectories(yearDirectory);
                        }
                        if (!(Files.exists(monthDirectory) && Files.isDirectory(monthDirectory))) {
                            Files.createDirectories(monthDirectory);
                        }
                        Files.copy(photo, targetFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.err.println("Something Went Wrong!");
                    }
                });

        System.out.println("Done!");

    }

}
