package de.lv1871.messages;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rx.subjects.PublishSubject;

@Component
@RestController
public class Service {

    private static final PublishSubject<String> subject = PublishSubject.create();

    public PublishSubject<String> doSomething() {
        return subject;
    }

    public PublishSubject<String> addValue(final String newValue){
        subject.onNext(newValue);

        return subject;
    }

    @RequestMapping("/message")
    public String greeting(@RequestParam(value="message", defaultValue="hallo") String message) {
        addValue(message);
        return message;
    }

}
