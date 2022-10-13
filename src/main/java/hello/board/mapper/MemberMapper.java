package hello.board.mapper;

import hello.board.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    Member login(Member member);

    void save(Member member);

    Member findById(Long id);

    List<Member> findAll();
}
