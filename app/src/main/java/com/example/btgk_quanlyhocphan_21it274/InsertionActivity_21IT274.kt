package com.example.btgk_quanlyhocphan_21it274

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import com.example.btgk_quanlyhocphan_21it274.databinding.ActivityInsertionActivity21It274Binding
import com.example.btgk_quanlyhocphan_21it274.model.HocPhan
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InsertionActivity_21IT274 : AppCompatActivity() {
    private lateinit var binding: ActivityInsertionActivity21It274Binding
    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertionActivity21It274Binding.inflate(layoutInflater)
        setContentView(binding.root)
        dbRef = FirebaseDatabase.getInstance().getReference("HocPhan")

        binding.btnInsert.setOnClickListener {
            themHocPhan()
        }
    }

    private fun themHocPhan() {
        val idHp = binding.idHp.text.toString()
        val tenHp = binding.tenHp.text.toString()
        val sotinchi = binding.soTinChi.text.toString().toInt()
        val rg = binding.loai
        val loaiHp = rg.findViewById<RadioButton>(rg.checkedRadioButtonId).text.toString()
        val hocphan = HocPhan(idHp, tenHp, sotinchi, loaiHp)

        if (idHp.isEmpty()) {
            binding.idHp.error = "Hãy nhập ID"
            return
        } else
        if (tenHp.isEmpty()) {
            binding.tenHp.error = "Hãy nhập  Tên Học Phần"
            return
        } else
        if (sotinchi.toString().isEmpty() || sotinchi > 5) {
            binding.soTinChi.error = "Hãy nhập số tín chỉ <= 5"
            return
        } else {
            dbRef.child(idHp).setValue(hocphan).addOnCompleteListener {
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show()
                binding.idHp.setText("")
                binding.tenHp.setText("")
                binding.soTinChi.setText("")
                startActivity(Intent(this, MainActivity::class.java))
            }.addOnFailureListener { err ->
                Toast.makeText(this, "Loi ${err.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
