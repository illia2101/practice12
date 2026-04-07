package ua.university;

import java.nio.file.Path;

public class PathSafety {

    public static Path safeResolve(Path base, String userInput) {

        Path resolved = base.resolve(userInput).normalize();

        if (!resolved.startsWith(base.normalize())) {
            throw new IllegalArgumentException("Виявлено проходження шляху: " + userInput);
        }

        return resolved;
    }
}
