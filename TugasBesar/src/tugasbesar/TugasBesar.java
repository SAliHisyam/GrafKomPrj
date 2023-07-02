package tugasbesar;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TugasBesar extends JPanel {
    private List<Line> lines;
    private boolean isDrawing;
    private Line currentLine;
    private String currentObjectName;

    public TugasBesar() {
        lines = new ArrayList<>();
        isDrawing = false;
        currentLine = null;
        currentObjectName = "";

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!isDrawing) {
                    currentLine = new Line();
                    currentLine.x1 = e.getX();
                    currentLine.y1 = e.getY();
                    isDrawing = true;
                } else {
                    currentLine.x2 = e.getX();
                    currentLine.y2 = e.getY();
                    lines.add(currentLine);
                    isDrawing = false;
                    printLineCoordinates(currentLine);
                    tampilkanGaris(currentObjectName, currentLine.x1, currentLine.y1, currentLine.x2, currentLine.y2);
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (Line line : lines) {
            g2d.drawLine(line.x1, line.y1, line.x2, line.y2);
        }
    }

    public void printLineCoordinates(Line line) {
        System.out.println("Nama Objek: " + currentObjectName);
        System.out.println("Koordinat Garis:");
        System.out.println("Titik Awal: (" + line.x1 + ", " + line.y1 + ")");
        System.out.println("Titik Akhir: (" + line.x2 + ", " + line.y2 + ")");
        System.out.println();
    }

    public static void tampilkanGaris(String namaObjek, int x1, int y1, int x2, int y2) {
        System.out.println("Objek: " + namaObjek);
        System.out.println("Koordinat awal: (" + x1 + ", " + y1 + ")");
        System.out.println("Koordinat akhir: (" + x2 + ", " + y2 + ")");

        // Menampilkan grafik garis secara sederhana
        int minX = Math.min(x1, x2);
        int maxX = Math.max(x1, x2);
        int minY = Math.min(y1, y2);
        int maxY = Math.max(y1, y2);

        for (int y = maxY; y >= minY; y--) {
            for (int x = minX; x <= maxX; x++) {
                if ((x == x1 && y == y1) || (x == x2 && y == y2)) {
                    System.out.print("*"); // Simbol untuk titik garis
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Program Membuat Garis");
        TugasBesar panel = new TugasBesar();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(100, 100);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        System.out.println("===== Program Membuat Garis =====");
        System.out.println("Klik mouse untuk menggambar garis. Klik pertama menandai titik awal, klik kedua menandai titik akhir.");
        System.out.println("Masukkan nama objek (hingga 10 karakter):");

        Scanner scanner = new Scanner(System.in);
        panel.currentObjectName = scanner.nextLine();
    }

    private static class Line {
        public int x1, y1, x2, y2;
    }
}