import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class SudokuBoard extends JFrame {
    private String[][] boardData = new String[9][9];
    private static int[][] boardDataIntegers = new int[9][9];
    public SudokuBoard() {
        setTitle("Sudoku Board");
        setSize(450, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel sudokuPanel = new JPanel(new GridLayout(9, 9));
        JTextField[][] textFields = new JTextField[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                textFields[i][j] = new JTextField();
                textFields[i][j].setHorizontalAlignment(JTextField.CENTER);
                sudokuPanel.add(textFields[i][j]);

                ((AbstractDocument) textFields[i][j].getDocument()).setDocumentFilter(new DocumentFilter() {
                    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                        String newValue = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                        if (newValue.length() <= 1 && newValue.matches("\\d")) {
                            super.replace(fb, offset, length, text, attrs);
                        }
                    }
                });



                // Add the grid to the frame
                getContentPane().add(sudokuPanel, BorderLayout.CENTER);
                setVisible(true);
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        new SudokuBoard();
    }
}