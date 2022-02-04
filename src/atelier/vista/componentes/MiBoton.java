package atelier.vista.componentes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MiBoton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4442593098919633372L;

	public MiBoton(String text) {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(100, 30));
		setMaximumSize(new Dimension(100, 30));
		setHorizontalAlignment(CENTER);
		setHorizontalTextPosition(TRAILING);
		setVerticalAlignment(CENTER);
		setVerticalTextPosition(CENTER);
		setFont(new Font("Helvetica", Font.BOLD, 12));
		setFocusPainted(true);
		setContentAreaFilled(false);
		setBorderPainted(true);
		setOpaque(false);
		setText(text);
		setIcono(text);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	

	public void setIcono(String nombreIcono) {
		try {
			// Se recupera la posible ubicacion del icono
			URL url = MiBoton.class.getResource("/img/" + nombreIcono + ".png");
			// Asigna el icono al boton
			setIcon(new ImageIcon(url));

		} catch (Exception e) {
		}
	}

}
