import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.example.newvivamacho.MainActivity
import com.example.newvivamacho.ui.add.AddFragment


class TimePick : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    // Bundle sould be nullable, Bundle?
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // to initialize a Calender instance
        val c = java.util.Calendar.getInstance()

        // at the first, to get the system current hour and minute
        val hour = c.get(java.util.Calendar.HOUR_OF_DAY)
        val minute = c.get(java.util.Calendar.MINUTE)

        return TimePickerDialog(
            activity,
            // in order to return parameters to MainActivity
            android.R.style.Theme_Material_Dialog,
            activity as MainActivity?,
            hour,
            minute,
            true)
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        //
    }
}