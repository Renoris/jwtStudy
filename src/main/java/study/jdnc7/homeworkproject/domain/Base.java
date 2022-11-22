package study.jdnc7.homeworkproject.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Base {
    protected LocalDateTime createdAt;
    protected Long createdBy;
    protected LocalDateTime modifiedAt;
    protected Long modifiedBy;
}
