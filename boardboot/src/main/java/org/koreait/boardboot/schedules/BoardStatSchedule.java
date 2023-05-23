package org.koreait.boardboot.schedules;

import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log
@Component
public class BoardStatSchedule {

    @Scheduled(cron="0 0 1 * * *")
    public void process(){

    }
}
