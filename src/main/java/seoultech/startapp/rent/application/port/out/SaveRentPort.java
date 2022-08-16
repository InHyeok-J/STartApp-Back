package seoultech.startapp.rent.application.port.out;

import seoultech.startapp.rent.domain.Rent;

public interface SaveRentPort {

    void save(Rent rent);
}
