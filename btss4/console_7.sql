#bt3
# create database quanlyvatuvatlieu;
use quanlyvatuvatlieu;

create table phieuxuat
(
    sopx     int primary key,
    ngayxuat datetime
);

create table vattu
(
    mavt  int primary key,
    tenvt varchar(255)
);

create table phieuxuatchitiet
(
    sopx        int,
    mavt        int,
    dongiaxuat  double,
    soluongxuat int,
    primary key (sopx, mavt),
    foreign key (sopx) references phieuxuat (sopx),
    foreign key (mavt) references vattu (mavt)
);
create table nhacungcap
(
    mancc  int primary key,
    tenncc varchar(255)
);

create table dondathang
(
    sodh        int primary key,
    diachi      varchar(255),
    sodienthoai varchar(11),
    mancc       int,
    ngaydh      datetime,
    foreign key (mancc) references nhacungcap (mancc)
);

create table chitietdondathang
(
    mavt int,
    sodh int,
    primary key (mavt, sodh),
    foreign key (mavt) references vattu (mavt),
    foreign key (sodh) references dondathang (sodh)
);

create table phieunhap
(
    sogn     int primary key,
    ngaynhap datetime,
    mancc    int,
    foreign key (mancc) references nhacungcap (mancc)
);

create table phieunhapchitiet
(
    sogn        int,
    mavt        int,
    dongianhap  double,
    soluongnhap int,
    mancc       int,
    primary key (sogn, mavt),
    foreign key (sogn) references phieunhap (sogn),
    foreign key (mavt) references vattu (mavt),
    foreign key (mancc) references nhacungcap (mancc)
);

INSERT INTO vattu (mavt, tenvt)
VALUES (1, 'Vật tư A'),
       (2, 'Vật tư B');


INSERT INTO phieuxuat (sopx, ngayxuat)
VALUES (1, '2023-01-01'),
       (2, '2023-02-02');

INSERT INTO phieuxuatchitiet (sopx, mavt, dongiaxuat, soluongxuat)
VALUES (1, 1, 10000, 15),
       (2, 2, 12000, 8);

INSERT INTO nhacungcap (mancc, tenncc)
VALUES (1, 'Nhà cung cấp A'),
       (2, 'Nhà cung cấp B');

INSERT INTO phieunhap (sogn, ngaynhap, mancc)
VALUES (1, '2023-02-12', 1),
       (2, '2023-02-12', 2);

INSERT INTO phieunhapchitiet (sogn, mavt, dongianhap, soluongnhap, mancc)
VALUES (1, 1, 9000, 20, 1),
       (2, 2, 11000, 12, 2);

INSERT INTO phieunhap (sogn, ngaynhap, mancc)
VALUES (3, '2023-03-03', 1),
       (4, '2023-04-04', 2);

INSERT INTO phieunhapchitiet (sogn, mavt, dongianhap, soluongnhap, mancc)
VALUES (3, 1, 1300000, 10, 1),
       (4, 2, 1250000, 15, 2);

INSERT INTO dondathang (sodh, diachi, sodienthoai, mancc, ngaydh)
VALUES (1, 'Long Biên, Hà Nội', '0912345678', 1, '2023-01-01'),
       (2, 'Hai Bà Trưng, Hà Nội', '0987654321', 2, '2023-02-02');
INSERT INTO chitietdondathang (mavt, sodh)
VALUES (1, 1),
       (2, 2);


#1 Hiển thị tất cả vật tư dựa vào phiếu xuất có số lượng lớn hơn 10:

select vattu.*
from phieuxuatchitiet
         join vattu on phieuxuatchitiet.mavt = vattu.mavt
where phieuxuatchitiet.soluongxuat > 10;
#2 Hiển thị tất cả vật tư mua vào ngày 12/2/2023:

select vattu.*
from phieunhapchitiet
         join vattu on phieunhapchitiet.mavt = vattu.mavt
         join phieunhap on phieunhapchitiet.sogn = phieunhap.sogn
where date(phieunhap.ngaynhap) = '2023-02-12';
#3 Hiển thị tất cả vật tư được nhập vào với đơn giá lớn hơn 1.200.000:

select vattu.*
from phieunhapchitiet
         join vattu on phieunhapchitiet.mavt = vattu.mavt
where phieunhapchitiet.dongianhap > 1200000;
#4 Hiển thị tất cả vật tư dựa vào phiếu xuất có số lượng lớn hơn 5:

select vattu.*
from phieuxuatchitiet
         join vattu on phieuxuatchitiet.mavt = vattu.mavt
where phieuxuatchitiet.soluongxuat > 5;
#5 Hiển thị tất cả nhà cung cấp ở Long Biên có số điện thoại bắt đầu với 09:

select nhacungcap.*
from nhacungcap
         join dondathang on nhacungcap.mancc = dondathang.mancc
where dondathang.diachi like 'Long Biên%'
  and dondathang.sodienthoai like '09%';
