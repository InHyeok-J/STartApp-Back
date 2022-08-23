package seoultech.startapp.suggestion.adapter.out;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import seoultech.startapp.global.common.BaseTimeJpaEntity;
import seoultech.startapp.global.converter.BooleanToYNConverter;
import seoultech.startapp.suggestion.domain.SuggestionCategory;

@Entity(name = "suggestion")
@Getter
@NoArgsConstructor
public class JpaSuggestion extends BaseTimeJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "suggestion_id")
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false, columnDefinition = "varchar(500)")
  private String content;

  @Column(nullable = false)
  @Convert(converter = BooleanToYNConverter.class)
  private Boolean isCheck;

  private String imageUrl;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private SuggestionCategory category;

  @Builder
  public JpaSuggestion(Long id, String title, String content, Boolean isCheck,
      String imageUrl, SuggestionCategory category, LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.isCheck = isCheck;
    this.imageUrl = imageUrl;
    this.category = category;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}
