package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

@JsonDeserialize(builder = Song.Builder.class)
public class Song {
    @JsonProperty
    @NotNull(message = "Name is required")
    private String name;
    @JsonProperty
    private String album;
    @JsonProperty
    private GENRES genre;
    @Min(value = 0, message = "Price can't be lesser than 0.")
    @Max(value = 10, message = "Price can't be greater than 10.")
    private double price;
    @Min(value = 0, message = "A number of listens can't be lesser than zero.")
    private int listens;

    public Song fromStringSerialize(String content) {
        Song song = new Song();
        String[] carString = content.split(",");
        var values = new ArrayList<String>();
        for (String item : carString) {
            String[] innerItem = item.split("=");
            values.add(innerItem[1]);
        }
        song.name = values.get(0).trim();
        song.album = values.get(1).trim();
        song.genre = GENRES.valueOf(values.get(2).trim());
        song.price = Double.parseDouble(values.get(3).trim());
        song.listens = Integer.parseInt(values.get(4).trim());
        return song;
    }

    public String toStringSerialize() {
        return "name=" + name +
                ",album=" + album +
                ",genre=" + genre +
                ",price=" + price +
                ",listens=" + listens;
    }

    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "set")
    public static class Builder {
        private Song song;

        public Builder() {
            song = new Song();
        }

        public Builder setName(String name) {
            song.name = name;
            return this;
        }

        public Builder setAlbum(String album) {
            song.album = album;
            return this;
        }

        public Builder setGenre(GENRES genre) {
            song.genre = genre;
            return this;
        }

        public Builder setPrice(double price) {
            song.price = price;
            return this;
        }

        public Builder setListens(int listens) {
            song.listens = listens;
            return this;
        }

        public Song build() {
            Song s = this.song;
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Song>> violations = validator.validate(s);
            StringBuilder errorMessage = new StringBuilder();
            for(ConstraintViolation<Song> violation : violations){
                errorMessage.append(violation.getInvalidValue() + violation.getMessage() + violation.getPropertyPath());
            }
            if(!errorMessage.isEmpty()){
                throw new IllegalArgumentException(errorMessage.toString());

            }

            return song;
        }
    }

    /**
     * Overriding toString method
     *
     * @return information on the object
     */
    @Override
    public String toString() {
        return "{\"name\":" + name + ",\"album\":" + album + ",\"genre\":" + genre + "}";
    }

    /**
     * This is the method which tells you whether object are equal or not.
     *
     * @param object
     * @return if objects are equal
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Song song = (Song) object;
        return Objects.equals(name, song.name)
                && Objects.equals(album, song.album)
                && Objects.equals(genre, song.genre)
                && Objects.equals(price, song.price)
                && Objects.equals(listens, song.listens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, album, genre, price, listens);
    }

    public String getName() {
        return name;
    }

    public String getAlbum() {
        return album;
    }

    public GENRES getGenre() {
        return genre;
    }

    public double getPrice() {
        return price;
    }
    public double getListens() {
        return listens;
    }
}
