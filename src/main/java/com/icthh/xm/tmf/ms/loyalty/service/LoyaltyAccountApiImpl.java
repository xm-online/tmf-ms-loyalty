package com.icthh.xm.tmf.ms.loyalty.service;

import com.icthh.xm.commons.lep.LogicExtensionPoint;
import com.icthh.xm.commons.lep.spring.LepService;
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

    @LogicExtensionPoint(value = "earnLoyaltyBalance")
    public ResponseEntity<LoyaltyTransactionRef> earnLoyaltyBalance(String profileId,
                                                                    String accountId,
                                                                    String balanceId,
                                                                    LoyaltyEarnDTO loyaltyEarnDTO){
        return null;
    }
}
