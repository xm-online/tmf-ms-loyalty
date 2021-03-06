package com.icthh.xm.tmf.ms.loyalty.web.rest;

import com.icthh.xm.commons.lep.LogicExtensionPoint;
import com.icthh.xm.commons.lep.spring.LepService;
import com.icthh.xm.tmf.ms.loyalty.lep.keyresolver.ProfileChannelKeyResolver;
import com.icthh.xm.tmf.ms.loyalty.web.api.LoyaltyAccountApiDelegate;
import com.icthh.xm.tmf.ms.loyalty.web.api.model.LoyaltyEarnDTO;
import com.icthh.xm.tmf.ms.loyalty.web.api.model.LoyaltyTransactionRef;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@LepService(group = "methods")
public class LoyaltyAccountApiImpl implements LoyaltyAccountApiDelegate {

    @LogicExtensionPoint(value = "earnLoyaltyBalance", resolver = ProfileChannelKeyResolver.class)
    @Override
    public ResponseEntity<LoyaltyTransactionRef> earnLoyaltyBalance(String accountId,
                                                                    String balanceId,
                                                                    LoyaltyEarnDTO loyaltyEarnDTO) {
        return null;
    }
}
