package util

import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

interface SimpleDocumentListener : DocumentListener {

    fun update(e: DocumentEvent?)

    override fun changedUpdate(e: DocumentEvent?) = update(e)

    override fun insertUpdate(e: DocumentEvent?) = update(e)

    override fun removeUpdate(e: DocumentEvent?) = update(e)
}
