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
@Entity(name = "product_info")
public class ProductInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "org_cd", columnDefinition = "VARCHAR(5)")
    @Convert(converter = OrganizationConverter.class)
    private Organization organization;

    @Column(name = "prod_cd", columnDefinition = "VARCHAR(3)")
    @Convert(converter = ProductConverter.class)
    private Product product;

    @Column(name = "prod_nm")
    private String productName;

    @Column(name = "prod_min_intr")
    private double product_minimum_interest;

    @Column(name = "prod_max_intr")
    private double product_maximum_interest;
}
