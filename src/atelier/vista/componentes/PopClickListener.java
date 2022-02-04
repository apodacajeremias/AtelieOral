package atelier.vista.componentes;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PopClickListener extends MouseAdapter {
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.isPopupTrigger())
			doPop(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.isPopupTrigger())
			doPop(e);
	}

	private void doPop(MouseEvent e) {
		PopUpDemo menu = new PopUpDemo();
		menu.show(e.getComponent(), e.getX(), e.getY());
	}
}

class PopUpDemo extends JPopupMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5506763947488123819L;
	JMenuItem anItem;
	public PopUpDemo() {
		anItem = new JMenuItem("Avisar");
		add(anItem);
	}

}
