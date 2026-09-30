package com.icthh.xm.tmf.ms.loyalty.lep.keyresolver;

import com.icthh.xm.lep.api.LepKeyResolver;
import com.icthh.xm.lep.api.LepMethod;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Appends the {@code profile} request header to the LEP key, e.g. {@code earnLoyaltyBalance$$B2C}.
 * xm-commons 5 also looks up the legacy script name ({@code -} to {@code _}, {@code .} to {@code $}),
 * which the xm-commons 2 resolver used to build.
 */
@Component
public class ProfileChannelKeyResolver implements LepKeyResolver {

    @Override
    public List<String> segments(LepMethod method) {
        // as before: a request without the profile header fails, the xm-commons 2 resolver required it too
        String profile = Objects.requireNonNull(getProfileIdFromRequestHeader(), "xmEntitySpecKey can't be null");
        return List.of(profile);
    }

    private String getProfileIdFromRequestHeader() {
        String result = null;
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes servletRequestAttributes) {
            HttpServletRequest request = servletRequestAttributes.getRequest();
            result = request.getHeader("profile");
        }
        return result;
    }
}
