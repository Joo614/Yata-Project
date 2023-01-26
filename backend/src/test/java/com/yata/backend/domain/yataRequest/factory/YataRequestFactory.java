package com.yata.backend.domain.yataRequest.factory;

import com.yata.backend.common.utils.RandomUtils;
import com.yata.backend.domain.Yata.factory.YataFactory;
import com.yata.backend.domain.member.entity.Member;
import com.yata.backend.domain.member.factory.MemberFactory;
import com.yata.backend.domain.yata.dto.LocationDto;
import com.yata.backend.domain.yata.dto.YataRequestDto;
import com.yata.backend.domain.yata.entity.Location;
import com.yata.backend.domain.yata.entity.Yata;
import com.yata.backend.domain.yata.entity.YataRequest;
import com.yata.backend.global.utils.GeometryUtils;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.yata.backend.common.utils.RandomUtils.*;

public class YataRequestFactory {
    // Request / Invitation 모두 이거 사용
    public static YataRequest createYataRequest() throws org.locationtech.jts.io.ParseException {
        Yata yata = YataFactory.createYata();

        return YataRequest.builder()
                .YataRequestId(getRandomLong())
                .requestStatus(YataRequest.RequestStatus.APPLY)
                .approvalStatus(YataRequest.ApprovalStatus.NOT_YET)
                .title(getRandomWord())
                .specifics(getRandomWord())
                .departureTime(getRandomDate())
                .timeOfArrival(getRandomDate())
                .boardingPersonCount(2)
                .maxWaitingTime(10)
                .yata(yata)
                .strPoint(new Location(RandomUtils.getRandomLong() , GeometryUtils.getEmptyPoint() , getRandomWord() , null))
                .destination(new Location(RandomUtils.getRandomLong() , GeometryUtils.getEmptyPoint() , getRandomWord() , null))
                .build();
    }

    public static YataRequestDto.RequestPost createYataRequestPostDto() {
        LocationDto.Post strPoint = new LocationDto.Post(2.5, 2.0, "강원도 원주시");
        LocationDto.Post destination = new LocationDto.Post(2.5, 2.0, "강원도 원주시");

        return YataRequestDto.RequestPost.builder()
                .title("태워주세욥")
                .specifics("애완견을 동반하고싶어요")
                .departureTime(new Date())
                .timeOfArrival(new Date())
                .boardingPersonCount(2)
                .maxWaitingTime(10)
                .strPoint(strPoint)
                .destination(destination)
                .build();
    }

    public static YataRequestDto.RequestResponse createYataRequestResponseDto(YataRequest yataRequest) {
        Member member = MemberFactory.createMember(RandomUtils.getRandomWord(10)+"@gmail.com");
        yataRequest.setMember(member);
        return YataRequestDto.RequestResponse.builder()
                .yataId(yataRequest.getYata().getYataId())
                .yataRequestId(yataRequest.getYataRequestId())
                .yataRequestStatus(yataRequest.getRequestStatus())
                .approvalStatus(yataRequest.getApprovalStatus())
                .title(yataRequest.getTitle())
                .specifics(yataRequest.getSpecifics())
                .departureTime(yataRequest.getDepartureTime())
                .timeOfArrival(yataRequest.getTimeOfArrival())
                .boardingPersonCount(yataRequest.getBoardingPersonCount())
                .maxWaitingTime(yataRequest.getMaxWaitingTime())
                .strPoint(new LocationDto.Response(
                        yataRequest.getStrPoint().getLocation().getX(),
                        yataRequest.getStrPoint().getLocation().getY(),
                        yataRequest.getStrPoint().getAddress()))
                .destination(new LocationDto.Response(
                        yataRequest.getDestination().getLocation().getX(),
                        yataRequest.getDestination().getLocation().getY(),
                        yataRequest.getDestination().getAddress()))
                .createdAt(LocalDateTime.now())
                .imgUrl(yataRequest.getMember().getImgUrl() != null ? yataRequest.getMember().getImgUrl().getUrl() : null)
                .build();
    }

    public static YataRequestDto.InvitationResponse createYataInvitationResponseDto(YataRequest yataRequest) {
        return YataRequestDto.InvitationResponse.builder()
                .yataRequestId(yataRequest.getYataRequestId())
                .yataId(yataRequest.getYata().getYataId())
                .yataRequestStatus(yataRequest.getRequestStatus())
                .approvalStatus(yataRequest.getApprovalStatus())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static List<YataRequest> createYataRequestList() throws ParseException, org.locationtech.jts.io.ParseException {
        List<YataRequest> yataRequestList = new ArrayList<>();
        for(int i=0; i<10; i++){
            yataRequestList.add(createYataRequest());
        }
        return yataRequestList;
    }

    public static List<YataRequestDto.RequestResponse> createYataRquestResponseDtoList(List<YataRequest> yataRquestsList){
        List<YataRequestDto.RequestResponse> yataRequestResponseDtoList = new ArrayList<>();
        for(YataRequest yataRequest : yataRquestsList){
            yataRequestResponseDtoList.add(createYataRequestResponseDto(yataRequest));
        }
        return yataRequestResponseDtoList;
    }
}
