package com.theIronYard.entity;

/**
 * Created by chris on 9/7/16.
 */
public class AnimalType {
    private int id;
    private String name;

    public AnimalType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public AnimalType(String name) {
        this.name = name;
    }

    public int getTypeId() {
        return id;
    }

    public void setTypeId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return name;
    }

    public void setTypeName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnimalType that = (AnimalType) o;

        if (getTypeId() != that.getTypeId()) return false;
        return getTypeName().equals(that.getTypeName());

    }

    @Override
    public int hashCode() {
        int result = getTypeId();
        result = 31 * result + getTypeName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AnimalType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
