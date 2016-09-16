package com.theIronYard.entity;

/**
 * Created by chris on 9/7/16.
 */
public class AnimalBreed {
    private int breedId;
    private String name;
    private int typeId;

    public AnimalBreed(int breedId, String name, int typeId) {
        this.breedId = breedId;
        this.name = name;
        this.typeId = typeId;
    }
    public AnimalBreed(String name, int typeId) {
        this.name = name;
        this.typeId = typeId;
    }

    public AnimalBreed() {
        this.typeId = 0;
        this.name = "";
        this.breedId = 0;
    }

    public int getBreedId() {
        return breedId;
    }

    public void setBreedId(int breedId) {
        this.breedId = breedId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTypeId() {return typeId;}

    public void setTypeId(int typeId) {this.typeId = typeId;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnimalBreed that = (AnimalBreed) o;

        if (getBreedId() != that.getBreedId()) return false;
        if (getTypeId() != that.getTypeId()) return false;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        int result = getBreedId();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getTypeId();
        return result;
    }

    @Override
    public String toString() {
        return "AnimalBreed{" +
                "breedId=" + breedId +
                ", name='" + name + '\'' +
                ", typeId=" + typeId +
                '}';
    }
}
