package trafficlights;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TrafficLight {
	JFrame frame = new JFrame();
	JButton button = new JButton();
	JPanel panel = new JPanel();
	JLabel label = new JLabel();
	Timer timer = new Timer(1000, new TimerEvent());
	Shapes shape = new Shapes();
	Font font = new Font("Tahoma", Font.BOLD, 70);
	int time = 20;
	boolean cycle = false;
	boolean run = false;
	
	// constructor that initializes the contents in the main class
	TrafficLight() {
		Frame(); 
		Button();
		Panel();
		Label();
		timer.start();
	}
	
	// method for the frame or the whole window
	public void Frame() {
		frame.setSize(400, 700); // 400, 640
		frame.setLocation(600, 200);
		frame.setTitle("Traffic Light Simulation");
		frame.setVisible(true);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(button);
		frame.add(panel);
		frame.add(label);
		frame.add(shape);
	}

	// method for stop button
	public void Button() {
		button.setSize(100,  50);
		button.setText("STOP");
		button.setFont(new Font("Tahoma", Font.BOLD, 20));
		button.setLocation(140, 600);
		button.setForeground(Color.RED);
		button.setHorizontalAlignment(SwingConstants.CENTER);
		button.addActionListener(new ButtonEvent());
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
	
	// class for the button event that stop/start the timer
	public class ButtonEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (run) {
				button.setText("STOP");
				button.setForeground(Color.RED);
				timer.start();
				run = false;
			} else {
				button.setText("START");
				button.setForeground(Color.GREEN);
				timer.stop();
				run = true;
			}
		}
	}
}