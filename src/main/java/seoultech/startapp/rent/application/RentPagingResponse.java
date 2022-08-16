package seoultech.startapp.rent.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RentPagingResponse {

    private int totalPage;

    private List<RentResponse> RentList;
}
