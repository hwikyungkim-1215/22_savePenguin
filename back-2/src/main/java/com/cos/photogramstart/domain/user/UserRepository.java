package com.cos.photogramstart.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


// 서비스 만들고 DB저장하기 위해 
// 어노테이션이 없어도 JpaRepository를 상속하면 IoC 등록이 자동으로 된다.
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{ //<user, 타입>
	// JPA query method
	User findByUserid(String userid); //userid으로 찾음 
	
//	String findByUserid2(String userid);
	
	@Query(value = "SELECT point From user where userid = ?1", nativeQuery = true)
	int pointtByUserid(String userid);
	
	@Query(value = "SELECT point From user where userid = ?1", nativeQuery = true)
	int findByUserPoint(String userid);
	

	boolean existsByUserid(String userid);
	boolean existsByUseremail(String useremail);

	void save(int point);
	
	
	
}
