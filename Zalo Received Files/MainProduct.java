package Product;

import java.util.Scanner;

public class MainProduct {
    private static Product[] products = new Product[100];
    static  {
        products[0] = new Product("P001", "Sản phẩm 1", 100, 150, 50, 10, "Mô tả sản phẩm 1", true);
        products[1] = new Product("P002", "Sản phẩm 2", 120, 180, 60, 15, "Mô tả sản phẩm 2", true);
        products[2] = new Product("P003", "Sản phẩm 3", 150, 200, 50, 20, "Mô tả sản phẩm 3", false);
    }
    private static int length = 3;
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        do {
        System.out.println(
                "1. Nhập thông tin cho n sản phẩm (n nhập từ bàn phím) \n" +

                "2. Hiển thị thông tin các sản phẩm \n" +

                "3. Tính lợi nhuận các sản phẩm \n" +

                "4. Sắp xếp các sản phẩm theo lợi nhuận giảm dần \n" +

                "5. Thống kê các sản phẩm theo khoảng giá bán \n"+

                "6. Tìm các sản phẩm theo tên sản phẩm \n"+

                "7. Nhập sản phẩm \n"+

                "8. Bán sản phẩm  \n"+

                "9. Cập nhật trạng thái sản phẩm theo mã SP\n"+

                "10. Xóa theo mã SP\n"+

                "0. Thoát ");
            System.out.println("Moi lua chon");
            int choice =  Integer.parseInt(scanner.nextLine());
            switch (choice) {
            case 1:
                creatProduct();
                break;
            case 2:
                showAllProduct();
                break;
            case 3:
                calculateProfit();
                break;
            case 4:
                selectionSortProfit();
                break;
            case 5:
                exportPriceRange();
                break;
            case 6:
                searchProductName();
                break;
            case 7:
                inputProduct();
                break;
            case 8:
                sellProduct();
                break;
            case 9:
                updateStatusProduct();
                break;
            case 10:
                deleteProduct();
                break;
            }
        }while (true);

    }
    public static void creatProduct(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Muốn nhập thêm mấy sản phẩm :");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            boolean checkID = false;
            do {
                System.out.println("Nhập mã sản phẩm (độ dài 4 ký tự, bắt đầu bằng 'P'): ");
                String inputProductId = scanner.nextLine();
                if (inputProductId.length() == 4 && inputProductId.charAt(0) == 'P') {
                    boolean checkNewID = false;
                    for (int j = 0; j < length; j++) {
                        if (products[j].getProductID().equals(inputProductId)) {
                            checkNewID = true;
                            break;
                        }
                    }
                    if (!checkNewID) {
                        checkID = true;
                        products[length] = new Product();
                        products[length].inputData(scanner);
                        products[length].setProductID(inputProductId);
                        length++;
                    }else {
                        System.out.println("Mã sản phẩm đã tồn tại. Vui lòng nhập lại.");
                        }
                } else {
                    System.out.println("Mã sản phẩm không hợp lệ. Vui lòng nhập lại.");
                }
            } while (!checkID);
        }
    }

    public static void showAllProduct(){
        for (int i = 0; i < length; i++) {
            products[i].displayData();
        }
    }

    public static void calculateProfit(){
        System.out.println("Lợi nhuận của các sản phẩm:");
        for (int i = 0; i < length; i++) {
                products[i].calProfit();
            System.out.println("Sản phẩm " + products[i].getProductID() + ": " + products[i].getProfit());
        }
    }

    public static void selectionSortProfit(){
        for (int i = 0; i < length; i++) {
           int maxIndex = i;
            for (int j = i+1; j < length; j++) {
                if (products[j].getProfit()>products[i].getProfit()){
                    maxIndex=j;
                }
            }
            Product temp = products[i];
            products[i]=products[maxIndex];
            products[maxIndex] = temp;
        }
        System.out.println("Đã sắp xếp sản phẩm theo profit giảm dần.");
    }

    public static void exportPriceRange(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập giá thấp nhất trong khoảng giá bán");
        float fromExportPrice = Float.parseFloat(scanner.nextLine());
        System.out.println("Nhập giá cao nhất trong khoảng giá bán");
        float toExportPrice = Float.parseFloat(scanner.nextLine());
        System.out.println("Sản phẩm trong khoảng giá bán từ "+fromExportPrice+" đến "+toExportPrice+" là:");
        boolean checkExportPrice = false;
        for (int i = 0; i < length; i++) {
            if (products[i].getExportPrice() >= fromExportPrice && products[i].getImportPrice() <= toExportPrice){
                products[i].displayData();
                checkExportPrice = true;
            }
        }
        if (!checkExportPrice){
            System.out.println("Không có sản phẩm trong khoảng giá bán từ "+fromExportPrice+" đến "+toExportPrice);
        }
    }

    public static void searchProductName (){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Mời bạn nhập tên sản phầm cần tìm:");
        String inputName = scanner.nextLine();
        boolean checkName = false;
        for (int i = 0; i < length; i++) {
            if (products[i].getProductName().equals(inputName)){
                    products[i].displayData();
                    checkName= true;
            }
        }if (!checkName){
                System.out.println("Không co sản phẩm với tên cần tìm");
        }
    }

    public static void inputProduct(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập mã sản phầm :");
        String inputProductID = scanner.nextLine();
        boolean check = false;
        for (int i = 0; i < length; i++) {
            if (products[i].getProductID().equals(inputProductID)){
                check = true;
                System.out.println("Nhập số lượng cần them:");
                int addQuantity = Integer.parseInt(scanner.nextLine());
                products[i].setQuantity(products[i].getQuantity() + addQuantity);
                System.out.println("Số lượng sản phẩm đã được cập nhật thành công.");
                break;
            }
        }
        if (!check) {
            System.out.println("Không tìm thấy sản phẩm với mã sản phẩm đã nhập.");
        }
    }

    public static void sellProduct(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên sản phầm :");
        String inputProductName = scanner.nextLine();
        boolean check = false;
        for (int i = 0; i < length; i++) {
            if (products[i].getProductName().equals(inputProductName)) {
                check = true;
                if (products[i].getStatus()) {
                    System.out.println("Nhập số lượng sản phẩm cần bán:");
                    int soldQuantity = Integer.parseInt(scanner.nextLine());
                    if (soldQuantity <= products[i].getQuantity()) {
                        products[i].setQuantity(products[i].getQuantity() - soldQuantity);
                        System.out.println("Sản phẩm dã bán thành công");
                    } else {
                        System.out.println("Số lượng sp bán vuot qua số lượng sp hiện có");
                    }
                } else {
                    System.out.println("Sản phẩm không còn bán");
                }
            }
        }
        if (!check){
            System.out.println("Không có tên sản phẩm ");
        }
    }

    public static void updateStatusProduct(){
        boolean checkID = false;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Nhập mã sản phẩm (độ dài 4 ký tự, bắt đầu bằng 'P'): ");
            String inputProductId = scanner.nextLine();
            if (inputProductId.length() == 4 && inputProductId.charAt(0) == 'P') {
                checkID = true;
                boolean check = false;
                for (int i = 0; i < length; i++) {
                    if (products[i].getProductID().equals(inputProductId)){
                        check =true;
                        if (products[i].getStatus()) {
                                products[i].setStatus(false);
                            System.out.println("Đã cập nhật trạng thái mã ("+inputProductId+ ") chuyển sang : Chưa bán");
                        } else {
                            products[i].setStatus(true);
                            System.out.println("Đã cập nhật trạng thái mã ("+inputProductId+ ") chuyển sang : Đang bán");
                        }
                        break;
                    }
                }
                if (!check){
                    System.out.println("Mã sản phẩm không có ");
                }
            } else {
                System.out.println("Mã sản phẩm không hợp lệ. Vui lòng nhập lại.");
            }
        }while (!checkID);
    }

    public static void deleteProduct(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập mã sản phẩm cần xóa:");
        String delProductId = scanner.nextLine();
        boolean check = false;
        for (int i = 0; i < length; i++) {
            if (products[i].getProductID().equals(delProductId)) {
                check = true;
                for (int j = i; j < length - 1; j++) {
                    products[j] = products[j + 1];
                }
                length--;
                System.out.println("Đã xóa sản phẩm có mã: " + delProductId);
                break;
            }
        }
        if (!check) {
            System.out.println("Không tìm thấy sản phẩm có mã: " + delProductId);
        }
    }


}
