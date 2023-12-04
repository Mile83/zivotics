package entities;

import lombok.*;

import static util.Parser.parsePrice;

@Data
public class Product {
    @NonNull
    private String productTitle;
    @NonNull
    private Integer currentPrice;
    private Integer oldPrice;
    private Integer priceSaving;

    public static Product createProduct(String productTitle, String oldPrice, String currentPrice, String priceSaving) {
        Product product = new Product(productTitle, parsePrice(currentPrice));
        if(oldPrice != null && !oldPrice.isEmpty()) {
            product.setOldPrice(parsePrice(oldPrice));
        }
        if(priceSaving != null && !priceSaving.isEmpty()) {
            product.setPriceSaving(parsePrice(priceSaving));
        }
        return product;
    }

}
