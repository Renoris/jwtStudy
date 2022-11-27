package study.jdnc7.homeworkproject.feature.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.jdnc7.homeworkproject.domain.PageInfo;
import study.jdnc7.homeworkproject.domain.board.mapper.BoardFileMapper;
import study.jdnc7.homeworkproject.domain.board.mapper.BoardMapper;
import study.jdnc7.homeworkproject.domain.board.model.Board;
import study.jdnc7.homeworkproject.domain.file.model.FileInfo;
import study.jdnc7.homeworkproject.feature.board.model.BoardDto;
import study.jdnc7.homeworkproject.feature.board.model.BoardFileVo;
import study.jdnc7.homeworkproject.feature.board.model.BoardRequest;
import study.jdnc7.homeworkproject.feature.file.service.FileService;
import study.jdnc7.homeworkproject.util.SecurityUtil;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;
    private final BoardFileMapper boardFileMapper;
    private final FileService fileService;

    @Transactional(readOnly = true)
    public Page<BoardDto.ListItem> getBoardList(Pageable pageable) {
        PageInfo pageInfo = PageInfo.of(pageable);
        List<BoardDto.ListItem> list = boardMapper.findAllByPageInfo(pageInfo);
        Long count = boardMapper.findTotalCount();
        return new PageImpl<BoardDto.ListItem>(list, pageable, count);
    }

    @Transactional(readOnly = true)
    public BoardDto.Detail getBoardDetail(Long boardId) {
        return boardMapper.findByIdToDetail(boardId).orElseThrow(() -> new RuntimeException("해당 Board Id에 해당하는 정보가 없습니다"));
    }

    @Transactional
    public Long createBoard(Long userId, BoardRequest boardRequest) throws IOException {
        List<FileInfo> list = fileService.insertFiles(userId, boardRequest);
        Board board = boardRequest.toBoardEntity(userId);
        boardMapper.insert(board);

        if (board.getBoardID() == null) throw new RuntimeException("게시물이 저장되지 않았습니다.");
        mappingBoardFiles(board.getBoardID(), list);

        return board.getBoardID();
    }

    @Transactional
    public void updateBoard(Long userId, Long boardId, BoardRequest boardRequest) throws IOException {
        Board board = boardMapper.findById(boardId).orElseThrow(() -> new RuntimeException("해당 Id에 해당하는 게시글이 없습니다"));
        List<FileInfo> list = fileService.insertFiles(userId, boardRequest);

        //이미 있었던 파일 맵핑제거
        boardFileMapper.deleteAllByBoardId(board.getBoardID());

        board.updateContent(userId, boardRequest);
        boardMapper.update(board);
        mappingBoardFiles(board.getBoardID(), list);
    }

    @Transactional
    public void unVisible(Long userId, Long boardId) {
        Board board = boardMapper.findById(boardId).orElseThrow(() -> new RuntimeException("해당 Board Id에 해당하는 정보가 없습니다"));
        if (!(board.getCreatedBy().equals(userId) || SecurityUtil.isAdmin())) throw new RuntimeException("해당 Board 의 작성자가 아닙니다");
        boardMapper.unVisible(userId, boardId);
    }

    @Transactional
    public void delete(Long userId, Long boardId) {
        Board board = boardMapper.findById(boardId).orElseThrow(() -> new RuntimeException("해당 Board Id에 해당하는 정보가 없습니다"));
        if (!(board.getCreatedBy().equals(userId) || SecurityUtil.isAdmin())) throw new RuntimeException("해당 Board 의 작성자가 아닙니다");
        boardMapper.delete(boardId);
    }

    private void mappingBoardFiles(Long boardId, List<FileInfo> fileInfos) {
        List<BoardFileVo> fileVos= fileInfos.stream()
                .map((fileInfo -> new BoardFileVo(boardId, fileInfo.getFileId()))).collect(Collectors.toList());
        boardFileMapper.insert(fileVos);
    }

}
