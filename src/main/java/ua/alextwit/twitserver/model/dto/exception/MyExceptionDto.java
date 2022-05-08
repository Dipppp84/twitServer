package ua.alextwit.twitserver.model.dto.exception;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyExceptionDto {
    @Expose
    private int statusError;
    @Expose
    private String messageError;
}
