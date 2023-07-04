package com.jhsfully.api.controller;

import com.jhsfully.api.dto.ProductDto;
import com.jhsfully.api.dto.Response;
import com.jhsfully.api.service.ProductInformationService;
import com.jhsfully.domain.type.Organization;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fintech/v1/product")
public class ProductInformationControllerImpl implements ProductInformationController{

    private final ProductInformationService productInformationService;

    @GetMapping("/{organizationCode}")
    public ProductDto.ProductResponse getProducts(@PathVariable Organization organizationCode){
        return productInformationService.getProducts(organizationCode);
    }

    @PostMapping
    public Response receiveProduct(
            @RequestBody ProductDto.ProductReceiveRequest request)
    {
        return productInformationService.receiveProduct(request);
    }

}
