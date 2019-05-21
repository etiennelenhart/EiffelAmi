package ui

import com.intellij.openapi.ui.DialogWrapper
import feature.FeatureConfig
import util.SimpleDocumentListener
import javax.swing.JCheckBox
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.event.DocumentEvent

class NewFeatureDialog(private val config: FeatureConfig) : DialogWrapper(true) {

    private lateinit var contentPanel: JPanel
    private lateinit var nameField: JTextField
    private lateinit var factoryCheckBox: JCheckBox

    init {
        init()

        title = "New Eiffel Feature"
        isOKActionEnabled = false

        config.run {
            factoryCheckBox.isSelected = generateFactory
        }
        nameField.document!!.addDocumentListener(object : SimpleDocumentListener {
            override fun update(e: DocumentEvent?) {
                isOKActionEnabled = nameField.text.isNotBlank()
            }
        })

        setOKButtonText("Create")
    }

    override fun createCenterPanel() = contentPanel

    override fun getPreferredFocusedComponent() = nameField

    override fun doOKAction() {
        config.run {
            name = nameField.text
            generateFactory = factoryCheckBox.isSelected
        }
        super.doOKAction()
    }
}
