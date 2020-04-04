package com.dbms.questionbank;

public class quest {
    String ques;
    String leve;
    String subj;
    String quesid;


    public quest(){

    }

    public quest(String quesid,String ques,String leve,String subj){
        this.ques = ques;
        this.leve = leve;
        this.subj = subj;
        this.quesid=quesid;
    }

    public String getQuesid() {
        return quesid;
    }
    public String getQues() {
        return ques;
    }

    public String getLeve() {
        return leve;
    }

    public String getSubj() {
        return subj;
    }
}
