package business.entity;

import java.util.List;
import java.util.Scanner;

public class Catalog {
    private int catalogId;
    private String catalogName,descriptions;

    public Catalog() {
    }

    public Catalog(int catalogId, String catalogName, String descriptions) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.descriptions = descriptions;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    //Input Data Catalog
    public void inputData(Scanner sc, List<Catalog> catalogs) {
          this.catalogId = inputCatalogId(sc,catalogs);
          this.catalogName =inputCatalogName(sc);
          this.descriptions =inputCatalogDescriptions(sc);
    }
    public void inputUpdate(Scanner sc, List<Catalog> catalogs) {
        this.catalogName =inputCatalogName(sc);
        this.descriptions =inputCatalogDescriptions(sc);
    }

    public String inputCatalogDescriptions(Scanner sc) {
        System.out.println("Mời bạn nhập tên description nhé");
        do {
            String des=sc.nextLine();
            if (des.isBlank()) {
                System.err.println("Không để trống mô tả");
            }
            else {
                return des;
            }

        }while (true);
    }

    private String inputCatalogName(Scanner sc) {
        System.out.println("Mời bạn nhập tên cata nhé");
        do {
            String nameCata=sc.nextLine();
            if (nameCata.isBlank()) {
                System.err.println("Không để trống tên");
            }
            else {
                return nameCata;
            }

        }while (true);
    }

    public int inputCatalogId(Scanner sc,List<Catalog> catalogs) {
//        int maxId=0;
//        for(int i=0;i<catalogs.size();i++) {
//            if(catalogs.get(i).getCatalogId()>maxId) {
//                maxId=catalogs.get(i).getCatalogId();
//            }
//        }
//        return maxId+1;

        return catalogs.stream().mapToInt(Catalog::getCatalogId).
                max().
                orElse(0)+1;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "catalogId=" + catalogId +
                ", catalogName='" + catalogName + '\'' +
                ", descriptions='" + descriptions + '\'' +
                '}';
    }
}
