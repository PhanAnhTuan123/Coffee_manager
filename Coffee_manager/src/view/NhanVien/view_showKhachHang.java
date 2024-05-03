package view.NhanVien;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import list.List_KhachHang;
import model.KhachHang;
import service.KhachHang_DAO;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JTable;

public class view_showKhachHang extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField txtMaKH;
	private DefaultTableModel model;
	private List_KhachHang list_kh;
	private KhachHang_DAO kh_dao;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			view_showKhachHang dialog = new view_showKhachHang();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public view_showKhachHang() {
		kh_dao = new KhachHang_DAO();
		list_kh = new List_KhachHang();
		setBounds(100, 100, 485, 617);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Nhập Số Điện Thoại");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblNewLabel.setBounds(0, 11, 162, 26);
			contentPanel.add(lblNewLabel);
		}
		{
			textField = new JTextField();
			textField.setBounds(147, 11, 147, 26);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JButton btnChon = new JButton("Chọn");
			btnChon.setBounds(299, 10, 77, 26);
			contentPanel.add(btnChon);
		}
		{
			JButton btnLamMoi = new JButton("Làm Mới");
			btnLamMoi.setBounds(384, 9, 77, 26);
			contentPanel.add(btnLamMoi);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 47, 451, 477);
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				scrollPane.setViewportView(table);
			}
		}
		{
			
			String[] column = {
				"Mã Khách Hàng","Tên Khách Hàng","Số Điện Thoại"	
			};
			model = new DefaultTableModel(column,0);
			table.setModel(model);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JLabel lblNewLabel_1 = new JLabel("Mã Khách Hàng");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
				buttonPane.add(lblNewLabel_1);
			}
			{
				txtMaKH = new JTextField();
				txtMaKH.setFont(new Font("Tahoma", Font.BOLD, 15));
				buttonPane.add(txtMaKH);
				txtMaKH.setColumns(10);
			}
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		loadData();
	}

	private void loadData() {
		model.setRowCount(0);
//		for (KhachHang kh : ) {
//			
//		}
	}

	

}
