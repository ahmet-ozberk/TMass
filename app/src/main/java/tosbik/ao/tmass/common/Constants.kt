package tosbik.ao.tmass.common

import tosbik.ao.tmass.R

object Constants {
    val onboard1 = OnboardDataModel(
        title = "Easy Time Management",
        description = "With management based on priority and daily tasks, it will give you convenience in managing and determining the tasks that must be done first",
        image = R.drawable.onboard1,
    )
    val onboard2 = OnboardDataModel(
        title = "Increase Work Effectiveness",
        description = "Time management and the determination of more important tasks will give your job statistics better and always improve",
        image = R.drawable.onboard2,
    )
    val onboard3 = OnboardDataModel(
        title = "Reminder Notification",
        description = "The advantage of this application is that it also provides reminders for you so you don't forget to keep doing your assignments well and according to the time you have set",
        image = R.drawable.onboard3,
    )
}


data class OnboardDataModel(
    val title: String = "Onboard",
    val description: String = "Onboard Description",
    val image: Int = 0
)