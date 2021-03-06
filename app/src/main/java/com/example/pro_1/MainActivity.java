package com.example.pro_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.PagerAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.pro_1.Util.showToast;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView; //바텀 네비
    private FragmentManager fm;
    private FragmentTransaction ft;
    PagerAdapter pagerAdapter;
    private Frag1 frag1;
    private Frag2 frag2;
    private Frag3 frag3;
    private Frag4 frag4;
    private Frag5 frag5;

    Toolbar toolbar;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        if(firebaseUser != null){
            if (firebaseUser != null){
                String name = firebaseUser.getDisplayName();
                String email = firebaseUser.getEmail();
                showToast(MainActivity.this,"이름: "+name+"이메일: "+email);
            }
        }else {
            myStartActivity(SignUpActivity.class);

        }



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("What's your MBTI");
        setSupportActionBar(toolbar);
        //assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //App Bar의 좌측 영영에 Drawer를 Open 하기 위한 Incon 추가

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);





        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_home:
                        setFrag(0);
                        break;
                    case R.id.action_examination:
                        setFrag(1);
                        break;
                    case R.id.action_MBTI:
                        setFrag(2);
                        break;
                    case R.id.action_date:
                        setFrag(3);
                        break;
                    case R.id.action_mypage:
                        setFrag(4);
                        break;
                }
                return true;
            }

        });
        frag1 = new Frag1();
        frag2 = new Frag2();
        frag3 = new Frag3();
        frag4 = new Frag4();
        frag5 = new Frag5();

        setFrag(0); //첫 프레그먼트 화면 지정 해주기. 0번으로 지정함 나는.
    }
    private void setFrag(int n){

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction(); //실질적인 교체가 일어남
        switch (n){
            case 0:
                ft.replace(R.id.main_frame, frag1);
                ft.commit(); //저장을 의미함.
                break;
            case 1:
                ft.replace(R.id.main_frame, frag2);
                ft.commit(); //저장을 의미함.
                break;
            case 2:
                ft.replace(R.id.main_frame, frag3);
                ft.commit(); //저장을 의미함.
                break;
            case 3:
                ft.replace(R.id.main_frame, frag4);
                ft.commit();
                break;
            case 4:
                ft.replace(R.id.main_frame, frag5);
                ft.commit();
                break;

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                setFrag(0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void Button(View v)
    {
        Toast.makeText(getApplicationContext(), "이동하기", Toast.LENGTH_LONG).show();
    }

}
