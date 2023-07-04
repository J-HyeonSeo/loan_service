package com.jhsfully.domain.converter;

import com.jhsfully.domain.type.Product;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ProductConverter implements AttributeConverter<Product, String> {


    @Override
    public String convertToDatabaseColumn(Product attribute) {
        if(attribute == null){
            throw new IllegalArgumentException("Attribute is Empty!!");
        }
        return attribute.getCode();
    }

    @Override
    public Product convertToEntityAttribute(String dbData) {
        if(dbData == null){
            throw new IllegalArgumentException("DbData is Empty!!");
        }
        return Product.of(dbData);
    }
}
