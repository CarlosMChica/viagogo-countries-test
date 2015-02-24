package com.carlosdelachica.viagogo.domain;

import com.carlosdelachica.viagogo.domain.interactors.Interactor;
import com.carlosdelachica.viagogo.domain.interactors.InteractorExecutor;
import com.carlosdelachica.viagogo.domain.interactors.InteractorPriority;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.Params;

public class InteractorExecutorImp implements InteractorExecutor {

    private JobManager jobManager;
    private EventBus bus;

    public InteractorExecutorImp(JobManager jobManager, EventBus bus) {
        this.jobManager = jobManager;
        this.bus = bus;
    }

    @Override public void execute(Interactor interactor) {
        execute(interactor, InteractorPriority.MEDIUM);
    }

    @Override public void execute(Interactor interactor, InteractorPriority priority) {
        jobManager.addJob(interactorToJob(interactor, priority));
    }

    private Job interactorToJob(Interactor interactor, InteractorPriority priority) {
        Params params = new Params(priority.getPriorityValue());
        return new InteractorJobImp(params, bus, interactor);
    }

}
