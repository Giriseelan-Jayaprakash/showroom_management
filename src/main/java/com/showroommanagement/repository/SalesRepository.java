package com.showroommanagement.repository;

import com.showroommanagement.dto.BikeDetail;
import com.showroommanagement.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Integer> {
//    @Query(value = "select from Sales "+
//            "join sales.bike"+
//            "join sales.customer"+
//            "join bike.salesman"+
//            "join salesman.showroom"+
//            "join showroom.salesmanager"+
//            "where (:search is null or "+
//            "showroom.name like %:search% or showroom.brand like %:search% or" +
//            "salesman.name like %:search% or salesman.contactNumber like %:search% or"+
//            "salesmanager.name like %:search% or"+
//            "customer.name like %:search% or customer.contactNumber like %:search% or customer.email like %:search% or"+
//            "bike.name like %:search% or bike.price %:search% or"+
//            "sales.salesDate like %:search%)",
//            nativeQuery = false)
//    List<Sales> retrieveBySearch(@Param("search") final String search);

    @Query("SELECT s FROM Sales s " +
            "JOIN s.bike b " +
            "JOIN b.salesman sa " +
            "JOIN sa.showroom sh " +
            "Join sh.salesmanager smngr "+
            "JOIN s.customer cs " +
            "JOIN cs.salesman sman "+
            "JOIN sman.showroom sroom "+
            "WHERE sh.name = :showroomName " +
            "AND b.name = :bikeName")
    List<Sales> retrieveSalesByShowroomAndBikeName(String showroomName, String bikeName);

}
