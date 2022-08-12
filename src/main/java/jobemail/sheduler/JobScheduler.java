package jobemail.sheduler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableScheduling
@AllArgsConstructor
public class JobScheduler {
    @Scheduled(cron = "${cron}")
    public void execute() {
        log.info("Iniciando job para gerar o arquivo de faturamento");
    }
}
