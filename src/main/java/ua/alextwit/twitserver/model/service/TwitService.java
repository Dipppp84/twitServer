package ua.alextwit.twitserver.model.service;

import ua.alextwit.twitserver.model.dao.TwitDaoImpl;
import ua.alextwit.twitserver.model.dto.PostsAndHeartsDto;
import ua.alextwit.twitserver.model.entity.Twit;

import java.time.LocalDate;
import java.util.List;

public class TwitService {
    private static volatile TwitService twitService;
    private final TwitDaoImpl twitDao = TwitDaoImpl.getTwitDao();

    private TwitService() {
    }

    public synchronized static TwitService getTwitService() {
        if (twitService == null)
            twitService = new TwitService();
        return twitService;
    }

    public Twit save(Twit twit) {
        twit.setDateOfCreation(LocalDate.now());
        return twitDao.save(twit);
    }

    public Twit findById(Long id) {
        Twit twit = null;
        try {
            twit = twitDao.findById(id).orElseThrow();
        } catch (Exception e) {
            throw new RuntimeException("Can't find Twit");
        }
        return twit;
    }

    public List<Twit> getAll() {
        return twitDao.getAll();
    }

    public Twit update(Twit twit) {
        return twitDao.update(twit);
    }

    public List<Twit> updateAll(List<Twit> updatedTwits) {
        return twitDao.updateAll(updatedTwits);
    }

    public void delete(Long id) {
        twitDao.delete(id);
    }

    public PostsAndHeartsDto getPostCount(){
        return twitDao.getPostCount();
    }
}
