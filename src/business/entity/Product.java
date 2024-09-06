package business.entity;

import business.features.imp.CatalogFeatureImpl;
import business.features.imp.ProductFeatureImpl;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Product {
    private String productId,productName,description;
    private double productPrice;
    private int stock;
    private Catalog catalog;
    private boolean status;

    public Product() {
    }

    public Product(String productId, String productName, String description, double productPrice, int stock, Catalog catalog, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.productPrice = productPrice;
        this.stock = stock;
        this.catalog = catalog;
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    //Input Data Product
    public void inputData(Scanner sc, List<Product> products,List<Catalog> catalogs){
        this.productId = inputProductId(sc);
        this.productName = inputProductName(sc);
        this.productPrice = inputProductPrice(sc);
        this.description = inputProductDescriptions(sc);
        this.catalog = inputProductCatalog(sc);
        this.stock = inputProductStock(sc);
        this.status = true;
    }
    public void inputUpdate(Scanner sc, List<Product> products,List<Catalog> catalogs){
        this.productName = inputProductName(sc);
        this.productPrice = inputProductPrice(sc);
        this.description = inputProductDescriptions(sc);
        this.catalog = inputProductCatalog(sc);
        this.stock = inputProductStock(sc);
        this.status = inputStatusUpdate(sc);
    }

    private boolean inputStatusUpdate(Scanner sc) {
        System.out.println("Mời bạn nhập status true or false");
        do {
            String status = sc.nextLine();
            if (status.equalsIgnoreCase("true") || status.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(status);
            }
            else {
                System.err.println("Bạn nhập sai cú pháp True-False");
            }


        }while(true);
    }

    private int inputProductStock(Scanner sc) {
        int categoryStock ;
        do {
            while (true){
                try {
                    System.out.println("Mời bạn nhập catalog tồn kho bạn muốn thêm vào ");
                    categoryStock = Integer.parseInt(sc.nextLine());
                    break;
                }catch (NumberFormatException e){
                    System.out.println("Yêu cầu bạn nhập số nguyên");
                }
            }
            if (categoryStock < 10){
                System.err.println("Category stock phải lớn hơn 10");
            }
            else {
                return categoryStock;
            }

        }while (true);
    }

    private Catalog inputProductCatalog(Scanner sc) {
        //hiển thị danh sách Catalog
        CatalogFeatureImpl.catalogs.forEach(catalog -> {
            System.out.printf("[Id:%d|Name:%s]\n",catalog.getCatalogId(),catalog.getCatalogName());

        }
        );
        System.out.println("Lựa chọn id catalog");
        do {
            int idCata = Integer.parseInt(sc.nextLine());
//            if (catalogId.isBlank()) {
//                System.err.println("Không để trống ID");
//            }
            Optional<Catalog> optionalCatalog=CatalogFeatureImpl.catalogs.stream().filter(item->item.getCatalogId()==idCata).findFirst();
            if(optionalCatalog.isPresent()){
                Catalog catalog=optionalCatalog.get();
                return optionalCatalog.get();
            }
        }while(true);
    }

    private String inputProductDescriptions(Scanner sc) {
        System.out.println("Mời bạn nhập mô tả sản phẩm");
        String des=sc.nextLine();
        return des;
    }

    private double inputProductPrice(Scanner sc) {
        double productPrice;
        do {
           while (true){
               try {
                   System.out.println("Mời bạn nhập giá sản phẩm");
                   productPrice = Double.parseDouble(sc.nextLine());
                   break;
               }
               catch (NumberFormatException e) {
                   System.err.println("Bạn phải nhập số, xin vui lòng nhập lại");
               }
           }
           if(productPrice > 0){
               return productPrice;
           }else {
               System.err.println("giá sản phẩm phải lớn hơn không");;
           }



        }while (true);
    }

    private String inputProductName(Scanner sc) {
        System.out.println("Mời bạn nhập tên sản phẩm");
        do {
            String name = sc.nextLine();
            if (name.isBlank()){
                System.err.println("Không để trống tên");
            }
            else {
                return name;
            }


        }while(true);
    }

    private String inputProductId(Scanner sc) {
        System.out.println("Mời bạn nhập ID");
        do {
            String productId = sc.nextLine();
            if (productId.matches("^P\\d{4}$")) {
                boolean check= ProductFeatureImpl.products.stream().anyMatch(product -> product.getProductId().equals(productId));
                if (check) {
                    System.err.println("Tên sản phẩm đã bị trùng");
                }
                else {
                    return productId;
                }
            }
            else {
                System.err.println("Bạn nhập sai cú pháp vd P1000");
            }

        }while (true);

    }


    public void displayData() {
        System.out.printf(
                "[ID: %s | Name: %s | Des: %s | Price: %.2f | Stock: %d | Catalog: %s | Status: %s]\n",
                this.productId,
                this.productName,
                this.description,
                this.productPrice,
                this.stock,
                this.catalog.getCatalogName(),
                this.status ? "Active" : "Inactive"
        );
}}
