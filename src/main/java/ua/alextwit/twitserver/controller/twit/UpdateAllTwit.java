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
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/twit/update-all-twits")
public class UpdateAllTwit extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Gson gson = new JsonUtil().getGson();
            List<TwitDto> inputTwitsDto = gson.fromJson(req.getReader(), new TypeToken<ArrayList<TwitDto>>(){}.getType());
            TwitConvert convert = new TwitConvert();
            List<Twit> twits = inputTwitsDto.stream()
                    .map(convert::convertDtoToTwit)
                    .toList();
            List<Twit> updatedTwit = TwitService.getTwitService().updateAll(twits);
            List<TwitDto> TwitsDto = updatedTwit.stream()
                    .map(convert::convertTwitToDto)
                    .toList();
            resp.getWriter().write(gson.toJson(TwitsDto));
        } catch (Exception e) {
            new SentException().sent(resp, e);
        }
    }
}
