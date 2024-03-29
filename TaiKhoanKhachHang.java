/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BookStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Admin
 */
public class TaiKhoanKhachHang {
    private String username;
    private String userpassword;
    private KhachHang khachhang;
    private GioHang giohang;
    private DonHang donhang= new DonHang();
    private DanhGia danhgia= new DanhGia();
    
    public String fileGioHang;
    public TuSach tuSach= new TuSach();
    
    public TaiKhoanKhachHang() {
    }
    
    
    public TaiKhoanKhachHang(String username, String userpassword) {
        this.username = username;
        this.userpassword = userpassword;
    }

    public String getUsername() {
        return username;
    }

    public String getUserpassword() {
        return userpassword;
    }
    

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public KhachHang getKhachhang() {
        return khachhang;
    }

    
    public void setKhachhang(KhachHang khachhang) {
        this.khachhang = khachhang;
    }

    public GioHang getGiohang() {
        return giohang;
    }

    public void setGiohang(GioHang giohang) {
        this.giohang = giohang;
    }

    public DonHang getDonhang() {
        return donhang;
    }

    public void setDonhang(DonHang donhang) {
        this.donhang = donhang;
    }
    

    public String getFileGioHang() {
        return fileGioHang;
    }

    public void setFileGioHang(String fileGioHang) {
        this.fileGioHang = fileGioHang;
    }

    public TuSach getTuSach() {
        return tuSach;
    }

    public DanhGia getDanhgia() {
        return danhgia;
    }

    

    private void duaDuLieuVaoArrayList()
    {
        File file= new File(this.getFileGioHang());
        try ( Scanner scan= new Scanner(file))
        {
            GioHang temp= new GioHang();
            CT_GioHang tmp= new CT_GioHang();
            while ( scan.hasNextLine())
            {
                String dong=scan.nextLine();
                String dulieu[]=dong.split("#");
                if ( dulieu[0].equalsIgnoreCase(this.getKhachhang().getMaKH()))
                {
                    tmp.setMaKhachhang(dulieu[0]);
                    tmp.setMaSach(dulieu[1]);
                    tmp.setTenSach(dulieu[2]);
                    tmp.setLoaiSach(dulieu[3]);
                    tmp.setGiaSach(Double.parseDouble(dulieu[4]));
                    tmp.setSoLuong(Integer.parseInt(dulieu[5]));
                    tmp.setThanhTien(Double.parseDouble(dulieu[6]));
                    temp.setDsSanPham(tmp);
                }
            }
            this.setGiohang(temp);
            
        } catch (FileNotFoundException ex) {
            
        }
        
    }
    
    public  void setInfo()
    {
        Scanner input= new Scanner(System.in);
        System.out.println("----------Tạo tài khoản----------");
        System.out.print("1.Tạo tên đăng nhập: ");
        this.setUsername(input.nextLine());;
        System.out.print("2.Tạo mật khẩu: ");
        this.setUserpassword(input.nextLine());;
        try (FileWriter fileWtr= new FileWriter("user.txt",true))
        {
            StringBuilder sb= new StringBuilder();
            sb.append(this.getUsername()).append("#").append(this.getUserpassword());
            KhachHang thongtin= new KhachHang();
            thongtin.setInfo();
            sb.append("#").append(thongtin.getMaKH()).append(("#")).append(thongtin.getTenKH()).append(("#")).append(thongtin.getSdt()).append("#").append(thongtin.getEmail()).append("#");
            for ( int i=0; i< thongtin.getDiaChi().size();i++)
             sb.append(thongtin.getDiaChi().get(i)).append(";");
            sb.append(System.lineSeparator());
            fileWtr.write(sb.toString());
            fileWtr.flush();
        }catch (IOException e){
            System.out.println("File ko tồn tại");
        }
        System.out.println("Đã tạo tài khoản thành công!");
        int check=kiemTraDangNhap();
        if ( check==1)
            giaodienKhachHang();
    }
    
    
    private void checkFileGioHang(){
        String fileName="GioHang";
        fileName=fileName.concat(this.getKhachhang().getMaKH()).concat(".txt");
        File file= new File(fileName);
        if (file.exists())
            this.setFileGioHang(fileName);
        else 
            try 
            {
                file.createNewFile();
                this.setFileGioHang(fileName);
            } 
            catch (IOException ex) {
                System.out.println("Lỗi IOexception!!!");
            }
    }
    
    public  int kiemTraDangNhap()
    {
        int solan=1;
        int tieptuc=1, flag=0;
        do
        {
            Scanner input= new Scanner(System.in);
            System.out.println("----------Dăng nhập vào tài khoản----------");
            String tenDangNhap, matKhau;
            System.out.print("1.Tên đăng nhập: ");
            tenDangNhap=input.nextLine();
            System.out.print("2.Mật khẩu: ");
            matKhau=input.nextLine();
            File file= new File("user.txt");
            try (Scanner scan= new Scanner(file)){
                String dong, dulieu[] = null, user, pass;
                while (scan.hasNextLine())
                {
                    dong=scan.nextLine();
                    dulieu=dong.split("#");
                    user=dulieu[0].trim();
                    pass=dulieu[1].trim();
                    if ( user.equalsIgnoreCase(tenDangNhap.trim()) && pass.equalsIgnoreCase(matKhau.trim()))
                    {
                        flag=1;
                        break;
                    } 
                }
                if ( flag ==1)
                {
                    System.out.println("Đăng nhập thành công !");
                    this.setUsername(tenDangNhap.trim()); // đưa userName và password vào 2 biến private khi đăng nhập
                    this.setUserpassword(matKhau.trim());
                    this.setKhachhang(new KhachHang(dulieu[2],dulieu[3],dulieu[4],dulieu[5],dulieu[6])); // đưa thông tin khách hàng vào biến private
                    checkFileGioHang();
                    duaDuLieuVaoArrayList();
                    this.getDanhgia().setMaKhachHang(dulieu[2]);
                    FileWriter fileWrt= new FileWriter("userLogin.txt",true);
                    StringBuilder StrBld= new StringBuilder();
                    StrBld.append(this.getUsername()).append("#").append(this.getUserpassword());
                    fileWrt.write(StrBld.toString());
                    fileWrt.flush();
                    tieptuc=0;
                }
                else 
                {
                    if ( solan <3)
                    do 
                    {
                    System.out.println("Tài khoản không tồn tại !");
                    System.out.println("Bạn có muốn nhập lại ?");
                    System.out.println("0.Không\t 1.Có");    
                    tieptuc=Integer.parseInt(input.nextLine());
                    if (tieptuc != 1 && tieptuc != 0)
                        System.out.println("Vui lòng chọn đúng thao tác !!");
                    }while (tieptuc != 1 && tieptuc != 0);
                        
                }
                if ( tieptuc==1)
                    solan++;
                if( solan >3)
                {
                    do 
                    {
                    System.out.println("Tài khoản không tồn tại !");
                    System.out.println("Bạn có muốn nhập lại ?");
                    System.out.println("0.Không\t 1.Có\t 2.Tạo tài khoản");    
                    tieptuc=Integer.parseInt(input.nextLine());
                    if (tieptuc != 1 && tieptuc != 0 && tieptuc!=2)
                        System.out.println("Vui lòng chọn đúng thao tác !!");
                    }while (tieptuc != 1 && tieptuc != 0 && tieptuc!=2);
                    if( tieptuc==2)
                    {
                        setInfo();
                        break;
                    }
                        
                }
            } 
            catch (FileNotFoundException ex) {
               System.out.println("Khong tim thay file user.txt");
            }
            catch(NumberFormatException ei)
            {
                System.out.println("\n--Vui lòng không nhập ký tự!!!--\n");
            } catch (IOException ex) {
                System.out.println("Khong tim thay file userLogin.txt");
            } 
        }while(tieptuc==1);
        return flag;
    }


    private boolean checkSanPhamTrongGio(String maKhachHang,String maSanPham, int soLuong)
    {
        boolean flag=false;

            // chạy for tìm thằng nào của mã đó rùi lấy để tăng số lượng
            for(CT_GioHang giohang : this.getGiohang().getDsSanPham())
            {
                if ( giohang.getMaKhachhang().equalsIgnoreCase(maKhachHang) && giohang.getMaSach().equalsIgnoreCase(maSanPham))
                {
                        giohang.setSoLuong(giohang.getSoLuong()+soLuong);
                        giohang.setThanhTien(giohang.getGiaSach()*giohang.getSoLuong());
                        flag=true;
                }
            
            }

        return flag;
    }
    
    public  void themVaoGiohang(){
    boolean flag=true; // kiểm tra số lượng sản phẩm
    File fileSach=new File("book.txt");
        try {
            Scanner docFileSach= new Scanner(fileSach);
            Scanner input= new Scanner(System.in);
            String maSanPham;
            int soLuongSanPham, kiemTraSach=0;
            System.out.print("Chọn mã sản phẩm để thêm vào giỏ: ");
            maSanPham=input.nextLine();
            while ( docFileSach.hasNextLine())
            {
                String Sach=docFileSach.nextLine();
                String thongTinSach[]=Sach.split("#");
                if ( maSanPham.equalsIgnoreCase(thongTinSach[1]))
                {
                    kiemTraSach=1;
                    System.out.print("Chọn số lượng muốn thêm vào giỏ: ");
                    soLuongSanPham=Integer.parseInt(input.nextLine());
                    if ( thongTinSach[8].equalsIgnoreCase("Giay") && soLuongSanPham > Integer.parseInt(thongTinSach[13]))
                    {
                        System.out.println("Số lượng sách này trong kho không đủ để bạn đặt hàng!!");
                        flag=false;
                    }
                    if(!thongTinSach[8].equalsIgnoreCase("Giay") && soLuongSanPham > Integer.parseInt(thongTinSach[13]))
                    {
                        System.out.println("Các loại sách điện tử chỉ nên đặt một sản phẩm duy nhất!!");
                        flag=false;
                    }
                    for( CT_GioHang giohang: this.getGiohang().getDsSanPham())
                        if (giohang.getLoaiSach().equalsIgnoreCase("Giay") &&giohang.getMaSach().equalsIgnoreCase(maSanPham) && (giohang.getSoLuong()+soLuongSanPham) >Integer.parseInt(thongTinSach[13]))
                        {
                            flag=false;
                            System.out.println("Số lượng sách này trong kho không đủ để bạn đặt hàng!!");
                            break;
                        }
                        else if (!giohang.getLoaiSach().equalsIgnoreCase("Giay") &&giohang.getMaSach().equalsIgnoreCase(maSanPham) && (giohang.getSoLuong()+soLuongSanPham) >Integer.parseInt(thongTinSach[13]))
                        {
                            flag=false;
                            System.out.println("Sản phẩm phiên bản điện tử bạn đã đặt trong giỏ rồi !!");
                            break;
                        }
                    
                    if(flag){
                        if ( checkSanPhamTrongGio(this.getKhachhang().getMaKH(), maSanPham, soLuongSanPham)==true)
                        {
                            this.getGiohang().ghiGioHangVaoFile(this.getKhachhang().getMaKH());
                            System.out.println("Bạn đã thêm sách thành công với sách đã có ^^");
                        }
                        else 
                        {
                            CT_GioHang sanpham= new CT_GioHang();
                            sanpham.setMaKhachhang(this.getKhachhang().getMaKH());
                            sanpham.setMaSach(maSanPham);
                            sanpham.setTenSach(thongTinSach[0]);
                            sanpham.setLoaiSach(thongTinSach[8]);
                            sanpham.setGiaSach(Double.parseDouble(thongTinSach[6]));
                            sanpham.setSoLuong(soLuongSanPham);
                            sanpham.setThanhTien(sanpham.getGiaSach()*soLuongSanPham);
                            this.getGiohang().getDsSanPham().add(sanpham);
                            System.out.println("Bạn đã thêm sách thành công ^^");
                        }              
                    }
                    
                    
                }   
            }
            if (kiemTraSach != 1)
                System.out.println("Không có mã sách này trong kho !!!");
        } catch (FileNotFoundException ex) {
            System.out.println("Không tìm thấy file !!!");
        }
              
    }
    
    
    public void xoaSanPhamTrongGioHang(){
        Scanner input= new Scanner(System.in);
        System.out.print("Chọn mã sản phẩm muốn xỏa khỏi giỏ:" );
        String ma=input.nextLine();
        boolean flag=false;
        for(int i=0; i < this.getGiohang().getDsSanPham().size(); i++)
        {
            CT_GioHang sanpham=this.getGiohang().getDsSanPham().get(i);
            if (sanpham.getMaSach().equalsIgnoreCase(ma))
            {
                this.getGiohang().getDsSanPham().remove(i);
                flag=true;
            }
        }
        if ( flag)
            System.out.println("Đã xóa sản phẩm khỏi giỏ");
        else 
            System.out.println("Không có sản phẩm trong giỏ để xóa");
    }
  
    public  void chucNangNguoiDung(){
        Scanner input= new Scanner(System.in);
        File file= new File("user.txt");
        int choice;
        do {
            System.out.println("\t+---------------------------------------------Chức năng người dùng---------------------------------------------+");
            System.out.printf("\t| %-35s| %-35s| %-35s|\n","1.Xem thông tin cá nhân","2.Xem danh sách sản phẩm bán","3.Tìm kiếm sản phẩm");
            System.out.printf("\t| %-35s| %-35s| %-35s|\n","4.Thêm sản phẩm trong giỏ","5.Xem giỏ hàng","6.Xóa sản phẩm trong giỏ hàng");
            System.out.printf("\t| %-35s| %-35s| %-35s|\n","7.Đặt đơn hàng","8.Xem đơn đã đặt","9.Hủy đơn hàng");
            System.out.printf("\t| %-35s| %-35s| %-35s|\n","10.Dánh giá sản phẩm","11.Xem đánh giá sách" ,"12. Xem các mã giảm giá");
            System.out.printf("\t| %-109s|\n", "0.Thoát khỏi chức năng người dùng");
            System.out.println("\t+--------------------------------------------------------------------------------------------------------------+");
            System.out.print("Chọn thao tác: ");
            choice=Integer.parseInt(input.nextLine());
            switch(choice)
            {
                case 1:
                    if ( this.getKhachhang().toString().isEmpty())
                    {
                        System.out.println("Không có thông tin. Bạn có muốn nhập thông tin không ?");
                        System.out.print("1. Có || 0. Không ");
                        int choiceCase1=Integer.parseInt(input.nextLine());
                        if(choiceCase1==1)
                        {
                            this.getKhachhang().setInfo();
                            
                            System.out.println(this.getKhachhang().toString());
                        }
                    }else System.out.println(this.getKhachhang().toString());
                   break;
                case 2:
                    TuSach tusach= new TuSach();
                    tusach.hienThiSachDangKinhDoanh();
                    break;
                case 3:
                    this.getTuSach().timSach();
                    System.out.println("Bạn có muốn thêm sách vào giỏ không ?");
                    System.out.println("1.Có\t2.Không");
                    int chon;
                   do {
                        chon=Integer.parseInt(input.nextLine());
                        if ( chon != 1 && chon !=2)
                            System.out.println("Vui lòng nhập đúng thao tác!!");
                        if( chon==1)
                        {
                            themVaoGiohang();
                            this.getGiohang().ghiGioHangVaoFile(this.getFileGioHang());
                        }
                        }while( chon != 1 && chon != 2);
                    break;
                case 4:
                    themVaoGiohang();
                    this.getGiohang().ghiGioHangVaoFile(this.getFileGioHang());
                    break;
                case 5:
                    this.getGiohang().xemGioHang(this.getFileGioHang());
                    break;
                case 6:
                    xoaSanPhamTrongGioHang();
                    this.getGiohang().ghiGioHangVaoFile(this.getFileGioHang());
                    break;
                case 7:
                    this.getDonhang().datHang(this.getFileGioHang());
                    break;
                case 8:
                    this.getDonhang().xemChiTietDonHang(this.getKhachhang().getMaKH());
                    break;
                case 9:
                    this.getDonhang().huyDonhang(this.getKhachhang().getMaKH());
                    break;
                case 10:
                    this.getDanhgia().danhGia("CT_DonHang.txt");
                    break;
                case 11:
                    this.getDanhgia().xemDanhGia();
                    break;
                case 12:
                    PhieuGiamGia.xemVoucher();
                    break;
            }
        }while (choice!=0);
    }
    
    public void chucNangChoKhach()
    {
        Scanner input= new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("\n***Hãy đăng nhập hoặc tạo tài khoản để dùng được nhiều chức năng hơn*** \n");
            System.out.println("\t+-----------------Chức năng dạng khách--------------+");
            System.out.printf("\t| %-50s|\n","1.Xem danh sách sản phẩm bán");
            System.out.printf("\t| %-50s|\n","2.Tìm kiếm sản phẩm");
            System.out.printf("\t| %-50s|\n","3.Xem đánh giá sản phẩm");
            System.out.printf("\t| %-50s|\n","0.Thoát");
            System.out.println("\t+---------------------------------------------------+");
            try {
                System.out.print("Chọn thao tác: ");
                choice=Integer.parseInt(input.nextLine());
                switch(choice)
                {
                    case 1:
                        TuSach tusach= new TuSach();
                        tusach.hienThiSachDangKinhDoanh();
                        break;
                    case 2:
                        this.getTuSach().timSach();
                        System.out.println("Bạn muốn đăng ký tài khoản để thêm sản phẩm vào giỏ hàng ?");
                        System.out.println("1.Có\t2.Không");
                        int chon;
                        do {
                            chon=Integer.parseInt(input.nextLine());
                            if ( chon != 1 && chon !=2)
                                System.out.println("Vui lòng nhập đúng thao tác!!");
                            if( chon==1)
                                this.setInfo();
                        }while( chon != 1 && chon != 2);
                        break;
                    case 3:
                        this.getDanhgia().xemDanhGia();
                        break;
                }
            }catch (NumberFormatException ei) {
                System.out.println("\n--Vui lòng chọn đúng các thao tác đã hiển thị!!!--\n");
                choice=1;
            }
            
        }while (choice!=0);
    }
        
    public void giaodienKhachHang()
    {
        int choice=1;
        do {
            Scanner input= new Scanner(System.in);
            System.out.println("\t+-------------Giao diện của Khách Hàng---------+");
            System.out.printf("\t| %-45s|\n","1. Chức năng người dùng");
            System.out.printf("\t| %-45s|\n","0. Thoát");
            System.out.println("\t+----------------------------------------------+");
            
            try{
                do {
                    System.out.print("Chọn thao tác: ");
                    choice=Integer.parseInt(input.nextLine());
                    if ( choice != 1 && choice != 0)
                        System.out.println("Vui lòng chọn đúng thao tác đã hiển thị !!!");
                }while ( choice != 1 && choice!= 0);

                switch(choice)
                {
                    case 1:
                        chucNangNguoiDung();
                        break;
                    case 0:
                        break;
                }
            }        
            catch(NumberFormatException ei)
            {
                System.out.println("\n--Vui lòng chọn đúng các thao tác đã hiển thị!!!--\n");
            }
            catch ( Exception e)
            {
                System.out.println(e.getMessage());
            }
            
        }while (choice !=0);
    }
    
}
