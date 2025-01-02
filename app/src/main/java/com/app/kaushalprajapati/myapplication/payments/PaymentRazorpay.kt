package com.app.kaushalprajapati.myapplication.payments
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.kaushalprajapati.myapplication.R
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject

class PaymentRazorpay : AppCompatActivity(),PaymentResultListener {

    private var KEYRAZORPAY: String = "rzp_test_AIHnL4LWr9Dokc"
    private val amount:Double = 0.0
    private var currentBalance:Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Get the current balance passed from HomeScreen
        currentBalance = intent.getDoubleExtra("currentBalance", 0.0)

        startPayment(amount=200.0, currentBalance)
    }
    // Function to start Razorpay payment
    private fun startPayment(amount:Double,currentBalance:Double) {
        val checkout = Checkout()
        checkout.setKeyID(KEYRAZORPAY) // Replace with your Razorpay key

        val options = JSONObject()
        try {
            options.put("name", "Crypto Future")
            options.put("description", "Add Funds to Wallet")
            options.put("currency", "INR")
            options.put("amount", (amount * 100).toInt()) // Razorpay uses paise
            options.put("logo", R.drawable.bitcoin)

            val prefill = JSONObject()
            prefill.put("email", "kaushalkumar00277@gmail.com") // Replace with actual email
            prefill.put("contact", "+919953857495") // Replace with actual phone

            options.put("prefill", prefill)

            // Open Razorpay Checkout Activity
            checkout.open(this, options)
        } catch (e: Exception) {
            Log.e("TAG", "Error starting payment: ${e.localizedMessage}")
            Toast.makeText(this, "Payment error: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
        }
    }

    // Callback for successful payment
    override fun onPaymentSuccess(razorpayPaymentId: String?) {
        razorpayPaymentId?.let {

            val updatedBalance = currentBalance + amount

            // Send updated balance back to HomeScreen
            val resultIntent = Intent()
            resultIntent.putExtra("updatedBalance", updatedBalance)
            setResult(RESULT_OK, resultIntent)

            // Close the payment activity and return to HomeScreen
            finish()

            Toast.makeText(this, "Payment Successful! Wallet Balance Updated.", Toast.LENGTH_SHORT)
                .show()
            Log.d("Razorpay", "Payment success with ID: $razorpayPaymentId")

        }
    }

    // Callback for payment failure
    override fun onPaymentError(code: Int, response: String?) {
        Log.e("TAG", "Payment Failed: $response")
        Toast.makeText(this, "Payment Failed: $response", Toast.LENGTH_SHORT).show()
        Log.d("Razorpay", "Payment error: $response")
    }

}