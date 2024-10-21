import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SimpleTextEditorTest {
    private SimpleTextEditor editor;

    @Before
    public void setUp() {
        editor = new SimpleTextEditor();
    }

    // Test adding text to the editor
    @Test
    public void testAddText() {
        editor.addText('a');
        assertEquals("Expected text to be 'a' after adding 'a'", "a", editor.getText());
    }

    // Test deleting text from the editor
    @Test
    public void testDeleteText() {
        editor.addText('a');
        editor.deleteText();
        assertEquals("Expected text to be empty after deleting last character", "", editor.getText());
    }

    // Test the undo functionality after adding text
    @Test
    public void testUndoAddition() {
        editor.addText('a');
        editor.undo();
        assertEquals("Expected text to be empty after undoing addition", "", editor.getText());
    }

    // Test the undo functionality after deleting text
    @Test
    public void testUndoDeletion() {
        editor.addText('a');
        editor.deleteText();
        editor.undo();
        assertEquals("Expected text to be 'a' after undoing deletion", "a", editor.getText());
    }

    // Test multiple consecutive undos
    @Test
    public void testMultipleUndos() {
        editor.addText('a');
        editor.addText('b');
        editor.undo();
        editor.undo();
        assertEquals("Expected text to be empty after undoing all operations", "", editor.getText());
    }

    // Test undo operation when no operations have been performed
    @Test
    public void testUndoWithNoOperations() {
        editor.undo();
        assertEquals("Expected no change to text when no operations have been performed", "", editor.getText());
    }
}
