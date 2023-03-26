package com.example.btgk_quanlyhocphan_21it274

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.btgk_quanlyhocphan_21it274.adpater.ListHpRVAdapter_21IT274
import com.example.btgk_quanlyhocphan_21it274.databinding.ActivityMainBinding
import com.example.btgk_quanlyhocphan_21it274.model.HocPhan
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    private lateinit var listHp: ArrayList<HocPhan>
    private lateinit var binding: ActivityMainBinding
    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this, InsertionActivity_21IT274::class.java))
        }
        binding.listHp.layoutManager = LinearLayoutManager(this)
        binding.listHp.setHasFixedSize(true)
        listHp = arrayListOf<HocPhan>()
        listData()
    }
    private fun listData() {
        dbRef = FirebaseDatabase.getInstance().getReference("HocPhan")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listHp.clear()
                if (snapshot.exists()){
                    for (HpSnap in snapshot.children){a
                        val hPData = HpSnap.getValue(HocPhan::class.java)
                        listHp.add(hPData!!)
                    }
                    val mAdapter = ListHpRVAdapter_21IT274(listHp)
                    binding.listHp.adapter = mAdapter
                    mAdapter.setOnItemClickListener(object : ListHpRVAdapter_21IT274.OnItemClickListener {
                        override fun onItemClick(position: Int) {
                            val intent =
                                Intent(this@MainActivity, DetailActivity_21IT274::class.java)

                            intent.putExtra("id",listHp[position].maHP)
                            intent.putExtra("ten",listHp[position].tenHP)
                            intent.putExtra("sotinchi",listHp[position].soTC.toString())
                            intent.putExtra("loai",listHp[position].loai)
                            startActivity(intent)
                        }
                    })

                }
            }
            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}