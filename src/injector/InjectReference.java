package injector;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InjectReference implements PsiReference {
    PsiElement element;
    TextRange textRange;

    public InjectReference(PsiElement psiElement, TextRange textRange) {
        this.element = psiElement;
        this.textRange = textRange;
    }

    @Nullable
    @Override
    public PsiElement resolve() {

        return null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return new Object[0];
    }

    @Override
    public PsiElement getElement() {
        return this.element;
    }

    @Override
    public TextRange getRangeInElement() {
        return this.textRange;
    }

    @NotNull
    @Override
    public String getCanonicalText() {
        return this.element.getText();
    }

    @Override
    public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
        throw new IncorrectOperationException();
    }

    @Override
    public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException {
        throw new IncorrectOperationException();
    }

    @Override
    public boolean isReferenceTo(PsiElement element) {
        return false;
    }

    @Override
    public boolean isSoft() {
        return false;
    }
}
