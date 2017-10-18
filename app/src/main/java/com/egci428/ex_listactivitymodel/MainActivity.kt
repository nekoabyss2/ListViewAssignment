package com.egci428.ex_listactivitymodel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.course_item.view.*


class MainActivity : AppCompatActivity() {

    val DETAIL_REQUEST_CODE = 1001
    protected var data: ArrayList<Course>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        data = DataProvider.getData()

        val courseArrayAdapter = CourseArrayAdapter(this,0,data!!)//this is MainActivity
        list.setAdapter(courseArrayAdapter)

        list.setOnItemClickListener { adapterView, view, position,id ->
            val course = data!!.get(position)
            displayDetail(course,position)


        }
    }

    private fun displayDetail(course: Course, position: Int) { //เราสร้างเอง เพราะมีการส่งค่าหลายเลยสร้างเพิ่ม ไม่เอาไปไว้ใน onCreate

        val intent = Intent(this,DetailActivity::class.java) //(start,terminal)

        intent.putExtra("title",course.title);
        intent.putExtra("courseNum",course.courseNumber);
        intent.putExtra("credit",course.credits);
        intent.putExtra("des",course.description);
        intent.putExtra("image",position);
        startActivity(intent)
    }

    private class CourseArrayAdapter(var context: Context, resource: Int, var objects: ArrayList<Course>) : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view: View      //view of each row
            val course = objects[position]
            //ตัวแปรในฟังก์ชันสามารถประกาศซ้ำได้
            if(convertView == null) {
                val layoutInflator = LayoutInflater.from(parent!!.context)
                view = layoutInflator.inflate(R.layout.course_item,parent,false)
                val viewHolder = ViewHolder(view.titleText,view.imageCourse)
                view.tag = viewHolder //update in layout
                //ตัวแปรที่ถูกประกาศใน condition จะจบในนี้ส่งออกข้างนอกไม่ได้

            }else{
                view = convertView
            }
            val viewHolder = view.tag as ViewHolder //don't confused
            viewHolder.titleTextView.text = course.title

            val impos = position%3+1
            val res = context.resources.getIdentifier("image1010"+impos,"drawable",context.packageName)
            viewHolder.imageCouse.setImageResource(res)

            return view
        }


        override fun getCount(): Int {
            return objects.size //คืนจำนวน item ที่อยู่ใน array ออกมา
        }

        override fun getItem(position: Int): Any {
            return objects[position] //click ที่อันไหนก็คืนค่าตำแหน่งนั้นมา
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }
        private class ViewHolder(val titleTextView: TextView, val imageCouse: ImageView)
    }

}