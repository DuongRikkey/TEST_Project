package presentation;

import business.entity.CartItem;
import business.entity.Product;
import business.features.ICartFeature;
import business.features.imp.CartFeatureImpl;
import business.features.imp.CatalogFeatureImpl;
import business.features.imp.ProductFeatureImpl;

import java.util.Scanner;

public class UserManagement {
    ICartFeature cartFeature=new CartFeatureImpl();
    public void menuUser(Scanner sc) {

        boolean isLoop=true;
        do {
            System.out.println("""
							======================= MENU =======================
							1. Hiển thị danh sách sản phẩm
							2. Thêm vào giỏ hàng
							3. Hiển thị tất cả danh sách giỏ hàng
							4. Thay đổi số lượng sản phẩm trong giỏ hàng
							5. Xóa 1 sản phẩm trong giỏ hàng
							6. Xóa tất cả sản phẩm trong giỏ hàng
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
            switch (choice){
                case 1:{
                    displayAllProduct();
                    break;

                }
                case 2:{
                    addToCart(sc);
                    break;

                }
                case 3:{
                    showAllCart();
                    break;
                }
                case 4:{
                    editQuantityCart(sc);
                    break;

                }
                case 5:{
                    deleteOneCart(sc);
                     break;   }
                case 6:{
                    deleteAllProduct();
                    break;

                }
                case 7:
                {
                    isLoop=false;
                    break;

                }
            }

        }while (isLoop);
    }

    private void deleteAllProduct() {
        if (CartFeatureImpl.cartItems==null || CartFeatureImpl.cartItems.isEmpty() ){
            System.err.println("Danh sách trống");
            return;
        }
        CartFeatureImpl.cartItems.clear();
    }


    private void deleteOneCart(Scanner sc) {
        if(CartFeatureImpl.cartItems==null||CartFeatureImpl.cartItems.isEmpty()){
            System.out.println("Danh sách trống");
            return;
        }
        int idDelete;
        while (true){
            try {
                System.out.println("Mời bạn nhập mã ID xóa");
                idDelete = Integer.parseInt(sc.nextLine());
                if (idDelete <= 0){
                    System.err.println("Bạn phải nhập số lớn hơn ko");
                    continue;
                }
                break;
            }
            catch (NumberFormatException e){
                System.err.println("Bạn phải nhập số nguyên");
            }
        }
        int newidDelte=idDelete;
        boolean isExist= CartFeatureImpl.cartItems.stream().anyMatch(cartItem -> cartItem.getCartItemId()==newidDelte);
        if (!isExist) {
            System.err.println("Không tìm thấy"+idDelete);
            return;
        }
         cartFeature.remove(newidDelte);
    }

    private void editQuantityCart(Scanner sc) {
       if(CartFeatureImpl.cartItems==null||CartFeatureImpl.cartItems.isEmpty()){
           System.out.println("Danh sách trống");
           return;
       }
       int cartID;
       while (true){
           try {
               System.out.println("Nhập ID");
               cartID = Integer.parseInt(sc.nextLine());
               break;
           }
           catch (NumberFormatException e) {
               System.out.println("Bạn phải nhập số nguyên, vui lòng nhập lại");
           }
       }
       int qtyCart;
       while (true){
           try {
               System.out.println("Nhập số lượng bạn muốn sửa quantity");
               qtyCart = Integer.parseInt(sc.nextLine());
               break;
           }catch (NumberFormatException e) {
               System.err.println("Bạn nhập vào số nguyên nhé");
           }
       }
        boolean check = false;

        for (CartItem cartItem : CartFeatureImpl.cartItems) {
            if (cartItem.getCartItemId() == cartID) {
                Product product = cartItem.getProduct();

                int currentQuantity = cartItem.getQuantity();
                int difference = qtyCart - currentQuantity;

                if (difference > 0) {
                    if (difference > product.getStock()) {
                        System.err.println("Không đủ hàng trong kho, vui lòng thử lại");
                        return;
                    }
                    product.setStock(product.getStock() - difference);
                } else {
                    product.setStock(product.getStock() - difference); // `-difference` adds the stock back
                }

                cartItem.setQuantity(qtyCart);
                System.out.println("Cập nhật số lượng thành công");
                check = true;
                break;
            }
        }

        if (!check) {
            System.out.println("Không tìm thấy sản phẩm với ID: " + cartID);
        }
    }

    private void showAllCart() {
        if (CartFeatureImpl.cartItems.isEmpty()){
            System.out.println("Danh sách trống");
            return;
        }
        CartFeatureImpl.cartItems.forEach(CartItem::displayData);
    }

    private void addToCart(Scanner sc) {
        ProductFeatureImpl.products.forEach(product -> {
            System.out.printf("[ID:%s|Name:%s]\n", product.getProductId(), product.getProductName());
        });
        System.out.println("Mời bạn nhập ID sản phẩm bạn muốn thêm vào giỏ hàng");
        String productId = sc.nextLine();
        boolean checkProductCart=false;
        for(Product product : ProductFeatureImpl.products) {
            if(product.getProductId().equals(productId)) {
                checkProductCart=true;
                boolean check=false;
                for (CartItem cartItem: CartFeatureImpl.cartItems) {
                    if (cartItem.getProduct().getProductId().equals(productId)) {
                        int qtyProduct;
                        while (true){
                            System.out.println("Sản phẩm đã có trong cart,vui lòng nhập số lương");
                            qtyProduct = Integer.parseInt(sc.nextLine());
                            if (qtyProduct >product.getStock()) {
                                System.err.println("hết hàng vui lòng thử lại");
                            }
                            else {
                                break;
                            }
                        }
                        cartItem.setQuantity(cartItem.getQuantity() + qtyProduct);
                        product.setStock(product.getStock() - qtyProduct);
                        System.out.println("Thêm vào giỏ hàng thành công");
                        check=true;
                        break;
                    }

                }
                if (!check) {
                    CartItem newCartItem = new CartItem();
                    newCartItem.inputData(sc,CartFeatureImpl.cartItems,ProductFeatureImpl.products,productId);
                    cartFeature.addAndUpdate(newCartItem);
                    product.setStock(product.getStock() - newCartItem.getQuantity());
                    break;
                }
            }
            if(!checkProductCart) {
                System.err.println("Không thể tìm thấy ID");
            }
        }


    }

    private void displayAllProduct() {
        int count=0;
        for (Product product: ProductFeatureImpl.products){
            ProductFeatureImpl.products.forEach(Product::displayData);
            count++;

        }
        System.out.println("Cửa hàng có "+count+" sản phẩm");
    }
}
