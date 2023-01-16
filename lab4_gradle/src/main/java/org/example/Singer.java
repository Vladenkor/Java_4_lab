package org.example;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@JsonDeserialize(builder = Singer.Builder.class)
public class Singer {
    @NotNull(message = "Name is required")
    private String name;
    @Size(max = 50)
    @Pattern(regexp = "^(.+)@(.+)$")
    private String email;
    private List<Song> songs = new ArrayList<>();

    public static class Builder {
        private Singer singer;

        public Builder() {
            singer = new Singer();
        }

        public Builder setName(String name) {
            singer.name = name;
            return this;
        }

        public Builder setSongs(List<Song> songs) {
            singer.songs = songs;
            return this;
        }

        public Builder setEmail(String email) {
            singer.email = email;
            return this;
        }

        public Singer build() {
            Singer s = this.singer;
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Singer>> violations = validator.validate(s);
            StringBuilder errorMessage = new StringBuilder();
            for(ConstraintViolation<Singer> violation : violations){
                errorMessage.append(violation.getInvalidValue()).append(violation.getMessage()).append(violation.getPropertyPath());
            }
            if(!errorMessage.isEmpty()){
                throw new IllegalArgumentException(errorMessage.toString());
            }

            return singer;
        }
    }

    /**
     * Overriding toString method
     *
     * @return information on the object
     */
    @Override
    public String toString() {
        return "SINGER:\n" + "Name: " + name + " Email:" + email + "\nSongs: " + songs.toString()
                + "\n___________________________\n";
    }

    /**
     * This is the method which tells you whether object are equal or not.
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
        Singer singer = (Singer) object;
        return Objects.equals(name, singer.name)
                && Objects.equals(songs, singer.songs)
                && Objects.equals(email, singer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, songs, email);
    }

    public String getName() {
        return name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public String getEmail() {
        return email;
    }
}
