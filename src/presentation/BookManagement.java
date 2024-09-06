package presentation;

import business.features.imp.CatalogFeatureImpl;

import java.awt.*;
import java.util.Scanner;

public class BookManagement {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        do
        {
            System.out.println( """
					  				======================= MENU =======================
					  							1. Quản lý catalog
					  							2. Quản lý sản phẩm
					  							3. Quản lý người dùng
					  							4. Thoát
					  				====================================================
					  				Lựa chọn đê:
					  """);
            int choice;
            while (true){
                try {
                    System.out.println("Mời bạn lựa chọn Menu");
                    choice = Integer.parseInt(sc.nextLine());
                    if (choice <= 0){
                        System.err.println("Bạn phải nhập số lớn hơn không");
                    }
                    break;
                }catch (NumberFormatException e){
                    System.err.println("Không phải số nguyên");
                }
            }
            switch (choice)
            {
                case 1:
                {
                    CatalogManagement catalogManagement = new CatalogManagement();
                    catalogManagement.menuCatalog(sc);
                    break;
                }
                case 2:
                {   ProductManagement productManagement = new ProductManagement();
                    productManagement.menuProduct(sc);
                    break;
                }
                case 3:
                {   UserManagement userManagement = new UserManagement();
                    userManagement.menuUser(sc);
                    break;
                }
                case 4:
                {
                    System.exit(0);
                    break;
                }
                default:
                    System.err.println("Nhập lại từ 1 -> 3 đê: ");
            }
        }
        while (true);
    }
}
