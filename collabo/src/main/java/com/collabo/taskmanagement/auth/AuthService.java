package com.collabo.taskmanagement.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    MemberRepository memberRepository;

    public String insert(Member member){
        Member dbmember = memberRepository.findByEmail(member.getEmail());

        if(dbmember == null){
            memberRepository.insert(member);
            return "success";
        }else{
            return "duplicate";
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member dbMember = memberRepository.findByEmail(email);

        if(dbMember == null)
            throw new UsernameNotFoundException(email);

        return User.builder()
                .username(dbMember.getEmail())
                .password(dbMember.getPassword())
                .roles("USER")
                .build();

    }
}
