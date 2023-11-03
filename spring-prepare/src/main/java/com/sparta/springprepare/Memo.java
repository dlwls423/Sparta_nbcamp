package com.sparta.springprepare;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor // final 필드를 가진 생성자를 만들어줌
public class Memo {
    private final String username;
    private String contents;
}
