package com.sample.configuration.thread;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Log4j2
@Data
@Configuration
public class ThreadPool {
  @Value("${core.pool.size:10}")
  private int corePoolSize;
  @Value("${max.pool.size:10}")
  private int maxPoolSize;
  @Value("${keep.alive.time:10}")
  private long keepAliveTime;
  @Value("${queue.capacity:3}")
  private int queueCapacity;
  @Autowired
  ThreadRejectedExecutionHandler threadRejectedExecutionHandler;

  @Bean(name = "threadPoolExecutor")
  public ThreadPoolExecutor threadPoolExecutor() {
   return new ThreadPoolExecutor(getCorePoolSize(),
      getMaxPoolSize(),
      getKeepAliveTime(),
      TimeUnit.SECONDS,
      new ArrayBlockingQueue<Runnable>(getQueueCapacity()),
      getThreadRejectedExecutionHandler());
  }

  @Bean(name = "cachedThreadPool")
  public ThreadPoolExecutor cachedThreadPool() {
    return (ThreadPoolExecutor) Executors.newCachedThreadPool();
  }

  @Bean(name = "singleThreadExecutor")
  public Executor singleThreadExecutor() {
    return Executors.newSingleThreadExecutor();
  }
}
