package com.sparta.memo.dto;

import com.sparta.memo.entity.Memo;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class MemoResponseDto {
    private Long id;
    private String username;
    private String contents;
    private LocalDateTime created_at;
    private LocalDateTime modified_at;

    public MemoResponseDto(Memo memo) {
        this.id = memo.getId();
        this.username = memo.getUsername();
        this.contents = memo.getContents();
        this.created_at = memo.getCreatedAt();
        this.modified_at = memo.getModifiedAt();
    }
}
