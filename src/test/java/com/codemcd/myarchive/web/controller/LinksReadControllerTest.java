package com.codemcd.myarchive.web.controller;

import com.codemcd.myarchive.service.dto.LinkDeleteResponseDto;
import com.codemcd.myarchive.service.dto.LinkRequestDto;
import com.codemcd.myarchive.service.dto.LinkResponseDto;
import com.codemcd.myarchive.service.dto.LinkResponseDtoList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class LinksReadControllerTest {
    private static final String TYPE = "글";
    private static final String TAG_1 = "tag1";
    private static final String TAG_2 = "tag2";
    private static final String TAG_3 = "tag3";

    private List<Long> linkIds = new ArrayList<>();

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void setup() {
        List<String> tags1 = Collections.singletonList(TAG_1);
        LinkRequestDto linkRequestDto = new LinkRequestDto("www.park1.com", "link1", tags1, TYPE);
        LinkResponseDto response1 = webTestClient.post()
                .uri("/api/links")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(linkRequestDto), LinkRequestDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(LinkResponseDto.class)
                .returnResult()
                .getResponseBody();
        assert response1 != null;
        linkIds.add(response1.getId());

        List<String> tags2 = Collections.singletonList(TAG_2);
        linkRequestDto = new LinkRequestDto("www.park2.com", "link2", tags2, TYPE);
        LinkResponseDto response2 = webTestClient.post()
                .uri("/api/links")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(linkRequestDto), LinkRequestDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(LinkResponseDto.class)
                .returnResult()
                .getResponseBody();
        assert response2 != null;
        linkIds.add(response2.getId());

        List<String> tags3 = Collections.singletonList(TAG_3);
        linkRequestDto = new LinkRequestDto("www.park3.com", "link3", tags3, TYPE);
        LinkResponseDto response3 = webTestClient.post()
                .uri("/api/links")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(linkRequestDto), LinkRequestDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(LinkResponseDto.class)
                .returnResult()
                .getResponseBody();
        assert response3 != null;
        linkIds.add(response3.getId());

        List<String> tags4 = Arrays.asList(TAG_1, TAG_2);
        linkRequestDto = new LinkRequestDto("www.park4.com", "link4", tags4, TYPE);
        LinkResponseDto response4 = webTestClient.post()
                .uri("/api/links")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(linkRequestDto), LinkRequestDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(LinkResponseDto.class)
                .returnResult()
                .getResponseBody();
        assert response4 != null;
        linkIds.add(response4.getId());

        List<String> tags5 = Arrays.asList(TAG_2, TAG_3);
        linkRequestDto = new LinkRequestDto("www.park5.com", "link5", tags5, TYPE);
        LinkResponseDto response5 = webTestClient.post()
                .uri("/api/links")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(linkRequestDto), LinkRequestDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(LinkResponseDto.class)
                .returnResult()
                .getResponseBody();
        assert response5 != null;
        linkIds.add(response5.getId());

        List<String> tags6 = Arrays.asList(TAG_1, TAG_2, TAG_3);
        linkRequestDto = new LinkRequestDto("www.park6.com", "link6", tags6, TYPE);
        LinkResponseDto response6 = webTestClient.post()
                .uri("/api/links")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(linkRequestDto), LinkRequestDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(LinkResponseDto.class)
                .returnResult()
                .getResponseBody();
        assert response6 != null;
        linkIds.add(response6.getId());
    }

    @AfterEach
    void deleteAll() {
        for (Long linkId : linkIds) {
            webTestClient.delete()
                    .uri("/api/links/" + linkId)
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .expectStatus().isOk()
                    .expectHeader().contentType(MediaType.APPLICATION_JSON)
                    .expectBody(LinkDeleteResponseDto.class)
                    .returnResult()
                    .getResponseBody();
        }
    }

    @Test
    @DisplayName("메인 화면에서 조회 시(기본 조회) 시간 순으로 조회한다.(최신순)")
    void default_read() {
        LinkResponseDtoList linkResponsesDto = webTestClient.get()
                .uri("/api/links")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(LinkResponseDtoList.class)
                .returnResult()
                .getResponseBody()
                ;

        assert linkResponsesDto != null;
        List<LinkResponseDto> linkResponses = linkResponsesDto.getLinkResponseDtos();
        assertThat(linkResponses).hasSize(6);
        assertThat(linkResponses.get(0).getTitle()).isEqualTo("link6");
        assertThat(linkResponses.get(1).getTitle()).isEqualTo("link5");
        assertThat(linkResponses.get(2).getTitle()).isEqualTo("link4");
        assertThat(linkResponses.get(3).getTitle()).isEqualTo("link3");
        assertThat(linkResponses.get(4).getTitle()).isEqualTo("link2");
        assertThat(linkResponses.get(5).getTitle()).isEqualTo("link1");
    }

    @Test
    @DisplayName("tag1 태그를 가지고 있는 Link 조회하기(최신순)")
    void read_tag1() {
        String tagName = "tag1";
        LinkResponseDtoList linkResponsesDto = webTestClient.get()
                .uri("/api/tags/" + tagName)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(LinkResponseDtoList.class)
                .returnResult()
                .getResponseBody()
                ;

        assert linkResponsesDto != null;
        List<LinkResponseDto> linkResponses = linkResponsesDto.getLinkResponseDtos();
        assertThat(linkResponses).hasSize(3);
        assertThat(linkResponses.get(0).getTitle()).isEqualTo("link6");
        assertThat(linkResponses.get(1).getTitle()).isEqualTo("link4");
        assertThat(linkResponses.get(2).getTitle()).isEqualTo("link1");
    }

    @Test
    @DisplayName("tag2 태그를 가지고 있는 Link 조회하기(최신순)")
    void read_tag2() {
        String tagName = "tag2";
        LinkResponseDtoList linkResponsesDto = webTestClient.get()
                .uri("/api/tags/" + tagName)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(LinkResponseDtoList.class)
                .returnResult()
                .getResponseBody()
                ;

        assert linkResponsesDto != null;
        List<LinkResponseDto> linkResponses = linkResponsesDto.getLinkResponseDtos();
        assertThat(linkResponses).hasSize(4);
        assertThat(linkResponses.get(0).getTitle()).isEqualTo("link6");
        assertThat(linkResponses.get(1).getTitle()).isEqualTo("link5");
        assertThat(linkResponses.get(2).getTitle()).isEqualTo("link4");
        assertThat(linkResponses.get(3).getTitle()).isEqualTo("link2");
    }

    @Test
    @DisplayName("tag3 태그를 가지고 있는 Link 조회하기(최신순)")
    void read_tag3() {
        String tagName = "tag3";
        LinkResponseDtoList linkResponsesDto = webTestClient.get()
                .uri("/api/tags/" + tagName)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(LinkResponseDtoList.class)
                .returnResult()
                .getResponseBody()
                ;

        assert linkResponsesDto != null;
        List<LinkResponseDto> linkResponses = linkResponsesDto.getLinkResponseDtos();
        assertThat(linkResponses).hasSize(3);
        assertThat(linkResponses.get(0).getTitle()).isEqualTo("link6");
        assertThat(linkResponses.get(1).getTitle()).isEqualTo("link5");
        assertThat(linkResponses.get(2).getTitle()).isEqualTo("link3");
    }
}
