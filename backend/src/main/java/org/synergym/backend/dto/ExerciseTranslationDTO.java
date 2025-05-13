package org.synergym.backend.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseTranslationDTO {
    private String name;               // 번역된 이름
    private String description;        // 번역된 설명
    private LocalDateTime creationDate;
    private Integer license;
    private Integer language;

    private String translatedName;     // 실제 번역 테이블용 필드
    private String translationsKey;
    private String translations;
    private String languageCode;       // 번역 테이블용 키
    private String translationText;    // 번역 테이블용 텍스트
}
