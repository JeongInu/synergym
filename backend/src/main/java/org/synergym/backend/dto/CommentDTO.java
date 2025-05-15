package org.synergym.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CommentDTO {
    private Integer id;
    private Integer postId;
    private String content;
    private Integer userId;
    private String username; // 작성자 이름
    private LocalDateTime createdAt; // 작성일
    private LocalDateTime updatedAt; // 수정일
}