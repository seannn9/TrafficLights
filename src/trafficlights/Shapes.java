package trafficlights;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Shapes extends JComponent{
	// class for the shapes/circles that displays the colors
		private Color A = Color.DARK_GRAY;
		private Color B = Color.DARK_GRAY;
		private Color C = Color.GREEN;
		private Color X = Color.RED;
		private Color Y = Color.DARK_GRAY;
		private Color Z = Color.DARK_GRAY;
		private int h = 150, w = 150;
		
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
		public Color getColorC() {
			return C;
		}
		
		// getter for the third circle in column 2
		public Color getColorZ() {
			return Z;
		}
}
