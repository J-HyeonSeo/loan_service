package com.jhsfully.domain.repository;

import com.jhsfully.domain.domain.ProductInfo;
import com.jhsfully.domain.type.Organization;
import com.jhsfully.domain.type.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Long> {

    Optional<ProductInfo> findByOrganizationAndProduct(Organization organization, Product product);

    List<ProductInfo> findByOrganization(Organization organization);
}
