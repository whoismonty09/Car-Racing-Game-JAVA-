import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class CarRacingGame extends JPanel implements ActionListener, KeyListener {

    Timer timer;
    int carX = 200, carY = 450;
    int enemyX = 100, enemyY = -100;
    int score = 0;
    Random rand = new Random();

    Image car, enemy, road;

    public CarRacingGame() {
        car = new ImageIcon("car.png").getImage();
        enemy = new ImageIcon("enemy.png").getImage();
        road = new ImageIcon("road.png").getImage();

        timer = new Timer(20, this);
        timer.start();
        addKeyListener(this);
        setFocusable(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(road, 0, 0, 500, 600, null);
        g.drawImage(car, carX, carY, 60, 100, null);
        g.drawImage(enemy, enemyX, enemyY, 60, 100, null);

        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Score: " + score, 20, 30);
    }

    public void actionPerformed(ActionEvent e) {
        enemyY += 5;

        if (enemyY > 600) {
            enemyY = -100;
            enemyX = rand.nextInt(400);
            score++;
        }

        Rectangle playerRect = new Rectangle(carX, carY, 60, 100);
        Rectangle enemyRect = new Rectangle(enemyX, enemyY, 60, 100);

        if (playerRect.intersects(enemyRect)) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "Game Over! Score: " + score);
            System.exit(0);
        }

        repaint();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT && carX > 0) carX -= 20;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && carX < 430) carX += 20;
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Car Racing Game developed by Monty");
        CarRacingGame game = new CarRacingGame();
        frame.add(game);
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
