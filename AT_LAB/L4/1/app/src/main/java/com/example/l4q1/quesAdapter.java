package com.example.l4q1;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class quesAdapter extends RecyclerView.Adapter<quesAdapter.ViewHolder> {

    private final ArrayList<question> questions;

    public quesAdapter(ArrayList<question> questions) {
        this.questions = questions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.questions, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        question currentQuestion = questions.get(position);
        holder.bind(currentQuestion);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }
//
//    public void setQues(List<question> ques) {
//        this.questions = ques;
//        notifyDataSetChanged();
//    }

    public int calculateScore() {
        int score = 0;
        for (question q : questions) {
            if (q.isCorrect()) {
                score++;
            }
        }
        return score;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView questionText;
        RadioButton option1;
        RadioButton option2;
        RadioButton option3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            questionText = itemView.findViewById(R.id.question_text);
            option1 = itemView.findViewById(R.id.option_1);
            option2 = itemView.findViewById(R.id.option_2);
            option3 = itemView.findViewById(R.id.option_3);
        }

        public void bind(question currentQuestion) {
            questionText.setText(currentQuestion.getqText());
            option1.setText(currentQuestion.getOp1());
            option2.setText(currentQuestion.getOp2());
            option3.setText(currentQuestion.getOp3());

            option1.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    currentQuestion.setSelectedOption(currentQuestion.getOp1());
                }
            });
            option2.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    currentQuestion.setSelectedOption(currentQuestion.getOp2());
                }
            });
            option3.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    currentQuestion.setSelectedOption(currentQuestion.getOp3());
                }
            });
        }
    }
}