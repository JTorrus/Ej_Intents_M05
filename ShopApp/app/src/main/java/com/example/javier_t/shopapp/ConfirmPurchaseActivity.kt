package com.example.javier_t.shopapp

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_shop.*

class ConfirmPurchaseActivity : AppCompatActivity(), View.OnClickListener {

    val USER_INPUT_ED = "edited_quantity"

    override fun onClick(p0: View?) {
        when (p0?.id) {
            button_to_buy.id -> {
                if (qty_to_buy.selectedItem !=0) {
                    val quantity: Int = qty_to_buy.selectedItem as Int
                    val intent = intent

                    intent.putExtra(USER_INPUT_ED, quantity)

                    setResult(Activity.RESULT_OK, intent)
                    finish()
                } else {
                    val messageKO = resources.getString(R.string.activity_empty_selection)

                    Toast.makeText(applicationContext, messageKO, Toast.LENGTH_LONG).show()
                }

            }

            button_to_cancel.id -> {
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        val quantity = intent.getIntExtra(MainShopActivity::USER_INPUT.get(MainShopActivity()), 0)

        populateSpinner()

        qty_to_buy.setSelection(quantity)
        button_to_buy.setOnClickListener(this)
        button_to_cancel.setOnClickListener(this)
    }

    private fun populateSpinner() {
        val adapter = ArrayAdapter<Int>(applicationContext, android.R.layout.simple_spinner_dropdown_item)

        for (key in 0..MainShopActivity::stock.get(MainShopActivity())) {
            adapter.add(key)
        }

        qty_to_buy.adapter = adapter
    }
}
