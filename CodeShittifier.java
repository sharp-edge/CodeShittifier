package com.bankalfalah.alfapay.utils;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * CodeShittifier - Making your code shittier.
 * 
 * A satirical code formatter that does the exact opposite of Prettier/ktfmt.
 * Embraces chaos, messiness, and confusion in your Java/Kotlin codebase.
 * 
 * WARNING: This is a parody tool. Do NOT use on production code!
 * Always commit your code before running this tool.
 * 
 * @author Sarmad
 * @version 1.0.0 (Chaos Edition)
 */
public class CodeShittifier {
    
    private static final Random random = new Random();
    
    // Configuration
    private boolean dryRun = false;
    private boolean createBackups = true;
    private int shittificationLevel = 5; // 1-10, where 10 is maximum chaos
    private boolean verbose = false;
    private Path backupPath = null;
    
    // Statistics
    private int filesProcessed = 0;
    private int filesShittified = 0;
    private int linesShittified = 0;
    
    public static void main(String[] args) {
        if (args.length == 0) {
            printUsage();
            return;
        }
        
        CodeShittifier shittifier = new CodeShittifier();
        shittifier.parseArgs(args);
        
        String targetPath = args[args.length - 1];
        Path path = Paths.get(targetPath);
        
        if (!Files.exists(path)) {
            System.err.println("Error: Path does not exist: " + targetPath);
            System.exit(1);
        }
        
        System.out.println("CodeShittifier v1.0.0 - Making Code Great Again (But Shittier)");
        System.out.println("-".repeat(80));
        System.out.println("Target: " + path.toAbsolutePath());
        System.out.println("Shittification Level: " + shittifier.shittificationLevel + "/10");
        System.out.println("Dry Run: " + (shittifier.dryRun ? "Yes (no files will be modified)" : "No"));
        System.out.println("Backups: " + (shittifier.createBackups ? "Enabled" : "Disabled"));
        System.out.println("-".repeat(80));
        
        try {
            // Create backup before processing
            if (shittifier.createBackups && !shittifier.dryRun) {
                shittifier.createBackupFolder(path);
            }
            
            shittifier.processPath(path);
            shittifier.printStatistics();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Error:  " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void createBackupFolder(Path targetPath) throws IOException {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String backupFolderName = targetPath.getFileName() + "_backup_" + timestamp;
        
        Path parentDir = targetPath.getParent();
        if (parentDir == null) {
            parentDir = Paths.get(".");
        }
        
        backupPath = parentDir.resolve(backupFolderName);
        
        System.out.println("Creating backup: " + backupPath.toAbsolutePath());
        
        // Copy entire directory
        copyDirectory(targetPath, backupPath);
        
        System.out.println("Backup created successfully!");
        System.out.println("-".repeat(80));
    }
    
    private void copyDirectory(Path source, Path target) throws IOException {
        Files.walk(source)
            .forEach(sourcePath -> {
                try {
                    Path targetPath = target.resolve(source.relativize(sourcePath));
                    if (Files.isDirectory(sourcePath)) {
                        Files.createDirectories(targetPath);
                    } else {
                        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
    }
    
    private void parseArgs(String[] args) {
        for (int i = 0; i < args.length - 1; i++) {
            switch (args[i]) {
                case "--dry-run":
                case "-d":
                    dryRun = true;
                    break;
                case "--no-backup":
                    createBackups = false;
                    break;
                case "--level":
                case "-l":
                    if (i + 1 < args.length - 1) {
                        shittificationLevel = Math.max(1, Math.min(10, Integer.parseInt(args[++i])));
                    }
                    break;
                case "--verbose":
                case "-v":
                    verbose = true;
                    break;
            }
        }
    }
    
    private void processPath(Path path) throws IOException {
        if (Files.isDirectory(path)) {
            try (Stream<Path> paths = Files.walk(path)) {
                paths.filter(Files::isRegularFile)
                     .filter(this::isJavaOrKotlinFile)
                     .forEach(this::shittifyFile);
            }
        } else if (isJavaOrKotlinFile(path)) {
            shittifyFile(path);
        }
    }
    
    private boolean isJavaOrKotlinFile(Path path) {
        String fileName = path.getFileName().toString().toLowerCase();
        return fileName.endsWith(".java") || fileName.endsWith(".kt");
    }
    
    private void shittifyFile(Path filePath) {
        filesProcessed++;
        
        try {
            String originalContent = Files.readString(filePath);
            String shittifiedContent = applyShittification(originalContent);
            
            if (!originalContent.equals(shittifiedContent)) {
                filesShittified++;
                
                if (verbose) {
                    System.out.println("Shittifying: " + filePath);
                }
                
                if (!dryRun) {
                    Files.writeString(filePath, shittifiedContent);
                }
                
                linesShittified += shittifiedContent.split("\n").length;
            } else if (verbose) {
                System.out.println("Skipping: " + filePath + " (no changes)");
            }
            
        } catch (IOException e) {
            System.err.println("Failed to process: " + filePath);
            if (verbose) {
                e.printStackTrace();
            }
        }
    }
    
    private String applyShittification(String content) {
        String result = content;
        
        // Apply various shittification strategies based on level
        result = randomizeIndentation(result);
        result = messUpSpacing(result);
        
        if (shittificationLevel >= 3) {
            result = randomizeBraceStyle(result);
        }
        
        if (shittificationLevel >= 5) {
            result = addRandomBlankLines(result);
            result = removeUsefulBlankLines(result);
        }
        
        if (shittificationLevel >= 7) {
            result = randomizeOperatorSpacing(result);
            result = addTrailingSpaces(result);
        }
        
        if (shittificationLevel >= 9) {
            result = maxChaosMode(result);
        }
        
        return result;
    }
    
    private String randomizeIndentation(String content) {
        String[] lines = content.split("\n", -1);
        StringBuilder result = new StringBuilder();
        
        for (String line : lines) {
            if (line.trim().isEmpty()) {
                result.append(line).append("\n");
                continue;
            }
            
            // Get original indentation level
            int originalIndent = line.length() - line.stripLeading().length();
            String trimmedLine = line.stripLeading();
            
            // Randomly choose between tabs and spaces
            String indentation;
            if (random.nextBoolean()) {
                // Use spaces with random count
                int spaces = random.nextInt(Math.max(1, originalIndent + 3));
                indentation = " ".repeat(spaces);
            } else {
                // Use tabs with random count
                int tabs = random.nextInt(Math.max(1, (originalIndent / 4) + 2));
                indentation = "\t".repeat(tabs);
            }
            
            // Sometimes mix tabs and spaces for maximum chaos
            if (random.nextInt(100) < shittificationLevel * 5) {
                indentation = mixTabsAndSpaces(indentation);
            }
            
            result.append(indentation).append(trimmedLine).append("\n");
        }
        
        return result.toString();
    }
    
    private String mixTabsAndSpaces(String indentation) {
        StringBuilder mixed = new StringBuilder();
        for (char c : indentation.toCharArray()) {
            if (random.nextBoolean()) {
                mixed.append(c);
            } else {
                mixed.append(c == '\t' ? "    " : "\t");
            }
        }
        return mixed.toString();
    }
    
    private String messUpSpacing(String content) {
        // Add or remove spaces around various elements
        Pattern pattern = Pattern.compile("([,;])");
        Matcher matcher = pattern.matcher(content);
        StringBuffer result = new StringBuffer();
        
        while (matcher.find()) {
            String char_ = matcher.group(1);
            int spaces = random.nextInt(4);
            String replacement;
            if (random.nextBoolean()) {
                replacement = char_ + " ".repeat(spaces);
            } else {
                replacement = " ".repeat(spaces) + char_;
            }
            matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(result);
        
        return result.toString();
    }
    
    private String randomizeBraceStyle(String content) {
        // Sometimes put opening braces on same line, sometimes on new line
        String[] lines = content.split("\n", -1);
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            
            // Find lines that might have braces
            if (line.contains("{")) {
                if (random.nextInt(100) < 40) {
                    // Move brace to next line
                    String beforeBrace = line.substring(0, line.indexOf("{")).trim();
                    String afterBrace = line.substring(line.indexOf("{") + 1);
                    
                    result.append(beforeBrace).append("\n");
                    result.append(" ".repeat(random.nextInt(8))).append("{");
                    if (!afterBrace.trim().isEmpty()) {
                        result.append(afterBrace);
                    }
                    result.append("\n");
                    continue;
                }
            }
            
            result.append(line).append("\n");
        }
        
        return result.toString();
    }
    
    private String addRandomBlankLines(String content) {
        String[] lines = content.split("\n", -1);
        StringBuilder result = new StringBuilder();
        
        for (String line : lines) {
            // Randomly add 0-3 blank lines before this line
            if (random.nextInt(100) < shittificationLevel * 3) {
                int blankLines = random.nextInt(4);
                result.append("\n".repeat(blankLines));
            }
            
            result.append(line).append("\n");
        }
        
        return result.toString();
    }
    
    private String removeUsefulBlankLines(String content) {
        // Remove intentional blank lines between methods/classes
        content = content.replaceAll("\n\n\n+", "\n");
        
        // Sometimes remove blank lines completely
        if (random.nextInt(100) < shittificationLevel * 2) {
            content = content.replaceAll("\n\n", "\n");
        }
        
        return content;
    }
    
    private String randomizeOperatorSpacing(String content) {
        // Mess up spacing around operators
        String[] operators = {"+", "-", "*", "/", "=", "==", "!=", "&&", "||", "<", ">", "<=", ">="};
        
        for (String op : operators) {
            String escaped = Pattern.quote(op);
            Pattern pattern = Pattern.compile("\\s*" + escaped + "\\s*");
            Matcher matcher = pattern.matcher(content);
            StringBuffer result = new StringBuffer();
            
            while (matcher.find()) {
                int leftSpaces = random.nextInt(4);
                int rightSpaces = random.nextInt(4);
                String replacement = " ".repeat(leftSpaces) + op + " ".repeat(rightSpaces);
                matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
            }
            matcher.appendTail(result);
            content = result.toString();
        }
        
        return content;
    }
    
    private String addTrailingSpaces(String content) {
        String[] lines = content.split("\n", -1);
        StringBuilder result = new StringBuilder();
        
        for (String line : lines) {
            result.append(line);
            // Add random trailing spaces
            if (random.nextInt(100) < shittificationLevel * 8) {
                result.append(" ".repeat(random.nextInt(10)));
            }
            result.append("\n");
        }
        
        return result.toString();
    }
    
   private String maxChaosMode(String content) {
    // Ultimate chaos: randomly break lines (but not critical ones)
    String[] lines = content.split("\n", -1);
    StringBuilder result = new StringBuilder();
    
    for (String line : lines) {
        String trimmedLine = line.trim();
        
        // NEVER break these critical lines
        if (trimmedLine.startsWith("package ") ||
            trimmedLine.startsWith("import ") ||
            trimmedLine.startsWith("@") ||
            trimmedLine.startsWith("//") ||
            trimmedLine.startsWith("/*") ||
            trimmedLine.startsWith("*") ||
            trimmedLine.contains("\"") ||  // Lines with strings
            line.length() <= 40) {
            
            result.append(line).append("\n");
            continue;
        }
        
        // Only break long, safe lines (method calls, conditions, etc.)
        if (random.nextInt(100) < 30) {
            // Find a safe break point (after operators, commas, parentheses)
            int breakPoint = findSafeBreakPoint(line);
            
            if (breakPoint > 0 && breakPoint < line.length() - 1) {
                result.append(line.substring(0, breakPoint)).append("\n");
                result.append(" ".repeat(random.nextInt(15)));
                result.append(line.substring(breakPoint).trim()).append("\n");
            } else {
                result.append(line).append("\n");
            }
        } else {
            result.append(line).append("\n");
        }
    }
    
    return result.toString();
}

private int findSafeBreakPoint(String line) {
    // Find safe break points: after operators, commas, semicolons, opening braces
    char[] safeBreakChars = {',', ';', '{', '(', '&', '|', '+', '-', '*', '/'};
    
    // Look for break points in the middle section of the line
    int minBreak = line.length() / 3;
    int maxBreak = (2 * line.length()) / 3;
    
    for (int i = maxBreak; i >= minBreak; i--) {
        char c = line.charAt(i);
        for (char breakChar : safeBreakChars) {
            if (c == breakChar) {
                return i + 1; // Break after the character
            }
        }
    }
    
    return -1; // No safe break point found
}    
    private void printStatistics() {
        System.out.println("\n" + "-".repeat(80));
        System.out.println("Shittification Complete!");
        System.out.println("-".repeat(80));
        System.out.println("Files Scanned: " + filesProcessed);
        System.out.println("Files Shittified: " + filesShittified);
        System.out.println("Lines Affected: " + linesShittified);
        
        if (dryRun) {
            System.out.println("\nThis was a dry run. No files were actually modified.");
        } else if (createBackups && backupPath != null) {
            System.out.println("\nBackup folder created at: " + backupPath.toAbsolutePath());
        }
        
        System.out.println("\nYour code is now officially shittier! Enjoy the chaos!");
        System.out.println("-".repeat(80));
    }
    
    private static void printUsage() {
        System.out.println("""
            CodeShittifier - Making Code Shittier Since 2026
            
            Usage: java CodeShittifier [OPTIONS] <path>
            
            Options:
              -d, --dry-run       Show what would be changed without modifying files
              --no-backup         Don't create backup folder (dangerous!)
              -l, --level <1-10>  Shittification level (default: 5)
              -v, --verbose       Show detailed output
            
            Examples:
              # Dry run to see what would happen
              java CodeShittifier --dry-run ./src
              
              # Shittify with medium chaos
              java CodeShittifier --level 5 ./src/main/java
              
              # Maximum chaos mode (YOLO)
              java CodeShittifier --level 10 ./myproject
              
              # Process a single file
              java CodeShittifier MyClass.java
            
            WARNING: This is a parody tool for educational/entertainment purposes.
                     Always commit your code before running this tool!
                     The maintainers are not responsible for any tears, rage quits,
                     or team members questioning your sanity.
            
            Remember: With great power comes great responsibility to make code shittier!
            """);
    }
}
// Checked: 2026-03-24
// Updated: 2026-03-31
