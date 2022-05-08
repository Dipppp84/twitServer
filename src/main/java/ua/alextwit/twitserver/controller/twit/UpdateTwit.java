package ua.alextwit.twitserver.controller.twit;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.alextwit.twitserver.controller.SentException;
import ua.alextwit.twitserver.model.dto.TwitDto;
import ua.alextwit.twitserver.model.entity.Twit;
import ua.alextwit.twitserver.model.service.TwitService;
import ua.alextwit.twitserver.model.service.converter.TwitConvert;
import ua.alextwit.twitserver.utils.gson.JsonUtil;

import java.io.IOException;

@WebServlet("/twit/update-twit")
public class UpdateTwit extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Gson gson = new JsonUtil().getGson();
            TwitDto inputTwitDto = gson.fromJson(req.getReader(), TwitDto.class);
            TwitConvert convert = new TwitConvert();
            Twit twit = convert.convertDtoToTwit(inputTwitDto);
            Twit updatedTwit = TwitService.getTwitService().update(twit);
            TwitDto twitDto = convert.convertTwitToDto(updatedTwit);
            resp.getWriter().write(gson.toJson(twitDto));
        } catch (Exception e) {
            new SentException().sent(resp, e);
        }
    }
}
