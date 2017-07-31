package com.rongyi.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.rx.RxDao;

import java.util.List;
import java.util.Queue;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private DaoSession mDaoSession;
    private StudentMsgBeanDao mMsgBeanDao;
    private RecyclerView mRecyclerView;
    private List<StudentMsgBean> mList;
    private DaoAdapter mDaoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);


        //数据库初始化
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "student.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        mDaoSession = daoMaster.newSession();
        mMsgBeanDao = mDaoSession.getStudentMsgBeanDao();


        //增加
        findViewById(R.id.btnZ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StudentMsgBean studentMsgBean = new StudentMsgBean();
                studentMsgBean.setName("zone");
                studentMsgBean.setStudentNum("123456");
                mMsgBeanDao.insert(studentMsgBean);

                Query<StudentMsgBean> query = mMsgBeanDao.queryBuilder().where(StudentMsgBeanDao.Properties.Id.isNotNull()).build();
                mList = query.list();

                mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                mDaoAdapter = new DaoAdapter(MainActivity.this, mList);
                mRecyclerView.setAdapter(mDaoAdapter);
            }
        });


        //删除
        findViewById(R.id.btnS).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                List<StudentMsgBean> list = mMsgBeanDao.queryBuilder()
                        .build().list();
                for (int i = 0; i < list.size(); i++) {
                    Log.d("zoneLog", "studentNumber: " + list.get(i).getStudentNum());
                    Log.d("zoneLog", "name: " + list.get(i).getName());
                    if (i == 0) {
                        mMsgBeanDao.deleteByKey(list.get(0).getId());//通过 Id 来删除数据
//                        msgBeanDao.delete(list.get(0));//通过传入实体类的实例来删除数据
                    }

                }


                Query<StudentMsgBean> query = mMsgBeanDao.queryBuilder().where(StudentMsgBeanDao.Properties.Id.isNotNull()).build();
                List<StudentMsgBean> listS = query.list();
                mList.clear();
                mList.addAll(listS);
                mDaoAdapter.notifyDataSetChanged();

            }
        });


        //修改
        findViewById(R.id.btnG).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                List<StudentMsgBean> list = mMsgBeanDao.queryBuilder()
                        .build().list();


                for (int i = 0; i < list.size(); i++) {
                    if (i == 10) {
                        list.get(i).setName("yikwing");
                        mMsgBeanDao.update(list.get(i));
                    }
                }


                Query<StudentMsgBean> query = mMsgBeanDao.queryBuilder().where(StudentMsgBeanDao.Properties.Id.isNotNull()).build();
                List<StudentMsgBean> listG = query.list();
                mList.clear();
                mList.addAll(listG);
                mDaoAdapter.notifyDataSetChanged();


            }
        });


        //查找
        findViewById(R.id.btnC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<StudentMsgBean> list = mMsgBeanDao.queryBuilder()
                        .offset(2)//偏移量，相当于 SQL 语句中的 skip
                        .limit(4)//只获取结果集的前 3 个数据
                        .orderAsc(StudentMsgBeanDao.Properties.StudentNum)//通过 StudentNum 这个属性进行正序排序
                        .where(StudentMsgBeanDao.Properties.Name.eq("zone"))//数据筛选，只获取 Name = "zone" 的数据。
                        .build()
                        .list();

                mList.clear();
                mList.addAll(list);

                mDaoAdapter.notifyDataSetChanged();
            }
        });

    }
}
