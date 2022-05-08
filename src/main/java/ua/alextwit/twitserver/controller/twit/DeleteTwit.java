package ua.alextwit.twitserver.controller.twit;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.alextwit.twitserver.controller.SentException;
import ua.alextwit.twitserver.model.service.TwitService;

import java.io.IOException;

@WebServlet("/twit/delete-twit")
public class DeleteTwit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Object oId = req.getParameter("id");
            Long id = Long.parseLong(oId.toString());
            TwitService.getTwitService().delete(id);
        } catch (Exception e) {
            new SentException().sent(resp, e);
        }
    }
}
