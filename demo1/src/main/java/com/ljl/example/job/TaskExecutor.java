package com.ljl.example.job;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public abstract class TaskExecutor implements Runnable{

    private final static String TASK_FAIL = "任务执行失败";

    private Long taskId;

    private String taskType;

    public TaskExecutor() {
    }

    public TaskExecutor(Long taskId, String taskType) {
        this.taskId = taskId;
        this.taskType = taskType;
    }

    public abstract Boolean onBefore();

    public abstract Boolean onExecute() throws Exception;

    public abstract Boolean onExecuteError(String msg);

    public abstract Boolean onAfter();

    @Override
    public void run() {
        log.info("beging task id -> {}, type -> {}", taskId, taskType);
        this.onBefore();
        try {
            log.info("execute task id -> {}, type -> {}", taskId, taskType);
            Boolean execute = this.onExecute();
            if(!execute){
                log.error("execute error task id -> {}, type -> {}, error-> {}", taskId, taskType, TASK_FAIL);
                this.onExecuteError(TASK_FAIL);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("execute error task id -> {}, type -> {}, error-> {}", taskId, taskType, e.getMessage());
            this.onExecuteError(TASK_FAIL);
            return;
        }
        this.onAfter();
        log.info(" after excute task id -> {}, type -> {}", taskId, taskType);
    }
}
