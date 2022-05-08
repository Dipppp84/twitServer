package ua.alextwit.twitserver.controller.twit;

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
import java.util.List;

@WebServlet("/twit/get-all-twits")
public class GetAllTwit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            List<Twit> twits = TwitService.getTwitService().getAll();
            List<TwitDto> twitsDto = twits.stream()
                    .map(new TwitConvert()::convertTwitToDto)
                    .toList();
            resp.getWriter().write(new JsonUtil().getGson().toJson(twitsDto));
        } catch (Exception e) {
            new SentException().sent(resp, e);
        }
    }
}
