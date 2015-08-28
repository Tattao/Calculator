package com.tattao.calculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    //初始化各控件
    boolean clear_flag;//清空标识
    private Button btn_0;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private Button btn_Add;//加号按钮
    private Button btn_Min;//减号按钮
    private Button btn_Mul;//乘号按钮
    private Button btn_Div;//除号按钮
    private Button btn_Del;//单个删除按钮
    private Button btn_Point;//小数点按钮
    private Button btn_Clear;//全屏清除按钮
    private Button btn_Equ;//等号按钮

    private EditText et_input;//输入显示框

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //实例化按钮
        btn_0=(Button)findViewById(R.id.btn_0);
        btn_1=(Button)findViewById(R.id.btn_1);
        btn_2=(Button)findViewById(R.id.btn_2);
        btn_3=(Button)findViewById(R.id.btn_3);
        btn_4=(Button)findViewById(R.id.btn_4);
        btn_5=(Button)findViewById(R.id.btn_5);
        btn_6=(Button)findViewById(R.id.btn_6);
        btn_7=(Button)findViewById(R.id.btn_7);
        btn_8=(Button)findViewById(R.id.btn_8);
        btn_9=(Button)findViewById(R.id.btn_9);
        btn_Add=(Button)findViewById(R.id.btn_Add);
        btn_Min=(Button)findViewById(R.id.btn_Min);
        btn_Mul=(Button)findViewById(R.id.btn_Mul);
        btn_Div=(Button)findViewById(R.id.btn_Div);
        btn_Del=(Button)findViewById(R.id.btn_Del);
        btn_Point=(Button)findViewById(R.id.btn_Point);
        btn_Clear=(Button)findViewById(R.id.btn_Clear);
        btn_Equ=(Button)findViewById(R.id.btn_Equ);

        //实例化输入框
        et_input=(EditText)findViewById(R.id.et_input);

        //设置按钮的点击事件
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_Add.setOnClickListener(this);
        btn_Min.setOnClickListener(this);
        btn_Mul.setOnClickListener(this);
        btn_Div.setOnClickListener(this);
        btn_Point.setOnClickListener(this);
        btn_Clear.setOnClickListener(this);
        btn_Del.setOnClickListener(this);
        btn_Equ.setOnClickListener(this);

        et_input.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String str=et_input.getText().toString();//显示屏的内容
        switch(v.getId()){
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_Point:
                if(clear_flag){
                    clear_flag=false;
                    et_input.setText("");
                }
                et_input.setText(str+((Button)v).getText());
                break;

            case R.id.btn_Add:
            case R.id.btn_Min:
            case R.id.btn_Mul:
            case R.id.btn_Div:
                if(clear_flag){
                    clear_flag=false;
                    et_input.setText("");
                }
                et_input.setText(str+" "+((Button)v).getText()+" ");
                break;

            case R.id.btn_Clear:
                clear_flag=false;
                et_input.setText("");
                break;

            case R.id.btn_Del:
                if(clear_flag){
                    clear_flag=false;
                    et_input.setText("");
                }else if(str!=null&&str.equals("")){
                    et_input.setText(str.substring(0,str.length()-1));
                }

            case R.id.btn_Equ:
            getResult();

                break;

        }
    }

    private void getResult() {
        String exp = et_input.getText().toString();
        if (exp == null || exp.equals("")) {
            return;
        }
        if (exp.contains(" ")) {
            return;
        }
        if(clear_flag){
            clear_flag=false;
            return;
        }
        clear_flag=true;
        //截取
        double result = 0;
        String s1 = exp.substring(0, exp.indexOf(" "));//运算符前面的那个字符
        String op = exp.substring(exp.indexOf(" ") + 1, exp.indexOf(" ") + 2);//运算符
        String s2 = exp.substring(1, exp.indexOf(" ") + 3);//运算符后面的
        if (s1.equals("") && s2.equals("")) {
            double d1 = Double.parseDouble(s1);
            double d2 = Double.parseDouble(s2);
            if (op.equals("+")) {
                result = d1 + d2;
            } else if (op.equals("-")) {
                result = d1 - d2;
            } else if (op.equals("*")) {
                result = d1 * d2;
            } else if (op.equals("/"))
                if (d2 == 0) {
                    result = 0;
                } else {
                    result = d1 / d2;
                }
            if (!s1.contains(".") && !s2.contains(".")) {
                int r = (int) result;
                et_input.setText(r + "");
            } else {
                et_input.setText(result + "");
            }
        } else if (!s1.equals("") && s2.equals("")) {
            et_input.setText(exp);
        } else if (s1.equals("") && !s2.equals("")) {
            double d2 = Double.parseDouble(s2);
            if (op.equals("+")) {
                result = 0 + d2;
            } else if (op.equals("-")) {
                result = 0 - d2;
            } else if (op.equals("*")) {
                result = 0;
            } else if (op.equals("/")) {
                result = 0;
            }
            if (!s2.contains(".")) {
                int r = (int) result;
                et_input.setText(r + "");
            } else {
                et_input.setText(result + "");
            }
        } else if(s1.equals("") && s2.equals("")){
            double d2 = Double.parseDouble(s2);
            if (op.equals("+")) {
                result = 0 + d2;
            } else if (op.equals("-")) {
                result = 0 - d2;
            } else if (op.equals("*")) {
                result = 0;
            } else if (op.equals("/")) {
                result = 0;
            }
            if (!s2.contains(".") ) {
                int r = (int) result;
                et_input.setText(r + "");
            } else {
                et_input.setText(result + "");
            }
        }else{
            et_input.setText("");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
