/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BookStore;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ACER
 */
public class TuSach {
    private int soSach;
    private static int sachDaHienThi = 0;
    private ArrayList<Sach> books = new ArrayList<>();

    
    public TuSach() {
        this.soSach = 0;
    }

    public int getSoSach() {
        return soSach;
    }

    public void setSoSach(int soSach) {
        this.soSach = soSach;
    }

    public ArrayList<Sach> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Sach> books) {
        this.books = books;
    }
    public void setBooks(Sach sach)
    {
        this.books.add(sach);
    }
    
    public String laySo(String id){
        String phanMaSo = id.replaceAll("[^0-9]", ""); //thay the bat cu ki tu nao khong phai la so
        return phanMaSo;
    }
    
    public void themSach() {
        Scanner input = new Scanner(System.in);
        String tiepTuc=null;
        File file= new File("book.txt");
        do{
            Sach sach = new Sach();
            sach.nhapThongTinSachChoNhanVien();
            System.out.println("Ban muon them loai sach nao?");
            System.out.println("1: Sach giay | 2: Sach mem | 3: Ca hai");
            
            String choice = input.nextLine();
            while(!choice.equalsIgnoreCase("1")&&!choice.equalsIgnoreCase("2")&&!choice.equalsIgnoreCase("3")){
                System.out.println("Lua chon khong hop le, moi ban nhap lai.....");
                choice = input.nextLine();
            }
            switch (choice) {
            case "1":
                SachGiay sachA = new SachGiay();
                ((SachGiay) sachA).nhapThongTinSachGiay(sach);
                this.setBooks(sachA);
                soSach++;
                vietSachVaoFile1(choice);//vietSachVaoFile1 sẽ có dòng thông báo "viết sách vào file thành công", còn vietSachVaoFile2 sẽ không có dòng đó, chia ra là vid nếu dùng chung 1 cái mà đễ dòng thông báo sẽ bị lặp khi chọn case 3
                break;
            case "2":
                SachMem sachB = new SachMem();
                ((SachMem) sachB).nhapThongTinSachMem(sach);
                this.setBooks(sachB);
                soSach++;
                vietSachVaoFile1(choice);
                break;
            case "3":
                SachGiay sachC = new SachGiay();
                ((SachGiay) sachC).nhapThongTinSachGiay(sach);
                this.setBooks(sachC);
                soSach++;
                vietSachVaoFile2(choice);
                
                SachMem sachD = new SachMem();
                ((SachMem) sachD).nhapThongTinSachMem(sach);
                this.setBooks(sachD);
                soSach++;
                vietSachVaoFile1(choice);
                break; 
            default:
                System.out.println("Lua chon khong phu hop.....");
                break;
            }
            System.out.println("Ban co muon tiep tuc them sach? (Y/N)");
            tiepTuc=input.nextLine();
        }while(!tiepTuc.equalsIgnoreCase("N"));  
    }
    private void vietSachVaoFile1(String choice) {
        String fileName = "book.txt";
        File file = new File(fileName);

        try (FileWriter fileWriter = new FileWriter(file, true)) {
            for (Sach sach : this.getBooks()) {
                StringBuilder sb = new StringBuilder();
                sb.append(sach.getTenSach()).append("#");
                if (sach instanceof SachGiay) {
                    sb.append(sach.getMaSach()).append("P").append("#");
                } else if (sach instanceof SachMem) {
                    if(((SachMem) sach).getLoai().equalsIgnoreCase("pdf"))
                        sb.append(sach.getMaSach()).append("SF").append("#");
                    else if(((SachMem) sach).getLoai().equalsIgnoreCase("epub"))
                        sb.append(sach.getMaSach()).append("SE").append("#");
                }
                sb.append(sach.getTacGia()).append("#");
                sb.append(sach.getSoTrang()).append("#");
                sb.append(sach.getTheLoai()).append("#");
                sb.append(sach.getNXB()).append("#");
                sb.append(sach.getGia()).append("#");
                sb.append(sach.getMoTa()).append("#");

                if (sach instanceof SachGiay) {
                    sb.append("Giay").append("#");
                    sb.append(((SachGiay) sach).getKichThuoc()).append("#");
                    sb.append(((SachGiay) sach).getLoaiBia()).append("#");
                    sb.append("////").append("#");
                    sb.append("////").append("#");
                    sb.append(((SachGiay) sach).getHienCo());
                } else if (sach instanceof SachMem) {
                    sb.append(((SachMem) sach).getLoai()).append("#");
                    sb.append("////").append("#");
                    sb.append("////").append("#");
                    sb.append(((SachMem) sach).getKichCoDungLuong()).append("#");
                    sb.append(((SachMem) sach).getDungLuong()).append("#");
                    sb.append("1");
                }
                sb.append(System.lineSeparator());
                fileWriter.write(sb.toString());   
            }
            this.getBooks().clear();
            fileWriter.flush();
            System.out.println("Viet sach vao file thanh cong.....");
        } catch (IOException e) {
            System.out.println("Khong hop le" + e.getMessage());
        }
    }
    private void vietSachVaoFile2(String choice) {
        String fileName = "book.txt";
        File file = new File(fileName);

        try (FileWriter fileWriter = new FileWriter(file, true)) {
            for (Sach sach : this.getBooks()) {
                StringBuilder sb = new StringBuilder();
                sb.append(sach.getTenSach()).append("#");
                if (sach instanceof SachGiay) {
                    sb.append(sach.getMaSach()).append("P").append("#");
                } else if (sach instanceof SachMem) {
                    if(((SachMem) sach).getLoai().equalsIgnoreCase("pdf"))
                        sb.append(sach.getMaSach()).append("SF").append("#");
                    else if(((SachMem) sach).getLoai().equalsIgnoreCase("epub"))
                        sb.append(sach.getMaSach()).append("SE").append("#");
                }
//                sb.append(sach.getMaSach()).append("#");
                sb.append(sach.getTacGia()).append("#");
                sb.append(sach.getSoTrang()).append("#");
                sb.append(sach.getTheLoai()).append("#");
                sb.append(sach.getNXB()).append("#");
                sb.append(sach.getGia()).append("#");
                sb.append(sach.getMoTa()).append("#");

                if (sach instanceof SachGiay) {
                    sb.append("Giay").append("#");
                    sb.append(((SachGiay) sach).getKichThuoc()).append("#");
                    sb.append(((SachGiay) sach).getLoaiBia()).append("#");
                    sb.append("////").append("#");
                    sb.append("////").append("#");
                    sb.append(((SachGiay) sach).getHienCo());
                } else if (sach instanceof SachMem) {
                    sb.append(((SachMem) sach).getLoai()).append("#");
                    sb.append("////").append("#");
                    sb.append("////").append("#");
                    sb.append(((SachMem) sach).getKichCoDungLuong()).append("#");
                    sb.append(((SachMem) sach).getDungLuong()).append("#");
                    sb.append("1");
                }
                sb.append(System.lineSeparator());
                fileWriter.write(sb.toString());      
            }
            this.getBooks().clear();
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println("Khong hop le" + e.getMessage());
        }
    }
    
    
    private void timSachTheoTen(){
        Scanner input = new Scanner(System.in);
        System.out.println("Nhap ten sach ban muon tim: ");
        String tenSachCanTim = input.nextLine();
        while(!tenSachCanTim.matches("^[a-zA-Z0-9\\s]+$")||tenSachCanTim.trim().isEmpty()){
            System.out.println("Lua chon khong phu hop, vui long nhap lai.....");
            tenSachCanTim=input.nextLine();
        }
        boolean found = false;
        String fileName = "book.txt";
        File file = new File(fileName);
        try(Scanner scanner = new Scanner(file)){
            System.out.println("\n+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                                                    THONG TIN SACH                                                                                                                                                                                 |");
            System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
            System.out.printf("| %-50s| %-10s| %-30s| %-9s| %-15s| %-15s| %-10s| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", "Ten Sach", "Ma Sach", "Tac Gia", "So Trang", "The Loai", "NXB", "Gia", "Mo Ta", "Loai Sach","Size","Loai Bia","FSize","Dung Luong","So Luong", "|");
            System.out.println("+---------------------------------------------------+-----------+-------------------------------+----------+----------------+----------------+-----------+----------------------------------------------+----------+-----+----------+-------+-----------+-----------+");
            while(scanner.hasNextLine()){   //từ đây đến else if là để input dữ liệu từ chuỗi thông tin về sách vào các mục tương ứng
                String line=scanner.nextLine();
                String[] sachDuocDoc = line.split("#");
                String tenSach = sachDuocDoc[0];
                String maSach = sachDuocDoc[1];
                String tacGia = sachDuocDoc[2];
                int soTrang = Integer.parseInt(sachDuocDoc[3]);
                String theloai = sachDuocDoc[4];
                String nXB = sachDuocDoc[5];
                double Gia = Double.parseDouble(sachDuocDoc[6]);
                String moTa = sachDuocDoc[7];
                String loaiSach = sachDuocDoc[8];
                String kichThuoc="";
                String loaiBia="";
                String kichCoDungLuong="";
                String dungLuong="";
                String soLuong="";
                if ( loaiSach.equals("Giay")){
                    kichThuoc = sachDuocDoc[9];
                    loaiBia = sachDuocDoc[10];
                    kichCoDungLuong="////";
                    dungLuong="////";
                    soLuong=sachDuocDoc[13];
                }
                else if(loaiSach.equalsIgnoreCase("PDf")||loaiSach.equalsIgnoreCase("EPUB")){
                    kichThuoc="////";
                    loaiBia="////";
                    kichCoDungLuong = sachDuocDoc[11];
                    dungLuong =sachDuocDoc[12];
                    soLuong=sachDuocDoc[13];
                }
                if(tenSach.toLowerCase().contains(tenSachCanTim.toLowerCase())){  //phần này để xét điều kiện để chọn các sách phù hợp yêu cầu để hiển thị
                    System.out.printf("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong,soLuong,"|");
                    System.out.println("+---------------------------------------------------+-----------+-------------------------------+----------+----------------+----------------+-----------+----------------------------------------------+----------+-----+----------+-------+-----------+-----------+");
                    found = true;
                   //break; //ngừng tìm khi sách đã được tìm thấy
                }
                try{
                    Thread.sleep(50);
                    }catch(InterruptedException e){}
            }
            
        }catch(IOException e){
            System.out.println("Khong doc duoc file.....\n" + e.getMessage());
        }
        
        if(!found) //(found) có nghĩa là cách viết khác của found==true, vậy !found tức là false
            System.out.println("Khong tim thay sach.....");
    }
    private void timSachTheoID(){
        Scanner input = new Scanner(System.in);
        System.out.println("Nhap ma sach ban muon tim: ");
        String maSachCanTim = input.nextLine();
        while(!maSachCanTim.matches("^[a-zA-Z0-9\\s]+$")||maSachCanTim.trim().isEmpty()){
            System.out.println("Lua chon khong phu hop, vui long nhap lai.....");
            maSachCanTim=input.nextLine();
        }
        boolean found = false;
        String fileName = "book.txt";
        File file = new File(fileName);
        try(Scanner scanner = new Scanner(file)){
            System.out.println("\n+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                                                    THONG TIN SACH                                                                                                                                                                                 |");
            System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
            System.out.printf("| %-50s| %-10s| %-30s| %-9s| %-15s| %-15s| %-10s| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", "Ten Sach", "Ma Sach", "Tac Gia", "So Trang", "The Loai", "NXB", "Gia", "Mo Ta", "Loai Sach","Size","Loai Bia","FSize","Dung Luong","So Luong", "|");
            System.out.println("+---------------------------------------------------+-----------+-------------------------------+----------+----------------+----------------+-----------+----------------------------------------------+----------+-----+----------+-------+-----------+-----------+");
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] sachDuocDoc = line.split("#");
                String tenSach = sachDuocDoc[0];
                String maSach = sachDuocDoc[1];
                String tacGia = sachDuocDoc[2];
                int soTrang = Integer.parseInt(sachDuocDoc[3]);
                String theloai = sachDuocDoc[4];
                String nXB = sachDuocDoc[5];
                double Gia = Double.parseDouble(sachDuocDoc[6]);
                String moTa = sachDuocDoc[7];
                String loaiSach = sachDuocDoc[8];
                String kichThuoc="";
                String loaiBia="";
                String kichCoDungLuong="";
                String dungLuong="";
                String soLuong="";
                if ( loaiSach.equals("Giay")){
                    kichThuoc = sachDuocDoc[9];
                    loaiBia = sachDuocDoc[10];
                    kichCoDungLuong="////";
                    dungLuong="////";
                    soLuong=sachDuocDoc[13];
                }
                else if(loaiSach.equalsIgnoreCase("PDf")||loaiSach.equalsIgnoreCase("EPUB")){
                    kichThuoc="////";
                    loaiBia="////";
                    kichCoDungLuong = sachDuocDoc[11];
                    dungLuong =sachDuocDoc[12];
                    soLuong=sachDuocDoc[13];
                }
                
                if(maSach.toLowerCase().contains(maSachCanTim.toLowerCase())){
                    System.out.printf("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong,soLuong,"|");
                    System.out.println("+---------------------------------------------------+-----------+-------------------------------+----------+----------------+----------------+-----------+----------------------------------------------+----------+-----+----------+-------+-----------+-----------+");
                    found = true;
                }
                try{
                    Thread.sleep(50);
                    }catch(InterruptedException e){}
                                    
            }
        }catch(IOException e){
            System.out.println("Khong doc duoc file.....\n" + e.getMessage());
        }
        
        if(!found)
            System.out.println("Khong tim thay sach.....");
    }
    
    public Sach timSachTheoID(String id){
        String maSachCanTim = id;
        Sach foundSach=null;
        while(!maSachCanTim.matches("^[a-zA-Z0-9\\s]+$") || maSachCanTim.trim().isEmpty()){
            return foundSach;
        }
        String fileName = "book.txt";
        File file = new File(fileName);
        try(Scanner scanner = new Scanner(file)){
           while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] sachDuocDoc = line.split("#");
                String tenSach = sachDuocDoc[0];
                String maSach = sachDuocDoc[1];
                String tacGia = sachDuocDoc[2];
                int soTrang = Integer.parseInt(sachDuocDoc[3]);
                String theLoai = sachDuocDoc[4];
                String NXB = sachDuocDoc[5];
                double gia = Double.parseDouble(sachDuocDoc[6]);
                String moTa = sachDuocDoc[7];
                String loaiSach = sachDuocDoc[8];
                String kichThuoc="";
                String loaiBia="";
                String kichCoDungLuong="";
                String dungLuong="";
                int soLuong=0;
                if ( loaiSach.equals("Giay")){
                    kichThuoc = sachDuocDoc[9];
                    loaiBia = sachDuocDoc[10];
                    kichCoDungLuong="////";
                    dungLuong="////";
                    soLuong=Integer.parseInt(sachDuocDoc[13]);
                }
                else if(loaiSach.equalsIgnoreCase("PDF")||loaiSach.equalsIgnoreCase("EPUB")){
                    kichThuoc="////";
                    loaiBia="////";
                    kichCoDungLuong = sachDuocDoc[11];
                    dungLuong =sachDuocDoc[12];
                    soLuong=Integer.parseInt(sachDuocDoc[13]);
                }
                if(maSach.toLowerCase().equalsIgnoreCase(maSachCanTim.toLowerCase())){
                    foundSach=new Sach(tenSach,maSach,tacGia,soTrang,theLoai,NXB,gia,moTa,loaiSach,soLuong);
                }
            }
        }catch(IOException e){
            System.out.println("Khong doc duoc file.....\n" + e.getMessage());
        }
        return foundSach;
    }
    
    
    
    private void timSachTheoTG(){
        Scanner input = new Scanner(System.in);
        System.out.println("Nhap ten tac gia ma ban muon tim: ");
        String tgCanTim = input.nextLine();
        while(!tgCanTim.matches("^[a-zA-Z\\s]+$")||tgCanTim.trim().isEmpty()){
            System.out.println("Lua chon khong phu hop, vui long nhap lai.....");
            tgCanTim=input.nextLine();
        }
        boolean found = false;
        String fileName = "book.txt";
        File file = new File(fileName);
        try(Scanner scanner = new Scanner(file)){               
            System.out.println("\n+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                                                    THONG TIN SACH                                                                                                                                                                                 |");
            System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
            System.out.printf("| %-50s| %-10s| %-30s| %-9s| %-15s| %-15s| %-10s| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", "Ten Sach", "Ma Sach", "Tac Gia", "So Trang", "The Loai", "NXB", "Gia", "Mo Ta", "Loai Sach","Size","Loai Bia","FSize","Dung Luong","So Luong", "|");
            System.out.println("+---------------------------------------------------+-----------+-------------------------------+----------+----------------+----------------+-----------+----------------------------------------------+----------+-----+----------+-------+-----------+-----------+");
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] sachDuocDoc = line.split("#");
                String tenSach = sachDuocDoc[0];
                String maSach = sachDuocDoc[1];
                String tacGia = sachDuocDoc[2];
                int soTrang = Integer.parseInt(sachDuocDoc[3]);
                String theloai = sachDuocDoc[4];
                String nXB = sachDuocDoc[5];
                double Gia = Double.parseDouble(sachDuocDoc[6]);
                String moTa = sachDuocDoc[7];
                String loaiSach = sachDuocDoc[8];
                String kichThuoc="";
                String loaiBia="";
                String kichCoDungLuong="";
                String dungLuong="";
                String soLuong="";
                if ( loaiSach.equals("Giay")){
                    kichThuoc = sachDuocDoc[9];
                    loaiBia = sachDuocDoc[10];
                    kichCoDungLuong="////";
                    dungLuong="////";
                    soLuong=sachDuocDoc[13];
                }
                else if(loaiSach.equalsIgnoreCase("PDf")||loaiSach.equalsIgnoreCase("EPUB")){
                    kichThuoc="////";
                    loaiBia="////";
                    kichCoDungLuong = sachDuocDoc[11];
                    dungLuong =sachDuocDoc[12];
                    soLuong=sachDuocDoc[13];
                }
                
                if(tacGia.toLowerCase().contains(tgCanTim.toLowerCase())){
                    System.out.printf("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong,soLuong,"|");
                    System.out.println("+---------------------------------------------------+-----------+-------------------------------+----------+----------------+----------------+-----------+----------------------------------------------+----------+-----+----------+-------+-----------+-----------+");
                    found = true;
                }
                                try{
                    Thread.sleep(50);
                    }catch(InterruptedException e){}
            }
        }catch(IOException e){
            System.out.println("Khong doc duoc file.....\n" + e.getMessage());
        }
        
        if(!found)
            System.out.println("Khong tim thay sach.....");
    }
    private void timSachTheoNXB(){
        Scanner input = new Scanner(System.in);
        System.out.println("Nhap NXB ma ban muon tim: ");
        String nxbCanTim = input.nextLine();
        while(!nxbCanTim.matches("^[a-zA-Z\\s]+$")||nxbCanTim.trim().isEmpty()){
            System.out.println("Lua chon khong phu hop, vui long nhap lai.....");
            nxbCanTim=input.nextLine();
        }
        boolean found = false;
        String fileName = "book.txt";
        File file = new File(fileName);
        try(Scanner scanner = new Scanner(file)){
            System.out.println("\n+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                                                    THONG TIN SACH                                                                                                                                                                                 |");
            System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
            System.out.printf("| %-50s| %-10s| %-30s| %-9s| %-15s| %-15s| %-10s| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", "Ten Sach", "Ma Sach", "Tac Gia", "So Trang", "The Loai", "NXB", "Gia", "Mo Ta", "Loai Sach","Size","Loai Bia","FSize","Dung Luong","So Luong", "|");
            System.out.println("+---------------------------------------------------+-----------+-------------------------------+----------+----------------+----------------+-----------+----------------------------------------------+----------+-----+----------+-------+-----------+-----------+");
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] sachDuocDoc = line.split("#");
                String tenSach = sachDuocDoc[0];
                String maSach = sachDuocDoc[1];
                String tacGia = sachDuocDoc[2];
                int soTrang = Integer.parseInt(sachDuocDoc[3]);
                String theloai = sachDuocDoc[4];
                String nXB = sachDuocDoc[5];
                double Gia = Double.parseDouble(sachDuocDoc[6]);
                String moTa = sachDuocDoc[7];
                String loaiSach = sachDuocDoc[8];
                String kichThuoc="";
                String loaiBia="";
                String kichCoDungLuong="";
                String dungLuong="";
                String soLuong="";
                if ( loaiSach.equals("Giay")){
                    kichThuoc = sachDuocDoc[9];
                    loaiBia = sachDuocDoc[10];
                    kichCoDungLuong="////";
                    dungLuong="////";
                    soLuong=sachDuocDoc[13];
                }
                else if(loaiSach.equalsIgnoreCase("PDf")||loaiSach.equalsIgnoreCase("EPUB")){
                    kichThuoc="////";
                    loaiBia="////";
                    kichCoDungLuong = sachDuocDoc[11];
                    dungLuong =sachDuocDoc[12];
                    soLuong=sachDuocDoc[13];
                }
                
                if(nXB.toLowerCase().contains(nxbCanTim.toLowerCase())){
                    System.out.printf("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong,soLuong,"|");
                    System.out.println("+---------------------------------------------------+-----------+-------------------------------+----------+----------------+----------------+-----------+----------------------------------------------+----------+-----+----------+-------+-----------+-----------+");
                    found = true;
                }
                                try{
                    Thread.sleep(50);
                    }catch(InterruptedException e){}
            }
        }catch(IOException e){
            System.out.println("Khong doc duoc file.....\n" + e.getMessage());
        }
        
        if(!found)
            System.out.println("Khong tim thay sach.....");
    }
    private void timSachTheoKhoangGia(){
        boolean found = false;
        String fileName = "book.txt";
        File file = new File(fileName);
        Scanner input = new Scanner(System.in);
        System.out.println("Nhap gia thap nhat: ");
        Double min = Double.parseDouble(input.nextLine());
        System.out.println("Nhap gia cao nhat: ");
        Double max = Double.parseDouble(input.nextLine());
        System.out.println("\nCac sach tu " + min + " - " + max +": \n" );
        try(Scanner scanner = new Scanner(file)){              
            System.out.println("\n+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                                                    THONG TIN SACH                                                                                                                                                                                 |");
            System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
            System.out.printf("| %-50s| %-10s| %-30s| %-9s| %-15s| %-15s| %-10s| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", "Ten Sach", "Ma Sach", "Tac Gia", "So Trang", "The Loai", "NXB", "Gia", "Mo Ta", "Loai Sach","Size","Loai Bia","FSize","Dung Luong","So Luong", "|");
            System.out.println("+---------------------------------------------------+-----------+-------------------------------+----------+----------------+----------------+-----------+----------------------------------------------+----------+-----+----------+-------+-----------+-----------+");
            while(scanner.hasNextLine()){    
                String line = scanner.nextLine();
                String[] sachDuocDoc = line.split("#");
                String tenSach = sachDuocDoc[0];
                String maSach = sachDuocDoc[1];
                String tacGia = sachDuocDoc[2];
                int soTrang = Integer.parseInt(sachDuocDoc[3]);
                String theloai = sachDuocDoc[4];
                String nXB = sachDuocDoc[5];
                double Gia = Double.parseDouble(sachDuocDoc[6]);
                String moTa = sachDuocDoc[7];
                String loaiSach = sachDuocDoc[8];
                String kichThuoc="";
                String loaiBia="";
                String kichCoDungLuong="";
                String dungLuong="";
                String soLuong="";
                if ( loaiSach.equals("Giay")){
                    kichThuoc = sachDuocDoc[9];
                    loaiBia = sachDuocDoc[10];
                    kichCoDungLuong="////";
                    dungLuong="////";
                    soLuong=sachDuocDoc[13];
                }
                else if(loaiSach.equalsIgnoreCase("PDf")||loaiSach.equalsIgnoreCase("EPUB")){
                    kichThuoc="////";
                    loaiBia="////";
                    kichCoDungLuong = sachDuocDoc[11];
                    dungLuong =sachDuocDoc[12];
                    soLuong=sachDuocDoc[13];
                }
                if(Gia>=min&&Gia<=max){
                    System.out.printf("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong,soLuong,"|");
                    System.out.println("+---------------------------------------------------+-----------+-------------------------------+----------+----------------+----------------+-----------+----------------------------------------------+----------+-----+----------+-------+-----------+-----------+");
                    found = true;
                }
                                try{
                    Thread.sleep(50);
                    }catch(InterruptedException e){}
            }
        }catch(IOException e){
            System.out.println("Khong doc duoc file.....\n" + e.getMessage());
        }
        if(!found)
            System.out.println("\nKhong co sach trong khoang nay...");
    }
    private void timSachTheoTheLoai(){
        Scanner input = new Scanner(System.in);
        System.out.println("Nhap the loai ban muon tim kiem: ");
        String theLoaiCanTim = input.nextLine();
        while(!theLoaiCanTim.matches("^[a-zA-Z\\s]+$")||theLoaiCanTim.trim().isEmpty()){
            System.out.println("Lua chon khong phu hop, vui long nhap lai.....");
            theLoaiCanTim=input.nextLine();
        }
        boolean found = false;
        String fileName = "book.txt";
        File file = new File(fileName);
        try(Scanner scanner = new Scanner(file)){
            System.out.println("\n\n+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                                                    THONG TIN SACH                                                                                                                                                                                 |");
            System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
            System.out.printf("| %-50s| %-10s| %-30s| %-9s| %-15s| %-15s| %-10s| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", "Ten Sach", "Ma Sach", "Tac Gia", "So Trang", "The Loai", "NXB", "Gia", "Mo Ta", "Loai Sach","Size","Loai Bia","FSize","Dung Luong","So Luong", "|");
            System.out.println("+---------------------------------------------------+-----------+-------------------------------+----------+----------------+----------------+-----------+----------------------------------------------+----------+-----+----------+-------+-----------+-----------+");
            while(scanner.hasNextLine()){ 
               String line = scanner.nextLine();
                String[] sachDuocDoc = line.split("#");
                String tenSach = sachDuocDoc[0];
                String maSach = sachDuocDoc[1];
                String tacGia = sachDuocDoc[2];
                int soTrang = Integer.parseInt(sachDuocDoc[3]);
                String theloai = sachDuocDoc[4];
                String nXB = sachDuocDoc[5];
                double Gia = Double.parseDouble(sachDuocDoc[6]);
                String moTa = sachDuocDoc[7];
                String loaiSach = sachDuocDoc[8];
                String kichThuoc="";
                String loaiBia="";
                String kichCoDungLuong="";
                String dungLuong="";
                String soLuong="";
                if ( loaiSach.equals("Giay")){
                    kichThuoc = sachDuocDoc[9];
                    loaiBia = sachDuocDoc[10];
                    kichCoDungLuong="////";
                    dungLuong="////";
                    soLuong=sachDuocDoc[13];
                }
                else if(loaiSach.equalsIgnoreCase("PDf")||loaiSach.equalsIgnoreCase("EPUB")){
                    kichThuoc="////";
                    loaiBia="////";
                    kichCoDungLuong = sachDuocDoc[11];
                    dungLuong =sachDuocDoc[12];
                    soLuong=sachDuocDoc[13];
                }
                
                if(theloai.toLowerCase().contains(theLoaiCanTim.toLowerCase())){
                    System.out.printf("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong,soLuong,"|");
                    System.out.println("+---------------------------------------------------+-----------+-------------------------------+----------+----------------+----------------+-----------+----------------------------------------------+----------+-----+----------+-------+-----------+-----------+");
                    found = true;
                }
                                try{
                    Thread.sleep(50);
                    }catch(InterruptedException e){}
            }
        }catch(IOException e){
            System.out.println("Khong doc duoc file.....\n" + e.getMessage());
        }
        if(!found)
            System.out.println("Khong tim thay sach.....");
    }
    
    private List<String> tim2DieuKien(String dk1, String dk2){
        List<String> timthay1 = new ArrayList<>();
        File file = new File("book.txt");
        boolean found = false;
        try(Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){ 
               String line = scanner.nextLine();
                String[] sachDuocDoc2 = line.split("#");
                String tenSach = sachDuocDoc2[0];
                String maSach = sachDuocDoc2[1];
                String tacGia = sachDuocDoc2[2];
                int soTrang = Integer.parseInt(sachDuocDoc2[3]);
                String theloai = sachDuocDoc2[4];
                String nXB = sachDuocDoc2[5];
                double Gia = Double.parseDouble(sachDuocDoc2[6]);
                String moTa = sachDuocDoc2[7];
                String loaiSach = sachDuocDoc2[8];
                String kichThuoc="";
                String loaiBia="";
                String kichCoDungLuong="";
                String dungLuong="";
                String soLuong="";
                if ( loaiSach.equals("Giay")){
                    kichThuoc = sachDuocDoc2[9];
                    loaiBia = sachDuocDoc2[10];
                    kichCoDungLuong="////";
                    dungLuong="////";
                    soLuong=sachDuocDoc2[13];
                }
                else if(loaiSach.equalsIgnoreCase("PDf")||loaiSach.equalsIgnoreCase("EPUB")){
                    kichThuoc="////";
                    loaiBia="////";
                    kichCoDungLuong = sachDuocDoc2[11];
                    dungLuong =sachDuocDoc2[12];
                    soLuong=sachDuocDoc2[13];
                }
                
                //String + string
                //tenSach + something
                if(tenSach.toLowerCase().contains(dk1.toLowerCase()) && tacGia.toLowerCase().contains(dk2.toLowerCase()) || tacGia.toLowerCase().contains(dk1.toLowerCase()) && tenSach.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                else if(tenSach.toLowerCase().contains(dk1.toLowerCase()) && maSach.toLowerCase().contains(dk2.toLowerCase()) || maSach.toLowerCase().contains(dk1.toLowerCase()) && tenSach.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                else if(tenSach.toLowerCase().contains(dk1.toLowerCase()) && theloai.toLowerCase().contains(dk2.toLowerCase()) || theloai.toLowerCase().contains(dk1.toLowerCase()) && tenSach.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                else if(tenSach.toLowerCase().contains(dk1.toLowerCase()) && nXB.toLowerCase().contains(dk2.toLowerCase()) || nXB.toLowerCase().contains(dk1.toLowerCase()) && tenSach.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                else if(tenSach.toLowerCase().contains(dk1.toLowerCase()) && loaiSach.toLowerCase().contains(dk2.toLowerCase()) || loaiSach.toLowerCase().contains(dk1.toLowerCase()) && tenSach.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                else if(tenSach.toLowerCase().contains(dk1.toLowerCase()) && loaiBia.toLowerCase().contains(dk2.toLowerCase()) || loaiBia.toLowerCase().contains(dk1.toLowerCase()) && tenSach.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                else if(tenSach.toLowerCase().contains(dk1.toLowerCase()) && kichThuoc.toLowerCase().contains(dk2.toLowerCase()) || kichThuoc.toLowerCase().contains(dk1.toLowerCase()) && tenSach.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                else if(tenSach.toLowerCase().contains(dk1.toLowerCase()) && kichCoDungLuong.toLowerCase().contains(dk2.toLowerCase()) || kichCoDungLuong.toLowerCase().contains(dk1.toLowerCase()) && tenSach.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                //maSach + something
                else if(maSach.toLowerCase().contains(dk1.toLowerCase()) && tacGia.toLowerCase().contains(dk2.toLowerCase()) || tacGia.toLowerCase().contains(dk1.toLowerCase()) && maSach.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                else if(maSach.toLowerCase().contains(dk1.toLowerCase()) && theloai.toLowerCase().contains(dk2.toLowerCase()) || theloai.toLowerCase().contains(dk1.toLowerCase()) && maSach.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                else if(maSach.toLowerCase().contains(dk1.toLowerCase()) && nXB.toLowerCase().contains(dk2.toLowerCase()) || nXB.toLowerCase().contains(dk1.toLowerCase()) && maSach.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                else if(maSach.toLowerCase().contains(dk1.toLowerCase()) && loaiSach.toLowerCase().contains(dk2.toLowerCase()) || loaiSach.toLowerCase().contains(dk1.toLowerCase()) && maSach.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                else if(maSach.toLowerCase().contains(dk1.toLowerCase()) && kichThuoc.toLowerCase().contains(dk2.toLowerCase()) || kichThuoc.toLowerCase().contains(dk1.toLowerCase()) && maSach.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                else if(maSach.toLowerCase().contains(dk1.toLowerCase()) && loaiBia.toLowerCase().contains(dk2.toLowerCase()) || loaiBia.toLowerCase().contains(dk1.toLowerCase()) && maSach.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                else if(maSach.toLowerCase().contains(dk1.toLowerCase()) && kichCoDungLuong.toLowerCase().contains(dk2.toLowerCase()) || kichCoDungLuong.toLowerCase().contains(dk1.toLowerCase()) && maSach.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                //tacgia + something
                else if(tacGia.toLowerCase().contains(dk1.toLowerCase()) && theloai.toLowerCase().contains(dk2.toLowerCase()) || theloai.toLowerCase().contains(dk1.toLowerCase()) && tacGia.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                else if(tacGia.toLowerCase().contains(dk1.toLowerCase()) && nXB.toLowerCase().contains(dk2.toLowerCase()) || nXB.toLowerCase().contains(dk1.toLowerCase()) && tacGia.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                else if(tacGia.toLowerCase().contains(dk1.toLowerCase()) && loaiSach.toLowerCase().contains(dk2.toLowerCase()) || loaiSach.toLowerCase().contains(dk1.toLowerCase()) && tacGia.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                else if(tacGia.toLowerCase().contains(dk1.toLowerCase()) && kichThuoc.toLowerCase().contains(dk2.toLowerCase()) || kichThuoc.toLowerCase().contains(dk1.toLowerCase()) && tacGia.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                else if(tacGia.toLowerCase().contains(dk1.toLowerCase()) && loaiBia.toLowerCase().contains(dk2.toLowerCase()) || loaiBia.toLowerCase().contains(dk1.toLowerCase()) && tacGia.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                else if(tacGia.toLowerCase().contains(dk1.toLowerCase()) && kichCoDungLuong.toLowerCase().contains(dk2.toLowerCase()) || kichCoDungLuong.toLowerCase().contains(dk1.toLowerCase()) && tacGia.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                //theloai + something
                else if(theloai.toLowerCase().contains(dk1.toLowerCase()) && nXB.toLowerCase().contains(dk2.toLowerCase()) || nXB.toLowerCase().contains(dk1.toLowerCase()) && theloai.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                else if(theloai.toLowerCase().contains(dk1.toLowerCase()) && loaiSach.toLowerCase().contains(dk2.toLowerCase()) || loaiSach.toLowerCase().contains(dk1.toLowerCase()) && theloai.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                else if(theloai.toLowerCase().contains(dk1.toLowerCase()) && kichThuoc.toLowerCase().contains(dk2.toLowerCase()) || kichThuoc.toLowerCase().contains(dk1.toLowerCase()) && theloai.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                else if(theloai.toLowerCase().contains(dk1.toLowerCase()) && loaiBia.toLowerCase().contains(dk2.toLowerCase()) || loaiBia.toLowerCase().contains(dk1.toLowerCase()) && theloai.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                else if(theloai.toLowerCase().contains(dk1.toLowerCase()) && kichCoDungLuong.toLowerCase().contains(dk2.toLowerCase()) || kichCoDungLuong.toLowerCase().contains(dk1.toLowerCase()) && theloai.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                //nxb + something
                else if(nXB.toLowerCase().contains(dk1.toLowerCase()) && loaiSach.toLowerCase().contains(dk2.toLowerCase()) || loaiSach.toLowerCase().contains(dk1.toLowerCase()) && nXB.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                else if(nXB.toLowerCase().contains(dk1.toLowerCase()) && kichThuoc.toLowerCase().contains(dk2.toLowerCase()) || kichThuoc.toLowerCase().contains(dk1.toLowerCase()) && nXB.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                else if(nXB.toLowerCase().contains(dk1.toLowerCase()) && loaiBia.toLowerCase().contains(dk2.toLowerCase()) || loaiBia.toLowerCase().contains(dk1.toLowerCase()) && nXB.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                else if(nXB.toLowerCase().contains(dk1.toLowerCase()) && kichCoDungLuong.toLowerCase().contains(dk2.toLowerCase()) || kichCoDungLuong.toLowerCase().contains(dk1.toLowerCase()) && nXB.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                //loaisach + something
                else if(loaiSach.toLowerCase().contains(dk1.toLowerCase()) && kichThuoc.toLowerCase().contains(dk2.toLowerCase()) || kichThuoc.toLowerCase().contains(dk1.toLowerCase()) && loaiSach.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                else if(loaiSach.toLowerCase().contains(dk1.toLowerCase()) && loaiBia.toLowerCase().contains(dk2.toLowerCase()) || loaiBia.toLowerCase().contains(dk1.toLowerCase()) && loaiSach.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                else if(loaiSach.toLowerCase().contains(dk1.toLowerCase()) && kichCoDungLuong.toLowerCase().contains(dk2.toLowerCase()) || kichCoDungLuong.toLowerCase().contains(dk1.toLowerCase()) && loaiSach.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                //kichthuoc + something
                else if(kichThuoc.toLowerCase().contains(dk1.toLowerCase()) && loaiBia.toLowerCase().contains(dk2.toLowerCase()) || loaiBia.toLowerCase().contains(dk1.toLowerCase()) && kichThuoc.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                else if(kichThuoc.toLowerCase().contains(dk1.toLowerCase()) && kichCoDungLuong.toLowerCase().contains(dk2.toLowerCase()) || kichCoDungLuong.toLowerCase().contains(dk1.toLowerCase()) && kichThuoc.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
                //loaibia + something
                else if(loaiBia.toLowerCase().contains(dk1.toLowerCase()) && kichCoDungLuong.toLowerCase().contains(dk2.toLowerCase()) || kichCoDungLuong.toLowerCase().contains(dk1.toLowerCase()) && loaiBia.toLowerCase().contains(dk2.toLowerCase())){
                    String bookInfo = String.format("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong, soLuong, "|");
                    timthay1.add(bookInfo);
                    found = true;
                }
            }
        }catch(IOException e){
            System.out.println("Khong doc duoc file.....\n" + e.getMessage());
        }
        if(!found)
            System.out.println("Khong tim thay sach.....");
        else if(found){
            System.out.println("\nCác sách tìm thấy:");
            System.out.println("+---------------------------------------------------+-----------+-------------------------------+----------+----------------+----------------+-----------+----------------------------------------------+----------+-----+----------+-------+-----------+-----------+");
            System.out.printf("| %-50s| %-10s| %-30s| %-9s| %-15s| %-15s| %-10s| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", "Ten Sach", "Ma Sach", "Tac Gia", "So Trang", "The Loai", "NXB", "Gia", "Mo Ta", "Loai Sach","Size","Loai Bia","FSize","Dung Luong","So Luong", "|");
            System.out.println("+---------------------------------------------------+-----------+-------------------------------+----------+----------------+----------------+-----------+----------------------------------------------+----------+-----+----------+-------+-----------+-----------+");
            for (String book : timthay1) {
                
                System.out.print(book);
                System.out.println("+---------------------------------------------------+-----------+-------------------------------+----------+----------------+----------------+-----------+----------------------------------------------+----------+-----+----------+-------+-----------+-----------+");
            }
        }
        
        return timthay1;
    }
    
    private void timNhieuDieuKien(){
        Scanner input = new Scanner(System.in);
        System.out.println("Số lượng điều kiện tìm kiếm(2): ");
        System.out.println("Điều kiện 1: ");
        String dk1 = input.nextLine();
        System.out.println("Điều kiện 2: ");
        String dk2 = input.nextLine();
        if(dk1 instanceof String && dk2 instanceof String)
           tim2DieuKien(dk1, dk2);


    }
        
    
    public void timSach(){
        String code;
        Scanner input = new Scanner(System.in);
        
        System.out.println("\n");
        System.out.println("\t+---------- Query Options -----------------+");
        System.out.println("\t|     1. Tim sach theo Ten                 |");
        System.out.println("\t|     2. Tim sach theo Ma Sach             |");
        System.out.println("\t|     3. Tim sach theo Tac Gia             |");
        System.out.println("\t|     4. Tim sach theo NXB                 |");
        System.out.println("\t|     5. Tim sach theo Gia                 |");
        System.out.println("\t|     6. Tim sach theo The Loai            |");
        System.out.println("\t|     7. Tim sach theo nhieu dieu kien     |");
        System.out.println("\t+------------------------------------------+");
        System.out.print("\n Moi ban nhap:  ");
        code = input.nextLine();
        while(!code.equals("1")&&!code.equals("2")&&!code.equals("3")&&!code.equals("4")&&!code.equals("5")&&!code.equals("6")&&!code.equals("7")){
            System.out.println("Lua chon khong hop le, vui long nhap lai.....");
            code=input.nextLine();
        }
        switch(code){
            case "1":
                System.out.println("\n\n\t\t\tTIM SACH THEO TEN");
                timSachTheoTen();
                break;
            case "2":
                System.out.println("\n\n\t\t\tTIM SACH THEO MA SACH");
                timSachTheoID();
                break;
            case "3":
                System.out.println("\n\n\t\t\tTIM SACH THEO TAC GIA");
                timSachTheoTG();
                break;
            case "4":
                System.out.println("\n\n\t\t\tTIM SACH THEO NXB");
                timSachTheoNXB();
                break;
            case "5":
                System.out.println("\n\n\t\t\tTIM SACH THEO KHOANG GIA");
                timSachTheoKhoangGia();
                break;
            case "6":
                System.out.println("\n\n\t\t\tTIM SACH THEO THE LOAI");
                timSachTheoTheLoai();
                break;
            case "7":
                System.out.println("\n\n\t\t\tTIM SACH THEO NHIEU DIEU KIEN");
                timNhieuDieuKien();
                break;
        }
        System.out.println("Ban co muon tiep tuc tim kiem? (Y/N)");
        String opt=input.nextLine();
        while(!opt.equalsIgnoreCase("N")&&!opt.equalsIgnoreCase("Y")&&!opt.equalsIgnoreCase("No")&&!opt.equalsIgnoreCase("Yes")){
                        System.out.println("Lua chon khong phu hop, vui long nhap lai.....");
                        opt = input.nextLine();
                    }
        if(opt.equalsIgnoreCase("Y")||opt.equalsIgnoreCase("Yes"))
            timSach();
    }

    
    public void hienThiSachDangKinhDoanh() { 
        File file= new File("book.txt");
        try (Scanner scanner = new Scanner(file)) 
        {
            System.out.println("\n\n+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                                                    BOOK  DATABASE                                                                                                                                                                                 |");
            System.out.println("|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|");
            System.out.printf("| %-50s| %-10s| %-30s| %-9s| %-15s| %-15s| %-10s| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", "Ten Sach", "Ma Sach", "Tac Gia", "So Trang", "The Loai", "NXB", "Gia", "Mo Ta", "Loai Sach","Size","Loai Bia","FSize","Dung Luong","So Luong", "|");
            System.out.println("+---------------------------------------------------+-----------+-------------------------------+----------+----------------+----------------+-----------+----------------------------------------------+----------+-----+----------+-------+-----------+-----------+");
            while(scanner.hasNextLine()){
                sachDaHienThi++;
                String line = scanner.nextLine();
                String[] sachDuocDoc = line.split("#");
                String tenSach = sachDuocDoc[0];
                String maSach = sachDuocDoc[1];
                String tacGia = sachDuocDoc[2];
                int soTrang = Integer.parseInt(sachDuocDoc[3]);
                String theloai = sachDuocDoc[4];
                String nXB = sachDuocDoc[5];
                double Gia = Double.parseDouble(sachDuocDoc[6]);
                String moTa = sachDuocDoc[7];
                String loaiSach = sachDuocDoc[8];
                String kichThuoc="";
                String loaiBia="";
                String kichCoDungLuong="";
                String dungLuong="";
                String soLuong="";
                if ( loaiSach.equals("Giay")){
                    kichThuoc = sachDuocDoc[9];
                    loaiBia = sachDuocDoc[10];
                    kichCoDungLuong="////";
                    dungLuong="////";
                    soLuong=sachDuocDoc[13];
                }
                else if(loaiSach.equalsIgnoreCase("PDf")||loaiSach.equalsIgnoreCase("EPUB")){
                    kichThuoc="////";
                    loaiBia="////";
                    kichCoDungLuong = sachDuocDoc[11];
                    dungLuong =sachDuocDoc[12];
                    soLuong=sachDuocDoc[13];
                }
                System.out.printf("| %-50s| %-10s| %-30s| %-9d| %-15s| %-15s| %-10.3f| %-45s| %-9s| %-4s| %-9s| %-6s| %-10s| %-10s| %n", tenSach, maSach, tacGia, soTrang, theloai, nXB, Gia, moTa, loaiSach, kichThuoc, loaiBia, kichCoDungLuong, dungLuong,soLuong,"|");
                System.out.println("+---------------------------------------------------+-----------+-------------------------------+----------+----------------+----------------+-----------+----------------------------------------------+----------+-----+----------+-------+-----------+-----------+");
                try {
				Thread.sleep(200);
                            } 
                            catch (InterruptedException e) {
				}

                if(sachDaHienThi%10==0){
                    System.out.println("Ban co muon tiep tuc hien thi? (Y/N)");
                    Scanner input = new Scanner(System.in);
                    String opt = input.nextLine();
                    
                    while(!opt.equalsIgnoreCase("N")&&!opt.equalsIgnoreCase("Y")&&!opt.equalsIgnoreCase("No")&&!opt.equalsIgnoreCase("Yes")){
                        System.out.println("Lua chon khong phu hop, vui long nhap lai.....");
                        opt = input.nextLine();
                    }
                    if(opt.equalsIgnoreCase("N")||opt.equalsIgnoreCase("No")){
                        sachDaHienThi=0;
                        break;
                    }
                    System.out.println("+---------------------------------------------------+----------+-------------------------------+----------+----------------+----------------+-----------+---------------------------------------------------+----------+-----+----------+-----+----------+----------+");

                }
            }
        }catch (FileNotFoundException e) {
        System.out.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
    
    
    private void suaTenSach(){
        String fileName = "book.txt";
        File file = new File(fileName);
        Scanner input = new Scanner(System.in);

        try (Scanner scanner = new Scanner(file)) {
            System.out.print("Nhập mã sách bạn muốn chỉnh sửa: ");
            String maSachCanSua = input.nextLine();

            while (!maSachCanSua.matches("^[a-zA-Z0-9\\s]+$") || maSachCanSua.trim().isEmpty()) {
                System.out.println("Lựa chọn không phù hợp, vui lòng nhập lại.....");
                maSachCanSua = input.nextLine();
            }

            //Tạo 1 danh sách tạm chứa tt các sách modified
            List<String> modifiedBookInfo = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] sachDuocDoc = line.split("#");
                String maSach = sachDuocDoc[1];

                if (maSach.equalsIgnoreCase(maSachCanSua)) {
                    //Tìm thấy sách trùng khớp, cho user chỉnh sửa
                    System.out.println("Nhập tên sách mới: ");
                    String newBookName = input.nextLine();
                    sachDuocDoc[0] = newBookName; // Cập nhật tên sách

                    // Chuyển cái array lại thành 1 line và add nó vào midifed list
                    String modifiedLine = String.join("#", sachDuocDoc);
                    modifiedBookInfo.add(modifiedLine);
                } else {
                    modifiedBookInfo.add(line); // Giữ nhứng sách không chỉnh sửa trong list
                }
            }

            // Viết lại thông tin sách được chỉnh sửa
            try (FileWriter writer = new FileWriter(fileName)) {
                for (String modifiedLine : modifiedBookInfo) {
                    writer.write(modifiedLine + "\n");
                }
                System.out.println("Cập nhật thông tin sách thành công.");
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found....." + e.getMessage());
        }
    }
    private void suaGiaSach(){
        String fileName = "book.txt";
        File file = new File(fileName);
        Scanner input = new Scanner(System.in);

        try (Scanner scanner = new Scanner(file)) {
            System.out.print("Nhập mã sách bạn muốn chỉnh sửa: ");
            String maSachCanSua = input.nextLine();

            while (!maSachCanSua.matches("^[a-zA-Z0-9\\s]+$") || maSachCanSua.trim().isEmpty()) {
                System.out.println("Lựa chọn không phù hợp, vui lòng nhập lại.....");
                maSachCanSua = input.nextLine();
            }

            //Tạo 1 danh sách tạm chứa tt các sách modified
            List<String> modifiedBookInfo = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] sachDuocDoc = line.split("#");
                String maSach = sachDuocDoc[1];

                if (maSach.equalsIgnoreCase(maSachCanSua)) {
                    //Tìm thấy sách trùng khớp, cho user chỉnh sửa
                    System.out.println("Nhập giá sách mới: ");
                    String newBookPrice = input.nextLine();
                    sachDuocDoc[6] = newBookPrice; // Cập nhật tên sách

                    // Chuyển cái array lại thành 1 line và add nó vào midifed list
                    String modifiedLine = String.join("#", sachDuocDoc);
                    modifiedBookInfo.add(modifiedLine);
                } else {
                    modifiedBookInfo.add(line); // Giữ nhứng sách không chỉnh sửa trong list
                }
            }

            // Viết lại thông tin sách được chỉnh sửa
            try (FileWriter writer = new FileWriter(fileName)) {
                for (String modifiedLine : modifiedBookInfo) {
                    writer.write(modifiedLine + "\n"); // Write each modified line to the file
                }
                System.out.println("Cập nhật thông tin sách thành công.");
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found....." + e.getMessage());
        }
    }
    private void suaSoLuongSach(){
        String fileName = "book.txt";
        File file = new File(fileName);
        Scanner input = new Scanner(System.in);

        try (Scanner scanner = new Scanner(file)) {
            System.out.print("Nhập mã sách bạn muốn chỉnh sửa: ");
            String maSachCanSua = input.nextLine();

            while (!maSachCanSua.matches("^[a-zA-Z0-9\\s]+$") || maSachCanSua.trim().isEmpty()) {
                System.out.println("Lựa chọn không phù hợp, vui lòng nhập lại.....");
                maSachCanSua = input.nextLine();
            }

            //Tạo 1 danh sách tạm chứa tt các sách modified
            List<String> modifiedBookInfo = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] sachDuocDoc = line.split("#");
                String maSach = sachDuocDoc[1];

                if (maSach.equalsIgnoreCase(maSachCanSua)) {
                    //Tìm thấy sách trùng khớp, cho user chỉnh sửa
                    System.out.println("Nhập số lượng sách mới: ");
                    String newBookQuantity = input.nextLine();
                    sachDuocDoc[13] = newBookQuantity; // Cập nhật tên sách

                    // Chuyển cái array lại thành 1 line và add nó vào midifed list
                    String modifiedLine = String.join("#", sachDuocDoc);
                    modifiedBookInfo.add(modifiedLine);
                } else {
                    modifiedBookInfo.add(line); // Giữ nhứng sách không chỉnh sửa trong list
                }
            }

            // Viết lại thông tin sách được chỉnh sửa
            try (FileWriter writer = new FileWriter(fileName)) {
                for (String modifiedLine : modifiedBookInfo) {
                    writer.write(modifiedLine + "\n"); // Write each modified line to the file
                }
                System.out.println("Cập nhật thông tin sách thành công.");
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found....." + e.getMessage());
        }
    }
    public void chinhSuaThongTinSach(){
        String code;
        Scanner input = new Scanner(System.in);
        
        System.out.println("\t+---------- Query Options ----------+");
        System.out.println("\t|     1. Sửa Tên Sách               |");
        System.out.println("\t|     2. Sửa Giá Sách               |");
        System.out.println("\t|     3. Sửa Số Lượng Sách          |");
        System.out.println("\t+-----------------------------------+");
        System.out.print("Moi ban nhap:  ");
        code = input.nextLine();
        while(!code.equals("1")&&!code.equals("2")&&!code.equals("3")&&!code.equals("4")&&!code.equals("5")&&!code.equals("6")){
            System.out.println("Lua chon khong hop le, vui long nhap lai.....");
            code=input.nextLine();
        }
        switch(code){
            case "1":
                System.out.println("\n-------------SỬA TÊN SÁCH-------------");
                suaTenSach();
                break;
            case "2":
                System.out.println("\n-------------SỬA GIÁ SÁCH-------------");
                suaGiaSach();
                break;
            case "3":
                System.out.println("\n-------------SỬA SỐ LƯỢNG SÁCH-------------");
                suaSoLuongSach();
                break;
            
        }
        System.out.println("Có muốn tiếp tục chỉnh sửa ? (Y/N)");
        String opt=input.nextLine();
        while(!opt.equalsIgnoreCase("N")&&!opt.equalsIgnoreCase("Y")&&!opt.equalsIgnoreCase("No")&&!opt.equalsIgnoreCase("Yes")){
                        System.out.println("Lựa chọn không phù hợp, vui lòng nhập lại.....");
                        opt = input.nextLine();
                    }
        if(opt.equalsIgnoreCase("Y")||opt.equalsIgnoreCase("Yes"))
            chinhSuaThongTinSach();
    }
    
}