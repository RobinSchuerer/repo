package de.lv1871.messages;

import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.protocol.ws.javax.WicketServerEndpointConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.servlet.Filter;

@SpringBootApplication
@Configuration
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Filter getWicketFilter() {
        WicketWebApplication webApplication = new WicketWebApplication();
        WicketFilter filter = new WicketFilter(webApplication);
        filter.setFilterPath("/");
        return filter;
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Bean
    public WicketServerEndpointConfig myWicketServerEndpointConfig() {
        return new WicketServerEndpointConfig();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.initialize();
        return taskExecutor;
    }
}
