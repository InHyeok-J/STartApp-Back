package seoultech.startapp.festival.application;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.festival.application.port.in.DeleteBoothUseCase;
import seoultech.startapp.festival.application.port.out.DeleteBoothPort;
import seoultech.startapp.festival.application.port.out.LoadBoothPort;
import seoultech.startapp.festival.domain.Booth;

@Service
@RequiredArgsConstructor
public class DeleteBoothService implements DeleteBoothUseCase {

  private final LoadBoothPort loadBoothPort;
  private final DeleteBoothPort deleteBoothPort;

  @CacheEvict(value = "festival", allEntries = true)
  @Transactional
  @Override
  public void deleteBooth(Long boothId) {
    Booth booth = loadBoothPort.loadByBoothId(boothId);
    deleteBoothPort.deleteBoothBooth(booth);
  }
}
