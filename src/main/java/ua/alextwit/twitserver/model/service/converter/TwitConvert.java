package ua.alextwit.twitserver.model.service.converter;

import ua.alextwit.twitserver.model.dto.TwitDto;
import ua.alextwit.twitserver.model.entity.Twit;

public class TwitConvert {
    public Twit convertDtoToTwit(TwitDto twitDto) {
        return new Twit().setId(twitDto.getId())
                .setMessage(twitDto.getMessage())
                .setHeart(twitDto.getHeart())
                .setImportant(twitDto.getImportant())
                .setDateOfCreation(twitDto.getDateOfCreation());
    }

    public TwitDto convertTwitToDto(Twit twit) {
        return new TwitDto().setId(twit.getId())
                .setMessage(twit.getMessage())
                .setHeart(twit.getHeart())
                .setImportant(twit.getImportant());
    }
}
