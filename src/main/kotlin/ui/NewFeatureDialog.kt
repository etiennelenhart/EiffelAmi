package ui

import com.intellij.openapi.ui.DialogWrapper
import javax.swing.JPanel
import javax.swing.JTextField

class NewFeatureDialog : DialogWrapper(true) {

    private lateinit var contentPanel: JPanel
    private lateinit var nameField: JTextField

    val featureName: String
        get() = nameField.text

    init {
        init()
        title = "New Eiffel Feature"
        setOKButtonText("Create")
    }

    override fun createCenterPanel() = contentPanel

    override fun getPreferredFocusedComponent() = nameField
}
