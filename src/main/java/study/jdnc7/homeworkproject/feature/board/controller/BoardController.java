package study.jdnc7.homeworkproject.feature.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import study.jdnc7.homeworkproject.domain.PageInfo;
import study.jdnc7.homeworkproject.domain.board.mapper.BoardMapper;
import study.jdnc7.homeworkproject.feature.board.model.BoardDto;
import study.jdnc7.homeworkproject.feature.board.service.BoardService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<BoardDto.ListItem> getBoardList(@PageableDefault Pageable pageable) {
        List<BoardDto.ListItem> list = boardService.getBoardList(PageInfo.of(pageable));
        return new PageImpl<>(list, pageable, 0); // 토탈을 한번에 끌어올 방법을 강구하자
    }
}
