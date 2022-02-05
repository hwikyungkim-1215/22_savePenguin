package com.cos.photogramstart.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cos.photogramstart.domain.qr.QRcode;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



//public interface QRcodeRepository extends CrudRepository <QRcode,Integer> {
public interface QRcodeRepository extends JpaRepository <QRcode,Integer> {
	
	int countByuserid(String userid);
	@Query(value = "SELECT qrname, cuppic From qrcode where userid = ?1", nativeQuery = true)
	List<Object[]>  sellectAllQRcode(String userid);
	

	//select qrpic, qrname from Qrcode where userid = "1" and qrname = "Name22";
	@Query(value = "SELECT qrpic, qrname from qrcode where userid= ?1 and qrname = ?2 ", nativeQuery = true)
	List<Object[]> sellectOneQRcode(String userid, String qrname);
	

	

}

