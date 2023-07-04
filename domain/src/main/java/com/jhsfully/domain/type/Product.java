package com.jhsfully.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Product {
    PRODUCT_ONE("001"),
    PRODUCT_TWO("002"),
    PRODUCT_THREE("003"),
    PRODUCT_FOUR("004"),
    PRODUCT_FIVE("005");

    private final String code;

    public static Product of(String code) {
        for (Product product : Product.values()) {
            if (product.getCode().equals(code)) {
                return product;
            }
        }
        throw new IllegalArgumentException("Invalid Product code: " + code);
    }
}
