package org.synergym.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseTranslation {

    @Column(name = "language_code")
    private String languageCode;

    @Column(name = "translation_text")
    private String translationText;

    @Column(name = "translated_name")
    private String translatedName;

    @Column(name = "translations_key")
    private String translationsKey;

    @Column(name = "translations")
    private String translations;
}
