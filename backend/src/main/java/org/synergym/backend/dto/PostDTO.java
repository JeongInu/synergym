package org.synergym.backend.dto;

import lombok.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostDTO {
    private Integer id;
    private String title;
    private String content;
    private Integer userId;
    private String username; // 작성자 이름 추가
    private int viewCount;
    private LocalDateTime createdAt; // 생성일 필드 추가
    private LocalDateTime updatedAt; // 수정일 필드 추가
    private boolean deleteYn; // 삭제 여부 필드 추가

}