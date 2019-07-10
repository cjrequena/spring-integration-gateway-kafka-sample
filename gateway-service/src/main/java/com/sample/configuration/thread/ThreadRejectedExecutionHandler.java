package com.sample.configuration.thread;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

@Log4j2
@Service
public class ThreadRejectedExecutionHandler implements RejectedExecutionHandler {

	public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
		log.debug(runnable.toString() + " : has been rejected");
	}
}
