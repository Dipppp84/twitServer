package ua.alextwit.twitserver;

import ua.alextwit.twitserver.model.dao.TwitDaoImpl;
import ua.alextwit.twitserver.model.dto.TwitDto;
import ua.alextwit.twitserver.model.entity.Twit;
import ua.alextwit.twitserver.model.service.TwitService;
import ua.alextwit.twitserver.model.service.converter.TwitConvert;
import ua.alextwit.twitserver.utils.gson.JsonUtil;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        TwitService twitService = TwitService.getTwitService();
        for (int i = 0; i < 25; i++) {

            twitService.save(new Twit().setMessage("qwe" + i + 1));
        }
    }
}
