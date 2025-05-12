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
    private int viewCount;
    private Integer userId;
    private String username;

}