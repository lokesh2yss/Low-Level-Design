package in_memory_file_system.entities;

import in_memory_file_system.command.*;
import in_memory_file_system.strategy.DetailedListingStrategy;
import in_memory_file_system.strategy.ListingStrategy;
import in_memory_file_system.strategy.SimpleListingStrategy;

import java.util.Arrays;

public class Shell {
    private final FileSystem fs;

    public Shell() {
        this.fs = FileSystem.getInstance();
    }

    public void executeCommand(String input) {
        String[] parts = input.trim().split("\\s+");
        String commandName = parts[0];
        Command command;
        try {
            command = switch (commandName) {
                case "mkdir" -> new MkdirCommand(fs, parts[1]);
                case "touch" -> new TouchCommand(fs, parts[1]);
                case "cd" -> new CdCommand(fs, parts[1]);
                case "ls" -> new LsCommand(fs, getPathArgumentForLs(parts), getListingStrategy(parts));
                case "pwd" -> new PwdCommand(fs);
                case "cat" -> new CatCommand(fs, parts[1]);
                case "echo" -> new EchoCommand(fs, getEchoContent(input), getEchoFilePath(parts));
                default -> () -> System.err.println("Error: Unknown command '" + commandName + "'.");
            };
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Missing argument for command '" + commandName + "'.");
            command = () -> {
            }; // No-op command
        }
        command.execute();
    }

    private ListingStrategy getListingStrategy(String[] args) {
        if (Arrays.asList(args).contains("-l")) {
            return new DetailedListingStrategy();
        }
        return new SimpleListingStrategy();
    }

    private String getPathArgumentForLs(String[] args) {
        return Arrays.stream(args)
                .skip(1)
                .filter(part -> !part.startsWith("-"))
                .findFirst()
                .orElse(null);
    }

    private String getEchoContent(String input) {
        // Simple parsing for "echo 'content' > file"
        try {
            return input.substring(input.indexOf("'") + 1, input.lastIndexOf("'"));
        } catch (Exception e) {
            return "";
        }
    }

    private String getEchoFilePath(String[] parts) {
        // The file path is the last argument after the redirection symbol '>'
        for(int i=0; i<parts.length; i++) {
            if(">".equals(parts[i]) && i+1 < parts.length) {
                return parts[i+1];
            }
        }
        return ""; //should be handled by arg check
    }
}