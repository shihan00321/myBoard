package hello.myBoard.dto.article;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticlesDto {
    private Long id;
    private String title;
    private String tag;
    private String createdBy;
    private LocalDateTime createdAt;

    public ArticlesDto(Long id, String title, String tag, String createdBy, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.tag = tag;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }
}
