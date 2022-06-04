package com.codeus.user;

import com.codeus.utils.FileUtils;
import com.codeus.utils.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
//    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    @Transactional
    public List<User> findAll(){//추가함
        List<User> users  = userRepository.findAll();
        return users;
    }

    public User loginUser(User user) throws Throwable {
        User newuser = userRepository.findByUUID(user.getUUID()).orElseThrow(()-> new IllegalArgumentException("학번 오류"));
        if(!user.getPwd().equals(newuser.getPwd())) throw new IllegalArgumentException("비밀번호 불일치") ;
        return newuser;
    }

}
