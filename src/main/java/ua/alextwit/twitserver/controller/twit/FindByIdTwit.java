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

@WebServlet("/twit/find-by-twit")
public class FindByIdTwit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Object oId = req.getParameter("id");
            Long id = Long.parseLong(oId.toString());
            Twit twit = TwitService.getTwitService().findById(id);
            TwitDto twitDto = new TwitConvert().convertTwitToDto(twit);
            resp.getWriter().write(new JsonUtil().getGson().toJson(twitDto));
        } catch (Exception e) {
            new SentException().sent(resp, e);
        }
    }
}
