package com.example.manageyourcar.UIlayer.view.common

import android.app.DatePickerDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import java.util.Calendar
import java.util.Date


@Composable
fun CalendarView(
    onDateSelected: (String) -> Unit
) {
    val mContext = LocalContext.current
    val mYear: Int
    val mMonth: Int
    val mDay: Int
    val mCalendar = Calendar.getInstance()
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    mCalendar.time = Date()

    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _, mYear, mMonth, mDayOfMonth ->
            onDateSelected("$mDayOfMonth/${mMonth + 1}/$mYear")
        }, mYear, mMonth, mDay
    )

    mDatePickerDialog.show()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCalender() {
    CalendarView(onDateSelected = {})
}