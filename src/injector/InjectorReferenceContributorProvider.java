package injector;

import com.intellij.patterns.StandardPatterns;
import com.intellij.psi.PsiReferenceContributor;
import com.intellij.psi.PsiReferenceRegistrar;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.javascript.psi.JSLiteralExpression;

public class InjectorReferenceContributorProvider extends PsiReferenceContributor {
    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        InjectorPsiReferenceContributor provider = new InjectorPsiReferenceContributor();
        registrar.registerReferenceProvider(StandardPatterns.instanceOf(JSLiteralExpression.class), provider);
    }
}
