package bmccalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import bmccalculator.BMC_Calculator_Activity.Companion.IMC_KEY
import com.example.appbodymassindexcalculator_xml.R

class ResultIMCActivity : AppCompatActivity() {

    private lateinit var TvStatus:TextView
    private lateinit var TvNumberIMC:TextView
    private lateinit var TvDescripcion:TextView
    private lateinit var btnReCalculate:Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_imcactivity)

        val result:Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponent()
        initUI(result)
        initListeners()
    }

    private fun initListeners() {
        btnReCalculate.setOnClickListener{onBackPressed()}
    }

    private fun initUI(result: Double) {
        TvNumberIMC.text = result.toString()
        when(result){
            in 0.0..18.50 -> { //Bajo peso
                TvStatus.text = getString(R.string.title_bajo_peso)
                TvStatus.setTextColor(ContextCompat.getColor(this, R.color.peso_bajo))
                TvDescripcion.text = getString(R.string.description_bajo_peso)
            }
            in 18.51..24.99 -> { // Peso normal
                TvStatus.text = getString(R.string.title_normal)
                TvStatus.setTextColor(ContextCompat.getColor(this, R.color.peso_normal))
                TvDescripcion.text = getString(R.string.description_peso_normal)
            }
            in 25.0..29.99 -> { //Sobre peso
                TvStatus.text = getString(R.string.title_sobrepeso)
                TvStatus.setTextColor(ContextCompat.getColor(this, R.color.sobrepeso))
                TvDescripcion.text = getString(R.string.description_sobrepeso)
            }
            in 30.0..99.99 -> { //Obesidad
                TvStatus.text = getString(R.string.title_obesidad)
                TvStatus.setTextColor(ContextCompat.getColor(this, R.color.obesidad))
                TvDescripcion.text = getString(R.string.description_obesidad)
            }
            else -> { //error
                TvStatus.text = getString(R.string.error)
                TvNumberIMC.text = getString(R.string.error)
                TvDescripcion.text = getString(R.string.error)
            }
        }
    }


    private fun initComponent(){
        TvStatus = findViewById(R.id.TvStatus)
        TvNumberIMC = findViewById(R.id.TvNumberIMC)
        TvDescripcion = findViewById(R.id.TvDescripcion)
        btnReCalculate = findViewById(R.id.btnReCalculate)
    }
}