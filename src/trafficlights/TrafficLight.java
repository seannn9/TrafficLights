package trafficlights;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TrafficLight {
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JLabel label = new JLabel();
	Timer timer = new Timer(500, new TimerEvent());
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
		panel.setBackground(Color.WHITE);
		panel.setBounds(10,10, 365, 100);
		panel.add(label);
	}
	
	// method for the label that shows the timer
	public void Label() {
		label.setText("" + time);
		label.setFont(font);
		label.setBackground(Color.WHITE);
	}
	
	// class timer event to simulate the alternating colors of traffic lights depending on the timer
	public class TimerEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			time--;
			label.setText("" + time);
			if (time == 5 && shape.getA() == Color.GREEN) {
				shape.setColor1(Color.GRAY, Color.YELLOW, Color.GRAY);
			} 
			if (time == 5 && shape.getColorX() == Color.GREEN) {
				shape.setColor2(Color.GRAY, Color.YELLOW, Color.GRAY);
			}
			if (time == 0) {
				if (cycle) {
					shape.setColor1(Color.GREEN, Color.GRAY, Color.GRAY);
					shape.setColor2(Color.GRAY, Color.GRAY, Color.RED);
					cycle = false;
				} else {
					shape.setColor1(Color.GRAY, Color.GRAY, Color.RED);
					shape.setColor2(Color.GREEN, Color.GRAY, Color.GRAY);
					cycle = true;
				} time = 20;
			}
			shape.repaint();
		}
	}
	
	// class for the shapes/circles that displays the colors
	@SuppressWarnings("serial")
	public class Shapes extends JComponent {
		Color A = Color.GREEN;
		Color B = Color.gray;
		Color C = Color.gray;
		Color X = Color.gray;
		Color Y = Color.gray;
		Color Z = Color.RED;
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
		
		// getter for the first circle in column 1
		public Color getA() {
			return A;
		}
		
		// getter for the first circle in column 2
		public Color getColorX() {
			return X;
		}
	}
}