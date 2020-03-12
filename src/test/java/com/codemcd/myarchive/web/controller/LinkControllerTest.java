package com.codemcd.myarchive.web.controller;

import com.codemcd.myarchive.service.dto.LinkRequestDto;
import com.codemcd.myarchive.service.dto.LinkResponseDto;
import com.codemcd.myarchive.web.support.error.ErrorCode;
import com.codemcd.myarchive.web.support.error.ErrorResponseDto;
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

    private static final String UPDATE_URI = "www.naver.com";

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("Link 등록기능 인수테스트가 정상적으로 동작하는지 확인합니다.")
    void create() {
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
        assertThat(linkResponseDto.getUri()).isEqualTo(TEST_URI);
        assertThat(linkResponseDto.getTitle()).isEqualTo(TEST_TITLE);
        assertThat(linkResponseDto.getTags()).hasSize(4);
        assertThat(linkResponseDto.getType()).isEqualTo(TEST_TYPE);
    }

    @Test
    @DisplayName("Link를 등록할 때 URI가 null인 경우 404 에러가 발생한다.")
    void create_empty_uri_error_1() {
        List<String> tags = Arrays.asList(TEST_TAG1, TEST_TAG2, TEST_TAG3, TEST_TAG4);
        LinkRequestDto linkRequestDto = new LinkRequestDto(null, TEST_TITLE, tags, TEST_TYPE);

        ErrorResponseDto error = webTestClient.post()
                .uri("/api/links")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(linkRequestDto), LinkResponseDto.class)
                .exchange()
                .expectStatus().is4xxClientError()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(ErrorResponseDto.class)
                .returnResult()
                .getResponseBody()
                ;

        assert error != null;
        assertThat(error.getStatus()).isEqualTo(ErrorCode.INVALID_INPUT_VALUE.getStatus());
        assertThat(error.getMessage()).isEqualTo(ErrorCode.INVALID_INPUT_VALUE.getMessage());
        assertThat(error.getCode()).isEqualTo(ErrorCode.INVALID_INPUT_VALUE.getErrorCode());
        assertThat(error.getErrors()).hasSize(1);
        assertThat(error.getErrors().get(0).getReason()).isEqualTo("URI를 입력해주세요.");
    }

    @Test
    @DisplayName("Link를 등록할 때 URI가 ''인 경우 404 에러가 발생한다.")
    void create_empty_uri_error_2() {
        List<String> tags = Arrays.asList(TEST_TAG1, TEST_TAG2, TEST_TAG3, TEST_TAG4);
        LinkRequestDto linkRequestDto = new LinkRequestDto("", TEST_TITLE, tags, TEST_TYPE);

        ErrorResponseDto error = webTestClient.post()
                .uri("/api/links")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(linkRequestDto), LinkResponseDto.class)
                .exchange()
                .expectStatus().is4xxClientError()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(ErrorResponseDto.class)
                .returnResult()
                .getResponseBody()
                ;

        assert error != null;
        assertThat(error.getStatus()).isEqualTo(ErrorCode.INVALID_INPUT_VALUE.getStatus());
        assertThat(error.getMessage()).isEqualTo(ErrorCode.INVALID_INPUT_VALUE.getMessage());
        assertThat(error.getCode()).isEqualTo(ErrorCode.INVALID_INPUT_VALUE.getErrorCode());
        assertThat(error.getErrors()).hasSize(1);
        assertThat(error.getErrors().get(0).getReason()).isEqualTo("URI를 입력해주세요.");
    }

    @Test
    @DisplayName("Link를 등록할 때 Title이 null인 경우 404 에러가 발생한다.")
    void create_empty_title_error_1() {
        List<String> tags = Arrays.asList(TEST_TAG1, TEST_TAG2, TEST_TAG3, TEST_TAG4);
        LinkRequestDto linkRequestDto = new LinkRequestDto(TEST_URI, null, tags, TEST_TYPE);

        ErrorResponseDto error = webTestClient.post()
                .uri("/api/links")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(linkRequestDto), LinkResponseDto.class)
                .exchange()
                .expectStatus().is4xxClientError()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(ErrorResponseDto.class)
                .returnResult()
                .getResponseBody()
                ;

        assert error != null;
        assertThat(error.getStatus()).isEqualTo(ErrorCode.INVALID_INPUT_VALUE.getStatus());
        assertThat(error.getMessage()).isEqualTo(ErrorCode.INVALID_INPUT_VALUE.getMessage());
        assertThat(error.getCode()).isEqualTo(ErrorCode.INVALID_INPUT_VALUE.getErrorCode());
        assertThat(error.getErrors()).hasSize(1);
        assertThat(error.getErrors().get(0).getReason()).isEqualTo("제목을 입력해주세요.");
    }

    @Test
    @DisplayName("Link를 등록할 때 Title이 ''인 경우 404 에러가 발생한다.")
    void create_empty_title_error_2() {
        List<String> tags = Arrays.asList(TEST_TAG1, TEST_TAG2, TEST_TAG3, TEST_TAG4);
        LinkRequestDto linkRequestDto = new LinkRequestDto(TEST_URI, "", tags, TEST_TYPE);

        ErrorResponseDto error = webTestClient.post()
                .uri("/api/links")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(linkRequestDto), LinkResponseDto.class)
                .exchange()
                .expectStatus().is4xxClientError()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(ErrorResponseDto.class)
                .returnResult()
                .getResponseBody()
                ;

        assert error != null;
        assertThat(error.getStatus()).isEqualTo(ErrorCode.INVALID_INPUT_VALUE.getStatus());
        assertThat(error.getMessage()).isEqualTo(ErrorCode.INVALID_INPUT_VALUE.getMessage());
        assertThat(error.getCode()).isEqualTo(ErrorCode.INVALID_INPUT_VALUE.getErrorCode());
        assertThat(error.getErrors()).hasSize(1);
        assertThat(error.getErrors().get(0).getReason()).isEqualTo("제목을 입력해주세요.");
    }

    @Test
    @DisplayName("Link를 등록할 때 Type이 존재하지 않는 경우 404 에러가 발생한다.")
    void create_not_found_LinkType_error() {
        List<String> tags = Arrays.asList(TEST_TAG1, TEST_TAG2, TEST_TAG3, TEST_TAG4);
        LinkRequestDto linkRequestDto = new LinkRequestDto(TEST_URI, TEST_TITLE, tags, "none");

        ErrorResponseDto error = webTestClient.post()
                .uri("/api/links")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(linkRequestDto), LinkResponseDto.class)
                .exchange()
                .expectStatus().is4xxClientError()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(ErrorResponseDto.class)
                .returnResult()
                .getResponseBody()
                ;

        assert error != null;
        assertThat(error.getStatus()).isEqualTo(ErrorCode.INVALID_INPUT_VALUE.getStatus());
        assertThat(error.getMessage()).isEqualTo(ErrorCode.INVALID_INPUT_VALUE.getMessage());
        assertThat(error.getCode()).isEqualTo(ErrorCode.INVALID_INPUT_VALUE.getErrorCode());
    }

    @Test
    @DisplayName("Link를 URI를 정상적으로 수정한다.")
    void update() {
        // given
        List<String> tags = Arrays.asList(TEST_TAG1, TEST_TAG2, TEST_TAG3, TEST_TAG4);
        LinkRequestDto linkRequest = new LinkRequestDto(TEST_URI, TEST_TITLE, tags, TEST_TYPE);

        LinkResponseDto linkResponse = webTestClient.post()
                .uri("/api/links")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(linkRequest), LinkRequestDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(LinkResponseDto.class)
                .returnResult()
                .getResponseBody()
                ;

        // when
        assert linkResponse != null;
        Long createdLinkId = linkResponse.getId();

        LinkRequestDto updateUriLinkRequest = new LinkRequestDto(UPDATE_URI,
                linkRequest.getTitle(), linkRequest.getTags(), linkRequest.getType());
        LinkResponseDto updatedLinkResponse = webTestClient.put()
                .uri("/api/links/" + createdLinkId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(updateUriLinkRequest), LinkRequestDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(LinkResponseDto.class)
                .returnResult()
                .getResponseBody()
                ;

        // then
        assert updatedLinkResponse != null;
        assertThat(updatedLinkResponse.getId()).isEqualTo(createdLinkId);
        assertThat(updatedLinkResponse.getUri()).isEqualTo(UPDATE_URI);
        assertThat(updatedLinkResponse.getTitle()).isEqualTo(linkRequest.getTitle());
    }
}
