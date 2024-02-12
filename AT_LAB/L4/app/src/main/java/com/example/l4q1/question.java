package com.example.l4q1;

import java.util.List;

public class question {
    private String qText;
    private String op1;
    private String op2;
    private String op3;
    private String ans;
    private String selectedOption;

    public question(String qText, String op1, String op2, String op3, String ans) {
        this.qText = qText;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.ans = ans;
    }

    public static question get(List<question> questions, int position) {
        if (position >= 0 && position < questions.size()) {
            return questions.get(position);
        } else {
            return null;
        }
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    public boolean isCorrect() {
        return this.ans.equals(this.selectedOption);
    }

    public String getqText() {
        return qText;
    }

    public void setqText(String qText) {
        this.qText = qText;
    }

    public String getOp1() {
        return op1;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public String getOp2() {
        return op2;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public String getOp3() {
        return op3;
    }

    public void setOp3(String op3) {
        this.op3 = op3;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getSelectedOption() {
        return selectedOption;
    }
}

