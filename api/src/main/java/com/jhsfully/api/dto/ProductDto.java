package com.jhsfully.api.dto;

import com.jhsfully.domain.domain.ProductInfo;
import com.jhsfully.domain.type.Organization;
import com.jhsfully.domain.type.Product;
import lombok.*;

import java.util.List;

public class ProductDto{

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductReceiveRequest{
        private Organization organizationCode;
        private Product productCode;
        private double productMaximumInterest;
        private double productMinimumInterest;
        private String productName;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductResponse extends Response{
        private List<ProductData> data;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ProductData{
        private String organizationCode;
        private String productCode;
        private String productName;
        private double productMaximumInterest;
        private double productMinimumInterest;

        public static ProductData of(ProductInfo productInfo){
            return ProductData.builder()
                    .organizationCode(productInfo.getOrganization().getCode())
                    .productCode(productInfo.getProduct().getCode())
                    .productName(productInfo.getProductName())
                    .productMaximumInterest(productInfo.getProduct_maximum_interest())
                    .productMinimumInterest(productInfo.getProduct_minimum_interest())
                    .build();
        }

    }

}
