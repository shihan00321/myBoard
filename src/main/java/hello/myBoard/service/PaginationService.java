package hello.myBoard.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PaginationService {

    private static final int BAR_LENGTH = 5; // 페이지 바의 개수는 5개
    public List<Integer> getPaginationNumbers(int currentPageNumber, int totalPages) {
        int startNumber = Math.max(0, currentPageNumber - (BAR_LENGTH / 2));
        int endNumber = Math.min(startNumber + BAR_LENGTH, totalPages);

        return IntStream.range(startNumber, endNumber).boxed().collect(Collectors.toList());
    }


}
