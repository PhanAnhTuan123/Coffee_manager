use QLCF

--INSERT BAN
INSERT INTO Ban(maBan,tenBan) 
VALUES ('BN001','B1')

INSERT INTO Ban(maBan,tenBan) 
VALUES ('BN002','B2')

--INSERT NHAN VIEN
INSERT INTO NhanVien(maNV,TenNV,DiaChi,SDT,ChucVu,GioiTinh) 
VALUES ('NV001','Tran Quang Minh','60 Nguyễn Biểu','0398887776','Nhân Viên',1)

INSERT INTO NhanVien(maNV,TenNV,DiaChi,SDT,ChucVu,GioiTinh) 
VALUES ('NV002','Tran Thi Kim Phuong','382A Nguyen Thai Hoc','0398887777','Nhân Viên',0)

--INSERT CHI TIET NHAN VIEN
INSERT INTO ChiTietNhanVien(maNV,TongSoCaLam,Luong) 
VALUES ('NV001',1,21000)

INSERT INTO ChiTietNhanVien(maNV,TongSoCaLam,Luong) 
VALUES ('NV002',1,22000)

--INSERT KHACH HANG
INSERT INTO KhachHang(maKH,tenKH,DiaChi,SDT) 
VALUES ('KH001','Trương Liêu','38 guyen Thi Dinh','0398232324')

INSERT INTO KhachHang(maKH,tenKH,DiaChi,SDT) 
VALUES ('KH002','Nguyễn Hoàng','68 Bùi Thị Xuân','0398323775')

--INSERT HANG HOA
INSERT INTO HangHoa(maHH,TenHH,Gia) 
VALUES ('HH001','Bò húc',20000)

INSERT INTO HangHoa(maHH,TenHH,Gia) 
VALUES ('HH002','Sting',25000)

INSERT INTO HangHoa(maHH,TenHH,Gia) 
VALUES ('HH003','Cà Phê Đen',15000)

--INSERT TAI KHOAN
INSERT INTO TaiKhoan(userName,passWord) 
VALUES ('NV001',1111)

INSERT INTO TaiKhoan(userName,passWord) 
VALUES ('NV002',2222)

--Test constaint tai khoan nhan vien

INSERT INTO TaiKhoan(userName,passWord) 
VALUES ('NV003',1234)

