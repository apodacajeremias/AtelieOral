package atelier.controlador.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import net.sf.jasperreports.view.JasperViewer;

public class ConexionReporte<E> {

	public JDialog ventanaReporte = new JDialog(new JFrame(), "Visualizar", true);

	private static final String PREFIX = "/atelier/jasper/";
	private static final String SUFFIX = ".jasper";

//	public void generarReporte(List<E> lista, Map<String, Object> parametros, String nombreReporte) throws JRException {
//		ventanaReporte.setSize(1000, 700);
//		ventanaReporte.setLocationRelativeTo(null);
//		ventanaReporte.setModal(true);
//		InputStream logo = ConexionReporte.class.getResourceAsStream("/img/LogoReporte.png");
//		parametros.put("logo", logo);
//		InputStream stream = ConexionReporte.class.getResourceAsStream(PREFIX + nombreReporte + SUFFIX);
//		JasperReport report = JasperCompileManager.compileReport(stream);
//		JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(lista));
//		JasperViewer viewer = new JasperViewer(print, true);
//		ventanaReporte.getContentPane().add(viewer.getContentPane());
//	}

	public void generarReporte(List<E> lista, Map<String, Object> parametros, String nombreReporte) throws JRException {
		// Parameters passed into the report.
//		Map<String, Object> parameters = new HashMap<>();

		// Arbitrary parameter passed into the report.
//		parameters.put("KEY", "Value");

		// Seleccionamos el logo
		InputStream logo = ConexionReporte.class.getResourceAsStream("/img/LogoReporte.png");
		parametros.put("LOGO_EMPRESA", logo);

		// The compiled report design.
		String path_1 = PREFIX + nombreReporte + SUFFIX;
		System.out.println(path_1);
		InputStream path = ConexionReporte.class.getResourceAsStream(PREFIX + nombreReporte + SUFFIX);
		System.out.println(path.toString());
		
		// Populate this list of beans as per your requirements.
//		List<Bean> beans = new ArrayList<>();

		// Wrap the beans in a beans in a JRBeanCollectionDataSource.
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(lista);

		// Fill the report, get the JasperPrint that can be exported to desired format.
		JasperPrint jasperPrint = JasperFillManager.fillReport(path, parametros, datasource);

		// Shows in a window
		JasperViewer viewer = new JasperViewer(jasperPrint, true);
		ventanaReporte.getContentPane().add(viewer.getContentPane());
		ventanaReporte.setSize(1000, 700);
		ventanaReporte.setLocationRelativeTo(null);
		ventanaReporte.setModal(true);
	}

	public void generarExcel(List<E> lista, Map<String, Object> parametros, String nombreReporte) throws JRException {
		try (InputStream stream = ConexionReporte.class.getResourceAsStream(PREFIX + nombreReporte + SUFFIX);) {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			// read report as input stream
			JasperReport report = JasperCompileManager.compileReport(stream); // compile report
			Map<String, Object> params = new HashMap<>(); // init map with report's parameters
			params.put(JRParameter.REPORT_LOCALE, Locale.US);
			params.put(JRParameter.IS_IGNORE_PAGINATION, true);
			JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(lista)); // prepare
			JRXlsxExporter exporter = new JRXlsxExporter(); // initialize exporter
			exporter.setExporterInput(new SimpleExporterInput(print)); // set compiled report as input
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output)); // set output file via path with
																						// // filenam
			SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
			configuration.setOnePagePerSheet(true); // setup configuration
			configuration.setDetectCellType(true);
			exporter.setConfiguration(configuration); // set configuration
			exporter.exportReport();
			JasperViewer viewer = new JasperViewer(print, true);
			ventanaReporte.setSize(1000, 700);
			ventanaReporte.setLocationRelativeTo(null);
			ventanaReporte.setModal(true);
			ventanaReporte.getContentPane().add(viewer.getContentPane());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
