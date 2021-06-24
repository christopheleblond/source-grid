import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

/**
 * Entrypoint
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException {
        // Configure JavaParser to use type resolution
        URL javaSourceFile = App.class.getResource("sources/SampleA.java");

        JavaParser javaParser = new JavaParser();

        ParseResult<CompilationUnit> parseResults = javaParser.parse(new File(javaSourceFile.getFile()));

        parseResults.getResult().ifPresent(compilationUnit -> {

            compilationUnit.getPackageDeclaration().ifPresent(packageDeclaration -> System.out.println("Package: " + packageDeclaration));
            compilationUnit.getPrimaryType().ifPresent(typeDeclaration -> {
                typeDeclaration.getFields().forEach(field -> System.out.println("Field: " + field));
                typeDeclaration.getMethods().forEach(method -> System.out.println("Method: " + method.getNameAsString()));
            });
        });
    }
}
