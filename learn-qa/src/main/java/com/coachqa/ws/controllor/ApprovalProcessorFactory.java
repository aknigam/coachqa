package com.coachqa.ws.controllor;

import com.coachqa.service.ApprovalProcessor;
import notification.entity.EventType;

import java.util.HashMap;
import java.util.Map;

public class ApprovalProcessorFactory {

    private Map<EventType, ApprovalProcessor> processorMap = new HashMap();

    public ApprovalProcessor getApprovalProcessor(EventType eventType) {
        return processorMap.get(eventType);
    }

    public void register(EventType eventType, ApprovalProcessor approvalProcessor) {
        processorMap.put(eventType, approvalProcessor);
    }
}
