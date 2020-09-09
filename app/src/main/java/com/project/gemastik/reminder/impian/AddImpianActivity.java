package com.project.gemastik.reminder.impian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.gemastik.reminder.R;
import com.project.gemastik.reminder.cnbfragment.ImpianFragment;

import java.util.ArrayList;

public class AddImpianActivity extends AppCompatActivity {

    LinearLayout layoutList;
    TextView addList;
    CardView btnSimpan;

    ArrayList<HabitsItem> impianItems = new ArrayList<HabitsItem>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_impian);

        layoutList = findViewById(R.id.layout_list);
        addList = findViewById(R.id.btnTambahList);
        btnSimpan = findViewById(R.id.btnsimpan_impian);

        addList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addListHabits();
            }
        });
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkNullData()){
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    Fragment aktifFragment = new ImpianFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Data",impianItems);
                    aktifFragment.setArguments(bundle);
                    ft.replace(R.id.impian_container,aktifFragment);
                    ft.addToBackStack(null);
                    ft.commit();
                }
            }
        });
    }

    private boolean checkNullData(){
        impianItems.clear();
        boolean result = true;

        for(int i=0;i<layoutList.getChildCount();i++){

            View cricketerView = layoutList.getChildAt(i);

            EditText editTextName = (EditText)cricketerView.findViewById(R.id.edt_habits);

            HabitsItem item = new HabitsItem();

            if(!editTextName.getText().toString().equals("")){
                item.setTextHabits(editTextName.getText().toString());
            }else {
                result = false;
                break;
            }

            impianItems.add(item);

        }

        if (impianItems.size()==0){
            result = false;
            Toast.makeText(this,"Tambahkan data!",Toast.LENGTH_SHORT).show();
        } else if (!result){
            Toast.makeText(this,"Masukkan Semua Data!",Toast.LENGTH_SHORT).show();
        }

        return result;
    }

    private void addListHabits(){
        final View habitsView = getLayoutInflater().inflate(R.layout.add_layout_habits,null,false);

        EditText editText = habitsView.findViewById(R.id.edt_habits);
        ImageButton imageButton = habitsView.findViewById(R.id.close_habits);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(habitsView);
            }
        });

        layoutList.addView(habitsView);
    }

    private void removeView(View view){
        layoutList.removeView(view);
    }
}
