package de.lv1871.messages;

import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

public class WicketWebApplication extends WebApplication {

    @Override
    public RuntimeConfigurationType getConfigurationType() {
        return RuntimeConfigurationType.DEVELOPMENT;
    }

    @Override
    public Class<? extends Page> getHomePage(){
        return MessagePage.class;
    }

    @Override
    protected void init() {

        super.init();
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));

    }

}
