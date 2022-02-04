package atelier.vista.componentes;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class CeldaRenderer extends JLabel implements TableCellRenderer {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7546163329986884234L;
	boolean isBordered = true;
	private int column;

    public CeldaRenderer(boolean isBordered, int column) {
        this.isBordered = isBordered;
        this.column = column;
        setOpaque(true);
    }

    @Override
	public Component getTableCellRendererComponent(JTable table, Object color, boolean isSelected, boolean hasFocus, int row, int column) {
        // Va a mostrar el botón solo en la última fila.
        // de otra forma muestra un espacio en blanco.
        if (this.column == column) {
        	MiBoton boton = new MiBoton("WhatsApp");
        	boton.setText("");
            return boton;
        } else {
            setBackground(new Color(0xffffff));
            return this;
        }
    }
}