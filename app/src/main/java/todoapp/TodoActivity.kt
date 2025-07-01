package todoapp

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appbodymassindexcalculator_xml.R
import com.example.appbodymassindexcalculator_xml.ResultActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import todoapp.TaskCategory.*

class TodoActivity : AppCompatActivity() {
    private val categories = listOf(
        Business,
        Personal,
        Other
    )
    private val tasks = mutableListOf(
        Task("PruebaBisness", Business),
        Task("PruebaPersonal", Personal),
        Task("PruebaOtros", Other),
    )


    private lateinit var rvCategorie: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter

    private lateinit var rvTask: RecyclerView
    private lateinit var taskAdapter: TaskAdapter

    private lateinit var fabAddTask: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_todo)
        initComponent()
        initUI()
        initListeners()

    }

    private fun initListeners() {
        fabAddTask.setOnClickListener { showDialog() }
    }

    private fun showDialog() {

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)
        val btnAddTask: Button = dialog.findViewById(R.id.btnAddTask)
        val etTask: EditText = dialog.findViewById(R.id.etTask)
        val rgCategories: RadioGroup = dialog.findViewById(R.id.rgCategories)

        btnAddTask.setOnClickListener {
            val TaskCategory = etTask.text.toString()
            if (TaskCategory.isNotEmpty()) {
                val selectedID = rgCategories.checkedRadioButtonId
                val selectedRadioButton: RadioButton = rgCategories.findViewById(selectedID)
                val currentCategory: TaskCategory = when (selectedRadioButton.text) {
                    getString(R.string.todo_dialog_category_business) -> Business
                    getString(R.string.todo_dialog_category_personal) -> Personal
                    else -> {
                        Other
                    }
                }

                tasks.add(Task(etTask.text.toString(), currentCategory))
                updateTask()
                dialog.hide()
            }

        }


        dialog.show()

    }


    private fun initComponent() {
        rvCategorie = findViewById(R.id.rvCategories)
        rvTask = findViewById(R.id.rvTasks)
        fabAddTask = findViewById(R.id.fabAddTask)

    }


    private fun initUI() {
        categoriesAdapter = CategoriesAdapter(categories) { position -> updateCategories(position) }
        rvCategorie.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategorie.adapter = categoriesAdapter


        taskAdapter = TaskAdapter(tasks) { position -> onItemSelected(position) }
        rvTask.layoutManager = (LinearLayoutManager(this))
        rvTask.adapter = taskAdapter

    }

    private fun onItemSelected(position: Int) {
        tasks[position].isSelected = !tasks[position].isSelected
        updateTask()
    }

    private fun updateCategories(position: Int) {
        categories[position].isSelected = !categories[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
        updateTask()
    }

    private fun updateTask() {
        val selectedCategories: List<TaskCategory> = categories.filter { it.isSelected }
        val newTask = tasks.filter { selectedCategories.contains(it.category) }
        taskAdapter.tasks = newTask
        taskAdapter.notifyDataSetChanged()
    }


}