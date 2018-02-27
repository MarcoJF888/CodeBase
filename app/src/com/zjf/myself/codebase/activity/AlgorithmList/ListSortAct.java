package com.zjf.myself.codebase.activity.AlgorithmList;

import android.os.Bundle;

import com.zjf.myself.codebase.R;
import com.zjf.myself.codebase.activity.BaseAct;
import com.zjf.myself.codebase.util.AppLog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <pre>
 *     author : ZouJianFeng
 *     e-mail : 1434690833@qq.com
 *     time   : 2017/07/29
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class ListSortAct extends BaseAct{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_list_sore);
        List<Student> list = new ArrayList<Student>();

        //创建3个学生对象，年龄分别是20、19、21，并将他们依次放入List中
        Student s1 = new Student();
        s1.setAge(20);
        Student s2 = new Student();
        s2.setAge(19);
        Student s3 = new Student();
        s3.setAge(21);
        list.add(s1);
        list.add(s2);
        list.add(s3);

       AppLog.d("排序前："+list);

        Collections.sort(list, new Comparator<Student>(){

            /*
             * int compare(Student o1, Student o2) 返回一个基本类型的整型，
             * 返回负数表示：o1 小于o2，
             * 返回0 表示：o1和o2相等，
             * 返回正数表示：o1大于o2。
             */
            public int compare(Student o1, Student o2) {

                //按照学生的年龄进行升序排列
                if(o1.getAge() > o2.getAge()){
                    return 1;
                }
                if(o1.getAge() == o2.getAge()){
                    return 0;
                }
                return -1;
            }
        });
        AppLog.d("排序后："+list);
    }
}
class Student{

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return getAge()+"";
    }
}
