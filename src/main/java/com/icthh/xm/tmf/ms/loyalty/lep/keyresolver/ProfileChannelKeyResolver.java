package com.icthh.xm.tmf.ms.loyalty.lep.keyresolver;

import com.icthh.xm.commons.lep.AppendLepKeyResolver;
import com.icthh.xm.lep.api.LepManagerService;
import com.icthh.xm.lep.api.LepMethod;
import com.icthh.xm.lep.api.commons.SeparatorSegmentedLepKey;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class ProfileChannelKeyResolver extends AppendLepKeyResolver {

    @Override
    protected String[] getAppendSegments(SeparatorSegmentedLepKey baseKey,
                                         LepMethod method,
                                         LepManagerService managerService) {

        String profile   = getProfileIdFromRequestHeader();
        profile   = translateToLepConvention(profile);
        return new String[]{profile};
    }

    private String getProfileIdFromRequestHeader() {
        String result = null;
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            result = request.getHeader("profileId");
        }
        return result;
    }
}
