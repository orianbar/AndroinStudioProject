package com.example.FlappyDiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class FoodAvcivity extends AppCompatActivity {
    RadioGroup rg;
    RadioButton rb;
    EditText food_amount;
    Button addImage;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_avcivity);


//     final EditText food_amount =(EditText) findViewById(R.id.amount);
        // final LinearLayout btnsLayout = findViewById(R.id.btns_layout);
        //        Button ButtonConfirm = (Button) findViewById(R.id.confirm);

        //     ButtonConfirm.setOnClickListener(new View.OnClickListener() {
        //                                @Override
        //                                 public void onClick(View view) {
        //                                   String numofFoodString = food_amount.getText().toString();

        // btnsEt.removeAllViews();
        //                                  int numofFood = Integer.parseInt(numofFoodString);
        //                                  LinearLayout DogFood = findViewById(R.id.dogfoodlayout);
        //                              for (int i = 0; i < numofFood; i++) {
        //                                  ImageView ImageDog = new ImageView(FoodAvcivity.this);
        //                                  ImageDog.setImageResource(R.drawable.bonzo);
        //                                  LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
        //                                  ImageDog.setLayoutParams(layoutParams);
        //                                 ImageDog.setId(i);
        //                               DogFood.addView(ImageDog);
        //                                                               }
        //                       }
        //                  });

        //      Button btn = new Button(FoodAvcivity.this);
        //       LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        //      float scale= getResources().getDisplayMetrics().density;
        //     params.setMarginStart((int)(30*scale));
        //     params.setMarginEnd((int)(30*scale));
        //     btn.setLayoutParams(params);
        //     btn.setText(i+1+"");
        //    btn.setOnClickListener(new View.OnClickListener(){
        //       @Override
        //       public void onClick(View view){
        //          Toast.makeText(FoodAvcivity.this,((Button)view).getText(),Toast.LENGTH_SHORT).show();
        //      }
        //  });

        //       btnsLayout.addView(btn);
        //       }
        // }
        //   }
        // });

//        rg = (RadioGroup) findViewById(R.id.rgroup);

        //       Button button = (Button) findViewById(R.id.checkout3);
        //       button.setOnClickListener(new View.OnClickListener() {
        //          @Override
        //         public void onClick(View v) {
        //              Intent toRatingbar = new Intent(FoodAvcivity.this, RatingbarActivity.class);
        //              startActivity(toRatingbar);
        //         }
        //     });

        Button button2 = (Button) findViewById(R.id.backmain3);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMain = new Intent(FoodAvcivity.this, MainActivity.class);
                startActivity(toMain);
            }
        });

        //     final EditText editText = findViewById(R.id.amount);

        //    final Button btn_confirm = (Button) findViewById(R.id.confirm);
        //    btn_confirm.setOnClickListener(new View.OnClickListener() {
        //       @Override
        //       public void onClick(View v) {
        //          String confirm = editText.getText().toString();
        //           onDisplay(editText);
        //       }
        //    });


        //  Intent intent = getIntent();
        //   String animal = intent.getStringExtra("animal");

        //  TextView textView = new TextView(this);
        //  textView.setTextColor(getResources().getColor(R.color.colorAccent));
        //   textView.setText(animal);
        //  textView.setTextSize(18);

        //  ConstraintLayout constraintLayout = findViewById(R.id.af_cl);
        //   constraintLayout.addView(textView);
////    }

        //  public void onDisplay(View v)
        //  {
        //edit = (EditText)findViewById(R.id.amount);
        //String message = edit.getText().toString();
        //    Toast.makeText(FoodAvcivity.this,((EditText)findViewById(R.id.amount)).getText(),Toast.LENGTH_SHORT).show();
        // String input = food_amount.getText().toString();
        // if(input.matches("[0-9]+")){
        //int numOfBtns = Integer.parseInt(input);

        //for(int i=0;i<numOfBtns;i++) {
        // EditText amount =findViewById(R.id.amount);
        //amount.remove

        //      }


////public void rbclick(View v)

///{
        //       int radiobuttonid = rg.getCheckedRadioButtonId();
        //      rb= (RadioButton) findViewById(radiobuttonid);
//
        ///       Toast.makeText(getBaseContext(),rb.getText(),Toast.LENGTH_SHORT).show();


    }
}







