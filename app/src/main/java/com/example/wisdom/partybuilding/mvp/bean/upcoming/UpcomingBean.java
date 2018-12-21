package com.example.wisdom.partybuilding.mvp.bean.upcoming;

import java.util.List;

public class UpcomingBean {


    /**
     * results : [{"allowBatchApprove":0,"allowDivert":0,"assignee":"10000000036895","codebefore":"","createDate":null,"createTime":{"date":21,"day":5,"hours":15,"minutes":35,"month":11,"seconds":6,"time":1545377706419,"timezoneOffset":-480,"year":118},"creator":"演示","creatorId":10000000036895,"defId":0,"delegationState":"","description":"-1","dueDate":null,"executionId":"10000011990221","globalFlowNo":"","hasRead":1,"id":"10000011990238","isAgent":false,"isDivert":false,"ischeck":0,"msgwarn":0,"name":"党委审批","orgName":"","owner":"10000000036895","parentTaskId":"","priority":50,"processDefinitionId":"zzgxzc:2:10000009260201","processInstanceId":"10000011990221","processName":"组织关系转出","reminderLv":0,"revision":1,"runId":0,"status":"","subject":"组织关系转出-演示-2018-12-21 15:35:04","tagIds":"","taskDefinitionKey":"task2","taskStatus":1,"taskUrl":"","type":"","typeId":10000002490451,"typeName":"集团通用流程"}]
     * totalCounts : 1
     */

    private int totalCounts;
    private List<ResultsBean> results;

    public int getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(int totalCounts) {
        this.totalCounts = totalCounts;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * allowBatchApprove : 0
         * allowDivert : 0
         * assignee : 10000000036895
         * codebefore :
         * createDate : null
         * createTime : {"date":21,"day":5,"hours":15,"minutes":35,"month":11,"seconds":6,"time":1545377706419,"timezoneOffset":-480,"year":118}
         * creator : 演示
         * creatorId : 10000000036895
         * defId : 0
         * delegationState :
         * description : -1
         * dueDate : null
         * executionId : 10000011990221
         * globalFlowNo :
         * hasRead : 1
         * id : 10000011990238
         * isAgent : false
         * isDivert : false
         * ischeck : 0
         * msgwarn : 0
         * name : 党委审批
         * orgName :
         * owner : 10000000036895
         * parentTaskId :
         * priority : 50
         * processDefinitionId : zzgxzc:2:10000009260201
         * processInstanceId : 10000011990221
         * processName : 组织关系转出
         * reminderLv : 0
         * revision : 1
         * runId : 0
         * status :
         * subject : 组织关系转出-演示-2018-12-21 15:35:04
         * tagIds :
         * taskDefinitionKey : task2
         * taskStatus : 1
         * taskUrl :
         * type :
         * typeId : 10000002490451
         * typeName : 集团通用流程
         */

        private int allowBatchApprove;
        private int allowDivert;
        private String assignee;
        private String codebefore;
        private Object createDate;
        private CreateTimeBean createTime;
        private String creator;
        private long creatorId;
        private int defId;
        private String delegationState;
        private String description;
        private Object dueDate;
        private String executionId;
        private String globalFlowNo;
        private int hasRead;
        private String id;
        private boolean isAgent;
        private boolean isDivert;
        private int ischeck;
        private int msgwarn;
        private String name;
        private String orgName;
        private String owner;
        private String parentTaskId;
        private int priority;
        private String processDefinitionId;
        private String processInstanceId;
        private String processName;
        private int reminderLv;
        private int revision;
        private int runId;
        private String status;
        private String subject;
        private String tagIds;
        private String taskDefinitionKey;
        private int taskStatus;
        private String taskUrl;
        private String type;
        private long typeId;
        private String typeName;

        public int getAllowBatchApprove() {
            return allowBatchApprove;
        }

        public void setAllowBatchApprove(int allowBatchApprove) {
            this.allowBatchApprove = allowBatchApprove;
        }

        public int getAllowDivert() {
            return allowDivert;
        }

        public void setAllowDivert(int allowDivert) {
            this.allowDivert = allowDivert;
        }

        public String getAssignee() {
            return assignee;
        }

        public void setAssignee(String assignee) {
            this.assignee = assignee;
        }

        public String getCodebefore() {
            return codebefore;
        }

        public void setCodebefore(String codebefore) {
            this.codebefore = codebefore;
        }

        public Object getCreateDate() {
            return createDate;
        }

        public void setCreateDate(Object createDate) {
            this.createDate = createDate;
        }

        public CreateTimeBean getCreateTime() {
            return createTime;
        }

        public void setCreateTime(CreateTimeBean createTime) {
            this.createTime = createTime;
        }

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public long getCreatorId() {
            return creatorId;
        }

        public void setCreatorId(long creatorId) {
            this.creatorId = creatorId;
        }

        public int getDefId() {
            return defId;
        }

        public void setDefId(int defId) {
            this.defId = defId;
        }

        public String getDelegationState() {
            return delegationState;
        }

        public void setDelegationState(String delegationState) {
            this.delegationState = delegationState;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Object getDueDate() {
            return dueDate;
        }

        public void setDueDate(Object dueDate) {
            this.dueDate = dueDate;
        }

        public String getExecutionId() {
            return executionId;
        }

        public void setExecutionId(String executionId) {
            this.executionId = executionId;
        }

        public String getGlobalFlowNo() {
            return globalFlowNo;
        }

        public void setGlobalFlowNo(String globalFlowNo) {
            this.globalFlowNo = globalFlowNo;
        }

        public int getHasRead() {
            return hasRead;
        }

        public void setHasRead(int hasRead) {
            this.hasRead = hasRead;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isIsAgent() {
            return isAgent;
        }

        public void setIsAgent(boolean isAgent) {
            this.isAgent = isAgent;
        }

        public boolean isIsDivert() {
            return isDivert;
        }

        public void setIsDivert(boolean isDivert) {
            this.isDivert = isDivert;
        }

        public int getIscheck() {
            return ischeck;
        }

        public void setIscheck(int ischeck) {
            this.ischeck = ischeck;
        }

        public int getMsgwarn() {
            return msgwarn;
        }

        public void setMsgwarn(int msgwarn) {
            this.msgwarn = msgwarn;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getParentTaskId() {
            return parentTaskId;
        }

        public void setParentTaskId(String parentTaskId) {
            this.parentTaskId = parentTaskId;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public String getProcessDefinitionId() {
            return processDefinitionId;
        }

        public void setProcessDefinitionId(String processDefinitionId) {
            this.processDefinitionId = processDefinitionId;
        }

        public String getProcessInstanceId() {
            return processInstanceId;
        }

        public void setProcessInstanceId(String processInstanceId) {
            this.processInstanceId = processInstanceId;
        }

        public String getProcessName() {
            return processName;
        }

        public void setProcessName(String processName) {
            this.processName = processName;
        }

        public int getReminderLv() {
            return reminderLv;
        }

        public void setReminderLv(int reminderLv) {
            this.reminderLv = reminderLv;
        }

        public int getRevision() {
            return revision;
        }

        public void setRevision(int revision) {
            this.revision = revision;
        }

        public int getRunId() {
            return runId;
        }

        public void setRunId(int runId) {
            this.runId = runId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getTagIds() {
            return tagIds;
        }

        public void setTagIds(String tagIds) {
            this.tagIds = tagIds;
        }

        public String getTaskDefinitionKey() {
            return taskDefinitionKey;
        }

        public void setTaskDefinitionKey(String taskDefinitionKey) {
            this.taskDefinitionKey = taskDefinitionKey;
        }

        public int getTaskStatus() {
            return taskStatus;
        }

        public void setTaskStatus(int taskStatus) {
            this.taskStatus = taskStatus;
        }

        public String getTaskUrl() {
            return taskUrl;
        }

        public void setTaskUrl(String taskUrl) {
            this.taskUrl = taskUrl;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public long getTypeId() {
            return typeId;
        }

        public void setTypeId(long typeId) {
            this.typeId = typeId;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public static class CreateTimeBean {
            /**
             * date : 21
             * day : 5
             * hours : 15
             * minutes : 35
             * month : 11
             * seconds : 6
             * time : 1545377706419
             * timezoneOffset : -480
             * year : 118
             */

            private int date;
            private int day;
            private int hours;
            private int minutes;
            private int month;
            private int seconds;
            private long time;
            private int timezoneOffset;
            private int year;

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }
        }
    }
}
