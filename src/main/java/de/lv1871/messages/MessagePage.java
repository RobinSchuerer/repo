package de.lv1871.messages;

import org.apache.wicket.Application;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.ws.WebSocketSettings;
import org.apache.wicket.protocol.ws.api.WebSocketBehavior;
import org.apache.wicket.protocol.ws.api.WebSocketPushBroadcaster;
import org.apache.wicket.protocol.ws.api.event.WebSocketPushPayload;
import org.apache.wicket.protocol.ws.api.message.IWebSocketPushMessage;
import org.apache.wicket.protocol.ws.api.registry.IWebSocketConnectionRegistry;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class MessagePage extends WebPage {

    @SpringBean
    private Service service;

    private IModel<String> model = new Model<String>();
    private Label label;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        // die Ausgabe
        label();

        // Websocket Behaviour
        addWebSocketBehaviour();


        final Application application = Application.get();
        WebSocketPushBroadcaster broadcaster = getWebSocketPushBroadcaster(application);

        // subscribe auf observable, hier wird im Falle eines neuen Events die Aktualisierung der Clients ausgelöst
        service.doSomething().subscribe(value -> {
            model.setObject(value);
            UpdateEvent event = new UpdateEvent();
            event.newValue = value;
            broadcaster.broadcastAll(application, event);
        });

    }

    private WebSocketPushBroadcaster getWebSocketPushBroadcaster(Application application) {
        final WebSocketSettings webSocketSettings = WebSocketSettings.Holder.get(application);
        final IWebSocketConnectionRegistry webSocketConnectionRegistry = webSocketSettings.getConnectionRegistry();
        return new WebSocketPushBroadcaster(webSocketConnectionRegistry);
    }

    private void addWebSocketBehaviour() {
        add(new WebSocketBehavior() {
        });
    }

    private void label() {
        label = new Label("label", model);
        label.setOutputMarkupId(true);
        add(label);
    }

    @Override
    public void onEvent(IEvent<?> event) {
        if (event.getPayload() instanceof WebSocketPushPayload) {
            WebSocketPushPayload wsEvent = (WebSocketPushPayload) event
                    .getPayload();
            UpdateEvent message = (UpdateEvent) wsEvent.getMessage();

            model.setObject(message.newValue);

            wsEvent.getHandler().add(label);
        }
    }

    private class UpdateEvent implements IWebSocketPushMessage {

        String newValue;
    }
}
