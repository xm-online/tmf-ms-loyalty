package com.icthh.xm.tmf.ms.loyalty.web.rest;

import com.icthh.xm.commons.lep.LogicExtensionPoint;
import com.icthh.xm.commons.permission.annotation.PrivilegeDescription;
import com.icthh.xm.commons.lep.spring.LepService;
import com.icthh.xm.tmf.ms.loyalty.lep.keyresolver.ProfileChannelKeyResolver;
import com.icthh.xm.tmf.ms.loyalty.web.api.LoyaltyProgramMemberApiDelegate;
import com.icthh.xm.tmf.ms.loyalty.web.api.model.ProductProgramRef;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@LepService(group = "methods")
public class LoyaltyProgramMemberApiImpl implements LoyaltyProgramMemberApiDelegate {

    @PreAuthorize("hasPermission({'memberId': #memberId}, 'LOYALTY.MEMBER.PRODUCT.GET_LIST')")
    @PrivilegeDescription("Privilege to get loyalty program member products")
    @LogicExtensionPoint(value = "ListProducts", resolver = ProfileChannelKeyResolver.class)
    @Override
    public ResponseEntity<List<ProductProgramRef>> listProducts(String memberId) {
        return ResponseEntity.ok(List.of());
    }
}
