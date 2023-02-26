import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public class SudokuBoard extends JFrame {
    public SudokuBoard(){
      setTitle("Sudoku");
      setSize(450, 450);
      setDefaultCloseOperation(EXIT_ON_CLOSE);


      JPanel sudoku = new JPanel(new GridLayout(9, 9));
      JTextField[][] numFields = new JTextField[9][9];

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                numFields[row][column] = new JTextField();
                numFields[row][column].setHorizontalAlignment(JTextField.CENTER);
                sudoku.add(numFields[row][column]);

                ((AbstractDocument) numFields[row][column].getDocument()).setDocumentFilter(new DocumentFilter() {
                    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                        String newValue = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                        if (newValue.length() <= 1 && newValue.matches("\\d")) {
                            super.replace(fb, offset, length, text, attrs);
                        }
                    }
                });
            }
        }


      getContentPane().add(sudoku, BorderLayout.CENTER);
      setVisible(true);
    }
    public static void main(String[] args) {
        new SudokuBoard();
    }
}
