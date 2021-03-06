package es.uniovi.ips.hospital.ui.admin.users;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uniovi.ips.hospital.domain.AdminAssistant;
import es.uniovi.ips.hospital.domain.Doctor;
import es.uniovi.ips.hospital.domain.Nurse;
import es.uniovi.ips.hospital.exception.BusinessException;
import es.uniovi.ips.hospital.exception.InputException;
import es.uniovi.ips.hospital.service.AdminAssistantService;
import es.uniovi.ips.hospital.service.DoctorService;
import es.uniovi.ips.hospital.service.NurseService;
import es.uniovi.ips.hospital.ui.util.Designer;
import es.uniovi.ips.hospital.ui.util.PaletteFactory;
import es.uniovi.ips.hospital.ui.util.Shiftable;
import es.uniovi.ips.hospital.ui.util.components.MyBackPanel;
import es.uniovi.ips.hospital.ui.util.components.MyButton;
import es.uniovi.ips.hospital.ui.util.components.MyFrontPanel;

import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.util.List;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;

@Component
public class CreateAdminsPanel extends JPanel implements Shiftable {

	private static final long serialVersionUID = 649590320703859836L;
	private final JPanel contentPanel = new JPanel();

	@Autowired
	private AdminAssistantService adminService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private NurseService nurseService;

	private JPanel panel;
	private JPanel pnPersonalData;
	private JLabel lblPersonalData;
	private JPanel pnDni;
	private JLabel lblDni;
	private JTextField txDni;
	private JPanel pnName;
	private JLabel lblName;
	private JTextField txName;
	private JPanel pnSurname;
	private JLabel lblSurname;
	private JTextField txSurname;
	private JPanel pnLogin;
	private JLabel lblLogin;
	private JPanel pnEmail;
	private JLabel lblEmail;
	private JTextField txEmail;
	private JPanel pnPassword;
	private JLabel lblPassword;
	private JTextField txPass;
	private JPanel pnAddress;
	private JLabel lblAddress;
	private JPanel pnAddressStreet;
	private JLabel lblAddressStreet;
	private JTextField txStreet;
	private JLabel lblAddressCity;
	private JTextField txCity;
	private JPanel pnZipCode;
	private JLabel lblZipCode;
	private JTextField txZip;
	private JButton btAdd;
	private JPanel pnAddressCity;

	/**
	 * Create the dialog.
	 */
	public CreateAdminsPanel() {
		setBounds(100, 100, 650, 700);
		setPreferredSize(new Dimension(650, 700));
		setMinimumSize(new Dimension(650, 700));
		setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(PaletteFactory.getBaseDark());
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.add(getPanel(), BorderLayout.CENTER);
		contentPanel.add(getBtAdd(), BorderLayout.SOUTH);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new MyBackPanel();
            GridBagLayout gbl_pn = new GridBagLayout();
            gbl_pn.columnWeights = new double[]{0.0};
            gbl_pn.columnWidths = new int[]{100,450,100};
            gbl_pn.rowHeights = new int[]{75,150,50,100,50,150,75};
            panel.setLayout(gbl_pn);
            GridBagConstraints gbc_pnPersonalData = new GridBagConstraints();
            gbc_pnPersonalData.insets = new Insets(5, 5, 5, 5);
            gbc_pnPersonalData.fill = GridBagConstraints.BOTH;
            gbc_pnPersonalData.gridx = 1;
            gbc_pnPersonalData.gridy = 1;
            panel.add(getPnPersonalData(), gbc_pnPersonalData);
            GridBagConstraints gbc_pnLogin = new GridBagConstraints();
            gbc_pnLogin.insets = new Insets(0, 0, 5, 0);
            gbc_pnLogin.fill = GridBagConstraints.BOTH;
            gbc_pnLogin.gridx = 1;
            gbc_pnLogin.gridy = 3;
            panel.add(getPnLogin(), gbc_pnLogin);
            GridBagConstraints gbc_pnAddress = new GridBagConstraints();
            gbc_pnAddress.insets = new Insets(0, 0, 5, 0);
            gbc_pnAddress.fill = GridBagConstraints.BOTH;
            gbc_pnAddress.gridx = 1;
            gbc_pnAddress.gridy = 5;
            panel.add(getPnAddress(), gbc_pnAddress);
		}
		return panel;
	}
	
	private JPanel getPnPersonalData() {
		if (pnPersonalData == null) {
			pnPersonalData = new MyFrontPanel();
			pnPersonalData.setBorder(Designer.getBorder());
			pnPersonalData.setLayout(new BoxLayout(pnPersonalData, BoxLayout.Y_AXIS));
			pnPersonalData.add(getLblPersonalData());
			pnPersonalData.add(getPnDni());
			pnPersonalData.add(getPnName());
			pnPersonalData.add(getPnSurname());
		}
		return pnPersonalData;
	}
	
	private JLabel getLblPersonalData() {
		if (lblPersonalData == null) {
			lblPersonalData = new JLabel("Personal data");
			lblPersonalData.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
			lblPersonalData.setHorizontalAlignment(SwingConstants.CENTER);
			lblPersonalData.setHorizontalTextPosition(SwingConstants.RIGHT);
		}
		return lblPersonalData;
	}
	
	private JPanel getPnDni() {
		if (pnDni == null) {
			pnDni = new MyFrontPanel();
			FlowLayout flowLayout = (FlowLayout) pnDni.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnDni.add(getLblDni());
			pnDni.add(getTxDni());
		}
		return pnDni;
	}

	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("Dni");
			lblDni.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblDni;
	}

	private JTextField getTxDni() {
		if (txDni == null) {
			txDni = new JTextField();
			txDni.setColumns(10);
		}
		return txDni;
	}
	
	private JPanel getPnName() {
		if (pnName == null) {
			pnName = new MyFrontPanel();
			FlowLayout flowLayout = (FlowLayout) pnName.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnName.add(getLblName());
			pnName.add(getTxName());
		}
		return pnName;
	}

	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("Name");
			lblName.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblName;
	}

	private JTextField getTxName() {
		if (txName == null) {
			txName = new JTextField();
			txName.setColumns(10);
		}
		return txName;
	}
	
	private JPanel getPnSurname() {
		if (pnSurname == null) {
			pnSurname = new MyFrontPanel();
			FlowLayout flowLayout = (FlowLayout) pnSurname.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnSurname.add(getLblSurname());
			pnSurname.add(getTxSurname());
		}
		return pnSurname;
	}

	private JLabel getLblSurname() {
		if (lblSurname == null) {
			lblSurname = new JLabel("Surname");
			lblSurname.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblSurname;
	}

	private JTextField getTxSurname() {
		if (txSurname == null) {
			txSurname = new JTextField();
			txSurname.setColumns(10);
		}
		return txSurname;
	}
	
	private JPanel getPnLogin() {
		if (pnLogin == null) {
			pnLogin = new MyFrontPanel();
			pnLogin.setBorder(Designer.getBorder());
			pnLogin.setLayout(new BoxLayout(pnLogin, BoxLayout.Y_AXIS));
			pnLogin.add(getLblLogin());
			pnLogin.add(getPnEmail());
			pnLogin.add(getPnPassword());
		}
		return pnLogin;
	}
	
	private JLabel getLblLogin() {
		if (lblLogin == null) {
			lblLogin = new JLabel("Log-in data");
			lblLogin.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
			lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
			lblLogin.setHorizontalTextPosition(SwingConstants.RIGHT);
		}
		return lblLogin;
	}
	
	private JPanel getPnEmail() {
		if (pnEmail == null) {
			pnEmail = new MyFrontPanel();
			FlowLayout flowLayout = (FlowLayout) pnEmail.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnEmail.add(getLblEmail());
			pnEmail.add(getTxEmail());
		}
		return pnEmail;
	}

	private JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel("Email");
			lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblEmail;
	}

	private JTextField getTxEmail() {
		if (txEmail == null) {
			txEmail = new JTextField();
			txEmail.setColumns(10);
		}
		return txEmail;
	}
	
	private JPanel getPnPassword() {
		if (pnPassword == null) {
			pnPassword = new MyFrontPanel();
			FlowLayout flowLayout = (FlowLayout) pnPassword.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnPassword.add(getLblPassword());
			pnPassword.add(getTxPass());
		}
		return pnPassword;
	}

	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("Password");
			lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPassword;
	}

	private JTextField getTxPass() {
		if (txPass == null) {
			txPass = new JTextField();
			txPass.setColumns(10);
		}
		return txPass;
	}
	
	private JPanel getPnAddress() {
		if (pnAddress == null) {
			pnAddress = new MyFrontPanel();
			pnAddress.setBorder(Designer.getBorder());
			pnAddress.setLayout(new BoxLayout(pnAddress, BoxLayout.Y_AXIS));
			pnAddress.add(getLblAddress());
			pnAddress.add(getPnAddressStreet());
			pnAddress.add(getPnAddressCity());
			pnAddress.add(getPnZipCode());
		}
		return pnAddress;
	}
	
	private JLabel getLblAddress() {
		if (lblAddress == null) {
			lblAddress = new JLabel("Address data");
			lblAddress.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
			lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
			lblAddress.setHorizontalTextPosition(SwingConstants.RIGHT);
		}
		return lblAddress;
	}
	
	private JPanel getPnAddressStreet() {
		if (pnAddressStreet == null) {
			pnAddressStreet = new MyFrontPanel();
			FlowLayout flowLayout = (FlowLayout) pnAddressStreet.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnAddressStreet.add(getLblAddressStreet());
			pnAddressStreet.add(getTxStreet());
		}
		return pnAddressStreet;
	}

	private JLabel getLblAddressStreet() {
		if (lblAddressStreet == null) {
			lblAddressStreet = new JLabel("Addres Street");
			lblAddressStreet.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblAddressStreet;
	}

	private JTextField getTxStreet() {
		if (txStreet == null) {
			txStreet = new JTextField();
			txStreet.setColumns(10);
		}
		return txStreet;
	}
	
	private JPanel getPnAddressCity() {
		if (pnAddressCity == null) {
			pnAddressCity = new MyFrontPanel();
			FlowLayout flowLayout = (FlowLayout) pnAddressCity.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnAddressCity.add(getLblAddressCity());
			pnAddressCity.add(getTxCity());
		}
		return pnAddressCity;
	}

	private JLabel getLblAddressCity() {
		if (lblAddressCity == null) {
			lblAddressCity = new JLabel("Address City");
			lblAddressCity.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblAddressCity;
	}

	private JTextField getTxCity() {
		if (txCity == null) {
			txCity = new JTextField();
			txCity.setColumns(10);
		}
		return txCity;
	}
	
	private JPanel getPnZipCode() {
		if (pnZipCode == null) {
			pnZipCode = new MyFrontPanel();
			FlowLayout flowLayout = (FlowLayout) pnZipCode.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnZipCode.add(getLblZipCode());
			pnZipCode.add(getTxZip());
		}
		return pnZipCode;
	}

	private JLabel getLblZipCode() {
		if (lblZipCode == null) {
			lblZipCode = new JLabel("ZIP Code");
		}
		return lblZipCode;
	}

	private JTextField getTxZip() {
		if (txZip == null) {
			txZip = new JTextField();
			txZip.setColumns(10);
		}
		return txZip;
	}

	private JButton getBtAdd() {
		if (btAdd == null) {
			btAdd = new MyButton("Add");
			btAdd.addActionListener(e -> addAdmin());
		}
		return btAdd;
	}

	///////////////////////////////////////////////////////////////////////////////

	private void addAdmin() {
		List<Doctor> doctors = doctorService.findAllDoctors();
		List<AdminAssistant> assistants = adminService.findAllAdminAssistant();
		List<Nurse> nurses = nurseService.findAllNurses();
		try {
			checkInput();
			checkDuplicates(doctors, assistants, nurses);
			AdminAssistant as = new AdminAssistant(txDni.getText(), txName.getText(), txSurname.getText(),
					txEmail.getText(), txPass.getText(), txStreet.getText(), txCity.getText(), txZip.getText());
			adminService.createAdminAssistant(as);
			JOptionPane.showMessageDialog(this, "Admin created");
			txCity.setText("");
			txZip.setText("");
			txSurname.setText("");
			txStreet.setText("");
			txPass.setText("");
			txName.setText("");
			txEmail.setText("");
			txDni.setText("");
		} catch (InputException ie) {
			JOptionPane.showMessageDialog(this, ie.getMessage());
		} catch (BusinessException be) {
			JOptionPane.showMessageDialog(this, be.getMessage());
		}
	}

	private void checkInput() throws InputException {
		String a = String.valueOf(txPass.getText().length());
		if (txCity.getText().equals("") || txDni.getText().equals("") || txZip.getText().equals("")
				|| txName.getText().equals("") || txPass.getText().equals("") || txSurname.getText().equals("")
				|| txStreet.getText().equals("") || txEmail.getText().equals(""))
			throw new InputException("Fill the fields");
		if (!txEmail.getText().matches("[a-zA-Z1-9]+@[a-zA-Z1-9]+.[a-zA-Z1-9]+"))
			throw new InputException("Wrong email format");
		if (a.equals("0") || a.equals("1") || a.equals("2") || a.equals("3") || a.equals("4"))
			throw new InputException("Wrong password lenght");
		if (!txDni.getText().matches("[0-9]+[A-Z]") || !String.valueOf(txDni.getText().length()).equals("9"))
			throw new InputException("Wrong dni format");
	}

	private void checkDuplicates(List<Doctor> doctors, List<AdminAssistant> assistants, List<Nurse> nurses)
			throws BusinessException {
		for (AdminAssistant aa : assistants)
			if (txDni.getText().equals(aa.getDni()))
				throw new BusinessException("Admin already exists");
		for (Nurse n : nurses)
			if (txDni.getText().equals(n.getDni()))
				throw new BusinessException("Staff member already exists");
		for (Doctor d : doctors)
			if (txDni.getText().equals(d.getDni()))
				throw new BusinessException("Staff member already exists");
	}

	@Override
	public void setFocus() {
		txDni.requestFocus();
	}
}
