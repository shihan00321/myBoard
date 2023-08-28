package hello.myBoard.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

@DisplayName("Pagination")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = PaginationService.class)
class PaginationServiceTest {
    private final PaginationService paginationService;

    @Autowired
    public PaginationServiceTest(PaginationService paginationService) {
        this.paginationService = paginationService;
    }

    @DisplayName("현재 페이지 번호, 총 페이지 수로 페이징 바 리스트 반환 메서드 테스트")
    @MethodSource
    @ParameterizedTest
    void test(int currentPageNumber, int totalPages, List<Integer> expected) {
        List<Integer> actual = paginationService.getPaginationNumbers(currentPageNumber, totalPages);
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void name() {
    }

    static Stream<Arguments> test() {
        return Stream.of(
                arguments(0, 10, List.of(0, 1, 2, 3, 4)),
                arguments(1, 10, List.of(0, 1, 2, 3, 4)),
                arguments(2, 10, List.of(0, 1, 2, 3, 4)),
                arguments(3, 10, List.of(1, 2, 3, 4, 5)),
                arguments(4, 10, List.of(2, 3, 4, 5, 6)),
                arguments(7, 10, List.of(5, 6, 7, 8, 9)),
                arguments(8, 10, List.of(6, 7, 8, 9)),
                arguments(9, 10, List.of(7, 8, 9))
        );
    }
}