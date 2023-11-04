package samad.cgm

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val enterPerson: EditText = findViewById(R.id.enterPerson)
        val addPerson: Button = findViewById(R.id.add)
        val groupsTextView: TextView = findViewById(R.id.textView3)
        val groupSizeEditText: EditText = findViewById(R.id.groupSizeEditText)

        addPerson.setOnClickListener(View.OnClickListener {
            // Move this line inside the OnClickListener to get the updated value
            val enterPerson1 = enterPerson.text.toString()

            val personList = enterPerson1.split(",")
            val shuffledNames = personList.shuffled()
            val groupSizeText = groupSizeEditText.text.toString()
            val groupSize = groupSizeText.toIntOrNull()

            if (groupSize != null && groupSize > 0) {
                val groups = shuffledNames.chunked(groupSize)

                val groupsText = buildGroupsText(groups)
                groupsTextView.text = groupsText
            } else {
                groupsTextView.text = "Invalid group size"
            }
        })
    }

    private fun buildGroupsText(groups: List<List<String>>): String {
        val stringBuilder = StringBuilder()

        for ((index, group) in groups.withIndex()) {
            stringBuilder.append("Group ${index + 1}:\n")
            for (name in group) {
                stringBuilder.append("$name\n")
            }
            stringBuilder.append("\n") // Add a blank line between groups
        }

        return stringBuilder.toString()
    }
}
