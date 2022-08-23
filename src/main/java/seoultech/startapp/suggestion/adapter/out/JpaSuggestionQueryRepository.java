package seoultech.startapp.suggestion.adapter.out;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import seoultech.startapp.suggestion.domain.SuggestionCategory;

import static seoultech.startapp.suggestion.adapter.out.QJpaSuggestion.jpaSuggestion;

@Repository
@RequiredArgsConstructor
public class JpaSuggestionQueryRepository {

  private final JPAQueryFactory query;

  public Page<JpaSuggestion> findAllOrderByIsCheck(Pageable pageable, SuggestionCategory category){
    List<JpaSuggestion> result = query.selectFrom(jpaSuggestion)
        .where(suggestionCategoryEq(category))
        .orderBy(jpaSuggestion.isCheck.asc())
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();
    return new PageImpl<>(result, pageable, result.size());
  }

  private BooleanExpression suggestionCategoryEq(SuggestionCategory suggestionCategory) {
    return suggestionCategory == SuggestionCategory.ALL ? null : jpaSuggestion.category.eq(suggestionCategory);
  }
}
