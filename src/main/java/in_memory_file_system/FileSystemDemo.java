package in_memory_file_system;

import in_memory_file_system.entities.Shell;

public class FileSystemDemo {
    public static void main(String[] args) {
        Shell shell = new Shell();
        String[] commands = {
                "pwd",                          // /
                "mkdir /home",
                "mkdir /home/user",
                "touch /home/user/file1.txt",
                "ls -l /home",                  // d user
                "cd /home/user",
                "pwd",                          // /home/user
                "ls",                           // file1.txt
                "echo 'Hello World!' > file1.txt",
                "cat file1.txt",                // Hello World!
                "echo 'Overwriting content' > file1.txt",
                "cat file1.txt",                // Overwriting content
                "mkdir documents",
                "cd documents",
                "pwd",                          // /home/user/documents
                "touch report.docx",
                "ls",                           // report.docx
                "cd ..",
                "pwd",                          // /home/user
                "ls -l",                        // d documents, f file1.txt
                "cd /",
                "pwd",                          // /
                "ls -l",                        // d home
                "cd /nonexistent/path"          // Error: not a directory
        };

        for (String command : commands) {
            System.out.println("\n$ " + command);
            shell.executeCommand(command);
        }
    }
}
