package presentation;

import business.entity.Product;
import business.features.ICartFeature;
import business.features.IProducFeature;
import business.features.imp.CartFeatureImpl;
import business.features.imp.CatalogFeatureImpl;
import business.features.imp.ProductFeatureImpl;

import java.util.Scanner;

public class ProductManagement {
    IProducFeature producFeature = new ProductFeatureImpl();
    public void menuProduct(Scanner sc) {
        boolean isLoop = true;
        do
        {
            System.out.println("""
							======================= MENU =======================
							1. Nhập số sản phẩm và nhập thông tin sản phẩm
							2. Hiển thị thông tin sản phẩm
							3. Sắp xếp sản phẩm theo giá theo thứ tự giảm dần
							4. Xóa sản phẩm theo id
							5. Tìm kiếm sản phẩm theo tên
							6. Thay đổi thông tin sách theo id sách
							7. Quay lại							
							
							====================================================
											
							""");
            int choice ;
            while (true) {
                try {
                    System.out.println("Mời bạn lựa chọn menu");
                    choice = Integer.parseInt(sc.nextLine());
                    if (choice <= 0) {
                        System.err.println("Vui lòng nhập lại số duong");
                    }
                    break;
                }
                catch (NumberFormatException e
                )
                {
                    System.err.println("Bạn vui lòng nhập số nguyên");
                }
            }
            switch (choice)
            {
                case 1:
                {
                    addNewProduct(sc);
                    break;
                }
                case 2:
                {
                    showAllProduct();
                    break;
                }
                case 3:
                {
                    arrangeProductByPriceDecrease();
                    break;
                }
                case 4:
                {
                    deleteProduct(sc);
                    break;
                }
                case 5:
                {
                    searchProductByName(sc);
                    break;
                }
                case 6:
                {
                    updateProduct(sc);
                    break;
                }
                case 7:
                {
                    isLoop = false;
                    break;
                }
                default:
                    System.err.println("Nhập lại từ 1 -> 6 đê");
            }
        }
        while (isLoop);
    }

    private void searchProductByName(Scanner sc) {
        if (ProductFeatureImpl.products.isEmpty()){
            System.err.println("Danh sách trống ko thể search theo tên");
            return;
        }
        System.out.println("Mời bạn nhập tên sản phẩm");
        String name = sc.nextLine();
        boolean isExit=ProductFeatureImpl.products.stream().anyMatch(product -> product.getProductName().equals(name));
        if (isExit){
            producFeature.searchProductByName(name);
        }else {
            System.err.println("Không tìm thấy sản phẩm "+name);
        }

    }

    private void arrangeProductByPriceDecrease() {
         producFeature.sortProductByPrice();
    }

    private void updateProduct(Scanner sc) {
        if (ProductFeatureImpl.products.isEmpty()){
            System.out.println("Danh sách trống");
            return;
        }
        System.out.println("Mời bạn nhâập idUpdate");
        String idUpdate = sc.nextLine();
        int indexUpdate = producFeature.findIndexById(idUpdate);
        if(indexUpdate == -1) {
            System.out.println("Không tồn tại" + idUpdate);
        }else {
            Product productOLD = ProductFeatureImpl.products.get(indexUpdate);
            productOLD.inputUpdate(sc,ProductFeatureImpl.products,CatalogFeatureImpl.catalogs);
            producFeature.addAndUpdate(productOLD);

        }

    }

    private void deleteProduct(Scanner sc) {
        if (ProductFeatureImpl.products.isEmpty()){
            System.out.println("Danh sách trống");
            return;
        }
        System.out.println("Mời bạn nhập id muốn xóa");
        String idDelete = sc.nextLine();
        boolean isExit=ProductFeatureImpl.products.stream().anyMatch(product -> product.getProductId().equals(idDelete));
        if (!isExit){
            System.err.println("Không tìm thấy id muốn xóa"+idDelete);
        }
        else {
            producFeature.remove(idDelete);
        }
    }

    private void showAllProduct() {
    if (ProductFeatureImpl.products.isEmpty()){
        System.out.println("Danh sách trống");
        return;
    }
    ProductFeatureImpl.products.forEach(Product::displayData);
    }

    private void addNewProduct(Scanner sc) {
        System.out.println("Mời bạn nhập số lượng sách muốn thêm vào");
        int n=0;
        while (true) {
            try {
                n = Integer.parseInt(sc.nextLine());
                if (n <= 0) {
                    System.err.println("Mời bạn nhập số duong");
                }break;
            } catch (NumberFormatException e) {
                System.err.println("Bạn phải nhập số nguyên");
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println("Mời bạn thêm phần tử thứ" +(i+1));
            Product p = new Product();
            p.inputData(sc, ProductFeatureImpl.products, CatalogFeatureImpl.catalogs);
            producFeature.addAndUpdate(p);

        }
    }
}

