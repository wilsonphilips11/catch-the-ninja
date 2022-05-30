package id.ac.umn.uts2020_mobile_bl_00000019916_wilsonphilips_soal02;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ClassUsedAdapter adapter;
    private ArrayList<ClassUsed> classUsedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        addClassUsed();
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new ClassUsedAdapter(classUsedList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ProfileActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    void addClassUsed(){
        classUsedList = new ArrayList<>();
        classUsedList.add(new ClassUsed("Accelerate Interpolator", "Accelerate ninja animation time speed"));
        classUsedList.add(new ClassUsed("Animation & Animation Utils", "Load ninja animation defined in XML"));
        classUsedList.add(new ClassUsed("Animator Set", "Play ninja movement animation"));
        classUsedList.add(new ClassUsed("Array List", "Store used class data on array list "));
        classUsedList.add(new ClassUsed("Card View", "Make a card view for used class"));
        classUsedList.add(new ClassUsed("Display Metrics", "Get height and width of screen size for ninja movement field"));
        classUsedList.add(new ClassUsed("Layout Inflater", "Create a new view object from XML"));
        classUsedList.add(new ClassUsed("MediaController", "Play audio when ninja being hit or missed"));
        classUsedList.add(new ClassUsed("Motion Event", "Get touch trigger when user misses catching ninja"));
        classUsedList.add(new ClassUsed("Progress Bar", "View for ninja HP bar"));
        classUsedList.add(new ClassUsed("Object Animator", "Define ninja's properties to be animated"));
        classUsedList.add(new ClassUsed("Random", "Generate a random number for ninja position"));
        classUsedList.add(new ClassUsed("Recycler View", "Dynamically rendering list of used class"));
        classUsedList.add(new ClassUsed("Rotate Animation", "Make a shuriken rotating animation"));
        classUsedList.add(new ClassUsed("Timer", "Finish ninja animation tasks in given period"));
        classUsedList.add(new ClassUsed("Timer Task", "Define ninja animation tasks called on scheduled time"));
        classUsedList.add(new ClassUsed("Toast", "Make a toast"));
    }

}
