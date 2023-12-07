package com.collabo.taskmanagement.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    MemberRepository memberRepository;

    public Member loadUserByAuthority(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String userEmail = authentication.getName();

            Member member = memberRepository.findByEmail(userEmail);

            return member;
        } else {
            return null;
        }
    }


    public String insert(Member member){
        Member dbmember = memberRepository.findByEmail(member.getEmail());

        if(dbmember == null){
            memberRepository.insert(member);
            return "success";
        }else{
            return "duplicate";
        }
    }

    public Member searchMemberByEmail(String email){
        Member member = memberRepository.findByEmail(email);
        return member;
    }

    public Member searchMemberByIdx(int idx){
        Member member = memberRepository.findByIdx(idx);
        return member;
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
