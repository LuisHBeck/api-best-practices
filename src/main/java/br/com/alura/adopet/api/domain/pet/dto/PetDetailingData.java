package br.com.alura.adopet.api.domain.pet.dto;

import br.com.alura.adopet.api.domain.pet.model.Pet;
import br.com.alura.adopet.api.domain.pet.model.PetType;
import br.com.alura.adopet.api.domain.shelter.model.Shelter;

public record PetDetailingData(PetType type, String name, String race, Integer age, String color, Float weight, Shelter shelter)  {
    public PetDetailingData(Pet pet){
        this(pet.getType(), pet.getName(), pet.getRace(), pet.getAge(), pet.getColor(), pet.getWeight(), pet.getShelter());
    }
}
