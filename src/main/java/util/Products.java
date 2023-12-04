package util;

import entities.Product;
import org.apache.commons.lang3.RandomUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Products {
    public static int getRandomProductPosition(List<Product> productList) {
        int position = RandomUtils.nextInt(0, productList.size());
        return position;
    }

    public static Product getNthSmallest(List<Product> productList, int position) {
        List<Product> sortedProduct = productList.stream()
                .sorted(Comparator.comparing(Product::getCurrentPrice))
                .collect(Collectors.toList());
        return sortedProduct.get(position);
    }

    public static Product closestToAverage(List<Product> productList) {
        float averagePrice = getAveragePrice(productList);
        float minPriceDiff = averagePrice;
        Product result = null;
        for (Product product : productList) {
            float priceDiff = Math.abs(product.getCurrentPrice() - averagePrice);
            if(priceDiff < minPriceDiff) {
                minPriceDiff = priceDiff;
                result = product;
            }
        }
        return result;
    }

    public static float getAveragePrice(List<Product> productList) {
        Integer sum = 0;
        for (Product product : productList) {
            sum += product.getCurrentPrice();
        }
        return (float)sum / productList.size();
    }
}
