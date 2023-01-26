package trafficlights;
// Traffic Light program that simulates how a traffic light functions based on a timer
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TrafficLight {
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JLabel label = new JLabel();
	Timer timer = new Timer(1000, new TimerEvent());
	Shapes shape = new Shapes();
	Font font = new Font("Tahoma", Font.BOLD, 70);
	int time = 20;
	boolean cycle = false;
	
	// constructor that initializes the contents in the main class
	TrafficLight() {
		Frame();
		Panel();
		Label();
		timer.start();
	}
	
	// method for the frame or the whole window
	public void Frame() {
		frame.setSize(400, 640);
		frame.setLocation(600, 200);
		frame.setTitle("Traffic Light Simulation");
		frame.setVisible(true);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.add(label);
		frame.add(shape);
	}
	
	// method for the panel
	public void Panel() {
		panel.setBackground(Color.GRAY);
		panel.setBounds(10,10, 365, 100);
		panel.add(label);
	}
	
	// method for the label that shows the timer
	public void Label() {
		label.setText("" + time);
		label.setFont(font);
		label.setForeground(Color.BLACK);
	}
	
	// class timer event to simulate the alternating colors of traffic lights depending on the timer
	public class TimerEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Color def = Color.DARK_GRAY;
			Color go = Color.GREEN;
			Color stop = Color.RED;
			Color ready = Color.YELLOW;
			time--;
			label.setText("" + time);
			if (time == 5 && shape.getColorA() == Color.GREEN) {
				shape.setColor1(def, ready, def);
			} 
			if (time == 5 && shape.getColorX() == Color.GREEN) {
				shape.setColor2(def, ready, def);
			}
			if (time == 0) {
				if (cycle) {
					shape.setColor1(def, def, go);
					shape.setColor2(stop, def, def);
					cycle = false;
				} else {
					shape.setColor1(stop, def, def);
					shape.setColor2(def, def, go);
					cycle = true;
				} time = 20;
			}
			shape.repaint();
		}
	}
	
	// class for the shapes/circles that displays the colors
	@SuppressWarnings("serial")
	public class Shapes extends JComponent {
		Color A = Color.DARK_GRAY;
		Color B = Color.DARK_GRAY;
		Color C = Color.GREEN;
		Color X = Color.RED;
		Color Y = Color.DARK_GRAY;
		Color Z = Color.DARK_GRAY;
		int h = 150, w = 150;
		public void paintComponent(Graphics g) {
			// Column 1 circles
			g.setColor(A);
			g.fillOval(20, 120, w, h);
			g.setColor(B);
			g.fillOval(20, 280, w, h);
			g.setColor(C);
			g.fillOval(20, 440, w, h);
			// Column 2 circles
			g.setColor(X);
			g.fillOval(215, 120, w, h);
			g.setColor(Y);
			g.fillOval(215, 280, w, h);
			g.setColor(Z);
			g.fillOval(215, 440, w, h);
		}
		
		// setter for column 1 circles
		public void setColor1(Color A, Color B, Color C) {
			this.A = A;
			this.B = B;
			this.C = C;
		}
		
		// setter for column 2 circles
		public void setColor2(Color X, Color Y, Color Z) {
			this.X = X;
			this.Y = Y;
			this.Z = Z;
		}
		
		// getter for the third circle in column 1
		public Color getColorA() {
			return C;
		}
		
		// getter for the third circle in column 2
		public Color getColorX() {
			return Z;
		}
	}
}