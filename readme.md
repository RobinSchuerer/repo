# Wicket, WebSockets, RxJava und Spring Boot
Dieses Projekt zeigt, wie durch das Backend alle verbundenen Clients benachrichtigt werden können.

## RxJava
RxJava ist die Java Implementierung von ReactiveX. Mit dieser Bibliothek kann man auf sehr elegante Art und Weise
Schnittstellen zwischen GUI und Backend entwerfen. Dabei wird dem Frontend ein Event-Stream zur Verfügung gestellt,
auf den die GUI entsprechend reagiert.

Mit dieser Technologie ist es außerdem möglich den parallelen Zugriff zu verstecken. Die Ergebnisse von parallelen
Verarbeitungen werden auf einen Stream von Ergebnissen abgebildet.

In diesem Beispiel ist ein Spring Service über ein Subject<String> mit der GUI verbunden. Wenn im Backend eine neue
Nachricht eintrifft wird diese "dem Stream" übergeben und die GUI aktualisiert sich selber.

## WebSocket
WebSockets sind eine Technologie, mit der vom WebServer Nachrichten an die Clients übertragen werden können. Wenn eine
neue Nachricht eintrifft, wird diese auf alle verbundenen Clients übertragen, ohne dass vom Client aktiv ein Request
erzeugt wird.