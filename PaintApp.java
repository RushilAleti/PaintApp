import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class PaintApp extends JPanel implements MouseMotionListener, MouseListener {
    private ArrayList<Point> points = new ArrayList<>();
    private ArrayList<Color> colors = new ArrayList<>();
    private Color currentColor = Color.BLACK;

    public PaintApp() {
        addMouseMotionListener(this);
        addMouseListener(this);
        setBackground(Color.WHITE);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (int i = 0; i < points.size(); i++) {
            g2.setColor(colors.get(i));
            Point p = points.get(i);
            g2.fillOval(p.x, p.y, 8, 8);
        }
    }

    public void mouseDragged(MouseEvent e) {
        points.add(e.getPoint());
        colors.add(currentColor);
        repaint();
    }

    public void mousePressed(MouseEvent e) {
        points.add(e.getPoint());
        colors.add(currentColor);
        repaint();
    }

    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Paint App");
        PaintApp canvas = new PaintApp();

        JButton clearButton = new JButton("Clear");
        JButton colorButton = new JButton("Choose Color");

        clearButton.addActionListener(e -> {
            canvas.points.clear();
            canvas.colors.clear();
            canvas.repaint();
        });

        colorButton.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(frame, "Pick a Color", canvas.currentColor);
            if (newColor != null) {
                canvas.currentColor = newColor;
            }
        });

        JPanel controls = new JPanel();
        controls.add(colorButton);
        controls.add(clearButton);

        frame.setLayout(new BorderLayout());
        frame.add(canvas, BorderLayout.CENTER);
        frame.add(controls, BorderLayout.SOUTH);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}