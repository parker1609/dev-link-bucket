package com.codemcd.myarchive.web.controller;

import com.codemcd.myarchive.service.dto.LinkResponseDto;
import com.codemcd.myarchive.service.dto.LinkResponseDtoList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class LinksReadControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("메인 화면에서 조회 시(기본 조회) 시간 순으로 조회한다.(최신순)")
    void default_read() {
        LinkResponseDtoList linkResponseDtos = webTestClient.get()
                .uri("/api/links")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(LinkResponseDtoList.class)
                .returnResult()
                .getResponseBody()
                ;

        assert linkResponseDtos != null;
        List<LinkResponseDto> linkResponses = linkResponseDtos.getLinkResponseDtos();
        assertThat(linkResponses).hasSize(6);
        assertThat(linkResponses.get(0).getCreatedAt())
                .isEqualTo(LocalDateTime.of(2020, 2, 15, 1, 0, 0));
        assertThat(linkResponses.get(1).getCreatedAt())
                .isEqualTo(LocalDateTime.of(2020, 2, 14, 1, 0, 0));
        assertThat(linkResponses.get(2).getCreatedAt())
                .isEqualTo(LocalDateTime.of(2020, 2, 13, 1, 0, 0));
        assertThat(linkResponses.get(3).getCreatedAt())
                .isEqualTo(LocalDateTime.of(2020, 2, 12, 1, 0, 0));
        assertThat(linkResponses.get(4).getCreatedAt())
                .isEqualTo(LocalDateTime.of(2020, 2, 11, 1, 0, 0));
        assertThat(linkResponses.get(5).getCreatedAt())
                .isEqualTo(LocalDateTime.of(2020, 2, 10, 1, 0, 0));
    }
}
