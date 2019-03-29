package util

import com.intellij.psi.PsiDirectory
import org.w3c.dom.Element
import javax.xml.parsers.DocumentBuilderFactory

fun PsiDirectory.createOrGetSubdirectory(name: String) = findSubdirectory(name) ?: createSubdirectory(name)

fun PsiDirectory.manifestPackage(): String? {
    val builder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
    val document = builder.parse(findFile("AndroidManifest.xml")?.virtualFile?.inputStream)

    return document.getElementsByTagName("manifest").item(0)?.let {
        (it as? Element)?.getAttribute("package")
    }
}
