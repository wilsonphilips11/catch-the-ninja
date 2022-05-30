package id.ac.umn.uts2020_mobile_bl_00000019916_wilsonphilips_soal02;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClassUsedAdapter extends RecyclerView.Adapter<ClassUsedAdapter.ClassUsedViewHolder> {
    private ArrayList<ClassUsed> classUsedList;

    public ClassUsedAdapter(ArrayList<ClassUsed> classUsedList) {
        this.classUsedList = classUsedList;
    }

    @Override
    public ClassUsedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_class_used, parent, false);
        return new ClassUsedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClassUsedViewHolder holder, int position) {
        holder.txtClassName.setText(classUsedList.get(position).getClassName());
        holder.txtClassDescription.setText(classUsedList.get(position).getClassDescription());

        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(3000);
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setInterpolator(new LinearInterpolator());
        holder.viewClassImage.startAnimation(rotate);
    }

    @Override
    public int getItemCount() {
        return (classUsedList != null) ? classUsedList.size() : 0;
    }

    public class ClassUsedViewHolder extends RecyclerView.ViewHolder{
        private TextView txtClassName, txtClassDescription;
        private ImageView viewClassImage;

        public ClassUsedViewHolder(View itemView) {
            super(itemView);
            txtClassName = itemView.findViewById(R.id.className);
            txtClassDescription = itemView.findViewById(R.id.classDescription);
            viewClassImage = itemView.findViewById(R.id.classImage);
        }
    }
}
