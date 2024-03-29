package BookStore;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App {
    
    public static void taoFileTuDong(){
        File file = new File("book.txt");
        if(!file.exists()){
            try(FileWriter write = new FileWriter(file,true)){
                StringBuilder sb = new StringBuilder();
                sb.append("Giai thich ngu phap tieng Anh#001P#M.L Huong;H.T Uyen#560#Giao duc#Da Nang#220000#Sach danh cho viec on luyen ngu phap#Giay#A4#Mem#\\\\\\\\#\\\\\\\\#20\n" +"");
                sb.append("Giai thich ngu phap tieng Anh#002P#M.L Huong;H.T Uyen#560#Giao duc#Da Nang#285000#Sach danh cho viec on luyen ngu phap#Giay#A4#Cung#\\\\\\\\#\\\\\\\\#5\n" +"");
                sb.append("Su im lang cua bay cuu#003P#Thomas Harris#359#Trinh tham#Hoi Nha Van#115000#Tieu thuyet trinh tham kinh di day hap dan#Giay#A4#Mem#\\\\\\\\#\\\\\\\\#15\n" +"");
                sb.append("Su im lang cua bay cuu#004P#Thomas Harris#359#Trinh tham#Hoi Nha Van#185000#Tieu thuyet trinh tham kinh di day hap dan#Giay#A4#Cung#\\\\\\\\#\\\\\\\\#5\n" +"");
                sb.append("Nhan gui mot toi nguoi da yeu em#005P#Otono Yomoji#235#Tinh cam#Ha Noi#128000#Cau truyen tinh cam va noi buon#Giay#A5#Mem#\\\\\\\\#\\\\\\\\#30\n" +"");
                sb.append("Nhan gui tat ca cac em nguoi toi da yeu#006P#Otono Yomoji#235#Tinh cam#Ha Noi#128000#Cau truyen tinh cam va noi buon#Giay#A5#Mem#\\\\\\\\#\\\\\\\\#30\n" +"");
                sb.append("5centimet tren giay#007P#Shinkai Makoto#188#Tinh cam#Ha Noi#50000#Cau truyen tinh cam tuoi thanh xuan#Giay#A5#Mem#\\\\\\\\#\\\\\\\\#30\n" +"");
                sb.append("Thi va su phoi hop thi trong tieng Anh#008P#M.L Huong; N.T.T Tam#183#Giao duc#Da Nang#56800#Sach danh cho viec on luyen ngu phap#Giay#A4#Mem#\\\\\\\\#\\\\\\\\#22\n" +"");
                sb.append("Thi va su phoi hop thi trong tieng Anh#009P#M.L Huong; N.T.T Tam#183#Giao duc#Da Nang#122000#Sach danh cho viec on luyen ngu phap#Giay#A4#Cung#\\\\\\\\#\\\\\\\\#5\n" +"");
                sb.append("Dong tu bat quy tac & ngu phap tieng Anh co ban#010P#M.LHuong#200#Giao duc#Da Nang#34400#Sach danh cho viec on luyen ngu phap#Giay#A4#Mem#\\\\\\\\#\\\\\\\\#10\n" +"");
                sb.append("Hieu ve trai tim#011P#Minh Niem#480#Tam ly#TP.HCM#158000#Hieu them ve tam ly cua ban than#Giay#A4#Mem#\\\\\\\\#\\\\\\\\#10\n" +"");
                sb.append("Atomic Habits#012P#James Clear#200#Tam ly#NXB The Gioi#186000#Hieu them ve tam ly cua ban than#Giay#A4#Mem#\\\\\\\\#\\\\\\\\#20\n" +"");
                sb.append("Atomic Habits#013P#James Clear#200#Tam ly#NXB The Gioi#250000#Hieu them ve tam ly cua ban than#Giay#A4#Cung#\\\\\\\\#\\\\\\\\#4\n" +"");
                sb.append("Dam nghi lai#014P#Adam Grant#336#Tam ly#NXB Dan Tri#153000#Hieu them ve tam ly cua ban than#Giay#A4#Mem#\\\\\\\\#\\\\\\\\#20\n" +"");
                sb.append("Thay doi cuoc song than so hoc#015P#L.D.Q Huong#240#Tam ly#NXB HaNoi#342000#Hieu them ve tam ly cua ban than#Giay#A4#Mem#\\\\\\\\#\\\\\\\\#15\n" +"");
                sb.append("Toi thay hoa vang tren co xanh#016P#Nguyen Nhat Anh#378#Truyen ngan#NXB Tre#82000#Nhung cau truyen thu vi hap dan#Giay#A5#Mem#\\\\\\\\#\\\\\\\\#16\n" +"");
                sb.append("Toi thay hoa vang tren co xanh#017P#Nguyen Nhat Anh#378#Truyen ngan#NXB Tre#150000#Nhung cau truyen thu vi hap dan#Giay#A5#Cung#\\\\\\\\#\\\\\\\\#3\n" +"");
                sb.append("Co hai con meo ngoi ben cua so#018P#Nguyen Nhat Anh#210#Truyen ngan#NXB Tre#71500#Nhung cau truyen thu vi hap dan#Giay#A5#Mem#\\\\\\\\#\\\\\\\\#16\n" +"");
                sb.append("Mat biec#019P#Nguyen Nhat Anh#300#Truyen ngan#NXB Tre#75400#Nhung cau truyen thu vi hap dan#Giay#A5#Mem#\\\\\\\\#\\\\\\\\#12\n" +"");
                sb.append("Cho toi xin mot ve di tuoi tho#020P#Nguyen Nhat Anh#208#Truyen ngan#NXB Tre#72000#Nhung cau truyen thi vi hap dan#Giay#A5#Mem#\\\\\\\\#\\\\\\\\#13\n" +"");
                sb.append("Tuoi tre dang gia bao nhieu#021P#Rosie Nguyen#283#Tam ly#Hoi Nha Vien#80000#Hieu them ve tam ly cua ban than#Giay#A4#Mem#\\\\\\\\#\\\\\\\\#7\n" +"");
                sb.append("O quan ca phe cua tuoi tre lac loi#022P#Patrick Modiano#153#Truyen ngan#NXB Van Hoc#59000#Nhung cau truyen thi vi hap dan#Giay#A5#Mem#\\\\\\\\#\\\\\\\\#4\n" +"");
                sb.append("Vo nhat#023P#Kim Lan#242#Truyen ngan#NXB Van Hoc#41500#Nhung cau truyen thi vi hap dan#Giay#A4#Mem#\\\\\\\\#\\\\\\\\#4\n" +"");
                sb.append("Lap trinh ngon ngu C++#024P#V.V Vu; P.T.T Hien#300#Giao duc#NXB KHKT#108000#Lap trinh ngon ngu may tinh#Giay#A4#Mem#\\\\\\\\#\\\\\\\\#18\n" +"");
                sb.append("Lap trinh huong doi tuong#025P#P.V At; L.T Thong#488#Giao duc#NXB BKHN#168000#Lap trinh huong doi tuong#Giay#A4#Mem#\\\\\\\\#\\\\\\\\#4\n" +"");
                sb.append("Tra hoa nu#026P#Alexandre Dumas#303#Tinh cam#Hoi Nha Van#60000#nhung cau truyen tinh yeu lang man#Giay#A4#Mem#\\\\\\\\#\\\\\\\\#8\n" +"");
                sb.append("Rung Na Uy#027P#Naruki Murakami#556#Tinh cam#Hoi Nha Van#126000#nhung cau truyen tinh yeu lang man#Giay#A4#Mem#\\\\\\\\#\\\\\\\\#12\n" +"");
                sb.append("An mang tren song Nile#028P#Agatha Christie#336#Trinh tham#NXB Tre#147000#Nhung cau truyen ly ki hap dan#Giay#A4#Mem#\\\\\\\\#\\\\\\\\#22\n" +"");
                sb.append("An mang tren chuyen tau toc hanh Phuong Dong#029P#Agatha Christie#306#Trinh Tham#NXb Tre#110000#Nhung cau truyen ly ki hap dan#Giay#A4#Mem#\\\\\\\\#\\\\\\\\#2\n" +"");
                sb.append("Giai thich ngu phap tieng Anh#030SP#M.L Huong;H.T Uyen#560#Giao duc#Da Nang#110000#Sach danh cho viec on luyen ngu phap#pdf#\\\\\\\\#\\\\\\\\#mb#4.5#\\\\\\\\\n" +"");
                sb.append("Giai thich ngu phap tieng Anh#031SE#M.L Huong;H.T Uyen#560#Giao duc#Da Nang#110000#Sach danh cho viec on luyen ngu phap#epub#\\\\\\\\#\\\\\\\\#mb#4.2#\\\\\\\\\n" +"");
                sb.append("Su im lang cua bay cuu#032SP#Thomas Harris#359#Trinh tham#Hoi Nha Van#74000#Tieu thuyet trinh tham kinh di day hap dan#pdf#\\\\\\\\#\\\\\\\\#mb#2.2#\\\\\\\\\n" +"");
                sb.append("Su im lang cua bay cuu#033SE#Thomas Harris#359#Trinh tham#Hoi Nha Van#72000#Tieu thuyet trinh tham kinh di day hap dan#epub#\\\\\\\\#\\\\\\\\#mb#1.4#\\\\\\\\\n" +"");
                sb.append("Nhan gui mot toi nguoi da yeu em#034SP#Otono Yomoji#235#Tinh cam#Ha Noi#84000#Cau truyen tinh cam va noi buon#pdf#\\\\\\\\#\\\\\\\\#mb#3#\\\\\\\\\n" +"");
                sb.append("Nhan gui mot toi nguoi da yeu em#034SP#Otono Yomoji#235#Tinh cam#Ha Noi#84000#Cau truyen tinh cam va noi buon#pdf#\\\\\\\\#\\\\\\\\#mb#3#\\\\\\\\\n" +"");
                sb.append("Nhan gui tat ca cac em nguoi toi da yeu#035SP#Otono Yomoji#235#Tinh cam#Ha Noi#84000#Cau truyen tinh cam va noi buon#pdf#\\\\\\\\#\\\\\\\\#mb#3#\\\\\\\\\n" +"");
                sb.append("5centimet tren giay#036SP#Shinkai Makotokai#188#Tinh cam#Ha Noi#25000#Cau truyen tinh cam tuoi thanh xuan#pdf#\\\\\\\\#\\\\\\\\#mb#3.7#\\\\\\\\\n" +"");
                sb.append("Thi va su phoi hop thi trong tieng Anh#037SP#M.L Huong; N.T.T Tam#183#Giao duc#Da Nang#30000#Sach danh cho viec on luyen ngu phap#pdf#\\\\\\\\#\\\\\\\\#mb#2.8#\\\\\\\\\n" +"");
                sb.append("Thi va su phoi hop thi trong tieng Anh#038SE#M.L Huong; N.T.T Tam#183#Giao duc#Da Nang#60000#Sach danh cho viec on luyen ngu phap#epub#\\\\\\\\#\\\\\\\\#mb#2.4#\\\\\\\\\n" +"");
                sb.append("Dong tu bat quy tac & ngu phap tieng Anh co ban#039SP#M.LHuong#200#Giao duc#Da Nang#20000#Sach danh cho viec on luyen ngu phap#pdf#\\\\\\\\#\\\\\\\\#mb#2.4#\\\\\\\\\n" +"");
                sb.append("Hieu ve trai tim#040SP#Minh Niem#480#Tam ly#TP.HCM#78000#Hieu them ve tam ly cua ban than#pdf#\\\\\\\\#\\\\\\\\#mb#3.3#\\\\\\\\\n" +"");
                sb.append("Atomic Habits#041SP#James Clear#200#Tam ly#NXB The Gioi#100000#Hieu them ve tam ly cua ban than#pdf#\\\\\\\\#\\\\\\\\#mb#2.2#\\\\\\\\\n" +"");
                sb.append("Atomic Habits#042SE#James Clear#200#Tam ly#NXB The Gioi#95000#Hieu them ve tam ly cua ban than#epub#\\\\\\\\#\\\\\\\\#mb#2.2#\\\\\\\\\n" +"");
                sb.append("Dam nghi lai#043SP#Adam Grant#336#Tam ly#NXB Dan Tri#65000#Hieu them ve tam ly cua ban than#pdf#\\\\\\\\#\\\\\\\\#mb#1.4#\\\\\\\\\n" +"");
                sb.append("Thay doi cuoc song than so hoc#044SP#L.D.Q Huong#240#Tam ly#NXB HaNoi#200000#Hieu them ve tam ly cua ban than#pdf#\\\\\\\\#\\\\\\\\#mb#4.2#\\\\\\\\\n" +"");
                sb.append("Toi thay hoa vang tren co xanh#045SP#Nguyen Nhat Anh#378#Truyen ngan#NXB Tre#40000#Nhung cau truyen thu vi hap dan#pdf#\\\\\\\\#\\\\\\\\#mb#4.7#\\\\\\\\\n" +"");
                sb.append("Toi thay hoa vang tren co xanh#046SE#Nguyen Nhat Anh#378#Truyen ngan#NXB Tre#80000#Nhung cau truyen thu vi hap dan#epub#\\\\\\\\#\\\\\\\\#mb#4.4#\\\\\\\\\n" +"");
                sb.append("Co hai con meo ngoi ben cua so#047SE#Nguyen Nhat Anh#210#Truyen ngan#NXB Tre#52500#Nhung cau truyen thu vi hap dan#epub#\\\\\\\\#\\\\\\\\#mb#4.2#\\\\\\\\\n" +"");
                sb.append("Mat biec#048SP#Nguyen Nhat Anh#300#Truyen ngan#NXB Tre#55200#Nhung cau truyen thu vi hap dan#pdf#\\\\\\\\#\\\\\\\\#mb#4.7#\\\\\\\\\n" +"");
                sb.append("Cho toi xin mot ve di tuoi tho#049SP#Nguyen Nhat Anh#208#Truyen ngan#NXB Tre#64000#Nhung cau truyen thi vi hap dan#pdf#\\\\\\\\#\\\\\\\\#mb#2.2#\\\\\\\\\n" +"");
                sb.append("Tuoi tre dang gia bao nhieu#050SP#Rosie Nguyen#283#Tam ly#Hoi Nha Vien#63000#Hieu them ve tam ly cua ban than#pdf#\\\\\\\\#\\\\\\\\#mb#2.2#\\\\\\\\\n" +"");
                sb.append("O quan ca phe cua tuoi tre lac loi#051SP#Patrick Modiano#153#Truyen ngan#NXB Van Hoc#27000#Nhung cau truyen thi vi hap dan#pdf#\\\\\\\\#\\\\\\\\#mb#1.4#\\\\\\\\\n" +"");
                sb.append("Vo nhat#052SP#Kim Lan#242#Truyen ngan#NXB Van Hoc#20000#Nhung cau truyen thi vi hap dan#pdf#\\\\\\\\#\\\\\\\\#mb#1.4#\\\\\\\\\n" +"");
                sb.append("Lap trinh ngon ngu C++#053SP#V.V Vu; P.T.T Hien#300#Giao duc#NXB KHKT#70000#Lap trinh ngon ngu may tinh#pdf#\\\\\\\\#\\\\\\\\#mb#3.3#\\\\\\\\\n" +"");
                sb.append("Lap trinh huong doi tuong#054SP#P.V At; L.T Thong#488#Giao duc#NXB BKHN#80000#Lap trinh huong doi tuong#pdf#\\\\\\\\#\\\\\\\\#mb#4.4#\\\\\\\\\n" +"");
                sb.append("Tra hoa nu#055SP#Alexandre Dumas#303#Tinh cam#Hoi Nha Van#50000#nhung cau truyen tinh yeu lang man#pdf#\\\\\\\\#\\\\\\\\#mb#2.7#\\\\\\\\\n" +"");
                sb.append("Rung Na Uy#056SP#Naruki Murakami#556#Tinh cam#Hoi Nha Van#110000#nhung cau truyen tinh yeu lang man#pdf#\\\\\\\\#\\\\\\\\#mb#2.2#\\\\\\\\\n" +"");
                sb.append("An mang tren song Nile#057SP#Agatha Christie#336#Trinh tham#NXB Tre#120000#Nhung cau truyen ly ki hap dan#pdf#\\\\\\\\#\\\\\\\\#mb#4.7#\\\\\\\\\n" +"");
                sb.append("An mang tren chuyen tau toc hanh Phuong Dong#058SP#Agatha Christie#306#Trinh Tham#NXb Tre#90000#Nhung cau truyen ly ki hap dan#pdf#\\\\\\\\#\\\\\\\\#mb#4.8#\\\\\\\\\n" +"");
                write.write(sb.toString());
                write.flush();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void taofileNhanVien()
    {
        File filenhanvien= new File("nhanvien.txt");
        if ( !filenhanvien.exists())
        {
            try(FileWriter ghiFile = new FileWriter(filenhanvien,true)){
                
                StringBuilder StringBld = new StringBuilder();
                    StringBld.append("nv11111#Tieu Long Nu#23/12/2000#shipper#pt#300").append(System.lineSeparator());
                    StringBld.append("nv22222#Duong Qua#21/02/1999#quanly#ft#26").append(System.lineSeparator());
                    StringBld.append("nv33333#Duong Khang#24/11/2003#shipper#pt#117.4").append(System.lineSeparator());
                    StringBld.append("nv44444#Quach Tinh#12/12/2021#shipper#pt#267.1").append(System.lineSeparator());
                    StringBld.append("nv55555#Hoang Duoc Su#29/02/2019#quanly#ft#28").append(System.lineSeparator());
                    StringBld.append("nv66666#Hong That Cong#23/01/2018#quanly#ft#29").append(System.lineSeparator());
                    StringBld.append("nv77777#Au Duong Phong#21/01/2017#quanly#pt#201.3").append(System.lineSeparator());
                    StringBld.append("nv88888#Vuong Trung Duong#21/05/2022#shipper#pt#136.5").append(System.lineSeparator());
                    StringBld.append("nv99999#Doan Tri Hung#04/02/2011#shipper#ft#240").append(System.lineSeparator());
                    StringBld.append("nv00000#Ly Mac Sau#29/02/2020#quanly#pt#150");
               ghiFile.write(StringBld.toString());
               ghiFile.flush();
            } catch (IOException ex) {
                System.out.println("Lỗi IO");
            }  
        }
        File filetaikhoannhanvien= new File("taikhoannhanvien.txt");
        if ( !filetaikhoannhanvien.exists())
        {
            try(FileWriter ghiFile = new FileWriter(filetaikhoannhanvien,true)){
                
                StringBuilder StringBld = new StringBuilder();
                    StringBld.append("nv11111#123").append(System.lineSeparator());
                    StringBld.append("nv22222#123").append(System.lineSeparator());
                    StringBld.append("nv33333#123").append(System.lineSeparator());
                    StringBld.append("nv44444#123").append(System.lineSeparator());
                    StringBld.append("nv55555#123").append(System.lineSeparator());
                    StringBld.append("nv66666#123").append(System.lineSeparator());
                    StringBld.append("nv77777#123").append(System.lineSeparator());
                    StringBld.append("nv88888#123").append(System.lineSeparator());
                    StringBld.append("nv99999#123").append(System.lineSeparator());
                    StringBld.append("nv00000#123");
               ghiFile.write(StringBld.toString());
               ghiFile.flush();
            } catch (IOException ex) {
                System.out.println("Lỗi IO");
            }  
        }
    }
    
    public static void taoFile()
    {
        try {
            File fileDonHang= new File("DonHang.txt");
            if ( !fileDonHang.exists())
                fileDonHang.createNewFile();
            File fileUser= new File("user.txt");
            if ( !fileUser.exists())
                fileUser.createNewFile();
            File fileUserLogin= new File("userLogin.txt");
            if (!fileUserLogin.exists())
                fileUserLogin.createNewFile();
            File fileGiamGia= new File("voucher.txt");
            if(!fileGiamGia.exists())
                fileGiamGia.createNewFile();
        } 
        catch (IOException ex) {
            System.out.println("Lỗi IO");
        }
        
    }
    public static void startMenu()
    {
        System.out.println("\t+---------Chào mừng đến với Ứng dụng mua hàng BookStore-------+");
        System.out.printf("\t| %-60s|\n","1. Đăng nhập");
        System.out.printf("\t| %-60s|\n","2. Tạo tài khoản");
        System.out.printf("\t| %-60s|\n","3. Dùng guest để truy cập vào app");
        System.out.printf("\t| %-60s|\n","0. Thoát.");
        System.out.println("\t+-------------------------------------------------------------+");
    }
    
    public static void main(String[] args) throws IOException {
        taoFileTuDong();
        taoFile();
        taofileNhanVien();
        int choice=0;
        do {  
            TaiKhoanKhachHang demo= new TaiKhoanKhachHang();
            TaiKhoanNhanVien tk=new TaiKhoanNhanVien();
            startMenu();  
            FileWriter userLogin= new FileWriter("userLogin.txt");
            userLogin.write("");
            userLogin.flush();
            Scanner input= new Scanner(System.in);
            try {

                System.out.print("Chọn thao tác: ");
                choice=Integer.parseInt(input.nextLine());
                switch(choice)
                {
                    case 1:
                        int chon=0;
                        System.out.println("\t+--------------------Loại tài khoản đăng nhập-----------------+");
                        System.out.printf("\t| %-60s|\n","1. Đăng nhập vào tài khoản khách hàng");
                        System.out.printf("\t| %-60s|\n","2. Đăng nhập vào tài khoản nhân viên");
                        System.out.println("\t+-------------------------------------------------------------+");
                        try {
                            System.out.print("Chọn thao tác: ");
                            chon=Integer.parseInt(input.nextLine());
                            if ( chon <1 || chon >2)
                                System.out.println("\n--Vui lòng chọn đúng thao tác !!!--\n");
                        }
                        catch(NumberFormatException ei){
                            System.out.println("\n--Vui lòng chọn đúng các thao tác đã hiển thị!!!--\n");
                            chon=0;
                        }
                        if ( chon==1)
                        {
                            int check=demo.kiemTraDangNhap();
                            if ( check==1)
                                demo.giaodienKhachHang();
                        }
                        else if ( chon==2)
                        {
                            NhanVien nhanvien=tk.xacMinhTaiKhoanNhanVien();
                            if(nhanvien instanceof NhanVienGiaoHang) {
                                NhanVienGiaoHang nv=(NhanVienGiaoHang) nhanvien;
                                nv.quanLyDonHang();
                            }else if(nhanvien instanceof NhanVienQuanLy) {
                                NhanVienQuanLy nv=(NhanVienQuanLy) nhanvien;
                                nv.quanLy();
                            }
                        }
                        break;
                        
                    case 2:
                        demo.setInfo();
                        break;
                    case 3:
                        demo.chucNangChoKhach();
                        break;    
                    case 0:
                        FileWriter user= new FileWriter("userLogin.txt");
                            user.write("");
                            user.flush();
                            break;    
                }
                if ( choice < 0 || choice > 3)
                System.out.println("\n--Vui lòng chọn đúng các thao tác đã hiển thị!!!--\n");

            } 
            catch(NumberFormatException ei)
            {
                System.out.println("\n--Vui lòng chọn đúng các thao tác đã hiển thị!!!--\n");
                choice=-1;
            }
            catch ( IOException e)
            {
                System.out.println("Lỗi không tìm được file");
            }
            
            if ( choice==0)
                System.out.println("Cảm ơn đã sử dụng dịch vụ ^^");
            
        }while (choice !=0);
    }
    
}