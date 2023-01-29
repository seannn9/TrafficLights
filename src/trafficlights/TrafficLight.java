package trafficlights;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class TrafficLight {
	File file = new File("Button-click-sound.wav");
	JFrame frame = new JFrame();
	JButton stopButton = new JButton();
	JButton addTimeButton = new JButton();
	JButton subTimeButton = new JButton();
	JPanel panel = new JPanel();
	JLabel timeLabel = new JLabel();
	Timer timer = new Timer(1000, new TimerEvent());
	Shapes shape = new Shapes();
	Font font = new Font("Tahoma", Font.BOLD, 70);
	Font buttonFont = new Font("Tahoma", Font.BOLD, 20);
	int time = 20;
	boolean cycle = false;
	boolean run = false;
	
	TrafficLight() {
		Frame(); 
		Button();
		Panel();
		Label();
		timer.start();
	}
	
	public void Frame() {
		frame.setSize(400, 700); // 400, 640
		frame.setLocation(600, 200);
		frame.setTitle("Traffic Light Simulation");
		frame.setVisible(true);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(stopButton);
		frame.add(addTimeButton);
		frame.add(subTimeButton);
		frame.add(panel);
		frame.add(timeLabel);
		frame.add(shape);
	}

	public void Button() {
		// stop button properties
		stopButton.setSize(100,  50);
		stopButton.setText("STOP");
		stopButton.setFont(buttonFont);
		stopButton.setLocation(140, 600);
		stopButton.setForeground(Color.RED);
		stopButton.setHorizontalAlignment(SwingConstants.CENTER);
		stopButton.addActionListener(new StopButtonEvent());
		// add time button properties
		addTimeButton.setSize(50, 25);
		addTimeButton.setText("+");
		addTimeButton.setFont(buttonFont);
		addTimeButton.setLocation(15, 615);
		addTimeButton.setForeground(Color.GREEN);
		addTimeButton.setBackground(Color.DARK_GRAY);
		addTimeButton.setHorizontalAlignment(SwingConstants.CENTER);
		addTimeButton.addActionListener(new AddTimeButtonEvent());
		// subtract time button properties
		subTimeButton.setSize(50, 25);
		subTimeButton.setText("-");
		subTimeButton.setFont(buttonFont);
		subTimeButton.setLocation(70, 615);
		subTimeButton.setForeground(Color.RED);
		subTimeButton.setBackground(Color.DARK_GRAY);
		subTimeButton.setHorizontalAlignment(SwingConstants.CENTER);
		subTimeButton.addActionListener(new SubTimeButtonEvent());
	}
	
	public void Panel() {
		panel.setBackground(Color.GRAY);
		panel.setBounds(10,10, 365, 100);
		panel.add(timeLabel);
	}
	
	public void Label() {
		timeLabel.setText("" + time);
		timeLabel.setFont(font);
		timeLabel.setForeground(Color.BLACK);
	}
	
	// plays the audio whenever its called
	public void playAudio() {
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
			e1.printStackTrace();
		}
	}
	
	// timer event that changes the lights depending on the time left
	public class TimerEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Color def = Color.DARK_GRAY;
			Color go = Color.GREEN;
			Color stop = Color.RED;
			Color ready = Color.YELLOW;
			time--;
			timeLabel.setText("" + time);
			if (time == 5 && shape.getColorA() == Color.GREEN) {
				shape.setColor1(def, ready, def);
				playAudio(); // plays audio (button-click sound) when the colors change
			} 
			if (time == 5 && shape.getColorX() == Color.GREEN) {
				shape.setColor2(def, ready, def);
				playAudio();
			}
			if (time == 0) {
				if (cycle) {
					shape.setColor1(def, def, go);
					shape.setColor2(stop, def, def);
					playAudio();
					cycle = false;
				} else {
					shape.setColor1(stop, def, def);
					shape.setColor2(def, def, go);
					playAudio();
					cycle = true;
				} time = 20;
			}
			shape.repaint();
		}
	}
	
	// actions specifically for the stop/start button
	public class StopButtonEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (run) {
				stopButton.setText("STOP");
				stopButton.setForeground(Color.RED);
				timer.start();
				run = false;
			} else {
				stopButton.setText("START");
				stopButton.setForeground(Color.GREEN);
				timer.stop();
				run = true;
			}
		}
	}
	
	// add 5 seconds increment to timer for the current count-down cycle (doesn't apply to the next count-down cycle)
	public class AddTimeButtonEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (time >= 96) {
				JOptionPane.showMessageDialog(null, "Cannot exceed 100 seconds", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				time += 5;
				timeLabel.setText("" + time);
			}
		}
	}
	
	// subtract 5 seconds increment to timer for the current count-down cycle (doesn't apply to next count-down cycle)
	public class SubTimeButtonEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (time <= 14) {
				JOptionPane.showMessageDialog(null, "Cannot be less than 10 seconds", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				time -= 5;
				timeLabel.setText("" + time);
			}
		}	
	}
 }