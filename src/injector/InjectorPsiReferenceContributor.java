package injector;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

public class InjectorPsiReferenceContributor extends PsiReferenceProvider {

    @NotNull
    @Override
    public PsiReference[] getReferencesByElement(PsiElement psiElement, ProcessingContext context) {
        String path = psiElement.getText();
        if (isInjectCall(psiElement)) {
            PsiReference ref = new InjectReference(psiElement, new TextRange(1, path.length() - 1));
            return new PsiReference[] {ref};
        }

        return new PsiReference[0];
    }

    private boolean isInjectCall(PsiElement psiElement) {
        return false;
    }
}
