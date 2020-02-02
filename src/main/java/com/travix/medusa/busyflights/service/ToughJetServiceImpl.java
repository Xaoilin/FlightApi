package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetService;
import org.springframework.stereotype.Service;

@Service
public class ToughJetServiceImpl implements ToughJetService {

    @Override
    public ToughJetResponse getAvailableFlights(ToughJetRequest request) {
        return null;
    }
}
