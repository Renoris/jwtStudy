package study.jdnc7.homeworkproject.feature.board.mapper;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import study.jdnc7.homeworkproject.feature.board.model.dto.BoardDto;
import study.jdnc7.homeworkproject.feature.board.model.dto.BoardRequest;
import study.jdnc7.homeworkproject.feature.board.model.entity.Board;
import study.jdnc7.homeworkproject.feature.common.model.PageInfo;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@MybatisTest
public class BoardMapperTest {
    @Autowired
    public BoardMapper boardMapper;

    @Test
    public void 데이터_상세_검색시_올바르게_조회되어야한다() {
        //given
        Long boardId = 1L;

        //when
        Optional<BoardDto.Detail> info = boardMapper.findByIdToDetail(boardId);

        //then
        assertThat(info).isPresent();
        assertThat(info.get().getBoardContent()).isNotNull();
        assertThat(info.get().getModifiedAt()).isNotNull();
    }

    @Test
    public void 데이터목록_검색시_올바르게_검색되어야한다() {
        //given
        PageInfo pageInfo = PageInfo.builder().pageSize(10).pageNum(0).build();

        //when
        List<BoardDto.ListItem> list = boardMapper.findAllByPageInfo(pageInfo);

        //then
        assertThat(list.get(0)).isNotNull();
        assertThat(list.get(0).getBoardId()).isNotNull();
        assertThat(list.get(0).getBoardTitle()).isEqualTo("testtitle");
        assertThat(list.size()).isGreaterThanOrEqualTo(2);
    }


    @Test
    public void 데이터삽입시_올바르게_삽입되어야한다 () {
        //given
        BoardRequest boardRequest = BoardRequest.builder().boardTitle("test1").boardContent("test2").build();
        Board board = boardRequest.toBoardEntity(1L);

        //when
        boardMapper.insert(board);

        //then
        assertThat(board.getBoardId()).isNotNull();
    }

    @Test
    public void 데이터수정시_올바르게_수정되어야한다() {
        //given
        Long targetId = 1L;
        Long modifiedBy = 1L;
        BoardRequest boardRequest = BoardRequest.builder().boardTitle("changedTitle").boardContent("changedContent").build();
        Board board = boardMapper.findById(targetId).get();
        board.updateContent(modifiedBy, boardRequest);

        //when
        boardMapper.update(board);
        Board afterBoard = boardMapper.findById(1L).get();

        //then
        assertThat(afterBoard.getBoardContent()).isEqualTo(boardRequest.getBoardContent());
        assertThat(afterBoard.getBoardTitle()).isEqualTo(boardRequest.getBoardTitle());
        assertThat(afterBoard.getModifiedBy()).isEqualTo(modifiedBy);
    }

}
