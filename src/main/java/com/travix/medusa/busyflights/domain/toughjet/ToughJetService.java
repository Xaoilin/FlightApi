package com.travix.medusa.busyflights.domain.toughjet;

import java.util.List;

public interface ToughJetService {
    List<ToughJetResponse> getAvailableFlights(ToughJetRequest request);

}
