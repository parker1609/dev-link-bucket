package com.codemcd.myarchive.web.controller;

import com.codemcd.myarchive.service.dto.LinkRequestDto;
import com.codemcd.myarchive.service.dto.LinkResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class LinkControllerTest {
    private static final String TEST_URI = "https://velog.io/@codemcd/Sync-VS-Async-Blocking-VS-Non-Blocking-sak6d01fhx";
    private static final String TEST_TITLE = "Sync VS Async, Blocking VS Non-Blocking";
    private static final String TEST_TAG1 = "Sync";
    private static final String TEST_TAG2 = "Async";
    private static final String TEST_TAG3 = "Blocking";
    private static final String TEST_TAG4 = "Non-Blocking";
    private static final String TEST_TYPE = "글";

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("Link 등록기능 인수테스트가 정상적으로 동작하는지 확인합니다.")
    void Link_Create() {
        List<String> tags = Arrays.asList(TEST_TAG1, TEST_TAG2, TEST_TAG3, TEST_TAG4);
        LinkRequestDto linkRequestDto = new LinkRequestDto(TEST_URI, TEST_TITLE, tags, TEST_TYPE);

        LinkResponseDto linkResponseDto = webTestClient.post()
                .uri("/api/links")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(linkRequestDto), LinkRequestDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(LinkResponseDto.class)
                .returnResult()
                .getResponseBody()
                ;

        assert linkResponseDto != null;
        assertThat(linkResponseDto.getId()).isEqualTo(1L);
        assertThat(linkResponseDto.getUri()).isEqualTo(TEST_URI);
        assertThat(linkResponseDto.getTitle()).isEqualTo(TEST_TITLE);
        assertThat(linkResponseDto.getTags()).hasSize(4);
        assertThat(linkResponseDto.getType()).isEqualTo(TEST_TYPE);
    }
}
