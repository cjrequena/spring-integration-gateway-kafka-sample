package com.sample.configuration.thread;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadPoolExecutor;

@Log4j2
@Data
@Service
public class ThreadPoolMonitor implements Runnable {

  @Value("${monitoring.period:5}")
  private long monitoringPeriod;
  private ThreadPoolExecutor executor;

  public void run() {
    try {
      while (true) {
        threadPoolMonitor();
        Thread.sleep(monitoringPeriod * 1000);
      }
    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }

  public void threadPoolMonitor() {
    StringBuffer strBuff = new StringBuffer();
    strBuff.append("CurrentPoolSize : ").append(executor.getPoolSize());
    strBuff.append(" - CorePoolSize : ").append(executor.getCorePoolSize());
    strBuff.append(" - MaximumPoolSize : ").append(executor.getMaximumPoolSize());
    strBuff.append(" - ActiveTaskCount : ").append(executor.getActiveCount());
    strBuff.append(" - CompletedTaskCount : ").append(executor.getCompletedTaskCount());
    strBuff.append(" - UnCompletedTaskCount : ").append(executor.getTaskCount() - executor.getCompletedTaskCount());
    strBuff.append(" - TotalTaskCount : ").append(executor.getTaskCount());
    strBuff.append(" - isTerminated : ").append(executor.isTerminated());
    log.trace(strBuff.toString());
  }
}
