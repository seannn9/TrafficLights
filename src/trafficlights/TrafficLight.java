package trafficlights;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TrafficLight {
	JFrame frame = new JFrame();
	JButton button = new JButton();
	JButton addTimeButton = new JButton();
	JButton subTimeButton = new JButton();
	JPanel panel = new JPanel();
	JLabel label = new JLabel();
	Timer timer = new Timer(500, new TimerEvent());
	Shapes shape = new Shapes();
	Font font = new Font("Tahoma", Font.BOLD, 70);
	Font buttonFont = new Font("Tahoma", Font.BOLD, 20);
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
		frame.add(addTimeButton);
		frame.add(subTimeButton);
		frame.add(panel);
		frame.add(label);
		frame.add(shape);
	}

	// method for stop button
	public void Button() {
		button.setSize(100,  50);
		button.setText("STOP");
		button.setFont(buttonFont);
		button.setLocation(140, 600);
		button.setForeground(Color.RED);
		button.setHorizontalAlignment(SwingConstants.CENTER);
		button.addActionListener(new StopButtonEvent());
		// add time button
		addTimeButton.setSize(50, 25);
		addTimeButton.setText("+");
		addTimeButton.setFont(buttonFont);
		addTimeButton.setLocation(15, 615);
		addTimeButton.setForeground(Color.GREEN);
		addTimeButton.setBackground(Color.DARK_GRAY);
		addTimeButton.setHorizontalAlignment(SwingConstants.CENTER);
		addTimeButton.addActionListener(new AddTimeButtonEvent());
		// subtract time button
		subTimeButton.setSize(50, 25);
		subTimeButton.setText("-");
		subTimeButton.setFont(buttonFont);
		subTimeButton.setLocation(70, 615);
		subTimeButton.setForeground(Color.RED);
		subTimeButton.setBackground(Color.DARK_GRAY);
		subTimeButton.setHorizontalAlignment(SwingConstants.CENTER);
		subTimeButton.addActionListener(new SubTimeButtonEvent());	
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
		@Override
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
	public class StopButtonEvent implements ActionListener {
		@Override
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
	
	// add 5 seconds increment to timer for the current count-down cycle (doesn't apply to the next count-down cycle)
	public class AddTimeButtonEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (time >= 96) { // limit time to be less than 100 seconds
				System.out.println("Cannot exceed 100 seconds");
			} else {
				time += 5;
				label.setText("" + time);
			}
		}
	}
	
	// subtract 5 seconds increment to timer for the current count-down cycle (doesn't apply to next count-down cycle)
	public class SubTimeButtonEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (time <= 14) { // limit time to be not less than 10 seconds
				System.out.println("Cannot be less than 10 seconds");
			} else {
				time -= 5;
				label.setText("" + time);
			}
		}	
	}
 }