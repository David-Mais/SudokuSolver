import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class SudokuBoard extends JFrame {
    SudokuSolver sudokuSolver = new SudokuSolver();
    private static int[][] boardDataIntegers = new int[9][9];
    private static String[][] boardData = new String[9][9];
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

                //adding keylisteners to every textbox
                textFields[i][j].addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                            // Transfer contents of board to string matrix
                            for (int k = 0; k < 9; k++) {
                                for (int l = 0; l < 9; l++) {
                                    String textBoxData = textFields[k][l].getText();
                                    if(textBoxData.isEmpty()){
                                        textBoxData = "0";
                                    }
                                    boardDataIntegers[k][l] = Integer.parseInt(textBoxData);
                                }
                            }

                            SudokuSolver.solver(boardDataIntegers);
                            SudokuSolver.printGrid(boardDataIntegers);

                            // Dispose the JFrame
                            dispose();
                        }
                    }
                });

                //making sure each textbox takes at most one integer of size 1 mening from 0 to 9
                ((AbstractDocument) textFields[i][j].getDocument()).setDocumentFilter(new DocumentFilter() {
                    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                        String newValue = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                        if (newValue.length() <= 1 && newValue.matches("\\d")) {
                            super.replace(fb, offset, length, text, attrs);
                        }
                    }
                });

                sudokuPanel.add(textFields[i][j]);
            }

        }
        getContentPane().add(sudokuPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) throws InterruptedException {
        new SudokuBoard();
    }
}