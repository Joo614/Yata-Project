package com.yata.backend.domain.Yata.factory;

import com.yata.backend.domain.yata.dto.YataDto;
import com.yata.backend.domain.yata.entity.Yata;

import java.util.Date;


public class YataFactory {

    public static YataDto.YataPost createNeotaPostDto(){

        return YataDto.YataPost.builder()
                .title("부산까지 같이가실 분~")
                .content("같이 노래들으면서 가요~")
                .departureTime("2023-01-10 21:04:00")
                .timeOfArrival("2023-01-10 21:04:00")
                .amount(2000)
                .carModel("bmw")
                .maxPeople(3)
                .maxWaitingTime(20)
                .build();
    }

    public static YataDto.Response createYataResponseDto(Yata yata){
        return YataDto.Response.builder()
                .title(yata.getTitle())
                .content(yata.getContent())
                .timeOfArrival(String.valueOf(yata.getTimeOfArrival()))
                .departureTime(String.valueOf(yata.getDepartureTime()))
                .amount(yata.getAmount())
                .carModel(yata.getCarModel())
                .maxPeople(yata.getMaxPeople())
                .maxWaitingTime(yata.getMaxWaitingTime())
                .build();
    }
}
