package com.jhsfully.domain.converter;

import com.jhsfully.domain.type.Organization;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class OrganizationConverter implements AttributeConverter<Organization, String> {


    @Override
    public String convertToDatabaseColumn(Organization attribute) {
        if(attribute == null){
            throw new IllegalArgumentException("Attribute is Empty!!");
        }
        return attribute.getCode();
    }

    @Override
    public Organization convertToEntityAttribute(String dbData) {
        if(dbData == null){
            throw new IllegalArgumentException("DbData is Empty!!");
        }
        return Organization.of(dbData);
    }
}
