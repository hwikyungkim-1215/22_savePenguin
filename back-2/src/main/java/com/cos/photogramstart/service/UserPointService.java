package com.cos.photogramstart.service;

import java.util.List;

import com.cos.photogramstart.domain.user.User;
/*
public class UserPointService {
	
    @Transactional(readOnly = true)
    public List<User> findAllDesc(){
        return boardRepository.findAllDesc().stream()
                .map(BoardListDto::new)
                .collect(Collectors.toList());
    }
    
    public User findById(Long id){
        User entity = boardRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
        return new UserPoint(entity);
    }

}
*/