package seoultech.startapp.rent.adapter.out;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import seoultech.startapp.member.adapter.out.JpaMember;
import seoultech.startapp.rent.application.port.out.CountRentPort;
import seoultech.startapp.rent.application.port.out.LoadRentPort;
import seoultech.startapp.rent.application.port.out.SaveRentPort;
import seoultech.startapp.rent.domain.ItemCategory;
import seoultech.startapp.rent.domain.Rent;
import seoultech.startapp.rent.domain.RentStatus;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class RentPersistenceAdapter implements SaveRentPort, CountRentPort, LoadRentPort {

  private final JpaRentQueryRepository jpaRentQueryRepository;
  private final JpaRentRepository jpaRentRepository;
  private final RentMapper rentMapper;
  private final RenterRepository renterRepository;
  private final RenterMapper renterMapper;

  @Override
  public void save(Rent rent) {
    JpaRent jpaRent = rentMapper.mapToJpaRent(rent);
    jpaRentRepository.save(jpaRent);
  }

  @Override
  public long countIncludingStartTime(LocalDate startTime, ItemCategory itemCategory) {
    return jpaRentQueryRepository.countIncludingStartTime(startTime, itemCategory);
  }

  @Override
  public long countIncludingEndTIme(LocalDate endTime, ItemCategory itemCategory) {
    return jpaRentQueryRepository.countIncludingEndTime(endTime, itemCategory);
  }

  @Override
  public Page<Rent> loadByPaging(PageRequest pageRequest, RentStatus status) {
    return jpaRentQueryRepository.findAllByRentStatusOrderByStartTimeDesc(pageRequest, status)
        .map(rent -> {
          JpaMember jpaMember = renterRepository.findByMemberId(
              rent.getMemberId());
          return rentMapper.mapToDomainRentWithRenter(rent, renterMapper.toRenter(jpaMember));
        });
  }

  @Override
  public Rent loadById(Long rentId) {
    JpaRent jpaRent = jpaRentRepository.findById(rentId)
        .orElseThrow(() -> new NotFoundRentException("rentId에 해당하는 rent가 없습니다"));
    return rentMapper.mapToDomainRent(jpaRent);
  }

  @Override
  public Rent loadByIdWithRenter(Long rentId) {
    JpaRent jpaRent = jpaRentRepository.findById(rentId)
        .orElseThrow(() -> new NotFoundRentException("rentId에 해당하는 rent가 없습니다."));
    JpaMember jpaMember = renterRepository.findByMemberId(jpaRent.getMemberId());

    return rentMapper.mapToDomainRentWithRenter(jpaRent, renterMapper.toRenter(jpaMember));
  }

  @Override
  public List<Rent> loadListByMemberId(Long memberId) {
    List<JpaRent> rentList = jpaRentRepository.findAllByMemberId(memberId);
    return rentList.stream().map(rentMapper::mapToDomainRent).collect(Collectors.toList());
  }

  @Override
  public List<Rent> loadListByYearMonthCategory(int year, int month, ItemCategory category) {
    return null;
  }
}
