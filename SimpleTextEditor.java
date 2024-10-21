import java.util.Stack;
import java.util.Scanner;

public class SimpleTextEditor {
    private StringBuilder text;
    private Stack<TextOperation> operationsStack;

    public String getText() {
        return text.toString();
    }

    // Inner class to represent operations
    private class TextOperation {
        String operationType;  // "add" or "delete"
        char character;        // Character in the operation

        public TextOperation(String operationType, char character) {
            this.operationType = operationType;
            this.character = character;
        }
    }

    public SimpleTextEditor() {
        text = new StringBuilder();
        operationsStack = new Stack<>();
    }

    // Add a character to the text
    public void addText(char character) {
        text.append(character);
        operationsStack.push(new TextOperation("add", character));
        displayText();
    }

    // Delete the last character
    public void deleteText() {
        if (text.length() > 0) {
            char lastChar = text.charAt(text.length() - 1);
            text.deleteCharAt(text.length() - 1);
            operationsStack.push(new TextOperation("delete", lastChar));
        }
        displayText();
    }

    // Undo the last operation
    public void undo() {
        if (!operationsStack.isEmpty()) {
            TextOperation lastOperation = operationsStack.pop();
            if ("add".equals(lastOperation.operationType)) {
                text.deleteCharAt(text.length() - 1);
            } else if ("delete".equals(lastOperation.operationType)) {
                text.append(lastOperation.character);
            }
        }
        displayText();
    }

    // Display the current state
    public void displayText() {
        System.out.println("Current Text: " + text.toString());
    }

    public static void main(String[] args) {
        SimpleTextEditor editor = new SimpleTextEditor();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Simple Text Editor");
        while (true) {
            System.out.println("\nOptions: (1) Add Text (2) Delete Text (3) Undo (4) Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter a character to add: ");
                    char ch = scanner.nextLine().charAt(0);
                    editor.addText(ch);
                    break;
                case 2:
                    editor.deleteText();
                    break;
                case 3:
                    editor.undo();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
