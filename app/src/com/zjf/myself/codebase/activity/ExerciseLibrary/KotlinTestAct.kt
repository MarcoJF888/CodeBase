package com.zjf.myself.codebase.activity.ExerciseLibrary

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.zjf.myself.codebase.R
import com.zjf.myself.codebase.activity.BaseAct
import com.zjf.myself.codebase.util.AppLog
import com.zjf.myself.codebase.util.ViewUtils.setText
import kotlinx.android.synthetic.main.act_kotlin_test.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class KotlinTestAct : BaseAct(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_kotlin_test)


        txtTest.setText("点我点我点我")

        txtTest.setOnClickListener { setText(txtTest,"您点了一下");toast("小提示：您点了一下下") }

        txtTest.setOnLongClickListener { txtTest.text="您长按了一小会"; true ;longToast("长提示：您长按了一小会"); true }


        val origin:Float = 65.0f
        txtFirst.text = origin.toString()

        var int:Int
        btnToInt.setOnClickListener { int=origin.toInt(); txtResult.text=int.toString() }

        var long:Long
        btnToLong.setOnClickListener { long=origin.toLong(); txtResult.text=long.toString() }

        var float:Float
        btnToFloat.setOnClickListener { float=origin.toDouble().toFloat(); txtResult.text=float.toString() }

        var double:Double
        btnToDouble.setOnClickListener { double=origin.toDouble(); txtResult.text=double.toString() }

        var boolean:Boolean
        btnToBoolean.setOnClickListener { boolean=origin.isNaN(); txtResult.text=boolean.toString() }

        var char:Char
        btnToChar.setOnClickListener { char=origin.toChar(); txtResult.text=char.toString() }

//
//        var int_array:IntArray = intArrayOf(1, 2, 3)
////        长整型数组：数组类型为LongArray，分配方法为longArrayOf；
//        var long_array:LongArray = longArrayOf(1, 2, 3)
//
////        浮点数组：数组类型为FloatArray，分配方法为floatArrayOf；
//        var float_array:FloatArray = floatArrayOf(1.0f, 2.0f, 3.0f)
//
////        双精度数组：数组类型为DoubleArray，分配方法为doubleArrayOf；
//        var double_array:DoubleArray = doubleArrayOf(1.0, 2.0, 3.0)
//
////        布尔型数组：数组类型为BooleanArray，分配方法为booleanArrayOf；
//        var boolean_array:BooleanArray = booleanArrayOf(true, false, true)
////        字符数组：数组类型为CharArray，分配方法为charArrayOf；
//        var char_array:CharArray = charArrayOf('a', 'b', 'c')
//
////         字符串数组：Array<String>，分配字符串数组的方法也相应变成了arrayOf
//        var string_array:Array<String> = arrayOf("How", "Are", "You")
////        全部改成arrayOf
//        var int_array2:Array<Int> = arrayOf(1, 2, 3)
//        var long_array2:Array<Long> = arrayOf(1, 2, 3)
//        var float_array2:Array<Float> = arrayOf(1.0f, 2.0f, 3.0f)
//        var double_array2:Array<Double> = arrayOf(1.0, 2.0, 3.0)
//        var boolean_array2:Array<Boolean> = arrayOf(true, false, true)
//        var char_array2:Array<Char> = arrayOf('a', 'b', 'c')


//        下面是字符串数组的具体操作代码：
//        btn_string.setOnClickListener {
//            var str:String = ""
//            var i:Int = 0
//            while (i<string_array.size) {
//                str = str + string_array[i] + ", "
//                //数组元素可以通过下标访问，也可通过get方法访问
//                //str = str + string_array.get(i) + ", "
//                i++
//            }
//            tv_item_list.text = str
//        }


        //****   if。。。slse的基本使用

//        var is_odd:Boolean = true;
//        tv_puzzle.text = "凉风有信，秋月无边。打二字"
//        btn_if_simple.setOnClickListener {
//            if (is_odd == true) {
//                tv_answer.text = "凉风有信的谜底是“讽”"
//            } else {
//                tv_answer.text = "秋月无边的谜底是“二”"
//            }
//            is_odd = !is_odd
//        }

        //****   可优化为以下

//        btn_if_simple.setOnClickListener {
//            tv_answer.text = if (is_odd == true) {
//                "凉风有信的谜底是“讽”"
//            } else {
//                "秋月无边的谜底是“二”"
//            }
//            is_odd = !is_odd
//        }

        //****   继续优化为一行代码
//        btn_if_value.setOnClickListener {
//            tv_answer.text = if (is_odd==true) "凉风有信的谜底是“讽”" else "秋月无边的谜底是“二”"
//            is_odd = !is_odd
//        }


        //****多路分支
        var x: Int = 6;
        when (x) {
            1 -> AppLog.e("x is 1")
            2 -> AppLog.e("x is 2")
            3, 4 -> AppLog.e("x is 3 or 4")
            in 5..10 -> AppLog.e("x is 5, 6, 7, 8, 9, or 10")
            else -> AppLog.e("x is out of range")
        }


        fun cases(obj: Any) {
            when (obj) {
                1       -> print("第一项")
                "hello" -> print("这个是字符串hello")
                is Long -> print("这是一个Long类型数据")
                !is String -> print("这不是String类型的数据")
                else    -> print("else类似于Java中的default")
            }
        }


        //****进行分支处理时允许引入变量判断，当然引入具体的运算表达式也是可以的
//        var odd:Int = 0
//        var even:Int = 1
//        btn_when_variable.setOnClickListener {
//            tv_answer.text = when (count) {
//                odd -> "凉风有信的谜底是“讽”"
//                even -> "秋月无边的谜底是“二”"
//                else -> "好诗，这真是一首好诗"
//            }
//            count = (count+1) % 3
//        }



        //****可以使用“in 开始值..结束值”指定区间范围；举一反三，如果要求不在某个区间范围，则使用语句“!in 开始值..结束值”
//        btn_when_region.setOnClickListener {
//            tv_answer.text = when (count) {
//                1,3,5,7,9 -> "凉风有信的谜底是“讽”"
//                in 13..19 -> "秋月无边的谜底是“二”"
//                !in 6..10 -> "当里的当，客官你来猜猜"
//                else -> "好诗，这真是一首好诗"
//            }
//            count = (count+1) % 20
//        }



        //****在变量countType为Long、Double、Float三种类型时做多路处理：
//        var countType:Number;
//        btn_when_instance.setOnClickListener {
//            count = (count+1) % 3
//            countType = when (count) {
//                0 -> count.toLong();
//                1 -> count.toDouble()
//                else -> count.toFloat()
//            }
//            tv_answer.text = when (countType) {
//                is Long -> "此恨绵绵无绝期"
//                is Double -> "树上的鸟儿成双对"
//                else -> "门泊东吴万里船"
//            }
//        }


        val poemArray:Array<String> = arrayOf("朝辞白帝彩云间", "千里江陵一日还", "两岸猿声啼不住", "轻舟已过万重山")
        txtKoltinFor.setOnClickListener {
            var poem:String=""
            for (item in poemArray) {
                poem = "$poem$item，\n"
            }
            txtKoltinFor.text = poem
        }

        //输出示例
        var quantity = 5
        val price: Double = 20.3
        val name: String = "大米"

        println("单价:$price")
        println("数量:$quantity")
        println("产品:$name 总计:${quantity * price}")


        //****in关键字的使用，判断一个对象是否在某一个区间内，可以使用in关键字
//        //如果存在于区间(1,Y-1)，则打印OK
//        if (x in 1..y-1)
//            print("OK")
//
//        //如果x不存在于array中，则输出Out
//        if (x !in 0..array.lastIndex)
//            print("Out")
//
//        //打印1到5
//        for (x in 1..5)
//            print(x)
//
//        //遍历集合(类似于Java中的for(String name : names))
//        for (name in names)
//            println(name)
//
//        //如果names集合中包含text对象则打印yes
//        if (text in names)
//            print("yes")


        //跳转测试
        btnGoGoGo.setOnClickListener {
//            //获取intent对象
//            val intent = Intent()
//            // 获取class是使用::反射
//            intent.setClass(this,KotlinTestAct2::class.java)
//            intent.putExtra("aaa","27")
//            startActivity(intent)

            startActivity<KotlinTestAct2>("aaa" to "欢迎来到Ktolin")
        }

        testFunction("欢迎来到Kotlin")
    }

    fun testFunction(test : String){
        toast(test)
    }
}
