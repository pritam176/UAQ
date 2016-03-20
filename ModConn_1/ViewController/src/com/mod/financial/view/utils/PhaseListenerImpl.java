package com.mod.financial.view.utils;


import com.mod.financial.view.utils.JSFUtils;

import com.mod.financial.view.utils.UserInfo;

import java.util.Locale;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class PhaseListenerImpl implements PhaseListener {
    public PhaseListenerImpl() {

    }

    @Override
    public void afterPhase(PhaseEvent phaseEvent) {

    }

    @Override
    public void beforePhase(PhaseEvent phaseEvent) {
        UserInfo userInfo =
            (UserInfo)JSFUtils.resolveExpression("#{userInfo}");
        if (userInfo != null) {
            String locale = userInfo.getLanguage();
            phaseEvent.getFacesContext().getViewRoot().setLocale(new Locale(locale));


        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }


}

