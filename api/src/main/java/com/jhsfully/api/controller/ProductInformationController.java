package com.jhsfully.api.controller;

import com.jhsfully.api.dto.ProductDto;
import com.jhsfully.api.dto.Response;
import com.jhsfully.domain.type.Organization;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProductInformationController {

    ProductDto.ProductResponse getProducts(@PathVariable Organization organization);
    Response receiveProduct(@RequestBody ProductDto.ProductReceiveRequest request);
}
