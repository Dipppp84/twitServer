package ua.alextwit.twitserver.controller;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import ua.alextwit.twitserver.model.dto.exception.MyExceptionDto;
import ua.alextwit.twitserver.utils.gson.JsonUtil;

import java.io.IOException;

/**
 * Логика в том что HttpServlet это отдельные нити, и каждая из них будет создавать
 * свой объект для ответа на ошибку,если такое потребуется.
 */
public class SentException {
    public void sent(HttpServletResponse resp, Exception e) throws IOException {
        MyExceptionDto exceptionDto = new MyExceptionDto(500, e.getLocalizedMessage());
        Gson gson = new JsonUtil().getGson();
        System.out.println(gson.toJson(exceptionDto));
        resp.getWriter().write(gson.toJson(exceptionDto));
        resp.setStatus(exceptionDto.getStatusError());
    }
}
