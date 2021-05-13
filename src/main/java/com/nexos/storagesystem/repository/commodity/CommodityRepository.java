package com.nexos.storagesystem.repository.commodity;

import com.nexos.storagesystem.model.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommodityRepository extends JpaRepository<Commodity, UUID>, JpaSpecificationExecutor<Commodity> {
    Boolean existsByName(String name);
}
