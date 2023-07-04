package com.jhsfully.api.service.impl;

import com.jhsfully.api.dto.ProductDto;
import com.jhsfully.api.dto.Response;
import com.jhsfully.api.exception.ProductException;
import com.jhsfully.api.service.ProductInformationService;
import com.jhsfully.domain.domain.ProductInfo;
import com.jhsfully.domain.domain.ProductList;
import com.jhsfully.domain.repository.ProductInfoRepository;
import com.jhsfully.domain.repository.ProductListRepository;
import com.jhsfully.domain.type.Organization;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductInformationServiceImpl implements ProductInformationService {

    private final ProductInfoRepository productInfoRepository;
    private final ProductListRepository productListRepository;

    @Override
    @Cacheable(value = "product", key = "#organization", cacheManager = "redisCacheManager")
    public ProductDto.ProductResponse getProducts(Organization organization) {

        List<ProductInfo> productInfoList;

        if(organization == Organization.NONE){
            productInfoList = productInfoRepository.findAll();
        }else{
            productInfoList = productInfoRepository.findByOrganization(organization);
        }

        List<ProductDto.ProductData> productDtoList = productInfoList.stream()
                .map(ProductDto.ProductData::of).collect(Collectors.toList());

        ProductDto.ProductResponse response = new ProductDto.ProductResponse();

        response.setData(productDtoList);
        response.setResponseCode("200");
        response.setResponseMessage("Success >_<?");

        return response;
    }

    @Override
    @CacheEvict(value = "product", key = "#request.organizationCode", cacheManager = "redisCacheManager")
    @Transactional
    public Response receiveProduct(ProductDto.ProductReceiveRequest request) {

        if(request.getOrganizationCode() == Organization.NONE){
            throw new ProductException("존재하지 않는 기관입니다.");
        }

        ProductInfo productInfo;

        Optional<ProductInfo> optionalProductInfo = productInfoRepository
                .findByOrganizationAndProduct(request.getOrganizationCode(),
                        request.getProductCode());

        if(optionalProductInfo.isPresent()){
            productInfo = optionalProductInfo.get();

            productInfo.setProductName(request.getProductName());
            productInfo.setProduct_minimum_interest(request.getProductMinimumInterest());
            productInfo.setProduct_maximum_interest(request.getProductMaximumInterest());

        }else{
            productInfo = ProductInfo.builder()
                    .organization(request.getOrganizationCode())
                    .product(request.getProductCode())
                    .productName(request.getProductName())
                    .product_minimum_interest(request.getProductMinimumInterest())
                    .product_maximum_interest(request.getProductMaximumInterest())
                    .build();

            ProductList productList = ProductList.builder()
                    .organization(request.getOrganizationCode())
                    .product(request.getProductCode())
                    .build();

            productListRepository.save(productList);
        }

        productInfoRepository.save(productInfo);


        return new Response("200", "Success >_<!!");
    }
}
