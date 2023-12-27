package br.com.alura.adopet.api.domain.tutor.dto;

import br.com.alura.adopet.api.domain.tutor.model.Tutor;

public record TutorDetailingData(Long id, String name, String phone, String email) {

    public TutorDetailingData(Tutor tutor) {
        this(tutor.getId(), tutor.getName(), tutor.getPhone(), tutor.getEmail());
    }
}
