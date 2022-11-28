package study.jdnc7.homeworkproject.domain;

import lombok.*;
import org.apache.ibatis.type.Alias;
import org.springframework.data.domain.Pageable;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("PageInfo")
public class PageInfo {
    private int pageNum;
    private int pageSize;
    private int offset;
    private String direction;
    private String sortKey;

    //sort는 한번만 있다고 가정
    public static PageInfo of(Pageable pageable) {
        String[] list = new String[2];
        if (pageable.getSort().isSorted()) {
            list = pageable.getSort().toString().split(": ");
        }
        return new PageInfo(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                (int) pageable.getOffset(),
                list[1],
                list[0]);
    }

    public int getOffset() {
        return pageSize * pageNum;
    }
}
