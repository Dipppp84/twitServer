package ua.alextwit.twitserver.model.dto;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class TwitDto {
    @EqualsAndHashCode.Exclude
    @Expose
    Long id;
    @Expose
    String message;
    @Expose
    Boolean important;
    @Expose
    Boolean heart;
    @EqualsAndHashCode.Exclude
    @Expose(deserialize = false)
    LocalDate dateOfCreation;
}
