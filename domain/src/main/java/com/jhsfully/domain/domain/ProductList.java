package com.jhsfully.domain.domain;

import com.jhsfully.domain.converter.OrganizationConverter;
import com.jhsfully.domain.converter.ProductConverter;
import com.jhsfully.domain.type.Organization;
import com.jhsfully.domain.type.Product;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "product_list")
public class ProductList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "org_cd", columnDefinition = "VARCHAR(5)")
    @Convert(converter = OrganizationConverter.class)
    private Organization organization;

    @Column(name = "prod_cd", columnDefinition = "VARCHAR(3)")
    @Convert(converter = ProductConverter.class)
    private Product product;

}
