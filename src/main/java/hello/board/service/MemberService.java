package hello.board.service;

import hello.board.domain.Member;
import hello.board.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    public Member login(Member member){
        return memberMapper.login(member);
    }

    public void save(Member member){
        memberMapper.save(member);
    }

    public Member findById(Long id){
        return memberMapper.findById(id);
    }

    public List<Member> findAll(){
        return memberMapper.findAll();
    }
}
