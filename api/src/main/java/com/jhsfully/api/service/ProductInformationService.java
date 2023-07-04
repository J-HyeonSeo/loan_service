package com.jhsfully.api.service;

import com.jhsfully.api.dto.ProductDto;
import com.jhsfully.api.dto.Response;
import com.jhsfully.domain.type.Organization;

public interface ProductInformationService {

    ProductDto.ProductResponse getProducts(Organization organization);
    Response receiveProduct(ProductDto.ProductReceiveRequest request);

}
