package hello.board.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {

    void save();
    void findById(Long id);
}
