package view.NhanVien;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import db.ConnectDB;
import list.List_Ban;
import list.List_NhanVien;
import model.Ban;
import model.NhanVien;
import runapp.Login;
import service.Ban_DAO;
import testbutton.Buttontest;
import view.QuanLy.Main_form_manager;
import view.QuanLy.view_QuanLyNhanVien;

public class view_hoaDon extends JFrame implements ActionListener{
	private String tempMaBan;
	private Ban_DAO ban_dao;
	private List_Ban list_Ban;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnThem, btnXoa, btnSua, btntimkiem;
	private JLabel lbltennv;
	private JTable tableShowSP;
	private DefaultTableModel tableModel;
	private DefaultTableModel tableModelSanPham;
	private JTextField txtSDT, txtDiaChi, txtTimKiem, txtMaHD;
	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	private JPanel panelHangHoa, panelDatHang, panelNhanVien, panelTaiKhoan, panelThongKe;
	Color customColor = new Color(255, 255, 255, 0);
	Color whiteColor = new Color(255, 255, 255, 0);
	private JLabel lblNvIcon; // Thêm biến để lưu đối tượng JLabel chứa ảnh NV
	private List_NhanVien list_nv = new List_NhanVien();
	private JTable table_chonSP;
	private JTextField txtKhachHang;
	private JTextField txtTien;
	private JTextField txtChietKhau;
	private JTextField txtTongTien;
	private JTextField txtSoLuong;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(view_QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(view_QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(view_QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(view_QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}
		
		try {
			ConnectDB.getInstance().connect();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		view_hoaDon frame = new view_hoaDon();
		frame.setVisible(true);
	}
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public view_hoaDon() throws SQLException {
		try {
			ConnectDB.getInstance().connect();
		}catch (Exception e) {
			e.printStackTrace();
		}
		ban_dao = new Ban_DAO();
		list_Ban = new List_Ban();
		
//		initComponents();
		setResizable(false);
		setBackground(Color.WHITE);
		setTitle("Giao Diện Quản Lý Bàn");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tableModelSanPham = new DefaultTableModel();
		this.setLocationRelativeTo(null);
		setBounds(100, 100, 1168, 650);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNvIcon = new JLabel("");
		lblNvIcon.setIcon(new ImageIcon(view_QuanLyNhanVien.class.getResource("/image/avt.png"))); // Thay đổi đường dẫn
																									// ảnh của bạn
		lblNvIcon.setBounds(760, 5, 40, 40); // Điều chỉnh tọa độ và kích thước của ảnh
		contentPane.add(lblNvIcon);

		JLabel lblnhanvien = new JLabel("QL:");
		lblnhanvien.setForeground(Color.WHITE);
		lblnhanvien.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblnhanvien.setBounds(801, 0, 39, 50);
		lblnhanvien.setForeground(Color.WHITE);
		contentPane.add(lblnhanvien);

		lbltennv = new JLabel("Trương Đại Lộc");
		lbltennv.setForeground(Color.WHITE);
		lbltennv.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbltennv.setBounds(832, 0, 238, 50);
		lbltennv.setForeground(Color.WHITE);
//		lbltennv.setText(UserInfo.getTenNhanVien());
		contentPane.add(lbltennv);

		// Thêm panel nằm ngang ở trên cùng
		JPanel topPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		topPanel.setOpaque(false);
		topPanel.setBounds(0, 0, 1168, 50);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Sử dụng FlowLayout
		topPanel.setBackground(customColor); // Thay đổi ở đây
		contentPane.add(topPanel);

		// Thêm toolbar "Hàng hóa"
		JToolBar qlyHangHoaToolbar = new JToolBar();
		qlyHangHoaToolbar.setFloatable(false);
		qlyHangHoaToolbar.setMargin(new java.awt.Insets(-5, -5, 0, -5));
		testbutton.Buttontest qlyHangHoaButton = new Buttontest();
		qlyHangHoaButton.setText("Hàng hóa");
		qlyHangHoaButton.setFont(new Font("Open Sans", Font.BOLD, 15));
		qlyHangHoaButton.setForeground(SystemColor.text);
		qlyHangHoaButton.setRippleColor(new Color(255, 255, 255));
		qlyHangHoaButton.setBackground(new Color(255, 128, 64));
		qlyHangHoaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panelHangHoa.isVisible() || panelDatHang.isVisible() || panelNhanVien.isVisible()
						|| panelTaiKhoan.isVisible() || panelThongKe.isVisible()) {
					panelHangHoa.setVisible(false);
					panelDatHang.setVisible(false);
					panelNhanVien.setVisible(false);
					panelTaiKhoan.setVisible(false);
					panelThongKe.setVisible(false);
				} else {
					panelHangHoa.setVisible(true);
				}
			}
		});
		qlyHangHoaToolbar.add(qlyHangHoaButton);
		qlyHangHoaToolbar.setBackground(customColor); // Thay đổi ở đây
		topPanel.add(qlyHangHoaToolbar);

		panelHangHoa = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		panelHangHoa.setBounds(0, 49, 1175, 47); // Điều chỉnh tọa độ và kích thước của panel theo ý muốn
		panelHangHoa.setLayout(new FlowLayout(FlowLayout.LEFT)); // Thay đổi ở đây
		panelHangHoa.setVisible(false); // tắt/ẩn panel
		panelHangHoa.setBackground(whiteColor);
		contentPane.add(panelHangHoa);

		JButton btnqlyMonAn = new JButton("Quản Lý Món Ăn");
		btnqlyMonAn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnqlyMonAn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		panelHangHoa.add(btnqlyMonAn);

		// Thêm toolbar "dịch vụ"
		JToolBar datHangToolbar = new JToolBar();
		datHangToolbar.setFloatable(false);
		datHangToolbar.setMargin(new java.awt.Insets(-5, -5, 0, -5));
		testbutton.Buttontest datHangButton = new Buttontest();
		datHangButton.setText("Đặt Hàng");
		datHangButton.setFont(new Font("Open Sans", Font.BOLD, 15));
		datHangButton.setForeground(SystemColor.text);
		datHangButton.setRippleColor(new Color(255, 255, 255));
		datHangButton.setBackground(new Color(46, 139, 87));
		datHangButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panelHangHoa.isVisible() || panelDatHang.isVisible() || panelNhanVien.isVisible()
						|| panelTaiKhoan.isVisible() || panelThongKe.isVisible()) {
					panelHangHoa.setVisible(false);
					panelDatHang.setVisible(false);
					panelNhanVien.setVisible(false);
					panelTaiKhoan.setVisible(false);
					panelThongKe.setVisible(false);
				} else {
					panelDatHang.setVisible(true);
				}
			}
		});
		datHangToolbar.add(datHangButton);
		datHangToolbar.setBackground(customColor); // Thay đổi ở đây
		topPanel.add(datHangToolbar);

		panelDatHang = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		panelDatHang.setBounds(0, 49, 1175, 47); // Điều chỉnh tọa độ và kích thước của panel theo ý muốn
		panelDatHang.setLayout(new FlowLayout(FlowLayout.LEFT)); // Thay đổi ở đây
		panelDatHang.setVisible(false); // tắt/ẩn panel
		panelDatHang.setBackground(whiteColor);
		contentPane.add(panelDatHang);

		JButton btnBan = new JButton("Quản Lý Bàn");
		btnBan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		JButton btnKhachHang = new JButton("Quản Lý Khách Hàng");
		btnKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnKhachHang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		panelDatHang.add(btnKhachHang);
		panelDatHang.add(btnBan);

		// Thêm toolbar "nhân viên"
		JToolBar nhanVienToolbar = new JToolBar();
		nhanVienToolbar.setFloatable(false);
		nhanVienToolbar.setMargin(new java.awt.Insets(-5, -5, 0, -5));
		testbutton.Buttontest nhanVienButton = new Buttontest();
		nhanVienButton.setText("Nhân Viên");
		nhanVienButton.setFont(new Font("Open Sans", Font.BOLD, 15));
		nhanVienButton.setForeground(SystemColor.text);
		nhanVienButton.setRippleColor(new Color(255, 255, 255));
		nhanVienButton.setBackground(new Color(255, 0, 128));
		nhanVienButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panelHangHoa.isVisible() || panelDatHang.isVisible() || panelNhanVien.isVisible()
						|| panelTaiKhoan.isVisible() || panelThongKe.isVisible()) {
					panelHangHoa.setVisible(false);
					panelDatHang.setVisible(false);
					panelNhanVien.setVisible(false);
					panelTaiKhoan.setVisible(false);
					panelThongKe.setVisible(false);
				} else {
					panelNhanVien.setVisible(true);
				}
			}
		});
		nhanVienToolbar.add(nhanVienButton);
		nhanVienToolbar.setBackground(customColor);
		topPanel.add(nhanVienToolbar);

		panelNhanVien = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		panelNhanVien.setBounds(0, 49, 1175, 47); // Điều chỉnh tọa độ và kích thước của panel theo ý muốn
		panelNhanVien.setLayout(new FlowLayout(FlowLayout.LEFT)); // Thay đổi ở đây
		panelNhanVien.setVisible(false); // tắt/ẩn panel
		panelNhanVien.setBackground(whiteColor);
		contentPane.add(panelNhanVien);

		JButton btnNhanVien = new JButton("Quản Lý Bàn");
		btnNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNhanVien.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}

		});
		panelNhanVien.add(btnNhanVien);

		// Thêm toolbar "tài khoản"
		JToolBar taiKhoanToolbar = new JToolBar();
		taiKhoanToolbar.setFloatable(false);
		taiKhoanToolbar.setMargin(new java.awt.Insets(-5, -5, 0, -5));
		testbutton.Buttontest taiKhoanButton = new Buttontest();
		taiKhoanButton.setText("Tài Khoản");
		taiKhoanButton.setFont(new Font("Open Sans", Font.BOLD, 15));
		taiKhoanButton.setForeground(SystemColor.text);
		taiKhoanButton.setRippleColor(new Color(255, 255, 255));
		taiKhoanButton.setBackground(new Color(99, 176, 28));
		taiKhoanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panelHangHoa.isVisible() || panelDatHang.isVisible() || panelNhanVien.isVisible()
						|| panelTaiKhoan.isVisible() || panelThongKe.isVisible()) {
					panelHangHoa.setVisible(false);
					panelDatHang.setVisible(false);
					panelNhanVien.setVisible(false);
					panelTaiKhoan.setVisible(false);
					panelThongKe.setVisible(false);
				} else {
					panelTaiKhoan.setVisible(true);
				}
			}
		});
		taiKhoanToolbar.add(taiKhoanButton);
		taiKhoanToolbar.setBackground(customColor);
		topPanel.add(taiKhoanToolbar);

		panelTaiKhoan = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		panelTaiKhoan.setBounds(0, 49, 1175, 47); // Điều chỉnh tọa độ và kích thước của panel theo ý muốn
		panelTaiKhoan.setLayout(new FlowLayout(FlowLayout.LEFT)); // Thay đổi ở đây
		panelTaiKhoan.setVisible(false); // tắt/ẩn panel
		panelTaiKhoan.setBackground(whiteColor);
		contentPane.add(panelTaiKhoan);

		JButton btnTaiKhoan = new JButton("Quản Lý Tài Khoản");
		btnTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTaiKhoan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}

		});
		panelTaiKhoan.add(btnTaiKhoan);

		// thêm toolbar "thống kê"
		JToolBar thongKeToolbar = new JToolBar();
		thongKeToolbar.setFloatable(false);
		thongKeToolbar.setMargin(new java.awt.Insets(-5, -5, 0, -5));
		testbutton.Buttontest thongKeButton = new Buttontest();
		thongKeButton.setText("Thống Kê");
		thongKeButton.setFont(new Font("Open Sans", Font.BOLD, 15));
		thongKeButton.setForeground(SystemColor.text);
		thongKeButton.setRippleColor(new Color(255, 255, 255));
		thongKeButton.setBackground(new Color(100, 100, 255));
		thongKeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panelHangHoa.isVisible() || panelDatHang.isVisible() || panelNhanVien.isVisible()
						|| panelTaiKhoan.isVisible() || panelThongKe.isVisible()) {
					panelHangHoa.setVisible(false);
					panelDatHang.setVisible(false);
					panelNhanVien.setVisible(false);
					panelTaiKhoan.setVisible(false);
					panelThongKe.setVisible(false);
				} else {
					panelThongKe.setVisible(true);
				}
			}
		});
		thongKeToolbar.add(thongKeButton);
		thongKeToolbar.setBackground(customColor);
		topPanel.add(thongKeToolbar);

		panelThongKe = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				g.setColor(getBackground());
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		panelThongKe.setBounds(0, 49, 1175, 47); // Điều chỉnh tọa độ và kích thước của panel theo ý muốn
		panelThongKe.setLayout(new FlowLayout(FlowLayout.LEFT)); // Thay đổi ở đây
		panelThongKe.setVisible(false); // tắt/ẩn panel
		panelThongKe.setBackground(whiteColor);
		contentPane.add(panelThongKe);

		JButton btnThongKeNhanVien = new JButton("Thống Kê Nhân Viên");
		btnThongKeNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThongKeNhanVien.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		JButton btnThongKeDoanhThu = new JButton("Thống Kê Doanh Thu");
		btnThongKeDoanhThu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThongKeDoanhThu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		panelThongKe.add(btnThongKeNhanVien);
		panelThongKe.add(btnThongKeDoanhThu);

		// Create logout button
		JToolBar logoutToolBar = new JToolBar();
		logoutToolBar.setFloatable(false);
		logoutToolBar.setMargin(new java.awt.Insets(-5, 550, 0, 0));
		testbutton.Buttontest logoutButton = new Buttontest();
		logoutButton.setText("Đăng Xuất");
		logoutButton.setFont(new Font("Open Sans", Font.BOLD, 15));
		logoutButton.setForeground(SystemColor.text);
		logoutButton.setRippleColor(new Color(255, 255, 255));
		logoutButton.setBackground(new Color(226, 110, 110));
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất!", null,
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					Login lg = new Login();
					lg.setVisible(true);
					lg.setLocationRelativeTo(null);
					dispose();
				}
			}
		});
		logoutToolBar.add(logoutButton);
		logoutToolBar.setBackground(customColor);
		topPanel.add(logoutToolBar);
		
		JLabel lblQLKH = new JLabel("Quản Lý Hóa Đơn");
		lblQLKH.setForeground(new Color(255, 255, 255));
		lblQLKH.setFont(new Font("Open Sans", 1, 16));
		lblQLKH.setBounds(43, 102, 170, 20);
		contentPane.add(lblQLKH);

		JLabel lblHoTen = new JLabel("Mã Hóa Đơn:");
		lblHoTen.setForeground(new Color(255, 255, 255));
		lblHoTen.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblHoTen.setBounds(10, 132, 130, 21);
		contentPane.add(lblHoTen);

		JPanel pnlHoTen = new JPanel();
		pnlHoTen.setBackground(new Color(255, 255, 0));
		pnlHoTen.setBounds(10, 161, 230, 37);
		pnlHoTen.setOpaque(false);
		contentPane.add(pnlHoTen);

		// Thêm chúng vào ButtonGroup
		ButtonGroup buttonGroup = new ButtonGroup();

		// Add JTextField below JCheckBox
		txtMaHD = new JTextField();
		txtMaHD.setFont(new Font("Open Sans", 0, 16));
		txtMaHD.setEnabled(false);
		txtMaHD.setColumns(16); // You can adjust the column count based on your requirement
		pnlHoTen.add(txtMaHD);

		txtTimKiem = new JTextField();
		txtTimKiem.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtTimKiem.setColumns(16);
		txtTimKiem.setBounds(886, 128, 214, 30);
		contentPane.add(txtTimKiem);

		btntimkiem = new JButton("");
		btntimkiem.setIcon(new ImageIcon(view_QuanLyNhanVien.class.getResource("/image/search.png")));
		btntimkiem.setBounds(1110, 128, 40, 30);
		contentPane.add(btntimkiem);

		// Khởi tạo các nút
		btnThem = new JButton("Thêm");
		btnThem.setBackground(new Color(50, 205, 50));
		btnXoa = new JButton("Xóa");
		btnXoa.setBackground(new Color(255, 140, 0));
		btnSua = new JButton("Sửa");
		btnSua.setBackground(new Color(0, 255, 255));

		// Đặt vị trí cho các nút
		btnThem.setBounds(404, 305, 100, 30);
		btnXoa.setBounds(514, 305, 100, 30);
		btnSua.setBounds(624, 305, 100, 30);
		// Thêm các nút vào contentPane
		contentPane.add(btnThem);
		contentPane.add(btnXoa);
		contentPane.add(btnSua);

		// Khởi tạo DefaultTableModel với các cột
		String[] columnNames = {"Mã Bàn","Tên Bàn"}; // Thay đổi tên cột tùy ý
		tableModel = new DefaultTableModel(columnNames, 0);

		// Khởi tạo JTable với DefaultTableModel
		tableShowSP = new JTable(tableModel);
		tableShowSP.getColumnModel().getColumn(1).setPreferredWidth(50); // Đặt giá trị 300 làm ví dụ, bạn có thể điều chỉnh
		
		tableShowSP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				int r = table.getSelectedRow();
//				txtHoTen.setText(tableModel.getValueAt(r,1).toString());
//				txtSDT.setText(tableModel.getValueAt(r,3).toString());
//				txtDiaChi.setText(tableModel.getValueAt(r,2).toString());
//				if(tableModel.getValueAt(r,5).toString().equals("Nam")) {
////					rdbtnNam.setSelected(true);
//				}else {
////					rdbtnNu.setSelected(true);
//				}
//				if(tableModel.getValueAt(r,4).toString().equals("Nhân Viên")) {
////					cboxChucVu.setSelectedIndex(0);
//				}else {
////					cboxChucVu.setSelectedIndex(1);
//				}
				int r = tableShowSP.getSelectedRow();
				txtMaHD.setText(tableModel.getValueAt(r, 1).toString());
				tempMaBan = tableModel.getValueAt(r, 0).toString();
				btnSua.setEnabled(true);
				btnXoa.setEnabled(true);
			}
		});
		
		// theo ý muốn

		// Tạo JScrollPane để thêm bảng vào để có thể cuộn
		JScrollPane scrollPane = new JScrollPane(tableShowSP);
		scrollPane.setBounds(250, 347, 900, 169); // Điều chỉnh tọa độ và kích thước của bảng

		// Thêm bảng và JScrollPane vào contentPane
		contentPane.add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(250, 161, 900, 136);
		contentPane.add(scrollPane_1);
		String[] Column2 = {"Mã Bàn","Tên Bàn"}; // Thay đổi tên cột tùy ý
		tableModelSanPham = new DefaultTableModel(Column2, 0);

		// Khởi tạo JTable với DefaultTableModel
		table_chonSP = new JTable(tableModelSanPham);
		table_chonSP.getColumnModel().getColumn(1).setPreferredWidth(50); // Đặt giá trị 300 làm ví dụ, bạn có thể điều chỉnh
				
//		table_1 = new JTable();
		table_chonSP.getColumnModel().getColumn(1).setPreferredWidth(50);
		scrollPane_1.setViewportView(table_chonSP);
		
		JLabel lblMKhchHng = new JLabel("Mã Khách Hàng");
		lblMKhchHng.setForeground(Color.WHITE);
		lblMKhchHng.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblMKhchHng.setBounds(10, 215, 130, 21);
		contentPane.add(lblMKhchHng);
		
		txtKhachHang = new JTextField();
		txtKhachHang.setText("");
		txtKhachHang.setEnabled(false);
		txtKhachHang.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtKhachHang.setColumns(16);
		txtKhachHang.setBounds(20, 246, 161, 27);
		contentPane.add(txtKhachHang);
		


		
		
		
		

		
		JLabel lblTng = new JLabel("Tổng Tiền:");
		lblTng.setForeground(Color.WHITE);
		lblTng.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblTng.setBounds(10, 313, 130, 21);
		contentPane.add(lblTng);
		
		txtTien = new JTextField();
		txtTien.setText("");
		txtTien.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtTien.setColumns(16);
		txtTien.setBounds(20, 344, 220, 27);
		txtTien.setEnabled(false);
		contentPane.add(txtTien);
		
		
		
		
	
		
		JLabel lblTng_1 = new JLabel("Chiết Khấu");
		lblTng_1.setForeground(Color.WHITE);
		lblTng_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblTng_1.setBounds(10, 405, 130, 21);
		contentPane.add(lblTng_1);
		
		txtChietKhau = new JTextField();
		txtChietKhau.setText("");
		txtChietKhau.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtChietKhau.setColumns(16);
		txtChietKhau.setBounds(20, 436, 220, 27);
		txtChietKhau.setEnabled(false);
		contentPane.add(txtChietKhau);
		
		JLabel lblTng_2 = new JLabel("Tổng Tiền:");
		lblTng_2.setForeground(Color.WHITE);
		lblTng_2.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblTng_2.setBounds(10, 506, 130, 21);
		contentPane.add(lblTng_2);
		
		txtTongTien = new JTextField();
		txtTongTien.setText("");
		txtTongTien.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtTongTien.setColumns(16);
		txtTongTien.setBounds(20, 539, 220, 27);
		txtTongTien.setEnabled(false);
		contentPane.add(txtTongTien);
		
		Buttontest btnDatHang = new Buttontest();
		btnDatHang.setText("Đặt Hàng");
		btnDatHang.setRippleColor(Color.WHITE);
		btnDatHang.setForeground(SystemColor.text);
		btnDatHang.setFont(new Font("Dialog", Font.BOLD, 15));
		btnDatHang.setBackground(new Color(46, 139, 87));
		btnDatHang.setBounds(986, 528, 144, 56);
		contentPane.add(btnDatHang);
		
		Buttontest btnHuy = new Buttontest();
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnHuy.setText("Hủy Bỏ");
		btnHuy.setRippleColor(Color.WHITE);
		btnHuy.setForeground(SystemColor.text);
		btnHuy.setFont(new Font("Dialog", Font.BOLD, 15));
		btnHuy.setBackground(new Color(255, 0, 0));
		btnHuy.setBounds(696, 528, 144, 56);
		contentPane.add(btnHuy);
		
		Buttontest btnTaoMoi = new Buttontest();
		btnTaoMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnTaoMoi.setText("Tạo Mới");
		btnTaoMoi.setRippleColor(Color.WHITE);
		btnTaoMoi.setForeground(SystemColor.text);
		btnTaoMoi.setFont(new Font("Dialog", Font.BOLD, 15));
		btnTaoMoi.setBackground(new Color(0, 255, 255));
		btnTaoMoi.setBounds(842, 528, 144, 56);
		contentPane.add(btnTaoMoi);
		

		JButton btnTimKhachHang = new JButton("");
		
		btnTimKhachHang.setBounds(191, 246, 40, 30);
		contentPane.add(btnTimKhachHang);
		btnTimKhachHang.setIcon(new ImageIcon(view_QuanLyNhanVien.class.getResource("/image/search.png")));
		
		JLabel lblSoLuong = new JLabel("Số Lượng:");
		lblSoLuong.setForeground(Color.WHITE);
		lblSoLuong.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblSoLuong.setBounds(250, 307, 88, 21);
		contentPane.add(lblSoLuong);
		
		txtSoLuong = new JTextField();
		txtSoLuong.setText("");
		txtSoLuong.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtSoLuong.setColumns(16);
		txtSoLuong.setBounds(331, 307, 63, 27);
		contentPane.add(txtSoLuong);
		
		JLabel lblTimKiem = new JLabel("Tìm Kiếm Sản Phẩm");
		lblTimKiem.setForeground(Color.WHITE);
		lblTimKiem.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblTimKiem.setBounds(706, 132, 170, 21);
		contentPane.add(lblTimKiem);
		
		JLabel lblDanhMc = new JLabel("Danh Mục ");
		lblDanhMc.setForeground(Color.WHITE);
		lblDanhMc.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblDanhMc.setBounds(250, 132, 88, 26);
		contentPane.add(lblDanhMc);
		
		JComboBox combo_category = new JComboBox();
		combo_category.setBounds(343, 133, 161, 24);
		contentPane.add(combo_category);
		
		
		
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btntimkiem.addActionListener(this);
		btnTimKhachHang.addActionListener(this);
		btnHuy.addActionListener(this);
		btnDatHang.addActionListener(this);
		btnTaoMoi.addActionListener(this);
		

		JLabel background = new JLabel("");
		background.setHorizontalAlignment(SwingConstants.CENTER);
		background.setIcon(new ImageIcon(view_QuanLyNhanVien.class.getResource("/image/bgCF.jpg")));
		background.setBounds(0, 5, 1162, 613);
		contentPane.add(background);
//		
		
		loadData();
//		refresh();
	}
	
	private void loadData() throws SQLException {
		
//		for(NhanVien nv : list_nv.getAll()) {
//			String gioiTinh = "";
//			if(nv.getGioiTinh() == true) {
//				gioiTinh = "Nam"; 
//			}else {
//				gioiTinh = "Nữ";
//			}
//			tableModel.addRow(new Object[] {nv.getMaNV(),nv.getTenNV(),nv.getDiaChi(),nv.getSdt(),nv.getChucVu(),gioiTinh});
//		}
		tableModel.setRowCount(0);
		for (Ban item : list_Ban.getAll()) {
			tableModel.addRow(new Object[] {
					item.getMaBan(),
					item.getTenBan()
			});
		}
	}
	public void loadByName() {
		String ten = txtTimKiem.getText();
		ArrayList<Ban>tempList = new ArrayList<Ban>();
		tempList = list_Ban.findByName(ten);
		tableModel.setRowCount(0);
		for(Ban item :tempList) {
			tableModel.addRow(new Object[] {
					item.getMaBan(),
					item.getTenBan()
			});
		}
	}
	
	private void initComponents() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				formWindowClosing(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 300, Short.MAX_VALUE));

		pack();
	}
	private void formWindowClosing(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_formWindowClosing
		Main_form_manager gdql = new Main_form_manager();
		gdql.setLocationRelativeTo(null);
		gdql.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	

}
