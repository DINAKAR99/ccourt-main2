package com.example.demo.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ServiceMaster;

@Repository
public interface ServiceMasterRepository
    extends JpaRepository<ServiceMaster, Long> {
  ServiceMaster findById(long serviceid);

  List<ServiceMaster> findAllByOrderByDisplayOrder();

  List<ServiceMaster> findAllByDeleteFlagOrderByServiceIdAsc(String deleteFlag);

  @Query(value = "select ms.* from tfiber_services_mst ms where service_id in (select service_id from tfiber_role_services where role_id=?1 ) order by display_order", nativeQuery = true)
  List<ServiceMaster> getAllServicesByRole(Long roleId);

  @Transactional
  @Modifying
  @Query(value = "INSERT INTO tfiber_role_services (role_id, service_id) VALUES (?1, ?2)", nativeQuery = true)
  void insertRoleService(int roleId, int serviceId);

  @Transactional
  @Modifying
  @Query(value = " DELETE FROM tfiber_role_services\r\n" + //
      "WHERE role_id = ?1\r\n", nativeQuery = true)
  void deleteRoleService(int roleId);
}
