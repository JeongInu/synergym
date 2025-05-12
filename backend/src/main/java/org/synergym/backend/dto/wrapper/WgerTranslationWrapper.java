package org.synergym.backend.dto.wrapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.synergym.backend.dto.TranslationDTO;

import java.util.List;

@Getter
@NoArgsConstructor
public class WgerTranslationWrapper {
    private List<TranslationDTO> results;
}

