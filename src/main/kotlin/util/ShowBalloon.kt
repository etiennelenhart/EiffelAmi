package util

import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project

val notificationGroup = NotificationGroup("eiffelami", NotificationDisplayType.BALLOON, true)

fun Project.showWarningBalloon(title: String, content: String) = showBalloon(title, content, NotificationType.WARNING)

fun Project.showErrorBalloon(title: String, content: String) = showBalloon(title, content, NotificationType.ERROR)

fun Project.showBalloon(title: String, content: String, type: NotificationType) {
    notificationGroup.createNotification(title, content, type, null).notify(this)
}
