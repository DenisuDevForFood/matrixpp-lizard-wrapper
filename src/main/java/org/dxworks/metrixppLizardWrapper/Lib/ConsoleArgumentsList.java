package org.dxworks.metrixppLizardWrapper.Lib;

import lombok.Data;
import lombok.ToString;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Data
@ToString
public class ConsoleArgumentsList {

    private static ConsoleArgumentsList instance = null;

    private Path inputDir;
    private Path outputDir;
    private String lizardImageId;
    private String metrixppImageID;
    private File configFile;

    private ConsoleArgumentsList() {}

    public static ConsoleArgumentsList getInstance() {

        if (Objects.isNull(instance)) {
            instance = new ConsoleArgumentsList();

            String input = System.getProperty("inputDir", null);

            String currentDir = System.getProperty("user.dir");

            String defaultOutputDir = currentDir + "/results";

            String output = System.getProperty("outputDir", defaultOutputDir);

            String lizard = System.getProperty("lizardImageID", "denisudevforfood/lizard:1.1");

            String metrixpp = System.getProperty("metrixppImageID", "dxworks/metrixpp:1.4");

            String config = System.getProperty("config", null);

            if (!Objects.isNull(input)) {
                instance.setInputDir(Paths.get(input));
            } else {
                throw new RuntimeException("No input path provided for property -DinputDir");
            }

            instance.setOutputDir(Paths.get(output));

            instance.setLizardImageId(lizard);

            instance.setMetrixppImageID(metrixpp);

            if (!Objects.isNull(config)) {
                instance.setConfigFile(Paths.get(config).toFile());
            }
        }

        return instance;
    }
}
