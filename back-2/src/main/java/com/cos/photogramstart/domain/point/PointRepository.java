package com.cos.photogramstart.domain.point;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cos.photogramstart.domain.user.User;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public interface PointRepository extends JpaRepository<Point, Integer>{

	@Query(value = "SELECT cuppint, pointDate, pointLocation, qrname From point where username = ?1", nativeQuery = true)
	List<Object[]>  sellectAllPoint(String username);

	//int countByUserid(String userid);
	
	@Query(value = "UPDATE User SET point = ?1 where userid = ?2",nativeQuery = true)
	User updateUserPoint(int point, String userid);
	


}
