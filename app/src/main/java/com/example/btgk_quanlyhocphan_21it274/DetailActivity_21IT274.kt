package com.example.btgk_quanlyhocphan_21it274

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.btgk_quanlyhocphan_21it274.databinding.ActivityDetailActivity21It274Binding
import com.google.firebase.database.FirebaseDatabase

class DetailActivity_21IT274 : AppCompatActivity() {
    private lateinit var binding: ActivityDetailActivity21It274Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailActivity21It274Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setValueToView()
        binding.btnDelete.setOnClickListener {
            deleteRecord(intent.getStringExtra("id").toString())
        }



    }

    private fun deleteRecord(id: String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("HocPhan").child(id)
        val mTask = dbRef.removeValue()
        mTask.addOnSuccessListener {
            Toast.makeText(this, "Đã xóa", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { err ->
            Toast.makeText(this, "Lỗi ${err.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setValueToView() {
        binding.textIDHp.text = intent.getStringExtra("id")
        binding.textTenHp.text = intent.getStringExtra("ten")
        binding.textSoTinChi.text = intent.getStringExtra("sotinchi")
        binding.textLoai.text = intent.getStringExtra("loai")
    }
}