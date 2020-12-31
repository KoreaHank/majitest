package com.getmaji.majitest.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.getmaji.majitest.R
import com.getmaji.majitest.repository.test.TestData
import com.getmaji.majitest.repository.test.TestDataBase
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    val REQUEST_CODE = 1
    lateinit var mContext: Context
    lateinit var mAdapter:MyAdapter
    lateinit var mViewModel: SecondViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(SecondViewModel::class.java)
        mViewModel.run {
            mResult.observe(viewLifecycleOwner, Observer {
                mAdapter.setNewData(it)
            })
        }
        listview.layoutManager = LinearLayoutManager(mContext)
        mAdapter = MyAdapter()
        listview.adapter = mAdapter

        if (mContext.checkCallingOrSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            mViewModel.dao = TestDataBase.getDatabase(mContext).testDao()
        } else {
            val permissions= arrayOf<String>(Manifest.permission.READ_EXTERNAL_STORAGE)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permissions, REQUEST_CODE)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mViewModel.getData()
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mViewModel.dao = TestDataBase.getDatabase(mContext).testDao()
            }
        }
    }




    inner class MyAdapter : RecyclerView.Adapter<MyAdapter.MyHolder>() {
        private var mList: List<TestData>? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
            var view = LayoutInflater.from(mContext).inflate(R.layout.item_data, parent, false)
            return MyHolder(view)
        }

        override fun getItemCount(): Int = mList?.size!!

        override fun onBindViewHolder(holder: MyHolder, position: Int) {
            holder?.textview?.text = mList!![position].toString()
        }

        fun setNewData(list:List<TestData>?){
            mList = list
            notifyDataSetChanged()
        }

        inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var textview: TextView = itemView!!.findViewById(R.id.tv_data)
        }
    }
}