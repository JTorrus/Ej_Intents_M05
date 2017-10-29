package com.example.javier_t.shopapp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_shop.*

class MainShopActivity : AppCompatActivity(), View.OnClickListener {
    val USER_INPUT = "selected_quantity"
    val stock = 25

    override fun onClick(p0: View?) {
        if  (p0?.id == button_to_buy.id) {
            if (qty_to_buy.selectedItem != 0) {
                val intent = Intent(this@MainShopActivity, ConfirmPurchaseActivity::class.java)
                val quantity: Int = qty_to_buy.selectedItem as Int

                intent.putExtra(USER_INPUT, quantity)

                startActivityForResult(intent, 0)
            } else {
                val messageKO = resources.getString(R.string.activity_empty_selection)

                Toast.makeText(applicationContext, messageKO, Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        populateSpinner()

        button_to_cancel.visibility = GONE
        button_to_buy.setOnClickListener(this)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                val quantity: Int = data!!.getIntExtra(ConfirmPurchaseActivity::USER_INPUT_ED.get(ConfirmPurchaseActivity()), 0)
                val messageOk = resources.getString(R.string.activity_messageOK, quantity)

                qty_to_buy.setSelection(quantity)

                Toast.makeText(applicationContext, messageOk, Toast.LENGTH_LONG).show()
            } else if (resultCode == Activity.RESULT_CANCELED) {
                val messageKO = resources.getString(R.string.activity_messageKO)

                Toast.makeText(applicationContext, messageKO, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun populateSpinner() {
        val adapter = ArrayAdapter<Int>(applicationContext, android.R.layout.simple_spinner_dropdown_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        for (key in 0..stock) {
            adapter.add(key)
        }

        qty_to_buy.adapter = adapter
    }
}
