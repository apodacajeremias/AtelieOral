package atelier.vista.ventana.principal;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import atelier.controlador.util.ConnectionHelper;
import atelier.modelo.entidades.Funcionario;
import atelier.modelo.entidades.dao.UsuarioDao;
import atelier.modelo.sesion.Sesion;
import atelier.vista.componentes.CampoTextoPersonalizado;
import atelier.vista.componentes.LabelPersonalizado;
import atelier.vista.componentes.MiBoton;

public class VentanaAcceso extends JDialog {

	private static final long serialVersionUID = 5266841984275796870L;
	private JPasswordField tContra;
	private CampoTextoPersonalizado tUsuario;
	private MiBoton btnIngresar;
	private LabelPersonalizado lMensaje;
	private JLabel lImg;

	public static void main(String[] args) {
		try {
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
			BeautyEyeLNFHelper.launchBeautyEyeLNF();
			ConnectionHelper.setUp();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					VentanaAcceso dialog = new VentanaAcceso();
					dialog.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}

	public VentanaAcceso() {
		setTitle("INGRESAR");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 400, 235);
		setLocationRelativeTo(this);
		getContentPane().setLayout(null);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		tUsuario = new CampoTextoPersonalizado();
		tUsuario.mayusculas();
		tUsuario.setBounds(10, 31, 150, 30);
		getContentPane().add(tUsuario);
		tUsuario.setColumns(10);

		tContra = new JPasswordField();
		tContra.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String u = tUsuario.getText();
					String c = String.valueOf(tContra.getPassword());

					verificarAcceso(u, c);
				}
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
		});
		tContra.setBounds(10, 92, 150, 30);
		getContentPane().add(tContra);

		btnIngresar = new MiBoton("Acceder");
		btnIngresar.setActionCommand("Acceder");
		btnIngresar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String u = tUsuario.getText();
				String c = String.valueOf(tContra.getPassword());

				verificarAcceso(u, c);

			}
		});
		btnIngresar.setBounds(10, 133, 150, 30);
		getContentPane().add(btnIngresar);

		LabelPersonalizado labelPersonalizado = new LabelPersonalizado(12);
		labelPersonalizado.setText("Usuario");
		labelPersonalizado.setHorizontalAlignment(SwingConstants.LEFT);
		labelPersonalizado.setFont(new Font("Tahoma", Font.BOLD, 18));
		labelPersonalizado.setBounds(10, 11, 69, 20);
		getContentPane().add(labelPersonalizado);

		LabelPersonalizado labelPersonalizado_1 = new LabelPersonalizado(12);
		labelPersonalizado_1.setText("Contraseña");
		labelPersonalizado_1.setHorizontalAlignment(SwingConstants.LEFT);
		labelPersonalizado_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		labelPersonalizado_1.setBounds(10, 72, 104, 20);
		getContentPane().add(labelPersonalizado_1);

		lMensaje = new LabelPersonalizado(10);
		lMensaje.setBounds(10, 174, 374, 20);
		getContentPane().add(lMensaje);

		lImg = new JLabel("");
		lImg.setIcon(new ImageIcon(VentanaAcceso.class.getResource("/img/LogoReporte.png")));
		lImg.setForeground(Color.DARK_GRAY);
		lImg.setVerticalAlignment(SwingConstants.BOTTOM);
		lImg.setHorizontalAlignment(SwingConstants.LEFT);
		lImg.setBounds(184, 11, 200, 160);
		getContentPane().add(lImg);
	}

	private void verificarAcceso(String u, String c) {
		UsuarioDao dao = new UsuarioDao();
		Funcionario funcionario = dao.verificarAcceso(c, u);

		if (funcionario != null) {
			Sesion.getInstance().setFuncionario(funcionario);
			Sesion.getInstance().setFuncionarios(dao.recuperarTodo());
			Sesion.getInstance().setConfiguracion(dao.recuperarUltimaConfiguracion());
			VentanaPrincipal ventana = new VentanaPrincipal();
			dispose();
			ventana.getLblPODAC().setText(Sesion.getInstance().getFuncionario().toString());
			ventana.setVisible(true);
			return;
		}
		lMensaje.setText("ACCESO INCORRECTO");
	}
}
