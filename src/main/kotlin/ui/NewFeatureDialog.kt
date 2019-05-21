package ui

import com.intellij.openapi.ui.DialogWrapper
import component.EiffelFeatureState
import util.SimpleDocumentListener
import java.awt.event.ItemEvent
import javax.swing.JCheckBox
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.event.DocumentEvent

class NewFeatureDialog(private val state: EiffelFeatureState) : DialogWrapper(true) {

    private lateinit var contentPanel: JPanel
    private lateinit var nameField: JTextField
    private lateinit var interceptionsCheckBox: JCheckBox
    private lateinit var factoryCheckBox: JCheckBox

    init {
        init()

        title = "New Eiffel Feature"
        isOKActionEnabled = false

        state.run {
            interceptionsCheckBox.isSelected = addInterceptions
            factoryCheckBox.isSelected = generateFactory
            factoryCheckBox.isEnabled = !addInterceptions
        }

        nameField.document!!.addDocumentListener(object : SimpleDocumentListener {
            override fun update(e: DocumentEvent?) {
                isOKActionEnabled = nameField.text.isNotBlank()
            }
        })
        interceptionsCheckBox.addItemListener { factoryCheckBox.isEnabled = it.stateChange == ItemEvent.DESELECTED }

        setOKButtonText("Create")
    }

    override fun createCenterPanel() = contentPanel

    override fun getPreferredFocusedComponent() = nameField

    override fun doOKAction() {
        state.run {
            name = nameField.text
            addInterceptions = interceptionsCheckBox.isSelected
            generateFactory = factoryCheckBox.isSelected
        }
        super.doOKAction()
    }
}
