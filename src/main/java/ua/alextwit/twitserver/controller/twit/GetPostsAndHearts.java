package ua.alextwit.twitserver.controller.twit;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.alextwit.twitserver.controller.SentException;
import ua.alextwit.twitserver.model.dto.PostsAndHeartsDto;
import ua.alextwit.twitserver.model.dto.TwitDto;
import ua.alextwit.twitserver.model.entity.Twit;
import ua.alextwit.twitserver.model.service.TwitService;
import ua.alextwit.twitserver.model.service.converter.TwitConvert;
import ua.alextwit.twitserver.utils.gson.JsonUtil;

import java.io.IOException;
import java.util.List;

@WebServlet("/twit/get-posts-and-hearts")
public class GetPostsAndHearts extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            PostsAndHeartsDto postsAndHeartsDto = TwitService.getTwitService().getPostCount();
            resp.getWriter().write(new JsonUtil().getGson().toJson(postsAndHeartsDto));
        } catch (Exception e) {
            new SentException().sent(resp, e);
        }
    }
}
