package eu.foobarssgamesmithy.chessmanager.fixtures;

import java.io.IOException;

public class FileFixtures {

    public static String getFromResources(String path) throws IOException {
        return new String(
                FileFixtures.class.getClassLoader()
                        .getResourceAsStream(path)
                        .readAllBytes());
    }
}
