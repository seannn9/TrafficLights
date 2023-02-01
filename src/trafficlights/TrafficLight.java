package trafficlights;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
import javax.sound.sampled.*;

public class TrafficLight {
	// JFrame
	JFrame frame = new JFrame();
	// JButton
	JButton stopButton = new JButton();
	JButton addTimeButton = new JButton();
	JButton subTimeButton = new JButton();
	JButton helpButton = new JButton();
	JButton manualButton = new JButton();
	JButton redButton = new JButton();
	JButton yellowButton = new JButton();
	JButton greenButton = new JButton();
	JButton northSouthButton = new JButton();
	JButton eastWestButton = new JButton();
	// JPanel
	JPanel panel = new JPanel();
	// JLabel
	JLabel timeLabel = new JLabel();
	JLabel northSouth = new JLabel();
	JLabel eastWest = new JLabel();
	// Others
	Timer timer = new Timer(1000, new TimerEvent());
	Shapes shape = new Shapes();
	Font font = new Font("Tahoma", Font.BOLD, 70);
	Font buttonFont = new Font("Tahoma", Font.BOLD, 20);
	File audioFile = new File("C:\\Users\\naesk\\Documents\\JAVA\\TrafficLights_New\\src\\sound\\click.wav");
	// Variables
	int time = 20;
	boolean cycle = false;
	boolean run = false, modeRun = false;
	String direction, mode = "Auto";
	Color def = Color.DARK_GRAY;
	Color go = Color.GREEN;
	Color stop = Color.RED;
	Color ready = Color.YELLOW;
	
	TrafficLight() {
		Frame(); 
		Button();
		Panel();
		Label();
		timer.start();
	}
	
	public void Frame() {
		frame.setSize(400, 700);
		frame.setLocation(600, 200);
		frame.setTitle("Traffic Light Simulation");
		frame.setVisible(true);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(stopButton);
		frame.add(addTimeButton);
		frame.add(subTimeButton);
		frame.add(helpButton);
		frame.add(manualButton);
		frame.add(northSouthButton);
		frame.add(eastWestButton);
		frame.add(redButton);
		frame.add(yellowButton);
		frame.add(greenButton);
		frame.add(panel);
		frame.add(timeLabel);
		frame.add(shape);
	}

	public void Button() {
		// button that stops/starts the timer when clicked
		stopButton.setSize(100,  50);
		stopButton.setText("STOP");
		stopButton.setFont(buttonFont);
		stopButton.setLocation(140, 600);
		stopButton.setForeground(Color.RED);
		stopButton.setHorizontalAlignment(SwingConstants.CENTER);
		stopButton.addActionListener(new StopButtonEvent());
		// button that adds 5 seconds to timer
		addTimeButton.setSize(50, 25);
		addTimeButton.setText("+");
		addTimeButton.setFont(buttonFont);
		addTimeButton.setLocation(15, 615);
		addTimeButton.setForeground(Color.GREEN);
		addTimeButton.setBackground(Color.DARK_GRAY);
		addTimeButton.setHorizontalAlignment(SwingConstants.CENTER);
		addTimeButton.addActionListener(new AddTimeButtonEvent());
		// button that subtracts 5 seconds to timer
		subTimeButton.setSize(50, 25);
		subTimeButton.setText("-");
		subTimeButton.setFont(buttonFont);
		subTimeButton.setLocation(80, 615);
		subTimeButton.setForeground(Color.RED);
		subTimeButton.setBackground(Color.DARK_GRAY);
		subTimeButton.setHorizontalAlignment(SwingConstants.CENTER);
		subTimeButton.addActionListener(new SubTimeButtonEvent());
		// button that shows help dialogue
		helpButton.setSize(45, 25);
		helpButton.setText("?");
		helpButton.setFont(buttonFont);
		helpButton.setLocation(330, 615);
		helpButton.setForeground(Color.ORANGE);
		helpButton.setBackground(Color.DARK_GRAY);
		helpButton.addActionListener(new HelpButtonEvent());
		// button to set the colors manually 
		manualButton.setSize(75, 25);
		manualButton.setText("Manual");
		manualButton.setLocation(250, 615);
		manualButton.setForeground(Color.WHITE);
		manualButton.setBackground(Color.DARK_GRAY);
		manualButton.addActionListener(new ManualButtonEvent());
		// manually set lights to red
		redButton.setSize(45, 30);
		redButton.setLocation(120, 700);
		redButton.setBackground(Color.RED);
		redButton.setVisible(false);
		redButton.addActionListener(new ColorButtonEvent());
		// manually set lights to yellow
		yellowButton.setSize(45, 30);
		yellowButton.setLocation(170, 700);
		yellowButton.setBackground(Color.YELLOW);
		yellowButton.setVisible(false);
		yellowButton.addActionListener(new ColorButtonEvent());
		// manually set lights to green
		greenButton.setSize(45, 30);
		greenButton.setLocation(220, 700);
		greenButton.setBackground(Color.GREEN);
		greenButton.setVisible(false);
		greenButton.addActionListener(new ColorButtonEvent());
		// change colors manually on north-south column
		northSouthButton.setSize(125, 25);
		northSouthButton.setLocation(30, 660);
		northSouthButton.setVisible(false);
		northSouthButton.setText("North-South");
		northSouthButton.addActionListener(new DirectionButtonEvent());
		// change colors manually on east west column
		eastWestButton.setSize(125, 25);
		eastWestButton.setLocation(230, 660);
		eastWestButton.setVisible(false);
		eastWestButton.setText("East-West");
		eastWestButton.addActionListener(new DirectionButtonEvent());
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
	
	public void playAudio() {
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
		} catch(UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
			e1.printStackTrace();
		}
	}
	
	// timer event that changes the lights depending on the time left
	public class TimerEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			time--;
			timeLabel.setText("" + time);
			if (time > 5) {
				timeLabel.setForeground(Color.BLACK);
			}
			if (time <= 5 && shape.getColorC() == Color.GREEN) {
				timeLabel.setForeground(Color.RED);
				shape.setColor1(def, ready, def);
				playAudio();
			} 
			if (time <= 5 && shape.getColorZ() == Color.GREEN) {
				timeLabel.setForeground(Color.RED);
				shape.setColor2(def, ready, def);
				playAudio();
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
				playAudio();
			}
			shape.repaint();
		}
	}
	
	// actions specifically for the stop/start button
	public class StopButtonEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (run) {
				timer.start();
				stopButton.setText("STOP");
				stopButton.setForeground(Color.RED);
				run = false;
			}  else {
				timer.stop();
				stopButton.setText("START");
				stopButton.setForeground(Color.GREEN);
				run = true;
			}
		}
	}
	
	// add 5 seconds increment to timer for the current count-down cycle (doesn't apply to the next count-down cycle)
	public class AddTimeButtonEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (time >= 196) {
				JOptionPane.showMessageDialog(null, "Cannot exceed 200 seconds", "Warning", JOptionPane.WARNING_MESSAGE);
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
			if (time <= 9) {
				JOptionPane.showMessageDialog(null, "Cannot be less than 5 seconds", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				time -= 5;
				timeLabel.setText("" + time);
			}
		}	
	}
	
	// shows information about the functions of the button
	public class HelpButtonEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			timer.stop();
			int ans = JOptionPane.showConfirmDialog(null, "STOP : Stop the timer\nSTART : Start the timer"
					+ "\n'+' : Add 5 seconds to timer\n'-' : Subtract 5 seconds to timer"
					+ "\nManual : Pick direction first to set colors manually"
					+ "\n   - North-South : change Column1 colors manually"
					+ "\n   - East-West : change Column2 colors manually"
					+ "\nAuto : Colors changes automatically (default)", "Help on Buttons", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (ans == JOptionPane.OK_OPTION || ans == JOptionPane.DEFAULT_OPTION) {
				if (mode.equals("Auto")) {
					timer.start();
				} 
			}
		}
	}
	
	// change color manually by the user or automatically by default
	public class ManualButtonEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (modeRun) {
				timeLabel.setText("" + time);
				timeLabel.setFont(font);
				manualButton.setText(mode);
				mode = "Auto";
				stopButton.setEnabled(modeRun);
				stopButton.setText("STOP");
				stopButton.setForeground(Color.RED);
				timer.start();
				frame.setSize(400, 700);
				northSouthButton.setVisible(false);
				eastWestButton.setVisible(false);
				redButton.setVisible(false);
				yellowButton.setVisible(false);
				greenButton.setVisible(false);
				addTimeButton.setEnabled(modeRun);
				subTimeButton.setEnabled(modeRun);
				modeRun = false;
			} else {
				timeLabel.setText("Manual Mode");
				timeLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
				stopButton.setText("STOP");
				manualButton.setText(mode);
				mode = "Manual";
				timer.stop();
				stopButton.setEnabled(modeRun);
				frame.setSize(400, 780);
				northSouthButton.setVisible(true);
				eastWestButton.setVisible(true);
				redButton.setVisible(true);
				yellowButton.setVisible(true);
				greenButton.setVisible(true);
				redButton.setEnabled(modeRun);
				yellowButton.setEnabled(modeRun);
				greenButton.setEnabled(modeRun);
				addTimeButton.setEnabled(modeRun);
				subTimeButton.setEnabled(modeRun);
				modeRun = true;
			} shape.repaint();
		}
	}
	
	// determines which direction is manually operated
	public class DirectionButtonEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			redButton.setEnabled(true);
			yellowButton.setEnabled(true);
			greenButton.setEnabled(true);
			if (e.getSource() == northSouthButton) {
				direction = "northsouth";
			} else if (e.getSource() == eastWestButton) {
				direction = "eastwest";
			}
		}
	}
	
	// change color based on what direction was picked and what color is picked
	public class ColorButtonEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (direction.equals("northsouth")) {
				if (e.getSource() == redButton) {
					shape.setColor1(stop, def, def);
				} else if (e.getSource() == yellowButton) {
					shape.setColor1(def, ready, def);
				} else if (e.getSource() == greenButton) {
					shape.setColor1(def, def, go);
				}
			}
			if (direction.equals("eastwest")) {
				if (e.getSource() == redButton) {
					shape.setColor2(stop, def, def);
				} else if (e.getSource() == yellowButton) {
					shape.setColor2(def, ready, def);
				} else if (e.getSource() == greenButton) {
					shape.setColor2(def, def, go);
				} 
			} shape.repaint();
		}
	}
 }