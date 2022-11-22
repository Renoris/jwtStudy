package study.jdnc7.homeworkproject.feature.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.jdnc7.homeworkproject.domain.File.model.File;
import study.jdnc7.homeworkproject.domain.PageInfo;
import study.jdnc7.homeworkproject.domain.board.mapper.BoardFileMapper;
import study.jdnc7.homeworkproject.domain.board.mapper.BoardMapper;
import study.jdnc7.homeworkproject.domain.board.model.Board;
import study.jdnc7.homeworkproject.feature.board.model.BoardDto;
import study.jdnc7.homeworkproject.feature.board.model.BoardFileVo;
import study.jdnc7.homeworkproject.feature.board.model.BoardRequest;
import study.jdnc7.homeworkproject.util.SecurityUtil;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;
    private final BoardFileMapper boardFileMapper;

    @Transactional(readOnly = true)
    public List<BoardDto.ListItem> getBoardList(PageInfo pageInfo) {
        return boardMapper.findByPageable(pageInfo);
    }
    
    @Transactional(readOnly = true)
    public BoardDto.Detail getBoardDetail(Long boardId) {
        return boardMapper.findByIdToDetail(boardId).orElseThrow(() -> new RuntimeException("해당 Board Id에 해당하는 정보가 없습니다"));
    }

    @Transactional
    public Long createBoard(BoardRequest boardRequest) {
        Long userId = SecurityUtil.getCurrentUserId().orElseThrow(() -> new RuntimeException("접속하지 않은 유저입니다"));
        Board board = boardRequest.toBoardEntity(userId);
        boardMapper.insert(board);
        List<BoardFileVo> vos = resolveBoardFileVo(board.getBoardID(), boardRequest);
        boardFileMapper.insert(vos);
        return board.getBoardID();
    }

    @Transactional
    public void updateBoard(Long boardId, BoardRequest boardRequest) {
        Long userId = SecurityUtil.getCurrentUserId().orElseThrow(() -> new RuntimeException("접속하지 않은 유저입니다"));
        Board board = boardMapper.findById(boardId).orElseThrow(() -> new RuntimeException("해당 Board Id에 해당하는 정보가 없습니다"));
        if (!(board.getCreatedBy().equals(userId) || SecurityUtil.isAdmin())) throw new RuntimeException("해당 Board 의 작성자가 아닙니다");
        board.updateContent(userId, boardRequest);
        boardMapper.update(board);
        boardFileMapper.deleteAllByBoardId(boardId);
        List<BoardFileVo> vos = resolveBoardFileVo(board.getBoardID(), boardRequest);
        boardFileMapper.insert(vos);
    }

    @Transactional
    public void unVisible(Long boardId) {
        Long userId = SecurityUtil.getCurrentUserId().orElseThrow(() -> new RuntimeException("접속하지 않은 유저입니다"));
        Board board = boardMapper.findById(boardId).orElseThrow(() -> new RuntimeException("해당 Board Id에 해당하는 정보가 없습니다"));
        if (!(board.getCreatedBy().equals(userId) || SecurityUtil.isAdmin())) throw new RuntimeException("해당 Board 의 작성자가 아닙니다");
        boardMapper.unVisible(userId, boardId);
    }

    @Transactional
    public void delete(Long boardId) {
        Long userId = SecurityUtil.getCurrentUserId().orElseThrow(() -> new RuntimeException("접속하지 않은 유저입니다"));
        Board board = boardMapper.findById(boardId).orElseThrow(() -> new RuntimeException("해당 Board Id에 해당하는 정보가 없습니다"));
        if (!(board.getCreatedBy().equals(userId) || SecurityUtil.isAdmin())) throw new RuntimeException("해당 Board 의 작성자가 아닙니다");
        boardMapper.delete(boardId);
    }

    private List<BoardFileVo> resolveBoardFileVo(Long boardId, BoardRequest request) {
        List<File> files = request.getBoardFiles();
        return files.stream().map(file -> new BoardFileVo(boardId, file.getFileId()))
                .collect(Collectors.toList());

    }
}
