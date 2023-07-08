package tugasbesar;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TugasBesar extends JFrame {
    private int x, y, radius;
    private String objectName;
    private boolean isInputNameDone;
    private boolean isDrawing;

    public TugasBesar() {
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        isInputNameDone = false;
        isDrawing = false;

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (isInputNameDone && !isDrawing) {
                    x = e.getX();
                    y = e.getY();
                    isDrawing = true;
                    repaint();
                } else if (!isInputNameDone) {
                    objectName = JOptionPane.showInputDialog(null, "Masukkan nama objek:", "Input Nama Objek", JOptionPane.QUESTION_MESSAGE);
                    if (objectName != null && !objectName.isEmpty()) {
                        isInputNameDone = true;
                    }
                }
            }

            public void mouseReleased(MouseEvent e) {
                if (isInputNameDone && isDrawing) {
                    showOutputDialog();
                    isDrawing = false;
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (isDrawing) {
                    int currentX = e.getX();
                    int currentY = e.getY();
                    int deltaX = currentX - x;
                    int deltaY = currentY - y;
                    radius = (int) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
                    repaint();
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setFocusable(true);
        requestFocus();
    }

    private void showOutputDialog() {
        String message = "Objek Lingkaran dengan nama '" + objectName + "' dan koordinat pusat (" + x + ", " + y + ") serta radius " + radius;
        JOptionPane.showMessageDialog(null, message, "Output Teks", JOptionPane.INFORMATION_MESSAGE);
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (isInputNameDone && isDrawing) {
            g.setColor(Color.RED);
            int diameter = radius * 2;
            int xStart = x - radius;
            int yStart = y - radius;
            g.drawOval(xStart, yStart, diameter, diameter);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TugasBesar();
            }
        });
    }
}
